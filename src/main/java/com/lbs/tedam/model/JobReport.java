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

/**
 * @author Canberk.Erkmen<br>
 */
public class JobReport {

    private int jobId = 0;

    private int testSetId = 0;

    private int testCaseId = 0;

    private String testCaseName = "";

    /**
     * @return the jobId
     */
    public int getJobId() {
        return jobId;
    }

    /**
     * @param jobId the jobId to set
     */
    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

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

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + jobId;
        result = prime * result + testCaseId;
        result = prime * result + testSetId;
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        JobReport other = (JobReport) obj;
        if (jobId != other.jobId)
            return false;
        if (testCaseId != other.testCaseId)
            return false;
        if (testSetId != other.testSetId)
            return false;
        return true;
    }
}
