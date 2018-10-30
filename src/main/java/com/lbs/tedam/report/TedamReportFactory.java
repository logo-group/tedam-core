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

package com.lbs.tedam.report;

import com.lbs.tedam.model.JobReport;
import com.lbs.tedam.model.JobReportResult;

import java.util.HashMap;
import java.util.Map;

public class TedamReportFactory {

    private static Map<JobReport, JobReportResult> jobReportMap = null;

    private TedamReportFactory() {
        // private TedamReportFactory constructor
    }

    public synchronized static Map<JobReport, JobReportResult> getJobReportMap() {
        if (jobReportMap == null) {
            jobReportMap = new HashMap<>();
        }
        return jobReportMap;
    }

}
