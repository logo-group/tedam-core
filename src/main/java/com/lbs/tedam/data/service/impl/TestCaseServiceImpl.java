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

package com.lbs.tedam.data.service.impl;

import com.lbs.tedam.data.dao.TestCaseDAO;
import com.lbs.tedam.data.service.TedamFolderService;
import com.lbs.tedam.data.service.TestCaseService;
import com.lbs.tedam.data.service.TestCaseTestRunService;
import com.lbs.tedam.exception.localized.GeneralLocalizedException;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.Project;
import com.lbs.tedam.model.TedamFolder;
import com.lbs.tedam.model.TestCase;
import com.lbs.tedam.model.TestCaseTestRun;
import com.lbs.tedam.util.EnumsV2.TedamBoolean;
import com.lbs.tedam.util.EnumsV2.TedamFolderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class TestCaseServiceImpl extends BaseServiceImpl<TestCase, Integer> implements TestCaseService {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private TestCaseDAO dao;
    private TedamFolderService folderService;
    private TestCaseTestRunService testCaseRunService;

    @Autowired
    public void setDao(TestCaseDAO dao, TedamFolderService folderService, TestCaseTestRunService testCaseRunService) {
        this.dao = dao;
        this.folderService = folderService;
        this.testCaseRunService = testCaseRunService;
        super.setBaseDao(dao);
    }

    @Override
    public List<TestCase> getTestCaseListByProjectAndFolder(Project project, TedamFolder tedamFolder) throws LocalizedException {
        List<TestCase> testCaseList = dao.getTestCaseListByProjectAndFolder(project, tedamFolder);
        List<TedamFolder> folderList = folderService.getTedamFolderListByProjectAndFolderType(project, TedamFolderType.TESTCASE);
        setTestCaseFolderName(testCaseList, folderList);
        afterGet(testCaseList);
        return testCaseList;
    }

    @Override
    public List<TestCase> getTestCaseListByProject(Project project) throws LocalizedException {
        List<TedamFolder> folderList = folderService.getTedamFolderListByProjectAndFolderType(project, TedamFolderType.TESTCASE);
        List<TestCase> testCaseList = dao.getTestCaseListByProject(project);
        setTestCaseFolderName(testCaseList, folderList);
        afterGet(testCaseList);
        return testCaseList;
    }

    private void setTestCaseFolderName(List<TestCase> testCaseList, List<TedamFolder> folderList) {
        for (TestCase testCase : testCaseList) {
            if (testCase.getTestCaseFolderId() != null) {
                for (TedamFolder folder : folderList) {
                    if (testCase.getTestCaseFolderId().equals(folder.getId())) {
                        testCase.setTestCaseFolder(folder.getName());
                        break;
                    }
                }
            }
        }
    }

    private void setTestCaseExecutionStatus(List<TestCase> testCaseList) throws GeneralLocalizedException {
        int size = testCaseList.size();
        if (size > 0) {
            TestCase endTestCase = testCaseList.get(0);
            TestCase startTestCase = testCaseList.get(size - 1);
            if (endTestCase == null || startTestCase == null) {
                return;
            }
            Integer endId = endTestCase.getId();
            Integer startId = startTestCase.getId();
            List<TestCaseTestRun> runList = testCaseRunService.findByTestCaseIdRange(startId, endId, TedamBoolean.FALSE.getBooleanValue());
            for (TestCase testCase : testCaseList) {
                for (TestCaseTestRun run : runList) {
                    if (testCase.getId().equals(run.getTestCaseId())) {
                        testCase.setExecutionStatus(run.getExecutionStatus());
                        testCase.setVersion(run.getVersion());
                        break;
                    }
                }
            }
        }
    }

    @Override
    public TestCase getTestCaseByName(String name) throws LocalizedException {
        TestCase testCase = dao.getTestCaseByName(name);
        afterGet(Arrays.asList(testCase));
        return testCase;
    }

    @Override
    public boolean isTestSetInProgressStatus(TestCase testCase) throws LocalizedException {
        boolean result = dao.isTestSetInProgressStatus(testCase);
        return result;
    }

    @Override
    public void updateTestCaseExecutionDateTime(TestCase testCase, LocalDateTime endDate) throws LocalizedException {
        dao.updateTestCaseExecutionDateTime(testCase, endDate);
    }

    @Override
    public void afterGet(List<TestCase> itemList) throws LocalizedException {
        setTestCaseExecutionStatus(itemList);
    }

}
