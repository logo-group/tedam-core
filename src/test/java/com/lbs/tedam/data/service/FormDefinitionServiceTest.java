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
import com.lbs.tedam.data.service.impl.FormDefinitionServiceImpl;
import com.lbs.tedam.data.service.impl.FormFieldServiceImpl;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.FormDefinition;
import com.lbs.tedam.test.BaseServiceTest;
import com.lbs.tedam.util.TedamDOMUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;

import javax.xml.xpath.XPathExpressionException;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FormDefinitionServiceImpl.class, FormFieldServiceImpl.class, TestDataConfig.class, DataConfig.class})
public class FormDefinitionServiceTest extends BaseServiceTest {

    @Autowired
    private FormDefinitionService formDefinitionService;

    @Test
    public void testGetFormDefByNameAndMode() throws LocalizedException {
        FormDefinition formDefinition = formDefinitionService.getFormDefByNameAndMode("AMXFAsset", "1");
        Assert.assertNotNull(formDefinition);
    }

    @Test
    public void testGetFormDefByNameAndModeNull() throws LocalizedException {
        FormDefinition formDefinition = formDefinitionService.getFormDefByNameAndMode("NotExistForm", "1");
        Assert.assertNull(formDefinition);
    }

    @Test
    public void testGetUpdatedFormDefinitionIdList() throws LocalizedException {
        List<Integer> list = formDefinitionService.getUpdatedFormDefinitionIdList("2.47.6.0");
        Assert.assertEquals(15, list.size());
    }

    @Test
    @Transactional
    public void testSaveUpdateFormContentExist() throws XPathExpressionException, LocalizedException {
        String filePath = getFilePathFromSourceName("/Carihesap.xml");
        Document doc = TedamDOMUtils.domParserStarter(filePath);
        FormDefinition formDefinition = formDefinitionService.saveUpdateFormContent(doc.getDocumentElement(), false);
        assertNotNull(formDefinition);
    }

    @Test
    @Transactional
    public void testSaveUpdateFormContentNotExist() throws XPathExpressionException, LocalizedException {
        String filePath = getFilePathFromSourceName("/SatisSiparisleri.xml");
        Document doc = TedamDOMUtils.domParserStarter(filePath);
        FormDefinition formDefinition = formDefinitionService.saveUpdateFormContent(doc.getDocumentElement(), false);
        assertNotNull(formDefinition);
    }

}
