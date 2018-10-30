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

import com.lbs.tedam.model.DTO.LookupParameter;
import com.lbs.tedam.util.Enums.OperationTypes;
import com.lbs.tedam.util.Enums.Regex;
import com.lbs.tedam.util.Enums.ScriptParameters;
import com.lbs.tedam.util.Enums.VerifyTypes;
import com.lbs.tedam.util.EnumsV2.TedamBoolean;
import com.lbs.tedam.util.EnumsV2.TestStepType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class TedamRegexUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(TedamRegexUtils.class);

    private TedamRegexUtils() {
        // TedamRegexUtils private constructor
    }

    /**
     * Parse the lookupParameter information in the SnapshotValue and return it in the testStep style.
     *
     * @param paramWithRegex
     * @return
     */
    // TODO:to be used to remove the generator
    public static List<LookupParameter> getLookupParamProp(String paramWithRegex) {
        List<LookupParameter> returnList = new ArrayList<>();
        StringBuilder sbParamWithRegex = new StringBuilder();
        sbParamWithRegex.append(paramWithRegex);

        try {
            boolean loopCond = true;
            while (loopCond) {
                if (sbParamWithRegex.toString().startsWith(TestStepType.GRID_SEARCH.getBeginRegex())) {
                    // For lookup operations of type GridSearch, the text between the <gs> and </ gs> regexes is taken at the parameter in our hand.
                    int firstIndex = TestStepType.GRID_SEARCH.getBeginRegex().length();
                    int lastIndex = sbParamWithRegex.indexOf(TestStepType.GRID_SEARCH.getEndRegex(), TestStepType.GRID_SEARCH.getBeginRegex().length());
                    String param = sbParamWithRegex.substring(firstIndex, lastIndex);
                    //  The buttonClick parameters are parsed based on the !ps! regex.
                    String[] paramList = param.split(Regex.PARAMETER_SPLITTER.getRegex());

                    LookupParameter lookupParam = new LookupParameter(OperationTypes.GRID_SEARCH.getType());
                    if (paramList.length > 1) {
                        // If the GridTag value is given
                        lookupParam.addParameters(ScriptParameters.GRID_TAG, paramList[0]);
                        lookupParam.addParameters(ScriptParameters.SEARCH_DETAILS, paramList[1]);
                    } else {
                        // If the GridTag value is not given
                        lookupParam.addParameters(ScriptParameters.SEARCH_DETAILS, paramList[0]);
                    }
                    sbParamWithRegex.delete(0, lastIndex + TestStepType.GRID_SEARCH.getEndRegex().length());
                    returnList.add(lookupParam);

                } else if (sbParamWithRegex.toString().startsWith(TestStepType.FILTER_FILL.getBeginRegex())) {
                    // For lookup operations that are of type FilterFill
                    // In our parameter, text is taken between <ff> and </ ff>
                    // regexes.
                    int firstIndex = TestStepType.FILTER_FILL.getBeginRegex().length();
                    int lastIndex = sbParamWithRegex.indexOf(TestStepType.FILTER_FILL.getEndRegex(), TestStepType.FILTER_FILL.getBeginRegex().length());
                    String param = sbParamWithRegex.substring(firstIndex, lastIndex);

                    LookupParameter lookupParam = new LookupParameter(OperationTypes.FILTER_FILL.getType());
                    lookupParam.addParameters(ScriptParameters.UPLOADED_SNAPSHOT_ID, param);
                    sbParamWithRegex.delete(0, lastIndex + TestStepType.FILTER_FILL.getEndRegex().length());
                    returnList.add(lookupParam);
                } else if (sbParamWithRegex.toString().startsWith(TestStepType.FORM_FILL.getBeginRegex())) {
                    // For lookup operations that are of the FormFill type
                    // In our parameter, text is taken between <fof> and </fof>
                    // regexes.
                    int firstIndex = TestStepType.FORM_FILL.getBeginRegex().length();
                    int lastIndex = sbParamWithRegex.indexOf(TestStepType.FORM_FILL.getEndRegex(), TestStepType.FORM_FILL.getBeginRegex().length());
                    String param = sbParamWithRegex.substring(firstIndex, lastIndex);

                    LookupParameter lookupParam = new LookupParameter(OperationTypes.FORM_FILL.getType());
                    lookupParam.addParameters(ScriptParameters.UPLOADED_SNAPSHOT_ID, param);
                    sbParamWithRegex.delete(0, lastIndex + TestStepType.FORM_FILL.getEndRegex().length());
                    returnList.add(lookupParam);
                } else if (sbParamWithRegex.toString().startsWith(TestStepType.GRID_CELL_SELECT.getBeginRegex())) {
                    // For lookup operations that are of type GridCellSelect
                    // In our parameter, text is taken between the <gcs> and </ gcs>
                    // regexes.
                    int firstIndex = TestStepType.GRID_CELL_SELECT.getBeginRegex().length();
                    int lastIndex = sbParamWithRegex.indexOf(TestStepType.GRID_CELL_SELECT.getEndRegex(), TestStepType.GRID_CELL_SELECT.getBeginRegex().length());
                    String param = sbParamWithRegex.substring(firstIndex, lastIndex);

                    // The buttonClick parameters are parsed based on the
                    // !ps! regex.
                    String[] paramList = param.split(Regex.PARAMETER_SPLITTER.getRegex());
                    LookupParameter lookupParam = new LookupParameter(OperationTypes.GRID_CELL_SELECT.getType());
                    lookupParam.addParameters(ScriptParameters.GRID_TAG, paramList[0]);
                    lookupParam.addParameters(ScriptParameters.ROW_INDEX, paramList[1].replace("[", "").replace("]", ""));
                    lookupParam.addParameters(ScriptParameters.COLUMN_TAG, paramList[2]);
                    sbParamWithRegex.delete(0, lastIndex + TestStepType.GRID_CELL_SELECT.getEndRegex().length());
                    returnList.add(lookupParam);
                } else if (sbParamWithRegex.toString().startsWith(TestStepType.GRID_ROW_SELECT.getBeginRegex())) {
                    // For lookup operations that are of type GridRowSelect
                    // In our parameter, text is taken between <grs> and </grs>
                    // regexes.
                    int firstIndex = TestStepType.GRID_ROW_SELECT.getBeginRegex().length();
                    int lastIndex = sbParamWithRegex.indexOf(TestStepType.GRID_ROW_SELECT.getEndRegex(), TestStepType.GRID_ROW_SELECT.getBeginRegex().length());
                    String param = sbParamWithRegex.substring(firstIndex, lastIndex);

                    // The buttonClick parameters are parsed based on the
                    // !ps! regex
                    String[] paramList = param.split(Regex.PARAMETER_SPLITTER.getRegex());
                    LookupParameter lookupParam = new LookupParameter(OperationTypes.GRID_ROW_SELECT.getType());
                    lookupParam.addParameters(ScriptParameters.GRID_TAG, paramList[0]);
                    lookupParam.addParameters(ScriptParameters.ROW_INDEX_LIST, paramList[1]);
                    sbParamWithRegex.delete(0, lastIndex + TestStepType.GRID_ROW_SELECT.getEndRegex().length());
                    returnList.add(lookupParam);
                } else if (sbParamWithRegex.toString().startsWith(TestStepType.GRID_DOUBLE_CLICK.getBeginRegex())) {
                    // For lookup operations that are of type DoubleClick
                    // In our parameter, the text between the <dc> and </ dc> regex
                    // is taken.
                    int firstIndex = TestStepType.GRID_DOUBLE_CLICK.getBeginRegex().length();
                    int lastIndex = sbParamWithRegex.indexOf(TestStepType.GRID_DOUBLE_CLICK.getEndRegex(), TestStepType.GRID_DOUBLE_CLICK.getBeginRegex().length());
                    String param = sbParamWithRegex.substring(firstIndex, lastIndex);
                    // The buttonClick parameters are parsed based on the !dc!
                    // regex.
                    String[] paramList = param.split(Regex.PARAMETER_SPLITTER.getRegex());
                    LookupParameter lookupParam = new LookupParameter(OperationTypes.DOUBLE_CLICK.getType());
                    lookupParam.addParameters(ScriptParameters.GRID_TAG, paramList[0]);
                    lookupParam.addParameters(ScriptParameters.ROW_INDEX, paramList[1].replace("[", "").replace("]", ""));
                    sbParamWithRegex.delete(0, lastIndex + TestStepType.GRID_DOUBLE_CLICK.getEndRegex().length());
                    returnList.add(lookupParam);

                } else if (sbParamWithRegex.toString().startsWith(TestStepType.ROW_COUNT_VERIFY.getBeginRegex())) {
                    // For lookup operations that are of type RowCountVerify
                    // In our parameter, text is taken between <rcv> and </ rcv>
                    // regexes.
                    int firstIndex = TestStepType.ROW_COUNT_VERIFY.getBeginRegex().length();
                    int lastIndex = sbParamWithRegex.indexOf(TestStepType.ROW_COUNT_VERIFY.getEndRegex(), TestStepType.ROW_COUNT_VERIFY.getBeginRegex().length());

                    // The buttonClick parameters are parsed based on the
                    // !ps! regex.
                    String[] param = sbParamWithRegex.substring(firstIndex, lastIndex).split(Regex.PARAMETER_SPLITTER.getRegex());
                    LookupParameter lookupParam = new LookupParameter(VerifyTypes.ROW_COUNT_VERIFY.getType());
                    lookupParam.addParameters(ScriptParameters.GRID_TAG, param[0]);
                    lookupParam.addParameters(ScriptParameters.ROW_COUNT, param[1]);
                    sbParamWithRegex.delete(0, lastIndex + TestStepType.ROW_COUNT_VERIFY.getEndRegex().length());

                    returnList.add(lookupParam);

                } else if (sbParamWithRegex.toString().startsWith(TestStepType.VERIFY.getBeginRegex())) {
                    // For lookup operations that are of type FieldValueVerify
                    // In our parameter, text is taken between <fvv> and </ fvv>
                    // regexes.
                    int firstIndex = TestStepType.VERIFY.getBeginRegex().length();
                    int lastIndex = sbParamWithRegex.indexOf(TestStepType.VERIFY.getEndRegex(), TestStepType.VERIFY.getBeginRegex().length());
                    // The buttonClick parameters are parsed based on the !ps! regex.
                    String[] params = sbParamWithRegex.substring(firstIndex, lastIndex).split(Regex.PARAMETER_SPLITTER.getRegex());
                    LookupParameter lookupParam = new LookupParameter(VerifyTypes.FIELD_VALUE_VERIFY.getType());
                    if (params.length == 1) {// the old data only has uploadedSnapshotId. Since there is no IS_IGNORE_ROW INDEX in the old, all the parameters.length with a value
                        // of 1 will be set to default false (0).
                        lookupParam.addParameters(ScriptParameters.UPLOADED_SNAPSHOT_ID, params[0]);
                        lookupParam.addParameters(ScriptParameters.IS_IGNORE_ROW_INDEX, TedamBoolean.FALSE.getCode().toString());
                    } else if (params.length == 2) {
                        lookupParam.addParameters(ScriptParameters.UPLOADED_SNAPSHOT_ID, params[0]);
                        lookupParam.addParameters(ScriptParameters.IS_IGNORE_ROW_INDEX, params[1]);

                    }
                    sbParamWithRegex.delete(0, lastIndex + TestStepType.VERIFY.getEndRegex().length());

                    returnList.add(lookupParam);

                } else if (sbParamWithRegex.toString().startsWith(TestStepType.MESSAGE_VERIFY.getBeginRegex())) {
                    // For lookup operations that are of type FieldValueVerify
                    // In our parameter, text is taken between <mv> and </ mv>
                    // regexes.
                    int firstIndex = TestStepType.MESSAGE_VERIFY.getBeginRegex().length();
                    int lastIndex = sbParamWithRegex.indexOf(TestStepType.MESSAGE_VERIFY.getEndRegex(), TestStepType.MESSAGE_VERIFY.getBeginRegex().length());
                    String param = sbParamWithRegex.substring(firstIndex, lastIndex).replace(Regex.SPACE.getRegex(), " ");

                    LookupParameter lookupParam = new LookupParameter(VerifyTypes.MESSAGE_VERIFY.getType());
                    lookupParam.addParameters(ScriptParameters.MESSAGE_VERIFY_PARAMETER, param);
                    sbParamWithRegex.delete(0, lastIndex + TestStepType.MESSAGE_VERIFY.getEndRegex().length());

                    returnList.add(lookupParam);

                } else if (sbParamWithRegex.toString().startsWith(TestStepType.BUTTON_CLICK.getBeginRegex())) {
                    // For button look-type lookup operations
                    // In our parameter, the text between the <bc> and </ bc>
                    // regexes is taken.
                    int firstIndex = TestStepType.BUTTON_CLICK.getBeginRegex().length();
                    int lastIndex = sbParamWithRegex.indexOf(TestStepType.BUTTON_CLICK.getEndRegex(), TestStepType.BUTTON_CLICK.getBeginRegex().length());

                    // The buttonClick parameters are parsed based on the
                    // !ps! regex.
                    String param = sbParamWithRegex.substring(firstIndex, lastIndex);
                    String[] paramList = param.split(Regex.PARAMETER_SPLITTER.getRegex());
                    LookupParameter lookupParam = new LookupParameter(OperationTypes.BUTTON_CLICK.getType());
                    lookupParam.addParameters(ScriptParameters.BUTTON_TAG, paramList[0]);
                    if (paramList.length > 1) {
                        // If the MenuButton is pressed
                        lookupParam.addParameters(ScriptParameters.MENU_BUTTON_RESOURCE_TAG, paramList[1]);
                    }
                    sbParamWithRegex.delete(0, lastIndex + TestStepType.BUTTON_CLICK.getEndRegex().length());
                    returnList.add(lookupParam);
                } else if (sbParamWithRegex.toString().startsWith(TestStepType.POPUP.getBeginRegex())) {
                    // For lookup operations that are popup-type
                    // In our parameter, the text between the <pu> and </pu>
                    //  regexes is taken.
                    int firstIndex = TestStepType.POPUP.getBeginRegex().length();
                    int lastIndex = sbParamWithRegex.indexOf(TestStepType.POPUP.getEndRegex(), TestStepType.POPUP.getBeginRegex().length());

                    // Popup parameters are parsed based on !ps!
                    // regex.
                    String param = sbParamWithRegex.substring(firstIndex, lastIndex);
                    String[] paramList = param.split(Regex.PARAMETER_SPLITTER.getRegex());
                    LookupParameter lookupParam = new LookupParameter(OperationTypes.POP_UP.getType());
                    lookupParam.addParameters(ScriptParameters.POP_UP_TAG, paramList[0]);
                    // Since there are no scenarios where rowindex and column tag are set, the condition for the first 2 cases specified in BaseBshGenerator is written.
                    if (paramList.length == 1) {
                        lookupParam.addParameters(ScriptParameters.POP_UP_TAG, paramList[0]);
                    } else if (paramList.length == 2) {
                        // The gridTag is not set here because there is no popup in the lookup when I look at the scenario created up to now.
                        lookupParam.addParameters(ScriptParameters.POP_UP_TAG, paramList[1]);
                    } else if (paramList.length == 4) {
                        lookupParam.addParameters(ScriptParameters.POP_UP_TAG, paramList[0]);
                        lookupParam.addParameters(ScriptParameters.GRID_TAG, paramList[1]);
                        lookupParam.addParameters(ScriptParameters.ROW_INDEX, paramList[2]);
                        lookupParam.addParameters(ScriptParameters.COLUMN_TAG, paramList[3]);
                    }
                    sbParamWithRegex.delete(0, lastIndex + TestStepType.POPUP.getEndRegex().length());
                    returnList.add(lookupParam);
                } else {
                    loopCond = false;
                }
            }
        } catch (Exception e) {
            LOGGER.error("An error was encountered while creating the parameter.", e);
        }
        return returnList;
    }

    /**
     * This method decodes filterfill parameters for nonrange types and returns in order. <br>
     * index 0 => grouped value entered to filter. <br>
     * index 1 => excluded value entered to filter. <br>
     *
     * @param parameterCollection
     * @return
     * @author Ozgur.Ozbil
     */
    public static List<String> decomposeNonRangeValues(String parameterCollection) {
        List<String> parameters = new ArrayList<String>();
        int index;
        int sizeOfRegexExclude = Constants.VALUE_REGEX_EXCLUDE.length();
        int sizeOfRegexGroupped = Constants.VALUE_REGEX_GROUPPED.length();

        for (int i = 0; i < 2; i++) {
            parameters.add(null);
        }
        if (parameterCollection.contains(Constants.VALUE_REGEX_EXCLUDE)) {
            index = parameterCollection.indexOf(Constants.VALUE_REGEX_EXCLUDE);
            if (parameterCollection.contains(Constants.VALUE_REGEX_GROUPPED)) {
                parameters.set(0, parameterCollection.substring(sizeOfRegexGroupped, index));
            }
            parameters.set(1, parameterCollection.substring(index + sizeOfRegexExclude));
        } else if (parameterCollection.contains(Constants.VALUE_REGEX_GROUPPED)) {
            parameters.set(0, parameterCollection.substring(sizeOfRegexGroupped));
        }
        return parameters;
    }

    /**
     * This method decodes filterfill parameters for range types and returns in order. <br>
     * index 0 => grouped value entered to filter. <br>
     * index 1 => low value entered to filter. <br>
     * index 2 => high value entered to filter. <br>
     * index 3 => excluded value entered to filter.<br>
     *
     * @param parameterCollection
     * @usedIn FilterFill.bsh
     */
    public static List<String> decomposeRangeValues(String parameterCollection) {
        List<String> parameters = new ArrayList<String>();
        int index;
        int secondaryIndex;
        int sizeOfRegexExclude = Constants.VALUE_REGEX_EXCLUDE.length();
        int sizeOfRegexGroupped = Constants.VALUE_REGEX_GROUPPED.length();
        int sizeOfRegexLow = Constants.VALUE_REGEX_LOWVALUE.length();
        int sizeOfRegexHigh = Constants.VALUE_REGEX_HIGHVALUE.length();

        for (int i = 0; i < 4; i++) {
            parameters.add(null);
        }
        // If parameter contains excluded value
        if (parameterCollection.contains(Constants.VALUE_REGEX_EXCLUDE)) {
            index = parameterCollection.indexOf(Constants.VALUE_REGEX_EXCLUDE);
            // If filter is grouped
            if (parameterCollection.contains(Constants.VALUE_REGEX_GROUPPED)) {
                parameters.set(0, parameterCollection.substring(sizeOfRegexGroupped, index));
            } else if (parameterCollection.contains(Constants.VALUE_REGEX_LOWVALUE) && parameterCollection.contains(Constants.VALUE_REGEX_HIGHVALUE)) {
                // If filter has low and high values
                secondaryIndex = parameterCollection.indexOf(Constants.VALUE_REGEX_HIGHVALUE);
                parameters.set(1, parameterCollection.substring(sizeOfRegexGroupped, secondaryIndex));
                parameters.set(2, parameterCollection.substring(secondaryIndex + sizeOfRegexHigh, index));
            }
            parameters.set(3, parameterCollection.substring(index + sizeOfRegexExclude));
        } else {
            // If filter is grouped
            if (parameterCollection.contains(Constants.VALUE_REGEX_GROUPPED)) {
                parameters.set(0, parameterCollection.substring(sizeOfRegexGroupped));
            } else if (parameterCollection.contains(Constants.VALUE_REGEX_LOWVALUE) && parameterCollection.contains(Constants.VALUE_REGEX_HIGHVALUE)) {
                // If filter has low and high values
                secondaryIndex = parameterCollection.indexOf(Constants.VALUE_REGEX_HIGHVALUE);
                parameters.set(1, parameterCollection.substring(sizeOfRegexLow, secondaryIndex));
                parameters.set(2, parameterCollection.substring(secondaryIndex + sizeOfRegexHigh));
            }
        }
        return parameters;
    }
}
