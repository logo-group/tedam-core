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

import java.util.Arrays;
import java.util.List;

/**
 * @author ozgur.ozbil
 * @author Tarik.Mikyas
 */
public final class Constants {
    // Form XML analysis

    public static final String VERSION = "9.99";
    public static final int SECONDSPERMINUTES = 60;
    public static final int ONE_SECOND = 1000;
    public static final int WAIT_COEFFICIENT = 1;
    public static final boolean isWindows = System.getProperty("os.name").toLowerCase().contains("windows");
    public static final String CTRL_PROPERTY = "Property";
    public static final String CTRL_PROPERTY_ATTRIBUTE = "Attribute";
    public static final String CTRL_TYPE_IMAGEBUTTON = "IMAGEBUTTON";
    public static final String CTRL_TYPE_MENUBUTTON = "MENUBUTTON";
    public static final String CTRL_TYPE_BUTTON = "BUTTON";
    public static final String CTRL_TYPE_POPUPMENU = "POPUPMENU";
    public static final String PROPERTY_PROPERTY_ATTRIBUTE_CUSTOM = "CUSTOM";
    public static final String PROPERTY_PROPERTY_ATTRIBUTE_FORMPOPUP = "FORMPOPUP";
    public static final String CTRL_ATTRIBUTE_FILTERLOAD = "FILTERLOAD";
    public static final String CTRL_ATTRIBUTE_FILTERAPPLY = "FILTERAPPLY";
    public static final String CTRL_ATTRIBUTE_CLOSE = "CLOSE";
    public static final String CTRL_ATTRIBUTE_SAVECLOSE = "SAVECLOSE";
    public static final String CTRL_ATTRIBUTE_NEW = "DBNEW";
    public static final String CTRL_ATTRIBUTE_UPDATE = "DBUPDATE";
    public static final String CTRL_ATTRIBUTE_DELETE = "DBDELETE";
    public static final String CTRL_ATTRIBUTE_DUPLICATE = "DBDUPLICATE";
    public static final String CTRL_ATTRIBUTE_VIEW = "DBVIEW";
    public static final String CTRL_ATTRIBUTE_SELECT = "DBSELECT";
    public static final String CTRL_TAG = "Tag";
    public static final String CTRL_TYPE = "Type";
    public static final String CTRL_XUIDOC = "XUIDoc";
    public static final String MODULE_NAME = "Name";
    public static final String MODULE_PARAMETER = "ModuleParameter";
    public static final String MODULE_PARAMETER_NAME = "Name";
    public static final String MODULE_PARAMETER_VALUE = "Value";
    public static final String OPERATION_FORM = "Form";
    public static final String OPERATION_CONFIG = "Config";
    // Config File analysis
    public static final String CONFIG_NODETYPE_DEFINITIONS = "Definitions";
    public static final String CONFIG_NODETYPE_TRANSACTIONS = "Transactions";
    public static final String CONFIG_NODE_ATTRIBUTES_CODE = "code";
    public static final String CONFIG_NODE_ATTRIBUTES_NAME = "name";
    public static final String CONFIG_NODE_ATTRIBUTES_ID = "id";
    public static final String CONFIG_NODE_ATTRIBUTES_CODEVALUE_C = "C";
    public static final String CONFIG_NODE_ATTRIBUTES_CODEVALUE_O = "O";
    public static final String CONFIG_NODE_ATTRIBUTES_NAMEVALUE_DEF = "Definitions";
    public static final String CONFIG_NODE_ATTRIBUTES_NAMEVALUE_TRANS = "Transactions";
    public static final String CONFIG_NODE_ATTRIBUTES_NAMEVALUE_TYPES = "Types";
    public static final String CONFIG_NODE_ATTRIBUTES_NAMEVALUE_DEFAULTSANDPARAMETERS = "Defaults and Parameters";
    // Snapshot analysis
    public static final String SNAPSHOT_GRID_COLUMNS = "Columns";
    public static final String SNAPSHOT_GRID_COLUMN = "Column";
    public static final String SNAPSHOT_GRID_ROWS = "Rows";
    public static final String SNAPSHOT_GRID_ROW = "Row";
    public static final String SNAPSHOT_GRID_COL = "col";
    public static final String SNAPSHOT_GRID_COLUMN_ATTRIBUTES_CAPTION = "caption";
    public static final String SNAPSHOT_CONTROL_ATTRIBUTES_ITEMLIST = "itemList";
    public static final String SNAPSHOT_CONTROL_ATTRIBUTES_SELECTEDLIST = "selectedList";
    public static final String SNAPSHOT_CONTROL_ATTRIBUTES_TAG = "tag";
    public static final String SNAPSHOT_CONTROL_ATTRIBUTES_TYPE = "type";
    public static final String SNAPSHOT_CONTROL_ATTRIBUTES_VALUE = "value";
    public static final String SNAPSHOT_CONTROL_ATTRIBUTES_VALUETAG = "value_tag";
    public static final String SNAPSHOT_CONTROL_ATTRIBUTES_TITLE = "title";
    public static final String SNAPSHOT_CONTROL_ATTRIBUTES_VISIBLE = "visible";
    public static final String SNAPSHOT_CONTROL_ATTRIBUTES_ENABLED = "enabled";
    public static final String SNAPSHOT_CONTROL_ATTRIBUTES_MANDATORY = "mandatory";
    public static final String SNAPSHOT_CONTROL_ATTRIBUTES_HAS_LOOKUP = "hasLookup";
    public static final String SNAPSHOT_CONTROL_ATTRIBUTES_MESSAGE = "message";
    public static final String SNAPSHOT_CONTROL_ATTRIBUTES_ATTRIBUTE = "attribute";
    public static final String SNAPSHOT_CONTROL_ATTRIBUTES_NAME = "name";
    public static final String SNAPSHOT_FILTER_FILTERS = "Filters";
    public static final String SNAPSHOT_FILTER_FILTER = "Filter";
    public static final String SNAPSHOT_FILTER_ATTRIBUTES_DESCRIPTION = "description";
    public static final String SNAPSHOT_FILTER_ATTRIBUTES_ID = "id";
    public static final String SNAPSHOT_FILTER_ATTRIBUTES_HIGHVALUE = "highValue";
    public static final String SNAPSHOT_FILTER_ATTRIBUTES_LOWVALUE = "lowValue";
    public static final String SNAPSHOT_FILTER_ATTRIBUTES_EXCLUDEDVALUE = "excludedValue";
    public static final String SNAPSHOT_FILTER_ATTRIBUTES_GROUPVALUE = "groupValue";
    public static final String SNAPSHOT_FILTER_ATTRIBUTES_VALUELIST = "valueList";
    public static final String SNAPSHOT_GRID = "GRID";
    public static final String SNAPSHOT_CONTROL = "CONTROL";
    public static final String SNAPSHOT_FILTER = "FILTER";
    public static final String SNAPSHOT_GRID_SPLITTER_INDEX = "splitterIndex";
    public static final String SNAPSHOT_GRID_ROW_STATE = "state";
    public static final String SNAPSHOT_FORM_CONTROL = "Control";
    public static final String SNAPSHOT_FORM_FORM = "Form";
    public static final String SNAPSHOT_FORM_NAME = "name";
    public static final String SNAPSHOT_FORM_TITLE = "title";
    public static final String SNAPSHOT_FORM_VERSION = "version";
    public static final String SNAPSHOT_FIELD_MESSAGES = "Messages";
    public static final String SNAPSHOT_FORM_UNXF = "UNXF";
    public static final String SNAPSHOT_FORM_UNXFSTART = "\t It starts with UNXF.";
    public static final String SNAPSHOT_FORM_KEYERROR = "\t DBNEW button not found.";
    public static final String SNAPSHOT_EDITORNOTOPENED = "\t Editor failed to start.";
    public static final String SNAPSHOT_NEW_CARD_NOT_FOUND = " New card key not found. ";
    public static final String SNAPSHOT_BROWSER_EDITOR_DBSAVE_ERROR = "\t Problems were encountered while browsers and their associated editors were being thrown into the DB.";
    public static final String SNAPSHOT_COLLECTOR_REPORT_PATH = "C:\\temp\\SnapshotScanner\\ErrorReports\\ReportSnapshotCollector\\ReportSnapshotCollector";
    public static final String SNAPSHOT_BATCH_REPORT_PATH = "C:\\temp\\SnapshotScanner\\ErrorReports\\ReportSnapshotCollector\\ReportSnapshotCollectorBatch";
    public static final String SNAPSHOT_NOT_OPENED = " Could not be opened..";
    public static final String SAVE_TO_DB_ERROR = "The problem was encountered while saving to the database.";
    public static final String SNASPSHOTXML_SOURCE_FORMDEFINITION = "fd";
    public static final String SNASPSHOTXML_SOURCE_SNAPSHOTDEFINITION = "sd";
    // DB operations
    public static final int VALUE_POSITIVE = 1;
    public static final int VALUE_NEGATIVE = -1;
    public static final String VALUE_EVET = "YES";
    public static final String VALUE_HAYIR = "NO";
    public static final String VALUE_VAR = "THERE IS";
    public static final String VALUE_YOK = "NO";
    public static final String VALUE_FORMSCANNER = "FormScanner";
    // Used in: FormFill.bsh
    public static final String VALUE_NULL = "<%NULLVALUE%>";
    // Used in: FormFill.bsh
    public static final int VALUE_NULL_INTEGER = -9999;
    public static final int VALUE_NULL_ORDER_INTEGER = -9999;
    public static final int VALUE_CONTROL_ROWID = -1;
    public static final int VALUE_FILTER_ROWID = -3;
    public static final String VALUE_ZERO = "0";
    public static final int ZERO = 0;
    public static final String VALUE_NULL_STRING = "-9999";
    public static final String VALUE_CREATED_BY_TEDAM = "TEDAM";
    // Property
    public static final String PROPERTY_SPLITTER = "SPLITTER";
    public static final String PROPERTY_CONFIG = "CONFIG";
    public static final String PROPERTY_VERSION = "version";
    public static final String PROPERTY_TEMP_FILE_PATH = "tempFilePath";
    public static final String PROPERTY_SNAPSHOT_FILE_FOLDER = "snapshotFileFolder";
    public static final String PROPERTY_MODULE_CONFIG_POOL_PATH = "moduleConfigPoolPath";
    public static final String PROPERTY_MODULE_CONFIG_ORDER_PATH = "moduleConfigOrderPath";
    public static final String PROPERTY_SNAPSHOTCOLLECTOR_ORDERFILE_PATH = "scorder";
    public static final String PROPERTY_JOBRUNNER_REST_URL = "jobrunnerRestUrl";
    // Component Types
    public static final String COMBO_CONTROLTYPE_TREEGRID = "7";
    public static final String COMBO_CONTROLTYPE_FILTERGRID = "11";
    public static final String COMBO_CONTROLTYPE_LABEL = "101";
    public static final List<String> unpermittedComponentList = Arrays.asList(Constants.COMBO_CONTROLTYPE_LABEL);
    public static final String COMBO_CONTROLTYPE_TEXTEDIT = "102";
    public static final String COMBO_CONTROLTYPE_DATEEDIT = "104";
    public static final String COMBO_CONTROLTYPE_COMBOBOX = "106";
    public static final String COMBO_CONTROLTYPE_CHECKBOX = "107";
    public static final String COMBO_CONTROLTYPE_RADIOBUTTON = "108";
    public static final String COMBO_CONTROLTYPE_BUTTON = "109";
    public static final String COMBO_CONTROLTYPE_IMAGEBUTTON = "111";
    public static final String COMBO_CONTROLTYPE_MENUBUTTON = "112";
    public static final String COMBO_CONTROLTYPE_TEXTAREA = "113";
    public static final String COMBO_CONTROLTYPE_NUMERICEDITWITHCALCULATOR = "117";
    public static final String COMBO_CONTROLTYPE_RADIOBUTTONGROUP = "152";
    public static final String COMBO_CONTROLTYPE_CHECKBOXGROUP = "151";
    public static final String COMBO_CONTROLTYPE_IMAGE = "153";
    public static final String COMBO_CONTROLTYPE_POPUPMENU = "154";
    public static final String COMBO_CONTROLTYPE_COMBOEDIT = "157";
    // Bsh generator commands
    public static final String COMMAND_VERSION = "version";
    public static final String COMMAND_RESULTFILEPATH = "path";
    public static final String PARAMETER_FILLANDCLICK_SNAPSHOTID = "snapshotid";
    public static final String PARAMETER_FILLANDCLICK_BUTTONTAG = "buttontag";
    public static final String PARAMETER_FILLANDCLICK_MENUBUTTONITEM = "menubuttonitem";
    public static final String SCRIPT_SYNTAX_FORMNAME = "formName = TPW.getCurrentContainerFormName()";
    public static final String SCRIPT_SYNTAX_CONTINUECHECKBEGIN = "if(passCond) {";
    public static final String SCRIPT_SYNTAX_BREAKCHECKBEGIN = "if(!passCond) {";
    public static final String SCRIPT_SYNTAX_CHECKISFAILEDBEFORE = "if(addToReport) {";
    public static final String SCRIPT_SYNTAX_SETADDTOREPORT = "addToReport = false";
    public static final String SCRIPT_SYNTAX_CURLYBRACEEND = "}";
    public static final String SCRIPT_SYNTAX_OPENBRACKET = "(";
    public static final String SCRIPT_SYNTAX_ENDBRACKET = ")";
    // Bsh Operation names
    public static final String OPERATION_FORMOPEN_BSH = "FormOpen";
    public static final String OPERATION_FORMFILL_BSH = "FormFill";
    public static final String OPERATION_FILTERFILL_BSH = "FilterFill";
    public static final String OPERATION_POPUP_BSH = "PopUp";
    public static final String OPERATION_GRIDSEARCH_BSH = "GridSearch";
    public static final String OPERATION_BUTTONCLICK_BSH = "ButtonClick";
    public static final String OPERATION_VERIFY_BSH = "Verify";
    public static final String OPERATION_ROWCOUNTVERIFY_BSH = "RowCountVerify";
    public static final String OPERATION_MESSAGEVERIFY_BSH = "MessageVerify";
    public static final String OPERATION_FIELDVALUEVERIFY_BSH = "FieldValueVerify";
    public static final String OPERATION_GRIDCELLSELECT_BSH = "GridCellSelect";
    public static final String OPERATION_GRIDROWSELECT_BSH = "GridRowSelect";
    public static final String OPERATION_ROWDELETE_BSH = "RowDelete";
    public static final String OPERATION_REPORT_BSH = "Report";
    public static final String OPERATION_MESSAGEDIALOG_BSH = "Dialog";
    public static final String OPERATION_STATECHANGE_BSH = "StateChange";
    public static final String OPERATION_DOUBLECLICK_BSH = "DoubleClick";
    // Sah Operation names
    public static final String OPERATION_FORMFILL_SAH = "FormFill";
    public static final String OPERATION_BUTTONCLICK_SAH = "ButtonClick";
    public static final String OPERATION_VERIFY_SAH = "Verify";
    public static final String OPERATION_FORM_OPEN_SAH = "FormOpen";
    public static final String SAHI_SCRIPT_SYNTAX_CONTINUECHECKBEGIN = "if($passCond) {";
    // VersionDiffereneceReport operation names
    public static final String VDR_OPERATION_FORMID = "formid";
    public static final String VDR_OPERATION_VERSION = "version";
    // Form_FieldsDAO operations
    public static final String DAO_QUERY_OPERATION_ISNEW = "isnew";
    public static final String DAO_QUERY_OPERATION_FINDVERSIONDIFF = "findVersionDiff";
    // SCNavigator constants
    public static final Integer NAVIGATION_ROOT_VALUE = -9999;
    // Other utility
    public static final String VALUE_FIELD_MENUBUTTONITEMNO = "MenuButtonItemNo";
    public static final String VALUE_FIELD_MENUPATH = "MenuPath";
    public static final String VALUE_FIELD_FORMAY = "FormAy";
    public static final String VALUE_TRUE = "true";
    public static final String VALUE_FALSE = "false";
    public static final String VALUE_UNKNOWN = "Unknown";
    public static final String VALUE_NAME = "name";
    public static final String VALUE_MODE = "mode";
    public static final String VALUE_TITLE = "title";
    public static final String VALUE_VERSION = "version";
    public static final String VALUE_POPUPTAG = "popupTag";
    public static final String VALUE_MENUBUTTONITEMNO = "menuButtonItemNo";
    public static final String VALUE_MENUBUTTONITEMNAME = "menuButtonItemName";
    public static final String VALUE_FORMAY = "formAy";
    public static final String VALUE_FORMAYINDEX = "formAyIndex";
    public static final String VALUE_FORMAYNO = "formAyNo";
    public static final String VALUE_FORMAYNAME = "formAyName";
    public static final String VALUE_PARENTID = "parentID";
    public static final String VALUE_MENUPATH = "menuPath";
    public static final String VALUE_MENUPATHTITLE = "menuPathTitle";
    public static final String VALUE_SNAPSHOT = "snapshot";
    public static final String VALUE_TABBEDPANE = "Tabbed Pane - Selected Index";
    public static final String VALUE_REGEX_EXCLUDE = "<$!>";
    public static final String VALUE_REGEX_GROUPPED = "<$gr>";
    public static final String VALUE_REGEX_LOWVALUE = "<$gt>";
    public static final String VALUE_REGEX_HIGHVALUE = "<$lt>";
    public static final String TEXT_PERCENTSIGN = "%";
    public static final String TEXT_AND = "&";
    public static final String TEXT_AND_SCRIPT = "^&";
    public static final String TEXT_UNDERSCORE = "_";
    public static final String TEXT_NEWLINE = "\n";
    public static final String TEXT_NEWLINE_SCRIPT = "\r\n";
    public static final String TEXT_DOUBLE_NEWLINE = "\n\n";
    public static final String TEXT_HTML_NEWLINE = "<br>";
    public static final String TEXT_TAB = "\t";
    public static final String DOUBLE_TEXT_TAB = "\t\t";
    public static final String TEXT_DUOTAB = TEXT_TAB + TEXT_TAB;
    public static final String TEXT_TRIPLETAB = TEXT_DUOTAB + TEXT_TAB;
    public static final String TEXT_COMMANEWLINE = ";\n";
    public static final String TEXT_SEMICOLON = ";";
    public static final String TEXT_COMMA = ",";
    public static final String TEXT_COMMA_WITH_SPACE = ", ";
    public static final String TEXT_DASH = " - ";
    public static final String TEXT_COLON = ":";
    public static final String TEXT_BLANK = " ";
    public static final String FILE_EXTENSION_BSH = ".bsh";
    public static final String FILE_EXTENSION_SAH = ".sah";
    public static final String FILE_EXTENSION_XML = ".xml";
    public static final String FILE_EXTENSION_BAT = ".bat";
    public static final String FILE_EXTENSION_SH = ".sh";
    public static final String FILE_EXTENSION_FLT = ".flt";
    public static final String FILE_EXTENSION_IGV = ".igv";
    public static final String FILE_EXTENSION_PDF = ".pdf";
    public static final String FILE_EXTENSION_XLS = ".xls";
    public static final String FILE_EXTENSION_JFM = ".jfm";
    public static final String FILE_EXTENSION_TXT = ".txt";
    public static final String FILE_FORMAT_UTF8 = "UTF-8";
    public static final String FILE_SEPARATOR = "/"; // TedamStringUtils.getSystemSeparator(); there is no need for this procedure . with java "/". It was also tested in scripts.
    // Bsh string messages
    public static final String BSH_MESSAGE_SUCCESS = " has been successfully completed. Parameter : ";
    public static final String BSH_MESSAGE_DASHSIGN = "-----------------";
    public static final String BSH_MESSAGE_POPUPOPENERROR = "The problem was encountered when opening the Target Popup menu.";
    public static final String BSH_MESSAGE_POPUPSELECTERROR = "The problem was encountered when selecting the target popup item. Parameter : ";
    public static final String BSH_MESSAGE_ENDS = " ends. ";
    public static final String BSH_MESSAGE_PARAMETER = " Parameter : ";
    public static final String BSH_MESSAGE_BUTTONPARAMETER = "The operation depends on the button parameter entered. Parameter : ";
    public static final String BSH_MESSAGE_GRIDROWVERIFYNOTEQUAL = "The number of rows in the grid is not equal to the expected value. Verify Type : ";
    public static final String BSH_MESSAGE_VERIFYERROR = "Verify parameter entered incorrectly. Verify Type : ";
    public static final String BSH_MESSAGE_VERIFYSUCCESS = "Verify completed successfully. Verify Type : ";
    public static final String BSH_MESSAGE_VERIFYINCOMPATIBLE = "There are inconsistencies in the values to be checked. Verify Type : ";
    public static final String BSH_MESSAGE_VERIFYTYPE = " Verify Type : ";
    public static final String BSH_MESSAGE_TAG = "Tag: ";
    public static final String BSH_MESSAGE_TEXTEDITFORMFILL = " type: TextEdit. Component ";
    public static final String BSH_MESSAGE_COMBOEDITFORMFILL = " type: ComboEdit. Component ";
    public static final String BSH_MESSAGE_LOOKUPCOMBOEDITFORMFILL = " type: comboEdit/Lookup. Component ";
    public static final String BSH_MESSAGE_NONRANGEFILTERCELLS = " type: NonRangeFilterCells. Component ";
    public static final String BSH_MESSAGE_DATEEDITFORMFILL = " type: DateEdit. Component ";
    public static final String BSH_MESSAGE_COMBOBOXFORMFILL = " type: ComboBox. Component ";
    public static final String BSH_MESSAGE_CHECKBOXFORMFILL = " type: checkBox. Component ";
    public static final String BSH_MESSAGE_TEXTAREAFORMFILL = " type: TextArea. Component ";
    public static final String BSH_MESSAGE_NUMERICEDITCALCFORMFILL = " type: NumericEditWithCalculator. Component ";
    public static final String BSH_MESSAGE_CHECKBOCGROUPFORMFILL = " type: CheckBoxGroup. Component ";
    public static final String BSH_MESSAGE_RADIOBUTTONGROUPFORMFILL = " type: RadioButtonGroup. Component ";
    public static final String BSH_MESSAGE_SYNTAXEDITFORMFILL = " type: SyntaxEdit. Component ";
    public static final String BSH_MESSAGE_TIMEEDITFORMFILL = " type: TimeEdit. Component ";
    public static final String BSH_MESSAGE_RANGEFILTERCELLS = " type: RangeFilterCells. Component ";
    public static final String BSH_MESSAGE_FORMFILLPARAMETERERROR = "The FormFill parameter is entered incorrectly. Parameter: ";
    public static final String BSH_MESSAGE_ASSIGNERROR = " encountered a problem when assigning a value";
    public static final String BSH_MESSAGE_CLOSEALLFORMSERROR = "An error was encountered while closing forms";
    public static final String BSH_MESSAGE_STATUSMESSAGE = "Fail";
    public static final String BSH_MESSAGE_COLUMNINDEX = " ColumnIndex : ";
    public static final String BSH_MESSAGE_ROWINDEX = " rowIndex : ";
    public static final String BSH_MESSAGE_ROWDELETEERROR = "An error was encountered during the RowDelete operation. Parameter : ";
    public static final String BSH_MESSAGE_DOUBLECLICKERROR = "An error was encountered during the DoubleClick process. Parameter : ";
    public static final String BSH_CREATION_SUCCESS = "BSH was successfully produced.";
    public static final String BSH_CREATION_FAILED = "Failure was made when generating BSH.";
    public static final String BSH_MESSAGE_FILTERFILLPARAMETERERROR = "The FilterFill parameter was entered incorrectly. Parameter: ";
    public static final String BSH_MESSAGE_GETSNAPSHOTERROR = " I can not get the snapshot of the screen.";
    public static final String BSH_MESSAGE_BUTTONCLICKERROR = "An error was encountered while pressing the Apply button.";
    public static final String BSH_MESSAGE_FORMOPENPARAMETERERROR = "The FormOpen parameter is entered incorrectly. Parameter : ";
    public static final String BSH_MESSAGE_FORMOPENSUCCESS = "FormOpen has completed successfully. Parameter : ";
    public static final String BSH_MESSAGE_GRIDSEARCHSUCCESS = "GridSearch has been successfully completed. Parameter : ";
    public static final String BSH_MESSAGE_GRIDCELLSELECTPARAMETERERROR = "The GridCellSelect parameter was entered incorrectly. Parameter : ";
    public static final String BSH_MESSAGE_GRIDSEARCHPARAMETERERROR = "The GridSearch parameter was entered incorrectly. Parameter : ";
    public static final String BSH_MESSAGE_GRIDCELLSELECTSUCCESS = "GridCellSelect was successfully completed. Parameter : ";
    public static final String BSH_MESSAGE_GRIDROWSELECTSUCCESS = "GridRowSelect was successfully completed. Parameter : ";
    public static final String BSH_MESSAGE_FILTERFILLSUCCESS = "FilterFill completed successfully. Parameter: ";
    public static final String BSH_MESSAGE_ROWDELETESUCCESS = "RowDelete is completed successfully. Parameter : ";
    public static final String BSH_MESSAGE_DOUBLECLICKSUCCESS = "DoubleClick has been successfully completed. Parameter : ";
    public static final String BSH_MESSAGE_TIME = "TIME";
    public static final String BSH_MESSAGE_DATE = "DATE";
    public static final String BSH_MESSAGE_STRING = "STRING";
    public static final String BSH_MESSAGE_NUMERIC = "NUMERIC";
    public static final String BSH_MESSAGE_TIMERANGE = "TIMERANGE";
    public static final String BSH_MESSAGE_DATERANGE = "DATERANGE";
    public static final String BSH_MESSAGE_STRINGRANGE = "STRINGRANGE";
    public static final String BSH_MESSAGE_NUMERICRANGE = "NUMERICRANGE";
    public static final String BSH_MESSAGE_GROUP = "GROUP";
    public static final String BSH_MESSAGE_SELECTION = "SELECTION";
    public static final String BSH_MESSAGE_GRIDTAGNOTFOUND = "Grid tagi bulunamadi.";
    public static final String BSH_MESSAGE_GRIDTAG = " Grid tag : ";
    public static final String BSH_MESSAGE_CLASSPATHTEDAM = "C:\\Projects\\test\\Tools\\ty\\Beanshell_Scripts\\lib\\TEDAM.jar";
    public static final String BSH_MESSAGE_CLASSPATHSQLJDBC4 = "C:\\Projects\\test\\Tools\\ty\\Beanshell_Scripts\\lib\\sqljdbc4.jar";
    public static final String BSH_MESSAGE_CLASSPATHJXL = "C:\\Projects\\test\\Tools\\ty\\Beanshell_Scripts\\lib\\jxl.jar";
    public static final String BSH_MESSAGE_POI = "C:\\Projects\\test\\Tools\\ty\\Beanshell_Scripts\\lib\\poi-3.10-FINAL-20140208.jar";
    public static final String BSH_MESSAGE_ELSE = "Last Else";
    // Sah String messages
    public static final String SAH_CREATION = "Base.sah Establishment";
    public static final String SAH_CREATION_SUCCESS = "The base.sah file was successfully produced.";
    public static final String SAH_CREATION_FAILED = "Error while generating base.sah file.";
    public static final String SAH_MESSAGE_SUCCESS = " has been completed successfully. ";
    public static final String SAH_MESSAGE_PARAMETER = " Parameter : ";
    public static final String SAH_MESSAGE_BUTTONPARAMETER = "The operation depends on the button parameter entered. Parameter : ";
    public static final String SAH_MESSAGE_VERIFYERROR = "The Verify parameter is not found on the page or the values are not the same.";
    public static final String SAH_MESSAGE_VERIFYSUCCESS = "The Verify parameter is found on the page and the values are the same.";
    public static final String SAH_MESSAGE_NOT_EQUAL = " does not equal snapshot ";
    public static final String SAH_MESSAGE_EQUAL = " equals snapshot ";
    public static final String SAH_MESSAGE_TAG = " Tag : ";
    public static final String SAH_MESSAGE_SNAPSHOT_DEFINITION = " SnapshotDefinitionId: ";
    public static final String SAH_MESSAGE_FORMFILLPARAMETERROR = "The FormFill parameter was not found on the page. Parameter: ";
    public static final String SAH_MESSAGE_SNAPSHOTVALUELISTERROR = "SnapshotValueList is empty. SnapshotDefinitionId: ";
    public static final String SAH_MESSAGE_VERSION = "Version:";
    public static final String SAH_MESSAGE_TIME = "TIME";
    public static final String SAH_MESSAGE_DATE = "DATE";
    public static final String SAH_MESSAGE_STRING = "STRING";
    // //////////////////////////// TEDAMFACE
    public static final String FACE_URL_PARAMETER_PAGE = "page";
    public static final String FACE_URL_PARAMETER_TC = "tc";
    public static final String FACE_URL_PARAMETER_FUNCTION = "f";
    // PAGE OPERATIONS
    public static final String FACE_PAGE_FORMOPEN = "FORM_OPEN";
    public static final String FACE_PAGE_BUTTONCLICK = "BUTTON_CLICK";
    public static final String FACE_PAGE_SNAPSHOTUPLOADER = "SNAPSHOT_UPLOADER";
    public static final String FACE_PAGE_FORMFILL = "FORM_FILL";
    public static final String FACE_PAGE_FILTERFILL = "FILTER_FILL";
    public static final String FACE_PAGE_VERIFY = "VERIFY";
    public static final String FACE_PAGE_FORMOPERATIONS = "FORM_OPERATIONS";
    public static final String FACE_PAGE_CHANGESTATE = "CHANGE_STATE";
    public static final String FACE_PAGE_ROWSELECT = "ROW_SELECT";
    public static final String FACE_PAGE_ROWDELETE = "ROW_DELETE";
    public static final String FACE_PAGE_DIALOG = "DIALOG";
    public static final String FACE_PAGE_NAVIGATION = "NAVIGATION";
    public static final String FACE_PAGE_GRIDSEARCH = "GRID_SEARCH";
    public static final String FACE_PAGE_REPORT = "REPORT";
    // Synthetic Data
    public static final String OPERATION_TYPE_ONLYMANDATORY = "onlyMandatory";
    public static final String OPERATION_TYPE_ALLFIELDS = "allFields";
    public static final String OPERATION_TYPE_FORMRUNNER = "formRunner";
    // Spira Integration
    public static final String TEST_CASE_DESCRIPTION = "TestCaseDescription";
    public static final String TEST_STEP_TYPE = "Step Type";
    public static final String TEST_STEP_ID = "TestStepId";
    public static final String TEST_STEP_POSITION = "TestStepPosition";
    public static final String TEST_STEP_DESCRIPTION = "TestStepDescription";
    public static final String TEST_STEP_EXPECTED_RESULT = "TestStepExpectedResult";
    public static final String TEST_STEP_PARAMETER = "StepParameter";
    public static final String TEST_TEST_STEP_TYPE = "StepType";
    public static final int SPIRA_INDIA_PROJECT_ID = 41;
    // teststep artifact id
    public static final int SPIRA_ARTIFACT_TYPE_ID = 7;
    public static final int CUSTOM_PROPERTY_LIST_ID = 32;
    public static final int SPIRA_AUTOMATION_ENGINE_ID = 3;
    public static final int SPIRA_TIME_DIFFERENCE = -3;
    /**
     * int TEDAM_TIME_DIFFERENCE Time difference from spira for tedamdb
     */
    public static final int TEDAM_TIME_DIFFERENCE = 3;
    /**
     * int TEDAM_TIME_DIFFERENCE Time difference from spira for tedamdb
     */
    // Spira AddDocument
    public static final String DOCUMENT_DESCRIPTION = "Test Sonuç Dosyası";
    // Remote Launcher Constants
    public static final int DEFAULTMINUTES = 5;
    public static final int ADDMINUTES = 10;
    public static final int CORRECTION_TIME = 3; // time difference between sets
    public static final String VALUE_RUNNERSTACKTRACE = "RunnerStackTrace";
    public static final String VALUE_RUNNERMESSAGE = "RunnerMessage";
    public static final String VALUE_RUNNERTESTNAME = "RunnerTestName";
    public static final String VALUE_RELEASE_ID = "ReleaseId";
    public static final String TEST_SET_THREAD_NAME = "tryStartingTestSetThread";
    public static final String EVENTQUE_THREAD_NAME = "AWT-EventQueue-0";
    /**
     * String XPATH_CONTROL
     */
    public static final String XPATH_END_OF_COMPILE = "']";

