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
import com.lbs.tedam.util.EnumsV2.ExecutionStatus;
import com.lbs.tedam.util.EnumsV2.TestRunType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestDataConfig.class, DataConfig.class})
public class TestRunTest extends BaseServiceTest {

    @Test
    public void testSetters() {
        TestRun testRun = new TestRun();
        testRun.setEndDate(LocalDateTime.now());
        testRun.setExecutionMessage("executionMessage");
        testRun.setExecutionStatus(ExecutionStatus.FAILED);
        testRun.setStartDate(LocalDateTime.now());
        testRun.setTestRunType(TestRunType.MANUAL);
        testRun.setVersion("version");
    }

    @Test
    public void testGetters() {
        TestRun testRun = new TestRun("version", TestRunType.MANUAL, ExecutionStatus.CAUTION, "executionMessage", null, null);
        testRun.getEndDate();
        testRun.getExecutionMessage();
        testRun.getExecutionStatus();
        testRun.getStartDate();
        testRun.getTestRunType();
        testRun.getVersion();
        testRun.getActualDuration();

        testRun.setEndDate(LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 10)));
        assertEquals(testRun.getActualDuration(), Integer.valueOf(0));
        testRun.setStartDate(LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 10)));
        assertNotEquals(testRun.getActualDuration(), Integer.valueOf(0));

        testRun.setEndDate(LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 10)));
        testRun.setStartDate(LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 10)));
        assertEquals(testRun.getActualDuration(), Integer.valueOf(0));

    }

}
