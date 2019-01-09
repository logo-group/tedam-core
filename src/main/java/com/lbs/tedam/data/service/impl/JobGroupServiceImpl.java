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

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbs.tedam.data.dao.JobGroupDAO;
import com.lbs.tedam.data.service.JobGroupService;
import com.lbs.tedam.exception.localized.JobGroupContainsRunningJobException;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.Job;
import com.lbs.tedam.model.JobGroup;
import com.lbs.tedam.model.Project;

@Service
public class JobGroupServiceImpl extends BaseServiceImpl<JobGroup, Integer> implements JobGroupService {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private JobGroupDAO dao;

	@Autowired
	public void setDao(JobGroupDAO dao) {
		this.dao = dao;
		super.setBaseDao(dao);
	}

	@Override
	public List<JobGroup> getJobGroupListByProject(Project project) throws LocalizedException {
		List<JobGroup> jobGroupList = dao.getJobGroupList(project);
		return jobGroupList;
	}

	@Override
	public List<JobGroup> getRunnableJobGroupListByProject(Project project) throws LocalizedException {
		List<JobGroup> jobGroupList = dao.getRunnableJobGroupList(project);
		return jobGroupList;
	}

	@Override
	public void checkForRunningJobGroups(JobGroup entity, Project project) throws LocalizedException {
		List<JobGroup> runningJobGroupList = dao.getRunningJobGroupList(project);
		List<Integer> jobIdList = new ArrayList<>();
		for (Job job : entity.getJobs()) {
			jobIdList.add(job.getId());
		}
		for (JobGroup jobGroup : runningJobGroupList) {
			if (jobGroup.equals(entity) == false) {
				for (Job job : jobGroup.getJobs()) {
					if (jobIdList.contains(job.getId())) {
						throw new JobGroupContainsRunningJobException(jobGroup.getId(), job.getId());
					}
				}
			}
		}
	}

}
