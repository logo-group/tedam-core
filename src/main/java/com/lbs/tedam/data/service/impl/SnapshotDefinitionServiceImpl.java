/*
 * Copyright 2014-2019 Logo Business Solutions
 * (a.k.a. LOGO YAZILIM SAN. VE TIC. A.S)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.lbs.tedam.data.service.impl;

import com.lbs.tedam.data.dao.SnapshotDefinitionDAO;
import com.lbs.tedam.data.service.PropertyService;
import com.lbs.tedam.data.service.SnapshotDefinitionService;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.exception.localized.SnapshotFileNotFoundException;
import com.lbs.tedam.generator.steptype.AbstractFillTestStepGenerator;
import com.lbs.tedam.generator.steptype.Generator;
import com.lbs.tedam.generator.steptype.GeneratorFactory;
import com.lbs.tedam.model.*;
import com.lbs.tedam.util.Constants;
import com.lbs.tedam.util.EnumsV2.TestStepType;
import com.lbs.tedam.util.HasLogger;
import com.lbs.tedam.util.TedamDOMUtils;
import com.lbs.tedam.util.TedamXPathUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;

@Service
public class SnapshotDefinitionServiceImpl extends BaseServiceImpl<SnapshotDefinition, Integer> implements SnapshotDefinitionService, HasLogger {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    public void setDao(SnapshotDefinitionDAO dao) {
        super.setBaseDao(dao);
    }

    @Override
    public int save(SnapshotDefinition snapshotDefinition, String filename) throws LocalizedException {

        List<String> parentIDList = new ArrayList<String>();
        // The Value list is edited and added to the SnapshotDefinition object that should be attached.
        for (SnapshotValue snapshotValue : snapshotDefinition.getSnapshotValues()) {
            if (!snapshotValue.getParentTag().equals(Constants.VALUE_NULL) && snapshotValue.getRowIndex() != Constants.VALUE_CONTROL_ROWID
                    && snapshotValue.getRowIndex() != Constants.VALUE_FILTER_ROWID && !parentIDList.contains(snapshotValue.getParentTag())) {
                parentIDList.add(snapshotValue.getParentTag());
            }
        }

        Document doc = TedamDOMUtils.domParserStarter(filename);
        if (doc == null)
            throw new SnapshotFileNotFoundException(filename);

        // The Snapshot object is edited and added to the SnapshotDefinition object that should be attached.
        Snapshot snapshot = new Snapshot();
        snapshot.setXml(TedamDOMUtils.elementToString(doc.getDocumentElement()));
        snapshot.setVersion(TedamXPathUtils.getVersionFromSnapshot(doc.getDocumentElement()));
        snapshot.setFileName(filename);
        snapshotDefinition.setSnapshot(snapshot);

        // Possible Property objects are retrieved from the document and made ready for save operation.
        // If the SplitterIndex -1 is a different value, the property table splitterIndex is processed in the appropriate format.
        List<Property> propertyList = new ArrayList<Property>();
        for (int i = 0; i < parentIDList.size(); i++) {
            Property property = new Property();
            String propertyValue = TedamXPathUtils.getPropertValueFromDocument(doc, parentIDList.get(i));
            if (propertyValue != null && Integer.valueOf(propertyValue) != -1) {
                property.setName(Constants.PROPERTY_SPLITTER);

                property.setParameter(parentIDList.get(i));
                property.setValue(propertyValue);

                propertyList.add(property);
            }
        }

        int snapshotDefinitionId = save(snapshotDefinition).getId();
        propertyList.forEach(property -> property.setParameter(snapshotDefinitionId + "," + property.getParameter()));
        List<Property> propertyListInDB = propertyService.getPropertyListByCriteria(Constants.PROPERTY_SPLITTER, String.valueOf(snapshotDefinitionId), true);
        List<Property> redefinePropertyList = propertyService.redefinePropertyList(propertyList, propertyListInDB);
        for (Property property : redefinePropertyList) {
            propertyService.save(property);
        }
        return snapshotDefinitionId;

    }

    @Override
    public void createLookupParameterContent(SnapshotDefinition snapshotDefinition, TestCase newTestCase) throws LocalizedException {
        for (SnapshotValue snapshotValue : snapshotDefinition.getSnapshotValues()) {
            if (StringUtils.isEmpty(snapshotValue.getLookUpParameter()) || snapshotValue.getLookUpParameter().equals(Constants.VALUE_NULL)) {
                continue;
            }
            StringBuilder sbLookupParameter = new StringBuilder();
            String[] lookupParameterArray = snapshotValue.getLookUpParameter().split("</(\\w+)>");
            for (String lookupParameter : lookupParameterArray) {
                String newLookupParameter = createNewLookUpParameter(newTestCase, lookupParameter);
                sbLookupParameter.append(newLookupParameter);
            }
            snapshotValue.setLookUpParameter(sbLookupParameter.toString());
        }
    }

    // the generator is created by determining which TestStep belongs to the incoming lookupParameter. The lookupParameter is fragmented and cloned to create the new one.
    private String createNewLookUpParameter(TestCase newTestCase, String lookupParameter) throws LocalizedException {
        for (TestStepType testStepType : TestStepType.getLookupParameterIsNotEmptyTestStepTypes()) {
            if (lookupParameter.startsWith(testStepType.getBeginRegex())) {
                Generator generator = GeneratorFactory.getGenerator(testStepType, beanFactory);
                generator.degenerateLookUp(lookupParameter);
                if (generator instanceof AbstractFillTestStepGenerator) {
                    doFillTestStepOperations(newTestCase, generator);
                }
                return generator.generateLookUp();
            }
        }
        return "";
    }

    private void doFillTestStepOperations(TestCase newTestCase, Generator generator) throws LocalizedException {
        // The clone operation is performed and saved according to the snapshotDefinitionId contained in the lookup.
        SnapshotDefinition tempSnapshotDefinition = ((AbstractFillTestStepGenerator) generator).getSnapshotDefinition();
        SnapshotDefinition newSnapshotDefinition = tempSnapshotDefinition.cloneSnapshotDefinition();
        newSnapshotDefinition = save(newSnapshotDefinition);
        ((AbstractFillTestStepGenerator) generator).setSnapshotDefinition(newSnapshotDefinition);
        findAndUpdateLookUp(String.valueOf(tempSnapshotDefinition.getId()), String.valueOf(newSnapshotDefinition.getId()), newTestCase.getLookUps());
    }

    /**
     * this method findAndUpdateLookUp changes the old snapshotDefinitionId and the newly created snapshotDefinitionId to change the lookup parameters of testCase.<br>
     *
     * @param oldSnapshotDefinitionId
     * @param newSnapshotDefinitionId
     * @param lookUps                 <br>
     * @author Canberk.Erkmen
     */
    private void findAndUpdateLookUp(String oldSnapshotDefinitionId, String newSnapshotDefinitionId, List<TestStep> lookUps) {
        for (TestStep lookUp : lookUps) {
            lookUp.setParameter(lookUp.getParameter().replaceAll(oldSnapshotDefinitionId, newSnapshotDefinitionId));
        }
    }
}
