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

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@MappedSuperclass
public class TestRun extends AbstractBaseEntity {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * String expectedFormName
     */
    @Column(name = "VERSION")
    private String version;

    /**
     * String expectedFormName
     */
    @Column(name = "TEST_RUN_TYPE")
    private TestRunType testRunType;

    /**
     * String expectedFormName
     */
    @Column(name = "EXECUTION_STATUS")
    private ExecutionStatus executionStatus;

    /**
     * String expectedFormName
     */
    @Column(name = "EXECUTION_MESSAGE", columnDefinition = "TEXT")
    private String executionMessage;

    /**
     * String expectedFormName
     */
    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    /**
     * String expectedFormName
     */
    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @Column(name = "CLIENT_NAME")
    private String clientName;

    public TestRun() {
    }

    public TestRun(String version, TestRunType testRunType, ExecutionStatus executionStatus, String executionMessage, LocalDateTime startDate, LocalDateTime endDate) {
        super();
        this.version = version;
        this.testRunType = testRunType;
        this.executionStatus = executionStatus;
        this.executionMessage = executionMessage;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the testRunType
     */
    public TestRunType getTestRunType() {
        return testRunType;
    }

    /**
     * @param testRunType the testRunType to set
     */
    public void setTestRunType(TestRunType testRunType) {
        this.testRunType = testRunType;
    }

    /**
     * @return the executionStatus
     */
    public ExecutionStatus getExecutionStatus() {
        return executionStatus;
    }

    /**
     * @param executionStatus the executionStatus to set
     */
    public void setExecutionStatus(ExecutionStatus executionStatus) {
        this.executionStatus = executionStatus;
    }

    /**
     * @return the executionMessage
     */
    public String getExecutionMessage() {
        return executionMessage;
    }

    /**
     * @param executionMessage the executionMessage to set
     */
    public void setExecutionMessage(String executionMessage) {
        this.executionMessage = executionMessage;
    }

    /**
     * @return the startDate
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * @return the actualDuration
     */
    public Integer getActualDuration() {
        if (getEndDate() != null && getStartDate() != null && getEndDate().isAfter(getStartDate())) {
            Long until = getStartDate().until(getEndDate(), ChronoUnit.MINUTES);
            return until.intValue();
        }
        return Integer.valueOf(0);
    }

}
