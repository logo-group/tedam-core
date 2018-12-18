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

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lbs.tedam.model.TestCaseTestRun;

/**
 * Repository for entity TestCaseTestRun.
 */
public interface TestCaseTestRunRepository extends BaseRepository<TestCaseTestRun, Integer> {

	@Query("select run from TestCaseTestRun run where run.testCaseId between :testCaseIdStart and :testCaseIdEnd and run.deleted = :deleted group by run.testCaseId order by run.testCaseId desc, run.id desc")
    public List<TestCaseTestRun> findByTestCaseIdRange(@Param("testCaseIdStart") Integer testCaseIdStart, @Param("testCaseIdEnd") Integer testCaseIdEnd, @Param("deleted") boolean deleted);

	@Query("select run from TestCaseTestRun run where run.testSetId between :testSetIdStart and :testSetIdEnd and run.deleted = :deleted group by run.testSetId order by run.testSetId desc, run.id desc")
    public List<TestCaseTestRun> findByTestSetIdRange(@Param("testSetIdStart") Integer testSetIdStart, @Param("testSetIdEnd") Integer testSetIdEnd, @Param("deleted") boolean deleted);

}
