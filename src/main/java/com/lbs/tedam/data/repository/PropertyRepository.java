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

import com.lbs.tedam.model.Property;

import java.util.List;

/**
 * Repository for entity Property.
 */
public interface PropertyRepository extends BaseRepository<Property, Integer> {

    /**
     * List of Property that equals value.
     *
     * @param value To search.
     * @return List of Property.
     */
    public List<Property> findByValue(String value);

    /**
     * List of Property that equals name and parameter.
     *
     * @param name      To search.
     * @param parameter To search
     * @return List of Property.
     */
    public List<Property> findByNameAndParameter(String name, String parameter);

    /**
     * List of Property that equals name and like parameter.
     *
     * @param name      To search.
     * @param parameter To search
     * @return List of Property.
     */
    public List<Property> findByNameAndParameterStartingWithIgnoreCase(String name, String parameter);

    /**
     * List of Property that equals name.
     *
     * @param name To search.
     * @return List of Property.
     */
    public List<Property> findByName(String name);

}
