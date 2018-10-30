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

package com.lbs.tedam.recorder;

import com.lbs.tedam.util.Constants;

/**
 * Time record for each TestTimeLog.
 */
public class TestStepTimeRecord extends TimeRecord {

    /**
     * Test step id.
     */
    private int testStepId = Constants.ZERO;

    /**
     * Release.
     */
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
