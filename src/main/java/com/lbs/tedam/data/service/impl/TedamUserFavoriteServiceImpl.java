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

import com.lbs.tedam.data.dao.TedamUserFavoriteDAO;
import com.lbs.tedam.data.service.TedamUserFavoriteService;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.TedamUserFavorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TedamUserFavoriteServiceImpl extends BaseServiceImpl<TedamUserFavorite, Integer>
        implements TedamUserFavoriteService {

    private static final long serialVersionUID = 1L;

    private TedamUserFavoriteDAO dao;

    @Autowired
    public void setDao(TedamUserFavoriteDAO dao) {
        this.dao = dao;
        super.setBaseDao(dao);
    }

    @Override
    public void deleteClientFavorite(Integer clientId) throws LocalizedException {
        dao.deleteClientFavorite(clientId);
    }

}
