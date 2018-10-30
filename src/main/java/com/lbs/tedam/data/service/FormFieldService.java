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

import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.FormDefinition;
import com.lbs.tedam.model.FormField;
import org.w3c.dom.Element;

import javax.xml.xpath.XPathExpressionException;
import java.util.List;

public interface FormFieldService extends BaseService<FormField, Integer> {

    /**
     * @param version
     * @param formId
     * @return
     * @throws LocalizedException
     * @author Tarik.Mikyas
     * <p>
     * Only the given form id gets the formField list below.
     * @notUsed
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
    public List<FormField> getFieldsOfVersionsAndForms(String version, int formId, boolean status) throws LocalizedException;

    /**
     * @param id
     * @return
     * @throws LocalizedException
     * @author Tarik.Mikyas
     * <p>
     * Finds all past movements and returns them with the field in tag attached to the given id.
     */
    public List<FormField> getHistoryOfTag(int id) throws LocalizedException;

    /**
     * @param version
     * @param formId
     * @param status
     * @param parentTag
     * @return
     * @throws LocalizedException
     * @author Tarik.Mikyas
     * <p>
     * With given version and formid, it fetches the fields that the relevant form should be exposed to update.
     */
    public List<FormField> getControlFieldsOfVersionsAndForms(String version, int formId) throws LocalizedException;

    /**
     * @param snapshotValueId
     * @param tag
     * @return
     * @throws LocalizedException
     * @author Tarik.Mikyas
     * <p>
     * The SnapShotvalueId is used in TedamFace to return the formfield.Caption field with the Id and tag and the info.
     */
    public String getCaptionBySnapshotValue(Integer snapshotValueId, String tag) throws LocalizedException;

    public List<FormField> prepareFormFields(Element xmlDoc, FormDefinition formDefinition, boolean existenceCond, String version) throws XPathExpressionException, LocalizedException;

}
