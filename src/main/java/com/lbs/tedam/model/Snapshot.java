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
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Where(clause = "IS_DELETED=0")
@Table(name = "SNAPSHOT")
public class Snapshot extends AbstractBaseEntity {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1362873919375709909L;

    /**
     * String xml
     */
    @Type(type = "text")
    @Column(name = "XML")
    private String xml;
    /**
     * String version
     */
    @Column(name = "VERSION")
    private String version;
    /**
     * String fileName
     */
    @Column(name = "FILE_NAME")
    private String fileName;

    /**
     * FormDefinition formDefinition
     */
    @Column(name = "FORM_DEFINITION_ID")
    private Integer formDefinitionId;

    /**
     *
     */
    public Snapshot() {
        super();
        init();
    }

    /**
     * @param xml
     * @param version
     * @param fileName
     * @param formDefinition
     */
    public Snapshot(String xml, String version, String fileName) {
        super();
        init();
        this.xml = xml;
        this.version = version;
        this.fileName = fileName;
    }

    /**
     * @author Tarik.Mikyas <br>
     * this method initiliaze variables
     */
    private void init() {
        this.fileName = Constants.VALUE_NULL;
        this.xml = Constants.VALUE_NULL;
        this.version = Constants.VALUE_NULL;
    }

    /**
     * @return <br>
     * this method getXml
     * @author Tarik.Mikyas
     */
    public String getXml() {
        return xml;
    }

    /**
     * @param xml <br>
     *            this method setXml
     * @author Tarik.Mikyas
     */
    public void setXml(String xml) {
        this.xml = xml;
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
     * this method getFileName
     * @author Tarik.Mikyas
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName <br>
     *                 this method setFileName
     * @author Tarik.Mikyas
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    /**
     * this method cloneSnapshot creates and returns a new object with the attributes of the given Snapshot.<br>
     *
     * @return <br>
     * @author Tarik.Mikyas
     */
    public Snapshot cloneSnapshot() {
        Snapshot clonedSnapshot = new Snapshot();
        clonedSnapshot.setFileName(fileName);
        clonedSnapshot.setFormDefinitionId(formDefinitionId);
        clonedSnapshot.setVersion(version);
        clonedSnapshot.setXml(xml);
        return clonedSnapshot;
    }

}
