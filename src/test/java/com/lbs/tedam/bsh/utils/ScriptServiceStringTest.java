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

import com.lbs.tedam.model.DTO.MessageDialog;
import com.lbs.tedam.test.BaseServiceTest;
import com.lbs.tedam.util.TedamStringUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Tarik.Mikyas<br>
 */
public class ScriptServiceStringTest extends BaseServiceTest {

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
     *         this method testGetGridSearchParameter
     */
//	@Test
//	// TODO:it was intervened.
//	public void testGetGridSearchParameter() {
//		List<GridSearch> gridSearchList = scriptService.getGridSearchParameter("[(1001,TC002235SF),(1002,Confirming)]");
//		assertTrue(gridSearchList.size() > 0);
//	}

    /**
     * @author Tarik.Mikyas <br>
     * this method testMessageDialogParameterParser
     */
    @Test
    public void testMessageDialogParameterParser() {
        List<MessageDialog> messageDialogList = scriptService.messageDialogParameterParser("[(true,Emin!spc!Misiniz),(false,Son!spcKarar!spcmi?)]");
        assertNotEquals(messageDialogList.size(), 0);
    }

    /**
     * @author Tarik.Mikyas <br>
     * this method test1DecomposeFormName
     */
    @Test
    public void test1DecomposeFormName() {
        Map<String, String> resultMap = TedamStringUtils.decomposeFormName("LOXFInvoice%8");
        assertTrue(resultMap.get("name").equalsIgnoreCase("LOXFInvoice") && resultMap.get("mode").equalsIgnoreCase("8"));

    }

    /**
     * @author Tarik.Mikyas <br>
     * this method test2DecomposeFormName
     */
    @Test
    public void test2DecomposeFormName() {
        Map<String, String> resultMap = TedamStringUtils.decomposeFormName("LOXFInvoice");
        assertTrue(resultMap.get("name").equalsIgnoreCase("LOXFInvoice") && resultMap.get("mode").equalsIgnoreCase("0"));

    }

    /**
     * @author Tarik.Mikyas <br>
     * this method testGetRowIndexList
     */
    @Test
    public void testGetRowIndexList() {
        List<Integer> returnList = scriptService.getRowIndexList("[1,2,3,4]");
        assertTrue(returnList.get(0) == 1);
    }

}
