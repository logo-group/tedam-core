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

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Where(clause = "IS_DELETED=0")
@Table(name = "FORM_DEFINITION")
public class FormDefinition extends AbstractBaseEntity {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = -4015461734962160034L;

    /**
     * String name
     */
    @Column(name = "NAME")
    private String name;
    /**
     * String mode
     */
    @Column(name = "MODE")
    private String mode;

    /**
     * List<FormField> formFields
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "FORM_DEFINITION_ID")
    @Where(clause = "IS_DELETED=0")
    private List<FormField> formFields = new ArrayList<FormField>();

    /**
     * List<Snapshot> snapshots
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "FORM_DEFINITION_ID")
    @Where(clause = "IS_DELETED=0")
    private List<Snapshot> snapshots = new ArrayList<Snapshot>();

    /**
     *
     */
    public FormDefinition() {
        super();
        init();
    }

    /**
     * @author Tarik.Mikyas <br>
     * this method initilize entities variables
     */
    private void init() {
        this.name = Constants.VALUE_NULL;
        this.mode = Constants.VALUE_NULL;
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
     * this method getMode
     * @author Tarik.Mikyas
     */
    public String getMode() {
        return mode;
    }

    /**
     * @param mode <br>
     *             this method setMode
     * @author Tarik.Mikyas
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * @return <br>
     * this method getFormFields
     * @author Tarik.Mikyas
     */
    public List<FormField> getFormFields() {
        return formFields;
    }

    /**
     * @param formFields <br>
     *                   this method setFormFields
     * @author Tarik.Mikyas
     */
    public void setFormFields(List<FormField> formFields) {
        this.formFields = formFields;
    }

    /**
     * @param formField <br>
     *                  this method addFormField
     * @author Tarik.Mikyas
     */
    public void addFormField(FormField formField) {
        this.formFields.add(formField);
    }

    /**
     * @param formFields <br>
     *                   this method addAllFormFields
     * @author Tarik.Mikyas
     */
    public void addAllFormFields(List<FormField> formFields) {
        this.formFields.addAll(formFields);
    }

    /**
     * @return <br>
     * this method getSnapshots
     * @author Tarik.Mikyas
     */
    public List<Snapshot> getSnapshots() {
        return snapshots;
    }

    /**
     * @param snapshots <br>
     *                  this method setSnapshots
     * @author Tarik.Mikyas
     */
    public void setSnapshots(List<Snapshot> snapshots) {
        this.snapshots = snapshots;
    }

    /**
     * @param snapshot <br>
     *                 this method addSnapshot
     * @author Tarik.Mikyas
     */
    public void addSnapshot(Snapshot snapshot) {
        this.snapshots.add(snapshot);
    }

    /**
     * @param snapshots <br>
     *                  this method addAllSnapshots
     * @author Tarik.Mikyas
     */
    public void addAllSnapshots(List<Snapshot> snapshots) {
        this.snapshots.addAll(snapshots);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.name + " - " + this.mode;
    }
}
