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

import com.lbs.tedam.data.dao.DraftCommandDAO;
import com.lbs.tedam.data.repository.DraftCommandRepository;
import com.lbs.tedam.exception.localized.GeneralLocalizedException;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.DraftCommand;
import com.lbs.tedam.model.Project;
import com.lbs.tedam.util.EnumsV2.TedamBoolean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DraftCommandDAOImpl extends BaseDAOImpl<DraftCommand, Integer> implements DraftCommandDAO {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private transient DraftCommandRepository repository;

    @Autowired
    public void setRepository(DraftCommandRepository repository) {
        this.repository = repository;
        super.setRepository(repository);
    }

    @Override
    public List<DraftCommand> getDraftCommandListByProject(Project project) throws LocalizedException {
        try {
            List<DraftCommand> draftCommandList = repository.findByProjectAndDeleted(project, TedamBoolean.FALSE.getBooleanValue());
            return draftCommandList;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

}
