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

import com.lbs.tedam.model.SnapshotDefinition;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for entity SnapshotDefinition.
 */
public interface SnapshotDefinitionRepository extends BaseRepository<SnapshotDefinition, Integer> {

    @Query("select sd from SnapshotDefinition sd where (sd.userId = :userId and sd.description like :description) or (sd.id like :description) ")
    public List<SnapshotDefinition> findByUserIdAndDescriptionLikeAndLookupNullOrIdLike(@Param("userId") String userId, @Param("description") String description);

    @Query("select sd from SnapshotDefinition sd where (sd.userId = :userId and sd.description like :description and ( substring(sd.description,10,:lookupTypeLength) = :lookType or substring(sd.description,17,:lookupTypeLength) = :lookType ) or (sd.id like :description)) ")
    public List<SnapshotDefinition> findByUserIdAndDescriptionLikeAndLookupNotNullOrIdLike(@Param("userId") String userId, @Param("description") String description,
                                                                                           @Param("lookupTypeLength") int lookupTypeLength, @Param("lookType") String lookType);

    @Query("select sd from SnapshotDefinition sd where (sd.userId = :userId and sd.description = :description) or (sd.id like :description) ")
    public List<SnapshotDefinition> findByUserIdAndDescriptionEqualAndLookupNullOrIdEquals(@Param("userId") String userId, @Param("description") String description);

    @Query("select sd from SnapshotDefinition sd where (sd.userId = :userId and sd.description = :description and ( substring(sd.description,10,:lookupTypeLength) = :lookType or substring(sd.description,17,:lookupTypeLength) = :lookType )) or (sd.id like :description) ")
    public List<SnapshotDefinition> findByUserIdAndDescriptionEqualAndLookupNotNullOrIdEquals(@Param("userId") String userId, @Param("description") String description,
                                                                                              @Param("lookupTypeLength") int lookupTypeLength, @Param("lookType") String lookType);

}
