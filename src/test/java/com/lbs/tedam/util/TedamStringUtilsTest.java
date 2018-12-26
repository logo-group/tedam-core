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

import com.lbs.tedam.exception.JobCommandBuildException;
import com.lbs.tedam.model.DTO.LogoTestResult;
import com.lbs.tedam.model.DTO.Resource;
import com.lbs.tedam.model.TestCase;
import com.lbs.tedam.model.TestStep;
import com.lbs.tedam.test.BaseServiceTest;
import com.lbs.tedam.util.Enums.StatusMessages;
import com.lbs.tedam.util.EnumsV2.ExecutionStatus;
import com.lbs.tedam.util.EnumsV2.TestStepType;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.xpath.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Tarik.Mikyas<br>
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TedamStringUtilsTest extends BaseServiceTest {

	private List<LogoTestResult> results;

	@Before
	public void initialize() {
		LogoTestResult result = new LogoTestResult();
		result.setDescription("description");
		result.setId(1);
		result.setName("name");
		result.setResult(ExecutionStatus.FAILED);
		LogoTestResult result1 = new LogoTestResult();
		result1.setDescription("description1");
		result1.setId(2);
		result1.setName("name1");
		result1.setResult(ExecutionStatus.CAUTION);
		LogoTestResult result2 = new LogoTestResult();
		result2.setDescription("description2");
		result2.setId(3);
		result2.setName("name2");
		result2.setResult(ExecutionStatus.SUCCEEDED);
		results = Arrays.asList(result, result1, result2);
	}

	/**
	 * this method testCreateTestPackageFileException <br>
	 *
	 * @throws FileNotFoundException
	 *             <br>
	 * @author Tarik.Mikyas
	 */
	@Test(expected = FileNotFoundException.class)
	public void testCreateTestPackageFileException() throws FileNotFoundException {
		String[] args = ("configFilePath!equals!C:/temp resultReportAddress!equals!TEDAM_TX150_TC1242 version!equals!2.53.6.0 testCaseId!equals!1242 FormOpen=[200004,200041,200200] Report=PerformansAnalizRaporu!ps!1!ps!1!ps!0!ps!0!fn!")
				.split(" ");

		String testSetId = TedamStringUtils.findTestSetTestCaseParameters(args).get("testSetId");
		String testCaseId = TedamStringUtils.findTestSetTestCaseParameters(args).get("testCaseId");
		String testPackageName = "TestSet-" + testSetId + "_TestCase-" + testCaseId;
		String pTestCases = testCaseId;
		TedamStringUtils.createBSHPackageContentForTedam(PropUtils.getProperty(Constants.TEMP_FILE_PATH), testPackageName, pTestCases, Constants.EMPTY_STRING);
	}

	/**
	 * this method testCreateTestPackageFile <br>
	 *
	 * @throws FileNotFoundException
	 *             <br>
	 * @author Tarik.Mikyas
	 */
	@Test
	public void testCreateTestPackageFile() throws FileNotFoundException {
		String[] args = ("configFilePath!equals!C:/temp resultReportAddress!equals!TEDAM_TX150_TC1242 version!equals!2.53.6.0 testCaseId!equals!1242 FormOpen=[200004,200041,200200] Report=PerformansAnalizRaporu!ps!1!ps!1!ps!0!ps!0!fn!")
				.split(" ");

		String baseBshPath = getFilePathFromSourceName("/Base.bsh");
		String testSetId = TedamStringUtils.findTestSetTestCaseParameters(args).get("testSetId");
		String testCaseId = TedamStringUtils.findTestSetTestCaseParameters(args).get("testCaseId");
		String testPackageName = "TestSet-" + testSetId + "_TestCase-" + testCaseId;
		String pTestCases = testCaseId;
		TedamStringUtils.createBSHPackageContentForTedam(PropUtils.getProperty(Constants.TEMP_FILE_PATH), testPackageName, pTestCases, baseBshPath);
	}

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

	/**
	 * this method testGetIntegerListAsString <br>
	 *
	 * @author Canberk.Erkmen <br>
	 */
	@Test
	public void testGetListAsString() {
		List<String> list = new ArrayList<>();
		list.add("5");
		list.add("6");
		assertNotNull(TedamStringUtils.getListAsStringWithSeparator(list, Constants.TEXT_COMMA));
	}

	@Test
	public void testSplitStringByParameter() {
		String line = "Deneme1 deneme2 deneme3";
		List<String> arrayList = TedamStringUtils.splitStringContentByParameter(line, "");
		assertNotEquals(0, arrayList.size());
	}

	@Test
	public void testCollectResourceItemList() {
		List<Integer> list = TedamStringUtils.collectResourceItemList("Barkod|Musteri/Tedarikci Kodu~2|Uretici Kodu~3");
		assertNotEquals(0, list.size());
	}

	@Test
	public void testCollectResourceItemList2() {
		List<Integer> list = TedamStringUtils.collectResourceItemList("Barkod|Musteri/Tedarikci Kodu|Uretici Kodu");
		assertNotEquals(0, list.size());
	}

	@Test
	public void testCollectResourceItemList3() {
		List<Integer> list = TedamStringUtils.collectResourceItemList("");
		assertEquals(0, list.size());
	}

	@Test
	public void testCollectResourceItemNameList() {
		List<String> list = TedamStringUtils.collectResourceItemNameList("Barkod|Musteri/Tedarikci Kodu~2|Uretici Kodu~3");
		assertNotEquals(0, list.size());
	}

	@Test
	public void testCollectResourceItemNameList2() {
		List<String> list = TedamStringUtils.collectResourceItemNameList("");
		assertEquals(0, list.size());
	}

	@Test
	public void testCollectTotalResourceList() {
		List<Resource> list = TedamStringUtils.collectTotalResourceList("Barkod|Musteri/Tedarikci Kodu~2|Uretici Kodu~3");
		assertNotEquals(0, list.size());
	}

	@Test
	public void testCollectTotalResourceList3() {
		List<Resource> list = TedamStringUtils.collectTotalResourceList("~3");
		assertNotEquals(0, list.size());
	}

	@Test
	public void testFindTestParamaters() throws JobCommandBuildException {
		TestStep testStep = new TestStep("description", "expectedResult", TestStepType.FORM_FILL, "parameter", "filename", "expectedFormname", null);
		testStep.setId(1);
		testStep.setPosition(1);
		testStep.setLookUp(false);
		List<TestStep> testSteps = Arrays.asList(testStep);
		assertNotNull(TedamStringUtils.findTestParamaters(testSteps));
	}

	@Test
	public void testFindTestParamaters2() throws JobCommandBuildException {
		TestStep testStep = new TestStep("description", "expectedResult", TestStepType.FORM_FILL, "parameter", "filename", null, null);
		testStep.setId(1);
		testStep.setPosition(1);
		testStep.setLookUp(false);
		List<TestStep> testSteps = Arrays.asList(testStep);
		assertNotNull(TedamStringUtils.findTestParamaters(testSteps));
	}

	@Test
	public void testFindTestParamatersEmptyExpectedFormName() throws JobCommandBuildException {
		TestStep testStep = new TestStep("description", "expectedResult", TestStepType.FORM_FILL, "parameter", "filename", "", null);
		testStep.setId(1);
		testStep.setPosition(1);
		testStep.setLookUp(false);
		List<TestStep> testSteps = Arrays.asList(testStep);
		assertNotNull(TedamStringUtils.findTestParamaters(testSteps));
	}

	@Test(expected = JobCommandBuildException.class)
	public void testFindTestParamatersEmptyTestStepList() throws JobCommandBuildException {
		TedamStringUtils.findTestParamaters(new ArrayList<>());
	}

	@Test(expected = JobCommandBuildException.class)
	public void testFindTestParamatersTestStepTypeNull() throws JobCommandBuildException {
		TestStep testStep = new TestStep("description", "expectedResult", null, "parameter", "filename", "", null);
		TestCase testCase = new TestCase();
		testCase.setId(1);
		testStep.setId(1);
		testStep.setPosition(1);
		testStep.setLookUp(false);
		testStep.setTestCaseId(testCase.getId());
		List<TestStep> testSteps = Arrays.asList(testStep);
		assertNotNull(TedamStringUtils.findTestParamaters(testSteps));
	}

	@Test
	public void testGetTestResultReportPath() {
		assertNotNull(TedamStringUtils.getTestResultReportPath(1, 1, true));
	}

	@Test
	public void testGetTestResultReportPath1() {
		TedamStringUtils.getTestResultReportPath(1, 2, false);
	}

	@Test
	public void testMessageDialogParameterParserControl() {
		assertNull(TedamStringUtils.messageDialogParameterParser(null));
	}

	@Test
	public void testCalculateTotalTestCaseResultCaution() {
		LogoTestResult result = new LogoTestResult();
		result.setDescription("description");
		result.setId(2);
		result.setName("name");
		result.setResult(ExecutionStatus.CAUTION);
		assertTrue(TedamStringUtils.calculateTotalTestCaseResult(Arrays.asList(result)).equals(ExecutionStatus.CAUTION));
	}

	@Test
	public void testCalculateTotalTestCaseResultFailed() {
		LogoTestResult result = new LogoTestResult();
		result.setDescription("description");
		result.setId(2);
		result.setName("name");
		result.setResult(ExecutionStatus.FAILED);
		assertTrue(TedamStringUtils.calculateTotalTestCaseResult(Arrays.asList(result)).equals(ExecutionStatus.FAILED));
	}

	@Test
	public void testCalculateTotalTestCaseResultSucceded() {
		LogoTestResult result = new LogoTestResult();
		result.setDescription("description");
		result.setId(2);
		result.setName("name");
		result.setResult(ExecutionStatus.SUCCEEDED);
		assertTrue(TedamStringUtils.calculateTotalTestCaseResult(Arrays.asList(result)).equals(ExecutionStatus.SUCCEEDED));
	}

	@Test
	public void testCalculateTotalTestCaseResultBlocked() {
		LogoTestResult result = new LogoTestResult();
		result.setDescription("description");
		result.setId(2);
		result.setName("name");
		result.setResult(ExecutionStatus.BLOCKED);
		assertTrue(TedamStringUtils.calculateTotalTestCaseResult(Arrays.asList(result)).equals(ExecutionStatus.BLOCKED));
	}

	@Test
	public void testDecomposeFormName() {
		assertNotNull(TedamStringUtils.decomposeFormName(null));
	}

	@Test
	public void testGetStatus() {
		assertEquals(StatusMessages.CAUTION, TedamStringUtils.getStatus(StatusMessages.CAUTION, true));
	}

	@Test
	public void testGetStatus1() {
		assertEquals(StatusMessages.FAILED, TedamStringUtils.getStatus(StatusMessages.FAILED, true));
	}

	@Test
	public void testGetStatus2() {
		assertEquals(StatusMessages.FAILED, TedamStringUtils.getStatus(StatusMessages.FAILED, false));
	}

	@Test
	public void testBuildTestCaseResultDescription() {
		assertNotNull(TedamStringUtils.buildTestCaseResultDescription(ExecutionStatus.SUCCEEDED, new ArrayList<>()));
	}

	@Test
	public void testBuildTestCaseResultDescription1() {
		assertNotNull(TedamStringUtils.buildTestCaseResultDescription(ExecutionStatus.NOTRUN, new ArrayList<>()));
	}

	@Test
	public void testBuildTestCaseResultDescription2() {
		assertNotNull(TedamStringUtils.buildTestCaseResultDescription(ExecutionStatus.BLOCKED, new ArrayList<>()));
	}

	@Test
	public void testBuildTestCaseResultDescription3() {
		String description = TedamStringUtils.buildTestCaseResultDescription(ExecutionStatus.CAUTION, results);
		assertNotNull(description);
	}

	@Test
	public void testMessageDialogParameterParserValueNull() {
		assertNull(TedamStringUtils.messageDialogParameterParser(Constants.VALUE_NULL));
	}

	@Test
	public void testMessageDialogParameterParserEmpty() {
		assertTrue(TedamStringUtils.messageDialogParameterParser("").isEmpty());
	}
}
