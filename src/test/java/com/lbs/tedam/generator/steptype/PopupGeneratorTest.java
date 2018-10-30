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

package com.lbs.tedam.generator.steptype;

import com.lbs.tedam.data.config.DataConfig;
import com.lbs.tedam.data.service.TestDataConfig;
import com.lbs.tedam.data.service.impl.PropertyServiceImpl;
import com.lbs.tedam.data.service.impl.SnapshotDefinitionServiceImpl;
import com.lbs.tedam.test.BaseServiceTest;
import com.lbs.tedam.util.EnumsV2.TestStepType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SnapshotDefinitionServiceImpl.class, PropertyServiceImpl.class, TestDataConfig.class, DataConfig.class})
public class PopupGeneratorTest extends BaseServiceTest {

    @Autowired
    private BeanFactory beanFactory;

    @Test
    public void testGenerate() {
        PopupGenerator popupGenerator = (PopupGenerator) GeneratorFactory.getGenerator(TestStepType.POPUP, beanFactory);
        popupGenerator.degenerate("100!ps!Tümünü!spc!Eşleştir");
        popupGenerator.validate();
        assertNotNull(popupGenerator.generate());
        popupGenerator.getGridTag();
        popupGenerator.setGridTag("123");
        popupGenerator.getPopupItem();
        popupGenerator.setPopupItem("1");
    }

    @Test
    public void testGenerateControl() {
        PopupGenerator popupGenerator = (PopupGenerator) GeneratorFactory.getGenerator(TestStepType.POPUP, beanFactory);
        assertNotNull(popupGenerator.generate());
    }

    @Test
    public void testDegenerateControl() {
        PopupGenerator popupGenerator = (PopupGenerator) GeneratorFactory.getGenerator(TestStepType.POPUP, beanFactory);
        popupGenerator.degenerate("");
        popupGenerator.degenerate("Tümünü!spc!Eşleştir");
    }

    @Test
    public void testValidateControl() {
        PopupGenerator popupGenerator = (PopupGenerator) GeneratorFactory.getGenerator(TestStepType.POPUP, beanFactory);
        popupGenerator.validate();
        popupGenerator.setGridTag("0");
        popupGenerator.validate();
        popupGenerator.setGridTag("123");
        popupGenerator.validate();
    }

    @Test
    public void testGenerateLookUp() {
        PopupGenerator popupGenerator = (PopupGenerator) GeneratorFactory.getGenerator(TestStepType.POPUP, beanFactory);
        popupGenerator.degenerateLookUp(TestStepType.POPUP.getBeginRegex() + "100!ps!Tümünü!spc!Eşleştir");
        assertNotNull(popupGenerator.generateLookUp());
    }

}
