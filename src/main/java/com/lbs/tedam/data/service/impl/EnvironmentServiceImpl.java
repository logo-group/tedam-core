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

import com.lbs.tedam.data.dao.EnvironmentDAO;
import com.lbs.tedam.data.service.EnvironmentService;
import com.lbs.tedam.data.service.JobService;
import com.lbs.tedam.exception.localized.EnvironmentInUseInJobException;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.Environment;
import com.lbs.tedam.model.JobParameterValue;
import com.lbs.tedam.model.Project;
import com.lbs.tedam.model.TedamUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnvironmentServiceImpl extends BaseServiceImpl<Environment, Integer> implements EnvironmentService {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private EnvironmentDAO dao;

    @Autowired
    private JobService jobService;

    @Autowired
    public void setDao(EnvironmentDAO dao) {
        this.dao = dao;
        super.setBaseDao(dao);
    }

    @Override
    public List<Environment> getEnvironmentListByProject(Project project) throws LocalizedException {
        List<Environment> environmentList = dao.getEnvironmentListByProject(project);
        return environmentList;
    }

    @Override
    public void uploadJobParameterValueToAllEnvironments(JobParameterValue selectedJobParameterValue, Project project, String userName) throws LocalizedException {
        List<Environment> environmentList = getEnvironmentListByProject(project);
        for (Environment environment : environmentList) {
            removeExistJobParameterValueInEnvironment(selectedJobParameterValue, environment);
            environment.getJobParameterValues().add(selectedJobParameterValue);
            environment.setUpdatedUser(userName);
            save(environment);
        }
    }

    private void removeExistJobParameterValueInEnvironment(JobParameterValue selectedJobParameterValue, Environment environment) {
        for (JobParameterValue jobParameterValue : environment.getJobParameterValues()) {
            if (selectedJobParameterValue.getJobParameterId().equals(jobParameterValue.getJobParameterId())) {
                environment.getJobParameterValues().remove(jobParameterValue);
                break;
            }
        }
    }

    @Override
    public List<Environment> getEnvironmentListWithFavorites(TedamUser user, Project project)
            throws LocalizedException {
        List<Environment> favoritesEnvironmentList = user.getUserFavoritesEnvironments(project);
        List<Environment> environmentList = getEnvironmentListByProject(project);
        for (Environment environment : environmentList) {
            boolean isSame = false;
            for (Environment favoriteEnvironment : favoritesEnvironmentList) {
                if (environment.getId().equals(favoriteEnvironment.getId())) {
                    isSame = true;
                }
            }
            if (!isSame) {
                favoritesEnvironmentList.add(environment);
            }
        }
        return favoritesEnvironmentList;

    }

    @Override
    public List<Environment> getEnvironmentListWithoutFavorites(TedamUser user, Project project)
            throws LocalizedException {
        List<Environment> environmentList = getEnvironmentListByProject(project);
        List<Environment> favoritesEnvironmentList = user.getUserFavoritesEnvironments(project);
        List<Environment> withoutFavoritesList = new ArrayList<>();
        for (Environment environment : environmentList) {
            boolean isFavorite = false;
            for (Environment favoriteEnvironment : favoritesEnvironmentList) {
                if (environment.getId().equals(favoriteEnvironment.getId())) {
                    isFavorite = true;
                }
            }
            if (!isFavorite) {
                withoutFavoritesList.add(environment);
            }
        }
        return withoutFavoritesList;
    }

    @Override
    public void beforeDelete(Integer id) throws LocalizedException {
        List<Integer> jobIdList = jobService.getJobIdListByEnvironmentId(id);
        if (jobIdList != null && jobIdList.size() > 0) {
            throw new EnvironmentInUseInJobException(jobIdList);
        }
        super.beforeDelete(id);
    }

}
