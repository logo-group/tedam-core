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

import com.lbs.tedam.data.config.DataConfig;
import com.lbs.tedam.data.service.TestDataConfig;
import com.lbs.tedam.test.BaseServiceTest;
import com.lbs.tedam.util.EnumsV2.JobStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestDataConfig.class, DataConfig.class})
public class JobTest extends BaseServiceTest {

    @Test
    public void testSetters() {
        Job job = new Job();
        job.setActive(false);
        job.setCi(true);
        job.setClients(new ArrayList<>());
        job.setJobDetails(new ArrayList<>());
        job.setJobEnvironment(null);
        job.setLastExecutedEndDate(LocalDateTime.now());
        job.setLastExecutedStartDate(LocalDateTime.now());
        job.setName("name");
        job.setProject(null);
        job.setStatus(JobStatus.PLANNED);
        job.setType(null);
    }

    @Test
    public void testGetters() {
        Job job = new Job();
        job.isActive();
        job.isCi();
        job.getClients();
        job.getJobDetails();
        job.getJobEnvironment();
        job.getLastExecutedEndDate();
        job.getLastExecutedStartDate();
        job.getName();
        job.getProject();
        job.getStatus();
        job.getType();

        assertTrue(job.getExecutionDuration().isEmpty());
        job.setLastExecutedStartDate(LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 10)));
        assertTrue(job.getExecutionDuration().isEmpty());
        job.setLastExecutedEndDate(LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 10)));
        assertNotEquals(job.getExecutionDuration().length(), 0);

        job.setLastExecutedEndDate(LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 10)));
        job.setLastExecutedStartDate(LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 10)));
        assertTrue(job.getExecutionDuration().isEmpty());

    }

    @Test
    public void testEquals() {
        Job job = new Job();
        job.equals(job);
        job.equals(null);
        job.equals(new Environment());

        Job val1 = new Job();
        job.equals(val1);
        val1.setId(2);
        job.equals(val1);

        job.setId(1);
        val1.setId(1);
        job.equals(val1);
    }

}
