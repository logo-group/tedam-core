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

package com.lbs.tedam.generator.steptype;

import com.lbs.tedam.exception.localized.LocalizedException;
import org.springframework.util.StringUtils;

public class WaitGenerator extends Generator {

    private static final long serialVersionUID = 1L;
    private String continueOnError = "0";
    private String writeFilters = "1";
    private Long waitSleepMillis;

    @Override
    public boolean validate() {
        if (waitSleepMillis == null) {
            return false;
        }
        return true;
    }

    @Override
    public String generate() {
        String parameter = "";
        if (waitSleepMillis != null) {
            parameter += waitSleepMillis;
        }
        return parameter;

    }

    @Override
    public void degenerate(String parameter) throws LocalizedException {
        if (StringUtils.isEmpty(parameter)) {
            return;
        } else {
            waitSleepMillis = Long.valueOf(parameter);
        }

    }

    public String getContinueOnError() {
        return continueOnError;
    }

    public void setContinueOnError(String continueOnError) {
        this.continueOnError = continueOnError;
    }

    public Long getWaitSleepMillis() {
        return waitSleepMillis;
    }

    public void setWaitSleepMillis(Long waitSleepMillis) {
        this.waitSleepMillis = waitSleepMillis;
    }

    public String getWriteFilters() {
        return writeFilters;
    }

    public void setWriteFilters(String writeFilters) {
        this.writeFilters = writeFilters;
    }

}
