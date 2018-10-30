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
import com.lbs.tedam.data.service.impl.ProjectServiceImpl;
import com.lbs.tedam.data.service.impl.PropertyServiceImpl;
import com.lbs.tedam.data.service.impl.SnapshotDefinitionServiceImpl;
import com.lbs.tedam.data.service.impl.TestStepServiceImpl;
import com.lbs.tedam.exception.JobCommandBuildException;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.generator.steptype.FormFillGenerator;
import com.lbs.tedam.generator.steptype.GeneratorFactory;
import com.lbs.tedam.model.Project;
import com.lbs.tedam.model.SnapshotDefinition;
import com.lbs.tedam.model.TestStep;
import com.lbs.tedam.test.BaseServiceTest;
import com.lbs.tedam.util.EnumsV2.TestStepType;
import com.lbs.tedam.util.TedamStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Tarik.Mikyas <br>
 * Tests the TestSetServiceImpl class routines.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestStepServiceImpl.class, ProjectServiceImpl.class, SnapshotDefinitionServiceImpl.class, PropertyServiceImpl.class, TestDataConfig.class,
        DataConfig.class})
public class TestStepServiceTest extends BaseServiceTest {

    @Autowired
    private TestStepService testStepService;

    @Autowired
    private SnapshotDefinitionService snapshotDefinitionService;

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ProjectService projectService;

    @Test
    public void test01GetTestStepListByTypeAndProject() throws LocalizedException {
        Project project = projectService.getAll().get(0);
        List<TestStep> testStepList = testStepService.getTestStepListByTypeAndProject(TestStepType.FORM_FILL, project);
        assertNotEquals(testStepList.size(), 0);
    }

    @Test
    public void test02RegenerateTestStepParameter() throws LocalizedException {
        TestStep testStep = testStepService.getAll().get(0);
        FormFillGenerator formFillGenerator = (FormFillGenerator) GeneratorFactory.getGenerator(TestStepType.FORM_FILL, beanFactory);
        testStep.setGenerator(formFillGenerator);
        SnapshotDefinition snapshotDefinition = snapshotDefinitionService.getById(9360);
        testStepService.regenerateTestStepParameter(testStep, snapshotDefinition);
    }

    @Test
    public void test03CopyTestStepFillDefinitions() throws LocalizedException {
        TestStep testStep = testStepService.getAll().get(0);
        FormFillGenerator formFillGenerator = (FormFillGenerator) GeneratorFactory.getGenerator(TestStepType.FORM_FILL, beanFactory);
        SnapshotDefinition snapshotDefinition = snapshotDefinitionService.getById(9360);
        formFillGenerator.setSnapshotDefinition(snapshotDefinition);
        testStep.setGenerator(formFillGenerator);
        SnapshotDefinition clonedSnapshotDefinition = testStepService.copyTestStepFillDefinitions(testStep);
        assertNull(clonedSnapshotDefinition);
    }

    @Test
    public void test04CopyTestStepFillDefinitionsNotFillType() throws LocalizedException {
        TestStep testStep = testStepService.getAll().get(1);
        FormFillGenerator formFillGenerator = (FormFillGenerator) GeneratorFactory.getGenerator(TestStepType.FORM_FILL, beanFactory);
        SnapshotDefinition snapshotDefinition = snapshotDefinitionService.getById(9360);
        formFillGenerator.setSnapshotDefinition(snapshotDefinition);
        testStep.setGenerator(formFillGenerator);
        SnapshotDefinition clonedSnapshotDefinition = testStepService.copyTestStepFillDefinitions(testStep);
        assertNotNull(clonedSnapshotDefinition);
    }

    @Test
    public void test05FindTestParameters() throws JobCommandBuildException, LocalizedException {
        TestStep testStep = testStepService.getById(164571);
        String testParamaters = TedamStringUtils.findTestParamaters(Arrays.asList(testStep));
        System.out.println(testParamaters);
        assertNotNull(testParamaters);

    }

}
