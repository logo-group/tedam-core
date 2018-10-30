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

import com.lbs.tedam.data.dao.EnvironmentDAO;
import com.lbs.tedam.data.repository.EnvironmentRepository;
import com.lbs.tedam.exception.localized.GeneralLocalizedException;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.Environment;
import com.lbs.tedam.model.Project;
import com.lbs.tedam.util.EnumsV2.TedamBoolean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Canberk.Erkmen
 */
@Component
public class EnvironmentDAOImpl extends BaseDAOImpl<Environment, Integer> implements EnvironmentDAO {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private transient EnvironmentRepository repository;

    @Autowired
    public void setRepository(EnvironmentRepository repository) {
        this.repository = repository;
        super.setRepository(repository);
    }

    @Override
    public List<Environment> getEnvironmentListByProject(Project project) throws LocalizedException {
        try {
            List<Environment> environmentList = repository.findByProjectAndDeleted(project, TedamBoolean.FALSE.getBooleanValue());
            return environmentList;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

}
