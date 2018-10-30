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

public class JobXMLTestCaseReportDTO {

    private int testCaseId = 0;

    private String testCaseName = "";

    private JobXMLFailureReportDTO jobXMLFailureReportModel = null;

    /**
     * @return the testCaseId
     */
    public int getTestCaseId() {
        return testCaseId;
    }

    /**
     * @param testCaseId the testCaseId to set
     */
    public void setTestCaseId(int testCaseId) {
        this.testCaseId = testCaseId;
    }

    /**
     * @return the testCaseName
     */
    public String getTestCaseName() {
        return testCaseName;
    }

    /**
     * @param testCaseName the testCaseName to set
     */
    public void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }

    /**
     * @return the jobXMLFailureReportModel
     */
    public JobXMLFailureReportDTO getJobXMLFailureReportModel() {
        return jobXMLFailureReportModel;
    }

    /**
     * @param jobXMLFailureReportModel the jobXMLFailureReportModel to set
     */
    public void setJobXMLFailureReportModel(JobXMLFailureReportDTO jobXMLFailureReportModel) {
        this.jobXMLFailureReportModel = jobXMLFailureReportModel;
    }

}
