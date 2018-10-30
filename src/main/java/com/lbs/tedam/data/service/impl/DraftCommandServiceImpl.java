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

import com.lbs.tedam.data.dao.DraftCommandDAO;
import com.lbs.tedam.data.service.DraftCommandService;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.DraftCommand;
import com.lbs.tedam.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DraftCommandServiceImpl extends BaseServiceImpl<DraftCommand, Integer> implements DraftCommandService {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private DraftCommandDAO dao;

    @Autowired
    public void setDao(DraftCommandDAO dao) {
        this.dao = dao;
        super.setBaseDao(dao);
    }

    @Override
    public List<DraftCommand> getDraftCommandListByProject(Project project) throws LocalizedException {
        List<DraftCommand> draftCommandList = dao.getDraftCommandListByProject(project);
        return draftCommandList;
    }

}
