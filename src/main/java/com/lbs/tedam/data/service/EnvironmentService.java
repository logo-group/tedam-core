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

import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.Environment;
import com.lbs.tedam.model.JobParameterValue;
import com.lbs.tedam.model.Project;
import com.lbs.tedam.model.TedamUser;

import java.util.List;

public interface EnvironmentService extends BaseService<Environment, Integer> {
    /**
     * this method getEnvironmentsByProject returns the active environments associated with the project..<br>
     *
     * @param project
     * @return <br>
     * @throws LocalizedException
     * @author Canberk.Erkmen
     */
    public List<Environment> getEnvironmentListByProject(Project project) throws LocalizedException;

    /**
     * This method updates the jobParameterValue from uploadJobParameterValueToAllEnvironments in all environments.<br>
     *
     * @param jobParameterValue
     * @param project
     * @param userName          <br>
     * @throws LocalizedException
     * @author Canberk.Erkmen
     */
    public void uploadJobParameterValueToAllEnvironments(JobParameterValue jobParameterValue, Project project, String userName) throws LocalizedException;

    List<Environment> getEnvironmentListWithFavorites(TedamUser user, Project project) throws LocalizedException;

    List<Environment> getEnvironmentListWithoutFavorites(TedamUser user, Project project) throws LocalizedException;

}
