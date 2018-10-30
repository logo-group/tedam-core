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

import org.junit.Test;

public class GridPreferenceTest {

    /**
     * @author Seyma.Sahin
     */
    @Test
    public void testGetters() {
        GridPreference gridPreference = new GridPreference();
        gridPreference.getUserId();
        gridPreference.getViewId();
        gridPreference.getGridId();
        gridPreference.getColumnPreferenceList();
        gridPreference.getProjectId();
    }

    @Test
    public void testSetters() {
        GridPreference gridPreference = new GridPreference();
        gridPreference.setUserId(1);
        gridPreference.setViewId("ViewId");
        gridPreference.setGridId("GridId");
        gridPreference.setColumnPreferenceList(null);
        gridPreference.setProjectId(1);
    }
}
