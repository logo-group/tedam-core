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
import com.lbs.tedam.model.Job;
import com.lbs.tedam.model.Project;
import com.lbs.tedam.util.EnumsV2.JobStatus;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Canberk.Erkmen
 */
public interface JobDAO extends BaseDAO<Job, Integer> {

    List<Job> getJobList(Project project) throws LocalizedException;

    List<Job> getCIJobList(Project project) throws LocalizedException;

    List<Job> getRunnableJobList(Project project) throws LocalizedException;

    void updateJobStatusAndExecutedDateByJobId(Integer jobId, JobStatus jobStatus, LocalDateTime lastExecutedStartDate, LocalDateTime lastExecutedEndDate)
            throws LocalizedException;

    public void resetJob(Integer jobId) throws LocalizedException;

    public List<Integer> getJobIdListByEnvironmentId(Integer environmentId) throws LocalizedException;

    public List<Job> getJobListByClientId(Integer clientId) throws LocalizedException;

    public void resetJobPlannedDate(Integer jobId) throws LocalizedException;

}
