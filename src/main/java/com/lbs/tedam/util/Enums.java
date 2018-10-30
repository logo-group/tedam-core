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

/**
 * @author Tarik.Mikyas The common class of all enumerations in the project.
 */
public class Enums {

    public Enums() {
        super();
    }

    /**
     * @author Tarik.Mikyas Example Enum.
     * <p>
     * this enum was created in terms of example. It is not used anywhere.
     */
    public enum Number implements IEnums {
        // VALUE-NAME-ID // EXPLANATION
        ZERO("ZERO", 0), // 0=ZERO
        ONE("ONE", 1), // 1=ONE
        TWO("TWO", 2), // 2=TWO
        THREE("THREE", 3), // 3=THREE
        FOUR("FOUR", 4); // 4=FOUR

        private final String name;
        private final Integer code;

        Number(String name, Integer code) {
            this.name = name;
            this.code = code;
        }

        public static Number fromCode(Integer code) {
            if (code != null) {
                for (Number numbers : values()) {
                    if (numbers.getCode().equals(code)) {
                        return numbers;
                    }
                }
            }
            return getDefault();
        }

        /**
         * @return <br>
         * this method getDefault
         * @author Tarik.Mikyas
         */
        public static Number getDefault() {
            return null;
        }

        @Override
        public Integer getCode() {
            return code;
        }

        /**
         * @return <br>
         * this method getName
         * @author Tarik.Mikyas
         */
        public String getName() {
            return name;
        }

        /**
         * this method getIntCode <br>
         *
         * @return <br>
         * @author Tarik.Mikyas
         */
        public int getIntCode() {
            return code.intValue();
        }
    }

    /**
     * @author Tarik.Mikyas<br>
     * <p>
     * log message levels
     */
    public enum TedamLogLevel implements IEnums {
        /**
         * TedamLogLevel ALL
         */
        ALL("ALL", 0),
        /**
         * TedamLogLevel TRACE
         */
        TRACE("TRACE", 1),
        /**
         * TedamLogLevel TRACE_INT
         */
        TRACE_INT("TRACE_INT", 2),
        /**
         * TedamLogLevel DEBUG
         */
        DEBUG("DEBUG", 3),
        /**
         * TedamLogLevel INFO
         */
        INFO("INFO", 4),
        /**
         * TedamLogLevel WARN
         */
        WARN("WARN", 5),
        /**
         * TedamLogLevel ERROR
         */
        ERROR("ERROR", 6),
        /**
         * TedamLogLevel FATAL
         */
        FATAL("FATAL", 7),
        /**
         * TedamLogLevel OFF
         */
        OFF("OFF", 8);

        private String levelName;
        private Integer levelCode;

        /**
         * @param levelName
         * @param levelCode
         */
        TedamLogLevel(String levelName, Integer levelCode) {
            this.levelName = levelName;
            this.levelCode = levelCode;
        }

        /**
         * @return <br>
         * this method getDefault TedamLogLevel.ALL
         * @author Tarik.Mikyas
         */
        public static TedamLogLevel getDefault() {
            return ALL;
        }

        /**
         * @return <br>
         * this method getName
         * @author Tarik.Mikyas
         */
        public String getName() {
            return levelName;
        }

        @Override
        public Integer getCode() {
            return levelCode;
        }

    }

    /**
     * @author Tarik.Mikyas<br>
     * SnapshotControls
     */
    public enum SnapshotControls {
        /**
         * 2 values are kept.
         */
        TABBED_PANE("2"),
        /**
         * 3 values are kept.
         */
        TAB("3"),
        /**
         * 151 values are kept.
         */
        CHECKBOXGROUP("151");

        private String type;

        /**
         * @param type
         */
        private SnapshotControls(String type) {
            this.type = type;
        }

        /**
         * @return <br>
         * this method getType
         * @author Tarik.Mikyas
         */
        public String getType() {
            return type;
        }
    }

    /**
     * @author Tarik.Mikyas<br>
     * FileNames
     */
    public enum FileName {
        BASE("Base"), //
        CONTROL_FILE("controlFile.txt"), //
        FINISH_FILE("finishFile.txt"), //
        TEDAM_JAR_2_0("TEDAM-2.0.jar"), //
        TEDAM_JAR_2_1("TEDAM-2.1.jar"), //
        CONFIG_PROPERTIES("config.properties"),
        TEDAM_AGENT("TedamAgent"),
        AGENT_LOG("TedamAgentLog"),
        TEDAM_LOG("TEDAM_LOG (" + DateTimeUtils.getTEDAMFormatSystemDate() + ").log"),
        CLIENT_SINGLE_COMMAND("clientSingleCommand.log");

        private String name;

