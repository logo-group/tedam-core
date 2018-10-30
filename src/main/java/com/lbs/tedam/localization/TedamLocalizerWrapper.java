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

package com.lbs.tedam.localization;

import com.lbs.tedam.util.Constants;

import java.util.logging.Logger;

public interface TedamLocalizerWrapper {

    public default String getLocaleValue(String value) {
        String localValue = Constants.EMPTY_STRING;
        try {
            localValue = LocalizerManager.getLocalizer("tedamface").getValue(value);
        } catch (Exception e) {
            Logger.getLogger("TedamLocalizerWrapper").severe("Key not found in tedamface resource: " + value);
        }
        return localValue;
    }

    public default String getFormOpenLocaleValue(String value) {
        String localValue = Constants.EMPTY_STRING;
        try {
            localValue = LocalizerManager.getLocalizer("formopen").getValue(value);
        } catch (Exception e) {
            Logger.getLogger("TedamLocalizerWrapper").severe("Key not found in formopen resource: " + value);
        }
        return localValue;
    }

    public default String getExceptionLocaleValue(String value) {
        String localValue = Constants.EMPTY_STRING;
        try {
            localValue = LocalizerManager.getLocalizer("localizedException").getValue(value);
        } catch (Exception e) {
            Logger.getLogger("TedamLocalizerWrapper").severe("Key not found in localizedException resource: " + value);
        }
        return localValue;
    }

}
