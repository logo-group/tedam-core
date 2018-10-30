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

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Util class for manage Localizer classes.
 */
public class LocalizerManager {

    /**
     * Localizer map.
     */
    private static Map<String, Localizer> localizerMap = new HashMap<String, Localizer>();

    private LocalizerManager() {

    }

    /**
     * Adds localizer to list.
     *
     * @param alias     Localizer alias.
     * @param localizer Localizer instance.
     */
    public static void putLocalizer(String alias, Localizer localizer) {
        localizerMap.put(alias, localizer);
    }

    /**
     * Gets Localizer by alias.
     *
     * @param alias Alias of Localizer.
     * @return Localizer instance by alias.
     */
    public static Localizer getLocalizer(String alias) {
        return localizerMap.get(alias);
    }

    /***
     * Loads bundle to all Localizer instances in the list.
     *
     * @param locale
     *            Locale info.
     */
    public static void loadLocaleForAll(Locale locale) {
        for (Localizer localizer : localizerMap.values())
            localizer.loadBundle(locale);
    }

}