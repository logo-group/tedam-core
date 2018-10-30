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

//package com.lbs.tedam.test;
//
//import org.junit.BeforeClass;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.w3c.dom.Element;
//
//import com.lbs.tedam.util.Constants;
//import com.lbs.tedam.util.PropUtils;
//import com.lbs.tedam.util.TedamFileUtils;
//
//public class BaseTedamTest {
//
//	private static String tempdir = "";
//	private static String resourceConfigFilePath = "";
//
//	@BeforeClass
//	public static void testLoadPropertyFile() {
//		String configFilePath = BaseTedamTest.class.getResource(Constants.RESOURCE_CONFIG_TEST_PROPERTIES).getFile();
//		resourceConfigFilePath = BaseTedamTest.class.getResource(Constants.RESOURCE_CONFIG_TEST_PROPERTIES).getFile();
//		Logger LOGGER = LoggerFactory.getLogger(BaseTedamTest.class);
//		PropUtils.loadPropFile(configFilePath);
//		tempdir = System.getProperty("java.io.tmpdir");
//		LOGGER.info("java.io.tmpdir :" + tempdir);
//		LOGGER.info("Property File Loaded configFilePath : " + configFilePath);
//	}
//
//	/**
//	 * @author Tarik.Mikyas
//	 * @param sourcePath
//	 * @return <br>
//	 *         this method getElementFromXmlFile
//	 */
//	protected Element getElementFromXmlFilePath(String sourceName) {
//		return TedamFileUtils.getElementFromXmlFile(getFilePathFromSourceName(sourceName));
//	}
//
//	/**
//	 * this method getFilePathFromSourceName <br>
//	 * @author Tarik.Mikyas
//	 * @param sourcePath
//	 * @return <br>
//	 */
//	protected String getFilePathFromSourceName(String sourceName) {
//		return getClass().getResource(sourceName).getFile();
//	}
//
//	/**
//	 * this method getTempdir fetches the defined temp file.<br>
//	 * @author Tarik.Mikyas
//	 * @return <br>
//	 */
//	public static String getTempdir() {
//		return tempdir + Constants.FILE_SEPARATOR;
//	}
//
//	/**
//	 * this method getResourceFilePath <br>
//	 * @author Tarik.Mikyas
//	 * @return <br>
//	 */
//	public static String getResourceConfigFilePath() {
//		return resourceConfigFilePath;
//	}
//
//}
