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

package com.lbs.tedam.data.dao.impl;

import com.lbs.tedam.data.dao.JobParameterDAO;
import com.lbs.tedam.data.repository.JobParameterRepository;
import com.lbs.tedam.exception.localized.GeneralLocalizedException;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.JobParameter;
import com.lbs.tedam.model.Project;
import com.lbs.tedam.util.EnumsV2.TedamBoolean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class JobParameterDAOImpl extends BaseDAOImpl<JobParameter, Integer> implements JobParameterDAO {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private transient JobParameterRepository repository;

    @Autowired
    public void setRepository(JobParameterRepository repository) {
        this.repository = repository;
        super.setRepository(repository);
    }

    @Override
    public List<JobParameter> getJobParameterListByProject(Project project) throws LocalizedException {
        try {
            List<JobParameter> jobParameterList = repository.findByProjectAndDeleted(project, TedamBoolean.FALSE.getBooleanValue());
            return jobParameterList;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public void deleteByLogic(Integer id) throws LocalizedException {
        try {
            JobParameter jobParameter = getById(id);
            jobParameter.getJobParameterValues().forEach(jobParameterValue -> jobParameterValue.setDeleted(TedamBoolean.TRUE.getBooleanValue()));
            jobParameter.setDeleted(TedamBoolean.TRUE.getBooleanValue());
            jobParameter.setDateUpdated(LocalDateTime.now());
            repository.save(jobParameter);
        } catch (LocalizedException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public JobParameter getJobParameterByProjectAndName(Project project, String name) throws LocalizedException {
        try {
            JobParameter jobParameter = repository.findByProjectAndDeletedAndName(project, TedamBoolean.FALSE.getBooleanValue(), name);
            return jobParameter;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }
}
