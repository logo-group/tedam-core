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
import com.lbs.tedam.util.EnumsV2.CommandStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestDataConfig.class, DataConfig.class})
public class TestSetTest extends BaseServiceTest {

    @Test
    public void testSetters() {
        TestSet testSet = new TestSet();
        testSet.setActualDuration(3);
        testSet.setDescription("description");
        testSet.setExecutionDateTime(LocalDateTime.now());
        testSet.setName("name");
        testSet.setProject(null);
        testSet.setTestSetTestCases(new ArrayList<>());
        testSet.setTestSetFolderId(Integer.valueOf(1));
        testSet.setTestSetStatus(CommandStatus.IN_PROGRESS);
        testSet.setCautionTestCaseCount(3);
        testSet.setBlockedTestCaseCount(3);
        testSet.setNotRunTestCaseCount(3);
        testSet.setSucceededTestCaseCount(3);
        testSet.setFailedTestCaseCount(3);
        testSet.setJobListAsString("jobListAsString");
        testSet.setAutomated(true);
        testSet.setTestSetFolder("testSetFolder");
    }

    @Test
    public void testGetters() {
        TestSet testSet = new TestSet();
        testSet.getActualDuration();
        testSet.getDescription();
        testSet.getExecutionDateTime();
        testSet.getName();
        testSet.getProject();
        testSet.getTestSetTestCases();
        testSet.getTestSetFolderId();
        testSet.getTestSetStatus();
        testSet.isAutomated();
        testSet.getTestCasesOrdered();
        testSet.getTestCases();
        testSet.getTestSetFolder();
        testSet.getJobListAsString();
        testSet.getFailedTestCaseCount();
        testSet.getFailedTestCaseCount();
        testSet.getBlockedTestCaseCount();
        testSet.getCautionTestCaseCount();
        testSet.getSucceededTestCaseCount();
        testSet.getNotRunTestCaseCount();
        testSet.getBlockedTestCaseCount();

    }

}
