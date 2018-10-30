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

@Entity
@Where(clause = "IS_DELETED=0")
@Table(name = "SNAPSHOT_DEFINITION")
public class SnapshotDefinition extends AbstractBaseEntity {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 5971802261707788895L;

    /**
     * String description
     */
    @Column(name = "DESCRIPTION")
    @Size(min = 1, max = 255)
    private String description;
    /**
     * String userId
     */
    @Column(name = "USER_ID")
    private String userId;
    /**
     * int lookUp
     */
    @Column(name = "IS_LOOK_UP")
    private boolean lookUp;

    @Transient
    private String definitionType;

    /**
     * FormDefinition formDefinition
     */
    @ManyToOne
    @JoinColumn(name = "FORM_DEFINITION_ID")
    @Where(clause = "IS_DELETED=0")
    private FormDefinition formDefinition;

    /**
     * List<SnapshotValue> snapshotValues
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "SNAPSHOT_DEFINITION_ID")
    @OrderBy("order ASC")
    @Where(clause = "IS_DELETED=0")
    private List<SnapshotValue> snapshotValues = new ArrayList<SnapshotValue>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SNAPSHOT_ID")
    @Where(clause = "IS_DELETED=0")
    private Snapshot snapshot = new Snapshot();

    public SnapshotDefinition() {
        // SnapshotDefinition default constructor
    }

    /**
     * @return <br>
     * this method getDescription
     * @author Tarik.Mikyas
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description <br>
     *                    this method setDescription
     * @author Tarik.Mikyas
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return <br>
     * this method getUserId
     * @author Tarik.Mikyas
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId <br>
     *               this method setUserId
     * @author Tarik.Mikyas
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the lookUp
     */
    public boolean isLookUp() {
        return lookUp;
    }

    /**
     * @param lookUp the lookUp to set
     */
    public void setLookUp(boolean lookUp) {
        this.lookUp = lookUp;
    }

    /**
     * @return <br>
     * this method getFormDefinition
     * @author Tarik.Mikyas
     */
    public FormDefinition getFormDefinition() {
        return formDefinition;
    }

    /**
     * @param formDefinition <br>
     *                       this method setFormDefinition
     * @author Tarik.Mikyas
     */
    public void setFormDefinition(FormDefinition formDefinition) {
        this.formDefinition = formDefinition;
    }

    /**
     * @return <br>
     * this method getSnapshotValues
     * @author Tarik.Mikyas
     */
    public List<SnapshotValue> getSnapshotValues() {
        return snapshotValues;
    }

    /**
     * @param snapshotValues <br>
     *                       this method setSnapshotValues
     * @author Tarik.Mikyas
     */
    public void setSnapshotValues(List<SnapshotValue> snapshotValues) {
        this.snapshotValues = snapshotValues;
    }

    /**
     * @param snapshotValue <br>
     *                      this method addSnapshotValue
     * @author Tarik.Mikyas
     */
    public void addSnapshotValue(SnapshotValue snapshotValue) {
        this.snapshotValues.add(snapshotValue);
    }

    /**
     * @param snapshotValue <br>
     *                      this method removeSnapshotValue
     * @author Tarik.Mikyas
     */
    public void removeSnapshotValue(SnapshotValue snapshotValue) {
        this.snapshotValues.remove(snapshotValue);
    }

    /**
     * @param snapshotValues <br>
     *                       this method addAllSnapshotValues
     * @author Tarik.Mikyas
     */
    public void addAllSnapshotValues(List<SnapshotValue> snapshotValues) {
        this.snapshotValues.addAll(snapshotValues);
    }

    /**
     * @return <br>
     * this method getSnapshot
     * @author Tarik.Mikyas
     */
    public Snapshot getSnapshot() {
        return snapshot;
    }

    /**
     * @param snapshot <br>
     *                 this method setSnapshot
     * @author Tarik.Mikyas
     */
    public void setSnapshot(Snapshot snapshot) {
        this.snapshot = snapshot;
    }

    /**
     * @return the definitionType
     */
    public String getDefinitionType() {
        return definitionType;
    }

    /**
     * @param definitionType the definitionType to set
     */
    public void setDefinitionType(String definitionType) {
        this.definitionType = definitionType;
    }

    /**
     * this method cloneSnapshotDefinition creates and returns a new object with the attributes of the given SnapshotDefinition.<br>
     *
     * @return <br>
     * @author Tarik.Mikyas
     */
    public SnapshotDefinition cloneSnapshotDefinition() {
        SnapshotDefinition clonedSnapshotDefinition = new SnapshotDefinition();
        clonedSnapshotDefinition.setDescription(description);
        clonedSnapshotDefinition.setFormDefinition(formDefinition);
        clonedSnapshotDefinition.setLookUp(lookUp);
        clonedSnapshotDefinition.setSnapshot(snapshot);
        snapshotValues.forEach(snapshotValue -> {
            SnapshotValue clonedSnapshotValue = snapshotValue.cloneSnapshotValue();
            clonedSnapshotDefinition.getSnapshotValues().add(clonedSnapshotValue);
        });
        clonedSnapshotDefinition.setUserId(userId);
        return clonedSnapshotDefinition;
    }

    /**
     * this method cloneSnapshotDefinition creates and returns a new object with the attributes of the given SnapshotDefinition with id<br>
     *
     * @return <br>
     * @author Tarik.Mikyas
     */
    public SnapshotDefinition cloneSnapshotDefinitionWithId() {
        SnapshotDefinition clonedSnapshotDefinition = new SnapshotDefinition();
        clonedSnapshotDefinition.setId(getId());
        clonedSnapshotDefinition.setDescription(description);
        clonedSnapshotDefinition.setFormDefinition(formDefinition);
        clonedSnapshotDefinition.setLookUp(lookUp);
        clonedSnapshotDefinition.setSnapshot(snapshot);
        snapshotValues.forEach(snapshotValue -> {
            SnapshotValue clonedSnapshotValue = snapshotValue.cloneSnapshotValueWithId();
            clonedSnapshotDefinition.getSnapshotValues().add(clonedSnapshotValue);
        });
        clonedSnapshotDefinition.setUserId(userId);
        return clonedSnapshotDefinition;
    }

}
