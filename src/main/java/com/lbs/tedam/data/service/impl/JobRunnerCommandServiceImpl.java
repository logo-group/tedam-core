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

import com.lbs.tedam.data.service.JobRunnerCommandService;
import com.lbs.tedam.data.service.JobRunnerDetailCommandService;
import com.lbs.tedam.model.JobDetail;
import com.lbs.tedam.model.JobRunnerCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobRunnerCommandServiceImpl implements JobRunnerCommandService {

    private final JobRunnerDetailCommandService jobRunnerDetailCommandService;

    @Autowired
    public JobRunnerCommandServiceImpl(JobRunnerDetailCommandService jobRunnerDetailCommandService) {
        this.jobRunnerDetailCommandService = jobRunnerDetailCommandService;
    }

    @Override
    public JobRunnerCommand createJobRunnerCommand(JobDetail jobDetail) {
        JobRunnerCommand jobRunnerCommand = new JobRunnerCommand();
        jobRunnerCommand.setClient(jobDetail.getClient());
        jobRunnerCommand.setTestSetId(jobDetail.getTestSet().getId());
        jobRunnerCommand.setJobRunnerDetailCommandList(jobRunnerDetailCommandService.createJobRunnerDetailCommandByJobCommand(jobDetail));
        return jobRunnerCommand;
    }

}
