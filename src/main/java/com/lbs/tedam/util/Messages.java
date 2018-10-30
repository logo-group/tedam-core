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

/**
 */
public final class Messages {

    public static final String EDITOR_NAME_NOT_FOUND = "Editor info is not entered for this form";
    public static final String EDITOR_NAME_FOUND = "Editor name for tihs form: ";
    public static final String EDITOR_NAME_NOT_FOUND_EDITOR = "Current form is already an EDITOR";
    public static final String FORM_CONTRACT_STATUS_FALSE = "Form is not converted to contract structure";
    public static final String FORM_CONTRACT_STATUS_TRUE = "Form converted to contract structure";
    public static final String VDR_FORMID_MANDATORY = "The FormID parameter was not entered or was entered incorrectly. Please try again.";
    // Synthetic data messages
    public static final String SYNTHETIC_IMAGEBUTTON_CLICK_ERROR = " tagged button can not be pressed.";
    public static final String SYNTHETIC_IMAGEBUTTON_TAG_ERROR = ". An error has been encountered while retrieving the button's tag.";
    public static final String SYNTHETIC_GRID_TAG_ERROR = ". It encountered an error when retrieving the gridin label to be activated.";
    public static final String SYNTHETIC_VERIFY_LOOKUP_ERROR = " tagged field lookup encountered an error setting the value";
    public static final String SYNTHETIC_VERIFY_SAVED_ERROR = " An error was encountered while performing the verify operation on the created record.";
    public static final String SYNTHETIC_FORMFILL_PARAMETER_COMPOSE_ERROR = "Parameter creation for Formfill has failed.";
    public static final String BSH_MESSAGE_LOOKUPCOMBOEDITFORMFILL = " type: comboEdit/Lookup. Component ";
    public static final String BSH_MESSAGE_DATEEDITFORMFILL = " type: DateEdit. Component ";
    public static final String BSH_MESSAGE_COMBOBOXFORMFILL = " type: ComboBox. Component ";
    public static final String BSH_MESSAGE_CHECKBOXFORMFILL = " type: checkBox. Component ";
    public static final String BSH_MESSAGE_TEXTAREAFORMFILL = " type: TextArea. Component ";
    public static final String BSH_MESSAGE_NUMERICEDITCALCFORMFILL = " type: NumericEditWithCalculator. Component ";
    public static final String BSH_MESSAGE_RADIOBUTTONGROUPFORMFILL = " type: RadioButtonGroup. Component ";
    public static final String BSH_MESSAGE_SYNTAXEDITFORMFILL = " type: SyntaxEdit. Component ";
    public static final String BSH_MESSAGE_TIMEEDITFORMFILL = " type: TimeEdit. Component ";
    public static final String BSH_MESSAGE_ASSIGNERROR = " The problem was encountered when assigning a value. ";
    public static final String BSH_MESSAGE_TAG = " Tag: ";

    private Messages() {
        // Messages private constructor
    }
}
