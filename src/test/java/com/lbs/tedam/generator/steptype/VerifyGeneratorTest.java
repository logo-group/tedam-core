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
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.SnapshotDefinition;
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
public class VerifyGeneratorTest extends BaseServiceTest {

    @Autowired
    private BeanFactory beanFactory;

    @Test
    public void testGenerate() throws LocalizedException {
        VerifyGenerator verifyGenerator = (VerifyGenerator) GeneratorFactory.getGenerator(TestStepType.VERIFY, beanFactory);
        verifyGenerator.degenerate("12019!ps!0");
        verifyGenerator.validate();
        assertNotNull(verifyGenerator.generate());
    }

    @Test
    public void testValidateControl() {
        VerifyGenerator verifyGenerator = (VerifyGenerator) GeneratorFactory.getGenerator(TestStepType.VERIFY, beanFactory);
        verifyGenerator.validate();
        SnapshotDefinition snapshotDefinition = new SnapshotDefinition();
        verifyGenerator.setSnapshotDefinition(snapshotDefinition);
        verifyGenerator.validate();
        snapshotDefinition.setDescription("description");
        verifyGenerator.validate();
    }

    @Test
    public void testDeGenerateControl() throws LocalizedException {
        VerifyGenerator verifyGenerator = (VerifyGenerator) GeneratorFactory.getGenerator(TestStepType.VERIFY, beanFactory);
        verifyGenerator.degenerate("");
        verifyGenerator.degenerate("12019");
    }

    @Test
    public void testGenerateLookUp() throws LocalizedException {
        VerifyGenerator verifyGenerator = (VerifyGenerator) GeneratorFactory.getGenerator(TestStepType.VERIFY, beanFactory);
        verifyGenerator.degenerateLookUp(TestStepType.VERIFY.getBeginRegex() + "12019!ps!0");
        assertNotNull(verifyGenerator.generateLookUp());
    }

}
