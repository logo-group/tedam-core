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

import com.lbs.tedam.util.EnumsV2.ScriptAccessorOperationType;
import com.lbs.tedam.util.EnumsV2.ScriptAccessorType;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Canberk.Erkmen<br>
 */
@Entity
@Where(clause = "IS_DELETED=0")
@Table(name = "TEDAM_SCRIPT_ACCESSOR")
public class TedamScriptAccessor extends AbstractBaseEntity {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 300276841746208067L;

    /**
     * String scriptType
     */
    @Column(name = "SCRIPT_TYPE")
    private ScriptAccessorType scriptType;

    @Column(name = "OPERATION_COMMAND")
    private String operationCommand;

    @Column(name = "OPERATION_TYPE")
    private ScriptAccessorOperationType operationType;

    /**
     * String keyValue
     */
    @Column(name = "KEYWORD")
    private String keyword;

    public TedamScriptAccessor() {
        // An empty constructor is needed for all beans
    }

    public TedamScriptAccessor(ScriptAccessorType scriptType, ScriptAccessorOperationType operationType, String keyword, String operationCommand) {
        super();
        this.scriptType = scriptType;
        this.operationType = operationType;
        this.keyword = keyword;
        this.operationCommand = operationCommand;
    }

    /**
     * @return the scriptType
     */
    public ScriptAccessorType getScriptType() {
        return scriptType;
    }

    /**
     * @param scriptType the scriptType to set
     */
    public void setScriptType(ScriptAccessorType scriptType) {
        this.scriptType = scriptType;
    }

    /**
     * @return the operationType
     */
    public ScriptAccessorOperationType getOperationType() {
        return operationType;
    }

    /**
     * @param operationType the operationType to set
     */
    public void setOperationType(ScriptAccessorOperationType operationType) {
        this.operationType = operationType;
    }

    /**
     * this method getKeyword <br>
     *
     * @return <br>
     * @author Canberk.Erkmen
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * this method setKeyword <br>
     *
     * @param keyValue <br>
     * @author Canberk.Erkmen
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * this method getOperationCommand <br>
     *
     * @return <br>
     * @author Canberk.Erkmen
     */
    public String getOperationCommand() {
        return operationCommand;
    }

    /**
     * this method setOperationCommand <br>
     *
     * @param operationCommand <br>
     * @author Canberk.Erkmen
     */
    public void setOperationCommand(String operationCommand) {
        this.operationCommand = operationCommand;
    }
}