        private FileName(String name) {
            this.name = name;
        }

        /**
         * @return <br>
         * this method getFileName
         * @author Tarik.Mikyas
         */
        public String getName() {
            return this.name;
        }
    }

    /**
     * @author Tarik.Mikyas<br>
     */
    public enum FilePath {
        /**
         * FilePath MODULAR_SCRIPTS modulerScript path in the ty directory
         */
        BSH_MODULAR_SCRIPTS("Bsh_Modular_Scripts", "Bsh_Modular_Scripts"), //
        SAH_MODULAR_SCRIPTS("Sah_Modular_Scripts", "Sah_Modular_Scripts"), //
        SAHI_BIN("Sahi_bin", "userdata" + Constants.FILE_SEPARATOR + "bin"), //
        JGUAR_LIB("jguarLib", "lib" + Constants.FILE_SEPARATOR + "JguarLib"), //
        LIB_TEDAM_WEB("libTedamWeb", "lib" + Constants.FILE_SEPARATOR + "TedamWeb"), //
        LIB_TEDAM_JGUAR("libTedamJguar", "lib" + Constants.FILE_SEPARATOR + "TedamJguar"), //
        REPORTS("Reports", "Reports");

        /**
         * String fileName
         */
        private String name;
        /**
         * String filePath
         */
        private String path;

        /**
         * @param fileName
         * @param filePath
         */
        private FilePath(String name, String path) {
            this.name = name;
            this.path = path;
        }

        /**
         * this method getFileName <br>
         *
         * @return <br>
         * @author Tarik.Mikyas
         */
        public String getName() {
            return name;
        }

        /**
         * this method getFilePath <br>
         *
         * @return <br>
         * @author Tarik.Mikyas
         */
        public String getPath() {
            return path;
        }

    }

    public enum JguarLib {
        LOGO_CONTRIB_JAR("logo-contrib.jar"), //
        LBS_STARTUP_JAR("LbsStartup.jar"), //
        JACOCO_AGENT_JAR("jacocoagent.jar"), //
        SIMPLE_PERSIAN_CALENDAR("simple_persian_calendar-1.0.jar");

        /**
         * String value
         */
        private String value;

        /**
         * @param value
         */
        private JguarLib(String value) {
            this.value = value;
        }

        /**
         * this method getJguarLibs list the value values of the enum as a list <br>
         *
         * @return <br>
         * @author Tarik.Mikyas
         */
        public static List<String> getJguarLibs() {
            List<String> bshTestLibraries = new ArrayList<>();
            for (JguarLib jguarLib : JguarLib.values()) {
                if (jguarLib == JACOCO_AGENT_JAR) {
                    continue;
                }
                bshTestLibraries.add(jguarLib.getValue());
            }
            return bshTestLibraries;
        }

        /**
         * this method getValue <br>
         *
         * @return <br>
         * @author Tarik.Mikyas
         */
        public String getValue() {
            return value;
        }

    }

    /**
     * @author Tarik.Mikyas<br>
     * OperationTypes
     */
    public enum OperationTypes {
        FORM_OPEN("FormOpen"), //
        FORM_OPEN_SHORTCUT("FormOpenByShortcut"), //
        FORM_FILL("FormFill"), //
        FILTER_FILL("FilterFill"), //
        BUTTON_CLICK("ButtonClick"), //
        // TODO:canberk
        VERIFY("Verify"), //
        ROW_COUNT_VERIFY("RowCountVerify"), //
        MESSAGE_VERIFY("MessageVerify"), //
        POP_UP("PopUp"), //
        GRID_SEARCH("GridSearch"), //
        GRID_CELL_SELECT("GridCellSelect"), //
        GRID_ROW_SELECT("GridRowSelect"), //
        GRID_DELETE("Delete"), //
        DOUBLE_CLICK("DoubleClick"), //
        REPORT("Report"), //
        SCRIPT("Script"), //
        MESSAGE_DIALOG("Dialog"), //
        RESULT_REPORT_ADDRESS("resultReportAddress"), //
        VERSION("version"), //
        TEST_CASE_ID("testCaseId"),
        TIME_RECORDING("timeRecording"),
        CONFIG_FILE_PATH("configFilePath");

        private String type;

        OperationTypes(String type) {
            this.type = type;
        }

        /**
         * this method fromName <br>
         *
         * @param name
         * @return <br>
         * @author Canberk.Erkmen
         */
        public static OperationTypes fromName(String name) {
            if (name != null) {
                for (OperationTypes pperationType : values()) {
                    if (pperationType.getType().equalsIgnoreCase(name)) {
                        return pperationType;
                    }
                }
            }
            return getDefault();
        }

