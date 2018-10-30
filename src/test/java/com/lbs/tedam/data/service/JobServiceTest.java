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

import com.lbs.tedam.data.config.DataConfig;
import com.lbs.tedam.data.service.impl.JobServiceImpl;
import com.lbs.tedam.data.service.impl.ProjectServiceImpl;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.Job;
import com.lbs.tedam.model.Project;
import com.lbs.tedam.model.TedamUser;
import com.lbs.tedam.test.BaseServiceTest;
import com.lbs.tedam.util.EnumsV2.CommandStatus;
import com.lbs.tedam.util.EnumsV2.JobStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {JobServiceImpl.class, ProjectServiceImpl.class, TestDataConfig.class, DataConfig.class})
public class JobServiceTest extends BaseServiceTest {

    @Autowired
    private JobService jobService;

    @Autowired
    private TedamUserService userService;

    @Autowired
    private ProjectService projectService;

    @Test
    public void testGetJobList() throws LocalizedException {
        Project project = projectService.getAll().get(0);
        List<Job> jobList = jobService.getJobListByProject(project);
        Assert.assertNotEquals(jobList.size(), 0);
    }

    @Test
    public void testGetRunnableJobList() throws LocalizedException {
        Project project = projectService.getAll().get(0);
        List<Job> jobList = jobService.getRunnableJobListByProject(project);
        Assert.assertNotEquals(jobList.size(), 0);
    }

    @Test
    public void testGetCIJobList() throws LocalizedException {
        Project project = projectService.getAll().get(0);
        List<Job> jobList = jobService.getCIJobListByProject(project);
        Assert.assertNotEquals(jobList.size(), 0);
    }

    @Test
    public void testUpdateJobStatusAndExecutedDateByJobId() throws LocalizedException {
        jobService.updateJobStatusAndExecutedDateByJobId(1, JobStatus.QUEUED, LocalDateTime.now(), LocalDateTime.now());
    }

    @Test
    public void testSaveJobAndJobDetailsStatus() throws LocalizedException {
        Job job = jobService.getById(90);
        TedamUser user = userService.getAll().get(0);
        jobService.saveJobAndJobDetailsStatus(job, JobStatus.PLANNED, CommandStatus.IN_PROGRESS, user);
    }

    @Test
    public void testSaveAndDelete() throws LocalizedException {
        Project project = projectService.getAll().get(2);
        Job job = new Job("testTedam", project);
        job = jobService.save(job);
        jobService.deleteByLogic(job.getId());
        Job job1 = new Job("testTedam", project);
        job1 = jobService.save(job1);
        jobService.deleteById(job1.getId());

    }

}
