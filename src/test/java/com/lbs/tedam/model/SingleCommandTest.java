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

package com.lbs.tedam.model;

import com.lbs.tedam.data.config.DataConfig;
import com.lbs.tedam.data.service.TestDataConfig;
import com.lbs.tedam.test.BaseServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestDataConfig.class, DataConfig.class})
public class SingleCommandTest extends BaseServiceTest {

    @Test
    public void testSetters() {
        SingleCommand singleCommand = new SingleCommand("name", "windowsValue", "unixValue", null, "firstExpextedResult", "lastExpectedResult");
        singleCommand.setClients(new ArrayList<>());
        singleCommand.setFirstExpectedResult("firstExpectedResult");
        singleCommand.setLastExpectedResult("lastExpectedResult");
        singleCommand.setName("name");
        singleCommand.setUnixValue("unixValue");
        singleCommand.setWindowsValue("windowsValue");
    }

    @Test
    public void testGetters() {
        SingleCommand singleCommand = new SingleCommand();
        singleCommand.getClients();
        singleCommand.getFirstExpectedResult();
        singleCommand.getLastExpectedResult();
        singleCommand.getName();
        singleCommand.getUnixValue();
        singleCommand.getWindowsValue();
    }

}
