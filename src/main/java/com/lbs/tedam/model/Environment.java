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

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Canberk.Erkmen<br>
 */
@Entity
@Where(clause = "IS_DELETED=0")
@Table(name = "ENVIRONMENT")
public class Environment extends AbstractBaseEntity {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 300276841746208067L;

    /**
     * String name
     */
    @Column(name = "NAME", unique = true)
    @Size(min = 1, max = 255)
    private String name;

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    @Where(clause = "IS_DELETED=0")
    private Project project;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Where(clause = "IS_DELETED=0")
    private List<JobParameterValue> jobParameterValues = new ArrayList<>();

    public Environment() {
    }

    public Environment(String name, Project project) {
        this.name = name;
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
     * @return the jobParameterValues
     */
    public List<JobParameterValue> getJobParameterValues() {
        return jobParameterValues;
    }

    /**
     * @param jobParameterValues the jobParameterValues to set
     */
    public void setJobParameterValues(List<JobParameterValue> jobParameterValues) {
        this.jobParameterValues = jobParameterValues;
    }

    @Override
    public String toString() {
        return name;
    }

}
