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

import com.lbs.tedam.model.Snapshot;

/**
 * Repository for entity Snapshot.
 */
public interface SnapshotRepository extends BaseRepository<Snapshot, Integer> {

    /**
     * Finds snapshot due to form definition id.
     *
     * @param formDefinitionId Form definition id of snapshot.
     * @return Snapshot instance or null.
     */
    public Snapshot findByFormDefinitionId(int formDefinitionId);

}