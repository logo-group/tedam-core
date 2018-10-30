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
import com.lbs.tedam.util.EnumsV2.TestStepType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestDataConfig.class, DataConfig.class})
public class TestStepTest extends BaseServiceTest {

    @Test
    public void testSetters() {
        TestStep testStep = new TestStep("description", "expectedResult", TestStepType.FILTER_FILL, "parameter",
                "filename", "expectedFormname", null);
        testStep.setPosition(3);
        testStep.setLookUp(false);
        testStep.setDescription("description");
        testStep.setExpectedFormname("expectedFormName");
        testStep.setExpectedResult("expectedResult");
        testStep.setFilename("filename");
        testStep.setGenerator(null);
        testStep.setLookUp(true);
        testStep.setParameter("parameter");
        testStep.setPosition(5);
        testStep.setProject(null);
        testStep.setTestCaseId(Integer.valueOf(1));
        testStep.setTestStepTestRunList(new ArrayList<>());
        testStep.setType(TestStepType.FORM_FILL);
        testStep.cloneTestStep();
    }

    @Test
    public void testGetters() {
        TestStep testStep = new TestStep();
        testStep.getDescription();
        testStep.getExpectedFormname();
        testStep.getExpectedResult();
        testStep.getFilename();
        testStep.getGenerator();
        testStep.isLookUp();
        testStep.getParameter();
        testStep.getPosition();
        testStep.getProject();
        testStep.getTestCaseId();
        testStep.getTestStepTestRunList();
        testStep.getType();
        testStep.getExecutionStatus();

        TestStepTestRun testRun = new TestStepTestRun();
        testRun.setEndDate(LocalDateTime.now());
        TestStepTestRun testRun1 = new TestStepTestRun();
        testRun1.setEndDate(LocalDateTime.of(2018, 3, 20, 1, 10));
        TestStepTestRun testRun2 = new TestStepTestRun();
        testRun2.setEndDate(LocalDateTime.of(2018, 5, 20, 1, 10));
        testStep.getTestStepTestRunList().add(testRun);
        testStep.getTestStepTestRunList().add(testRun1);
        testStep.getTestStepTestRunList().add(testRun2);
        testStep.getExecutionStatus();

    }

}
