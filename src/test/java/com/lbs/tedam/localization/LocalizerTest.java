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

import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class LocalizerTest {

    @Test
    public void testLocalizerByENUS() {
        Localizer localizerenUS = Localizer.instance("localizerenUS", "localization.localizer");
        localizerenUS.loadBundle(new Locale("en", "US"));
        String value = localizerenUS.getValue("testKey");
        assertTrue("test key".equals(value));
    }

    @Test
    public void testLocalizerByTRTR() {
        Localizer localizertrTR = Localizer.instance("localizertrTR", "localization.localizer");
        localizertrTR.loadBundle(new Locale("tr", "TR"));
        String value = localizertrTR.getValue("testKey");
        assertTrue("test enaktar".equals(value));
    }

    @Test
    public void testLocalizerManager() {
        Localizer localizertrTR = Localizer.instance("localizertrTR", "localization.localizer");
        Localizer localizerenUS = Localizer.instance("localizerenUS", "localization.localizer");
        LocalizerManager.putLocalizer(localizertrTR.getAlias(), localizertrTR);
        LocalizerManager.putLocalizer(localizerenUS.getAlias(), localizerenUS);
        assertSame(localizertrTR, LocalizerManager.getLocalizer("localizertrTR"));
        assertSame(localizerenUS, LocalizerManager.getLocalizer("localizerenUS"));
    }

    /**
     * @author Berk.Kemaloglu
     * @author Seyma.Sahin
     */
    @Test
    public void testLocalConstants() {
        Locale locale1 = LocaleConstants.LOCALE_ENUS;
        Locale locale2 = LocaleConstants.LOCALE_TRTR;
        LocalizerManager.loadLocaleForAll(locale1);
        LocalizerManager.loadLocaleForAll(locale2);
    }

}