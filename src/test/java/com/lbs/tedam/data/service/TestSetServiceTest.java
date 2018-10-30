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
import com.lbs.tedam.data.service.impl.*;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.Project;
import com.lbs.tedam.model.TedamFolder;
import com.lbs.tedam.model.TestSet;
import com.lbs.tedam.test.BaseServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * @author Tarik.Mikyas <br>
 * Tests the TestSetServiceImpl class routines.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestSetServiceImpl.class, ProjectServiceImpl.class, JobDetailServiceImpl.class,
        TedamFolderServiceImpl.class, TestCaseServiceImpl.class, TestDataConfig.class, DataConfig.class,
        TestCaseTestRunServiceImpl.class})
public class TestSetServiceTest extends BaseServiceTest {

    @Autowired
    private TestSetService testSetService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TedamFolderService tedamFolderService;

    @Test
    public void test01GetTestSetListByProject() throws LocalizedException {
        Project project = projectService.getAll().get(0);
        List<TestSet> testSetList = testSetService.getTestSetListByProject(project);
        assertNotNull(testSetList);
    }

    @Test
    public void test02GetTestSetListByProjectAndFolder() throws LocalizedException {
        Project project = projectService.getAll().get(0);
        TedamFolder folder = tedamFolderService.getById(200);
        List<TestSet> testSetList = testSetService.getTestSetListWithJobIdByProjectAndFolder(project, folder);
        assertNotNull(testSetList);
    }

    @Test
    public void test03GetTestSetListWithJobIdByProject() throws LocalizedException {
        Project project = projectService.getAll().get(0);
        List<TestSet> testSetList = testSetService.getTestSetListWithJobIdByProject(project);
        assertNotNull(testSetList.get(0).getJobListAsString());
    }

    @Test
    public void test04GetTestSetByName() throws LocalizedException {
        TestSet testSet = testSetService.getTestSetByName("deneme");
        assertNotNull(testSet);
    }

}
