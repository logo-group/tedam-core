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

package com.lbs.tedam.util.enums;

import com.lbs.tedam.util.EnumsV2.JobStatus;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * The class created to test the JobStatus enum.
 *
 * @author Tarik.Mikyas<br>
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JobStatusTest {

    /**
     * @author Tarik.Mikyas <br>
     * this method test01CreateEnum
     */
    @Test
    public void test01CreateEnum() {
        JobStatus jobStatusNotStarted = JobStatus.NOT_STARTED;
        jobStatusNotStarted.getId();
        jobStatusNotStarted.getValue();
    }

}
