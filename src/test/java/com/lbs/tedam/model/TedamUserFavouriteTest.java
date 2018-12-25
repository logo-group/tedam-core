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

package com.lbs.tedam.model;


import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lbs.tedam.data.config.DataConfig;
import com.lbs.tedam.data.service.TestDataConfig;
import com.lbs.tedam.test.BaseServiceTest;
import com.lbs.tedam.util.EnumsV2.TedamUserFavoriteType;


/**
 * @author Berk.Kemaloglu
 * @author Seyma.Sahin
 * TedamUserFavorite's unit test class
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestDataConfig.class, DataConfig.class})
public class TedamUserFavouriteTest extends BaseServiceTest {

    Client client = new Client();
    Job job = new Job();
    TedamUserFavoriteType tedamUserFavoriteType = TedamUserFavoriteType.CLIENT;
    TedamUserFavorite tedamUserFavorite2;


    @Test
    public void testGetters() {
        TedamUserFavorite tedamUserFavorite = new TedamUserFavorite();
        tedamUserFavorite.getTedamUserId();
        tedamUserFavorite.getFavoriteType();
        tedamUserFavorite.getObjectId();
        tedamUserFavorite.getClient();
        tedamUserFavorite.getJob();
        tedamUserFavorite.getName();
    }

    @Test
    public void testSetters() {
        TedamUserFavorite tedamUserFavorite = new TedamUserFavorite();
        tedamUserFavorite.setTedamUserId(3);
        tedamUserFavorite.setFavoriteType(tedamUserFavoriteType);
        tedamUserFavorite.setObjectId(3);
        tedamUserFavorite.setJob(job);
        tedamUserFavorite.getName();
        tedamUserFavorite.setClient(client);
        tedamUserFavorite.getName();
    }

	@Test
	public void testgetEnvironment() {
		TedamUserFavorite tedamUserFavorite = new TedamUserFavorite();
		Environment environment = new Environment();
		tedamUserFavorite.setEnvironment(environment);
		assertNotNull(tedamUserFavorite.getEnvironment());
	}

	@Test
	public void testGetNameWithEnvironment() {
		TedamUserFavorite tedamUserFavorite = new TedamUserFavorite();
		Environment environment = new Environment();
		environment.setName("Environment Name");
		tedamUserFavorite.setEnvironment(environment);
		assertNotNull(tedamUserFavorite.getName());
	}

    @Test
    public void testConstructor() {
        tedamUserFavorite2 = new TedamUserFavorite(tedamUserFavoriteType, 3);
    }


}
