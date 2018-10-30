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

import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Where(clause = "IS_DELETED=0")
@Table(name = "SNAPSHOT_VALUE")
public class SnapshotValue extends AbstractBaseEntity {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 6630737221175124130L;

    /**
     * String caption
     */
    @Transient
    private String caption;

    /**
     * boolean isTempControl
     */
    @Transient
    private boolean isTempControl;

    /**
     * String tag
     */
    @Column(name = "TAG")
    private String tag;

    /**
     * String value
     */
    @Column(name = "VALUE", columnDefinition = "NVARCHAR(4000)")
    private String value;

    /**
     * String version
     */
    @Column(name = "VERSION")
    private String version;

    /**
     * boolean status
     */
    @Column(name = "STATUS", columnDefinition = "TINYINT", length = 1)
    private boolean status;

    /**
     * int rowIndex
     */
    @Column(name = "ROW_INDEX")
    private int rowIndex;

    /**
     * String type
     */
    @Column(name = "TYPE")
    private String type;

    /**
     * String parentTag
     */
    @Column(name = "PARENT_TAG")
    private String parentTag;

    /**
     * boolean lookUp
     */
    @Column(name = "HAS_LOOK_UP", columnDefinition = "TINYINT", length = 1)
    private boolean lookUp;

    /**
     * int order
     */
    @Column(name = "RUN_ORDER")
    private int order;

    /**
     * String lookUpParameter
     */
    @Column(name = "LOOK_UP_PARAMETER")
    private String lookUpParameter = null;

    /**
     * String dialogParameter
     */
    @Type(type = "text")
    @Column(name = "DIALOG_PARAMETER")
    private String dialogParameter;

    /**
     * boolean continueOnError
     */
    @Column(name = "CONTINUE_ON_ERROR", columnDefinition = "TINYINT", length = 1)
    private boolean continueOnError = true;

    /**
     * SnapshotDefinition snapshotDefinition
     */
    @Column(name = "SNAPSHOT_DEFINITION_ID")
    private Integer snapshotDefinitionId;

    /**
     *
     */
    public SnapshotValue() {
        // An empty constructor is needed for all beans
    }

