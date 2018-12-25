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

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;

import com.lbs.tedam.test.BaseServiceTest;

/**
 * @author Tarik.Mikyas<br>
 */
public class ScriptServiceOtherTest extends BaseServiceTest {

	@Parameter
	private static List<Object> popUpMenuItemIndexList;

	/**
	 * @author Tarik.Mikyas <br>
	 *         this method testFillPopUpMenuItemIndexList
	 */
	@BeforeClass
	public static void testFillPopUpMenuItemIndexList() {
		// [(Add,1), (Change,2), (Clear,3), (Look,4), (Copy,7), (-,-1), (Change
		// Status,13), (Bulk Status Change,15), (,0), (-,-2), (,0), (,0), (,0), (,0),
		// (,0),
		// (-,-3), (,0), (,0), (,0), (-,-4)]
		popUpMenuItemIndexList = new ArrayList<>();
		popUpMenuItemIndexList.add("(Ekle,1)");
		popUpMenuItemIndexList.add("(Değiştir,2)");
		popUpMenuItemIndexList.add("(Sil,3)");
		popUpMenuItemIndexList.add("(İncele,4)");
		popUpMenuItemIndexList.add("(Kopyala,7)");
		popUpMenuItemIndexList.add("(-,-1)");
		popUpMenuItemIndexList.add("(Durumunu Değiştir,13)");
		popUpMenuItemIndexList.add("(Toplu Durum Değişikliği,15)");
		popUpMenuItemIndexList.add("(,0)");
		popUpMenuItemIndexList.add("(-,-2)");
		popUpMenuItemIndexList.add("(,0)");
		popUpMenuItemIndexList.add("(,0)");
		popUpMenuItemIndexList.add("(,0)");
		popUpMenuItemIndexList.add("(,0)");
		popUpMenuItemIndexList.add("(,0)");
		popUpMenuItemIndexList.add("(-,-3)");
		popUpMenuItemIndexList.add("(,0)");
		popUpMenuItemIndexList.add("(,0)");
		popUpMenuItemIndexList.add("(,0)");
		popUpMenuItemIndexList.add("(-,-4)");
	}

	@Test
	public void testDummy() {
		String dummyContent = "dummy";
		assertNotNull(dummyContent);
	}

