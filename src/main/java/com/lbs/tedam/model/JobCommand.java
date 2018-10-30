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

import com.lbs.tedam.util.EnumsV2.CommandStatus;
import com.lbs.tedam.util.EnumsV2.ExecutionStatus;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * @author Tarik.Mikyas<br>
 */
@Entity
@Where(clause = "IS_DELETED=0")
@Table(name = "JOB_COMMAND")
public class JobCommand extends AbstractBaseEntity {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 6891240727075019647L;

    /**
     * String windowsCommand
     */
    @Column(name = "WINDOWS_COMMAND", columnDefinition = "TEXT")
    private String windowsCommand;

    /**
     * String unixCommand
     */
    @Column(name = "UNIX_COMMAND", columnDefinition = "TEXT")
    private String unixCommand;

    @ManyToOne
    @JoinColumn(name = "TEST_CASE_ID")
    @Where(clause = "IS_DELETED=0")
    private TestCase testCase;

    @Column(name = "JOB_DETAIL_ID")
    private Integer jobDetailId;

    /**
     * DraftCommand draftCommand
     */
    @ManyToOne
    @JoinColumn(name = "DRAFT_COMMAND_ID")
    @Where(clause = "IS_DELETED=0")
    private DraftCommand draftCommand;

    /**
     * Integer commandStatus
     */
    @Column(name = "COMMAND_STATUS")
    private CommandStatus commandStatus = CommandStatus.NOT_STARTED;

    /**
     * int executionStatus
     */
    @Column(name = "EXECUTION_STATUS")
    private ExecutionStatus executionStatus = ExecutionStatus.NOTRUN;

    /**
     * String description
     */
    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String description;

    public JobCommand() {
        // An empty constructor is needed for all beans
    }

    public JobCommand(String windowsCommand, String unixCommand, TestCase testCase, Integer jobDetailId, DraftCommand draftCommand, String description) {
        super();
        this.windowsCommand = windowsCommand;
        this.unixCommand = unixCommand;
        this.testCase = testCase;
        this.jobDetailId = jobDetailId;
        this.draftCommand = draftCommand;
        this.description = description;
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
     * @return the testCase
     */
    public TestCase getTestCase() {
        return testCase;
    }

    /**
     * @param testCase the testCase to set
     */
    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    /**
     * @return the jobDetailId
     */
    public Integer getJobDetailId() {
        return jobDetailId;
    }

    /**
     * @param jobDetailId the jobDetailId to set
     */
    public void setJobDetailId(Integer jobDetailId) {
        this.jobDetailId = jobDetailId;
    }

    /**
     * @return the draftCommand
     */
    public DraftCommand getDraftCommand() {
        return draftCommand;
    }

    /**
     * @param draftCommand the draftCommand to set
     */
    public void setDraftCommand(DraftCommand draftCommand) {
        this.draftCommand = draftCommand;
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public JobCommand cloneJobCommand() {
        return new JobCommand(windowsCommand, unixCommand, testCase, jobDetailId, draftCommand, description);
    }
}
