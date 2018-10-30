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

package com.lbs.tedam.data.service.impl;

import com.lbs.tedam.data.service.*;
import com.lbs.tedam.exception.VersionParameterValueException;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.DTO.LogoTestResult;
import com.lbs.tedam.model.*;
import com.lbs.tedam.util.Constants;
import com.lbs.tedam.util.EnumsV2.ExecutionStatus;
import com.lbs.tedam.util.EnumsV2.TestRunType;
import com.lbs.tedam.util.TedamStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobRunnerDetailCommandServiceImpl implements JobRunnerDetailCommandService {

    @Autowired
    private JobCommandService jobCommandService;

    @Autowired
    private JobParameterService jobParameterService;

    @Autowired
    private TestCaseService testCaseService;

    @Autowired
    private TestCaseTestRunService testCaseTestRunService;

    @Override
    public List<JobRunnerDetailCommand> createJobRunnerDetailCommandByJobCommand(JobDetail jobDetail) {
        List<JobRunnerDetailCommand> jobRunnerDetailCommandList = new ArrayList<>();
        for (JobCommand jobCommand : jobDetail.getJobCommands()) {
            JobRunnerDetailCommand jobRunnerDetailCommand = new JobRunnerDetailCommand();
            jobRunnerDetailCommand.setJobCommandId(jobCommand.getId());
            jobRunnerDetailCommand.setCommandStatus(jobCommand.getCommandStatus());
            jobRunnerDetailCommand.setDraftCommandName(jobCommand.getDraftCommand().getName());
            jobRunnerDetailCommand.setFirstExpectedResult(jobCommand.getDraftCommand().getFirstExpectedResult());
            jobRunnerDetailCommand.setLastExpectedResult(jobCommand.getDraftCommand().getLastExpectedResult());
            jobRunnerDetailCommand.setRunOrder(jobCommand.getDraftCommand().getRunOrder());
            jobRunnerDetailCommand.setWindowsCommand(jobCommand.getWindowsCommand());
            jobRunnerDetailCommand.setUnixCommand(jobCommand.getUnixCommand());
            jobRunnerDetailCommand.setExecutionStatus(jobCommand.getExecutionStatus());
            jobRunnerDetailCommand.setTestCaseId(jobCommand.getTestCase().getId());
            jobRunnerDetailCommandList.add(jobRunnerDetailCommand);
        }
        return jobRunnerDetailCommandList;
    }

    @Override
    public JobCommand getJobCommandByJobRunnerDetailCommand(JobRunnerDetailCommand jobRunnerDetailCommand) throws LocalizedException {
        JobCommand jobCommand = jobCommandService.getById(jobRunnerDetailCommand.getJobCommandId());
        jobCommand.setCommandStatus(jobRunnerDetailCommand.getCommandStatus());
        jobCommand.setExecutionStatus(jobRunnerDetailCommand.getExecutionStatus());
        return jobCommand;
    }

    @Override
    public void createTestRunForTestCaseAndTestStep(JobRunnerDetailCommand jobRunnerDetailCommand, JobCommand jobCommand, JobDetail jobDetail, Job job)
            throws VersionParameterValueException, LocalizedException {
        String versionNumber = getVersionParameterValue(job);
        List<LogoTestResult> testResultList = jobRunnerDetailCommand.getTestResultList();
        ExecutionStatus executionStatus = jobRunnerDetailCommand.getExecutionStatus();
        if (!testResultList.isEmpty()) {
            executionStatus = TedamStringUtils.calculateTotalTestCaseResult(testResultList);
        }
        String description = TedamStringUtils.buildTestCaseResultDescription(executionStatus, testResultList);

        testCaseService.updateTestCaseExecutionDateTime(jobCommand.getTestCase(), jobRunnerDetailCommand.getEndDate());

        TestCaseTestRun testCaseTestRun = new TestCaseTestRun(jobDetail.getClient().getName(), versionNumber,
                TestRunType.AUTOMATED, executionStatus, description, jobRunnerDetailCommand.getStartDate(),
                jobRunnerDetailCommand.getEndDate());
        testCaseTestRun.setTestSetId(jobDetail.getTestSetId());
        testCaseTestRun.setTestCaseId(jobCommand.getTestCase().getId());
        testCaseTestRun.setDateCreated(LocalDateTime.now());
        testCaseTestRun.setCreatedUser(Constants.TEDAM_MANAGER);
        testCaseTestRun.setTestSetName(jobDetail.getTestSetName());
        testCaseTestRun.setJobName(job.getName());
        List<TestStepTestRun> testStepTestRunList = new ArrayList<>();
        for (LogoTestResult testResult : jobRunnerDetailCommand.getTestResultList()) {
            TestStep testStep = jobCommand.getTestCase().getTestSteps().stream().filter(tempTestStep -> tempTestStep.getId().equals(testResult.getId())).findAny().orElse(null);
            if (testStep != null) {
                TestStepTestRun testStepTestRun = new TestStepTestRun(jobDetail.getClient().getName(), versionNumber,
                        TestRunType.AUTOMATED, testResult.getResult(), testResult.getDescription(), null, null);
                testStepTestRun.setTestStepId(testStep.getId());
                testStepTestRun.setCreatedUser(Constants.TEDAM_MANAGER);
                testStepTestRun.setDateCreated(LocalDateTime.now());
                testStepTestRunList.add(testStepTestRun);
            }
        }
        testCaseTestRun.setTestStepTestRunList(testStepTestRunList);
        testCaseTestRunService.save(testCaseTestRun);
    }

    private String getVersionParameterValue(Job job) throws VersionParameterValueException, LocalizedException {
        JobParameter jobParameter = jobParameterService.getJobParameterByProjectAndName(job.getProject(), Constants.SNAPSHOT_FORM_VERSION);
        JobParameterValue versionParameterValue = job.getJobEnvironment().getJobParameterValues().stream()
                .filter(jobParameterValue -> jobParameterValue.getJobParameterId().equals(jobParameter.getId())).findAny().orElse(null);
        if (versionParameterValue == null) {
            throw new VersionParameterValueException(job.getId() + " No version value was found for job with id.");
        }
        return versionParameterValue.getValue();
    }

}
