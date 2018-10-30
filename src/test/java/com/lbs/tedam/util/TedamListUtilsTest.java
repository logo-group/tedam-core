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

package com.lbs.tedam.util;

import com.lbs.tedam.data.config.DataConfig;
import com.lbs.tedam.data.service.FormFieldService;
import com.lbs.tedam.data.service.SnapshotValueService;
import com.lbs.tedam.data.service.TestDataConfig;
import com.lbs.tedam.data.service.impl.*;
import com.lbs.tedam.exception.DifferencesSnapshotException;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.FormField;
import com.lbs.tedam.model.SnapshotValue;
import com.lbs.tedam.test.BaseServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SnapshotValueServiceImpl.class, SnapshotDefinitionServiceImpl.class, PropertyServiceImpl.class, MenuPathServiceImpl.class, FormFieldServiceImpl.class,
        TestDataConfig.class, DataConfig.class})
public class TedamListUtilsTest extends BaseServiceTest {

    @Autowired
    private SnapshotValueService snapshotValueService;

    @Autowired
    private FormFieldService formFieldService;

    @Test
    public void testGetTagIndexInList() throws LocalizedException {
        List<SnapshotValue> snapshotValues;
        try {
            snapshotValues = snapshotValueService.getSnapshotValuesFromFile("", getFilePathFromSourceName("/DifferencesSnapshots1.xml"), 37);
            int index = TedamListUtils.getTagIndexInList(snapshotValues, snapshotValues.get(0).getTag());
            assertNotEquals(index, -1);
        } catch (DifferencesSnapshotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetTagIndexInFieldList() throws LocalizedException {
        List<FormField> formFieldList = formFieldService.getFormFieldListByVersionAndFormDefId("2.34.9.0", 1);
        int index = TedamListUtils.getTagIndexInFieldList(formFieldList, formFieldList.get(0).getTag());
        assertNotEquals(index, -1);
    }

}