	/*
	 * @Test public void test1GetPopUpMenuItemIndex() { int index =
	 * scriptService.getPopUpMenuItemIndex(popUpMenuItemIndexList,
	 * "Durumunu Değiştir"); assertEquals(index, 13); }
	 * 
	 * @Test public void test2GetPopUpMenuItemIndex() { int index =
	 * scriptService.getPopUpMenuItemIndex(popUpMenuItemIndexList,
	 * "Olmayan bir item"); assertEquals(index, Constants.VALUE_NULL_INTEGER); }
	 * 
	 * @Test public void testGetSnapshotFilterFillValueBOList() {
	 * List<SnapshotValue> filterFillValueBOList =
	 * scriptService.getSnapshotFilterFillValueBOList("2.47.6.0", 1260);
	 * assertNotEquals(filterFillValueBOList.size(), 0); }
	 * 
	 * @Test public void testGetSnapshotFormFillValueBOList() { List<SnapshotValue>
	 * snapshotValues = scriptService.getSnapshotFormFillValueBOList("2.47.6.0",
	 * 11126); assertNotEquals(snapshotValues.size(), 0); }
	 * 
	 * @Test public void testIsComponentGridCell() { List<SnapshotValue>
	 * snapshotValues = scriptService.getSnapshotFormFillValueBOList("2.33.08.0",
	 * 40); boolean result =
	 * scriptService.isComponentGridCell(snapshotValues.get(0)); assertTrue(result);
	 * }
	 * 
	 * @Test public void testDecomposeGroupAndSelectionValues() { List<String>
	 * resultList = scriptService.decomposeGroupAndSelectionValues("1/2/3/4/5");
	 * assertNotEquals(resultList.size(), 0); }
	 * 
	 * @Test public void test1DecomposeNonRangeValues() { List<String> resultList =
	 * scriptService.decomposeNonRangeValues("<$gr>TestTedamGrup");
	 * assertTrue(resultList.get(0).equalsIgnoreCase("TestTedamGrup")); }
	 * 
	 * @Test public void test2Decompose1NonRangeValues() { List<String> resultList =
	 * scriptService.decomposeNonRangeValues(
	 * "<$gr>TestTedamGrup<$!>TestTedamExcluded");
	 * assertTrue(resultList.get(0).equalsIgnoreCase("TestTedamGrup") &&
	 * resultList.get(1).equalsIgnoreCase("TestTedamExcluded")); }
	 * 
	 * @Test public void test3Decompose1NonRangeValues() { List<String> resultList =
	 * scriptService.decomposeNonRangeValues("<$gt>3");
	 * assertEquals(resultList.size(), 2); }
	 * 
	 * // 4 tests must be written for this // 1-exclude group // 2-group without
	 * exclude // 3-exclude interval // 4-interval without exclude
	 * 
	 * @Test public void test1DecomposeRangeValues() { List<String> resultList =
	 * scriptService.decomposeRangeValues("<$gr>TestTedamGrup");
	 * assertTrue(resultList.get(0).equalsIgnoreCase("TestTedamGrup")); }
	 * 
	 * @Test public void test2DecomposeRangeValues() { List<String> resultList =
	 * scriptService.decomposeRangeValues("<$gr>TestTedamGrup<$!>TestTedamExcluded")
	 * ; assertTrue(resultList.get(0).equalsIgnoreCase("TestTedamGrup") &&
	 * resultList.get(3).equalsIgnoreCase("TestTedamExcluded")); }
	 * 
	 * @Test public void test3DecomposeRangeValues() { List<String> resultList =
	 * scriptService.decomposeRangeValues(
	 * "<$gt>TestTedamGrupAlt<$lt>TestTedamGrupUst");
	 * assertTrue(resultList.get(1).equalsIgnoreCase("TestTedamGrupAlt") &&
	 * resultList.get(2).equalsIgnoreCase("TestTedamGrupUst")); }
	 * 
	 * @Test public void test4DecomposeRangeValues() { List<String> resultList =
	 * scriptService .decomposeRangeValues(
	 * "<$gt>TestTedamGrupAlt<$lt>TestTedamGrupUst<$!>TestTedamExcluded");
	 * assertTrue(resultList.get(1).equalsIgnoreCase("TestTedamGrupAlt") &&
	 * resultList.get(2).equalsIgnoreCase("TestTedamGrupUst") &&
	 * resultList.get(3).equalsIgnoreCase("TestTedamExcluded")); }
	 * 
	 * @Test public void testGetFillerFieldsList() { List<SnapshotValue>
	 * fillerFieldsList = scriptService.getFillerFieldsList(37);
	 * assertNotEquals(fillerFieldsList.size(), 0); }
	 * 
	 * @Test public void test1GetGridSplitterIndex() { int index =
	 * scriptService.getGridSplitterIndex(3673, "400"); assertEquals(index, 0); }
	 * 
	 * @Test public void test2GetGridSplitterIndex() { // test for a non-property
	 * int index = scriptService.getGridSplitterIndex(0, "400");
	 * System.out.println(index); assertEquals(index, -1); }
	 * 
	 * @Test public void test1GetCorrectValueForCheckBox() { String result =
	 * scriptService.getCorrectValueForCheckBox("true");
	 * assertTrue(result.equalsIgnoreCase("1")); }
	 * 
	 * @Test public void test2GetCorrectValueForCheckBox() { String result =
	 * scriptService.getCorrectValueForCheckBox("false");
	 * assertTrue(result.equalsIgnoreCase("2")); }
	 * 
	 * @Test public void test3GetCorrectValueForCheckBox() { String result =
	 * scriptService.getCorrectValueForCheckBox("defaultDegerIcin");
	 * assertTrue(result.equalsIgnoreCase("2")); }
	 * 
	 * @Test public void test4GetGridSplitterIndex() {
	 * assertNotNull(scriptService.getGridSplitterIndex(41, "400")); }
	 * 
	 * @Test public void testIsMessageCorrect() { // TODO:mikyas this procedure is
	 * how to write the test. C/temp/ says it should // be a folder under ??? The
	 * procedure written by Ersin Bey will be erased. }
	 * 
	 * @Test public void testGetMultiSelectionList() { int[] list =
	 * scriptService.getMultiSelectionList("1/2/3/4"); assertEquals(list[2], 3); }
	 * 
	 * @Test public void testValidateSavedDataRowIndexsiz() { Element snapshot =
	 * getElementFromXmlFilePath("/HesapOzeti.xml"); List<SnapshotValue>
	 * fillerFieldsList = scriptService.getFillerFieldsList(11502); TestReport
	 * bshTestReport = scriptService.validateSavedData(snapshot, fillerFieldsList,
	 * "", false, true); assertNotNull(bshTestReport); }
	 * 
	 * @Test public void testValidateSavedDataRowIndexsizFailed() { Element snapshot
	 * = getElementFromXmlFilePath("/10PuantajHesapOzeti.xml"); List<SnapshotValue>
	 * fillerFieldsList = scriptService.getFillerFieldsList(5523); TestReport
	 * bshTestReport = scriptService.validateSavedData(snapshot, fillerFieldsList,
	 * "", false, true); assertNotNull(bshTestReport); }
	 * 
	 * @Test public void testValidateSavedDataRowIndexli() { Element snapshot =
	 * getElementFromXmlFilePath("/ValidateSaveDataWithRowIndex.xml");
	 * List<SnapshotValue> fillerFieldsList =
	 * scriptService.getFillerFieldsList(11523); TestReport bshTestReport =
	 * scriptService.validateSavedData(snapshot, fillerFieldsList, "", false,
	 * false); assertNotNull(bshTestReport); }
	 * 
	 * @Test public void testGetTedamScriptAccessorList() {
	 * List<TedamScriptAccessor> accessorList =
	 * scriptService.getTedamScriptAccessorList( ScriptAccessorType.SAHI.getValue(),
	 * ScriptAccessorOperationType.CLICK.getValue());
	 * assertNotNull(accessorList.size() > 0); }
	 * 
	 * @Test public void testGetFileContent() { String testCaseId = "6"; String
	 * fileName = "test"; assertTrue(scriptService.getFileContent(testCaseId,
	 * fileName) != null); }
	 * 
	 * @Test public void testSaveTestStepTimeRecordList() { List<TestStepTimeRecord>
	 * timeRecordList = new ArrayList<>(); TestStepTimeRecord testStepTimeRecord =
	 * new TestStepTimeRecord(); testStepTimeRecord.setTestStepId(99991);
	 * timeRecordList.add(testStepTimeRecord);
	 * scriptService.saveTestStepTimeRecordList(timeRecordList); }
	 * 
	 * @Test public void testGetGridSearchParameterList() { List<GridCell>
	 * gridSearchParameterList =
	 * scriptService.getGridSearchParameterList("[(1002,TC018361)]");
	 * assertNotNull(gridSearchParameterList.size() > 0); }
	 */
}
