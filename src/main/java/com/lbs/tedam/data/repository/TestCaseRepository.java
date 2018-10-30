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

package com.lbs.tedam.data.repository;

import com.lbs.tedam.model.Project;
import com.lbs.tedam.model.TestCase;
import com.lbs.tedam.util.EnumsV2.CommandStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository for entity TestCase.
 */
public interface TestCaseRepository extends BaseRepository<TestCase, Integer> {

    List<TestCase> findByProjectAndDeletedOrderByIdDesc(Project project, boolean deleted);

    TestCase findByName(String name);

    @Transactional
    @Modifying
    @Query("update TestCase j set j.testCaseFolderId = NULL where j.testCaseFolderId = ?1")
    public void deleteTestCaseFolderId(Integer testCaseFolderId);

    @Query("select ts.testSetStatus from TestSet ts inner join ts.testSetTestCases tstc inner join tstc.testCase tc where tc.id = :testCaseId)")
    public List<CommandStatus> isTestSetInProgressStatus(@Param("testCaseId") Integer testCaseId);

    @Transactional
    @Modifying
    @Query("update TestCase tc set tc.executionDateTime= :endDate where tc.id = :testCaseId")
    void updateTestCaseExecutionDateTime(@Param("testCaseId") Integer testCaseId, @Param("endDate") LocalDateTime endDate);

    List<TestCase> findByProjectAndTestCaseFolderIdAndDeletedOrderByIdDesc(Project project, Integer testCaseFolderId, boolean deleted);

}
