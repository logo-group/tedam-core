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

import com.lbs.tedam.model.FormDefinition;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for entity FormDefinition.
 */
public interface FormDefinitonRepository extends BaseRepository<FormDefinition, Integer> {

    /**
     * Finds FormDefinition by name and mode parameters.
     *
     * @param name Name of form definition.
     * @param mode Mode of form definition.
     * @return If finds a new instance of FormDefinition, else null.
     */
    public FormDefinition findByNameAndMode(String name, String mode);

    /**
     * Finds list of form definition id that updated by version.
     *
     * @param version Version to find.
     * @return List of id.
     */
    @Query("select fd.id from FormDefinition fd where fd.id in (select ff.formDefinitionId from FormField ff where ff.version = :version group by ff.formDefinitionId)")
    public List<Integer> getUpdatedFormDefinitionIdList(@Param("version") String version);

}
