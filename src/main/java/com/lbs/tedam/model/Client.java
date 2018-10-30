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

/**
 * @author Tarik.Mikyas<br>
 */

/**
 * @author Canberk.Erkmen
 *
 */
@Entity
@Where(clause = "IS_DELETED=0")
@Table(name = "CLIENT")
public class Client extends AbstractBaseEntity {
    /** long serialVersionUID */
    private static final long serialVersionUID = 8661312830056616561L;

    /** String name */
    @Column(name = "NAME", unique = true)
    @Size(min = 1, max = 255)
    private String name;

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    @Where(clause = "IS_DELETED=0")
    private Project project;

    public Client() {
        // An empty constructor is needed for all beans
    }

    public Client(String name) {
        super();
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
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
     * @param project
     *            the project to set
     */
    public void setProject(Project project) {
        this.project = project;
    }

}
