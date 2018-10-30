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

import com.lbs.tedam.bsh.utils.ScriptService;
import com.lbs.tedam.exception.JobCommandBuildException;
import com.lbs.tedam.model.DTO.LogoTestResult;
import com.lbs.tedam.model.DTO.MessageDialog;
import com.lbs.tedam.model.DTO.Resource;
import com.lbs.tedam.model.SnapshotValue;
import com.lbs.tedam.model.TestReport;
import com.lbs.tedam.model.TestStep;
import com.lbs.tedam.util.Enums.Regex;
import com.lbs.tedam.util.Enums.StatusMessages;
import com.lbs.tedam.util.Enums.TedamLogLevel;
import com.lbs.tedam.util.EnumsV2.ExecutionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TedamStringUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(TedamStringUtils.class);

    private TedamStringUtils() {
        // TedamStringUtils private constructor
    }

    /**
     * Used for selecting multiple rows in the GridRowSelect operation. <br>
     * Example: indexes = "[1,2,3,4]" returns: returnList with elements 1,2,3,4
     *
     * @param indexes
     * @return
     * @author Ozgur.Ozbil
     */
    public static List<Integer> getRowIndexList(String indexes) {
        List<Integer> returnList = new ArrayList<Integer>();
        // Remove the leading and trailing "[" and "]" characters.
        String indxes = indexes.substring(1, indexes.length() - 1);
        // When it is parsed according to its "," character, it will have the form
        // tag-searchtext-tag-searchetext, ... in that order.
        String[] elementList = indxes.split(",");
        for (int i = 0; i < elementList.length; i++) {
            returnList.add(Integer.parseInt(elementList[i]));
        }
        return returnList;
    }

    /**
     * This method parse the given parameter and maintains the maneuverable parts. Each element of the rotated list has button and message values. <br>
     * [(true,Sure!spc!yourown),(false,Son!spcDecision!spc?)]
     *
     * @param messageDialogParameter
     * @return
     */
    public static List<MessageDialog> messageDialogParameterParser(String messageDialogParameter) {
        List<MessageDialog> returnList = new ArrayList<>();
        LOGGER.info("messageDialogParameter = " + messageDialogParameter);
        if (messageDialogParameter == null || messageDialogParameter.equals(Constants.VALUE_NULL)) {
            LOGGER.info("messageDialogParameter == null || messageDialogParameter.equals(Constants.VALUE_NULL)");
            return null;
        }
        if (!messageDialogParameter.isEmpty()) {
            // If it is not wrong
            // Remove the leading and trailing "[" and "]" characters.
            String messageDialogParam = messageDialogParameter.substring(1, messageDialogParameter.length() - 1);
            // When the "," character is parsed as gore, it will have the form
            // tag-searchtext-tag-searchtext, ... in that order.
            String[] propertySet = messageDialogParam.split(",");
            for (int i = 0; i < propertySet.length; i += 2) {
                // 2 skips the index and inserts the selection and subsequent
                // text value into the MessageDialog object.
                MessageDialog messageDialog;
                switch (propertySet[i].substring(1).toLowerCase()) {
                    case "tamam":
                    case "true":
                        messageDialog = new MessageDialog(propertySet[i + 1].substring(0, propertySet[i + 1].length() - 1), true);
                        break;
                    case "vazgec":
                    case "false":
                        messageDialog = new MessageDialog(propertySet[i + 1].substring(0, propertySet[i + 1].length() - 1), false);
                        break;
                    default:
                        messageDialog = null;
                }
                returnList.add(messageDialog);
            }
        }
        return returnList;
    }

    /**
     * This method separates the itemList value given in the parameter into units and returns a list of the parts equal to the index value in each unit. <br>
     * When index numbers are separated from within units, '~' is taken as basis. If the value of '~' is not found, the index value of that unit is '0'.<br>
     * Example: resourceList: "Barcode | Customer / Supplier Code ~ 2 | Producer Code ~ 3" returns: [0,2,3]
     *
     * @param itemList
     * @return
     * @author Tarik.Mikyas
     */
    public static List<Integer> collectResourceItemList(String itemList) {
        List<Integer> returnList = new ArrayList<>();
        // itemList "|" the character is divided into foci.
        List<String> itemDefs = dismantleResourceList(itemList);

        for (int i = 0; i < itemDefs.size(); i++) {
            if (itemDefs.get(i).contains("~")) {
                // It is taken from the after tilde.
                returnList.add(Integer.parseInt(itemDefs.get(i).substring(itemDefs.get(i).indexOf('~') + 1)));
            } else if (itemDefs.get(i).length() != 0 && !itemDefs.get(i).contains("~")) {
                // If there is no tilde, resourceTag is 0.
                returnList.add(0);
            }
        }

        return returnList;
    }

    /**
     * This method parses the resourceList value given in the parameter into units and returns it as a list. <br>
     * When parsing into units '|' is based on character.
     *
     * @param itemList
     * @return List
     * @author Tarik.Mikyas
     */
    public static List<String> dismantleResourceList(String resourceList) {
        return Arrays.asList(resourceList.split("\\|"));
    }

    /**
     * This method separates the itemList value given in the parameter into units and takes the parts corresponding to the index value in each unit and returns them as a list.<br>
     * When index numbers are separated from within units, '~' is taken as basis. If the value of '~' is not found, the index value of that unit is '0'.<br>
     * Example: resourceList: "Barcode | Customer / Supplier Code ~ 2 | Producer Code ~ 3" returns: [Barcode, Customer / Supplier Code, Producer Code]
     *
     * @param itemList
     * @return
     */
    public static List<String> collectResourceItemNameList(String itemList) {
        List<String> returnList = new ArrayList<>();
        // itemList "|" the character is divided into foci.
        List<String> itemDefs = dismantleResourceList(itemList);
        for (int i = 0; i < itemDefs.size(); i++) {
            if (itemDefs.get(i).contains("~")) {
                // If there is a tilde, the text before tilde is taken.
                returnList.add(itemDefs.get(i).substring(0, itemDefs.get(i).indexOf('~')));
            } else if (itemDefs.get(i).length() != 0 && !itemDefs.get(i).contains("~")) {
                // If there is no tilde, all text is taken.
                returnList.add(itemDefs.get(i));
            }
        }
        return returnList;
    }

    /**
     * Parse the itemList given in the parameter and return it as List<Resource>. Resource objects contain tag and text values.
     *
     * @param itemList
     * @return
     * @usedIn TedamFaceDataProvider
     * @author Ozgur.Ozbil
     */
    public static List<Resource> collectTotalResourceList(String itemList) {
        List<Resource> totalList = new ArrayList<>();
        // Tag list is sorted.
        List<Integer> tagList = collectResourceItemList(itemList);
        // The text list is sorted.
        List<String> descList = collectResourceItemNameList(itemList);
        if (tagList.isEmpty() || descList.isEmpty()) {
            return totalList;
        }
        for (int i = 0; i < tagList.size(); i++) {
            if (!"-".equals(descList.get(i))) {
                // Items with "-" as text value are not included.
                Resource resource = new Resource();
                resource.setDescription(descList.get(i));
                resource.setTag(tagList.get(i));
                totalList.add(resource);
            }
        }
        return totalList;
    }

    /**
     * @param reportDir
     * @param packageName
     * @param pTestCases
     * @param timeout
     * @param execRate
     * @param baseBshFileName
     * @return
     * @throws FileNotFoundException
     */
    public static String createBSHPackageContentForTedam(String reportDir, String packageName, String pTestCases, String baseBshFileName) throws FileNotFoundException {
        String result = "<TestPlayer>" + "\n" + "<MainDir Value=\"\"/>" + "\n" + "<ReportFile Directory=\"" + reportDir + "\" Name=\"" + packageName + "\" Type=\"1\"/>" + "\n"
                + "<ReportedStatus Completed=\"true\" CriticalError=\"true\" Error=\"true\" Repeated=\"false\"/>" + "\n" + "<TimeOut Enabled=\"true\" Value=\""
                + Constants.JGUAR_TIMEOUT + "\"/>" + "\n" + "<ExecRate Value=\"" + Constants.JGUAR_EXEC_RATE + "\"/>" + "\n" + "<TestPackageList>" + "\n"
                + "<TestPackage Desc=\"BSH Runner tarafından yaratılan paket\" Name=\"BSHRunner\" StopIfFailed=\"false\">" + "\n" + "<TestInstanceList>" + "\n";

        String mNameStr = "Test1";
        File filePath = new File(baseBshFileName);

        if (!filePath.exists()) {
            throw new FileNotFoundException("Unable to find a test script at : " + baseBshFileName);
        }
        result += "<TestInstance Desc=\"" + "http:\\\\tys.logo.com.tr/SpiraTest/TestCase/" + pTestCases + ".aspx" + "\" " + "Name=\"" + mNameStr + "\" Path=\"" + baseBshFileName
                + "\" SelectedDataPoolIndexes=\"1~1\" StopIfFailed=\"true\"/>" + "\n";

        result += "</TestInstanceList>" + "\n" + "</TestPackage>" + "\n" + "</TestPackageList>" + "\n" + "</TestPlayer>";

        return result;
    }

    /**
     * Used to find the parameters of the TestStep.
     *
     * @param testSteps
     * @return
     * @throws JobCommandBuildException
     */
    public static String findTestParamaters(List<TestStep> testSteps) throws JobCommandBuildException {
        String parameter = Constants.EMPTY_STRING;
        if (testSteps.isEmpty()) {
            throw new JobCommandBuildException("testSteps is empty.");
        }
        for (TestStep testStep : testSteps) {
            if (testStep.getExpectedFormname() == null || testStep.getExpectedFormname().isEmpty()) {
                if (testStep.getType() == null) {
                    LOGGER.error("TestCaseId : " + testStep.getTestCaseId() + " testStepId :" + testStep.getId() + " parameter is empty.");
                    throw new JobCommandBuildException("TestCaseId : " + testStep.getTestCaseId() + " testStepId :" + testStep.getId() + " parameter is empty.");
                }
                parameter += testStep.getType().getValue() + Regex.EQUALS.getRegex() + testStep.getParameter() + Regex.TEST_STEP.getRegex() + testStep.getId() + Constants.BLANK;
            } else {
                parameter += testStep.getType().getValue() + Regex.EQUALS.getRegex() + testStep.getParameter() + Regex.TEST_STEP.getRegex() + testStep.getId()
                        + Regex.FORM_NAME.getRegex() + testStep.getExpectedFormname() + Constants.BLANK;
            }
        }
        return parameter;
    }

    /**
     * Parses the formName given as parameter into name and mode components. <br>
     * The mode value is default (0) if "%" does not host the character.
     *
     * @param formName
     * @return
     */
    public static Map<String, String> decomposeFormName(String formName) {
        Map<String, String> nameAndMode = new HashMap<>();
        String name = "";
        String mode = "";
        if (formName != null) {
            if (formName.contains(Constants.TEXT_PERCENTSIGN)) {
                // If formName contains mode information
                name = formName.split(Constants.TEXT_PERCENTSIGN)[0];
                mode = formName.split(Constants.TEXT_PERCENTSIGN)[1];
            } else {
                // If formName does not contain mode information, it is 0.
                name = formName;
                mode = Constants.VALUE_ZERO;
            }
        }
        nameAndMode.put(Constants.VALUE_NAME, name);
        nameAndMode.put(Constants.VALUE_MODE, mode);
        return nameAndMode;
    }

    /**
     * This method compares control and datagrid elements of given xml document with the snapshotValues of snapshotDefinition with given snapshotDefinitionId. <br>
     * Returns test step object of resulting report for verify. <br>
     *
     * @param verifyFieldMap
     * @param fillerFieldsList
     * @param formName
     * @param isLookup
     * @param isIgnoreRowIndex Will the rowIndex be ignored or ignored?
     * @return
     * @usedIn: Verify.bsh
     * @author Tarik.Mikyas
     */
    public static TestReport validateSavedData(Map<String, String> verifyFieldMap, List<SnapshotValue> fillerFieldsList, String formName, boolean isLookup,
                                               boolean isIgnoreRowIndex) {

        TestReport bshTestReportResult = new TestReport(Constants.OPERATION_VERIFY_BSH, formName, isLookup);
        ScriptService ss = new ScriptService();
        StatusMessages status = StatusMessages.SUCCEEDED;
        ss.log("TedamReportUtils.validateSavedData", "verifyFieldMap :" + verifyFieldMap, TedamLogLevel.INFO, Boolean.TRUE);

        for (SnapshotValue snapshotValue : fillerFieldsList) {
            // For Control values
            if (snapshotValue.getRowIndex() == -1) {
                String tag = snapshotValue.getTag().trim();
                String value = snapshotValue.getValue().trim();
                // When loop encountered a incompability between values
                if (!value.equals(verifyFieldMap.get(tag))) {
                    bshTestReportResult.addMessage(tag + " tagged data (" + value + "), entered data (" + verifyFieldMap.get(tag) + ") It does not match.");
                    status = getStatus(status, snapshotValue.getContinueOnError());
                }
                // For DataGrid values
            } else if (snapshotValue.getRowIndex() != -1 && snapshotValue.getRowIndex() != -3) {
                String tag = snapshotValue.getTag().trim();
                String value = snapshotValue.getValue().trim();
                int rowID = snapshotValue.getRowIndex();
                String mapKey = tag + "," + Integer.toString(rowID);
                // If isIgnoreRowIndex is true, it navigates over all the data and continues
                // with the same data. If the rowIndex is checked independently but does not
                //  find the same datagram, it appends the error
                //  to the report.
                if (isIgnoreRowIndex && !isValueExistInVerifyFieldMap(tag, value, verifyFieldMap)) { // if rowIndex
                    // is to be
                    // checked
                    // independently
                    bshTestReportResult.addMessage(rowID + " indexed line, " + tag + " tagged data (" + value + "), entered data validation map" + tag
                            + " 'does not match any area.");
                    status = getStatus(status, snapshotValue.getContinueOnError());

                } else if (!isIgnoreRowIndex && !value.equals(verifyFieldMap.get(mapKey) == null ? null : verifyFieldMap.get(mapKey).trim())) {// When
                    // loop
                    // encountered
                    // a
                    // incompability
                    // between
                    // values
                    bshTestReportResult.addMessage(
                            rowID + " indexed line, " + tag + " tagged data (" + value + "), entered data(" + verifyFieldMap.get(mapKey) + ") It does not match.");
                    status = getStatus(status, snapshotValue.getContinueOnError());
                }
            }
        }

        if (status.equals(StatusMessages.SUCCEEDED)) {
            bshTestReportResult.setStatusMsg(StatusMessages.SUCCEEDED.getStatus());
        } else if (status.equals(StatusMessages.CAUTION)) {
            bshTestReportResult.setStatusMsg(StatusMessages.CAUTION.getStatus());
        } else {
            bshTestReportResult.setStatusMsg(StatusMessages.FAILED.getStatus());
        }

        return bshTestReportResult;
    }

    /**
     * this method returns true if the snapShotValue given in the controlIT attribute is in a verifyFieldMap of the appropriate data, false otherwise.<br>
     *
     * @param tag
     * @param value
     * @param rowID
     * @param verifyFieldMap <br>
     * @author Tarik.Mikyas
     */
    private static boolean isValueExistInVerifyFieldMap(String tag, String value, Map<String, String> verifyFieldMap) {
        for (Map.Entry<String, String> entry : verifyFieldMap.entrySet()) {
            String[] mapTagAndRow = entry.getKey().split(","); // The map key is held as [1000,2] [1000,3]. We need
            // this String in tag.
            String mapTag = mapTagAndRow[0]; //we handle the tag part of the string in the incoming map key
            if (mapTag.equals(tag) && value.equals(entry.getValue())) { // if the tag part we obtained from the map key
                // is the same as the snapShotValue tag, then
                // it will return true if the values match.
                return true;
            }
        }
        return false; // Checks only the tags without looking at rowID and returns false
        // if no matching value is found.
    }

    /**
     * If the incoming status parameter isContinue is true then @ statusMessages.caution, is false then @ statusMessages.failed.
     *
     * @param status
     * @param isContinue
     * @return
     * @author Tarik.Mikyas
     */
    public static StatusMessages getStatus(StatusMessages status, Boolean isContinue) {
        StatusMessages sm;
        if (!status.equals(StatusMessages.FAILED) && isContinue) {
            sm = StatusMessages.CAUTION;
        } else {
            sm = StatusMessages.FAILED;
        }
        return sm;
    }

    /**
     * it finds the testsetid and testcaseid from the incoming args.
     * <p>
     * this method findTestSetTestCaseParameters <br>
     *
     * @param args
     * @return <br>
     * @author Canberk.Erkmen
     */
    public static Map<String, String> findTestSetTestCaseParameters(String[] args) {

        Map<String, String> testSetTestCaseParameters = new HashMap<>();
        String pathString = Constants.EMPTY_STRING;
        for (String parameter : args) {
            if (parameter.startsWith("resultReportAddress")) {
                pathString = parameter;
                break;
            }
        }
        String testSetId = pathString.substring(pathString.indexOf(Constants.TEDAM_TEST_SET) + Constants.TEDAM_TEST_SET.length(),
                pathString.indexOf(Constants.TEDAM_TEST_CASE) - Constants.TEXT_UNDERSCORE.length());
        String testCaseId = pathString.substring(pathString.indexOf(Constants.TEDAM_TEST_CASE) + Constants.TEDAM_TEST_CASE.length());
        testSetTestCaseParameters.put(Constants.TEST_SET_ID, testSetId);
        testSetTestCaseParameters.put(Constants.TEST_CASE_ID, testCaseId);
        return testSetTestCaseParameters;
    }

    /**
     * this method calculateTotalTestResult reads the values from the resultet in the project id and returns the result.
     *
     * @param resultSet
     * @return <br>
     * @author Canberk.Erkmen
     */
    public static ExecutionStatus calculateTotalTestCaseResult(List<LogoTestResult> resultSet) {
        ExecutionStatus testResult = null;
        for (LogoTestResult currentTestResult : resultSet) {
            if (currentTestResult.getResult() == ExecutionStatus.FAILED) {
                testResult = ExecutionStatus.FAILED;
                break;
            } else if (currentTestResult.getResult() == ExecutionStatus.CAUTION) {
                testResult = ExecutionStatus.CAUTION;
            } else if (currentTestResult.getResult() == ExecutionStatus.BLOCKED) {
                testResult = ExecutionStatus.BLOCKED;
                break;
            }
        }
        if (testResult == null) {
            testResult = ExecutionStatus.SUCCEEDED;
        }
        return testResult;
    }

    /**
     * this method buildTestCaseResultDescription contains steps with error in testResult and is returned.<br>
     *
     * @param result
     * @param results
     * @return <br>
     * @author Canberk.Erkmen
     */
    public static String buildTestCaseResultDescription(ExecutionStatus result, List<LogoTestResult> results) {
        StringBuilder sb = new StringBuilder();
        if (!result.equals(ExecutionStatus.SUCCEEDED) && !result.equals(ExecutionStatus.NOTRUN) && !result.equals(ExecutionStatus.BLOCKED)) {
            LOGGER.info("result is either failed or cautioned.");
            for (int i = 0; i < results.size(); i++) {
                sb.append("TS" + results.get(i).getId() + " StepName = " + results.get(i).getName() + " Description = " + results.get(i).getDescription() + Constants.BLANK);
            }
        }
        return sb.toString().trim();
    }

    /**
     * this method getListAsString is written to the given Integer list as a String expression with a comma between them.<br>
     *
     * @param list
     * @return <br>
     * @author Canberk.Erkmen
     */
    public static String getListAsStringWithSeparator(List<String> list, String separator) {
        StringBuilder parameters = new StringBuilder();
        for (String element : list) {
            parameters.append(element);
            if (!list.get(list.size() - 1).equals(element)) {
                parameters.append(separator);
            }
        }
        return parameters.toString();
    }

    /**
     * this method getTestResultReportPath <br>
     *
     * @return <br>
     * @author Tarik.Mikyas
     */
    public static String getTestResultReportPath(int testSetId, int testCaseId, boolean isCreateScriptCommand) {
        String reportHeader = isCreateScriptCommand == true ? Constants.REPORT_HEADER_SCRIPT : Constants.REPORT_HEADER_RUN;
        String testResultReportPath = PropUtils.getProperty(Constants.TEMP_FILE_PATH) + Constants.FILE_SEPARATOR + reportHeader + testSetId + Constants.REPORT_TEST_CASE
                + testCaseId + Constants.FILE_EXTENSION_XLS;
        return testResultReportPath;
    }

    /**
     * this method splitStringContentByParameter <br>
     *
     * @param line
     * @param splitParameter
     * @return <br>
     * @author Canberk.Erkmen
     */
    public static List<String> splitStringContentByParameter(String line, String splitParameter) {
        List<String> stringList = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(line, (splitParameter == Constants.EMPTY_STRING) ? Constants.BLANK : splitParameter);
        while (stringTokenizer.hasMoreElements()) {
            stringList.add(stringTokenizer.nextElement().toString());
        }
        return stringList;

    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

}
