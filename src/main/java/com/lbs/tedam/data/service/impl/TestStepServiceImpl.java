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

package com.lbs.tedam.data.service.impl;

import com.lbs.tedam.data.dao.TestStepDAO;
import com.lbs.tedam.data.service.TestStepService;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.generator.steptype.AbstractFillTestStepGenerator;
import com.lbs.tedam.model.Project;
import com.lbs.tedam.model.SnapshotDefinition;
import com.lbs.tedam.model.TestStep;
import com.lbs.tedam.util.EnumsV2.TestStepType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class TestStepServiceImpl extends BaseServiceImpl<TestStep, Integer> implements TestStepService {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private TestStepDAO dao;

    @Autowired
    public void setDao(TestStepDAO dao) {
        this.dao = dao;
        super.setBaseDao(dao);
    }

    @Override
    public List<TestStep> getTestStepListByTypeAndProject(TestStepType testStepType, Project project) throws LocalizedException {
        List<TestStep> testStepList = dao.getTestStepListByTypeAndProject(testStepType, project);
        return testStepList;
    }

    /**
     * @param testStep teststep to receive and save information
     * @param isLookUp In the case of lookup, the generator runs on it gore.
     * @return
     */
    @Override
    public SnapshotDefinition copyTestStepFillDefinitions(TestStep testStep) {
        if (TestStepType.getFillTestStepTypeList().contains(testStep.getType()) && !StringUtils.isEmpty(testStep.getFilename()) && testStep.getGenerator() != null) {
            AbstractFillTestStepGenerator fillGenerator = (AbstractFillTestStepGenerator) testStep.getGenerator();
            SnapshotDefinition snapshotDefinition = fillGenerator.getSnapshotDefinition().cloneSnapshotDefinition();
            fillGenerator.setSnapshotDefinition(snapshotDefinition);
            testStep.setParameter(fillGenerator.generate());
            return snapshotDefinition;
        }
        return null;
    }

    @Override
    public void regenerateTestStepParameter(TestStep testStep, SnapshotDefinition snapshotDefiniton) {
        if (TestStepType.getFillTestStepTypeList().contains(testStep.getType()) && !StringUtils.isEmpty(testStep.getFilename())) {
            AbstractFillTestStepGenerator fillGenerator = (AbstractFillTestStepGenerator) testStep.getGenerator();
            fillGenerator.setSnapshotDefinition(snapshotDefiniton);
            testStep.setParameter(fillGenerator.generate());
        }
    }

}
