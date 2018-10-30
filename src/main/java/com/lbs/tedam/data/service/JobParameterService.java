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
import com.lbs.tedam.model.JobParameter;
import com.lbs.tedam.model.Project;

import java.util.List;

/**
 * @author Canberk.Erkmen
 */
public interface JobParameterService extends BaseService<JobParameter, Integer> {

    /**
     * this method getActiveJobParameterListByProjectId The job parameters that are active according to the given projectId are fetched.<br>
     *
     * @param project
     * @return <br>
     * @throws LocalizedException
     * @author Canberk.Erkmen
     */
    public List<JobParameter> getJobParameterListByProject(Project project) throws LocalizedException;

    public JobParameter getJobParameterByProjectAndName(Project project, String name) throws LocalizedException;
}
