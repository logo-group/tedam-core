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

import com.lbs.tedam.data.dao.FormDefinitionDAO;
import com.lbs.tedam.data.service.FormDefinitionService;
import com.lbs.tedam.data.service.FormFieldService;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.DTO.FormName;
import com.lbs.tedam.model.FormDefinition;
import com.lbs.tedam.model.Snapshot;
import com.lbs.tedam.util.Constants;
import com.lbs.tedam.util.TedamDOMUtils;
import com.lbs.tedam.util.TedamXPathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;

import javax.xml.xpath.XPathExpressionException;
import java.util.List;

@Service
public class FormDefinitionServiceImpl extends BaseServiceImpl<FormDefinition, Integer> implements FormDefinitionService {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private FormDefinitionDAO dao;

    @Autowired
    private FormFieldService formFieldService;

    @Autowired
    public void setDao(FormDefinitionDAO dao) {
        this.dao = dao;
        super.setBaseDao(dao);
    }

    @Override
    public FormDefinition getFormDefByNameAndMode(String name, String mode) throws LocalizedException {
        FormDefinition formDefinition = dao.getFormDefByNameAndMode(name, mode);
        return formDefinition;
    }

    @Override
    public List<Integer> getUpdatedFormDefinitionIdList(String version) throws LocalizedException {
        List<Integer> updatedFormDefinitionIdList = dao.getUpdatedFormDefinitionIdList(version);
        return updatedFormDefinitionIdList;
    }

    /**
     * It was brought here with TEDAMv2.
     *
     * @param xmlDoc
     * @param versionUpdate
     * @return
     * @throws XPathExpressionException
     * @throws LocalizedException
     */
    @Override
    public FormDefinition saveUpdateFormContent(Element xmlDoc, boolean versionUpdate) throws XPathExpressionException, LocalizedException {
        FormName formName = TedamXPathUtils.getFormNameAndMode(xmlDoc);
        String version = TedamXPathUtils.getVersionFromSnapshot(xmlDoc);

        String formDefName = formName.getName();
        String formDefValue = formName.getMode();

        FormDefinition formDefinition = getFormDefByNameAndMode(formDefName, formDefValue);
        if (formDefinition == null) {
            formDefinition = new FormDefinition();
            formDefinition.setName(formName.getName());
            formDefinition.setMode(formName.getMode());
            // A two-sided relationship is established between snapshot entity and formDefinition.
            Snapshot snapshot = new Snapshot(TedamDOMUtils.elementToString(xmlDoc), version, Constants.VALUE_NULL);
            formDefinition.addSnapshot(snapshot);

            // the fields of the corresponding window are added to the database.
            formFieldService.prepareFormFields(xmlDoc, formDefinition, false, version);
            formDefinition = save(formDefinition);
        } else if (versionUpdate) {
            formFieldService.prepareFormFields(xmlDoc, formDefinition, true, version);
        }
        return formDefinition;
    }

}
