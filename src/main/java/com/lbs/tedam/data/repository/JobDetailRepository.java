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

package com.lbs.tedam.data.repository;

import com.lbs.tedam.model.Client;
import com.lbs.tedam.model.JobDetail;
import com.lbs.tedam.model.Project;
import com.lbs.tedam.model.TestSet;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Repository for entity JobDetail.
 */
public interface JobDetailRepository extends BaseRepository<JobDetail, Integer> {

    /**
     * Finds list by job id.
     *
     * @param jobId Job id.
     * @return List of JobDetail.
     */
    public List<JobDetail> findByJobId(int jobId);

    /**
     * this method findByTestSetProjectAndTestSetDeleted <br>
     *
     * @param project
     * @param booleanValue
     * @return <br>
     * @author Canberk.Erkmen
     */
    public List<JobDetail> findByTestSetProjectAndTestSetDeleted(Project project, boolean deleted);

    /**
     * this method findByClientAndTestSetDeleted <br>
     *
     * @param client
     * @param booleanValue
     * @return <br>
     * @author Canberk.Erkmen
     */
    public List<JobDetail> findByClientAndTestSetDeleted(Client client, boolean deleted);

    /**
     * this method findByTestSetAndDeleted <br>
     *
     * @param testSet
     * @param deleted
     * @return <br>
     * @author Canberk.Erkmen
     */
    public List<JobDetail> findByTestSetAndDeleted(TestSet testSet, boolean deleted);

    @Transactional
    @Modifying
    @Query("update JobDetail jd set jd.status = 0, jd.client = null where jd.jobId = ?1")
    public void resetJobDetail(Integer jobId);

    @Transactional
    @Modifying
    @Query("update JobDetail jd set jd.client = null where jd.client.id = ?1")
    public void resetJobDetailClient(Integer clientId);

}
