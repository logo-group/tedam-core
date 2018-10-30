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

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SnapshotDefinitionServiceImpl.class, PropertyServiceImpl.class, TestDataConfig.class, DataConfig.class})
public class GridDeleteGeneratorTest extends BaseServiceTest {

    @Autowired
    private BeanFactory beanFactory;

    @Test
    public void testGenerate() {
        GridDeleteGenerator gridDeleteGenerator = (GridDeleteGenerator) GeneratorFactory.getGenerator(TestStepType.GRID_DELETE, beanFactory);
        gridDeleteGenerator.degenerate("1000!ps!3");
        gridDeleteGenerator.validate();
        assertNotNull(gridDeleteGenerator.generate());
        gridDeleteGenerator.getGridTag();
        gridDeleteGenerator.setGridTag("123");
        gridDeleteGenerator.getRowIndexes();
        gridDeleteGenerator.setRowIndexes(Arrays.asList(2, 3));
    }

    @Test
    public void testValidateControl() {
        GridDeleteGenerator gridDeleteGenerator = (GridDeleteGenerator) GeneratorFactory.getGenerator(TestStepType.GRID_DELETE, beanFactory);
        gridDeleteGenerator.validate();
        gridDeleteGenerator.setGridTag("0");
        gridDeleteGenerator.validate();
        gridDeleteGenerator.setGridTag("123");
        gridDeleteGenerator.validate();
    }

    @Test
    public void testGenerateControl() {
        GridDeleteGenerator gridDeleteGenerator = (GridDeleteGenerator) GeneratorFactory.getGenerator(TestStepType.GRID_DELETE, beanFactory);
        gridDeleteGenerator.generate();
    }

    @Test
    public void testDegenerateControl() {
        GridDeleteGenerator gridDeleteGenerator = (GridDeleteGenerator) GeneratorFactory.getGenerator(TestStepType.GRID_DELETE, beanFactory);
        gridDeleteGenerator.degenerate("");
    }

    @Test
    public void testGenerateLookUp() {
        GridDeleteGenerator gridDeleteGenerator = (GridDeleteGenerator) GeneratorFactory.getGenerator(TestStepType.GRID_DELETE, beanFactory);
        gridDeleteGenerator.degenerateLookUp(TestStepType.GRID_DELETE.getBeginRegex() + "1000!ps!3");
        assertNotNull(gridDeleteGenerator.generateLookUp());
    }

}
