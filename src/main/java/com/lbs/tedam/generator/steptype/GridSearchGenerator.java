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

import com.lbs.tedam.model.DTO.GridCell;
import com.lbs.tedam.util.Enums.Regex;
import com.lbs.tedam.util.EnumsV2.TestStepType;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class GridSearchGenerator extends Generator {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private String gridTag;
    private List<GridCell> searchValues = new ArrayList<>();

    @Override
    public boolean validate() {
        if (searchValues.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public String generateLookUp() {
        return TestStepType.GRID_SEARCH.getBeginRegex() + generate() + TestStepType.GRID_SEARCH.getEndRegex();
    }

    @Override
    public void degenerateLookUp(String parameter) {
        String paramWithNoRegex = parameter.replaceAll(TestStepType.GRID_SEARCH.getBeginRegex(), "");
        degenerate(paramWithNoRegex);
    }

    @Override
    public String generate() {
        String parameter = "";
        if (gridTag != null) {
            parameter += gridTag + Regex.PARAMETER_SPLITTER.getRegex();
        }
        int size = searchValues.size();
        if (size > 0) {
            parameter += "[";
            for (int i = 0; i < size; i++) {
                GridCell column = searchValues.get(i);
                parameter += "(" + column.getTag() + Regex.COMMA.getRegex() + column.getValue().replaceAll(" ", Regex.SPACE.getRegex()) + ")";
                if (i < size - 1)
                    parameter += Regex.COMMA.getRegex();
            }
            parameter += "]";
        }
        return parameter;
    }

    @Override
    public void degenerate(String parameter) {
        if (StringUtils.isEmpty(parameter)) {
            return;
        }
        String[] splittedParameter = parameter.split(Regex.PARAMETER_SPLITTER.getRegex());
        String columnValuePair = splittedParameter[0];
        if (splittedParameter.length > 1) {
            gridTag = splittedParameter[0];
            columnValuePair = splittedParameter[1];
        }
        findSearchValues(columnValuePair);
    }

    private void findSearchValues(String columnValuePair) {
        // Remove the leading and trailing "[" and "]" characters.
        String gridSearchParam = columnValuePair.substring(1, columnValuePair.length() - 1);
        // When parsed by "," character,
        // tag-searchText-tag-searchText will appear as.
        String[] propertySet = gridSearchParam.split(Regex.COMMA.getRegex());
        for (int i = 0; i < propertySet.length; i += 2) {
            // 2 skips the index and adds the tag value and
            // the text value following it to the GridSearch object.
            GridCell gridCell = new GridCell(propertySet[i].substring(1), propertySet[i + 1].substring(0, propertySet[i + 1].length() - 1));
            // !spc! is set to " " instead of regex.
            gridCell.setValue(gridCell.getValue().replaceAll(Regex.SPACE.getRegex(), " "));
            searchValues.add(gridCell);
        }
    }

    public String getGridTag() {
        return gridTag;
    }

    public void setGridTag(String gridTag) {
        this.gridTag = gridTag;
    }

    public List<GridCell> getSearchValues() {
        return searchValues;
    }

    public void setSearchValues(List<GridCell> searchValues) {
        this.searchValues = searchValues;
    }

}
