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

package com.lbs.tedam.data.service;

import com.lbs.tedam.data.config.DataConfig;
import com.lbs.tedam.data.service.impl.*;
import com.lbs.tedam.exception.VersionParameterValueException;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.*;
import com.lbs.tedam.model.DTO.LogoTestResult;
import com.lbs.tedam.test.BaseServiceTest;
import com.lbs.tedam.util.EnumsV2.CommandStatus;
import com.lbs.tedam.util.EnumsV2.ExecutionStatus;
import com.lbs.tedam.util.EnumsV2.RunOrder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {JobRunnerDetailCommandServiceImpl.class, ClientServiceImpl.class, JobServiceImpl.class, JobDetailServiceImpl.class, JobCommandServiceImpl.class,
        JobParameterServiceImpl.class, JobCommandServiceImpl.class, TestCaseServiceImpl.class, TestCaseTestRunServiceImpl.class, TestCaseServiceImpl.class, TestDataConfig.class,
        TedamFolderServiceImpl.class, DataConfig.class})
public class JobRunnerDetailCommandServiceTest extends BaseServiceTest {

    @Autowired
    private JobRunnerDetailCommandService jobRunnerDetailCommandService;

    @Autowired
    private JobDetailService jobDetailService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private JobService jobService;

    @Autowired
    private TestCaseService testCaseService;

    @Test
    public void test1CreateJobRunnerDetailCommand() throws LocalizedException {
        Assert.assertTrue(jobRunnerDetailCommandService.createJobRunnerDetailCommandByJobCommand(jobDetailService.getAll().get(0)).size() > 0);
    }

    @Test
    public void test2CreateTestRunForTestCaseAndTestStep() throws VersionParameterValueException, LocalizedException {
        LogoTestResult logoTestResult2 = new LogoTestResult();
        logoTestResult2.setId(15);
        logoTestResult2.setDescription("description3");
        logoTestResult2.setName("name3");
        logoTestResult2.setResult(ExecutionStatus.CAUTION);

        List<LogoTestResult> testResultList = new ArrayList<>(Arrays.asList(logoTestResult2));
        JobDetail jobDetail = jobDetailService.getAll().get(3);
        Job job = jobService.getById(jobDetail.getJobId());
        Client client = clientService.getAll().get(0);
        jobDetail.setClient(client);
        TestCase testCase = testCaseService.getAll().get(0);
        JobCommand jobCommand = new JobCommand();
        jobCommand.setTestCase(testCase);
        JobRunnerDetailCommand jobRunnerDetailCommand = new JobRunnerDetailCommand();
        jobRunnerDetailCommand.setCommandStatus(CommandStatus.COMPLETED);
        jobRunnerDetailCommand.setExecutionStatus(ExecutionStatus.FAILED);
        jobRunnerDetailCommand.setTestCaseId(1);
        jobRunnerDetailCommand.setJobCommandId(1);
        jobRunnerDetailCommand.setDraftCommandName("draftCommandName");
        jobRunnerDetailCommand.setFirstExpectedResult("firstExpectedResult");
        jobRunnerDetailCommand.setLastExpectedResult("lastExpectedResult");
        jobRunnerDetailCommand.setUnixCommand("unixValue");
        jobRunnerDetailCommand.setWindowsCommand("windowsValue");
        jobRunnerDetailCommand.setRunOrder(RunOrder.RUN_SCRIPT);
        jobRunnerDetailCommand.setEndDate(LocalDateTime.now());
        jobRunnerDetailCommand.setStartDate(LocalDateTime.now());
        jobRunnerDetailCommand.setTestResultList(testResultList);
        jobRunnerDetailCommandService.createTestRunForTestCaseAndTestStep(jobRunnerDetailCommand, jobCommand, jobDetail, job);
    }

}
