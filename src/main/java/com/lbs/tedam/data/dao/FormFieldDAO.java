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
import com.lbs.tedam.model.FormField;

import java.util.List;

/**
 * FormFieldService is the class to which the necessary database operations are based.
 *
 * @author Tarik.Mikyas
 */
public interface FormFieldDAO extends BaseDAO<FormField, Integer> {

    /**
     * It only returns the formField list of the given version.
     *
     * @param version
     * @param formId
     * @return
     * @throws LocalizedException
     */
    public List<FormField> getFormFieldListByVersionAndFormDefId(String version, int formId) throws LocalizedException;

    /**
     * @param version
     * @param formId
     * @param status
     * @return
     * @throws LocalizedException
     * @author Tarik.Mikyas
     * <p>
     * With given version and formid, it fetches fields that can be used for that version of the form.
     */
    public String[][] getFieldsOfVersionsAndForms(String version, int formId) throws LocalizedException;

    /**
     * @param int formDefinitionId
     * @param int tag
     * @return
     * @throws LocalizedException
     * @author Tarik.Mikyas
     * <p>
     * Returns the returned formDefinitionId and all past movements attached to the tag.
     */
    public List<FormField> getHistoryOfTag(int formDefinitionId, String tag) throws LocalizedException;

    /**
     * @param version
     * @param formId
     * @param status
     * @param parentTag
     * @return
     * @throws LocalizedException
     * @author Tarik.Mikyas
     * <p>
     * With the given version and formid, it fetches the fields that the relevant form needs to be updated.
     */
    public String[][] getControlFieldsOfVersionsAndForms(String version, int formId) throws LocalizedException;

    /**
     * @param snapshotValueId
     * @param tag
     * @return
     * @throws LocalizedException
     * @author Tarik.Mikyas
     * <p>
     * The snapshotvalueId is used in TedamFace to return the formfield.caption field with the Id and tag and the info.
     */
    public String getCaptionBySnapshotValue(Integer snapshotValueId, String tag) throws LocalizedException;

    public List<FormField> findByFormDefinitionId(int formId) throws LocalizedException;

    public List<FormField> findByFormDefinitionIdAndStatus(int formId, boolean status) throws LocalizedException;

}
