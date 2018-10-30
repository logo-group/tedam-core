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
import com.lbs.tedam.data.service.impl.JobDetailServiceImpl;
import com.lbs.tedam.data.service.impl.TedamFolderServiceImpl;
import com.lbs.tedam.data.service.impl.TestCaseTestRunServiceImpl;
import com.lbs.tedam.data.service.impl.TestSetServiceImpl;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.JobDetail;
import com.lbs.tedam.model.TestSet;
import com.lbs.tedam.test.BaseServiceTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {JobDetailServiceImpl.class, TestSetServiceImpl.class, TedamFolderServiceImpl.class, TestCaseTestRunServiceImpl.class, TestDataConfig.class,
        DataConfig.class})
public class JobDetailServiceTest extends BaseServiceTest {

    @Autowired
    private JobDetailService jobDetailService;

    @Autowired
    private TestSetService testSetService;

    @Test
    public void test01GetJobDetailListByJobId() throws LocalizedException {
        List<JobDetail> jobList = jobDetailService.getJobDetailListByJobId(73);
        Assert.assertNotEquals(jobList.size(), 0);
    }

    @Test
    public void test02GetJobDetailListByTestSet() throws LocalizedException {
        TestSet testSet = testSetService.getAll().get(0);
        List<JobDetail> jobDetailList = jobDetailService.getJobDetailListByTestSet(testSet);
        Assert.assertNotEquals(jobDetailList.size(), 0);
    }

}
