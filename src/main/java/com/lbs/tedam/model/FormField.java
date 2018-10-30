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

import com.lbs.tedam.util.Constants;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Tarik.Mikyas<br>
 */
@Entity
@Where(clause = "IS_DELETED=0")
@Table(name = "FORM_FIELD")
public class FormField extends AbstractBaseEntity {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 2234910835662477079L;

    /**
     * String tag
     */
    @Column(name = "TAG")
    private String tag;

    /**
     * String type
     */
    @Column(name = "TYPE")
    private String type;

    /**
     * String caption
     */
    @Column(name = "CAPTION")
    private String caption;

    /**
     * String version
     */
    @Column(name = "VERSION")
    private String version;

    /**
     * boolean status
     */
    @Column(name = "STATUS", columnDefinition = "TINYINT", length = 1)
    private boolean status = true;

    /**
     * boolean mandatory
     */
    @Column(name = "MANDATORY", columnDefinition = "TINYINT", length = 1)
    private boolean mandatory = false;

    /**
     * String parentTag
     */
    @Column(name = "PARENT_TAG")
    private String parentTag;

    /**
     * int columnIndex
     */
    @Column(name = "COLUMN_INDEX")
    private int columnIndex;

    /**
     * String attribute
     */
    @Column(name = "ATTRIBUTE")
    private String attribute;

    /**
     * boolean hasLookUp
     */
    @Column(name = "HAS_LOOK_UP", columnDefinition = "TINYINT", length = 1)
    private boolean hasLookUp = false;

    @Column(name = "FORM_DEFINITION_ID")
    private Integer formDefinitionId;

    /**
     *
     */
    public FormField() {
        super();
        init();
    }

    /**
     * @author Tarik.Mikyas <br>
     * this method init
     */
    private void init() {
        this.attribute = Constants.VALUE_NULL;
        this.caption = Constants.VALUE_NULL;
        this.columnIndex = Constants.VALUE_NULL_INTEGER;
        this.parentTag = Constants.VALUE_NULL;
        this.tag = Constants.VALUE_NULL;
        this.type = Constants.VALUE_NULL;
        this.version = Constants.VALUE_NULL;
    }

    /**
     * @return <br>
     * this method getTag
     * @author Tarik.Mikyas
     */
    public String getTag() {
        return tag;
    }

    /**
     * @param tag <br>
     *            this method setTag
     * @author Tarik.Mikyas
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * @return <br>
     * this method getType
     * @author Tarik.Mikyas
     */
    public String getType() {
        return type;
    }

    /**
     * @param type <br>
     *             this method setType
     * @author Tarik.Mikyas
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return <br>
     * this method getCaption
     * @author Tarik.Mikyas
     */
    public String getCaption() {
        return caption;
    }

    /**
     * @param caption <br>
     *                this method setCaption
     * @author Tarik.Mikyas
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     * @return <br>
     * this method getVersion
     * @author Tarik.Mikyas
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version <br>
     *                this method setVersion
     * @author Tarik.Mikyas
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return <br>
     * this method getStatus
     * @author Tarik.Mikyas
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * @param status <br>
     *               this method setStatus
     * @author Tarik.Mikyas
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * @return <br>
     * this method getMandatory
     * @author Tarik.Mikyas
     */
    public boolean getMandatory() {
        return mandatory;
    }

    /**
     * @param mandatory <br>
     *                  this method setMandatory
     * @author Tarik.Mikyas
     */
    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    /**
     * @return <br>
     * this method getParentTag
     * @author Tarik.Mikyas
     */
    public String getParentTag() {
        return parentTag;
    }

    /**
     * @param parentTag <br>
     *                  this method setParentTag
     * @author Tarik.Mikyas
     */
    public void setParentTag(String parentTag) {
        this.parentTag = parentTag;
    }

    /**
     * @return <br>
     * this method getColumnIndex
     * @author Tarik.Mikyas
     */
    public int getColumnIndex() {
        return columnIndex;
    }

    /**
     * @param columnIndex <br>
     *                    this method setColumnIndex
     * @author Tarik.Mikyas
     */
    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    /**
     * @return <br>
     * this method getAttribute
     * @author Tarik.Mikyas
     */
    public String getAttribute() {
        return attribute;
    }

    /**
     * @param attribute <br>
     *                  this method setAttribute
     * @author Tarik.Mikyas
     */
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    /**
     * @return <br>
     * this method hasLookUp
     * @author Tarik.Mikyas
     */
    public boolean hasLookUp() {
        return hasLookUp;
    }

    /**
     * @param hasLookUp <br>
     *                  this method setLookUp
     * @author Tarik.Mikyas
     */
    public void setLookUp(boolean hasLookUp) {
        this.hasLookUp = hasLookUp;
    }

    /**
     * @return the formDefinitionId
     */
    public Integer getFormDefinitionId() {
        return formDefinitionId;
    }

    /**
     * @param formDefinitionId the formDefinitionId to set
     */
    public void setFormDefinitionId(Integer formDefinitionId) {
        this.formDefinitionId = formDefinitionId;
    }

}
