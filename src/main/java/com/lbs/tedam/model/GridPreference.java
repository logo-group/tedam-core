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
import java.util.ArrayList;
import java.util.List;

@Entity
@Where(clause = "IS_DELETED=0")
@Table(name = "GRID_PREFERENCE", indexes = @Index(columnList = "USER_ID, PROJECT_ID, VIEW_ID, GRID_ID"))
public class GridPreference extends AbstractBaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "USER_ID", nullable = false)
    private Integer userId = null;

    @Column(name = "PROJECT_ID", nullable = false)
    private Integer projectId = null;

    @Column(name = "VIEW_ID", nullable = false)
    private String viewId = null;

    @Column(name = "GRID_ID", nullable = false)
    private String gridId = null;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "GRID_PREFERENCE_ID")
    @Where(clause = "IS_DELETED=0")
    private List<ColumnPreference> columnPreferenceList = new ArrayList<ColumnPreference>();

    /**
     * @return the userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return the viewId
     */
    public String getViewId() {
        return viewId;
    }

    /**
     * @param viewId the viewId to set
     */
    public void setViewId(String viewId) {
        this.viewId = viewId;
    }

    /**
     * @return the gridId
     */
    public String getGridId() {
        return gridId;
    }

    /**
     * @param gridId the gridId to set
     */
    public void setGridId(String gridId) {
        this.gridId = gridId;
    }

    /**
     * @return the columnPreferenceList
     */
    public List<ColumnPreference> getColumnPreferenceList() {
        return columnPreferenceList;
    }

    /**
     * @param columnPreferenceList the columnPreferenceList to set
     */
    public void setColumnPreferenceList(List<ColumnPreference> columnPreferenceList) {
        this.columnPreferenceList = columnPreferenceList;
    }

    /**
     * @return the projectId
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * @param projectId the projectId to set
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

}
