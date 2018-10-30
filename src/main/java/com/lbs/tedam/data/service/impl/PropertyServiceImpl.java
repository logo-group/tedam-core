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

package com.lbs.tedam.data.service.impl;

import com.lbs.tedam.data.dao.PropertyDAO;
import com.lbs.tedam.data.service.PropertyService;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.Property;
import com.lbs.tedam.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PropertyServiceImpl extends BaseServiceImpl<Property, Integer> implements PropertyService {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private PropertyDAO dao;

    @Autowired
    public void setDao(PropertyDAO dao) {
        this.dao = dao;
        super.setBaseDao(dao);
    }

    @Override
    public List<Property> getPropertyListByValue(String value) throws LocalizedException {
        List<Property> propertyList = dao.getPropertyListByValue(value);
        List<Property> specialList = new ArrayList<>();
        for (Property property : propertyList) {
            if (property.getValue().contains("3")) {
                specialList.add(property);
            }
        }
        return specialList;
    }

    @Override
    public Property getPropertyByNameAndParameter(String name, String parameter) throws LocalizedException {
        Property property = dao.getPropertyByNameAndParameter(name, parameter);
        return property;
    }

    @Override
    public List<Property> getPropertyListByCriteria(String name, String parameter, boolean useLike) throws LocalizedException {
        List<Property> propertyList = null;
        if (!useLike)
            propertyList = dao.getPropertyListByNameAndParameter(name, parameter);
        else
            propertyList = dao.getPropertyListByNameAndParameterStartingWithIgnoreCase(name, parameter);
        return propertyList;
    }

    @Override
    public List<Property> getPropertyListByName(String name) throws LocalizedException {
        List<Property> propertyList = dao.getPropertyListByName(name);
        return propertyList;
    }

    @Override
    public String getTestcaseFolder(Integer testCaseId) throws LocalizedException {
        Property property = dao.getPropertyByNameAndParameter(Constants.PROPERTY_CONFIG, Constants.PROPERTY_SNAPSHOT_FILE_FOLDER);
        String path = property.getValue() + "tc" + testCaseId + Constants.FILE_SEPARATOR;
        return path;
    }

    @Override
    public List<Property> redefinePropertyList(List<Property> propertyList, List<Property> propertyListInDB) {
        List<Property> reDefinedPropertyList = new ArrayList<>();
        for (Property property : propertyList) {
            Property propertyInDB = getPropertyByParameter(property.getParameter(), propertyListInDB);
            if (propertyInDB == null) {
                reDefinedPropertyList.add(property);
            } else if (!propertyInDB.getValue().equals(property.getValue())) {
                propertyInDB.setValue(property.getValue());
                reDefinedPropertyList.add(propertyInDB);
            }

        }
        return reDefinedPropertyList;
    }

    private Property getPropertyByParameter(String parameter, List<Property> propertyListInDB) {
        for (Property property : propertyListInDB) {
            if (property.getParameter().equals(parameter)) {
                return property;
            }
        }
        return null;
    }

}
