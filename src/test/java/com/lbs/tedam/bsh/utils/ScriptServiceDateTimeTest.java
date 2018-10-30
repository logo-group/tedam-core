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

package com.lbs.tedam.bsh.utils;

import com.lbs.tedam.test.BaseServiceTest;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Tarik.Mikyas<br>
 */
public class ScriptServiceDateTimeTest extends BaseServiceTest {

    private ScriptService scriptService = new ScriptService();

    /**
     * @author Tarik.Mikyas <br>
     * this method test1GetFormattedDateStringAsString
     */
    @Test
    public void test1GetFormattedDateStringAsString() {
        String result = scriptService.getFormattedDateStringAsString("12:34");
        assertTrue(result.equalsIgnoreCase("12:34:0:0"));
    }

    /**
     * @author Tarik.Mikyas <br>
     * this method test2GetFormattedDateStringAsString
     */
    @Test
    public void test2GetFormattedDateStringAsString() {
        String result = scriptService.getFormattedDateStringAsString("12:34:56");
        assertTrue(result.equalsIgnoreCase("12:34:56:0"));
    }

    /**
     * @author Tarik.Mikyas <br>
     * this method test3GetFormattedDateStringAsString
     */
    @Test
    public void test3GetFormattedDateStringAsString() {
        String result = scriptService.getFormattedDateStringAsString("12:34:56:78");
        assertTrue(result.equalsIgnoreCase("12:34:56:78"));
    }

}
