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

import java.util.ArrayList;
import java.util.List;

import com.lbs.tedam.localization.TedamLocalizerWrapper;

public class EnumsV2 {

	private static final String TAG_FORM_FILL = "FormFill";
	private static final String TAG_BUTTON_CLICK = "ButtonClick";
	private static final String TAG_VERIFY = "Verify";

	public enum TestStepType implements IEnumsV2 {

		FORM_OPEN("FormOpen", "", "", "enum.teststeptype.formopen"), //
		FORM_FILL(TAG_FORM_FILL, "<fof>", "</fof>", "enum.teststeptype.formfill"), //
		BUTTON_CLICK(TAG_BUTTON_CLICK, "<bc>", "</bc>", "enum.teststeptype.buttonclick"), //
		FILTER_FILL("FilterFill", "<ff>", "</ff>", "enum.teststeptype.filterfill"), //
		VERIFY(TAG_VERIFY, "<fvv>", "</fvv>", "enum.teststeptype.verify"),
		MESSAGE_VERIFY("MessageVerify", "<mv>", "</mv>", "enum.teststeptype.messageverify"),
		ROW_COUNT_VERIFY("RowCountVerify", "<rcv>", "</rcv>", "enum.teststeptype.rowcountverify"),
		GRID_SEARCH("GridSearch", "<gs>", "</gs>", "enum.teststeptype.gridsearch"),
		FORM_OPEN_SHORTCUT("FormOpenByShortcut", "", "", "enum.teststeptype.formopenshortcut"),
		GRID_ROW_SELECT("GridRowSelect", "<grs>", "</grs>", "enum.teststeptype.gridrowselect"),
		GRID_DOUBLE_CLICK("DoubleClick", "<dc>", "</dc>", "enum.teststeptype.griddoubleclick"),
		GRID_DELETE("Delete", "<gd>", "</gd>", "enum.teststeptype.griddelete"),
		GRID_CELL_SELECT("GridCellSelect", "<gcs>", "</gcs>", "enum.teststeptype.gridcellselect"), //
		POPUP("PopUp", "<pu>", "</pu>", "enum.teststeptype.popup"), //
		REPORT("Report", "", "", "enum.teststeptype.report"), //
		DOUBLE_CLICK("ComponentDoubleClick", "", "", "enum.teststeptype.doubleclick"), //
		WAIT("Wait", "", "", "enum.teststeptype.wait"); //

		private final String value;
		private final String beginRegex;
		private final String endRegex;
		private final String id;

		private TestStepType(String value, String beginRegex, String endRegex, String id) {
			this.value = value;
			this.beginRegex = beginRegex;
			this.endRegex = endRegex;
			this.id = id;
		}

		public static TestStepType fromValue(String value) {
			for (TestStepType testStepType : values()) {
				if (testStepType.getValue().equals(value)) {
					return testStepType;
				}
			}
			return null;
		}

		public static List<TestStepType> getFillTestStepTypeList() {
			List<TestStepType> testStepTypeList = new ArrayList<>();
			testStepTypeList.add(TestStepType.FORM_FILL);
			testStepTypeList.add(TestStepType.FILTER_FILL);
			testStepTypeList.add(TestStepType.VERIFY);
			return testStepTypeList;
		}

		public static List<TestStepType> getFormOpenTestStepTypeList() {
			List<TestStepType> testStepTypeList = new ArrayList<>();
			testStepTypeList.add(TestStepType.FORM_OPEN);
			testStepTypeList.add(TestStepType.FORM_OPEN_SHORTCUT);
			return testStepTypeList;
		}

		public static List<TestStepType> getLookupParameterIsNotEmptyTestStepTypes() {
			List<TestStepType> testStepTypeList = new ArrayList<>();
			testStepTypeList.add(TestStepType.FORM_FILL);
			testStepTypeList.add(TestStepType.BUTTON_CLICK);
			testStepTypeList.add(TestStepType.FILTER_FILL);
			testStepTypeList.add(TestStepType.VERIFY);
			testStepTypeList.add(TestStepType.MESSAGE_VERIFY);
			testStepTypeList.add(TestStepType.ROW_COUNT_VERIFY);
			testStepTypeList.add(TestStepType.GRID_SEARCH);
			testStepTypeList.add(TestStepType.GRID_ROW_SELECT);
			testStepTypeList.add(TestStepType.GRID_DOUBLE_CLICK);
			testStepTypeList.add(TestStepType.GRID_DELETE);
			testStepTypeList.add(TestStepType.GRID_CELL_SELECT);
			testStepTypeList.add(TestStepType.POPUP);
			testStepTypeList.add(TestStepType.WAIT);
			return testStepTypeList;
		}

