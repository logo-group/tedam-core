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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lbs.tedam.data.dao.ClientDAO;
import com.lbs.tedam.data.repository.ClientRepository;
import com.lbs.tedam.exception.localized.GeneralLocalizedException;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.Client;
import com.lbs.tedam.model.Project;
import com.lbs.tedam.util.EnumsV2.TedamBoolean;

@Component
public class ClientDAOImpl extends BaseDAOImpl<Client, Integer> implements ClientDAO {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    private transient ClientRepository repository;

    @Autowired
    public void setRepository(ClientRepository repository) {
        this.repository = repository;
        super.setRepository(repository);
    }

    @Override
    public List<Client> getClientListByProject(Project project) throws LocalizedException {
        try {
            List<Client> clientList = repository.findByProjectAndDeleted(project, TedamBoolean.FALSE.getBooleanValue());
            return clientList;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public List<Client> getClientList() throws LocalizedException {
        try {
            List<Client> clientList = repository.findByDeleted(TedamBoolean.FALSE.getBooleanValue());
            return clientList;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
	public Client getClientByNameAndProjectName(String clientName, String projectName) throws LocalizedException {
        try {
			Client client = repository.findByNameAndDeletedAndProjectName(clientName,
					TedamBoolean.FALSE.getBooleanValue(), projectName);
            return client;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

}
