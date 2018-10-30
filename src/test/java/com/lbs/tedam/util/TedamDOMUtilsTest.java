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

package com.lbs.tedam.util;

import com.lbs.tedam.exception.DocumentBuildException;
import com.lbs.tedam.test.BaseServiceTest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.xpath.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Tarik.Mikyas<br>
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TedamDOMUtilsTest extends BaseServiceTest {

    /**
     * this method testGetVersionFromSnapshot <br>
     *
     * @author Tarik.Mikyas <br>
     */
    @Test
    public void testDomParserStarter() {
        List<String> tags = new ArrayList<String>();
        String filePath = getFilePathFromSourceName("/Carihesap.xml");
        try {
            Document doc = TedamDOMUtils.domParserStarter(filePath);
            XPath xpath = XPathFactory.newInstance().newXPath();
            XPathExpression tagExpr = xpath.compile(Constants.XPATH_DATAGRID);
            NodeList nodes = (NodeList) tagExpr.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodes.getLength(); i++) {
                if (nodes.item(i).getAttributes() != null && nodes.item(i).getAttributes().getNamedItem("tag") != null) {
                    tags.add(nodes.item(i).getAttributes().getNamedItem("tag").getNodeValue());
                }
            }
        } catch (XPathExpressionException e) {
            System.out.println("excep" + e);
        }
        assertTrue(tags.get(0).equalsIgnoreCase("600"));
    }

    @Test
    public void getExpectedFormName() throws DocumentBuildException {
        assertNotNull(TedamDOMUtils.getExpectedFormName(getFilePathFromSourceName("/Carihesap.xml")));
    }
}