		@Override
		public String getValue() {
			return value;
		}

		@Override
		public String getId() {
			return id;
		}

		@Override
		public String toString() {
			return getLocaleValue(getId());
		}

		public String getBeginRegex() {
			return beginRegex;
		}

		public String getEndRegex() {
			return endRegex;
		}
	}

	public enum TestRunType implements IEnumsV2 {

		MANUAL("Manual", "enum.testruntype.manual"), //
		AUTOMATED("Automated", "enum.testruntype.automated"); //

		private final String value;
		private final String id;

		private TestRunType(String value, String id) {
			this.value = value;
			this.id = id;
		}

		@Override
		public String getValue() {
			return value;
		}

		@Override
		public String getId() {
			return id;
		}

		@Override
		public String toString() {
			return getLocaleValue(getId());
		}
	}

	public enum JobType implements IEnumsV2 {

		NORMAL("Normal", "enum.jobtype.normal"), //
		QUICK("Quick", "enum.jobtype.quick"); //

		private final String value;
		private final String id;

		private JobType(String value, String id) {
			this.value = value;
			this.id = id;
		}

		@Override
		public String getValue() {
			return value;
		}

		@Override
		public String getId() {
			return id;
		}

		@Override
		public String toString() {
			return getLocaleValue(getId());
		}
	}

	public enum ExecutionStatus implements IEnumsV2 {
		FAILED("Failed", "enum.executionstatus.failed"), // wrongly done.
		SUCCEEDED("Succeeded", "enum.executionstatus.succeeded"), // successful done.
		NOTRUN("Not Run", "enum.executionstatus.notrun"), // He has not run yet.
		BLOCKED("Blocked", "enum.executionstatus.blocked"), // ended due to an ambiguous reason.
		CAUTION("Caution", "enum.executionstatus.caution");// it ended unexpectedly.

		private final String value;
		private final String id;

		ExecutionStatus(String value, String id) {
			this.value = value;
			this.id = id;
		}

		@Override
		public String getValue() {
			return value;
		}

		@Override
		public String getId() {
			return id;
		}

		@Override
		public String toString() {
			return getLocaleValue(getId());
		}

	}

	/**
	 * TedamBoolean states<br>
	 *
	 * @author Tarik.Mikyas<br>
	 */
	public enum TedamBoolean implements IEnumsV2 {

		FALSE(Boolean.FALSE.booleanValue(), 0, "enum.tedamboolean.false"), //
		TRUE(Boolean.TRUE.booleanValue(), 1, "enum.tedamboolean.true");

		private final Boolean value;
		private final Integer code;
		private final String id;

		TedamBoolean(Boolean value, Integer code, String id) {
			this.code = code;
			this.value = value;
			this.id = id;
		}

		public Integer getCode() {
			return code;
		}

		@Override
		public String getValue() {
			return value.toString();
		}

		public Boolean getBooleanValue() {
			return value;
		}

		@Override
		public String getId() {
			return id;
		}

		@Override
		public String toString() {
			return getLocaleValue(getId());
		}

	}

	public enum FormOpenShortcutType implements IEnumsV2 {

		EXCHANGE_RATES("!er!", "enum.formopenshortcuttype.exchangerates"), //
		SET_WORK_DATES("!swd!", "enum.formopenshortcuttype.setworkdates"); //

		private final String value;
		private final String id;

		private FormOpenShortcutType(String value, String id) {
			this.value = value;
			this.id = id;

		}

		public static FormOpenShortcutType fromValue(String value) {
			for (FormOpenShortcutType formOpenShortcutType : values()) {
				if (formOpenShortcutType.getValue().equals(value)) {
					return formOpenShortcutType;
				}
			}
			return null;
		}

		@Override
		public String getValue() {
			return value;
		}

		@Override
		public String getId() {
			return id;
		}

		@Override
		public String toString() {
			return getLocaleValue(getId());
		}
	}

	public enum JobStatus implements IEnumsV2 {

