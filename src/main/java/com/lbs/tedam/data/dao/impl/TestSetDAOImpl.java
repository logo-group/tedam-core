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
//

import com.lbs.tedam.data.dao.TestSetDAO;
import com.lbs.tedam.data.repository.TestSetRepository;
import com.lbs.tedam.exception.localized.GeneralLocalizedException;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.Project;
import com.lbs.tedam.model.TedamFolder;
import com.lbs.tedam.model.TestSet;
import com.lbs.tedam.util.EnumsV2.TedamBoolean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestSetDAOImpl extends BaseDAOImpl<TestSet, Integer> implements TestSetDAO {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private transient TestSetRepository repository;

    @Autowired
    public void setRepository(TestSetRepository repository) {
        this.repository = repository;
        super.setRepository(repository);
    }

    @Override
    public List<TestSet> getTestSetListByProject(Project project) throws LocalizedException {
        try {
            List<TestSet> testSetList = repository.findByProjectAndDeletedOrderByIdDesc(project, TedamBoolean.FALSE.getBooleanValue());
            return testSetList;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public List<TestSet> getTestSetListByProjectAndFolder(Project project, TedamFolder folder) throws LocalizedException {
        try {
            List<TestSet> testSetList = repository.findByProjectAndTestSetFolderIdAndDeletedOrderByIdDesc(project, folder.getId(), TedamBoolean.FALSE.getBooleanValue());
            return testSetList;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public TestSet getTestSetByName(String name) throws LocalizedException {
        try {
            TestSet testSet = repository.findByName(name);
            return testSet;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public void deleteTestSetFolderId(Integer testSetFolderId) throws LocalizedException {
        try {
            repository.deleteTestSetFolderId(testSetFolderId);
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public List<Object[]> findByTestSetIdRange(Integer testSetIdStart, Integer testSetIdEnd, boolean deleted)
            throws LocalizedException {
        try {
            return repository.findByTestSetIdRange(testSetIdStart, testSetIdEnd, deleted);
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public void resetTestSet(Integer jobId) throws LocalizedException {
        try {
            repository.resetTestSet(jobId);
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

}