    // XPath compile parser constants
    public static final String XPATH_ALL_TAG = "//*[@tag='";
    public static final String XPATH_CONTROL = "//Control";
    public static final String XPATH_CONTROL_TAG = "//Control[@tag='";
    public static final String XPATH_CONTROL_ATTRIBUTE = "//Control[@attribute ='";
    public static final String XPATH_CONTROL_VISIBLE = "//Control[@visible='";
    public static final String XPATH_DATAGRID = "//DataGrid";
    public static final String XPATH_DATAGRID_TAG = "//DataGrid[@tag='";
    public static final String XPATH_FILTERGRID = "//FilterGrid";
    public static final String XPATH_FILTERGRID_TAG = "//FilterGrid[@tag='";
    public static final String XPATH_MESSAGE = "//Message";
    public static final String XPATH_MESSAGE_ITEM = "message";
    public static final String XPATH_ITEM_LIST_ITEM = "itemList";
    public static final String XPATH_MESSAGE_TYPE = "//Message[@type='";
    public static final String XPATH_REPORT = "//report";
    public static final String XPATH_ROWS = "//Rows";
    public static final String XPATH_IGNORED_VERSION_CHAR = "v";
    public static final String TC = "tc";
    // File path constants
    public static final String TYS_MACHINE_TC = "/tedamface/" + Constants.TC;
    public static final String TEDAMFACE_TC = "C:/TedamFace/" + Constants.TC;
    // jguar parameters needed when generating testPackageContent file
    public static final int JGUAR_TIMEOUT = 1000000;
    public static final int JGUAR_EXEC_RATE = 100;
    // Maximum number of lines used for AgentGUI textarea
    public static final int MAX_LINE = 100;
    // AgentGUI inter-method thread sleep time
    public static final int THREAD_SLEEP_TIME = 5000;
    public static final String RESOURCE_CONFIG_TEST_PROPERTIES = "/configTest.properties";
    // tys tedamFace url
    public static final String TYS_TEDAMFACE_URL = "/TedamFace/webservice/json/client/post";
    // tedamface jobrunner table name
    public static final String ACTIVE = "ACTIVE";
    public static final String PAST = "PAST";
    public static final String FAVORITES = "FAVORITES";
    /**
     * int TEST_CATE_ID_FOR_UNIT_TEST testCase for unit tests in spira
     */
    public static final int TEST_CASE_ID_FOR_UNIT_TEST = 17841;

