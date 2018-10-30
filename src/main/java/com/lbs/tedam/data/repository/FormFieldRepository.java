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

import com.lbs.tedam.model.FormField;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for entity FormField.
 */
public interface FormFieldRepository extends BaseRepository<FormField, Integer> {

    /**
     * Finds list of FormField due to parameters.
     *
     * @param version Version info.
     * @param formId  Form id value.
     * @return List of FormField.
     */
    public List<FormField> findByVersionAndFormDefinitionId(String version, int formId);

    /**
     * Finds list of FormField due to parameters.
     *
     * @param tag    Tag info.
     * @param formId Form id value.
     * @return List of FormField.
     */
    public List<FormField> findByTagAndFormDefinitionId(String tag, int formId);

    /**
     * Finds caption by given parameters.
     *
     * @param snapshotValueId Id of snapshot value.
     * @param tag             Tag value.
     * @return Caption of form field.
     */
//	@Query("select ff.caption from FormField ff " + "join FormDefinition fd on ff.formDefinitionId = fd.id " + "join SnapshotDefinition sd on fd." + "join sd.snapshotValues sv "
//			+ "where sv.id = :snapshotValueId and ff.tag = :tag")
    @Query("select ff.caption from SnapshotDefinition sd " + "join sd.snapshotValues sv " + "join sd.formDefinition fd " + "join fd.formFields ff "
            + "where sv.id = :snapshotValueId and ff.tag = :tag")
    public String getCaptionBySnapshotValue(@Param("snapshotValueId") Integer snapshotValueId, @Param("tag") String tag);

    /**
     * Finds records.
     *
     * @param version Versio info.
     * @param formId  Form id
     * @return List of FormFied.
     */
    @Query("select ff.tag, max(ff.version) from FormField ff where ff.version <= :version and ff.parentTag = :parentTag and ff.formDefinitionId = :formId group by ff.tag")
    public String[][] getControlFieldsOfVersionsAndForms(@Param("version") String version, @Param("formId") int formId, @Param("parentTag") String parentTag);

    /**
     * Finds records.
     *
     * @param version Versio info.
     * @param formId  Form id
     * @return List of FormFied.
     */
    @Query("select ff.tag, max(ff.version) from FormField ff where ff.version <= :version and ff.formDefinitionId = :formId group by ff.tag")
    public String[][] getFieldsOfVersionsAndForms(@Param("version") String version, @Param("formId") int formId);

    /**
     * Finds by form definition id.
     *
     * @param formDefinitionId Id of form definition.
     * @return List of FormFied.
     */
    public List<FormField> findByFormDefinitionId(int formDefinitionId);

    /**
     * Finds by form definition id.
     *
     * @param formDefinitionId Id of form definition.
     * @return List of FormFied.
     */
    public List<FormField> findByStatusAndFormDefinitionId(boolean status, int formDefinitionId);

}
