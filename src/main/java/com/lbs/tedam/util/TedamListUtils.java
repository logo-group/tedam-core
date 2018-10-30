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

package com.lbs.tedam.util;

import com.lbs.tedam.model.FormField;
import com.lbs.tedam.model.SnapshotValue;

import java.util.List;

public class TedamListUtils {

    private TedamListUtils() {
        // TedamListUtils private constructor
    }

    /**
     * In the given Snapshot_Values list, the given value is returned as a tag and returns the index.
     *
     * @param values Snapshot_Values list
     * @param tag    Search value
     * @return If the retrieved tag value is found, it returns -1 if no index is found.
     * @author Ahmet.Izgi
     */
    public static int getTagIndexInList(List<SnapshotValue> values, String tag) {
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i).getTag().equals(tag)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * In the given Snapshot_Values list, the given value is returned as a tag and returns the index.
     *
     * @param fields Snapshot_Values list
     * @param tag    Search value
     * @return If the retrieved tag value is found, it returns -1 if no index is found.
     * @author Ahmet.Izgi
     */
    public static int getTagIndexInFieldList(List<FormField> fields, String tag) {
        for (int i = 0; i < fields.size(); i++) {
            if (fields.get(i).getTag().equals(tag)) {
                return i;
            }
        }
        return -1;
    }
}