    /**
     * @param caption
     * @param tag
     * @param value
     * @param version
     * @param status
     * @param rowIndex
     * @param type
     * @param parentTag
     * @param hasLookUp
     * @param order
     * @param creator
     * @param lookUpParameter
     * @param dialogParameter
     * @param continueOnError
     * @param snapshotDefinition
     */
    public SnapshotValue(String caption, String tag, String value, String version, boolean status, int rowIndex,
                         String type) {
        super();
        this.caption = caption;
        this.tag = tag;
        this.value = value;
        this.version = version;
        this.status = status;
        this.rowIndex = rowIndex;
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
     * this method getRowIndex
     * @author Tarik.Mikyas
     */
    public int getRowIndex() {
        return rowIndex;
    }

    /**
     * @param rowIndex <br>
     *                 this method setRowIndex
     * @author Tarik.Mikyas
     */
    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
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
     * this method isLookUp
     * @author Tarik.Mikyas
     */
    public boolean isLookUp() {
        return lookUp;
    }

    /**
     * @param lookUp <br>
     *               this method setLookUp
     * @author Tarik.Mikyas
     */
    public void setLookUp(boolean lookUp) {
        this.lookUp = lookUp;
    }

    /**
     * @return <br>
     * this method getOrder
     * @author Tarik.Mikyas
     */
    public int getOrder() {
        return order;
    }

    /**
     * @param order <br>
     *              this method setOrder
     * @author Tarik.Mikyas
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * @return <br>
     * this method getLookUpParameter
     * @author Tarik.Mikyas
     */
    public String getLookUpParameter() {
        return lookUpParameter;
    }

    /**
     * @param lookUpParameter <br>
     *                        this method setLookUpParameter
     * @author Tarik.Mikyas
     */
    public void setLookUpParameter(String lookUpParameter) {
        this.lookUpParameter = lookUpParameter;
    }

    /**
     * @return <br>
     * this method getDialogParameter
     * @author Tarik.Mikyas
     */
    public String getDialogParameter() {
        return dialogParameter;
    }

    /**
     * @param dialogParameter <br>
     *                        this method setDialogParameter
     * @author Tarik.Mikyas
     */
    public void setDialogParameter(String dialogParameter) {
        this.dialogParameter = dialogParameter;
    }

    /**
     * @return <br>
     * this method getContinueOnError
     * @author Tarik.Mikyas
     */
    public boolean getContinueOnError() {
        return continueOnError;
    }

    /**
     * @param continueOnError <br>
     *                        this method setContinueOnError
     * @author Tarik.Mikyas
     */
    public void setContinueOnError(boolean continueOnError) {
        this.continueOnError = continueOnError;
    }

    /**
     * @return the snapshotDefinitionId
     */
    public Integer getSnapshotDefinitionId() {
        return snapshotDefinitionId;
    }

    /**
     * @param snapshotDefinitionId the snapshotDefinitionId to set
     */
    public void setSnapshotDefinitionId(Integer snapshotDefinitionId) {
        this.snapshotDefinitionId = snapshotDefinitionId;
    }

    /**
     * @return <br>
     * this method isTempControl
     * @author Tarik.Mikyas
     */
    public boolean isTempControl() {
        return isTempControl;
    }

    /**
     * @param isTempControl <br>
     *                      this method setTempControl
     * @author Tarik.Mikyas
     */
    public void setTempControl(boolean isTempControl) {
        this.isTempControl = isTempControl;
    }

    /**
     * this method cloneSnapshotValue produces and returns a new object with the attributes of the given SnapshotValue.<br>
     *
     * @return <br>
     * @author Tarik.Mikyas
     */
    public SnapshotValue cloneSnapshotValue() {
        SnapshotValue snapshotValue = new SnapshotValue();
        SnapshotValue clonedSnapshotValue = setSnapshotCommonAttributes(snapshotValue);
        return clonedSnapshotValue;
    }

    /**
     * this method cloneSnapshotValue produces and returns a new object with the attributes of the given SnapshotValue with id<br>
     *
     * @return <br>
     * @author Tarik.Mikyas
     */
    public SnapshotValue cloneSnapshotValueWithId() {
        SnapshotValue snapshotValue = new SnapshotValue();
        snapshotValue.setId(getId());
        SnapshotValue clonedSnapshotValue = setSnapshotCommonAttributes(snapshotValue);
        return clonedSnapshotValue;
    }

    public SnapshotValue setSnapshotCommonAttributes(SnapshotValue clonedSnapshotValue) {
        clonedSnapshotValue.setCaption(this.caption);
        clonedSnapshotValue.setTag(this.tag);
        clonedSnapshotValue.setValue(this.value);
        clonedSnapshotValue.setVersion(this.version);
        clonedSnapshotValue.setStatus(this.status);
        clonedSnapshotValue.setRowIndex(this.rowIndex);
        clonedSnapshotValue.setType(this.type);
        clonedSnapshotValue.setParentTag(this.parentTag);
        clonedSnapshotValue.setLookUp(this.lookUp);
        clonedSnapshotValue.setOrder(this.order);
        clonedSnapshotValue.setLookUpParameter(this.lookUpParameter);
        clonedSnapshotValue.setDialogParameter(this.dialogParameter);
        clonedSnapshotValue.setContinueOnError(this.continueOnError);
        clonedSnapshotValue.setSnapshotDefinitionId(this.snapshotDefinitionId);
        clonedSnapshotValue.setDateCreated(this.dateCreated);
        clonedSnapshotValue.setDateUpdated(this.dateUpdated);
        return clonedSnapshotValue;
    }
}
