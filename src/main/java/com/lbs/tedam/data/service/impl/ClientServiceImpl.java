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

import com.lbs.tedam.data.dao.ClientDAO;
import com.lbs.tedam.data.service.ClientService;
import com.lbs.tedam.data.service.JobDetailService;
import com.lbs.tedam.data.service.JobService;
import com.lbs.tedam.data.service.TedamUserFavoriteService;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.Client;
import com.lbs.tedam.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl extends BaseServiceImpl<Client, Integer> implements ClientService {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private ClientDAO dao;

    @Autowired
    private TedamUserFavoriteService tedamUserFavoriteService;

    @Autowired
    private JobDetailService jobDetailService;

    @Autowired
    private JobService jobService;

    @Autowired
    public void setDao(ClientDAO dao) {
        this.dao = dao;
        super.setBaseDao(dao);
    }

    @Override
    public List<Client> getClientListByProject(Project project) throws LocalizedException {
        List<Client> clientList = dao.getClientListByProject(project);
        return clientList;
    }

    @Override
    public List<Client> getClientList() throws LocalizedException {
        List<Client> clientList = dao.getClientList();
        return clientList;
    }

    @Override
    public Client getClientByName(String clientName) throws LocalizedException {
        Client client = dao.getClientByName(clientName);
        return client;
    }

    @Override
    public void afterDeleteByLogic(Integer id) throws LocalizedException {
        jobService.deleteJobClientByClientId(id);
        jobDetailService.resetJobDetailClient(id);
        tedamUserFavoriteService.deleteClientFavorite(id);
        super.afterDeleteByLogic(id);
    }

}
