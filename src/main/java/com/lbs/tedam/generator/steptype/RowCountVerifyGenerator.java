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

package com.lbs.tedam.generator.steptype;

import com.lbs.tedam.util.Constants;
import com.lbs.tedam.util.Enums.Regex;
import com.lbs.tedam.util.EnumsV2.TestStepType;
import org.springframework.util.StringUtils;

public class RowCountVerifyGenerator extends Generator {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private String gridTag;
    private String rowCount;
    private String continueOnError;

    @Override
    public boolean validate() {
        if (StringUtils.isEmpty(rowCount) || StringUtils.isEmpty(continueOnError)) {
            return false;
        }
        return true;
    }

    @Override
    public String generateLookUp() {
        return TestStepType.ROW_COUNT_VERIFY.getBeginRegex() + generate() + TestStepType.ROW_COUNT_VERIFY.getEndRegex();
    }

    @Override
    public void degenerateLookUp(String parameter) {
        String paramWithNoRegex = parameter.replaceAll(TestStepType.ROW_COUNT_VERIFY.getBeginRegex(), "");
        degenerate(paramWithNoRegex);
    }

    @Override
    public String generate() {
        String parameter = "";
        if (gridTag != null) {
            parameter += gridTag + Regex.PARAMETER_SPLITTER.getRegex();
        }
        if (rowCount != null) {
            parameter += rowCount + Regex.PARAMETER_SPLITTER.getRegex();
        }
        if (!StringUtils.isEmpty(continueOnError) && !continueOnError.equals("0")) {
            parameter += "1";
        } else {
            parameter += "0";
        }
        return parameter;
    }

    @Override
    public void degenerate(String parameter) {
        if (StringUtils.isEmpty(parameter)) {
            return;
        }
        String[] splittedParameter = parameter.split(Regex.PARAMETER_SPLITTER.getRegex());
        if (splittedParameter.length == 2) {
            rowCount = splittedParameter[0];
            continueOnError = splittedParameter[1];
        } else if (splittedParameter.length == 3) {
            gridTag = splittedParameter[0].replace(Regex.SPACE.getRegex(), Constants.TEXT_BLANK);
            rowCount = splittedParameter[1];
            continueOnError = splittedParameter[2];
        }
    }

    public String getContinueOnError() {
        return continueOnError;
    }

    public void setContinueOnError(String continueOnError) {
        this.continueOnError = continueOnError;
    }

    public String getGridTag() {
        return gridTag;
    }

    public void setGridTag(String gridTag) {
        this.gridTag = gridTag;
    }

    public String getRowCount() {
        return rowCount;
    }

    public void setRowCount(String rowCount) {
        this.rowCount = rowCount;
    }

}
