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

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

import com.lbs.tedam.model.Property;
import com.lbs.tedam.model.SnapshotValue;
import com.lbs.tedam.model.TedamScriptAccessor;
import com.lbs.tedam.model.TestReport;
import com.lbs.tedam.model.DTO.GridCell;
import com.lbs.tedam.model.DTO.LookupParameter;
import com.lbs.tedam.model.DTO.MessageDialog;
import com.lbs.tedam.model.DTO.TabbedPaneAndPageParent;
import com.lbs.tedam.recorder.TestStepTimeRecord;
import com.lbs.tedam.util.Constants;
import com.lbs.tedam.util.DateTimeUtils;
import com.lbs.tedam.util.Enums.FileName;
import com.lbs.tedam.util.Enums.TedamLogLevel;
import com.lbs.tedam.util.PropUtils;
import com.lbs.tedam.util.TedamFileUtils;
import com.lbs.tedam.util.TedamJsonFactory;
import com.lbs.tedam.util.TedamLogUtils;
import com.lbs.tedam.util.TedamRegexUtils;
import com.lbs.tedam.util.TedamStringUtils;
import com.lbs.tedam.util.TedamXPathUtils;
import com.lbs.tedam.webservice.rest.client.RestClient;

import jxl.write.WriteException;

/**
 * BSH will only communicate with TEDAM through this service class.<br>
 *
 * @author Tarik.Mikyas
 */
public class ScriptService {
	/**
	 * Logger LOGGER
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ScriptService.class);
	private static final String SCRIPT_SERVICE_CREATED = "ScriptService created";
	private static final String SCRIPT_SERVICE = "ScriptService";
	private String baseRestUrl;
	private RestClient restClient = new RestClient();

	/**
	 * @param projectPath
	 */
	public ScriptService(boolean isTest) {
		if (isTest) {
			String configFilePath = getClass().getResource(Constants.RESOURCE_CONFIG_TEST_PROPERTIES).getFile();
			LOGGER.info(configFilePath, "ScriptService created. configFilePath : {}");
			TedamLogUtils.log(SCRIPT_SERVICE, "configFilePath : " + configFilePath, TedamLogLevel.INFO, true);
			PropUtils.loadPropFile(configFilePath);
		}
		baseRestUrl = PropUtils.getProperty(Constants.BASE_REST_URL);
		TedamLogUtils.log(SCRIPT_SERVICE, SCRIPT_SERVICE_CREATED, TedamLogLevel.INFO, true);
	}

	public ScriptService(String configFilePath) {
		if (!configFilePath.isEmpty()) {
			PropUtils.loadPropFile(configFilePath + Constants.FILE_SEPARATOR + FileName.CONFIG_PROPERTIES.getName());
			TedamLogUtils.log(SCRIPT_SERVICE, "configFilePath " + configFilePath, TedamLogLevel.INFO, true);
		}
		baseRestUrl = PropUtils.getProperty(Constants.BASE_REST_URL);
		TedamLogUtils.log(SCRIPT_SERVICE, SCRIPT_SERVICE_CREATED, TedamLogLevel.INFO, true);
	}

	public ScriptService() {
		baseRestUrl = PropUtils.getProperty(Constants.BASE_REST_URL);
		TedamLogUtils.log(SCRIPT_SERVICE, SCRIPT_SERVICE_CREATED, TedamLogLevel.INFO, true);
		LOGGER.info("ScriptService created.");
	}

	/**
	 * It writes the log message to the screen according to incoming parameters and
	 * adds it to the static defined logList.
	 *
	 * @param header        where the procedure is called
	 * @param key           property key
	 * @param logLevel      message level
	 * @param loggingStatus
	 * @author Tarik.Mikyas
	 */
	public void log(String header, Object key, TedamLogLevel logLevel, Boolean loggingStatus) {
		TedamLogUtils.log(header, key, logLevel, loggingStatus);
	}

	/**
	 * This method parses the given parameter string and fills the member hashmap
	 * named m_popUpMenuItems
	 *
	 * @param popUpMenuItems
	 * @usedIn PopUp.bsh
	 * @author Tarik.Mikyas
	 */
	public int getPopUpMenuItemIndex(List<Object> popUpMenuItems, String popUpItemName) {
		for (int i = 0; i < popUpMenuItems.size(); i++) {
			String[] nameAndID = popUpMenuItems.get(i).toString().split(",");
			nameAndID[0] = nameAndID[0].substring(1);
			nameAndID[1] = nameAndID[1].replace(")", "");
			if (popUpItemName.equals(nameAndID[0])) {
				return Integer.parseInt(nameAndID[1]);
			}
		}
		return Constants.VALUE_NULL_INTEGER;
	}

