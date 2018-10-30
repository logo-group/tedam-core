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

@Entity
@Where(clause = "IS_DELETED=0")
@Table(name = "TESTSTEP_TESTRUN", indexes = {@Index(columnList = "TESTSTEP_ID"), @Index(columnList = "TEST_CASE_TEST_RUN_ID")})
public class TestStepTestRun extends TestRun {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @Column(name = "TESTSTEP_ID")
    private Integer testStepId;

    @Column(name = "TEST_CASE_TEST_RUN_ID")
    private Integer testCaseTestRunId;

    @OneToOne
    @JoinColumn(name = "TESTSTEP_ID", insertable = false, updatable = false)
    private TestStepAtom testStep;

    public TestStepTestRun() {
    }

    public TestStepTestRun(String clientName, String version, TestRunType testRunType, ExecutionStatus executionStatus,
                           String executionMessage, LocalDateTime startDate,
                           LocalDateTime endDate) {
        super(version, testRunType, executionStatus, executionMessage, startDate, endDate);
        setClientName(clientName);
    }

    /**
     * @return the testStepId
     */
    public Integer getTestStepId() {
        return testStepId;
    }

    /**
     * @param testStepId the testStepId to set
     */
    public void setTestStepId(Integer testStepId) {
        this.testStepId = testStepId;
    }

    /**
     * @return the testCaseTestRunId
     */
    public Integer getTestCaseTestRunId() {
        return testCaseTestRunId;
    }

    /**
     * @param testCaseTestRunId the testCaseTestRunId to set
     */
    public void setTestCaseTestRunId(Integer testCaseTestRunId) {
        this.testCaseTestRunId = testCaseTestRunId;
    }

    public String getTestStepDescription() {
        return testStep != null ? testStep.getDescription() : "";
    }

    public String getTestStepType() {
        return testStep != null ? testStep.getType().toString() : "";
    }

}
