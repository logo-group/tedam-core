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

import java.util.ArrayList;
import java.util.List;

public class GridDoubleClickGenerator extends Generator {
    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private String gridTag;
    private List<Integer> rowIndexes = new ArrayList<>();

    @Override
    public boolean validate() {
        if (StringUtils.isEmpty(gridTag) || gridTag.equals("0") || rowIndexes.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public String generateLookUp() {
        return TestStepType.GRID_DOUBLE_CLICK.getBeginRegex() + generate() + TestStepType.GRID_DOUBLE_CLICK.getEndRegex();
    }

    @Override
    public void degenerateLookUp(String parameter) {
        String paramWithNoRegex = parameter.replaceAll(TestStepType.GRID_DOUBLE_CLICK.getBeginRegex(), "");
        degenerate(paramWithNoRegex);
    }

    @Override
    public String generate() {
        String parameter = "";
        if (gridTag != null) {
            parameter += gridTag + Regex.PARAMETER_SPLITTER.getRegex();
        }
        parameter += rowIndexes;
        return parameter;
    }

    @Override
    public void degenerate(String parameter) {
        if (StringUtils.isEmpty(parameter)) {
            return;
        }
        String[] splittedParameter = parameter.split(Regex.PARAMETER_SPLITTER.getRegex());
        gridTag = splittedParameter[0].replace(Regex.SPACE.getRegex(), Constants.TEXT_BLANK);
        rowIndexes = rowIndexesStringToList(splittedParameter[1].replace("[", Constants.EMPTY_STRING).replace("]", Constants.EMPTY_STRING));
    }

    private List<Integer> rowIndexesStringToList(String rowIndexesString) {
        List<Integer> rowIndexesList = new ArrayList<>();
        String[] rowIndexesArray = rowIndexesString.split(Constants.TEXT_COMMA);
        for (int i = 0; i < rowIndexesArray.length; i++) {
            rowIndexesList.add(Integer.valueOf(rowIndexesArray[i].trim()));
        }
        return rowIndexesList;
    }

    public String getGridTag() {
        return gridTag;
    }

    public void setGridTag(String gridTag) {
        this.gridTag = gridTag;
    }

    public List<Integer> getRowIndexes() {
        return rowIndexes;
    }

    public void setRowIndexes(List<Integer> rowIndexes) {
        this.rowIndexes = rowIndexes;
    }
}
