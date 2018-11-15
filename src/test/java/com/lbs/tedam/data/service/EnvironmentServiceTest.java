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

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lbs.tedam.data.config.DataConfig;
import com.lbs.tedam.data.service.impl.EnvironmentServiceImpl;
import com.lbs.tedam.data.service.impl.JobParameterValueServiceImpl;
import com.lbs.tedam.data.service.impl.JobServiceImpl;
import com.lbs.tedam.data.service.impl.ProjectServiceImpl;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.Environment;
import com.lbs.tedam.model.JobParameterValue;
import com.lbs.tedam.model.Project;
import com.lbs.tedam.test.BaseServiceTest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { EnvironmentServiceImpl.class, ProjectServiceImpl.class, TestDataConfig.class,
		JobServiceImpl.class,
		DataConfig.class, JobParameterValueServiceImpl.class })
public class EnvironmentServiceTest extends BaseServiceTest {

    @Autowired
    private EnvironmentService environmentServiceImpl;

    @Autowired
    private ProjectService projectServiceImpl;

    @Autowired
    private JobParameterValueService jobParameterValueService;

    @Test
    public void testGetEnvironmentListByProject() throws LocalizedException {
        Project project = projectServiceImpl.getById(1);
        List<Environment> environmentList = environmentServiceImpl.getEnvironmentListByProject(project);
        Assert.assertNotEquals(environmentList.size(), 0);
    }

    @Test
    public void testUploadJobParameterValueToAllEnvironments() throws LocalizedException {
        Project project = projectServiceImpl.getById(1);
        JobParameterValue jobParameterValue = environmentServiceImpl.getById(1).getJobParameterValues().get(0);
        environmentServiceImpl.uploadJobParameterValueToAllEnvironments(jobParameterValue, project, "canberk.erkmen");
    }

}
