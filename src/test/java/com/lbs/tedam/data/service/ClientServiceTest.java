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

package com.lbs.tedam.data.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lbs.tedam.data.config.DataConfig;
import com.lbs.tedam.data.service.impl.ClientServiceImpl;
import com.lbs.tedam.data.service.impl.ProjectServiceImpl;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.Client;
import com.lbs.tedam.model.Project;
import com.lbs.tedam.test.BaseServiceTest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ClientServiceImpl.class, ProjectServiceImpl.class, TestDataConfig.class, DataConfig.class})
public class ClientServiceTest extends BaseServiceTest {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProjectService projectService;

    @Test
    public void testGetClientList() throws LocalizedException {
        List<Client> clientList = clientService.getClientList();
        Assert.assertNotEquals(clientList.size(), 0);
    }

    @Test
    public void testGetClientListByProject() throws LocalizedException {
        Project project = projectService.getAll().get(0);
        List<Client> clientList = clientService.getClientListByProject(project);
        Assert.assertNotEquals(clientList.size(), 0);
    }

    @Test
    public void testGetClientByName() throws LocalizedException {
		Client client = clientService.getClientByNameAndProjectName("Client2", "j-platform");
        Assert.assertNull(client);
    }

}
