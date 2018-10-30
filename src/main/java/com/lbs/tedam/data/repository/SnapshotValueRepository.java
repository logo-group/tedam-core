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

package com.lbs.tedam.data.repository;

import com.lbs.tedam.model.SnapshotValue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for entity SnapshotValue.
 */
public interface SnapshotValueRepository extends BaseRepository<SnapshotValue, Integer> {

    public List<SnapshotValue> findBySnapshotDefinitionId(int snapshotDefinitionId);

    public List<SnapshotValue> findBySnapshotDefinitionIdAndRowIndexNot(int snapshotDefinitionId, Integer excluded);

    public List<SnapshotValue> findByVersionLike(String version);

    @Query("select sv.snapshotDefinitionId, sv.tag, max(sv.version) from SnapshotValue sv where sv.tag = :tag and sv.rowIndex = -1 group by sv.tag, sv.snapshotDefinitionId")
    public Object[][] findLatestVersionOfSnapshotValueByTag(@Param("tag") String tag);

    public List<SnapshotValue> findByRowIndexAndSnapshotDefinitionId(int rowIndex, int snapshotDefinitionID);

    @Query("select sv.tag, max(sv.version) from SnapshotValue sv where sv.version <= :version and sv.snapshotDefinitionId = :snapshotDefinitionId group by sv.tag")
    public Object[][] findSnapshotValuesVersioned(@Param("version") String version, @Param("snapshotDefinitionId") int snapshotDefinitionId);

    public List<SnapshotValue> findByStatusAndSnapshotDefinitionIdAndRowIndex(boolean status, int snapshotDefinitionID, int rowIndex);

    public List<SnapshotValue> findByStatusAndSnapshotDefinitionIdAndRowIndexNot(boolean status, int snapshotDefinitionID, int rowIndex);

    public List<SnapshotValue> findByStatusAndSnapshotDefinitionId(boolean status, int snapshotDefinitionID);

}
