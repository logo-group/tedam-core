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

import com.lbs.tedam.util.EnumsV2.TedamUserFavoriteType;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;

@Entity
@Table(name = "TEDAM_USER_FAVORITE")
public class TedamUserFavorite extends AbstractBaseEntity {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @Column(name = "TEDAM_USER_ID")
    private Integer tedamUserId;

    @Column(name = "FAVORITE_TYPE")
    private TedamUserFavoriteType favoriteType;

    @Column(name = "OBJECT_ID")
    private Integer objectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinFormula("(" + "select CLIENT.ID from CLIENT where CLIENT.ID = OBJECT_ID and FAVORITE_TYPE = 0" + ")")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinFormula("(" + "select JOB.ID from JOB where JOB.ID = OBJECT_ID and FAVORITE_TYPE = 1" + ")")
    private Job job;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinFormula("(" + "select ENVIRONMENT.ID from ENVIRONMENT where ENVIRONMENT.ID = OBJECT_ID and FAVORITE_TYPE = 2"
            + ")")
    private Environment environment;

    public TedamUserFavorite() {
    }

    public TedamUserFavorite(TedamUserFavoriteType favoriteType, Integer objectId) {
        this.favoriteType = favoriteType;
        this.objectId = objectId;
    }

    /**
     * @return the tedamUserId
     */
    public Integer getTedamUserId() {
        return tedamUserId;
    }

    /**
     * @param tedamUserId the tedamUserId to set
     */
    public void setTedamUserId(Integer tedamUserId) {
        this.tedamUserId = tedamUserId;
    }

    /**
     * @return the favoriteType
     */
    public TedamUserFavoriteType getFavoriteType() {
        return favoriteType;
    }

    /**
     * @param favoriteType the favoriteType to set
     */
    public void setFavoriteType(TedamUserFavoriteType favoriteType) {
        this.favoriteType = favoriteType;
    }

    /**
     * @return the objectId
     */
    public Integer getObjectId() {
        return objectId;
    }

    /**
     * @param objectId the objectId to set
     */
    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public String getName() {
        if (client != null) {
            return client.getName();
        }
        if (job != null) {
            return job.getName();
        }
        if (environment != null) {
            return environment.getName();
        }
        return "";
    }

}
