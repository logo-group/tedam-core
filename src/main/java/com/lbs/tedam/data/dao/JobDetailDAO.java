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

package com.lbs.tedam.data.dao;

import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.Client;
import com.lbs.tedam.model.JobDetail;
import com.lbs.tedam.model.Project;
import com.lbs.tedam.model.TestSet;
import com.lbs.tedam.util.EnumsV2.CommandStatus;

import java.util.List;

public interface JobDetailDAO extends BaseDAO<JobDetail, Integer> {

    /**
     * this method getJobDetailListByJobId returns the jobDetail list to the given jobId.<br>
     *
     * @param jobId
     * @return <br>
     * @throws LocalizedException
     * @author Tarik.Mikyas
     */
    List<JobDetail> getJobDetailListByJobId(int jobId) throws LocalizedException;

    /**
     * this method updateJobDetail <br>
     *
     * @param jobDetail
     * @param jobDetailStatus
     * @return <br>
     * @throws LocalizedException
     * @author Canberk.Erkmen
     */
    void updateJobDetailStatus(JobDetail jobDetail, CommandStatus jobDetailStatus) throws LocalizedException;

    /**
     * this method getJobDetailListByProject <br>
     *
     * @param project
     * @return
     * @throws LocalizedException <br>
     * @author Canberk.Erkmen
     */
    List<JobDetail> getJobDetailListByProject(Project project) throws LocalizedException;

    /**
     * this method getJobDetailByClient <br>
     *
     * @param client
     * @return
     * @throws LocalizedException <br>
     * @author Canberk.Erkmen
     */
    List<JobDetail> getJobDetailByClient(Client client) throws LocalizedException;

    /**
     * this method getJobDetailListByTestSet <br>
     *
     * @param testSet
     * @return <br>
     * @author Canberk.Erkmen
     */
    List<JobDetail> getJobDetailListByTestSet(TestSet testSet) throws LocalizedException;

    public void resetJobDetail(Integer jobId) throws LocalizedException;

    public void resetJobDetailClient(Integer clientId) throws LocalizedException;
}
