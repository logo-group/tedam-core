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

import com.lbs.tedam.model.Environment;
import com.lbs.tedam.test.BaseServiceTest;
import org.junit.Test;

public class GridCellTest extends BaseServiceTest {

    /**
     * this method test02GettersAndSetters <br>
     *
     * @author Tarik.Mikyas <br>
     */
    @Test
    public void test02GettersAndSetters() {
        GridCell gridCell = new GridCell("tag", "caption", "value", 2);
        gridCell.setCaption("caption");
        gridCell.setRowIndex(2);
        gridCell.setTag("tag");
        gridCell.setValue("value");
        gridCell.getCaption();
        gridCell.getRowIndex();
        gridCell.getTag();
        gridCell.getValue();
        gridCell.hashCode();
        GridCell clonedGridCell = gridCell.cloneGridCell();
        gridCell.equals(clonedGridCell);
    }

    @Test
    public void testEqualsControl() {
        GridCell gridCell2 = new GridCell(null, null);
        GridCell gridCell = new GridCell(null, "caption2", "value2", 1);
        gridCell.hashCode();
        GridCell gridCell1 = new GridCell("tag", "caption", "value", 1);
        gridCell.equals(gridCell);
        gridCell.equals(null);
        gridCell.equals(new Environment());
        gridCell.equals(gridCell1);
        gridCell1.setTag(null);
        gridCell.equals(gridCell1);
        gridCell1.setTag("tag");
        gridCell.setTag("tag2");
        gridCell.hashCode();
        gridCell.equals(gridCell1);
        gridCell.setRowIndex(2);
        gridCell.equals(gridCell1);
        gridCell = new GridCell("tag", "caption", "value", 1);
        gridCell.equals(gridCell1);

    }

}
