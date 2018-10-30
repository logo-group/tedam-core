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

package com.lbs.tedam.data.dao.impl;

import com.lbs.tedam.data.dao.TestCaseDAO;
import com.lbs.tedam.data.repository.TestCaseRepository;
import com.lbs.tedam.exception.localized.GeneralLocalizedException;
import com.lbs.tedam.model.Project;
import com.lbs.tedam.model.TedamFolder;
import com.lbs.tedam.model.TestCase;
import com.lbs.tedam.util.EnumsV2.CommandStatus;
import com.lbs.tedam.util.EnumsV2.TedamBoolean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class TestCaseDAOImpl extends BaseDAOImpl<TestCase, Integer> implements TestCaseDAO {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private transient TestCaseRepository repository;

    @Autowired
    public void setRepository(TestCaseRepository repository) {
        this.repository = repository;
        super.setRepository(repository);
    }

    @Override
    public List<TestCase> getTestCaseListByProject(Project project) throws GeneralLocalizedException {
        try {
            List<TestCase> testCaseList = repository.findByProjectAndDeletedOrderByIdDesc(project, TedamBoolean.FALSE.getBooleanValue());
            return testCaseList;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }

    }

    @Override
    public List<TestCase> getTestCaseListByProjectAndFolder(Project project, TedamFolder tedamFolder) throws GeneralLocalizedException {
        try {
            List<TestCase> testCaseList = repository.findByProjectAndTestCaseFolderIdAndDeletedOrderByIdDesc(project, tedamFolder.getId(), TedamBoolean.FALSE.getBooleanValue());
            return testCaseList;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public TestCase getTestCaseByName(String name) throws GeneralLocalizedException {
        try {
            TestCase testCase = repository.findByName(name);
            return testCase;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }

    }

    @Override
    public boolean isTestSetInProgressStatus(TestCase testCase) throws GeneralLocalizedException {
        if (testCase.getId() == null)
            return false;
        try {
            List<CommandStatus> statusList = repository.isTestSetInProgressStatus(testCase.getId());
            for (CommandStatus commandStatus : statusList) {
                if (CommandStatus.IN_PROGRESS.equals(commandStatus)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public void updateTestCaseExecutionDateTime(TestCase testCase, LocalDateTime endDate) throws GeneralLocalizedException {
        try {
            repository.updateTestCaseExecutionDateTime(testCase.getId(), endDate);
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }

    }

}
