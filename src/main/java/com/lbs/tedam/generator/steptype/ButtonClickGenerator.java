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

/**
 *
 */
package com.lbs.tedam.generator.steptype;

import com.lbs.tedam.util.Enums.Regex;
import com.lbs.tedam.util.EnumsV2.TestStepType;
import org.springframework.util.StringUtils;

/**
 * @author Ahmet.Izgi
 */
public class ButtonClickGenerator extends Generator {

    private static final long serialVersionUID = 1L;
    private String buttonTag;
    private String menuButtonItemTag;
    private String continueOnError;

    @Override
    public boolean validate() {
        if (((buttonTag == null || "0".equals(buttonTag)) && (menuButtonItemTag == null || "0".equals(menuButtonItemTag))) || continueOnError == null) {
            return false;
        }
        return true;
    }

    @Override
    public String generateLookUp() {
        return TestStepType.BUTTON_CLICK.getBeginRegex() + generate() + TestStepType.BUTTON_CLICK.getEndRegex();
    }

    @Override
    public void degenerateLookUp(String parameter) {
        String paramWithNoRegex = parameter.replaceAll(TestStepType.BUTTON_CLICK.getBeginRegex(), "");
        degenerate(paramWithNoRegex);
    }

    @Override
    public String generate() {
        String parameter = "";
        if (!StringUtils.isEmpty(buttonTag))
            parameter = buttonTag + Regex.PARAMETER_SPLITTER.getRegex();
        if (!StringUtils.isEmpty(menuButtonItemTag)) {
            parameter += menuButtonItemTag + Regex.PARAMETER_SPLITTER.getRegex();
        }
        if (!StringUtils.isEmpty(continueOnError) && !"0".equals(continueOnError)) {
            parameter += "1";
        } else {
            parameter += "0";
        }
        return parameter.replace(" ", Regex.SPACE.getRegex());
    }

    @Override
    public void degenerate(String parameter) {
        if (parameter != null) {
            parameter = parameter.replace(Regex.SPACE.getRegex(), " ");
        }
        // sonar can not recognize stringutils' isEmpty method
        if ((parameter == null || "".equals(parameter))) {
            return;
        }
        String[] splittedParameter = parameter.split(Regex.PARAMETER_SPLITTER.getRegex());
        if (splittedParameter != null) {
            buttonTag = splittedParameter[0];
            if (splittedParameter.length > 2) {
                // If button is MenuButton then second parameter of three parameters is
                // menuButtonResourceTag(menuButtonItemNo)
                continueOnError = splittedParameter[2];
                menuButtonItemTag = splittedParameter[1];
            } else if (splittedParameter.length > 1) {
                // If there is only 2 parameters then second parameter is continueOnError
                continueOnError = splittedParameter[1];
            } else {
                continueOnError = "0";
            }
        }
    }

    /**
     * @return the buttonTag
     */
    public String getButtonTag() {
        return buttonTag;
    }

    /**
     * @param buttonTag the buttonTag to set
     */
    public void setButtonTag(String buttonTag) {
        this.buttonTag = buttonTag;
    }

    /**
     * @return the menuButtonItemTag
     */
    public String getMenuButtonItemTag() {
        return menuButtonItemTag;
    }

    /**
     * @param menuButtonItemTag the menuButtonItemTag to set
     */
    public void setMenuButtonItemTag(String menuButtonItemTag) {
        this.menuButtonItemTag = menuButtonItemTag;
    }

    /**
     * @return the continueOnError
     */
    public String getContinueOnError() {
        return continueOnError;
    }

    /**
     * @param continueOnError the continueOnError to set
     */
    public void setContinueOnError(String continueOnError) {
        this.continueOnError = continueOnError;
    }

}
