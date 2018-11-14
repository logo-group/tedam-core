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

package com.lbs.tedam.data.service.impl;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbs.tedam.data.dao.JobDAO;
import com.lbs.tedam.data.service.JobService;
import com.lbs.tedam.exception.localized.JobDeleteException;
import com.lbs.tedam.exception.localized.JobPlannedDateExpiredException;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.Client;
import com.lbs.tedam.model.Job;
import com.lbs.tedam.model.JobDetail;
import com.lbs.tedam.model.Project;
import com.lbs.tedam.model.TedamUser;
import com.lbs.tedam.util.EnumsV2.CommandStatus;
import com.lbs.tedam.util.EnumsV2.JobStatus;

@Service
public class JobServiceImpl extends BaseServiceImpl<Job, Integer> implements JobService {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private JobDAO dao;

	@Autowired
	public void setDao(JobDAO dao) {
		this.dao = dao;
		super.setBaseDao(dao);
	}

	@Override
	public List<Job> getJobListByProject(Project project) throws LocalizedException {
		List<Job> jobList = dao.getJobList(project);
		return jobList;
	}

	@Override
	public List<Job> getCIJobListByProject(Project project) throws LocalizedException {
		List<Job> jobList = dao.getCIJobList(project);
		return jobList;
	}

	@Override
	public List<Job> getRunnableJobListByProject(Project project) throws LocalizedException {
		List<Job> jobList = dao.getRunnableJobList(project);
		return jobList;
	}

	@Override
	public void updateJobStatusAndExecutedDateByJobId(Integer jobId, JobStatus jobStatus,
			LocalDateTime lastExecutedStartDate, LocalDateTime lastExecutedEndDate) throws LocalizedException {
		dao.updateJobStatusAndExecutedDateByJobId(jobId, jobStatus, lastExecutedStartDate, lastExecutedEndDate);
	}

	@Override
	public Job saveJobAndJobDetailsStatus(Job job, JobStatus jobStatus, CommandStatus commandStatus,
			TedamUser executingUser) throws LocalizedException {
		job.setLastExecutingUser(executingUser);
		job.setDateUpdated(LocalDateTime.now());
		job.setStatus(jobStatus);
		for (JobDetail jobDetail : job.getJobDetails()) {
			jobDetail.setStatus(commandStatus);
			jobDetail.getTestSet().setTestSetStatus(commandStatus);
		}
		return save(job);
	}

	@Override
	public void beforeDelete(Integer id) throws LocalizedException {
		Job deleteJob = getById(id);
		if (deleteJob != null && (deleteJob.getStatus().equals(JobStatus.STARTED)
				|| deleteJob.getStatus().equals(JobStatus.QUEUED))) {
			throw new JobDeleteException();
		}
		super.beforeDelete(id);
	}

	@Override
	public void resetJob(Integer jobId) throws LocalizedException {
		dao.resetJob(jobId);
	}

	@Override
	public List<Integer> getJobIdListByEnvironmentId(Integer environmentId) throws LocalizedException {
		return dao.getJobIdListByEnvironmentId(environmentId);
	}

	@Override
	public void deleteJobClientByClientId(Integer clientId) throws LocalizedException {
		List<Job> jobList = dao.getJobListByClientId(clientId);
		for (Job job : jobList) {
			boolean jobModified = false;
			List<Client> clients = job.getClients();
			if (clients == null) {
				continue;
			}
			Iterator<Client> iterator = clients.iterator();
			while (iterator.hasNext()) {
				Client client = iterator.next();
				if (client.getId().equals(clientId)) {
					iterator.remove();
					jobModified = true;
				}
			}
			if (jobModified) {
				save(job);
			}
		}
	}

	@Override
	public void checkJobPlannedDate(Job entity) throws JobPlannedDateExpiredException {
		LocalDateTime plannedDate = entity.getPlannedDate();
		if (plannedDate != null && LocalDateTime.now().compareTo(plannedDate) >= 0) {
			throw new JobPlannedDateExpiredException();
		}
	}

	@Override
	public void beforeSave(Job entity) throws LocalizedException {
		checkJobPlannedDate(entity);
		super.beforeSave(entity);
	}

}
