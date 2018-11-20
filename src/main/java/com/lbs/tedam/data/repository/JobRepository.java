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

import com.lbs.tedam.model.Job;
import com.lbs.tedam.model.Project;
import com.lbs.tedam.util.EnumsV2.JobStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository for entity Job.
 */
public interface JobRepository extends BaseRepository<Job, Integer> {

    public List<Job> findByProjectAndDeleted(Project project, boolean deleted);

    public List<Job> findByProjectAndDeletedAndCi(Project project, boolean deleted, boolean ci);

    public List<Job> findByProjectAndDeletedAndActive(Project project, boolean deleted, boolean active);

    @Transactional
    @Modifying
    @Query("update Job j set j.status = ?2, j.lastExecutedStartDate = ?3 , j.lastExecutedEndDate = ?4 where j.id = ?1")
    public void updateJobStatusAndExecutedDateByJobId(Integer jobId, JobStatus jobStatus, LocalDateTime lastExecutedStartDate, LocalDateTime lastExecutedEndDate);

    @Transactional
    @Modifying
    @Query("update Job j set j.status = 0 where j.id = ?1")
    public void resetJob(Integer jobId);
    
    @Transactional
    @Modifying
    @Query("update Job j set j.plannedDate = null where j.id = ?1")
    public void resetJobPlannedDate(Integer jobId);

    @Query("select j.id from Job j where j.jobEnvironment.id = ?1")
    public List<Integer> getJobIdListByEnvironmentId(Integer environmentId);

    public List<Job> findByClientsId(Integer clientId);

}