        /**
         * this method getDefault <br>
         *
         * @return <br>
         * @author Canberk.Erkmen
         */
        public static OperationTypes getDefault() {
            return null;
        }

        public String getType() {
            return type;
        }
    }

    /**
     * @author Canberk.Erkmen
     */
    public enum StepTypeSahi {
        /**
         * StepTypeSahi FORM_FILL
         */
        FORM_FILL("FormFill", 0), //
        /**
         * StepTypeSahi BUTTON_CLICK
         */
        BUTTON_CLICK("ButtonClick", 1), //
        /**
         * StepTypeSahi VERIFY
         */
        VERIFY("Verify", 2); //

        /**
         * String type
         */
        private String type;
        /**
         * Integer code
         */
        private Integer code;

        /**
         * @param type
         * @param code
         */
        private StepTypeSahi(String type, Integer code) {
            this.type = type;
            this.code = code;
        }

        /**
         * this method getDefault <br>
         *
         * @return <br>
         * @author Canberk.Erkmen
         */
        public static StepTypeSahi getDefault() {
            return null;
        }

        /**
         * this method fromName <br>
         *
         * @param name
         * @return <br>
         * @author Canberk.Erkmen
         */
        public static StepTypeSahi fromName(String name) {
            if (name != null) {
                for (StepTypeSahi stepTypeSahi : values()) {
                    if (stepTypeSahi.getType().equalsIgnoreCase(name)) {
                        return stepTypeSahi;
                    }
                }
            }
            return getDefault();
        }

        /**
         * this method getType <br>
         *
         * @return <br>
         * @author Canberk.Erkmen
         */
        public String getType() {
            return type;
        }

        /**
         * this method getCode <br>
         *
         * @return <br>
         * @author Canberk.Erkmen
         */
        public Integer getCode() {
            return code;
        }

    }

    /**
     * @author Canberk.Erkmen
     */
    public enum ScriptParameterSahi {
        UPLOADED_SNAPSHOT_ID("uploadedSnapshotID", 0), //
        BUTTON_PARAMETER("buttonParameter", 1), //
        BUTTON_TYPE("buttonType", 2),
        VERSION("version", 3), //
        TEST_CASE_ID("testCaseId", 4),
        TEST_SET_ID("testSetId", 5),
        FORM_NAME("formName", 6),
        TIME_RECORDING("timeRecording", 7),
        URL("url", 8),
        BROWSER("browser", 9),
        WAIT("wait", 10);

        /**
         * String type
         */
        private String type;
        /**
         * Integer code
         */
        private Integer code;

        /**
         * @param type
         * @param code
         */
        private ScriptParameterSahi(String type, Integer code) {
            this.type = type;
            this.code = code;
        }

        /**
         * this method getDefault <br>
         *
         * @return <br>
         * @author Canberk.Erkmen
         */
        public static ScriptParameterSahi getDefault() {
            return null;
        }

        /**
         * this method fromName <br>
         *
         * @param name
         * @return <br>
         * @author Canberk.Erkmen
         */
        public static ScriptParameterSahi fromName(String name) {
            if (name != null) {
                for (ScriptParameterSahi scriptParameterSahi : values()) {
                    if (scriptParameterSahi.getType().equalsIgnoreCase(name)) {
                        return scriptParameterSahi;
                    }
                }
            }
            return getDefault();
        }

        /**
         * this method getType <br>
         *
         * @return <br>
         * @author Canberk.Erkmen
         */
        public String getType() {
            return type;
        }

        /**
         * this method getCode <br>
         *
         * @return <br>
         * @author Canberk.Erkmen
         */
        public Integer getCode() {
            return code;
        }

    }

    /**
     * @author Tarik.Mikyas<br>
     * FormOpenTypes Form open types
     */
    public enum FormOpenTypes {
        TREE_FORM_OPEN("TreeFormOpen"), //
        EXCHANGE_RATES_FORM_OPEN("ExchangeRatesFormOpen"), //
        SET_WORK_DATES_FORM_OPEN("SetWorkDatesFormOpen");

        private String type;

