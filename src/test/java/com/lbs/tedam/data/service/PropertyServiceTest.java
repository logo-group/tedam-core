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

import com.lbs.tedam.data.config.DataConfig;
import com.lbs.tedam.data.service.impl.PropertyServiceImpl;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.Property;
import com.lbs.tedam.test.BaseServiceTest;
import com.lbs.tedam.util.Constants;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PropertyServiceImpl.class, TestDataConfig.class, DataConfig.class})
public class PropertyServiceTest extends BaseServiceTest {

    @Autowired
    private PropertyService propertyServiceImpl;

    @Test
    public void testGetPropertyListByValue() throws LocalizedException {
        List<Property> list = propertyServiceImpl.getPropertyListByValue("3");
        Assert.assertNotEquals(0, list.size());
    }

    // TODO: live will also be created.
    @Test
    public void test() {
        List<Property> propertyList = new ArrayList<>();
        Property property = new Property(Constants.PROPERTY_CONFIG, Constants.PROPERTY_MODULE_CONFIG_POOL_PATH,
                "C:\\Projects\\jguar_GIT_Set\\jprod\\Unity\\bin\\Config\\Modules\\");
        propertyList.add(property);
        Property property1 = new Property(Constants.PROPERTY_CONFIG, Constants.PROPERTY_MODULE_CONFIG_ORDER_PATH,
                "C:\\Projects\\jguar_GIT_Set\\jprod\\UnityServer\\Config\\Modules\\ModuleSchema.xml");
        propertyList.add(property1);
        Property property2 = new Property(Constants.PROPERTY_CONFIG, Constants.PROPERTY_TEMP_FILE_PATH, "C:/temp");
        propertyList.add(property2);
        Property property3 = new Property(Constants.PROPERTY_CONFIG, Constants.PROPERTY_SNAPSHOT_FILE_FOLDER, "C:/tedamfacetest/");
        propertyList.add(property3);
        Property property4 = new Property(Constants.PROPERTY_CONFIG, Constants.PROPERTY_SNAPSHOTCOLLECTOR_ORDERFILE_PATH,
                "C:\\Projects\\test\\Tools\\ty\\Beanshell_Scripts\\SnapshotCollector\\SCOrder.txt");
        propertyList.add(property4);
        Property property5 = new Property(Constants.PROPERTY_CONFIG, Constants.PROPERTY_JOBRUNNER_REST_URL, "http://localhost:9080/TedamManager/api/JobRunnerRestService/");
        propertyList.add(property5);
//		propertyServiceImpl.save(propertyList);
    }

    @Test
    public void testGetPropertyListByName() throws LocalizedException {
        List<Property> list = propertyServiceImpl.getPropertyListByName(Constants.PROPERTY_CONFIG);
        Assert.assertNotEquals(0, list.size());
    }

    @Test
    public void testGetPropertyListByValueEmpty() throws LocalizedException {
        List<Property> list = propertyServiceImpl.getPropertyListByValue("NotExist");
        Assert.assertNotEquals(0, list.size());
    }

    @Test
    public void testGetPropertyByNameAndParameter() throws LocalizedException {
        Property property = propertyServiceImpl.getPropertyByNameAndParameter("SPLITTER", "4346,5201");
        Assert.assertNotNull(property);
    }

    @Test
    public void testGetPropertyByNameAndParameterNull() throws LocalizedException {
        Property property = propertyServiceImpl.getPropertyByNameAndParameter("SPLITTER", "4346");
        Assert.assertNull(property);
    }

    @Test
    public void testGetPropertyByNameNullAndParameter() throws LocalizedException {
        Property property = propertyServiceImpl.getPropertyByNameAndParameter("NotExist", "4346,5201");
        Assert.assertNull(property);
    }

    @Test
    public void testGetPropertyListByCriteriaEqual() throws LocalizedException {
        List<Property> list = propertyServiceImpl.getPropertyListByCriteria("SPLITTER", "4346,5201", false);
        Assert.assertNotEquals(1, list.size());
    }

    @Test
    public void testGetPropertyListByCriteriaLike() throws LocalizedException {
        List<Property> list = propertyServiceImpl.getPropertyListByCriteria("SPLITTER", "43", true);
        Assert.assertNotEquals(11, list.size());
    }

    @Test
    public void testGetTestcaseFolder() throws LocalizedException {
        String testCaseFolderPath = propertyServiceImpl.getTestcaseFolder(1);
        Assert.assertNotNull(testCaseFolderPath);
    }

}
