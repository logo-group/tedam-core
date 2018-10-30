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
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "TESTSET")
public class TestSet extends AbstractBaseEntity {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1279539030870882339L;

    @Column(name = "NAME", unique = true)
    @Size(min = 1, max = 255)
    private String name;

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    @Where(clause = "IS_DELETED=0")
    private Project project;

    @Column(name = "EXECUTION_DATE")
    private LocalDateTime executionDateTime;

    @Column(name = "TESTSET_STATUS")
    private CommandStatus testSetStatus = CommandStatus.NOT_STARTED;

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String description;

    @Column(name = "TESTSET_FOLDER_ID")
    private Integer testSetFolderId;

    @Column(name = "ACTUAL_DURATION")
    private Integer actualDuration;

    @Transient
    private boolean automated;

    @Transient
    private String jobListAsString;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "TESTSET_ID")
    @OrderBy("position ASC")
    private List<TestSetTestCase> testSetTestCases = new ArrayList<>();

    @Transient
    private String testSetFolder;

    @Transient
    private int failedTestCaseCount = 0;

    @Transient
    private int succeededTestCaseCount = 0;

    @Transient
    private int notRunTestCaseCount = 0;

    @Transient
    private int blockedTestCaseCount = 0;

    @Transient
    private int cautionTestCaseCount = 0;

    public TestSet() {
        // default TestSet constructor
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the project
     */
    public Project getProject() {
        return project;
    }

    /**
     * @param project the project to set
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * @return the executionDateTime
     */
    public LocalDateTime getExecutionDateTime() {
        return executionDateTime;
    }

    /**
     * @param executionDateTime the executionDateTime to set
     */
    public void setExecutionDateTime(LocalDateTime executionDateTime) {
        this.executionDateTime = executionDateTime;
    }

    /**
     * @return the testSetStatus
     */
    public CommandStatus getTestSetStatus() {
        return testSetStatus;
    }

    /**
     * @param testSetStatus the testSetStatus to set
     */
    public void setTestSetStatus(CommandStatus testSetStatus) {
        this.testSetStatus = testSetStatus;
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

    /**
     * @return the testSetFolderId
     */
    public Integer getTestSetFolderId() {
        return testSetFolderId;
    }

    /**
     * @param testSetFolderId the testSetFolderId to set
     */
    public void setTestSetFolderId(Integer testSetFolderId) {
        this.testSetFolderId = testSetFolderId;
    }

    /**
     * @return the actualDuration
     */
    public Integer getActualDuration() {
        return actualDuration;
    }

    /**
     * @param actualDuration the actualDuration to set
     */
    public void setActualDuration(Integer actualDuration) {
        this.actualDuration = actualDuration;
    }

    /**
     * @return the testSetTestCases
     */
    public List<TestSetTestCase> getTestSetTestCases() {
        return testSetTestCases;
    }

    /**
     * @param testSetTestCases the testSetTestCases to set
     */
    public void setTestSetTestCases(List<TestSetTestCase> testSetTestCases) {
        this.testSetTestCases = testSetTestCases;
    }

    /**
     * It sorts by test value in testSetTestCases and returns it as testCase list. <br>
     *
     * @return <br>
     * @author Canberk.Erkmen
     */
    public List<TestCase> getTestCasesOrdered() {
        return testSetTestCases.stream().sorted((tc1, tc2) -> tc1.getPosition().compareTo(tc2.getPosition())).map(tstc -> tstc.getTestCase()).collect(Collectors.toList());
    }

    public List<TestCase> getTestCases() {
        return testSetTestCases.stream().map(tstc -> tstc.getTestCase()).collect(Collectors.toList());
    }

    public String getTestSetFolder() {
        return testSetFolder;
    }

    public void setTestSetFolder(String testSetFolder) {
        this.testSetFolder = testSetFolder;
    }

    /**
     * this method isAutomated <br>
     *
     * @return <br>
     * @author Canberk.Erkmen
     */
    public boolean isAutomated() {
        for (TestSetTestCase testSetTestCase : testSetTestCases) {
            if (!testSetTestCase.getTestCase().isAutomated()) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param automated the automated to set
     */
    public void setAutomated(boolean automated) {
        this.automated = automated;
    }

    /**
     * @return the jobListAsString
     */
    public String getJobListAsString() {
        return jobListAsString;
    }

    /**
     * @param jobListAsString the jobListAsString to set
     */
    public void setJobListAsString(String jobListAsString) {
        this.jobListAsString = jobListAsString;
    }

    /**
     * @return the failedTestCaseCount
     */
    public int getFailedTestCaseCount() {
        return failedTestCaseCount;
    }

    /**
     * @param failedTestCaseCount the failedTestCaseCount to set
     */
    public void setFailedTestCaseCount(int failedTestCaseCount) {
        this.failedTestCaseCount = failedTestCaseCount;
    }

    /**
     * @return the succeededTestCaseCount
     */
    public int getSucceededTestCaseCount() {
        return succeededTestCaseCount;
    }

    /**
     * @param succeededTestCaseCount the succeededTestCaseCount to set
     */
    public void setSucceededTestCaseCount(int succeededTestCaseCount) {
        this.succeededTestCaseCount = succeededTestCaseCount;
    }

    /**
     * @return the notRunTestCaseCount
     */
    public int getNotRunTestCaseCount() {
        return notRunTestCaseCount;
    }

    /**
     * @param notRunTestCaseCount the notRunTestCaseCount to set
     */
    public void setNotRunTestCaseCount(int notRunTestCaseCount) {
        this.notRunTestCaseCount = notRunTestCaseCount;
    }

    /**
     * @return the blockedTestCaseCount
     */
    public int getBlockedTestCaseCount() {
        return blockedTestCaseCount;
    }

    /**
     * @param blockedTestCaseCount the blockedTestCaseCount to set
     */
    public void setBlockedTestCaseCount(int blockedTestCaseCount) {
        this.blockedTestCaseCount = blockedTestCaseCount;
    }

    /**
     * @return the cautionTestCaseCount
     */
    public int getCautionTestCaseCount() {
        return cautionTestCaseCount;
    }

    /**
     * @param cautionTestCaseCount the cautionTestCaseCount to set
     */
    public void setCautionTestCaseCount(int cautionTestCaseCount) {
        this.cautionTestCaseCount = cautionTestCaseCount;
    }

}