    // UnitTest system parameters
    public static final int TEST_SET_ID_FOR_UNIT_TEST = 2230;
    public static final int TEST_SET_TEST_CASE_ID_FOR_UNIT_TEST = 20685;
    public static final int RELEASE_ID_FOR_UNIT_TEST = 312;
    public static final int SPIRA_PROJECT_ID_FOR_UNIT_TEST = 8;
    /**
     * String RUNNER_TEST_NAME_TEDAM
     */
    public static final String RUNNER_TEST_NAME_TEDAM = "TEDAM";

    // The value written to TestName for TEDAM in TestRun
    /**
     * String EMPTY_STRING
     */
    public static final String EMPTY_STRING = "";
    /**
     * String COMMAND_SPLIT_STRING
     */
    public static final String COMMAND_SPLIT_STRING = " ";
    /**
     * String TEDAM_SLASH Used for the testset ratios shown in JobInfoDTO.
     */
    public static final String TEDAM_SLASH = "/";
    public static final String AGENT_TITLE = "TEDAM-Agent";
    // TedamFace is used in the title of the tabs to be used on the testSetView screen.
    public static final String TEST_SET_TITLE = "Test Set";
    public static final String TEST_CASE_TITLE = "Test Case";
    public static final String TEST_RUN_TITLE = "Test Run";
    // The root folder located at the root like Spira.
    public static final String ROOT_FOLDER_NAME = "Root";

