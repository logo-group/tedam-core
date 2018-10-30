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
//

import com.lbs.tedam.data.dao.TedamFolderDAO;
import com.lbs.tedam.data.repository.TedamFolderRepository;
import com.lbs.tedam.exception.localized.GeneralLocalizedException;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.Project;
import com.lbs.tedam.model.TedamFolder;
import com.lbs.tedam.util.EnumsV2.TedamBoolean;
import com.lbs.tedam.util.EnumsV2.TedamFolderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TedamFolderDAOImpl extends BaseDAOImpl<TedamFolder, Integer> implements TedamFolderDAO {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private transient TedamFolderRepository repository;

    @Autowired
    public void setRepository(TedamFolderRepository repository) {
        this.repository = repository;
        super.setRepository(repository);
    }

    @Override
    public List<TedamFolder> getTedamFolderListByProjectAndFolderType(Project project, TedamFolderType folderType) throws LocalizedException {
        try {
            List<TedamFolder> tedamFolderList = repository.findByProjectAndFolderTypeAndDeleted(project, folderType, TedamBoolean.FALSE.getBooleanValue());
            return tedamFolderList;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }
}
