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

package com.lbs.tedam.data.dao.impl;

import com.lbs.tedam.data.dao.TestStepDAO;
import com.lbs.tedam.data.repository.TestStepRepository;
import com.lbs.tedam.exception.localized.GeneralLocalizedException;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.Project;
import com.lbs.tedam.model.TestStep;
import com.lbs.tedam.util.EnumsV2.TedamBoolean;
import com.lbs.tedam.util.EnumsV2.TestStepType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestStepDAOImpl extends BaseDAOImpl<TestStep, Integer> implements TestStepDAO {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private transient TestStepRepository repository;

    @Autowired
    public void setRepository(TestStepRepository repository) {
        this.repository = repository;
        super.setRepository(repository);
    }

    @Override
    public List<TestStep> getTestStepListByTypeAndProject(TestStepType testStepType, Project project) throws LocalizedException {
        try {
            List<TestStep> testStepList = repository.findByTypeAndFilenameNotNullAndProjectAndLookUpAndDeleted(testStepType, project, TedamBoolean.FALSE.getBooleanValue(),
                    TedamBoolean.FALSE.getBooleanValue());
            return testStepList;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

}
