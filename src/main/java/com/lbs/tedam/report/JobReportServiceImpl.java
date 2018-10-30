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

import com.lbs.tedam.model.*;

import java.util.Iterator;
import java.util.Map;

public abstract class JobReportServiceImpl implements JobReportService {

    @Override
    public void addJobReportMap(JobReport jobReport, JobReportResult jobReportResult) {
        Map<JobReport, JobReportResult> jobReportMap = TedamReportFactory.getJobReportMap();
        jobReportMap.put(jobReport, jobReportResult);
    }

    @Override
    public void removeJobReportMap(int jobId) {
        Map<JobReport, JobReportResult> jobReportMap = TedamReportFactory.getJobReportMap();
        for (Iterator<JobReport> iterator = jobReportMap.keySet().iterator(); iterator.hasNext(); ) {
            JobReport jobReport = iterator.next();
            if (jobReport.getJobId() == jobId) {
                iterator.remove();
            }
        }
    }

    @Override
    public JobReport getJobReportByParams(TestCase testCase, JobDetail jobDetail) {
        JobReport jobReport = new JobReport();
        jobReport.setJobId(jobDetail.getJobId());
        jobReport.setTestSetId(jobDetail.getTestSet().getId());
        jobReport.setTestCaseId(testCase.getId());
        jobReport.setTestCaseName(testCase.getName());
        return jobReport;
    }

    @Override
    public JobReportResult getJobReportResultByParams(JobCommand jobCommand) {
        JobReportResult jobReportResult = new JobReportResult();
        jobReportResult.setDescription(jobCommand.getDescription());
        jobReportResult.setExecutionStatus(jobCommand.getExecutionStatus());
        return jobReportResult;
    }

}
