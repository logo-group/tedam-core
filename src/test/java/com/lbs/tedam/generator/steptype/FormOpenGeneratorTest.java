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
import com.lbs.tedam.data.service.impl.MenuPathServiceImpl;
import com.lbs.tedam.data.service.impl.PropertyServiceImpl;
import com.lbs.tedam.data.service.impl.SnapshotDefinitionServiceImpl;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.MenuPath;
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
@SpringBootTest(classes = {SnapshotDefinitionServiceImpl.class, PropertyServiceImpl.class, MenuPathServiceImpl.class, TestDataConfig.class, DataConfig.class})
public class FormOpenGeneratorTest extends BaseServiceTest {

    @Autowired
    private BeanFactory beanFactory;

    @Test
    public void testGenerate() throws LocalizedException {
        FormOpenGenerator formOpenGenerator = (FormOpenGenerator) GeneratorFactory.getGenerator(TestStepType.FORM_OPEN, beanFactory);
        formOpenGenerator.degenerate("[10000,10200,10201]");
        formOpenGenerator.validate();
        formOpenGenerator.getMenuPathList();
        formOpenGenerator.addMenuPathList(new MenuPath(1, "caption", null, null));
        assertNotNull(formOpenGenerator.generate());
    }

    @Test
    public void testValidateControl() {
        FormOpenGenerator formOpenGenerator = (FormOpenGenerator) GeneratorFactory.getGenerator(TestStepType.FORM_OPEN, beanFactory);
        formOpenGenerator.validate();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testDegenerateLookUp() throws LocalizedException {
        FormOpenGenerator formOpenGenerator = (FormOpenGenerator) GeneratorFactory.getGenerator(TestStepType.FORM_OPEN, beanFactory);
        formOpenGenerator.degenerateLookUp("");
    }

    @Test
    public void testDegenerateControl() throws LocalizedException {
        FormOpenGenerator formOpenGenerator = (FormOpenGenerator) GeneratorFactory.getGenerator(TestStepType.FORM_OPEN, beanFactory);
        formOpenGenerator.degenerate("");
    }

    @Test
    public void testGenerateLookUp() {
        FormOpenGenerator formOpenGenerator = (FormOpenGenerator) GeneratorFactory.getGenerator(TestStepType.FORM_OPEN, beanFactory);
        assertNotNull(formOpenGenerator.generateLookUp());
    }

    /**
     * @author Seyma.Sahin
     */
    @Test
    public void testResetMenuPathList() {
        FormOpenGenerator formOpenGenerator = (FormOpenGenerator) GeneratorFactory.getGenerator(TestStepType.FORM_OPEN, beanFactory);
        formOpenGenerator.resetMenuPathList();
    }

}
