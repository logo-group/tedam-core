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

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SnapshotDefinitionServiceImpl.class, PropertyServiceImpl.class, TestDataConfig.class, DataConfig.class})
public class GridSearchGeneratorTest extends BaseServiceTest {

    @Autowired
    private BeanFactory beanFactory;

    @Test
    public void testGenerate() {
        GridSearchGenerator gridSearchGenerator = (GridSearchGenerator) GeneratorFactory.getGenerator(TestStepType.GRID_SEARCH, beanFactory);
        gridSearchGenerator.degenerate("100!ps![(1002,TC018361)]");
        gridSearchGenerator.validate();
        assertNotNull(gridSearchGenerator.generate());
        gridSearchGenerator.getGridTag();
        gridSearchGenerator.setGridTag("123");
        gridSearchGenerator.getSearchValues();
        gridSearchGenerator.setSearchValues(new ArrayList<>());
    }

    @Test
    public void testValidateControl() {
        GridSearchGenerator gridSearchGenerator = (GridSearchGenerator) GeneratorFactory.getGenerator(TestStepType.GRID_SEARCH, beanFactory);
        gridSearchGenerator.validate();
        gridSearchGenerator.setGridTag("123");
        gridSearchGenerator.validate();
    }

    @Test
    public void testGenerateControl() {
        GridSearchGenerator gridSearchGenerator = (GridSearchGenerator) GeneratorFactory.getGenerator(TestStepType.GRID_SEARCH, beanFactory);
        gridSearchGenerator.generate();
        gridSearchGenerator.degenerate("100!ps![(1002,TC018361,1,TC018322)]");
        gridSearchGenerator.generate();

    }

    @Test
    public void testDegenerateControl() {
        GridSearchGenerator gridSearchGenerator = (GridSearchGenerator) GeneratorFactory.getGenerator(TestStepType.GRID_SEARCH, beanFactory);
        gridSearchGenerator.degenerate("");
        gridSearchGenerator.degenerate("100");

    }

    @Test
    public void testGenerateLookUp() {
        GridSearchGenerator gridSearchGenerator = (GridSearchGenerator) GeneratorFactory.getGenerator(TestStepType.GRID_SEARCH, beanFactory);
        gridSearchGenerator.degenerateLookUp(TestStepType.GRID_SEARCH.getBeginRegex() + "100!ps![(1002,TC018361)]");
        assertNotNull(gridSearchGenerator.generateLookUp());
    }

    /**
     * @author Seyma.Sahin
     */
    @Test
    public void testGetters() {
        GridSearchGenerator gridSearchGenerator = new GridSearchGenerator();
        gridSearchGenerator.getSearchValues();
        gridSearchGenerator.getGridTag();
    }

    /**
     * @author Seyma.Sahin
     */
    @Test
    public void testSetters() {
        GridSearchGenerator gridSearchGenerator = new GridSearchGenerator();
        gridSearchGenerator.setSearchValues(null);
        gridSearchGenerator.setGridTag("gridTag");
    }

    /**
     * @author Seyma.Sahin
     */
    @Test
    public void testGenerateLookUp2() {
        GridSearchGenerator gridSearchGenerator = new GridSearchGenerator();
        gridSearchGenerator.generateLookUp();
    }
}
