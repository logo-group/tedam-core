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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Where;

/**
 * @author Tarik.Mikyas<br>
 */
@Entity
@Where(clause = "IS_DELETED=0")
@Table(name = "JOB_PARAMETER_VALUE", uniqueConstraints = {@UniqueConstraint(columnNames = {"VALUE", "JOB_PARAMETER_ID"})})
public class JobParameterValue extends AbstractBaseEntity {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = -4628004863534993848L;

    /**
     * String value
     */
	@Column(name = "VALUE")
    @Size(min = 1, max = 255)
    private String value;

    @Column(name = "JOB_PARAMETER_ID")
    private Integer jobParameterId;

    public JobParameterValue() {
    }

    public JobParameterValue(String value, Integer jobParameterId) {
        this.value = value;
        this.jobParameterId = jobParameterId;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the jobParameterId
     */
    public Integer getJobParameterId() {
        return jobParameterId;
    }

    /**
     * @param jobParameterId the jobParameterId to set
     */
    public void setJobParameterId(Integer jobParameterId) {
        this.jobParameterId = jobParameterId;
    }

    @Override
    public String toString() {
        return value;
    }

}
