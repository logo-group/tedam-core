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

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Util class for localization.
 */
public class Localizer {

    /**
     * Resource bundle instance.
     */
    private ResourceBundle resource = null;

    /**
     * Alias of Localizer.
     */
    private String alias = "";

    /**
     * Bundle file.
     */
    private String bundleFile;

    /**
     * To prevent to use new.
     *
     * @param alias      Alias of Localizer.
     * @param bundleFile Bundle file.
     */
    private Localizer(String alias, String bundleFile) {
        this.alias = alias;
        this.bundleFile = bundleFile;
    }

    /**
     * Creates a new instance.
     *
     * @param alias      Alias of Localizer.
     * @param bundleFile Bundle file
     * @return New instance of Localizer.
     */
    public static Localizer instance(String alias, String bundleFile) {
        return new Localizer(alias, bundleFile);
    }

    /**
     * Loads bundle content. Before use getValue, call loadBundle once.
     *
     * @param bundleFile File to load content.
     * @param locale     Locale info.
     */
    public void loadBundle(Locale locale) {
        resource = ResourceBundle.getBundle(getBundleFile(), locale);
    }

    /**
     * Returns value by key specified. Before use getValue, call loadBundle once.
     *
     * @param key Key value to search.
     * @return Value in the bundle.
     */
    public String getValue(String key) {
        return resource.getString(key);
    }

    /**
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * @return the bundleFile
     */
    public String getBundleFile() {
        return bundleFile;
    }

}