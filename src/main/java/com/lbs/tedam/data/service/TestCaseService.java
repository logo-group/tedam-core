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

import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.Project;
import com.lbs.tedam.model.TedamFolder;
import com.lbs.tedam.model.TestCase;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Canberk.Erkmen
 */
public interface TestCaseService extends BaseService<TestCase, Integer> {

    public List<TestCase> getTestCaseListByProject(Project project) throws LocalizedException;

    public TestCase getTestCaseByName(String name) throws LocalizedException;

    public boolean isTestSetInProgressStatus(TestCase testCase) throws LocalizedException;

    public void updateTestCaseExecutionDateTime(TestCase testCase, LocalDateTime endDate) throws LocalizedException;

    public List<TestCase> getTestCaseListByProjectAndFolder(Project project, TedamFolder tedamFolder) throws LocalizedException;

}
