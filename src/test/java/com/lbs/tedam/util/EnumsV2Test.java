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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lbs.tedam.data.config.DataConfig;
import com.lbs.tedam.data.service.TestDataConfig;
import com.lbs.tedam.test.BaseServiceTest;
import com.lbs.tedam.util.EnumsV2.ClientStatus;
import com.lbs.tedam.util.EnumsV2.CommandStatus;
import com.lbs.tedam.util.EnumsV2.ExecutionStatus;
import com.lbs.tedam.util.EnumsV2.FormOpenShortcutType;
import com.lbs.tedam.util.EnumsV2.JobParameterType;
import com.lbs.tedam.util.EnumsV2.JobStatus;
import com.lbs.tedam.util.EnumsV2.JobType;
import com.lbs.tedam.util.EnumsV2.LogMessage;
import com.lbs.tedam.util.EnumsV2.LogType;
import com.lbs.tedam.util.EnumsV2.ReportLifeCycle;
import com.lbs.tedam.util.EnumsV2.ReportStatus;
import com.lbs.tedam.util.EnumsV2.RunOrder;
import com.lbs.tedam.util.EnumsV2.ScriptAccessorOperationType;
import com.lbs.tedam.util.EnumsV2.ScriptAccessorType;
import com.lbs.tedam.util.EnumsV2.StaticJobParameter;
import com.lbs.tedam.util.EnumsV2.TedamBoolean;
import com.lbs.tedam.util.EnumsV2.TedamFolderType;
import com.lbs.tedam.util.EnumsV2.TedamSocketMessageType;
import com.lbs.tedam.util.EnumsV2.TedamUserFavoriteType;
import com.lbs.tedam.util.EnumsV2.TedamUserRole;
import com.lbs.tedam.util.EnumsV2.TestRunType;
import com.lbs.tedam.util.EnumsV2.TestStepType;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestDataConfig.class, DataConfig.class })
public class EnumsV2Test extends BaseServiceTest {

	@Test
	public void testEnumClientStatus() {
		ClientStatus clientStatus = ClientStatus.FREE;
		clientStatus = ClientStatus.fromValue("Busy");
		clientStatus.getValue();
		clientStatus.getId();
		clientStatus.toString();
		clientStatus = ClientStatus.fromValue("test");
	}

	@Test
	public void testEnumCommandStatus() {
		CommandStatus commandStatus = CommandStatus.COMPLETED;
		commandStatus = CommandStatus.fromValue("Blocked");
		commandStatus.getValue();
		commandStatus.getId();
		commandStatus.toString();
		commandStatus = CommandStatus.fromValue("test");
		CommandStatus.getCompletedCommandStatus();

	}

	@Test
	public void testEnumExecutionStatus() {
		ExecutionStatus executionStatus = ExecutionStatus.CAUTION;
		executionStatus.getValue();
		executionStatus.getId();
		executionStatus.toString();

	}

	@Test
	public void testEnumFormOpenShortcutType() {
		FormOpenShortcutType formOpenShortcutType = FormOpenShortcutType.SET_WORK_DATES;
		formOpenShortcutType = FormOpenShortcutType.fromValue("!er!");
		formOpenShortcutType.getValue();
		formOpenShortcutType.getId();
		formOpenShortcutType.toString();
		formOpenShortcutType = FormOpenShortcutType.fromValue("test");
	}

	@Test
	public void testEnumJobParameterType() {
		JobParameterType jobParameterType = JobParameterType.STATIC;
		jobParameterType = JobParameterType.fromValue("constant");
		jobParameterType.getValue();
		jobParameterType.getSign();
		jobParameterType.getId();
		jobParameterType.toString();
		jobParameterType = JobParameterType.fromValue("test");
	}

	@Test
	public void testEnumJobStatus() {
		JobStatus jobStatus = JobStatus.NOT_STARTED;
		jobStatus = JobStatus.fromValue("QUEUED");
		jobStatus.getValue();
		jobStatus.getId();
		jobStatus.toString();
		jobStatus = JobStatus.fromValue("test");
		JobStatus.getInActiveJobStatus();

	}

	@Test
	public void testEnumJobType() {
		JobType jobType = JobType.NORMAL;
		jobType.getValue();
		jobType.getId();
		jobType.toString();
	}

	@Test
	public void testEnumLogMesage() {
		LogMessage.values();

	}

	@Test
	public void testEnumLogType() {
		LogType.values();
	}

	@Test
	public void testEnumReportLifeCycle() {
		ReportLifeCycle reportLifeCycle = ReportLifeCycle.COMPARABLE;
		reportLifeCycle = ReportLifeCycle.fromValue("New");
		reportLifeCycle.getValue();
		reportLifeCycle.getId();
		reportLifeCycle.toString();
		reportLifeCycle = ReportLifeCycle.fromValue("test");
	}

	@Test
	public void testEnumReportStatus() {
		ReportStatus reportStatus = ReportStatus.MISSING_DATA;
		reportStatus = ReportStatus.fromValue("bigData");
		reportStatus.getValue();
		reportStatus.getId();
		reportStatus.toString();
		reportStatus = ReportStatus.fromValue("test");
	}

