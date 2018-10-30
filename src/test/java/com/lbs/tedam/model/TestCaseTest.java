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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestDataConfig.class, DataConfig.class})
public class TestCaseTest extends BaseServiceTest {

    @Test
    public void testSetters() {
        TestCase testCase = new TestCase("name", "description", null, null);
        testCase.setAutomated(false);
        testCase.setDescription("description");
        testCase.setExecutionDateTime(LocalDateTime.now());
        testCase.setLookUps(Arrays.asList(new TestStep()));
        testCase.setName("name");
        testCase.setProject(null);
        testCase.setTestCaseFolderId(Integer.valueOf(1));
        testCase.setTestCaseTestRunList(new ArrayList<>());
        testCase.setTestSteps(Arrays.asList(new TestStep()));
        testCase.cloneTestCase();
        testCase.generateName();
    }

    @Test
    public void testGetters() {
        TestCase testCase = new TestCase();
        testCase.isAutomated();
        testCase.getDescription();
        testCase.getExecutionDateTime();
        testCase.getLookUps();
        testCase.getName();
        testCase.getProject();
        testCase.getTestCaseFolderId();
        testCase.getTestCaseTestRunList();
        testCase.getTestSteps();
        testCase.getExecutionStatus();

        TestCaseTestRun testRun = new TestCaseTestRun();
        testRun.setEndDate(LocalDateTime.now());
        TestCaseTestRun testRun1 = new TestCaseTestRun();
        testRun1.setEndDate(LocalDateTime.of(2018, 3, 20, 1, 10));
        TestCaseTestRun testRun2 = new TestCaseTestRun();
        testRun2.setEndDate(LocalDateTime.of(2018, 5, 20, 1, 10));
        testCase.getTestCaseTestRunList().add(testRun);
        testCase.getTestCaseTestRunList().add(testRun1);
        testCase.getTestCaseTestRunList().add(testRun2);
        testCase.getExecutionStatus();

        testCase.setName("deneme_1");
        testCase.generateName();
        testCase.getVersion();
        testCase.getTestCaseFolder();

    }

}
