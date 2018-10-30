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

import com.lbs.tedam.model.*;

public interface JobReportService {

    /**
     * this method addJobReportMap <br>
     *
     * @param jobReport
     * @param jobReportResult <br>
     * @author Canberk.Erkmen
     */
    public void addJobReportMap(JobReport jobReport, JobReportResult jobReportResult);

    /**
     * this method removeJobReportMap <br>
     *
     * @param jobId <br>
     * @author Canberk.Erkmen
     */
    public void removeJobReportMap(int jobId);

    /**
     * this method getJobReportByParams <br>
     *
     * @param testCase
     * @param jobDetail
     * @return <br>
     * @author Canberk.Erkmen
     */
    public JobReport getJobReportByParams(TestCase testCase, JobDetail jobDetail);

    /**
     * this method getJobReportResultByParams <br>
     *
     * @param jobCommand
     * @return <br>
     * @author Canberk.Erkmen
     */
    public JobReportResult getJobReportResultByParams(JobCommand jobCommand);

    /**
     * this method createJobReportFile <br>
     *
     * @param jobId
     * @param jobReportFilePath <br>
     * @author Canberk.Erkmen
     */
    public void createJobReportFile(int jobId, String jobReportFilePath);

}
