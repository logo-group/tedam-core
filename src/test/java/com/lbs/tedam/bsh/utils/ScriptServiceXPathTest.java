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

package com.lbs.tedam.bsh.utils;

import com.lbs.tedam.model.DTO.TabbedPaneAndPageParent;
import com.lbs.tedam.test.BaseServiceTest;
import com.lbs.tedam.util.Constants;
import com.lbs.tedam.util.TedamXPathUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;
import org.w3c.dom.Element;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author Tarik.Mikyas<br>
 */
public class ScriptServiceXPathTest extends BaseServiceTest {

	/**
	 * ScriptService scriptService
	 */
	@Parameter
	private static ScriptService scriptService;

	/**
	 * @author Tarik.Mikyas <br>
	 *         this method create scriptService before class created
	 */
	@BeforeClass
	public static void testGetScriptService() {
		scriptService = new ScriptService();
	}

	/**
	 * @author Tarik.Mikyas <br>
	 *         this method testGetElementFromXmlFile
	 */
	@Test
	public void testGetElementFromXmlFile() {
		Element element = getElementFromXmlFilePath("/TestGetElementFromXmlFile.xml");
		assertEquals(false, element.equals(null));
	}

	/**
	 * @author Tarik.Mikyas <br>
	 *         this method testGetPopUpMenuTag
	 */
	@Test
	public void testGetPopUpMenuTag() {
		Element element = getElementFromXmlFilePath("/TestGetPopUpMenuTag.xml");
		int menuTag = scriptService.getPopUpMenuTag(element);
		assertEquals(1000006, menuTag);
	}

	/**
	 * @author Tarik.Mikyas <br>
	 *         this method testGetCloseButton
	 */
	@Test
	public void testGetCloseButton() {
		Element element = getElementFromXmlFilePath("/TestGetCloseButtton.xml");
		int closeButtonTag = scriptService.getCloseButton(element);
		assertEquals(162, closeButtonTag);
	}

	/**
	 * @author Tarik.Mikyas <br>
	 *         this method testGetFormNameFromSnapshot
	 */
	@Test
	public void testGetFormNameFromSnapshot() {
		Element element = getElementFromXmlFilePath("/TestGetFormNameFromSnapshot.xml");
		String formName = TedamXPathUtils.getFormNameFromSnapshot(element);
		assertTrue(formName.equalsIgnoreCase("LOXFContractsBrowser%2"));
	}

	/**
	 * @author Tarik.Mikyas <br>
	 *         this method testGetControlTabProperties
	 */
	@Test
	public void testGetControlTabProperties() {
		Element snapshot = getElementFromXmlFilePath("/TestGetControlTabProperties.xml");
		List<TabbedPaneAndPageParent> controlTabProperties = scriptService.getControlTabProperties(snapshot, 151);
		assertNotEquals(0, controlTabProperties.size());
	}

	/**
	 * @author Tarik.Mikyas <br>
	 *         this method testGetGridTabProperties
	 */
	@Test
	public void testGetGridTabProperties() {
		Element snapshot = getElementFromXmlFilePath("/TestGetGridTabProperties.xml");
		List<TabbedPaneAndPageParent> gridTabProperties = scriptService.getGridTabProperties(snapshot, 6601);
		assertNotEquals(0, gridTabProperties.size());
	}

	/**
	 * @author Tarik.Mikyas <br>
	 *         this method testGetFilterTabProperties
	 */
	@Test
	public void testGetFilterTabProperties() {
		Element snapshot = getElementFromXmlFilePath("/TestGetFilterTabProperties.xml");
		List<TabbedPaneAndPageParent> filterTabProperties = scriptService.getFilterTabProperties(snapshot, 300);
		assertNotEquals(0, filterTabProperties.size());
	}

	/**
	 * @author Tarik.Mikyas <br>
	 *         this method test1GetDataGridTag
	 */
	@Test
	public void test1GetDataGridTag() {
		Element snapshot = getElementFromXmlFilePath("/TestGetDataGridTag.xml");
		Integer tag = scriptService.getDataGridTag(snapshot);
		assertNotNull(tag);
	}

	/**
	 * @author Tarik.Mikyas <br>
	 *         this method test1IsControlEnabled
	 */
	@Test
	public void test1IsControlEnabled() {
		Element snapshot = getElementFromXmlFilePath("/TestIsControlEnabled.xml");
		boolean result = scriptService.isControlEnabled(snapshot, 5201);
		assertEquals(false, result);
	}

