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

package com.lbs.tedam.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lbs.tedam.util.Enums.FileName;

/**
 * @author Ahmet.Izgi
 */
public final class PropUtils {
	/**
	 * Properties config
	 */
	private static final Properties config = new Properties();
	private static final Logger LOGGER = LoggerFactory.getLogger(PropUtils.class);

	private PropUtils() {
	}

	/**
	 * It loads the instantly given file and extracts the desired prop from it.
	 *
	 * @param key
	 * @param file
	 * @return
	 * @author Ahmet.Izgi
	 */
	public static String getProperty(String key, File f) {
		Properties tempProp = new Properties();
		try (FileInputStream input = new FileInputStream(f)) {
			tempProp.load(input);
		} catch (IOException e) {
			LOGGER.error("file operation failed {0}", e);
		}
		if (!tempProp.isEmpty()) {
			return tempProp.getProperty(key);
		}
		return null;
	}

	/**
	 * @param key
	 * @return <br>
	 *         this method getProperty
	 * @author Tarik.Mikyas
	 */
	public static String getProperty(String key) {
		if (config.size() < 1) {
			loadPropFile();
		}
		return config.getProperty(key);
	}

	/**
	 * @author Tarik.Mikyas <br>
	 *         this method loadPropFile
	 * @deprecated
	 */
	@Deprecated
	private static void loadPropFile() {
		try (InputStream inputStream = PropUtils.class.getClassLoader().getResourceAsStream(FileName.CONFIG_PROPERTIES.getName())) {
			config.load(inputStream);
		} catch (IOException e) {
			LOGGER.error("file operation fail {0}", e);
		}
	}

	/**
	 * @author Tarik.Mikyas <br>
	 *         this method loadPropFile
	 */
	public static void loadPropFile(String configFile) {
		LOGGER.info("loadPropFile starts. ConfigFile : {0}", configFile);
		File file = new File(configFile);
		try (FileInputStream input = new FileInputStream(file)) {
			config.load(input);
		} catch (IOException e) {
			LOGGER.error("file operation fail {0}", e);
		}
	}

	/**
	 * this method getAllKeys returns all keys in the property file. <br>
	 *
	 * @return <br>
	 * @author Tarik.Mikyas
	 */
	private static Set<Object> getAllKeys() {
		Set<Object> keys = config.keySet();
		return keys;
	}

	/**
	 * this method getMaxWaitTimeMiliisFromProperty Returns the highest entered millisecond in the config file. <br>
	 *
	 * @return <br>
	 * @author Tarik.Mikyas
	 */
	public static Long getMaxWaitTimeMillisFromProperty() {
		Set<Object> keys = getAllKeys();
		Long maxWaitMillis = 0L;
		for (Object key : keys) {
			try {
				Long waitMillis = Long.valueOf(config.getProperty((String) key));
				if (waitMillis > maxWaitMillis) {
					maxWaitMillis = waitMillis;
					LOGGER.info("Updating maxWaitMillis. :{0}", maxWaitMillis);
				}
			} catch (NumberFormatException e) {
				LOGGER.info("The data is not read.");
			}

		}
		LOGGER.info("maxWaitMillis : ", maxWaitMillis);
		return maxWaitMillis;

	}
}
