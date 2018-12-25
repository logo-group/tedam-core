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

import com.lbs.tedam.data.dao.SnapshotValueDAO;
import com.lbs.tedam.data.service.SnapshotValueService;
import com.lbs.tedam.exception.DifferencesSnapshotException;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.SnapshotValue;
import com.lbs.tedam.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class SnapshotValueServiceImpl extends BaseServiceImpl<SnapshotValue, Integer> implements SnapshotValueService {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private SnapshotValueDAO dao;

    @Autowired
    public void setDao(SnapshotValueDAO dao) {
        this.dao = dao;
        super.setBaseDao(dao);
    }

    @Override
    public List<SnapshotValue> getSnapshotValueList(int snapshotDefinitionId, Integer excluded) throws LocalizedException {
        List<SnapshotValue> snapshotValList = null;
        if (excluded == null) {
            snapshotValList = dao.findBySnapshotDefinitionId(snapshotDefinitionId);
        } else {
            snapshotValList = dao.findBySnapshotDefinitionIdAndRowIndexNot(snapshotDefinitionId, excluded);
        }
        return snapshotValList;
    }

    @Override
    public SnapshotValue getLatestVersionOfSnapshotValue(String tag, int snapshotDefinitionId) throws LocalizedException {
        SnapshotValue snapshotVal = dao.getLatestVersionOfSnapshotValue(tag, snapshotDefinitionId);
        return snapshotVal;
    }

    @Override
    public List<SnapshotValue> getSnapshotValuesVersioned(String version, int snapshotDefinitionId, String orderingColumn, Boolean scope) throws LocalizedException {
        List<SnapshotValue> snapshotValList = dao.getSnapshotValuesVersioned(version, snapshotDefinitionId, scope);
        if (snapshotValList.isEmpty()) {
            return snapshotValList;
        }
        String[] orderingColumnArray = orderingColumn.split(Constants.TEXT_COMMA);
        for (int i = orderingColumnArray.length - 1; i >= 0; i--) {
            switch (orderingColumnArray[i].trim()) {
                case "ROW_INDEX":
                    Collections.sort(snapshotValList, new SnapshotValueRowIndexComparator());
                    break;
                case "RUN_ORDER":
                    Collections.sort(snapshotValList, new SnapshotValueRunOrderComparator());
                    break;
                default:
                    break;
            }
        }
        return snapshotValList;
    }

    @Override
    public List<SnapshotValue> getSnapshotValueListByVersion(String version) throws LocalizedException {
        List<SnapshotValue> list = dao.getSnapshotValueListByVersion(version);
        return list;
    }

    /**
     * Returns snapshotValue equivalents of the elements in snapshot xml with given path.
     *
     * @param tcVersion
     * @param fileAbsolutePath
     * @param snapshotDefinitionId
     * @return
     * @throws DifferencesSnapshotException
     * @throws LocalizedException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws XPathExpressionException
     * @throws TransformerException
     * @author Ozgur.Ozbil
     * @edited Tarik.Mikyas
     */
    // Used by TedamFace.
    @Override
    public List<SnapshotValue> getSnapshotValuesFromFile(String tcVersion, String fileAbsolutePath, int snapshotDefinitionId)
            throws DifferencesSnapshotException, LocalizedException {
        try {

            Document doc = TedamDOMUtils.domParserStarter(fileAbsolutePath);

            Element snapshot = doc.getDocumentElement();

            String version = TedamXPathUtils.getVersionFromSnapshot(snapshot);

            List<SnapshotValue> combinedSnapshotValues = new ArrayList<SnapshotValue>();

            // Searches through control values in xmlDoc
            XPath xpath;
            XPathExpression tagExpr;
            NodeList nodes;
            xpath = XPathFactory.newInstance().newXPath();
            tagExpr = xpath.compile("//Control[not(ancestor::Control[@visible='false' and @type!='3'])][@visible='true']");
            nodes = (NodeList) tagExpr.evaluate(snapshot, XPathConstants.NODESET);
            if (nodes != null && nodes.getLength() > 0) {
                combinedSnapshotValues = TedamXPathUtils.getControlValuesFromFile(nodes);
            }

            // Searches through DataGrid values in xmlDoc
            xpath = XPathFactory.newInstance().newXPath();
            tagExpr = xpath.compile("//DataGrid[not(ancestor::Control[@visible='false' and @type!='3'])][@visible='true']");
            nodes = (NodeList) tagExpr.evaluate(snapshot, XPathConstants.NODESET);
            if (nodes != null && nodes.getLength() > 0) {
                combinedSnapshotValues.addAll(TedamXPathUtils.getGridContentsFromFile(nodes));
            }

            // Searches through FilterGrid values in xmlDoc
            xpath = XPathFactory.newInstance().newXPath();
            tagExpr = xpath.compile("//FilterGrid[not(ancestor::Control[@visible='false' and @type!='3'])][@visible='true']");
            nodes = (NodeList) tagExpr.evaluate(snapshot, XPathConstants.NODESET);
            if (nodes != null && nodes.getLength() > 0) {
                combinedSnapshotValues.addAll(TedamXPathUtils.getFilterContentsFromFile(nodes));
            }

            setCombinedSnapshotValuesVersion(version, combinedSnapshotValues);
            // Lists snapshotValues with given snapshotDefinitionId with unique tags according to version currency
            List<SnapshotValue> snapshotValueList = getSnapshotValuesVersioned(tcVersion, snapshotDefinitionId, "RUN_ORDER", null);
            // If there are matching tags between xmlDoc elements and snapshotValues gathered from DB, override xmlDoc element with updated snapshotValue.
            checkForCombinedValues(combinedSnapshotValues, snapshotValueList);

            combinedSnapshotValuesWithList(combinedSnapshotValues, snapshotValueList);

            // Sort hybrid values by their order properties
            Comparator<SnapshotValue> comparaSnapshotValues = (o1, o2) -> {
                return o1.getOrder() - o2.getOrder();
            };
            Collections.sort(combinedSnapshotValues, comparaSnapshotValues);

            return combinedSnapshotValues;
        } catch (XPathExpressionException e) {
            throw new DifferencesSnapshotException("An error was encountered during Xpath operations. e: " + e);
        }
    }

	private void setCombinedSnapshotValuesVersion(String version, List<SnapshotValue> combinedSnapshotValues) {
		for (int i = 0; i < combinedSnapshotValues.size(); i++) {
		    combinedSnapshotValues.get(i).setVersion(version);
		}
	}

	private void combinedSnapshotValuesWithList(List<SnapshotValue> combinedSnapshotValues,
			List<SnapshotValue> snapshotValueList) {
		for (int i = 0; i < snapshotValueList.size(); i++) {

		    if (!combinedSnapshotValues.contains(snapshotValueList.get(i))) {
		        combinedSnapshotValues.add(snapshotValueList.get(i));
		    }
		}
	}

	private void checkForCombinedValues(List<SnapshotValue> combinedSnapshotValues,
			List<SnapshotValue> snapshotValueList) {
		for (int i = 0; i < combinedSnapshotValues.size(); i++) {
		    for (int j = 0; j < snapshotValueList.size(); j++) {
		        if (combinedSnapshotValues.get(i).getTag().equals(snapshotValueList.get(j).getTag())
		                && combinedSnapshotValues.get(i).getRowIndex() == snapshotValueList.get(j).getRowIndex()) {
		            snapshotValueList.get(j).setCaption(combinedSnapshotValues.get(i).getCaption());
		            combinedSnapshotValues.set(i, snapshotValueList.get(j));
		            break;
		        }
		    }
		}
	}

}
