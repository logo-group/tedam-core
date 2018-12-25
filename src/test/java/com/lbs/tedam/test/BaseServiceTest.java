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

package com.lbs.tedam.test;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

import com.lbs.tedam.config.ConfigTestContext;
import com.lbs.tedam.data.config.DataConfig;
import com.lbs.tedam.data.service.TestDataConfig;
import com.lbs.tedam.util.Constants;
import com.lbs.tedam.util.PropUtils;
import com.lbs.tedam.util.TedamFileUtils;

public class BaseServiceTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseServiceTest.class);

	private static String tempdir = System.getProperty("java.io.tmpdir");

	@BeforeClass
	public static void testLoadPropertyFile() {
		String configFilePath = BaseServiceTest.class.getResource(Constants.RESOURCE_CONFIG_TEST_PROPERTIES).getPath();
		PropUtils.loadPropFile(configFilePath);
		ConfigTestContext.registerConfig(TestDataConfig.class, DataConfig.class);
	}

	@Test
	public void testDummyBase() {
		String dummyContent = "dummy";
		assertNotNull(dummyContent);
	}

	/**
	 * this method getTempdir fetches the defined temp file.<br>
	 *
	 * @return <br>
	 * @author Tarik.Mikyas
	 */
	public static String getTempdir() {
		return tempdir + Constants.FILE_SEPARATOR;
	}

	/**
	 * @param sourcePath
	 * @return <br>
	 *         this method getElementFromXmlFile
	 * @author Tarik.Mikyas
	 */
	public Element getElementFromXmlFilePath(String sourceName) {
		return TedamFileUtils.getElementFromXmlFile(getFilePathFromSourceName(sourceName));
	}

	/**
	 * this method getFilePathFromSourceName <br>
	 *
	 * @param sourcePath
	 * @return <br>
	 * @author Tarik.Mikyas
	 */
	protected String getFilePathFromSourceName(String sourceName) {
		return getClass().getResource(sourceName).getFile();
	}

	/**
	 * @param sourcePath
	 * @return <br>
	 *         this method getFilePath
	 * @author Tarik.Mikyas
	 */
	public File getFilePath(String sourcePath) {
		URL resource = getClass().getResource(sourcePath);
		File file = null;
		try {
			file = (Paths.get(resource.toURI()).toFile()).getAbsoluteFile();
		} catch (URISyntaxException e) {
			LOGGER.error("" + e);
		}

		return file;
	}

}
