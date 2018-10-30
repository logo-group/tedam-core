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

package com.lbs.tedam.data.dao;

import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.Project;
import com.lbs.tedam.model.TedamFolder;
import com.lbs.tedam.model.TestSet;

import java.util.List;

public interface TestSetDAO extends BaseDAO<TestSet, Integer> {

    public List<TestSet> getTestSetListByProject(Project project) throws LocalizedException;

    public TestSet getTestSetByName(String name) throws LocalizedException;

    public void deleteTestSetFolderId(Integer testSetFolderId) throws LocalizedException;

    public List<TestSet> getTestSetListByProjectAndFolder(Project project, TedamFolder folder) throws LocalizedException;

    public List<Object[]> findByTestSetIdRange(Integer testSetIdStart, Integer testSetIdEnd, boolean deleted) throws LocalizedException;

    public void resetTestSet(Integer jobId) throws LocalizedException;

}
