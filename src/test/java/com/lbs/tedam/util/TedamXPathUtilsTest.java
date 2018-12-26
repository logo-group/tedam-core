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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.lbs.tedam.model.DTO.ButtonCtrl;
import com.lbs.tedam.model.DTO.GridRow;
import com.lbs.tedam.model.DTO.TabbedPaneAndPageParent;
import com.lbs.tedam.test.BaseServiceTest;

public class TedamXPathUtilsTest extends BaseServiceTest {

	/**
	 * @param sourcePath
	 * @return <br>
	 *         this method getElementFromXmlFile
	 * @author Tarik.Mikyas
	 */
	private Element getElementFromXmlFile(String sourcePath) {
		String filePath = getClass().getResource(sourcePath).getFile();
		return TedamFileUtils.getElementFromXmlFile(filePath);
	}

	/**
	 * this method testGetVersionFromSnapshot <br>
	 *
	 * @author Tarik.Mikyas <br>
	 * @edited Berk.Kemaloglu
	 */
	@Test
	public void testGetVersionFromSnapshot() {
		String version = TedamXPathUtils.getVersionFromSnapshot(getElementFromXmlFile("/GetVersionTest.xml"));
		assertTrue(version.equalsIgnoreCase("2.48.6.0"));
	}

	/**
	 * @author Berk.Kemaloglu
	 * @author Seyma.Sahin if the version value in the xml doesn't have "v"
	 */
	@Test
	public void testGetVersionFromSnapshot2() {
		String version = TedamXPathUtils.getVersionFromSnapshot(getElementFromXmlFile("/GetVersionTest2.xml"));
		assertTrue(version.equalsIgnoreCase("2.48.6.0"));
	}

	/**
	 * @author Berk.Kemaloglu
	 * @author Seyma.Sahin if we send empty snapshot we will get an exception(not working now)
	 */
	@Test
	public void testGetVersionFromSnapshot3() {
		Element nullSnapShot = null;
		String version = TedamXPathUtils.getVersionFromSnapshot(nullSnapShot);
		assertEquals(null, version);
	}

	/**
	 * this method testGetVersionFromSnapshot <br>
	 *
	 * @author Tarik.Mikyas <br>
	 */
	@Test
	public void testGetButtonList() {
		List<ButtonCtrl> buttonCtrlList = TedamXPathUtils.getButtonList(getElementFromXmlFile("/CRMMainPage.xml"));
		assertFalse(buttonCtrlList.isEmpty());
	}

	/**
	 * @author Berk.Kemaloglu
	 * @author Seyma.Sahin
	 */
	@Test
	public void testGetButtonList2() {
		List<ButtonCtrl> buttonCtrlList = TedamXPathUtils.getButtonList(getElementFromXmlFile("/CRMMainPage2.xml"));
		assertFalse(buttonCtrlList.isEmpty());
	}

	@Test
	public void testGetGridMatrix() {
		Node gridRootNode = TedamXPathUtils.getGridNode("100", getFilePathFromSourceName("/Malzemeler.xml"));
		List<GridRow> list = TedamXPathUtils.getGridMatrix(gridRootNode);
		assertNotEquals(list.size(), 0);
	}

	/**
	 * @author Berk.Kemaloglu
	 * @author Seyma.Sahin
	 */
	@Test
	public void testGetGridMatrix2() {
		Node gridRootNode = TedamXPathUtils.getGridNode("100", getFilePathFromSourceName("/Malzemeler3.xml"));
		List<GridRow> list = TedamXPathUtils.getGridMatrix(gridRootNode);
		assertEquals(0, list.size());
	}

	@Test
	public void testGetPopupItemList() {
		List<String> list = TedamXPathUtils.getPopupItemList(getFilePathFromSourceName("/Malzemeler.xml"));
		assertNotEquals(list.size(), 0);
	}

	/**
	 * @author Berk.Kemaloglu
	 * @author Seyma.Sahin
	 */
	@Test
	public void testGetPopupItemList2() {
		List<String> list = TedamXPathUtils.getPopupItemList(getFilePathFromSourceName("/Malzemeler4.xml"));
		assertNotEquals(list.size(), 0);
	}

	/**
	 * @author Berk.Kemaloglu
	 * @author Seyma.Sahin
	 */
	@Test(expected = NullPointerException.class)
	public void testGetPopupItemList3() {
		String nullFileAbsolutePath = null;
		List<String> list = TedamXPathUtils.getPopupItemList(nullFileAbsolutePath);

	}

	@Test
	public void testGetGridTags() {
		List<String> list = TedamXPathUtils.getGridTags(getFilePathFromSourceName("/Malzemeler.xml"));
		assertNotEquals(list.size(), 0);
	}

	/**
	 * @author Berk.Kemaloglu
	 * @author Seyma.Sahin
	 */
	@Test
	public void testGetGridTags2() {
		List<String> list = TedamXPathUtils.getGridTags(getFilePathFromSourceName("/Malzemeler4.xml"));
		assertNotEquals(list.size(), 0);
	}

	@Test
	public void testGetPropertValueFromDocument() {
		Document doc = TedamDOMUtils.domParserStarter(getFilePathFromSourceName("/Malzemeler.xml"));
		String param = TedamXPathUtils.getPropertValueFromDocument(doc, "100");
		assertNotNull(param);
	}

	@Test
	public void testGetPropertValueFromDocumentNullParameter() {
		Document doc = TedamDOMUtils.domParserStarter(getFilePathFromSourceName("/Malzemeler.xml"));
		String param = TedamXPathUtils.getPropertValueFromDocument(doc, "200");
		assertNull(param);
	}

	@Test
	public void testGetCloseButtonNull() {
		Element element = getElementFromXmlFilePath("/Malzemeler2.xml");
		int closeButtonTag = TedamXPathUtils.getCloseButton(element);
		assertEquals(0, closeButtonTag);
	}

	/**
	 * @author Berk.Kemaloglu
	 * @author Seyma.Sahin
	 */
	@Test
	public void testGetCloseButton2() {
		Element element = getElementFromXmlFilePath("/Malzemeler5.xml");
		int closeButtonTag = TedamXPathUtils.getCloseButton(element);
		assertNotEquals(0, closeButtonTag);
	}

	/**
	 * @author Berk.Kemaloglu
	 * @author Seyma.Sahin
	 */
	@Test
	public void testGetCloseButton3() {
		Element element = null;
		int closeButtonTag = TedamXPathUtils.getCloseButton(element);
		assertEquals(0, closeButtonTag);
	}

	@Test
	public void testGetControlTabPropertiesNull() {
		Element snapshot = getElementFromXmlFilePath("/TestGetControlTabProperties.xml");
		List<TabbedPaneAndPageParent> controlTabProperties = TedamXPathUtils.getControlTabProperties(snapshot, 150);
		assertTrue(controlTabProperties.isEmpty());
	}

	@Test
	public void testGetGridTabParentsNull() {
		Element snapshot = getElementFromXmlFilePath("/TestGetGridTabProperties.xml");
		List<TabbedPaneAndPageParent> controlTabProperties = TedamXPathUtils.getGridTabParents(snapshot, 150);
		assertTrue(controlTabProperties.isEmpty());
	}

}