	/**
	 * It finds the control element with the attribute = "POPUP" attribute in the
	 * given xml and returns the tag value.
	 *
	 * @param xmlDoc
	 * @return
	 * @throws SQLException
	 * @throws XPathExpressionException
	 * @usedIn PopUp.bsh
	 * @author Tarik.Mikyas
	 */
	public int getPopUpMenuTag(Element xmlDoc) {
		return TedamXPathUtils.getPopUpMenuTag(xmlDoc);
	}

	/**
	 * Returns the snapshotValue list will be @usedIn FilterFill <br>
	 * List elements will have snapshotDefinitionId and also lower than and equals
	 * the given version value. <br>
	 * If there is no SnapshotDefinition with given snapshotDefinitionId, then
	 * return value will be null. <br>
	 * If there is no SnapshotValue with given snapshotDefinitionId, then return
	 * will be empty list. <br>
	 *
	 * @param version
	 * @param snapshotDefinitionId
	 * @throws SQLException
	 * @usedIn Base.bsh, SCNavigator.bsh
	 * @author Tarik.Mikyas
	 */
	public List<SnapshotValue> getSnapshotFilterFillValueBOList(String version, int snapshotDefinitionId) {
		String url = baseRestUrl + "ScriptRestService/getSnapshotFilterFillValueBOList";
		String jsonString = restClient.getValue(url, version, String.valueOf(snapshotDefinitionId));
		List<SnapshotValue> snapshotValueList = TedamJsonFactory.fromJsonList(jsonString, SnapshotValue.class);
		return snapshotValueList;
	}

	/**
	 * This method returns the BO list to be sent as input to the FormFill modular
	 * script. <br>
	 * Returns the snaspshot_values table data corresponding to the version and
	 * ss_id values received as parameters. When ss_id is not in the
	 * snapshot_definitions table method returns null. If there is no entry with
	 * ss_id in the SnapshotValue table, it returns a hollow array. <br>
	 *
	 * @param version
	 * @param snapshotDefinitionId
	 * @throws SQLException
	 * @usedIn Base.bsh, SCNavigator.bsh
	 */
	public List<SnapshotValue> getSnapshotFormFillValueBOList(String version, int snapshotDefinitionId) {
		String url = baseRestUrl + "ScriptRestService/getSnapshotFormFillValueBOList";
		LOGGER.info(url);
		String jsonString = restClient.getValue(url, version, String.valueOf(snapshotDefinitionId));
		List<SnapshotValue> snapshotValueList = TedamJsonFactory.fromJsonList(jsonString, SnapshotValue.class);
		return snapshotValueList;
	}

	/**
	 * Snapshot_value's parent_tag indicates if there is a grid, false if the grid
	 * is not TRUE <br>
	 *
	 * @param snapshotValue
	 * @usedIn FormFill.bsh
	 */
	public boolean isComponentGridCell(SnapshotValue snapshotValue) {
		return !snapshotValue.getParentTag().equals(Constants.VALUE_NULL);
	}

	/**
	 * This method returns the tag value of button with attributes
	 * attribute="CLOSE", visible="true", enabled="true".<br>
	 *
	 * @param xmlDoc
	 * @return
	 * @throws XPathExpressionException
	 * @usedIn CloseAllForms.bsh
	 */
	public int getCloseButton(Element xmlDoc) {
		return TedamXPathUtils.getCloseButton(xmlDoc);

	}

	/**
	 * In the given snapshot, the control with fieldTagId tag value is found. All
	 * the tabs / tabbedpane binaries contained in the found control are
	 * hierarchically listed up to root. If a field that is to be edited while
	 * formFill is done in this way is in a different tab, then tabbing can be
	 * performed.
	 *
	 * @param snapshot
	 * @param fieldTagID
	 * @throws XPathExpressionException
	 * @usedIn FormFill.bsh
	 */
	public List<TabbedPaneAndPageParent> getControlTabProperties(Element snapshot, int fieldTagID) {
		return TedamXPathUtils.getControlTabProperties(snapshot, fieldTagID);
	}

