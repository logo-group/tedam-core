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

import com.lbs.tedam.model.DTO.LogoTestResult;
import com.lbs.tedam.util.EnumsV2.CommandStatus;
import com.lbs.tedam.util.EnumsV2.ExecutionStatus;
import com.lbs.tedam.util.EnumsV2.RunOrder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JobRunnerDetailCommand implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer jobCommandId;

    private String windowsCommand;

    private String unixCommand;

    private String draftCommandName;

    private RunOrder runOrder;

    private String firstExpectedResult;

    private String lastExpectedResult;

    private CommandStatus commandStatus;

    private ExecutionStatus executionStatus;

    private Integer testCaseId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private List<LogoTestResult> testResultList = new ArrayList<>();

    /**
     * @return the jobCommandId
     */
    public Integer getJobCommandId() {
        return jobCommandId;
    }

    /**
     * @param jobCommandId the jobCommandId to set
     */
    public void setJobCommandId(Integer jobCommandId) {
        this.jobCommandId = jobCommandId;
    }

    /**
     * @return the windowsCommand
     */
    public String getWindowsCommand() {
        return windowsCommand;
    }

    /**
     * @param windowsCommand the windowsCommand to set
     */
    public void setWindowsCommand(String windowsCommand) {
        this.windowsCommand = windowsCommand;
    }

    /**
     * @return the unixCommand
     */
    public String getUnixCommand() {
        return unixCommand;
    }

    /**
     * @param unixCommand the unixCommand to set
     */
    public void setUnixCommand(String unixCommand) {
        this.unixCommand = unixCommand;
    }

    /**
     * @return the draftCommandName
     */
    public String getDraftCommandName() {
        return draftCommandName;
    }

    /**
     * @param draftCommandName the draftCommandName to set
     */
    public void setDraftCommandName(String draftCommandName) {
        this.draftCommandName = draftCommandName;
    }

    /**
     * @return the runOrder
     */
    public RunOrder getRunOrder() {
        return runOrder;
    }

    /**
     * @param runOrder the runOrder to set
     */
    public void setRunOrder(RunOrder runOrder) {
        this.runOrder = runOrder;
    }

    /**
     * @return the firstExpectedResult
     */
    public String getFirstExpectedResult() {
        return firstExpectedResult;
    }

    /**
     * @param firstExpectedResult the firstExpectedResult to set
     */
    public void setFirstExpectedResult(String firstExpectedResult) {
        this.firstExpectedResult = firstExpectedResult;
    }

    /**
     * @return the lastExpectedResult
     */
    public String getLastExpectedResult() {
        return lastExpectedResult;
    }

    /**
     * @param lastExpectedResult the lastExpectedResult to set
     */
    public void setLastExpectedResult(String lastExpectedResult) {
        this.lastExpectedResult = lastExpectedResult;
    }

    /**
     * @return the commandStatus
     */
    public CommandStatus getCommandStatus() {
        return commandStatus;
    }

    /**
     * @param commandStatus the commandStatus to set
     */
    public void setCommandStatus(CommandStatus commandStatus) {
        this.commandStatus = commandStatus;
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

    /**
     * @return the testResultList
     */
    public List<LogoTestResult> getTestResultList() {
        return testResultList;
    }

    /**
     * @param testResultList the testResultList to set
     */
    public void setTestResultList(List<LogoTestResult> testResultList) {
        this.testResultList = testResultList;
    }

}
