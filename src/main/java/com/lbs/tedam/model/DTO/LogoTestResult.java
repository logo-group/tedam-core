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

package com.lbs.tedam.model.DTO;

import com.lbs.tedam.util.EnumsV2.ExecutionStatus;

import java.io.Serializable;

/**
 * @author Tarik.Mikyas
 * @since 20 June 2016 11:26:56
 */
public class LogoTestResult implements Serializable {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * String Name
     */
    private String Name;
    /**
     * TestResult Result
     */
    private ExecutionStatus Result;
    /**
     * String Description
     */
    private String Description;

    /**
     * @return <br>
     * this method getName
     * @author Tarik.Mikyas
     */
    public String getName() {
        return Name;
    }

    /**
     * @param name <br>
     *             this method setName
     * @author Tarik.Mikyas
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * @return <br>
     * this method getResult
     * @author Tarik.Mikyas
     */
    public ExecutionStatus getResult() {
        return Result;
    }

    /**
     * @param result <br>
     *               this method setResult
     * @author Tarik.Mikyas
     */
    public void setResult(ExecutionStatus result) {
        Result = result;
    }

    /**
     * @return <br>
     * this method getDescription
     * @author Tarik.Mikyas
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @param description <br>
     *                    this method setDescription
     * @author Tarik.Mikyas
     */
    public void setDescription(String description) {
        Description = description;
    }

    /**
     * this method getId <br>
     *
     * @return <br>
     * @author Canberk.Erkmen
     */
    public Integer getId() {
        return id;
    }

    /**
     * this method setId <br>
     *
     * @param id <br>
     * @author Canberk.Erkmen
     */
    public void setId(Integer id) {
        this.id = id;
    }

}
