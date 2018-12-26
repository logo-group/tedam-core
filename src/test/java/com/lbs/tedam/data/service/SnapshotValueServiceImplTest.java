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

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lbs.tedam.data.config.DataConfig;
import com.lbs.tedam.data.service.impl.MenuPathServiceImpl;
import com.lbs.tedam.data.service.impl.PropertyServiceImpl;
import com.lbs.tedam.data.service.impl.SnapshotDefinitionServiceImpl;
import com.lbs.tedam.data.service.impl.SnapshotValueServiceImpl;
import com.lbs.tedam.exception.DifferencesSnapshotException;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.SnapshotValue;
import com.lbs.tedam.test.BaseServiceTest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SnapshotValueServiceImpl.class, SnapshotDefinitionServiceImpl.class, PropertyServiceImpl.class, MenuPathServiceImpl.class, TestDataConfig.class,
		DataConfig.class })
public class SnapshotValueServiceImplTest extends BaseServiceTest {

	@Autowired
	private SnapshotValueService snapshotValueService;

	@Test
	public void testFindBySnapshotDefinitionId() throws LocalizedException {
		List<SnapshotValue> snapshotValList = snapshotValueService.getSnapshotValueList(37, null);
		Assert.assertNotEquals(0, snapshotValList.size());
	}

	@Test
	public void testFindBySnapshotDefinitionIdAndRowIndexNot() throws LocalizedException {
		List<SnapshotValue> snapshotValList = snapshotValueService.getSnapshotValueList(37, -1);
		Assert.assertNotEquals(0, snapshotValList.size());
	}

	@Test
	public void testFindByVersionLike() throws LocalizedException {
		List<SnapshotValue> snapshotValList = snapshotValueService.getSnapshotValueListByVersion("33.08");
		Assert.assertNotEquals(0, snapshotValList.size());
	}

	@Test
	public void testGetLatestVersionOfSnapshotValue() throws LocalizedException {
		SnapshotValue snapshotVal = snapshotValueService.getLatestVersionOfSnapshotValue("197001", 37);
		Assert.assertNotNull(snapshotVal);
	}

	@Test
	public void testGetSnapshotValuesVersionedRowIndexEquals() throws LocalizedException {
		List<SnapshotValue> snapshotValList = snapshotValueService.getSnapshotValuesVersioned("'2.36.6.0'", 37, "", true);
		Assert.assertNotEquals(0, snapshotValList.size());
	}

	@Test
	public void testGetSnapshotValuesVersionedRowIndexNotEquals() throws LocalizedException {
		List<SnapshotValue> snapshotValList = snapshotValueService.getSnapshotValuesVersioned("'2.36.6.0'", 37, "ROW_INDEX,RUN_ORDER", false);
		Assert.assertNotEquals(0, snapshotValList.size());
	}

	@Test
	public void test02GetLatestVersionOfSnapshotValue() throws LocalizedException {
		SnapshotValue snapshotVal;
		snapshotVal = snapshotValueService.getLatestVersionOfSnapshotValue("1001", 37);
		assertNotNull(snapshotVal);
	}

	@Test
	public void test03GetValuesVersioned() throws LocalizedException {
		List<SnapshotValue> snapshotValList = snapshotValueService.getSnapshotValuesVersioned("2.33.08.0", 37, "ID", false);
		Assert.assertNotEquals(0, snapshotValList.size());

	}

	@Test
	public void test04GetSnapshotValueListByVersion() throws LocalizedException {
		List<SnapshotValue> snapshotValList = snapshotValueService.getSnapshotValueListByVersion("2.45.16.0");
		Assert.assertNotEquals(0, snapshotValList.size());
	}

	@Test
	@Transactional
	public void testGetSnapshotValuesFromFile() throws LocalizedException {
		try {
			List<SnapshotValue> snapshotValList = snapshotValueService.getSnapshotValuesFromFile("", getFilePathFromSourceName("/DifferencesSnapshots1.xml"), 37);
			Assert.assertNotEquals(0, snapshotValList.size());
		} catch (DifferencesSnapshotException e) {
			e.printStackTrace();
		}
	}

}