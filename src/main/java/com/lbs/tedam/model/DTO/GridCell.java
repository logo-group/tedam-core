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

package com.lbs.tedam.model.DTO;

import java.io.Serializable;

/**
 * @author Ahmet.Izgi
 */
public class GridCell implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int rowIndex;
    private String tag;
    private String caption;
    private String value;

    public GridCell(String tag, String caption, String value, int rowIndex) {
        super();
        this.tag = tag;
        this.caption = caption;
        this.value = value;
        this.rowIndex = rowIndex;
    }

    public GridCell(String tag, String value) {
        super();
        this.tag = tag;
        this.value = value;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + rowIndex;
        result = prime * result + ((tag == null) ? 0 : tag.hashCode());
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
        GridCell other = (GridCell) obj;
        if (getRowIndex() != other.getRowIndex())
            return false;
        if (getTag() == null) {
            if (other.getTag() != null)
                return false;
        } else if (!getTag().equals(other.getTag()))
            return false;
        return true;
    }

    public GridCell cloneGridCell() {
        return new GridCell(this.getTag(), this.getCaption(), this.getValue(), this.getRowIndex());
    }

}
