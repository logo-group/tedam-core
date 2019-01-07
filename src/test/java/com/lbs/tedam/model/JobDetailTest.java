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

package com.lbs.tedam.model;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lbs.tedam.data.config.DataConfig;
import com.lbs.tedam.data.service.TestDataConfig;
import com.lbs.tedam.test.BaseServiceTest;
import com.lbs.tedam.util.EnumsV2.CommandStatus;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestDataConfig.class, DataConfig.class})
public class JobDetailTest extends BaseServiceTest {

    @Test
    public void testSetters() {
        JobDetail jobDetail = new JobDetail(null, new TestSet());
        jobDetail.setClient(null);
        jobDetail.setJobId(Integer.valueOf(1));
        jobDetail.setJobCommands(new ArrayList<>());
        jobDetail.setStatus(CommandStatus.NOT_STARTED);
        jobDetail.setTestSet(null);
        jobDetail.setTestSetId(1);
        jobDetail.setPosition(1);
		jobDetail.setJobGroupId(Integer.valueOf(1));
    }

    @Test
    public void testGetters() {
        JobDetail jobDetail = new JobDetail();
        jobDetail.getClient();
        jobDetail.getJobId();
        jobDetail.getJobCommands();
        jobDetail.getStatus();
        jobDetail.getTestSet();
        jobDetail.getPosition();
        jobDetail.getTestSetName();
		jobDetail.getJobGroupId();
    }

}
