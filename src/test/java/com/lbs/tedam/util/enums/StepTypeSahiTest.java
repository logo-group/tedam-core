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

import com.lbs.tedam.util.Enums.StepTypeSahi;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * OperationTypes The class created for testing the enum.
 *
 * @author Tarik.Mikyas<br>
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StepTypeSahiTest {

    /**
     * @author Tarik.Mikyas <br>
     * this method test01CreateEnum
     */
    @Test
    public void test01CreateEnum() {
        StepTypeSahi stepType = StepTypeSahi.BUTTON_CLICK;
        stepType.getType();
        stepType.getCode();
        StepTypeSahi.getDefault();
        StepTypeSahi.fromName("FormFill");
        StepTypeSahi.fromName("Form");
        StepTypeSahi.fromName("formfill");
        StepTypeSahi.fromName(null);

    }

}
