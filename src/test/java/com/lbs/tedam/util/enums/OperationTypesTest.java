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

package com.lbs.tedam.util.enums;

import com.lbs.tedam.util.Enums.OperationTypes;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;

/**
 * OperationTypes The class created for testing the enum.
 *
 * @author Tarik.Mikyas<br>
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OperationTypesTest {

    /**
     * @author Tarik.Mikyas <br>
     * this method test01CreateEnum
     */
    @Test
    public void test01CreateEnum() {
        OperationTypes operationTypes = OperationTypes.BUTTON_CLICK;
        operationTypes.getType();
    }

    /**
     * @author Berk.Kemaloglu
     * @author Seyma.Sahin
     */
    @Test
    public void testGetDefault() {
        assertEquals(null, OperationTypes.getDefault());
    }

    /**
     * @author Berk.Kemaloglu
     * @author Seyma.Sahin
     */
    @Test
    public void testFromName1() {
        String name = "dialog";
        OperationTypes operationTypes1 = OperationTypes.fromName(name);
        OperationTypes operationTypes2 = OperationTypes.MESSAGE_DIALOG;
        assertEquals(operationTypes2, operationTypes1);
    }

    /**
     * @author Berk.Kemaloglu
     * @author Seyma.Sahin
     */
    @Test
    public void testFromName2() {
        String name1 = "wrongName";
        String name2 = null;
        OperationTypes operationTypes1 = OperationTypes.fromName(name1);
        OperationTypes operationTypes2 = null;
        OperationTypes operationTypes3 = OperationTypes.fromName(name2);
        assertEquals(operationTypes2, operationTypes1);
        assertEquals(operationTypes2, operationTypes3);
    }

}
