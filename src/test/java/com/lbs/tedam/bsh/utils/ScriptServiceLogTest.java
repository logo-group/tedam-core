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
import com.lbs.tedam.util.Enums.TedamLogLevel;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;

/**
 * @author Tarik.Mikyas<br>
 */
public class ScriptServiceLogTest extends BaseServiceTest {

    /**
     * ScriptService scriptService
     */
    @Parameter
    private static ScriptService scriptService;

    /**
     * @author Tarik.Mikyas <br>
     * this method create scriptService before class created
     */
    @BeforeClass
    public static void testGetScriptService() {
        scriptService = new ScriptService();
    }

    /**
     * @author Tarik.Mikyas <br>
     * this method test1Log
     */
    @Test
    public void test1Log() {
        scriptService.log("ScriptServiceTest", "This is an information Message", TedamLogLevel.INFO, true);
    }

    /**
     * @author Tarik.Mikyas <br>
     * this method test2Log
     */
    @Test
    public void test2Log() {
        scriptService.log("ScriptServiceTest", "This is an ERROR message and will not be printed.", TedamLogLevel.ERROR, false);
    }
}
