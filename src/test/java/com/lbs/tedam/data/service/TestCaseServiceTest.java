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
import com.lbs.tedam.data.service.impl.TedamFolderServiceImpl;
import com.lbs.tedam.data.service.impl.TestCaseServiceImpl;
import com.lbs.tedam.data.service.impl.TestCaseTestRunServiceImpl;
import com.lbs.tedam.exception.JobCommandBuildException;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.Project;
import com.lbs.tedam.model.TedamFolder;
import com.lbs.tedam.model.TestCase;
import com.lbs.tedam.test.BaseServiceTest;
import com.lbs.tedam.util.TedamStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Tarik.Mikyas <br>
 * Tests the TestSetServiceImpl class routines.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestCaseServiceImpl.class, ProjectServiceImpl.class, TedamFolderServiceImpl.class, TestCaseTestRunServiceImpl.class, TestDataConfig.class,
        DataConfig.class})
public class TestCaseServiceTest extends BaseServiceTest {

    @Autowired
    private TestCaseService testCaseService;

    @Autowired
    private TedamFolderService tedamFolderService;

    @Autowired
    private ProjectService projectService;

    @Test
    public void test01GetTestCaseListByProject() throws LocalizedException {
        Project project = projectService.getAll().get(0);
        List<TestCase> testCaseList = testCaseService.getTestCaseListByProject(project);
        assertNotEquals(testCaseList.size(), 0);
    }

    @Test
    public void test01GetTestCaseListByProjectAndFolder() throws LocalizedException {
        Project project = projectService.getAll().get(0);
        TedamFolder tedamFolder = tedamFolderService.getById(1322);
        List<TestCase> testCaseList = testCaseService.getTestCaseListByProjectAndFolder(project, tedamFolder);
        assertNotEquals(testCaseList.size(), 0);
    }

    @Test
    public void test02GetTestCaseByName() throws LocalizedException {
        TestCase testCase = testCaseService.getTestCaseByName("Sevkiyat Fişine Dahil Sonrası Dağıtım için Rezerve Toplamları");
        assertNotNull(testCase);
    }

    @Test
    public void test02UpdateTestCaseExecutionDateTime() throws LocalizedException {
        List<TestCase> testCaseList = testCaseService.getAll();
        testCaseService.updateTestCaseExecutionDateTime(testCaseList.get(0), LocalDateTime.now());
    }

    @Test
    public void test05FindTestParameters() throws JobCommandBuildException, LocalizedException {
        TestCase testCase = testCaseService.getById(27437);
        String testParamaters = TedamStringUtils.findTestParamaters(testCase.getTestSteps());
        System.out.println(testParamaters);
        assertNotNull(testParamaters);

    }

}
