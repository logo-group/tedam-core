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

import com.lbs.tedam.model.DTO.LookupParameter;
import com.lbs.tedam.test.BaseServiceTest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;

import java.util.List;

import static org.junit.Assert.assertNotEquals;

/**
 * @author Tarik.Mikyas<br>
 */
public class ScriptServiceRegexTest extends BaseServiceTest {

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
     * this method testGetLookupParamProp
     */
    @Test
    public void testGetLookupParamProp() {
        StringBuilder sb = new StringBuilder();
        sb.append("<gs>[(105,04*)]</gs><gs>100!ps![(101,04*)]</gs>");// GridSearch
        sb.append("<ff>5870</ff>");// FilterFill
        sb.append("<fof>5869</fof>");// FormFill
        sb.append("<gcs>110!ps![0]!ps!111</gcs>");// GridCellSelect
        sb.append("<grs>110!ps![1]</grs>");// gridRowSelect
        sb.append("<dc>100!ps!0</dc>");// doubleClick
        sb.append("<rcv>100!ps!2!ps!1</rcv>");// rowCountVerify
        sb.append("<fvv>2397</fvv>");// fieldValueVerify
        sb.append("<mv>messageVerify</mv>");// messageVerify
        sb.append("<bc>161</bc>");// buttonClick
        sb.append("<bc>1000!ps!1002</bc>");// buttonClick
        sb.append("<pu>Tutar</pu>");// popUp
        sb.append("<pu>1501!ps!Hesap</pu>");// popUp
        List<LookupParameter> lookupParameterList = scriptService.getLookupParamProp(sb.toString());
        for (LookupParameter lookupParameter : lookupParameterList) {
            System.out.println("lookupParameter :" + lookupParameter.getLookupMethod() + " map : " + lookupParameter.getParameters());
        }
        assertNotEquals(lookupParameterList.size(), 0);
    }
}
