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
import com.lbs.tedam.model.Property;

import java.util.List;

/**
 * @author Tarik.Mikyas
 * @since 11 January 2016 10:33:51
 */
public interface PropertyService extends BaseService<Property, Integer> {

    /**
     * This method is an example of getSpecialList for param in this procedure. it is not used anywhere.
     *
     * @param param
     * @return
     * @throws LocalizedException
     * @author Tarik.Mikyas
     * @return_type List<String>
     * @since 11 January 2016 10:45:10
     */
    public List<Property> getPropertyListByValue(String value) throws LocalizedException;

    /**
     * Returns the Property object dependent on the parameters.
     *
     * @param name
     * @param parameter
     * @return
     * @throws LocalizedException
     * @author Tarik.Mikyas
     * @since 22 June 2016 10:33:44
     */
    public Property getPropertyByNameAndParameter(String name, String parameter) throws LocalizedException;

    /**
     * @param name
     * @param parameter
     * @param useLike   If TRUE, it returns the parameter value (like "parameter%"). If FALSE is given, it is dialed with "=".
     * @return
     * @throws LocalizedException
     * @author Ahmet.Izgi
     */
    public List<Property> getPropertyListByCriteria(String name, String parameter, boolean useLike) throws LocalizedException;

    /**
     * this method getPropertyListByName <br>
     *
     * @param name
     * @return <br>
     * @throws LocalizedException
     * @author Canberk.Erkmen
     */
    public List<Property> getPropertyListByName(String name) throws LocalizedException;

    public String getTestcaseFolder(Integer testCaseId) throws LocalizedException;

    public List<Property> redefinePropertyList(List<Property> propertyList, List<Property> propertyListInDB);
}
