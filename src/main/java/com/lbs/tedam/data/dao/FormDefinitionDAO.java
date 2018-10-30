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
import com.lbs.tedam.model.FormDefinition;

import java.util.List;

/**
 * FormDefinitionService is the class for which the necessary database operations are centered.
 *
 * @author Tarik.Mikyas
 * @since 27 June 2016 16:34:37
 */
public interface FormDefinitionDAO extends BaseDAO<FormDefinition, Integer> {

    /**
     * Gets the @FormDefinition by name and code.
     *
     * @param name the name
     * @param mode the mode
     * @return the form def by name and code
     * @throws LocalizedException
     */
    FormDefinition getFormDefByNameAndMode(String name, String mode) throws LocalizedException;

    /**
     * Returns the IDs of the @FormDefinitions added or updated depending on the version received as a parameter.
     *
     * @param version
     * @return
     * @throws LocalizedException
     * @author Tarik.Mikyas
     */
    public List<Integer> getUpdatedFormDefinitionIdList(String version) throws LocalizedException;

}
