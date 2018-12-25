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

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lbs.tedam.data.config.DataConfig;
import com.lbs.tedam.data.service.TestDataConfig;
import com.lbs.tedam.test.BaseServiceTest;
import com.lbs.tedam.util.EnumsV2.ExecutionStatus;
import com.lbs.tedam.util.EnumsV2.TestRunType;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestDataConfig.class, DataConfig.class})
public class TestCaseTestRunTest extends BaseServiceTest {

    @Test
    public void testSetters() {
        TestCaseTestRun testCaseTestRun = new TestCaseTestRun();
        testCaseTestRun.setClientName(null);
        testCaseTestRun.setEndDate(LocalDateTime.now());
        testCaseTestRun.setExecutionMessage("executionMessage");
        testCaseTestRun.setExecutionStatus(ExecutionStatus.CAUTION);
        testCaseTestRun.setStartDate(LocalDateTime.now());
        testCaseTestRun.setTestCaseId(Integer.valueOf(1));
        testCaseTestRun.setTestRunType(TestRunType.MANUAL);
        testCaseTestRun.setTestStepTestRunList(new ArrayList<>());
        testCaseTestRun.setVersion("version");
        testCaseTestRun.setTestSetId(1);
    }

    @Test
    public void testGetters() {
        TestCaseTestRun testCaseTestRun = new TestCaseTestRun();
        testCaseTestRun.getClientName();
        testCaseTestRun.getEndDate();
        testCaseTestRun.getExecutionMessage();
        testCaseTestRun.getExecutionStatus();
        testCaseTestRun.getStartDate();
        testCaseTestRun.getTestCaseId();
        testCaseTestRun.getTestRunType();
        testCaseTestRun.getTestStepTestRunList();
        testCaseTestRun.getVersion();
		testCaseTestRun.getTestCaseName();
		testCaseTestRun.getJobName();
		testCaseTestRun.getTestSetName();
    }

}
