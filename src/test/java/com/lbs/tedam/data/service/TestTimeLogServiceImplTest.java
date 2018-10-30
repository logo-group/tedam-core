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
import com.lbs.tedam.data.service.impl.TestTimeLogServiceImpl;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.TestTimeLog;
import com.lbs.tedam.recorder.TestStepTimeRecord;
import com.lbs.tedam.recorder.TestStepTimeRecorder;
import com.lbs.tedam.recorder.TimeRecorder;
import com.lbs.tedam.test.BaseServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestTimeLogServiceImpl.class, TestDataConfig.class, DataConfig.class})
public class TestTimeLogServiceImplTest extends BaseServiceTest {

    @Autowired
    private TestTimeLogService testTimeLogService;

    @Test
    public void testsaveList() throws LocalizedException {
        TimeRecorder<TestStepTimeRecord> recorder = new TestStepTimeRecorder();
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                }
            }
        };
        recorder.record(runnable);
        recorder.record(runnable);

        List<TestStepTimeRecord> recordList = recorder.getRecordList();
        List<TestTimeLog> logList = testTimeLogService.convertTimeRecord2TestTimeLog(recordList);

        testTimeLogService.save(logList);
    }

}
