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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Where(clause = "IS_DELETED=0")
@Table(name = "COLUMN_PREFERENCE", indexes = @Index(columnList = "GRID_PREFERENCE_ID"))
public class ColumnPreference extends AbstractBaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "GRID_PREFERENCE_ID")
    private Integer gridPreferenceId = null;

    @Column(name = "COLUMN_ID", nullable = false)
    private String columnId = null;

    @Column(name = "IS_HIDDEN")
    private boolean hidden = false;

    @Column(name = "COLUMN_WIDTH")
    private double columnWidth = 0;

    /**
     * @return the gridPreferenceId
     */
    public Integer getGridPreferenceId() {
        return gridPreferenceId;
    }

    /**
     * @param gridPreferenceId the gridPreferenceId to set
     */
    public void setGridPreferenceId(Integer gridPreferenceId) {
        this.gridPreferenceId = gridPreferenceId;
    }

    /**
     * @return the columnId
     */
    public String getColumnId() {
        return columnId;
    }

    /**
     * @param columnId the columnId to set
     */
    public void setColumnId(String columnId) {
        this.columnId = columnId;
    }

    /**
     * @return the hidden
     */
    public boolean isHidden() {
        return hidden;
    }

    /**
     * @param hidden the hidden to set
     */
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    /**
     * @return the columnWidth
     */
    public double getColumnWidth() {
        return columnWidth;
    }

    /**
     * @param columnWidth the columnWidth to set
     */
    public void setColumnWidth(double columnWidth) {
        this.columnWidth = columnWidth;
    }

}
