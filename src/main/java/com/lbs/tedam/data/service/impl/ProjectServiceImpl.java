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

import com.lbs.tedam.data.dao.ProjectDAO;
import com.lbs.tedam.data.service.ProjectService;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl extends BaseServiceImpl<Project, Integer> implements ProjectService {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private ProjectDAO dao;

    @Autowired
    public void setDao(ProjectDAO dao) {
        this.dao = dao;
        super.setBaseDao(dao);
    }

    @Override
    public List<Project> getProjectList() throws LocalizedException {
        List<Project> projectList = dao.getProjectList();
        return projectList;
    }

}
