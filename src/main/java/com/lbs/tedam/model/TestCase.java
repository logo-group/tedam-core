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
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TESTCASE")
public class TestCase extends AbstractBaseEntity {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 455667421219565288L;

    /**
     * String name
     */
    @Size(min = 1, max = 255)
    @Column(name = "NAME", unique = true)
    private String name;

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String description;

    @Column(name = "IS_AUTOMATED")
    private boolean automated = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROJECT_ID")
    @Where(clause = "IS_DELETED=0")
    private Project project;

    @Column(name = "TESTCASE_FOLDER_ID")
    private Integer testCaseFolderId;

    @Column(name = "EXECUTION_DATE")
    private LocalDateTime executionDateTime;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "TESTCASE_ID")
    @Where(clause = "IS_LOOK_UP=0 AND IS_DELETED=0")
    @OrderBy("position ASC")
    private List<TestStep> testSteps = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "TESTCASE_ID")
    @Where(clause = "IS_LOOK_UP=1 AND IS_DELETED=0")
    @OrderBy("position ASC")
    private List<TestStep> lookUps = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "TESTCASE_ID")
    @Where(clause = "IS_DELETED=0")
    private List<TestCaseTestRun> testCaseTestRunList = new ArrayList<>();

    @Transient
    private String testCaseFolder = "";

    @Transient
    private ExecutionStatus executionStatus = ExecutionStatus.NOTRUN;

    @Transient
    private String version = "";

    public TestCase() {
    }

    public TestCase(String name, String description, Project project, Integer testCaseFolderId) {
        super();
        this.name = name;
        this.description = description;
        this.project = project;
        this.testCaseFolderId = testCaseFolderId;
    }

    /**
     * @return the lookUps
     */
    public List<TestStep> getLookUps() {
        return lookUps;
    }

    /**
     * @param lookUps the lookUps to set
     */
    public void setLookUps(List<TestStep> lookUps) {
        this.lookUps = lookUps;
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
     * @return the automated
     */
    public boolean isAutomated() {
        return automated;
    }

    /**
     * @param automated the automated to set
     */
    public void setAutomated(boolean automated) {
        this.automated = automated;
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
     * @return the testSteps
     */
    public List<TestStep> getTestSteps() {
        return testSteps;
    }

    /**
     * @param testSteps the testSteps to set
     */
    public void setTestSteps(List<TestStep> testSteps) {
        this.testSteps = testSteps;
    }

    /**
     * @return the testCaseTestRunList
     */
    public List<TestCaseTestRun> getTestCaseTestRunList() {
        return testCaseTestRunList;
    }

    /**
     * @param testCaseTestRunList the testCaseTestRunList to set
     */
    public void setTestCaseTestRunList(List<TestCaseTestRun> testCaseTestRunList) {
        this.testCaseTestRunList = testCaseTestRunList;
    }

    /**
     * @return the executionStatus
     */
    public ExecutionStatus getExecutionStatus() {
        return executionStatus;

    }

    public void setExecutionStatus(ExecutionStatus executionStatus) {
        this.executionStatus = executionStatus;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the testCaseFolderId
     */
    public Integer getTestCaseFolderId() {
        return testCaseFolderId;
    }

    /**
     * @param testCaseFolderId the testCaseFolderId to set
     */
    public void setTestCaseFolderId(Integer testCaseFolderId) {
        this.testCaseFolderId = testCaseFolderId;
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

    public String getTestCaseFolder() {
        return testCaseFolder;
    }

    public void setTestCaseFolder(String testCaseFolder) {
        this.testCaseFolder = testCaseFolder;
    }

    public TestCase cloneTestCase() {
        TestCase newTestCase = new TestCase();
        newTestCase.setAutomated(automated);
        newTestCase.setDescription(description);
        newTestCase.setExecutionDateTime(executionDateTime);
        newTestCase.setName(generateName());
        newTestCase.setProject(project);
        newTestCase.setTestCaseFolderId(testCaseFolderId);
        testSteps.forEach(testStep -> {
            TestStep clonedTestStep = testStep.cloneTestStep();
            newTestCase.getTestSteps().add(clonedTestStep);
        });
        lookUps.forEach(lookUp -> {
            TestStep clonedLookUp = lookUp.cloneTestStep();
            newTestCase.getLookUps().add(clonedLookUp);
        });
        return newTestCase;
    }

    public String generateName() {
        String newName = name;
        String[] splittedName = newName.split("_");
        try {
            Integer value = Integer.parseInt(splittedName[splittedName.length - 1]);
            newName = newName.substring(0, newName.lastIndexOf('_')) + "_" + (++value);
        } catch (NumberFormatException e) {
            newName = newName + "_2";
        }
        return newName;
    }

}
