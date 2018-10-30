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

import com.lbs.tedam.data.dao.TestTimeLogDAO;
import com.lbs.tedam.data.service.TestTimeLogService;
import com.lbs.tedam.model.TestTimeLog;
import com.lbs.tedam.recorder.TestStepTimeRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class TestTimeLogServiceImpl extends BaseServiceImpl<TestTimeLog, Integer> implements TestTimeLogService {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private TestTimeLogDAO dao;

    @Autowired
    public void setDao(TestTimeLogDAO dao) {
        this.dao = dao;
        super.setBaseDao(dao);
    }

    @Override
    public List<TestTimeLog> convertTimeRecord2TestTimeLog(List<TestStepTimeRecord> testStepTimeRecordList) {
        List<TestTimeLog> logList = new LinkedList<>();
        for (TestStepTimeRecord timeRecord : testStepTimeRecordList) {
            TestTimeLog testTimeLog = new TestTimeLog();
            testTimeLog.setDuration(timeRecord.getDuration());
            testTimeLog.setLogDate(timeRecord.getStartDate());
            testTimeLog.setRelease(timeRecord.getRelease());
            testTimeLog.setTestStepId(timeRecord.getTestStepId());
            logList.add(testTimeLog);
        }
        return logList;
    }

}