	/**
	 * The given snapshot contains the DataGrid with the dataGridTag tag value. All
	 * of the tab / tabbedpane binaries in the found DataGrid are hierarchically
	 * listed up to root. If a field that is to be edited while formFill is done in
	 * this way is in a different tab, then tabbing can be performed.
	 *
	 * @param element
	 * @param gridTagID
	 * @return
	 * @throws XPathExpressionException
	 * @usedIn FormFill.bsh, PopUp.bsh
	 */
	public List<TabbedPaneAndPageParent> getGridTabProperties(Element element, Integer gridTagID) {
		return TedamXPathUtils.getGridTabParents(element, gridTagID);
	}

	/**
	 * The given snapshot contains the filterGrid with the filterGridTag tag value.
	 * All tabs / tabbedpane binaries in the filterGrid found are listed
	 * hierarchically up to root. If a field that is to be edited while formFill is
	 * done in this way is in a different tab, then tabbing can be performed.
	 *
	 * @param element
	 * @param gridTagID
	 * @return
	 * @throws XPathExpressionException
	 * @usedIn FilterFill.bsh
	 */
	public List<TabbedPaneAndPageParent> getFilterTabProperties(Element element, int filterTagID) {
		return TedamXPathUtils.getFilterTabParents(element, filterTagID);
	}

	/**
	 * This method returns the tag value of the first DataGrid element in the given
	 * snapshot.<br>
	 *
	 * @param xmlDoc
	 * @throws XPathExpressionException
	 * @usedIn FilterFill.bsh
	 */
	public Integer getDataGridTag(Element xmlDoc) {
		return TedamXPathUtils.getFirstOccuredGridTag(xmlDoc);
	}

	/**
	 * This method parses given parameter string and returns in proper MessageDialog
	 * parameter format.<br>
	 *
	 * @param snapshot
	 * @usedIn Base.bsh, formfill.bsh
	 */
	public List<MessageDialog> messageDialogParameterParser(String messageDialogParameter) {
		return TedamStringUtils.messageDialogParameterParser(messageDialogParameter);
	}

	/**
	 * This method checks the enabled value of the control element with the given
	 * tag. <br>
	 * If enabled true; false if it is not. <br>
	 *
	 * @param snapShot
	 * @param tag
	 * @throws XPathExpressionException
	 * @usedIn: FormFill.bsh
	 */
	public boolean isControlEnabled(Element snapShot, int tag) {
		return TedamXPathUtils.isControlEnabled(snapShot, tag);
	}

	/**
	 * This method parses given xml file and returns "message" elements' "message"
	 * attributes as list. <br>
	 * Returns an empty list if given xml does not contain "message" element.<br>
	 *
	 * @param snapShot
	 * @throws XPathExpressionException
	 * @usedIn ButtonClick.bsh
	 */
	public List<String> getErrorMessages(Element snapShot) {
		return TedamXPathUtils.getMessages(snapShot, 4);
	}

	/**
	 * It examines the message definitions in the given snapshot.Messages with the
	 * given messageType value as type value are returned. If the MessageType value
	 * is -9999, it returns all results.
	 *
	 * @param snapshot
	 * @param expectedMessage
	 * @param continueOnError
	 * @return
	 * @throws XPathExpressionException
	 * @author Ozgur.Ozbil
	 */
	public int doMessageExist(Element snapShot, String expectedMessage, boolean continueOnError) {
		List<String> messageList = TedamXPathUtils.getMessages(snapShot, Constants.VALUE_NULL_INTEGER);
		log("ScripService.doMessageExist", "messageList :" + messageList, TedamLogLevel.INFO, Boolean.TRUE);
		if (messageList.contains(expectedMessage.trim())) { // If it is not entered, it means there is an error.
			return 1; // statusMessages.succeeded.getStatusId()
		} else if (continueOnError) { // statusMessages.caution.getStatusId()
			return 2; // there is an error because the previous condition is not entered. If
			// continuing cautionId rotate
		} else {
			return 0; // statusMessages.failed.getStatusId() ERROR message returns.
		}
	}