        FormOpenTypes(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    /**
     * @author Tarik.Mikyas<br>
     * VerifyTypes
     */
    public enum VerifyTypes {
        ROW_COUNT_VERIFY("RowCountVerify"), //
        FIELD_VALUE_VERIFY("FieldValueVerify"), //
        MESSAGE_VERIFY("MessageVerify");

        private String type;

        VerifyTypes(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    /**
     * @author Tarik.Mikyas<br>
     * ScriptParameters
     * <p>
     * Script parameters used in fragmenting the script that comes with Command Prop
     */
    public enum ScriptParameters {
        OPERATION_TYPE("OperationType"), //
        STEP_FORM_NAME("stepFormName"), //
        MENU_PATH("menuPath"), //
        FORM_OPEN_TYPE("formOpenType"), //
        UPLOADED_SNAPSHOT_ID("uploadedSnapshotID"), //
        IS_IGNORE_ROW_INDEX("isIgnoreRowIndex"), //
        BUTTON_TAG("buttonTag"), //
        MENU_BUTTON_RESOURCE_TAG("menuButtonResourceTag"), //
        VERIFY_TYPE("verifyType"), //
        VERIFY_PARAM("verifyParam"), //
        CONTINUE_ON_ERROR("continueOnError"), //
        CONTINUE_ON_ERROR_REPORT("continueOnErrorReport"), //
        REPORT_WAIT_SLEEP_MILLIS("reportWaitSleepMillis"),
        IS_WRITE_FILTERS("isWriteFilters"), //
        IS_REPORT_SNAPSHOT_OVERWRITE_ENABLED("isReportSnapshotOverwriteEnabled"), //
        IS_REPORT_FILTERS_SNAPSHOT_OVERWRITE_ENABLED("isReportFiltersSnapshotOverwriteEnabled"), //
        POP_UP_TAG("popUpTag"), //
        GRID_TAG("gridTag"), //
        ROW_INDEX("rowIndex"), //
        ROW_INDEX_LIST("rowIndexList"), //
        COLUMN_TAG("columnTag"), //
        SEARCH_DETAILS("searchDetails"), //
        MESSAGE_DIALOG_DETAILS("messageDialogDetails"), //
        RESULT_REPORT_ADDRESS("resultReportAddress"), //
        VERSION("version"), //
        MESSAGE_VERIFY_PARAMETER("messageVerifyParameter"), //
        ROW_COUNT("rowCount"), //
        PATH("path"), //
        CONFIG_FILE_PATH("configFilePath"), //
        TEST_CASE_ID("testCaseId"),
        TEST_STEP_ID("testStepId"),
        TIME_RECORDING("timeRecording"),
        SCRIPT_FILE_NAME("scriptFileName"); //

        private String value;

        ScriptParameters(String value) {
            this.value = value;
        }

        /**
         * @return <br>
         * this method getType
         * @author Tarik.Mikyas
         */
        public String getType() {
            return value;
        }
    }

    /**
     * @author Tarik.Mikyas<br>
     * Regex
     */
    public enum Regex {
        SPACE("!spc!"), //
        ROW_COUNT_VERIFY("!rcv!"), //
        MESSAGE_VERIFY("!mv!"), //
        FIELD_VALUE_VERIFY("!fvv!"), //
        FORM_NAME("!fn!"), //
        PARAMETER_SPLITTER("!ps!"), //
        INNER_PARAMETER_SPLITTER("!ips!"), //
        EXCHANGE_RATES("!er!"), //
        EQUALS("!equals!"),
        TEST_STEP("!ts!"), //
        SET_WORK_DATES("!swd!"),
        COMMA("!cmm!"); //

        private String regexCode;

        Regex(String regex) {
            this.regexCode = regex;
        }

        /**
         * @return <br>
         * this method getRegex
         * @author Tarik.Mikyas
         */
        public String getRegex() {
            return regexCode;
        }
    }

    /**
     * @author Tarik.Mikyas<br>
     * StatusMessages
     */
    public enum StatusMessages {

        FAILED("FAILED", 0), //
        SUCCEEDED("SUCCEEDED", 1), //
        CAUTION("CAUTION", 2), //
        SUCCEEDED_WITH_ERRORS("SUCCEEDED WITH ERRORS", 2); //

        private String status;
        private int statusId;

        StatusMessages(String status, int statusId) {
            this.status = status;
            this.statusId = statusId;
        }

        /**
         * @return <br>
         * this method getStatus
         * @author Tarik.Mikyas
         */
        public String getStatus() {
            return status;
        }

        /**
         * @return <br>
         * this method getStatusId
         * @author Tarik.Mikyas
         */
        public int getStatusId() {
            return statusId;
        }
    }

    /**
     * @author Tarik.Mikyas<br>
     * PathType
     */
    public enum PathType {
        TOTAL, // Used for MenuPathCollector.
        DEFINITION_AND_TRANSACTION, // Used for SnapshotCollector.
        BATCH, //
        REPORT; //
    }

    /**
     * When you return an object value for each enum value, you get an enum value from the Integer value given by a static fromValue() routine.
     *
     * @author Tarik.Mikyas
     * @since 11 January 2016 14:36:30
     */
    public interface IEnums {
        // you return an enum value for each enum value from
        // a static value from the fromValue () routine.
        // Be Generic
        Object getCode();
    }

}
