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

import javax.persistence.*;

@Entity
@Table(name = "TESTSET_TESTCASE", indexes = {@Index(columnList = "TESTSET_ID")})
public class TestSetTestCase extends AbstractBaseEntity {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @Column(name = "POSITION")
    private Integer position;

    @Column(name = "TESTSET_ID")
    private Integer testSetId;

    // CascadeType.MERGE was removed because it received an error when trying to add the same TestCase to different test sets and create a job.
    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "TEST_CASE_ID")
    private TestCase testCase;

    public TestSetTestCase() {
    }

    public TestSetTestCase(Integer position, TestCase testCase) {
        this.position = position;
        this.testCase = testCase;
    }

    public TestSetTestCase(TestCase testCase) {
        this.testCase = testCase;
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

    public String getTestSetTestCaseName() {
        return testCase != null ? testCase.getName() : "";
    }

    public Integer getTestSetTestCaseId() {
        return testCase != null ? testCase.getId() : Integer.valueOf(0);
    }

}