	/**
	 * Separates the parameterCollection parameter with "/" characters, and returns
	 * the result list. <br>
	 * While the returned list might have more than one element when called for a
	 * filter of type group, When called for a selection type filter, only 1 element
	 * the owner will be.
	 *
	 * @param parameterCollection
	 * @usedIn FilterFill.bsh
	 */
	public List<String> decomposeGroupAndSelectionValues(String parameterCollection) {
		return Arrays.asList(parameterCollection.split("/"));
	}

	/**
	 * This method controls if any element exists with tag={buttonTag},
	 * visible="true", enabled="true" attributes in given xml document. <br>
	 *
	 * @param snapshot
	 * @param buttonTag
	 * @throws XPathExpressionException
	 * @usedIn ButtonClick.bsh
	 */
	public boolean buttonExists(Element xmlDoc, Integer buttonTag) {
		return TedamXPathUtils.buttonExists(xmlDoc, buttonTag);
	}

	/**
	 * This method outputs the resulting excel file of the test case <br>
	 *
	 * @param reportList
	 * @param resultFilePath
	 * @throws IOException
	 * @throws WriteException
	 * @usedIn Base.bsh
	 */
	public boolean printTestReport(List<TestReport> reportList, String resultFileName, String filePath) {
		TedamLogUtils.log("ScriptService.printTestReport", "printTestReport yordami cagiriliyor.", TedamLogLevel.INFO,
				true);
		StringBuilder sbResultFilePath;
		sbResultFilePath = new StringBuilder(filePath);
		sbResultFilePath.append(resultFileName);
		String stringResultFilePath = sbResultFilePath.toString();
		return TedamFileUtils.printReport(reportList, stringResultFilePath);
	}

	/**
	 * This method decodes filterfill parameters for nonrange types and returns in
	 * order. <br>
	 * index 0 => grouped value entered to filter. <br>
	 * index 1 => excluded value entered to filter. <br>
	 *
	 * @param parameterCollection
	 * @return
	 * @author Ozgur.Ozbil
	 */
	public List<String> decomposeNonRangeValues(String parameterCollection) {
		return TedamRegexUtils.decomposeNonRangeValues(parameterCollection);
	}

	/**
	 * This method decodes filterfill parameters for range types and returns in
	 * order. <br>
	 * index 0 => grouped value entered to filter. <br>
	 * index 1 => low value entered to filter. <br>
	 * index 2 => high value entered to filter. <br>
	 * index 3 => excluded value entered to filter.<br>
	 *
	 * @param parameterCollection
	 * @usedIn FilterFill.bsh
	 */
	public List<String> decomposeRangeValues(String parameterCollection) {
		return TedamRegexUtils.decomposeRangeValues(parameterCollection);
	}

	/**
	 * This method getting fillerFieldList with given snapshotDefinitionId. Returns
	 * SnapshotValue list. Returns all snapshotValue values that have a given
	 * SnapshotDefinitionID other than the Filter fields.
	 *
	 * @param snapshotDefinitionId
	 * @return
	 * @usedIn verify.bsh
	 * @author Tarik.Mikyas
	 */
	public List<SnapshotValue> getFillerFieldsList(Integer snapshotDefinitionId) {
		String url = baseRestUrl + "ScriptRestService/getSnapshotValueList";
		String jsonString = restClient.getValue(url, String.valueOf(snapshotDefinitionId));
		List<SnapshotValue> fillerFieldsList = TedamJsonFactory.fromJsonList(jsonString, SnapshotValue.class);
		log("ScriptUtils", "getFillerFieldsList (snapshotDefinitionId :" + snapshotDefinitionId + ")",
				TedamLogLevel.INFO, Boolean.TRUE);
		return fillerFieldsList;
	}

	/**
	 * This method compares control and datagrid elements of given xml document with
	 * the snapshotValues of snapshotDefinition with given snapshotDefinitionId.
	 * Returns test step object of resulting report for verify. <br>
	 *
	 * @param xmlDoc
	 * @param snapshotDefinitionId
	 * @param formName
	 * @return
	 * @usedIn Verify.bsh
	 * @author Ozgur.Ozbil @modified_by Tarik.Mikyas
	 */
	public TestReport validateSavedData(Element xmlDoc, List<SnapshotValue> fillerFieldsList, String formName,
			boolean isLookup, boolean isIgnoreRowIndex) {
		log("ScriptUtils", "ScriptUtils.validateSavedData(xmlDoc :" + xmlDoc + " fillerFieldsList :" + fillerFieldsList
				+ " formName :" + formName + " isLookup :" + isLookup, TedamLogLevel.INFO, Boolean.TRUE);

		Map<String, String> verifyFieldMap = TedamXPathUtils.getSnapshotFieldMap(xmlDoc);
		return TedamStringUtils.validateSavedData(verifyFieldMap, fillerFieldsList, formName, isLookup,
				isIgnoreRowIndex);

	}

