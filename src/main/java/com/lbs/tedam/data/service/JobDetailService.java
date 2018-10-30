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

import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.Client;
import com.lbs.tedam.model.JobDetail;
import com.lbs.tedam.model.Project;
import com.lbs.tedam.model.TestSet;

import java.util.List;

/**
 * @author Tarik.Mikyas<br>
 */
public interface JobDetailService extends BaseService<JobDetail, Integer> {

    /**
     * this method getJobDetailListByJobId Returns the jobDetail list based on the given jobId.<br>
     *
     * @param jobId
     * @return <br>
     * @throws LocalizedException
     * @author Tarik.Mikyas
     */
    public List<JobDetail> getJobDetailListByJobId(int jobId) throws LocalizedException;

    /**
     * this method getJobDetailListByTestSetIdList returns the project detail list of the project.<br>
     *
     * @param project
     * @return <br>
     * @throws LocalizedException
     * @author Canberk.Erkmen
     */
    public List<JobDetail> getJobDetailListByProject(Project project) throws LocalizedException;

    /**
     * this method getJobDetailByClientId returns the JobDetail attached to the given client.<br>
     *
     * @param client
     * @return
     * @throws LocalizedException <br>
     * @author Canberk.Erkmen
     */
    public List<JobDetail> getJobDetailByClient(Client client) throws LocalizedException;

    /**
     * this method getJobDetailListByTestSet returns the jobdetail list associated with the given testset.<br>
     *
     * @param testSet
     * @return
     * @throws LocalizedException <br>
     * @author Canberk.Erkmen
     */
    public List<JobDetail> getJobDetailListByTestSet(TestSet testSet) throws LocalizedException;

    public void resetJobDetail(Integer jobId) throws LocalizedException;

    public void resetJobDetailClient(Integer clientId) throws LocalizedException;

}
