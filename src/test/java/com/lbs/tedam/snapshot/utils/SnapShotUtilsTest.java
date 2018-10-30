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

package com.lbs.tedam.snapshot.utils;

import com.lbs.tedam.model.DTO.ButtonCtrl;
import com.lbs.tedam.test.BaseServiceTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

import javax.xml.xpath.XPathExpressionException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * @author Tarik.Mikyas<br>
 */
public class SnapShotUtilsTest extends BaseServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SnapShotUtilsTest.class);

    /**
     * this method testGetDBNewButton <br>
     *
     * @author Tarik.Mikyas <br>
     */
    @Test
    public void testGetDBNewButton() {
        Element element = getElementFromXmlFilePath("/Butceler.xml");
        ButtonCtrl buttonCtrl = null;
        try {
            buttonCtrl = new SnapShotUtils().getDBNewButton(element);
        } catch (XPathExpressionException e) {
            LOGGER.error("" + e);
        }
        assertNotNull(buttonCtrl);
    }

    /**
     * @author Berk.Kemaloglu
     * @author Seyma.Sahin
     */
    @Test
    public void testGetters() {
        SnapShotUtils snapShotUtils = new SnapShotUtils();
        snapShotUtils.getMenuBatchList();
        snapShotUtils.getMenuBrowserList();
        snapShotUtils.getMenuList();
        snapShotUtils.getMenuReportList();
        snapShotUtils.getPopUpMenuItems();
    }

    /**
     * @author Berk.Kemaloglu
     * @author Seyma.Sahin
     * Tests both 2 setPopUpMenuItems methods
     */
    @Test
    public void testSetPopUpMenuItems() {
        SnapShotUtils snapShotUtils = new SnapShotUtils();
        Map<String, String> myMap = new HashMap<String, String>();
        myMap.put("key", "value");
        snapShotUtils.setPopUpMenuItems(myMap);
        List<Object> myList = new ArrayList<Object>();
        myList.add("?name,tag)");
        snapShotUtils.setPopUpMenuItems(myList);
        Map<String, String> myMap2 = snapShotUtils.getPopUpMenuItems();
        assertEquals("tag", myMap2.get("name"));
        SnapShotUtils snapShotUtils2 = new SnapShotUtils();
        snapShotUtils2.setPopUpMenuItems(myList);
    }


}