	/**
	 * Returns the rowIndex of splitter of snapshotDefinition from property table of
	 * given gridTag.
	 *
	 * @param snapshotDefinitionId
	 * @param tag
	 * @return
	 * @usedIn FormFill.bsh
	 * @author Ozgur.Ozbil
	 */
	public int getGridSplitterIndex(int snapshotDefinitionId, String tag) {
		String url = baseRestUrl + "ScriptRestService/getGridSplitterIndex";
		String jsonString = restClient.getValue(url, String.valueOf(snapshotDefinitionId), tag);
		Property property = TedamJsonFactory.fromJson(jsonString, Property.class);
		if (property == null || property.getValue() == null) {
			return -1;
		} else {
			int gridSplitterIndex = Integer.valueOf(property.getValue().trim()).intValue();
			return gridSplitterIndex;
		}
	}

	/**
	 * Returns the rowIndex of Splitter of the grid with given gridTag in given
	 * xmlDoc.
	 *
	 * @param snapshot
	 * @param gridTag
	 * @return
	 * @throws XPathExpressionException
	 * @usedIn FormFill.bsh
	 */
	public int getCurrentGridSplitter(Element xmlDoc, String gridTag) {
		return TedamXPathUtils.getCurrentGridSplitter(xmlDoc, gridTag);
	}

	/**
	 * The CheckBox was developed to solve the problems encountered during the
	 * operation of the component.
	 *
	 * @param value
	 * @return
	 * @usedIn: FormFill.bsh
	 */
	public String getCorrectValueForCheckBox(String value) {
		switch (value) {
		case "true":
		case "1":
			return "1";
		case "false":
		case "2":
			return "2";
		default:
			return "2";
		}
	}

	/**
	 * This method returns the tag of the button whose attribute is equal to the 2nd
	 * parameter in the parameterized snapshot.
	 *
	 * @param snapshot
	 * @param buttonAttribute
	 * @return
	 * @throws XPathExpressionException
	 * @throws TransformerException
	 */
	public int getButtonTag(Element xmlDoc, String buttonAttribute) {
		return TedamXPathUtils.getButtonTag(xmlDoc, buttonAttribute);
	}

	/**
	 * It will parse the lookup parameter given to formfill in the SnapshotValue
	 * table and return it as the list of objects in LookupParameter.
	 *
	 * @param lookupParam
	 * @return
	 * @usedIn FormFill.bsh
	 * @author Ozgur.Ozbil
	 */
	public List<LookupParameter> getLookupParamProp(String lookupParam) {
		return TedamRegexUtils.getLookupParamProp(lookupParam);
	}

	/**
	 * Grid is used during row select operation. Returns the index of the row
	 * indexed by text, and returns an integer arrayList. <br>
	 *
	 * @param rowIndexList
	 * @return
	 * @throws XPathExpressionException
	 * @usedIn Base.bsh, tedamLookUp
	 * @author Ozgur.Ozbil
	 */
	public List<Integer> getRowIndexList(String rowIndexList) {
		return TedamStringUtils.getRowIndexList(rowIndexList);
	}

	/**
	 * CheckBoxGroup parses the value held in the database for the component.
	 * Returns a list of the tags of the options to be checked. <br>
	 *
	 * @param value
	 * @return
	 * @usedIn FormFill.bsh
	 */
	public int[] getMultiSelectionList(String value) {
		String[] resourceList = value.split("/");
		int[] resourceTagList = new int[resourceList.length];
		for (int i = 0; i < resourceList.length; i++) {
			resourceTagList[i] = Integer.parseInt(resourceList[i]);
		}
		return resourceTagList;
	}

