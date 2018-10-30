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

import com.lbs.tedam.data.dao.TedamFolderDAO;
import com.lbs.tedam.data.service.TedamFolderService;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.Project;
import com.lbs.tedam.model.TedamFolder;
import com.lbs.tedam.util.EnumsV2.TedamFolderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TedamFolderServiceImpl extends BaseServiceImpl<TedamFolder, Integer> implements TedamFolderService {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private TedamFolderDAO dao;

    @Autowired
    public void setDao(TedamFolderDAO dao) {
        this.dao = dao;
        super.setBaseDao(dao);
    }

    @Override
    public List<TedamFolder> getTedamFolderListByProjectAndFolderType(Project project, TedamFolderType folderType) throws LocalizedException {
        List<TedamFolder> tedamFolderList = dao.getTedamFolderListByProjectAndFolderType(project, folderType);
        return tedamFolderList;
    }

    @Override
    public TedamFolder findTedamFolder(List<TedamFolder> rootItems, Integer folderId) {
        if (rootItems != null && rootItems.size() > 0) {
            for (TedamFolder tedamFolder : rootItems) {
                if (tedamFolder.getId().equals(folderId)) {
                    return tedamFolder;
                } else {
                    TedamFolder findTedamFolder = findTedamFolder(tedamFolder.getChildFolders(), folderId);
                    if (findTedamFolder == null) {
                        continue;
                    } else {
                        return findTedamFolder;
                    }
                }
            }
        }
        return null;
    }

}