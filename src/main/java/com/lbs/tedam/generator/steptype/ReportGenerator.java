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

/**
 *
 */
package com.lbs.tedam.generator.steptype;

import com.lbs.tedam.util.Enums.Regex;
import org.springframework.util.StringUtils;

/**
 * @author Ahmet.Izgi
 */
public class ReportGenerator extends Generator {

    private static final long serialVersionUID = 1L;
    private String reportName;
    private String continueOnError = "0";
    private String writeFilters = "1";
    private Long reportWaitSleepMillis;

    @Override
    public boolean validate() {
        if (reportName == null) {
            return false;
        }
        return true;
    }

    @Override
    public String generate() {
        String parameter = "";
        if (reportName != null) {
            parameter = reportName + Regex.PARAMETER_SPLITTER.getRegex() + continueOnError + Regex.PARAMETER_SPLITTER.getRegex() + writeFilters
                    + Regex.PARAMETER_SPLITTER.getRegex() + reportWaitSleepMillis;
        }
        return parameter;
    }

    @Override
    public void degenerate(String parameter) {
        if (StringUtils.isEmpty(parameter)) {
            return;
        }
        String[] splittedParameter = parameter.split(Regex.PARAMETER_SPLITTER.getRegex());
        reportName = splittedParameter[0];
        if (splittedParameter.length > 3) {
            reportWaitSleepMillis = Long.valueOf(splittedParameter[3]);
        }
    }

    /**
     * @return the reportName
     */
    public String getReportName() {
        return reportName;
    }

    /**
     * @param reportName the reportName to set
     */
    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    /**
     * @return the continueOnError
     */
    public String getContinueOnError() {
        return continueOnError;
    }

    /**
     * @param continueOnError the continueOnError to set
     */
    public void setContinueOnError(String continueOnError) {
        this.continueOnError = continueOnError;
    }

    /**
     * @return the writeFilters
     */
    public String getWriteFilters() {
        return writeFilters;
    }

    /**
     * @param writeFilters the writeFilters to set
     */
    public void setWriteFilters(String writeFilters) {
        this.writeFilters = writeFilters;
    }

    /**
     * @return the reportWaitMillis
     */
    public Long getReportWaitSleepMillis() {
        return reportWaitSleepMillis;
    }

    /**
     * @param reportWaitSleepMillis the reportWaitMillis to set
     */
    public void setReportWaitSleepMillis(Long reportWaitSleepMillis) {
        this.reportWaitSleepMillis = reportWaitSleepMillis;
    }

}