	/**
	 * this method copySourceToTargetFileByFormat <br>
	 *
	 * @param sourceFilePathString
	 * @param targetFilePathString
	 * @param format
	 * @param isMoved
	 * @return <br>
	 * @author Canberk.Erkmen
	 */
	public boolean copySourceToDestinationWithFormat(String sourceFilePathString, String targetFilePathString,
			String format, boolean isMoved) {
		return TedamFileUtils.copySourceToDestinationWithFormat(sourceFilePathString, targetFilePathString, format,
				isMoved);
	}

	/**
	 * This method brings the name of the report from the file with the .igv
	 * extension.
	 *
	 * @param reportFilePath
	 * @return
	 * @usedIn report.bsh
	 * @author Tarik.Mikyas
	 */
	public String getReportNameFromFile(String reportFilePath) {
		return TedamXPathUtils.getReportNameFromFile(reportFilePath);
	}

	/**
	 * Used in the formfill procedure.
	 *
	 * @author Tarik.Mikyas
	 */
	public String getFormattedDateStringAsString(String time) {
		return DateTimeUtils.getFormattedDateAsString(time);
	}

	/**
	 * This method returns given tag's xml trace with title attributes until the
	 * root
	 *
	 * @param snapshot
	 * @param tag
	 * @return
	 * @author Tarik.Mikyas
	 */
	public String pathTooltip(Element snapshot, String tag) {
		return TedamXPathUtils.pathTooltip(snapshot, tag);
	}

	/**
	 * this method getTedamScriptAccessorList returns the values in the
	 * TedamScriptAccessors table in the database<br>
	 *
	 * @param scriptAccessorType
	 * @param scriptAccessorOperationType
	 * @return <br>
	 * @usedIn ButtonClick.sah, FormFill.sah, Verify.sah
	 * @author Canberk.Erkmen
	 */
	public List<TedamScriptAccessor> getTedamScriptAccessorList(String scriptAccessorType,
			String scriptAccessorOperationType) {
		String url = baseRestUrl + "ScriptRestService/getTedamScriptAccessorList";
		String jsonString = restClient.getValue(url, scriptAccessorType, scriptAccessorOperationType);
		List<TedamScriptAccessor> tedamScriptAccessorList = TedamJsonFactory.fromJsonList(jsonString,
				TedamScriptAccessor.class);
		log("getTedamScriptAccessorList",
				"url : " + url + " tedamScriptAccessorList size : " + tedamScriptAccessorList.size(),
				TedamLogLevel.INFO, Boolean.TRUE);
		return tedamScriptAccessorList;
	}

	/**
	 * this method saveTestStepTimeRecordList <br>
	 *
	 * @param timeRecordList <br>
	 * @author Canberk.Erkmen
	 */
	public void saveTestStepTimeRecordList(List<TestStepTimeRecord> timeRecordList) {
		if (timeRecordList.size() > 0) {
			String url = baseRestUrl + "ScriptRestService/saveTestStepTimeRecordList";
			restClient.postValue(url, TedamJsonFactory.toJson(timeRecordList));
		}
	}

	public String getFileContent(String testCaseId, String fileName) {
		String url = baseRestUrl + "TedamRestService/getFileContent";
		String fileContent = restClient.getValue(url, testCaseId, fileName);
		return fileContent;
	}

	public void fillReleaseInfo(List<TestStepTimeRecord> timeRecordList, String release) {
		for (int i = 0; i < timeRecordList.size(); i++)
			timeRecordList.get(i).setRelease(release);
	}

	public void setTestStepIdOfLastRecord(List<TestStepTimeRecord> timeRecordList, int testStepId) {
		if (timeRecordList.size() > 0)
			timeRecordList.get(timeRecordList.size() - 1).setTestStepId(testStepId);
	}

	public void setTestStepIdToReportList(List<TestReport> testReportList, int testStepId) {
		if (testReportList.size() > 0)
			testReportList.get(testReportList.size() - 1).setTestStepId(testStepId);
	}

	public void deleteFile(String filePath) {
		TedamFileUtils.deleteFile(filePath);
	}

	public List<GridCell> getGridSearchParameterList(String gridSearchParameter) {
		String url = baseRestUrl + "ScriptRestService/getGridSearchParameterList";
		String jsonString = restClient.getValue(url, gridSearchParameter);
		List<GridCell> list = TedamJsonFactory.fromJsonList(jsonString, GridCell.class);
		return list;
	}

}