		NOT_STARTED("NOT_STARTED", "enum.jobstatus.notstarted"), PLANNED("PLANNED", "enum.jobstatus.planned"),
		QUEUED("QUEUED", "enum.jobstatus.queued"), STARTED("STARTED", "enum.jobstatus.started"),
		PAUSED("PAUSED", "enum.jobstatus.paused"), STOPPED("STOPPED", "enum.jobstatus.stopped"),
		COMPLETED("COMPLETED", "enum.jobstatus.completed"), WAITING_STOP("WAITING_STOP", "enum.jobstatus.waitingstop"),
		WAITING_PAUSE("WAITING_PAUSE", "enum.jobstatus.waitingpause");

		private final String value;
		private final String id;

		private JobStatus(String value, String id) {
			this.value = value;
			this.id = id;

		}

		public static List<JobStatus> getInActiveJobStatus() {
			List<JobStatus> inActiveJobStatusList = new ArrayList<>();
			inActiveJobStatusList.add(NOT_STARTED);
			inActiveJobStatusList.add(PAUSED);
			inActiveJobStatusList.add(STOPPED);
			inActiveJobStatusList.add(COMPLETED);
			return inActiveJobStatusList;
		}

		public static JobStatus fromValue(String value) {
			for (JobStatus jobStatus : values()) {
				if (jobStatus.getValue().equals(value)) {
					return jobStatus;
				}
			}
			return null;
		}

		@Override
		public String getValue() {
			return value;
		}

		@Override
		public String getId() {
			return id;
		}

		@Override
		public String toString() {
			return getLocaleValue(getId());
		}
	}

	public enum CommandStatus implements IEnumsV2 {

		NOT_STARTED("Not_Started", "enum.commandstatus.notstarted"), //
		IN_PROGRESS("In_Progress", "enum.commandstatus.inprogress"), //
		COMPLETED("Completed", "enum.commandstatus.completed"), //
		BLOCKED("Blocked", "enum.commandstatus.blocked");//

		private final String value;
		private final String id;

		private CommandStatus(String value, String id) {
			this.value = value;
			this.id = id;

		}

		public static CommandStatus fromValue(String value) {
			for (CommandStatus commandStatus : values()) {
				if (commandStatus.getValue().equals(value)) {
					return commandStatus;
				}
			}
			return null;
		}

		public static List<CommandStatus> getCompletedCommandStatus() {
			List<CommandStatus> completedCommandStatusList = new ArrayList<>();
			completedCommandStatusList.add(COMPLETED);
			completedCommandStatusList.add(BLOCKED);
			return completedCommandStatusList;
		}

		@Override
		public String getValue() {
			return value;
		}

		@Override
		public String getId() {
			return id;
		}

		@Override
		public String toString() {
			return getLocaleValue(getId());
		}
	}

	public enum ClientStatus implements IEnumsV2 {
		FREE("Free", "enum.clientstatus.free"), //
		BUSY("Busy", "enum.clientstatus.busy"), //
		DEAD("Dead", "enum.clientstatus.dead");

		private final String value;
		private final String id;

		private ClientStatus(String value, String id) {
			this.value = value;
			this.id = id;

		}

		public static ClientStatus fromValue(String value) {
			for (ClientStatus clientStatus : values()) {
				if (clientStatus.getValue().equals(value)) {
					return clientStatus;
				}
			}
			return null;
		}

		@Override
		public String getValue() {
			return value;
		}

		@Override
		public String getId() {
			return id;
		}

		@Override
		public String toString() {
			return getLocaleValue(getId());
		}
	}

	public enum StaticJobParameter implements IEnumsV2 {

		TEST_SET_ID("testSetId", "enum.clientstatus.testSetId"), //
		TEST_CASE_ID("testCaseId", "enum.clientstatus.testCaseId"), //
		TEST_STEPS("testSteps", "enum.clientstatus.testSteps"),
		CLIENT_HOST_NAME("clientHostName", "enum.clientstatus.clientHostName");

		private final String value;
		private final String id;

		private StaticJobParameter(String value, String id) {
			this.value = value;
			this.id = id;

		}

		public static StaticJobParameter fromValue(String value) {
			for (StaticJobParameter staticJobParameter : values()) {
				if (staticJobParameter.getValue().equals(value)) {
					return staticJobParameter;
				}
			}
			return null;
		}

		@Override
		public String getValue() {
			return value;
		}

		@Override
		public String getId() {
			return id;
		}

		@Override
		public String toString() {
			return getLocaleValue(getId());
		}
	}

