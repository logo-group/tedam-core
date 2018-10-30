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

package com.lbs.tedam.data.dao;

import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.Project;

import java.util.List;

/**
 * @author Canberk.Erkmen<br>
 */
public interface ProjectDAO extends BaseDAO<Project, Integer> {

    /**
     * this method getProjectList pulls projects from the database.<br>
     *
     * @return <br>
     * @throws LocalizedException
     * @author Canberk.Erkmen
     */
    public List<Project> getProjectList() throws LocalizedException;

    /**
     * Gets project with given name.
     *
     * @param projectName Project name.
     * @return If name is valid Project instance else null.
     * @throws LocalizedException
     */
    public Project getProjectByName(String projectName) throws LocalizedException;

}
