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

import com.lbs.tedam.util.Enums.TedamLogLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TedamLogUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(TedamLogUtils.class);

    private TedamLogUtils() {
        // TedamLogUtils private constructor
    }

    /**
     * It writes the log message to the screen according to incoming parameters.
     *
     * @param header        where the procedure is called
     * @param key           property key
     * @param logLevel      message level
     * @param loggingStatus
     * @author Tarik.Mikyas
     */
    public static void log(String header, Object key, TedamLogLevel logLevel, Boolean loggingStatus) {
        String logString;
        if (loggingStatus) {
            String msg = PropUtils.getProperty((String) key);// here we send the key as a direct message
            logString = " # " + logLevel.getName() + " # " + DateTimeUtils.getTEDAMdbFormatSystemDateAsString() + " # " + header + " - " + (msg != null ? msg : key) + " #######";
            LOGGER.info(logString);
        }
    }
}
