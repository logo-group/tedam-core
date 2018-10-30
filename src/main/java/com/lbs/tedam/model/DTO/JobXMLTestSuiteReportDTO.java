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

import java.util.ArrayList;
import java.util.List;

public class JobXMLTestSuiteReportDTO {

    private int testSetId = 0;

    private List<JobXMLTestCaseReportDTO> jobXMLTestCaseReportModelList = new ArrayList<>();

    /**
     * @return the testSetId
     */
    public int getTestSetId() {
        return testSetId;
    }

    /**
     * @param testSetId the testSetId to set
     */
    public void setTestSetId(int testSetId) {
        this.testSetId = testSetId;
    }

    /**
     * @return the jobXMLTestCaseReportModelList
     */
    public List<JobXMLTestCaseReportDTO> getJobXMLTestCaseReportModelList() {
        return jobXMLTestCaseReportModelList;
    }

    /**
     * @param jobXMLTestCaseReportModelList the jobXMLTestCaseReportModelList to set
     */
    public void setJobXMLTestCaseReportModelList(List<JobXMLTestCaseReportDTO> jobXMLTestCaseReportModelList) {
        this.jobXMLTestCaseReportModelList = jobXMLTestCaseReportModelList;
    }

}
