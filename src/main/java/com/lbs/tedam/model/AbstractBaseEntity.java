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

import com.lbs.tedam.util.EnumsV2.TedamBoolean;
import com.lbs.tedam.util.HasLogger;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Tarik.Mikyas<br>
 * <p>
 * Base class where all entities are extended.
 */
@MappedSuperclass
public class AbstractBaseEntity implements Serializable, HasLogger {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = -8084551277209729852L;
    /**
     * String dateCreated
     */
    @Column(name = "CREATED_DATE")
    protected LocalDateTime dateCreated = LocalDateTime.now();
    /**
     * String dateUpdated
     */
    @Column(name = "UPDATED_DATE")
    protected LocalDateTime dateUpdated;
    /**
     * String createdUser
     */
    @Column(name = "CREATED_USER")
    protected String createdUser;
    /**
     * String updatedUser
     */
    @Column(name = "UPDATED_USER")
    protected String updatedUser;
    /**
     * boolean deleted
     */
    @Column(name = "IS_DELETED")
    protected boolean deleted = TedamBoolean.FALSE.getBooleanValue();
    /**
     * Integer id
     */
    @Id
    @GeneratedValue(generator = "IdOrGenerated")
    @GenericGenerator(name = "IdOrGenerated", strategy = "com.lbs.tedam.generator.UseIdOrGenerate")
    @Column(name = "ID")
    private Integer id = 0;

    public AbstractBaseEntity() {
        // abstract base entity constructor
    }

    public void updateCreationData(String createdUser, String updatedUser, LocalDateTime dateCreated, LocalDateTime dateUpdated) {
        setCreatedUser(createdUser);
        setUpdatedUser(updatedUser);
        setDateCreated(dateCreated);
        setDateUpdated(dateUpdated);
    }

    public boolean isNew() {
        return id == 0;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the dateCreated
     */
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * @return the dateUpdated
     */
    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    /**
     * @param dateUpdated the dateUpdated to set
     */
    public void setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    /**
     * @return the createdUser
     */
    public String getCreatedUser() {
        return createdUser;
    }

    /**
     * @param createdUser the createdUser to set
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    /**
     * @return the updatedUser
     */
    public String getUpdatedUser() {
        return updatedUser;
    }

    /**
     * @param updatedUser the updatedUser to set
     */
    public void setUpdatedUser(String updatedUser) {
        this.updatedUser = updatedUser;
    }

    /**
     * @return the deleted
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * @param deleted the deleted to set
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractBaseEntity other = (AbstractBaseEntity) obj;
        if (getId() == 0 && other.getId() == 0) {
            if (System.identityHashCode(this) == System.identityHashCode(other)) {
                return true;
            }
            return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    /**
     * @author Tarik.Mikyas
     * @return<br> for control of transient fields
     */
    @Transient
    public String getTransitVariableDescription() {
        return "";
    }

}