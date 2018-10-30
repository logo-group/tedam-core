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

import com.lbs.tedam.generator.steptype.Generator;
import com.lbs.tedam.util.EnumsV2.ExecutionStatus;
import com.lbs.tedam.util.EnumsV2.TestStepType;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Where(clause = "IS_DELETED=0")
@Table(name = "TESTSTEP", indexes = {@Index(columnList = "TESTCASE_ID")})
public class TestStep extends AbstractBaseEntity {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * String description
     */
    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String description;

    /**
     * String description
     */
    @Column(name = "EXPECTED_RESULT", columnDefinition = "TEXT")
    private String expectedResult;

    /**
     * String type
     */
    @Column(name = "TYPE")
    private TestStepType type;

    /**
     * String parameter
     */
    @Column(name = "PARAMETER", columnDefinition = "TEXT")
    private String parameter;

    /**
     * String fileName
     */
    @Column(name = "FILENAME", columnDefinition = "TEXT")
    private String filename;

    /**
     * String expectedFormName
     */
    @Column(name = "EXPECTED_FORMNAME", columnDefinition = "TEXT")
    private String expectedFormname;

    /**
     * int position
     */
    @Column(name = "POSITION")
    private Integer position;

    /**
     * int position
     */
    @Column(name = "IS_LOOK_UP")
    private boolean lookUp = false;

    @Column(name = "TESTCASE_ID")
    private Integer testCaseId;

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    @Where(clause = "IS_DELETED=0")
    private Project project;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "TESTSTEP_ID")
    @Where(clause = "IS_DELETED=0")
    private List<TestStepTestRun> testStepTestRunList = new ArrayList<>();

    @Transient
    private ExecutionStatus executionStatus = ExecutionStatus.NOTRUN;

    @Transient
    private Generator generator;

    public TestStep() {
    }

    public TestStep(String description, String expectedResult, TestStepType type, String parameter, String filename,
                    String expectedFormname, Project project) {
        super();
        this.description = description;
        this.expectedResult = expectedResult;
        this.type = type;
        this.parameter = parameter;
        this.filename = filename;
        this.expectedFormname = expectedFormname;
        this.project = project;
    }

    /**
     * @return the lookUp
     */
    public boolean isLookUp() {
        return lookUp;
    }

    /**
     * @param lookUp the lookUp to set
     */
    public void setLookUp(boolean lookUp) {
        this.lookUp = lookUp;
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
     * @return the expectedResult
     */
    public String getExpectedResult() {
        return expectedResult;
    }

    /**
     * @param expectedResult the expectedResult to set
     */
    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    /**
     * @return the type
     */
    public TestStepType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(TestStepType type) {
        this.type = type;
    }

    /**
     * @return the parameter
     */
    public String getParameter() {
        return parameter;
    }

    /**
     * @param parameter the parameter to set
     */
    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    /**
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * @return the expectedFormname
     */
    public String getExpectedFormname() {
        return expectedFormname;
    }

    /**
     * @param expectedFormname the expectedFormname to set
     */
    public void setExpectedFormname(String expectedFormname) {
        this.expectedFormname = expectedFormname;
    }

    /**
     * @return the position
     */
    public Integer getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Integer position) {
        this.position = position;
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
     * @return the testStepTestRunList
     */
    public List<TestStepTestRun> getTestStepTestRunList() {
        return testStepTestRunList;
    }

    /**
     * @param testStepTestRunList the testStepTestRunList to set
     */
    public void setTestStepTestRunList(List<TestStepTestRun> testStepTestRunList) {
        this.testStepTestRunList = testStepTestRunList;
    }

    /**
     * @return the executionStatus
     */
    public ExecutionStatus getExecutionStatus() {
        if (getTestStepTestRunList().isEmpty()) {
            return ExecutionStatus.NOTRUN;
        }
        TestStepTestRun testStepTestRun = getTestStepTestRunList().get(0);
        for (TestStepTestRun tempTestStepTestRun : getTestStepTestRunList()) {
            if (testStepTestRun.getEndDate().isBefore(tempTestStepTestRun.getEndDate())) {
                testStepTestRun = tempTestStepTestRun;
            } else {
                continue;
            }
        }
        return testStepTestRun.getExecutionStatus();
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
     * @return the generator
     */
    public Generator getGenerator() {
        return generator;
    }

    /**
     * @param generator the generator to set
     */
    public void setGenerator(Generator generator) {
        this.generator = generator;
    }

    public TestStep cloneTestStep() {
        TestStep testStep = new TestStep(description, expectedResult, type, parameter, filename, expectedFormname,
                project);
        testStep.setPosition(position);
        testStep.setLookUp(lookUp);
        return testStep;
    }
}
