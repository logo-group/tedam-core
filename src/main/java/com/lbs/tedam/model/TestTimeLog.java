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

import com.lbs.tedam.util.Constants;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * This entity is to hold time log of test operations.
 */
@Entity
@Where(clause = "IS_DELETED=0")
@Table(name = "TEST_TIME_LOG")
public class TestTimeLog extends AbstractBaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "TEST_STEP_ID", nullable = false, columnDefinition = "INT default 0")
    private int testStepId = Constants.ZERO;

    @Column(name = "LOG_DATE", columnDefinition = "DATETIME default null")
    private Date logDate = null;

    @Column(name = "DURATION", nullable = false, columnDefinition = "BIGINT default 0")
    private long duration = Constants.ZERO;

    @Column(name = "LOG_RELEASE", nullable = false, columnDefinition = "VARCHAR(255) default ''")
    private String release = Constants.EMPTY_STRING;

    /**
     * @return the testStepId
     */
    public int getTestStepId() {
        return testStepId;
    }

    /**
     * @param testStepId the testStepId to set
     */
    public void setTestStepId(int testStepId) {
        this.testStepId = testStepId;
    }

    /**
     * @return the logDate
     */
    public Date getLogDate() {
        return logDate;
    }

    /**
     * @param logDate the logDate to set
     */
    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    /**
     * @return the duration
     */
    public long getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(long duration) {
        this.duration = duration;
    }

    /**
     * @return the release
     */
    public String getRelease() {
        return release;
    }

    /**
     * @param release the release to set
     */
    public void setRelease(String release) {
        this.release = release;
    }

}
