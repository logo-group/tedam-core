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
import com.lbs.tedam.data.service.impl.FormFieldServiceImpl;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.FormField;
import com.lbs.tedam.test.BaseServiceTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FormFieldServiceImpl.class, TestDataConfig.class, DataConfig.class})
public class FormFieldServiceTest extends BaseServiceTest {

    @Autowired
    private FormFieldService formFieldService;

    @Test
    public void testGetFormFieldListByVersionAndFormDefId() throws LocalizedException {
        List<FormField> formFieldList = formFieldService.getFormFieldListByVersionAndFormDefId("2.34.9.0", 1);
        Assert.assertEquals(formFieldList.size(), 41);
    }

    @Test
    public void testGetHistoryOfTag() throws LocalizedException {
        List<FormField> formFieldList = formFieldService.getHistoryOfTag(1);
        Assert.assertEquals(formFieldList.size(), 1);
    }

    @Test
    public void testGetCaptionBySnapshotValue() throws LocalizedException {
        String caption = formFieldService.getCaptionBySnapshotValue(37999, "30004");
        Assert.assertTrue("Yetki Kodu".equalsIgnoreCase(caption));
    }

    @Test
    public void testGetControlFieldsOfVersionsAndForms() throws LocalizedException {
        List<FormField> formFieldList = formFieldService.getControlFieldsOfVersionsAndForms("2.52.7.0", 505);
        Assert.assertEquals(formFieldList.size(), 44);
    }

    @Test
    public void testGetFieldsOfVersionsAndForms() throws LocalizedException {
        List<FormField> formFieldList = formFieldService.getFieldsOfVersionsAndForms("2.52.7.0", 505, true);
        Assert.assertEquals(formFieldList.size(), 88);
    }

    @Test
    public void test01GetFormFieldListByVersionAndFormDefId() throws LocalizedException {
        List<FormField> formFieldList;
        formFieldList = formFieldService.getFormFieldListByVersionAndFormDefId("2.34.9.0", 1);
        Assert.assertNotEquals(formFieldList.size(), 0);

    }

}
