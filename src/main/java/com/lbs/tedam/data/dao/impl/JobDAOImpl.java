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

import com.lbs.tedam.data.dao.JobDAO;
import com.lbs.tedam.data.repository.JobRepository;
import com.lbs.tedam.exception.localized.GeneralLocalizedException;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.Job;
import com.lbs.tedam.model.Project;
import com.lbs.tedam.util.EnumsV2.JobStatus;
import com.lbs.tedam.util.EnumsV2.TedamBoolean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Canberk.Erkmen
 */
@Component
public class JobDAOImpl extends BaseDAOImpl<Job, Integer> implements JobDAO {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private transient JobRepository repository;

    @Autowired
    public void setRepository(JobRepository repository) {
        this.repository = repository;
        super.setRepository(repository);
    }

    @Override
    public List<Job> getJobList(Project project) throws LocalizedException {
        try {
            List<Job> jobList = repository.findByProjectAndDeleted(project, TedamBoolean.FALSE.getBooleanValue());
            return jobList;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public List<Job> getCIJobList(Project project) throws LocalizedException {
        try {
            List<Job> jobList = repository.findByProjectAndDeletedAndCi(project, TedamBoolean.FALSE.getBooleanValue(), TedamBoolean.TRUE.getBooleanValue());
            return jobList;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public List<Job> getRunnableJobList(Project project) throws LocalizedException {
        try {
            List<Job> jobList = repository.findByProjectAndDeletedAndActive(project, TedamBoolean.FALSE.getBooleanValue(), TedamBoolean.TRUE.getBooleanValue());
            return jobList;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public void updateJobStatusAndExecutedDateByJobId(Integer jobId, JobStatus jobStatus, LocalDateTime lastExecutedStartDate, LocalDateTime lastExecutedEndDate)
            throws LocalizedException {
        try {
            repository.updateJobStatusAndExecutedDateByJobId(jobId, jobStatus, lastExecutedStartDate, lastExecutedEndDate);
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }
    
	@Override
	public void resetJobPlannedDate(Integer jobId) throws LocalizedException {
		try {
			repository.resetJobPlannedDate(jobId);
		} catch (Exception e) {
			throw new GeneralLocalizedException(e);
		}
	}

    @Override
    public void deleteByLogic(Integer id) throws LocalizedException {
        try {
            Job job = getById(id);
            job.getJobDetails().forEach(jobDetail -> jobDetail.setDeleted(TedamBoolean.TRUE.getBooleanValue()));
            job.setDeleted(TedamBoolean.TRUE.getBooleanValue());
            job.setDateUpdated(LocalDateTime.now());
            repository.save(job);
        } catch (LocalizedException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public void resetJob(Integer jobId) throws LocalizedException {
        try {
            repository.resetJob(jobId);
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public List<Integer> getJobIdListByEnvironmentId(Integer environmentId) throws LocalizedException {
        try {
            return repository.getJobIdListByEnvironmentId(environmentId);
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public List<Job> getJobListByClientId(Integer clientId) throws LocalizedException {
        try {
            return repository.findByClientsId(clientId);
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

}