	@Test
	public void testEnumRunOrder() {
		RunOrder runOrder = RunOrder.RUN_SCRIPT;
		runOrder = RunOrder.fromValue("RunScript");
		runOrder.getValue();
		runOrder.getId();
		runOrder.toString();
		runOrder = RunOrder.fromValue("test");
	}

	@Test
	public void testEnumScriptAccessorOperationType() {
		ScriptAccessorOperationType scriptAccessorOperationType = ScriptAccessorOperationType.CLICK;
		scriptAccessorOperationType = ScriptAccessorOperationType.fromValue("read");
		scriptAccessorOperationType.getValue();
		scriptAccessorOperationType.getId();
		scriptAccessorOperationType.toString();
		scriptAccessorOperationType = ScriptAccessorOperationType.fromValue("test");
	}

	@Test
	public void testEnumScriptAccessorType() {
		ScriptAccessorType scriptAccessorType = ScriptAccessorType.SELENIUM;
		scriptAccessorType = ScriptAccessorType.fromValue("Sahi");
		scriptAccessorType.getValue();
		scriptAccessorType.getId();
		scriptAccessorType.toString();
		scriptAccessorType = ScriptAccessorType.fromValue("test");
	}

	@Test
	public void testEnumStaticJobParameter() {
		StaticJobParameter staticJobParameter = StaticJobParameter.TEST_CASE_ID;
		staticJobParameter = StaticJobParameter.fromValue("testSteps");
		staticJobParameter.getValue();
		staticJobParameter.getId();
		staticJobParameter.toString();
		staticJobParameter = StaticJobParameter.fromValue("test");
	}

	@Test
	public void testEnumTedamBoolean() {
		TedamBoolean tedamBoolean = TedamBoolean.TRUE;
		tedamBoolean.getCode();
		tedamBoolean.getBooleanValue();
		tedamBoolean.getValue();
		tedamBoolean.getId();
		tedamBoolean.toString();
	}

	/**
	 * @author Berk.Kemaloglu
	 * @author Seyma.Sahin
	 */
	@Test
	public void testEnumTedamFolderType() {
		TedamFolderType tedamFolderType1 = TedamFolderType.TESTCASE;
		tedamFolderType1.getId();
		tedamFolderType1.getValue();
		tedamFolderType1.toString();
		String value1 = "TestCase";
		String value2 = "wrongValue";
		TedamFolderType tedamFolderType2 = TedamFolderType.fromValue(value1);
		TedamFolderType tedamFolderType3 = TedamFolderType.fromValue(value2);
		TedamFolderType tedamFolderType4 = null;
		assertEquals(tedamFolderType1, tedamFolderType2);
		assertEquals(tedamFolderType4, tedamFolderType3);

	}

	@Test
	public void testEnumTedamSocketMessageType() {
		TedamSocketMessageType.values();
	}

	/**
	 * @author Berk.Kemaloglu
	 * @author Seyma.Sahin
	 */
	@Test
	public void testEnumTedamUserFavouriteType() {
		TedamUserFavoriteType tedamUserFavoriteType1 = TedamUserFavoriteType.CLIENT;
		tedamUserFavoriteType1.getValue();
		tedamUserFavoriteType1.getId();
		tedamUserFavoriteType1.toString();
		TedamUserFavoriteType tedamUserFavoriteType2 = TedamUserFavoriteType.fromValue("Client");
		TedamUserFavoriteType tedamUserFavoriteType3 = TedamUserFavoriteType.fromValue("wrongValue");
		TedamUserFavoriteType tedamUserFavoriteType4 = null;
		assertEquals(tedamUserFavoriteType1, tedamUserFavoriteType2);
		assertEquals(tedamUserFavoriteType4, tedamUserFavoriteType3);
	}

	@Test
	public void testEnumTedamUserRole() {
		TedamUserRole tedamUserRole = TedamUserRole.ADMIN;
		tedamUserRole.toString();
		String userRole = TedamUserRole.Constants.ADMIN;
		userRole = TedamUserRole.Constants.TESTER;
		userRole.toString();
		TedamUserRole.getAllRoles();
	}

	@Test
	public void testEnumTestRunType() {
		TestRunType testRunType = TestRunType.MANUAL;
		testRunType.getValue();
		testRunType.getId();
		testRunType.toString();
	}

	@Test
	public void testEnumTestStepType() {
		TestStepType staticJobParameter = TestStepType.FILTER_FILL;
		staticJobParameter = TestStepType.fromValue("RowCountVerify");
		staticJobParameter.getValue();
		staticJobParameter.getId();
		staticJobParameter.toString();
		staticJobParameter.getBeginRegex();
		staticJobParameter.getEndRegex();
		staticJobParameter = TestStepType.fromValue("test");
		TestStepType.getFillTestStepTypeList();
		TestStepType.getFormOpenTestStepTypeList();
		TestStepType.getLookupParameterIsNotEmptyTestStepTypes();
	}

}
