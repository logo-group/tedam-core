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

import com.lbs.tedam.data.config.DataConfig;
import com.lbs.tedam.data.service.impl.TedamUserServiceImpl;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.Project;
import com.lbs.tedam.model.TedamUser;
import com.lbs.tedam.test.BaseServiceTest;
import com.lbs.tedam.util.EnumsV2.TedamUserRole;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TedamUserServiceImpl.class, TestDataConfig.class, DataConfig.class})
public class TedamUserServiceTest extends BaseServiceTest {

    @Autowired
    private TedamUserService tedamUserService;

    @Test
    public void testGetActiveUserList() throws LocalizedException {
        List<TedamUser> userList = tedamUserService.getActiveUserList();
        Assert.assertNotEquals(userList.size(), 0);
    }

    @Test
    public void testGetRunnableJobList() throws LocalizedException {
        TedamUser user = tedamUserService.findByUserName("admin");
        Assert.assertNotNull(user);
    }

    @Test
    public void testCreateInitialUser() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        TedamUser admin = new TedamUser("admin", passwordEncoder.encode("logo"), TedamUserRole.ADMIN);

        Project jguar = new Project("j-platform", "j-platform");
        Project crm = new Project("CRM", "CRM");
        Project tedam = new Project("TEDAM Automation", "TEDAM Automation");

        List<Project> projectList = new ArrayList<>();
        projectList.add(jguar);
        projectList.add(crm);
        projectList.add(tedam);
        admin.setProjects(projectList);
        // tedamUserService.save(admin);
    }

}