	public enum ReportStatus implements IEnumsV2 {

		OK("ok", "enum.reportstatus.ok"), //
		MISSING_DATA("missingData", "enum.reportstatus.missingdata"), //
		MISSING_FILTER("missingFilter", "enum.reportstatus.missingfilter"), //
		ERROR("error", "enum.reportstatus.error"), //
		MISSING_PARAMETER("missingParameter", "enum.reportstatus.missingparameter"),
		BIG_DATA("bigData", "enum.reportstatus.bigdata"), UNKNOWN("unknown", "enum.reportstatus.unknown"),
		NO_DATA("noData", "enum.reportstatus.nodata");

		private final String value;
		private final String id;

		private ReportStatus(String value, String id) {
			this.value = value;
			this.id = id;

		}

		public static ReportStatus fromValue(String value) {
			for (ReportStatus reportStatus : values()) {
				if (reportStatus.getValue().equals(value)) {
					return reportStatus;
				}
			}
			return null;
		}

		@Override
		public String getValue() {
			return value;
		}

		@Override
		public String getId() {
			return id;
		}

		@Override
		public String toString() {
			return getLocaleValue(getId());
		}
	}

	public enum ReportLifeCycle implements IEnumsV2 {

		NEW("New", "enum.reportlifecycle.new"), //
		COMPARABLE("Comparable", "enum.reportlifecycle.comparable");

		private final String value;
		private final String id;

		private ReportLifeCycle(String value, String id) {
			this.value = value;
			this.id = id;

		}

		public static ReportLifeCycle fromValue(String value) {
			for (ReportLifeCycle reportLifeCycle : values()) {
				if (reportLifeCycle.getValue().equals(value)) {
					return reportLifeCycle;
				}
			}
			return null;
		}

		@Override
		public String getValue() {
			return value;
		}

		@Override
		public String getId() {
			return id;
		}

		@Override
		public String toString() {
			return getLocaleValue(getId());
		}
	}

	public enum JobParameterType implements IEnumsV2 {

		CONSTANT("constant", "enum.jobparametertype.constant", "$"), //
		STATIC("static", "enum.jobparametertype.static", "#"); //

		private final String value;
		private final String id;
		private final String sign;

		private JobParameterType(String value, String id, String sign) {
			this.value = value;
			this.id = id;
			this.sign = sign;

		}

		public static JobParameterType fromValue(String value) {
			for (JobParameterType jobParameterType : values()) {
				if (jobParameterType.getValue().equals(value)) {
					return jobParameterType;
				}
			}
			return null;
		}

		@Override
		public String getValue() {
			return value;
		}

		@Override
		public String getId() {
			return id;
		}

		@Override
		public String toString() {
			return getLocaleValue(getId());
		}

		public String getSign() {
			return sign;
		}
	}

	public enum TedamSocketMessageType {
		JOB, CLIENT;
	}

	public enum TedamFolderType implements IEnumsV2 {
		TESTSET("TestSet", "enum.tedamfoldertype.testset"), //
		TESTCASE("TestCase", "enum.tedamfoldertype.testcase"); //

		private final String value;
		private final String id;

		private TedamFolderType(String value, String id) {
			this.value = value;
			this.id = id;

		}

		public static TedamFolderType fromValue(String value) {
			for (TedamFolderType tedamFolderType : values()) {
				if (tedamFolderType.getValue().equals(value)) {
					return tedamFolderType;
				}
			}
			return null;
		}

		@Override
		public String getValue() {
			return value;
		}

		@Override
		public String getId() {
			return id;
		}

		@Override
		public String toString() {
			return getLocaleValue(getId());

		}
	}

	public enum TedamUserFavoriteType implements IEnumsV2 {
		CLIENT("Client", "enum.tedamuserfavoritetype.client"), //
		JOB("Job", "enum.tedamuserfavoritetype.job"), //
		ENVIRONMENT("Environment", "enum.tedamuserfavoritetype.environment"); //

		private final String value;
		private final String id;

		private TedamUserFavoriteType(String value, String id) {
			this.value = value;
			this.id = id;

		}

		public static TedamUserFavoriteType fromValue(String value) {
			for (TedamUserFavoriteType tedamUserFavoriteType : values()) {
				if (tedamUserFavoriteType.getValue().equals(value)) {
					return tedamUserFavoriteType;
				}
			}
			return null;
		}

