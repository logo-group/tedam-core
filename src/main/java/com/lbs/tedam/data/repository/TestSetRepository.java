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
import com.lbs.tedam.model.TestSet;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Repository for entity TestSet.
 */
public interface TestSetRepository extends BaseRepository<TestSet, Integer> {

    List<TestSet> findByProjectAndDeletedOrderByIdDesc(Project project, boolean deleted);

    TestSet findByName(String name);

    @Transactional
    @Modifying
    @Query("update TestSet j set j.testSetFolderId = NULL where j.testSetFolderId = ?1")
    public void deleteTestSetFolderId(Integer testSetFolderId);

    public List<TestSet> findByProjectAndTestSetFolderIdAndDeletedOrderByIdDesc(Project project, Integer testSetFolderId, boolean deleted);

    @Query("select tstc.testSetId, tstc.testCase.id from TestSetTestCase tstc where tstc.testSetId between :testSetIdStart and :testSetIdEnd and tstc.deleted = :deleted order by tstc.testSetId desc, tstc.id desc")
    public List<Object[]> findByTestSetIdRange(@Param("testSetIdStart") Integer testSetIdStart, @Param("testSetIdEnd") Integer testSetIdEnd, @Param("deleted") boolean deleted);

    @Transactional
    @Modifying
    @Query("update TestSet ts set ts.testSetStatus = 0 where ts.id IN (select jd.testSetId from JobDetail jd where jd.jobId = ?1)")
    public void resetTestSet(Integer jobId);

}
