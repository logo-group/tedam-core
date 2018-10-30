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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ahmet.Izgi
 */
public class GridRow {
    private int rowIndex;
    private List<GridCell> cells;

    public GridRow() {
        rowIndex = 0;
        cells = new ArrayList<GridCell>();
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public List<GridCell> getCells() {
        return cells;
    }

    public void setCells(List<GridCell> cells) {
        this.cells = cells;
    }

    public void add(GridCell cell) {
        this.cells.add(cell);
    }

    public int getSize() {
        return this.cells.size();
    }

}
