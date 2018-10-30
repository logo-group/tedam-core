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

package com.lbs.tedam.data.dao;

import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.SnapshotValue;

import java.util.List;

/**
 * SnapshotValue is the class for which the necessary database operations are centered.
 *
 * @author Tarik.Mikyas
 */
public interface SnapshotValueDAO extends BaseDAO<SnapshotValue, Integer> {

    /**
     * The latest version returns the SnapshotValue object.
     *
     * @param tag
     * @param snapshotDefinitionId
     * @return
     * @throws LocalizedException
     */
    public SnapshotValue getLatestVersionOfSnapshotValue(String tag, int snapshotDefinitionId) throws LocalizedException;

    /**
     * @param version
     * @param snapshotDefinitionId
     * @param scope                If it is true, it is fetched as rowIndex = -3 (filter fill values)<br>
     *                             If false, rowIndex <> -3 (form fill values) is returned<br>
     *                             If it is null, it is fetched without checking.
     * @return
     * @throws LocalizedException
     */
    public List<SnapshotValue> getSnapshotValuesVersioned(String version, int snapshotDefinitionId, Boolean scope) throws LocalizedException;

    /**
     * Returns the records created in the SnapshotValue table in the given version. <br>
     * WEB
     *
     * @param version
     * @return
     * @throws LocalizedException
     */
    public List<SnapshotValue> getSnapshotValueListByVersion(String version) throws LocalizedException;

    public List<SnapshotValue> findBySnapshotDefinitionId(int snapshotDefinitionId) throws LocalizedException;

    public List<SnapshotValue> findBySnapshotDefinitionIdAndRowIndexNot(int snapshotDefinitionId, Integer excluded) throws LocalizedException;

}
