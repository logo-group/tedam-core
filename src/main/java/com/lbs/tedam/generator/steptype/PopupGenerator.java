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

public class PopupGenerator extends Generator {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private String gridTag;
    private String popupItem;

    @Override
    public boolean validate() {
        if (StringUtils.isEmpty(popupItem)) {
            return false;
        }
        return true;
    }

    @Override
    public String generateLookUp() {
        return TestStepType.POPUP.getBeginRegex() + generate() + TestStepType.POPUP.getEndRegex();
    }

    @Override
    public void degenerateLookUp(String parameter) {
        String paramWithNoRegex = parameter.replaceAll(TestStepType.POPUP.getBeginRegex(), "");
        degenerate(paramWithNoRegex);
    }

    @Override
    public String generate() {
        String parameter = "";
        if (gridTag != null) {
            parameter += gridTag + Regex.PARAMETER_SPLITTER.getRegex();
        }
        if (popupItem != null) {
            popupItem = popupItem.replaceAll(Constants.BLANK, Regex.SPACE.getRegex());
            parameter += popupItem;
        }
        return parameter;
    }

    @Override
    public void degenerate(String parameter) {
        if (StringUtils.isEmpty(parameter)) {
            return;
        }
        String[] splittedParameter = parameter.split(Regex.PARAMETER_SPLITTER.getRegex());
        if (splittedParameter.length > 1) {
            popupItem = splittedParameter[1].replace(Regex.SPACE.getRegex(), Constants.TEXT_BLANK);
            gridTag = splittedParameter[0];
        } else {
            popupItem = splittedParameter[0].replace(Regex.SPACE.getRegex(), Constants.TEXT_BLANK);
        }
    }

    public String getGridTag() {
        return gridTag;
    }

    public void setGridTag(String gridTag) {
        this.gridTag = gridTag;
    }

    /**
     * @return the popupItem
     */
    public String getPopupItem() {
        return popupItem;
    }

    /**
     * @param popupItem the popupItem to set
     */
    public void setPopupItem(String popupItem) {
        this.popupItem = popupItem;
    }
}
