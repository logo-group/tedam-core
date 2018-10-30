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
public class GridRowSelectGeneratorTest extends BaseServiceTest {

    @Autowired
    private BeanFactory beanFactory;

    @Test
    public void testGenerate() {
        GridRowSelectGenerator gridRowSelectGenerator = (GridRowSelectGenerator) GeneratorFactory.getGenerator(TestStepType.GRID_ROW_SELECT, beanFactory);
        gridRowSelectGenerator.degenerate("100!ps![3,0,2,1]");
        gridRowSelectGenerator.validate();
        assertNotNull(gridRowSelectGenerator.generate());
        gridRowSelectGenerator.getGridTag();
        gridRowSelectGenerator.setGridTag("123");
        gridRowSelectGenerator.getRowIndexes();
        gridRowSelectGenerator.setRowIndexes(Arrays.asList(2, 3));
    }

    @Test
    public void testValidateControl() {
        GridRowSelectGenerator gridRowSelectGenerator = (GridRowSelectGenerator) GeneratorFactory.getGenerator(TestStepType.GRID_ROW_SELECT, beanFactory);
        gridRowSelectGenerator.validate();
        gridRowSelectGenerator.setGridTag("0");
        gridRowSelectGenerator.validate();
        gridRowSelectGenerator.setGridTag("123");
        gridRowSelectGenerator.validate();
    }

    @Test
    public void testGenerateControl() {
        GridRowSelectGenerator gridRowSelectGenerator = (GridRowSelectGenerator) GeneratorFactory.getGenerator(TestStepType.GRID_ROW_SELECT, beanFactory);
        gridRowSelectGenerator.generate();
    }

    @Test
    public void testDegenerateControl() {
        GridRowSelectGenerator gridRowSelectGenerator = (GridRowSelectGenerator) GeneratorFactory.getGenerator(TestStepType.GRID_ROW_SELECT, beanFactory);
        gridRowSelectGenerator.degenerate("");
    }

    @Test
    public void testGenerateLookUp() {
        GridRowSelectGenerator gridRowSelectGenerator = (GridRowSelectGenerator) GeneratorFactory.getGenerator(TestStepType.GRID_ROW_SELECT, beanFactory);
        gridRowSelectGenerator.degenerateLookUp(TestStepType.GRID_ROW_SELECT.getBeginRegex() + "100!ps![3,0,2,1]");
        assertNotNull(gridRowSelectGenerator.generateLookUp());
    }

}
