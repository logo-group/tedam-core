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

import com.lbs.tedam.data.dao.JobDetailDAO;
import com.lbs.tedam.data.service.JobDetailService;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.Client;
import com.lbs.tedam.model.JobDetail;
import com.lbs.tedam.model.Project;
import com.lbs.tedam.model.TestSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobDetailServiceImpl extends BaseServiceImpl<JobDetail, Integer> implements JobDetailService {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private JobDetailDAO dao;

    @Autowired
    public void setDao(JobDetailDAO dao) {
        this.dao = dao;
        super.setBaseDao(dao);
    }

    @Override
    public List<JobDetail> getJobDetailListByJobId(int jobId) throws LocalizedException {
        List<JobDetail> jobDetailList = dao.getJobDetailListByJobId(jobId);
        return jobDetailList;
    }

    @Override
    public List<JobDetail> getJobDetailListByProject(Project project) throws LocalizedException {
        List<JobDetail> jobDetailList = dao.getJobDetailListByProject(project);
        return jobDetailList;
    }

    @Override
    public List<JobDetail> getJobDetailByClient(Client client) throws LocalizedException {
        List<JobDetail> jobDetail = dao.getJobDetailByClient(client);
        return jobDetail;
    }

    @Override
    public List<JobDetail> getJobDetailListByTestSet(TestSet testSet) throws LocalizedException {
        List<JobDetail> jobDetailList = dao.getJobDetailListByTestSet(testSet);
        return jobDetailList;
    }

    @Override
    public void resetJobDetail(Integer jobId) throws LocalizedException {
        dao.resetJobDetail(jobId);
    }

    @Override
    public void resetJobDetailClient(Integer clientId) throws LocalizedException {
        dao.resetJobDetailClient(clientId);
    }

}
