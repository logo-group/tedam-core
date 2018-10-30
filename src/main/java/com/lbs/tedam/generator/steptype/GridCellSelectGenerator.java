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

public class GridCellSelectGenerator extends Generator {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private String gridTag;
    private int rowIndex;
    private String columnTag;

    @Override
    public boolean validate() {
        if (StringUtils.isEmpty(gridTag) || gridTag.equals("0") || StringUtils.isEmpty(columnTag)) {
            return false;
        }
        return true;
    }

    @Override
    public String generateLookUp() {
        return TestStepType.GRID_CELL_SELECT.getBeginRegex() + generate() + TestStepType.GRID_CELL_SELECT.getEndRegex();
    }

    @Override
    public void degenerateLookUp(String parameter) {
        String paramWithNoRegex = parameter.replaceAll(TestStepType.GRID_CELL_SELECT.getBeginRegex(), "");
        degenerate(paramWithNoRegex);
    }

    @Override
    public String generate() {
        String parameter = "";
        if (gridTag != null) {
            parameter += gridTag + Regex.PARAMETER_SPLITTER.getRegex();
        }
        parameter += "[" + rowIndex + "]" + Regex.PARAMETER_SPLITTER.getRegex();
        if (!StringUtils.isEmpty(columnTag) && !columnTag.equals("0")) {
            parameter += columnTag;
        }
        return parameter;
    }

    @Override
    public void degenerate(String parameter) {
        if (StringUtils.isEmpty(parameter)) {
            return;
        }
        String[] splittedParameter = parameter.split(Regex.PARAMETER_SPLITTER.getRegex());
        gridTag = splittedParameter[0].replace(Regex.SPACE.getRegex(), Constants.TEXT_BLANK);
        rowIndex = Integer.valueOf(splittedParameter[1].replace("[", Constants.EMPTY_STRING).replace("]", Constants.EMPTY_STRING));
        columnTag = splittedParameter[2];
    }

    public String getColumnTag() {
        return columnTag;
    }

    public void setColumnTag(String columnTag) {
        this.columnTag = columnTag;
    }

    public String getGridTag() {
        return gridTag;
    }

    public void setGridTag(String gridTag) {
        this.gridTag = gridTag;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

}
