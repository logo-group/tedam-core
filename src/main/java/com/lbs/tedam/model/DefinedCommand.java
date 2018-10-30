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

import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

@MappedSuperclass
public class DefinedCommand extends AbstractBaseEntity {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * String name
     */
    @Column(name = "NAME")
    @Size(min = 1, max = 255)
    private String name;

    /**
     * String windowsValue
     */
    @Column(name = "WINDOWS_VALUE", columnDefinition = "TEXT")
    @Size(min = 1, max = 500)
    private String windowsValue;

    /**
     * String unixValue
     */
    @Column(name = "UNIX_VALUE", columnDefinition = "TEXT")
    @Size(min = 1, max = 500)
    private String unixValue;

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    @Where(clause = "IS_DELETED=0")
    private Project project;

    /**
     * String firstExpectedResult
     */
    @Column(name = "FIRST_EXPECTED_RESULT")
    private String firstExpectedResult;

    /**
     * String lastExpectedResult
     */
    @Column(name = "LAST_EXPECTED_RESULT")
    private String lastExpectedResult;

    public DefinedCommand() {
        // An empty constructor is needed for all beans
    }

    public DefinedCommand(String name, String windowsValue, String unixValue, Project project,
                          String firstExpectedResult, String lastExpectedResult) {
        super();
        this.name = name;
        this.windowsValue = windowsValue;
        this.unixValue = unixValue;
        this.firstExpectedResult = firstExpectedResult;
        this.lastExpectedResult = lastExpectedResult;
        this.project = project;
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
     * @return the windowsValue
     */
    public String getWindowsValue() {
        return windowsValue;
    }

    /**
     * @param windowsValue the windowsValue to set
     */
    public void setWindowsValue(String windowsValue) {
        this.windowsValue = windowsValue;
    }

    /**
     * @return the unixValue
     */
    public String getUnixValue() {
        return unixValue;
    }

    /**
     * @param unixValue the unixValue to set
     */
    public void setUnixValue(String unixValue) {
        this.unixValue = unixValue;
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

}