    // The root folder located at the root like Spira.
    // Ted log file extension
    public static final String TEDAM_LOG_EXTENSION = ".log";
    // Tedam JobRunner spira user name
    public static final String TEDAM_JOBRUNNER_USERNAME = "TedamJobRunner";
    public static final String TEDAM_DATE_FORMAT = "dd-MM-yy";
    public static final String TEDAM_DB_DATE_FORMAT = "dd/MM/yy HH:mm:ss";
    // tedam commands
    public static final String WINDOW_COMMAND_HEAD = "cmd /C ";
    public static final String NETWORK_COMMAND_FIND_BYPORT = "netstat -ano | find ";
    public static final String TASK_KILL_COMMAND_HEAD_TITLE = "Taskkill /F /FI \"WINDOWTITLE eq ";
    public static final String TASK_KILL_COMMAND_HEAD_ID = "Taskkill /F /PID ";
    public static final String DOUBLE_QUOTE = "\"";
    public static final String DOUBLE_AND = "&&";
    public static final String EQUAL = "=";
    public static final String BLANK = " ";
    public static final String JAVA = "java";
    public static final String COPY_CP = "-cp";
    // The names of the fields to be used for the sahi in the config.properties file
    public static final String SAHI_INSTALLED_PATH = "sahiInstalledPath";
    public static final String SAHI_WAIT_MILLIS = "sahiWaitMillis";
    public static final String SAHI_FORM_FILL_WAIT_MILLIS = "sahiFormFillWaitMillis";
    public static final String RESOURCE_FOLDER = "C:/Projects/test/Tools/ty/Trunk/TEDAMv2/src/main/resources";
    public static final long CONNECTION_WAIT_MILLIS = 1000;
    public static final int CONNECTION_TRY_LIMIT = 300;
    public static final long JSON_POST_WAIT_MILLIS = 5000;
    // baseSahGenerator is used.
    public static final String SAHI_SCRIPT_PARAMETERS_MAP = "sahiScriptParametersMap";
    public static final String SAHI_SCRIPT_SAHI_PARAMETERS_DTO_LIST = "sahiParametersDTOList";
    public static final String WEB_SCRIPT_PARAMETERS_MAP = "webScriptParametersMap";
    public static final String WEB_SCRIPT_PARAMETERS_DTO_LIST = "webScriptParametersDTOList";
    // tedam report name result parameters
    public static final String REPORT_HEADER_SCRIPT = "TEDAM_SCRIPT_TX";
    public static final String REPORT_HEADER_RUN = "TEDAM_TX";
    public static final String REPORT_TEST_CASE = "_TC";
    public static final String TEDAM_TEST_SET = "TX";
    public static final String TEDAM_TEST_CASE = "TC";
    // name parameters used in reporting exceller
    public static final String TEDAM_PDF_COMPARISON = "TEDAM_PDF_COMPARISON";
    public static final String TEDAM_REPORT = "TEDAM_REPORT";
    public static final String IMPORT_JAVA_REQUIREMENT_SAHI = "";
    public static final String IMPORT_SYSTEM_REQUIREMENT_SAHI = "";
    public static final String GLOBAL_VARIABLE_LIST_SAHI = "";
    public static final String INCLUDE_SAHI = "_include";
    public static final String TEST_CASE_ID = "testCaseId";
    public static final String TEST_SET_ID = "testSetId";
    public static final String NUMBER_SIGN = "###############";
    public static final String STAR = "***************";
    public static final String STEP = " ADIM ";
    public static final String MULTI_COMMENT_START = " /* ";
    public static final String MULTI_COMMENT_END = " */ ";
    public static final String COMMON_SAH_PATH = "commonSahPath";
    public static final String SAH_FUNCTIONS_PATH = "sahFunctionsPath";
    public static final String TEDAM_EXECUTION_FILE_PATH = "tedamExecutionFilePath";
    public static final String TEMP_FILE_PATH = "tempFilePath";
    public static final String LOG_FILE_PATH = "logFilePath";
    public static final String CONFIG_FILE_PATH = "configFilePath";
    public static final String REST_PARAM_PROJECT_NAME = "ProjectName";
    public static final String BASE_REST_URL = "baseRestUrl";
    public static final String QUICK_JOB = "Hızlı İş";
    public static final String SCRIPT_CLICK = "click";
    public static final String TEDAM_MANAGER = "tedam.manager";
    public static final int KB_TO_BYTE = 1000;
    // JOB REPORT CONSTANTS
    public static final String ELEMENT_TESTSUITES = "testsuites";
    public static final String ELEMENT_TESTSUITE = "testsuite";
    public static final String ELEMENT_TESTCASE = "testcase";
    public static final String ELEMENT_FAILURE = "failure";
    public static final String ATTR_ID = "id";
    public static final String ATTR_NAME = "name";
    public static final String ATTR_CLASSNAME = "classname";
    public static final String ATTR_CLASSNAME_VALUE = "tedam";
    public static final String ATTR_TYPE = "type";

    private Constants() {
    }

}