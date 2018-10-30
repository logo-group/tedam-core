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

import com.lbs.tedam.util.EnumsV2.ExecutionStatus;
import com.lbs.tedam.util.EnumsV2.TestRunType;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Where(clause = "IS_DELETED=0")
@Table(name = "TESTCASE_TESTRUN", indexes = {@Index(columnList = "TESTCASE_ID"), @Index(columnList = "TESTSET_ID")})
public class TestCaseTestRun extends TestRun {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @Column(name = "TESTSET_ID")
    private Integer testSetId;

    @Column(name = "TESTCASE_ID")
    private Integer testCaseId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "TEST_CASE_TEST_RUN_ID")
    @Where(clause = "IS_DELETED=0")
    private List<TestStepTestRun> testStepTestRunList = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TESTCASE_ID", insertable = false, updatable = false)
    private TestCaseAtom testCase;

    @Column(name = "TESTSET_NAME")
    private String testSetName;

    @Column(name = "JOB_NAME")
    private String jobName;

    public TestCaseTestRun() {
    }

    public TestCaseTestRun(String clientName, String version, TestRunType testRunType, ExecutionStatus executionStatus, String executionMessage, LocalDateTime startDate,
                           LocalDateTime endDate) {
        super(version, testRunType, executionStatus, executionMessage, startDate, endDate);
        setClientName(clientName);
    }

    public List<TestStepTestRun> getTestStepTestRunList() {
        return testStepTestRunList;
    }

    public void setTestStepTestRunList(List<TestStepTestRun> testStepTestRunList) {
        this.testStepTestRunList = testStepTestRunList;
    }

    /**
     * @return the testCaseId
     */
    public Integer getTestCaseId() {
        return testCaseId;
    }

    /**
     * @param testCaseId the testCaseId to set
     */
    public void setTestCaseId(Integer testCaseId) {
        this.testCaseId = testCaseId;
    }

    /**
     * @return the testSetId
     */
    public Integer getTestSetId() {
        return testSetId;
    }

    /**
     * @param testSetId the testSetId to set
     */
    public void setTestSetId(Integer testSetId) {
        this.testSetId = testSetId;
    }

    public String getTestCaseName() {
        return testCase != null ? testCase.getName() : "";
    }

    public String getTestSetName() {
        return testSetName;
    }

    public void setTestSetName(String testSetName) {
        this.testSetName = testSetName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

}
