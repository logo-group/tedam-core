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

package com.lbs.tedam.report;

import com.lbs.tedam.data.config.DataConfig;
import com.lbs.tedam.data.service.JobDetailService;
import com.lbs.tedam.data.service.TestCaseService;
import com.lbs.tedam.data.service.TestDataConfig;
import com.lbs.tedam.data.service.impl.JobDetailServiceImpl;
import com.lbs.tedam.data.service.impl.TedamFolderServiceImpl;
import com.lbs.tedam.data.service.impl.TestCaseServiceImpl;
import com.lbs.tedam.data.service.impl.TestCaseTestRunServiceImpl;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.*;
import com.lbs.tedam.test.BaseServiceTest;
import com.lbs.tedam.util.Constants;
import com.lbs.tedam.util.EnumsV2.ExecutionStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestCaseTestRunServiceImpl.class, TedamFolderServiceImpl.class, JobXMLReportService.class, JobDetailServiceImpl.class, TestCaseServiceImpl.class, TestDataConfig.class, DataConfig.class})
public class JobXMLReportServiceTest extends BaseServiceTest {

    @Autowired
    private JobReportService jobReportService;

    @Autowired
    private JobDetailService jobDetailService;

    @Autowired
    private TestCaseService testCaseService;

    @Test
    public void testGetJobReportByParams() throws LocalizedException {
        JobDetail jobDetail = jobDetailService.getAll().get(0);
        TestCase testCase = testCaseService.getAll().get(0);
        JobCommand jobCommand = new JobCommand("windowsCommand", "unixCommand", testCase, jobDetail.getId(), null, "description");
        assertNotNull(jobReportService.getJobReportByParams(jobCommand.getTestCase(), jobDetail));
    }

    @Test
    public void testGetJobReportResultByParams() throws LocalizedException {
        JobDetail jobDetail = jobDetailService.getAll().get(0);
        TestCase testCase = testCaseService.getAll().get(0);
        JobCommand jobCommand = new JobCommand("windowsCommand", "unixCommand", testCase, jobDetail.getId(), null, "description");
        jobCommand.setExecutionStatus(ExecutionStatus.CAUTION);
        jobReportService.getJobReportResultByParams(jobCommand);
    }

    @Test
    public void testAddJobReportMap() {
        JobReport jobReport = new JobReport();
        jobReport.setJobId(1);
        jobReport.setTestCaseId(1);
        jobReport.setTestCaseName("testCaseName");
        jobReport.setTestSetId(1);

        JobReport jobReport1 = new JobReport();
        jobReport1.setJobId(2);
        jobReport1.setTestCaseId(2);
        jobReport1.setTestCaseName("testCaseName2");
        jobReport1.setTestSetId(2);

        JobReportResult jobReportResult = new JobReportResult();
        jobReportResult.setDescription("description");
        jobReportResult.setExecutionStatus(ExecutionStatus.FAILED);

        JobReportResult jobReportResult1 = new JobReportResult();
        jobReportResult1.setDescription("description1");
        jobReportResult1.setExecutionStatus(ExecutionStatus.CAUTION);

        jobReportService.addJobReportMap(jobReport, jobReportResult);
        jobReportService.addJobReportMap(jobReport1, jobReportResult1);
    }

    @Test
    public void testRemoveJobReportMap() {
        testAddJobReportMap();
        jobReportService.removeJobReportMap(1);
    }

    @Test
    public void testCreateJobReportFile() {
        testAddJobReportMap();
        jobReportService.createJobReportFile(2, getTempdir() + "tedam" + Constants.FILE_SEPARATOR + "jobreport.txt");
    }

    @Test
    public void testCreateJobReportFile1() {
        testAddJobReportMap();
        jobReportService.createJobReportFile(1, getTempdir() + "tedam" + Constants.FILE_SEPARATOR + "jobreport.txt");
    }

}
