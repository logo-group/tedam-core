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
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Where(clause = "IS_DELETED=0")
@Table(name = "PROPERTY")
public class Property extends AbstractBaseEntity {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 8934952537945890832L;

    /**
     * String name
     */
    @Column(name = "NAME")
    private String name;

    /**
     * String parameter
     */
    @Column(name = "PARAMETER", unique = true)
    @Size(min = 1, max = 255)
    private String parameter;

    /**
     * String value
     */
    @Column(name = "VALUE")
    @Size(min = 1, max = 255)
    private String value;

    public Property() {
        // An empty constructor is needed for all beans
    }

    /**
     * @param name
     * @param parameter
     * @param value
     */
    public Property(String name, String parameter, String value) {
        super();
        this.name = name;
        this.parameter = parameter;
        this.value = value;
    }

    /**
     * @return <br>
     * this method getName
     * @author Tarik.Mikyas
     */
    public String getName() {
        return name;
    }

    /**
     * @param name <br>
     *             this method setName
     * @author Tarik.Mikyas
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return <br>
     * this method getParameter
     * @author Tarik.Mikyas
     */
    public String getParameter() {
        return parameter;
    }

    /**
     * @param parameter <br>
     *                  this method setParameter
     * @author Tarik.Mikyas
     */
    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    /**
     * @return <br>
     * this method getValue
     * @author Tarik.Mikyas
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value <br>
     *              this method setValue
     * @author Tarik.Mikyas
     */
    public void setValue(String value) {
        this.value = value;
    }

}