	/**
	 * @author Tarik.Mikyas <br>
	 *         this method test2IsControlEnabled
	 */
	@Test
	public void test2IsControlEnabled() {
		Element snapshot = getElementFromXmlFilePath("/TestIsControlEnabled.xml");
		boolean result = scriptService.isControlEnabled(snapshot, 6200);
		assertTrue(result);
	}

	/**
	 * @author Tarik.Mikyas <br>
	 *         this method testGetErrorMessages
	 */
	@Test
	public void testGetErrorMessages() {
		Element snapshot = getElementFromXmlFilePath("/TestErrorMessages.xml");
		List<String> messageList = scriptService.getErrorMessages(snapshot);
		assertNotEquals(0, messageList.size());
	}

	/**
	 * @author Tarik.Mikyas <br>
	 *         this method test1DoMessageExist
	 */
	@Test
	public void test1DoMessageExist() {
		Element snapshot = getElementFromXmlFilePath("/TestErrorMessages.xml");
		int result = scriptService.doMessageExist(snapshot, "Fiş için organizasyonel birim bilgisi zorunludur.", true);
		assertEquals(1, result);
	}

	/**
	 * @author Tarik.Mikyas <br>
	 *         this method test2DoMessageExist
	 */
	@Test
	public void test2DoMessageExist() {
		Element snapshot = getElementFromXmlFilePath("/TestErrorMessages.xml");
		int result = scriptService.doMessageExist(snapshot, "dogru mesaj degil.", true);
		assertEquals(2, result);
	}

	/**
	 * @author Tarik.Mikyas <br>
	 *         this method test3DoMessageExist
	 */
	@Test
	public void test3DoMessageExist() {
		Element snapshot = getElementFromXmlFilePath("/TestErrorMessages.xml");
		int result = scriptService.doMessageExist(snapshot, "dogru mesaj degil.", false);
		assertEquals(0, result);
	}

	/**
	 * @author Tarik.Mikyas <br>
	 *         this method test1ButtonExists
	 */
	@Test
	public void test1ButtonExists() {
		Element snapshot = getElementFromXmlFilePath("/TestButtonExists.xml");
		boolean result = scriptService.buttonExists(snapshot, 305);
		assertEquals(false, result);
	}

	/**
	 * @author Tarik.Mikyas <br>
	 *         this method test2ButtonExists
	 */
	@Test
	public void test2ButtonExists() {
		Element snapshot = getElementFromXmlFilePath("/TestButtonExists.xml");
		boolean result = scriptService.buttonExists(snapshot, 301);
		assertTrue(result);
	}

	/**
	 * @author Tarik.Mikyas <br>
	 *         this method testSnapshotFieldMap
	 */
	@Test
	public void testSnapshotFieldMap() {
		Element snapshot = getElementFromXmlFilePath("/verify.xml");
		Map<String, String> verifyMap = TedamXPathUtils.getSnapshotFieldMap(snapshot);
		assertNotNull(verifyMap);
	}

	/**
	 * @author Tarik.Mikyas <br>
	 *         this method testGetCurrentGridSplitter
	 */
	@Test
	public void testGetCurrentGridSplitter() {
		Element snapshot = getElementFromXmlFilePath("/SatinalmaSiparisi_01.xml");
		int rowIndex = scriptService.getCurrentGridSplitter(snapshot, "400");
		assertEquals(0, rowIndex);
	}

	/**
	 * @author Tarik.Mikyas <br>
	 *         this method testGetButtonTag
	 */
	@Test
	public void testGetButtonTag() {
		Element snapshot = getElementFromXmlFilePath("/TestGetButtonTag.xml");
		int tag = scriptService.getButtonTag(snapshot, Constants.CTRL_ATTRIBUTE_SAVECLOSE);
		assertEquals(1100, tag);
	}

	/**
	 * @author Tarik.Mikyas <br>
	 *         this method testPathTooltip
	 */
	@Test
	public void testPathTooltip() {
		Element snapshot = getElementFromXmlFilePath("/TestPathTooltip.xml");
		String result = scriptService.pathTooltip(snapshot, "7003");
		assertTrue(result.equalsIgnoreCase("Analiz Detayları\\"));
	}
}
