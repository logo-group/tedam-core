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

package com.lbs.tedam.data.service;

import com.lbs.tedam.exception.DifferencesSnapshotException;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.SnapshotValue;

import java.util.List;

public interface SnapshotValueService extends BaseService<SnapshotValue, Integer> {

    /**
     * @param snapshotDefinitionId
     * @param excluded             The values to be excluded are executed with "-3", "-1". If null, only snapshotDefinitionId is checked.
     * @return
     * @throws LocalizedException
     */
    public List<SnapshotValue> getSnapshotValueList(int snapshotDefinitionId, Integer excluded) throws LocalizedException;

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
     * @param orderingColumn       the column to be sorted is entered.
     * @param scope                If true then rowIndex = -3 (filter fill values); if false, rowIndex <> -3 (form fill values). If scope -1, rowIndex = -3
     *                             <br>
     *                             0 means no rowIndex control.<br>
     *                             if 1 rowIndex <> -3
     *                             <p>
     *                             The above 2 scope comments are for overloaded procedures that take different scope parameters.
     *                             <p>
     *                             If scope is Boolean.TRUE then rowIndex = -3 (filter fill values) <br>
     *                             If Boolean.FALSE then rowIndex <> -3 (form fill values) <br>
     *                             If it comes null, rowIndex will not be checked <br>
     * @return
     * @throws LocalizedException
     */
    public List<SnapshotValue> getSnapshotValuesVersioned(String version, int snapshotDefinitionId, String orderingColumn, Boolean scope) throws LocalizedException;

    /**
     * Returns the records created in the SnapshotValue table in the given version. <br>
     * WEB
     *
     * @param version
     * @return
     * @throws LocalizedException
     */
    public List<SnapshotValue> getSnapshotValueListByVersion(String version) throws LocalizedException;

    public List<SnapshotValue> getSnapshotValuesFromFile(String tcVersion, String fileAbsolutePath, int snapshotDefinitionId) throws DifferencesSnapshotException, LocalizedException;

}
