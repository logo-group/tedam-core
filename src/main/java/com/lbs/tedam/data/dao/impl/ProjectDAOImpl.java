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

import com.lbs.tedam.data.dao.ProjectDAO;
import com.lbs.tedam.data.repository.ProjectRepository;
import com.lbs.tedam.exception.localized.GeneralLocalizedException;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.Project;
import com.lbs.tedam.util.EnumsV2.TedamBoolean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Canberk.Erkmen
 */
@Component
public class ProjectDAOImpl extends BaseDAOImpl<Project, Integer> implements ProjectDAO {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private transient ProjectRepository repository;

    @Autowired
    public void setRepository(ProjectRepository repository) {
        this.repository = repository;
        super.setRepository(repository);
    }

    @Override
    public List<Project> getProjectList() throws LocalizedException {
        try {
            List<Project> projectList = repository.findByDeleted(TedamBoolean.FALSE.getBooleanValue());
            return projectList;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public Project getProjectByName(String projectName) throws LocalizedException {
        try {
            Project project = repository.findByName(projectName);
            return project;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

}
