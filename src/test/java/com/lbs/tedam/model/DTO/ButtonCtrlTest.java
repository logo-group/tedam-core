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

package com.lbs.tedam.model.DTO;

import com.lbs.tedam.test.BaseServiceTest;
import org.junit.Test;

import java.util.Arrays;

public class ButtonCtrlTest extends BaseServiceTest {

    /**
     * this method test02GettersAndSetters <br>
     *
     * @author Tarik.Mikyas <br>
     */
    @Test
    public void test02GettersAndSetters() {
        ButtonCtrl buttonCtrl = new ButtonCtrl();
        buttonCtrl = new ButtonCtrl("type", "xuiDoc", "tag");
        buttonCtrl.setMenuButtonItemTextList(Arrays.asList("A-", "-B", "-C-"));
        buttonCtrl.setMenuButtonItemTagList(Arrays.asList(1, 2, 3));
        buttonCtrl.clearButtonLists(Arrays.asList("A-", "-B", "-C-"), Arrays.asList(1, 2, 3));
        buttonCtrl.getAttribute();
        buttonCtrl.getMenuButtonItemTagList();
        buttonCtrl.getMenuButtonItemTextList();
        buttonCtrl.getTag();
        buttonCtrl.getType();
        buttonCtrl.getXuiDoc();
        buttonCtrl.setAttribute("attribute");
        buttonCtrl.setTag("tag");
        buttonCtrl.setType("type");
        buttonCtrl.setXuiDoc("xuiDoc");
        buttonCtrl.toString();
    }

}