		@Override
		public String getValue() {
			return value;
		}

		@Override
		public String getId() {
			return id;
		}

		@Override
		public String toString() {
			return getLocaleValue(getId());
		}

	}

	public enum LogType {
		REPORT;
	}

	public enum LogMessage {
		ADD, DELETE, SIZE;
	}

	public enum ScriptAccessorType implements IEnumsV2 {
		SAHI("Sahi", "enum.scriptaccessortype.sahi"), //
		SELENIUM("Selenium", "enum.scriptaccessortype.selenium"); //

		private final String value;
		private final String id;

		private ScriptAccessorType(String value, String id) {
			this.value = value;
			this.id = id;

		}

		public static ScriptAccessorType fromValue(String value) {
			for (ScriptAccessorType scriptAccessorType : values()) {
				if (scriptAccessorType.getValue().equals(value)) {
					return scriptAccessorType;
				}
			}
			return null;
		}

		@Override
		public String getValue() {
			return value;
		}

		@Override
		public String getId() {
			return id;
		}

		@Override
		public String toString() {
			return getLocaleValue(getId());
		}

	}

	public enum SeleniumByType {
		XPATH, INNERTEXT, NAME, ID;

		public static SeleniumByType findSeleniumByType(String value) {
			for (SeleniumByType byType : values()) {
				if (value.toLowerCase().contains(byType.name().toLowerCase())) {
					return byType;
				}
			}
			return null;
		}

	}

	public enum BrowserType {
		CHROME, FIREFOX, IE;
	}

	public enum ScriptAccessorOperationType implements IEnumsV2 {
		CLICK("click", "enum.scriptaccessoroperationtype.click"), //
		READ("read", "enum.scriptaccessoroperationtype.read"), //
		WRITE("write", "enum.scriptaccessoroperationtype.write"); //

		private final String value;
		private final String id;

		private ScriptAccessorOperationType(String value, String id) {
			this.value = value;
			this.id = id;

		}

		public static ScriptAccessorOperationType fromValue(String value) {
			for (ScriptAccessorOperationType scriptAccessorOperationType : values()) {
				if (scriptAccessorOperationType.getValue().equals(value)) {
					return scriptAccessorOperationType;
				}
			}
			return null;
		}

		@Override
		public String getValue() {
			return value;
		}

		@Override
		public String getId() {
			return id;
		}

		@Override
		public String toString() {
			return getLocaleValue(getId());
		}

	}

	/**
	 * @author Canberk.Erkmen command sequence. This must change. RunJguar is also
	 *         used as a real run.<br>
	 *         TODO:canberk If draftOrder adds a type and chooses which of those
	 *         types is the main scenario, it will point to runOrder and give up the
	 *         enumerator. <br>
	 *         this choice should be in the draftCommand entity. We must combo pick
	 *         and fix. <br>
	 *         The main problem is that the command from the webservice does not
	 *         know which testCaseCost script is in the startJobCommandOperations
	 *         procedure <br>
	 */
	public enum RunOrder implements IEnumsV2 {
		CREATE_SCRIPT("CreateScript", "enum.runorder.createscript"), //
		RUN_SCRIPT("RunScript", "enum.runorder.runscript"); //

		private final String value;
		private final String id;

		private RunOrder(String value, String id) {
			this.value = value;
			this.id = id;

		}

		public static RunOrder fromValue(String value) {
			for (RunOrder runOrder : values()) {
				if (runOrder.getValue().equals(value)) {
					return runOrder;
				}
			}
			return null;
		}

		@Override
		public String getValue() {
			return value;
		}

		@Override
		public String getId() {
			return id;
		}

		@Override
		public String toString() {
			return getLocaleValue(getId());
		}
	}

	public enum TedamUserRole {
		ADMIN(Constants.ADMIN), TESTER(Constants.TESTER);

		private final String label;

		private TedamUserRole(String label) {
			this.label = label;
		}

		public static String[] getAllRoles() {
			return new String[] { ADMIN.toString(), TESTER.toString() };
		}

		@Override
		public String toString() {
			return this.label;
		}

		public static class Constants {

			public static final String ADMIN = "admin";
			public static final String TESTER = "tester";

			private Constants() {
				// Constants private constructor
			}
		}

	}

	public enum NotificationType {
		MAIL;
	}

	public interface IEnumsV2 extends TedamLocalizerWrapper {

		String getValue();

		String getId();
	}

}
