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

import com.lbs.tedam.util.EnumsV2.FormOpenShortcutType;
import org.springframework.util.StringUtils;

/**
 * @author Ahmet.Izgi
 */
public class FormOpenShortcutGenerator extends Generator {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private FormOpenShortcutType formOpenShortcutType;

    @Override
    public boolean validate() {
        if (formOpenShortcutType == null) {
            return false;
        }
        return true;
    }

    @Override
    public String generate() {
        return formOpenShortcutType.getValue();
    }

    @Override
    public void degenerate(String parameter) {
        if (StringUtils.isEmpty(parameter)) {
            return;
        }
        setFormOpenShortcutType(FormOpenShortcutType.fromValue(parameter));
    }

    /**
     * @return the formOpenShortcutType
     */
    public FormOpenShortcutType getFormOpenShortcutType() {
        return formOpenShortcutType;
    }

    /**
     * @param formOpenShortcutType the formOpenShortcutType to set
     */
    public void setFormOpenShortcutType(FormOpenShortcutType formOpenShortcutType) {
        this.formOpenShortcutType = formOpenShortcutType;
    }

}
