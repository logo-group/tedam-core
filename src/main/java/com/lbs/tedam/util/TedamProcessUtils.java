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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

public class TedamProcessUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(TedamProcessUtils.class);

    private TedamProcessUtils() {
        // TedamProcessUtils private constructor
    }

    /**
     * this method sleepThread waits for the given time. <br>
     *
     * @param waitMillis <br>
     * @author Tarik.Mikyas
     */
    public static void sleepThread(long waitMillis) {
        try {
            Thread.sleep(waitMillis);
        } catch (InterruptedException e) {
            LOGGER.error("" + e);
            Thread.currentThread().interrupt();
        }

    }

    /**
     * this method launchDraftCommand Procedure to run the draft commands <br>
     *
     * @param command <br>
     * @author Tarik.Mikyas
     */

    public static boolean launchCommand(String command, String processClassPath, String lastExpectedResult) {
        String line = null;
        boolean isCommandFinishedSucces = false;
        ProcessBuilder probuilder = new ProcessBuilder(command.split(Constants.COMMAND_SPLIT_STRING));
        probuilder.redirectErrorStream(true);
        File projectFile = new File(processClassPath);
        LOGGER.info("projectFile :" + projectFile.getParentFile());
        probuilder.directory(projectFile.getParentFile());
        try (BufferedReader standartInput = new BufferedReader(new InputStreamReader(probuilder.start().getInputStream()))) {
            if (StringUtils.isEmpty(lastExpectedResult)) {// if there is no end result expected, the end of the command is considered correct.
                isCommandFinishedSucces = true;
            }
            while ((line = standartInput.readLine()) != null) {
                LOGGER.info(line);
                // if an expected end result is found and any line contains this end, the end of the command is correct.
                if (!isCommandFinishedSucces && line.contains(lastExpectedResult)) {
                    isCommandFinishedSucces = true;
                } // if there is an expected end result and if any line does not contain this end, the end of the command is incorrect.
            }
        } catch (IOException e) {
            LOGGER.error("" + e);
        }
        return isCommandFinishedSucces;
    }

    /**
     * this method getClassPath returns the path of the given class in runtime.<br>
     *
     * @return <br>
     * @author Tarik.Mikyas
     */
    public static String getClassPath(Class<?> clazz) {
        try {
            return clazz.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        } catch (URISyntaxException e) {
            LOGGER.error("" + e);
        }
        return null;
    }

    /**
     * this method getClientHostName returns the name of the client where it is running. <br>
     *
     * @return <br>
     * @author Canberk.Erkmen
     */
    public static String getClientHostName() {
        String clientName = Constants.EMPTY_STRING;
        try {
            clientName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            LOGGER.error("" + e);
        }
        return clientName;
    }
}