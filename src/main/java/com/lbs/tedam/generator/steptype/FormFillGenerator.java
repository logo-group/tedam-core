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

import com.lbs.tedam.data.service.SnapshotDefinitionService;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.util.EnumsV2.TestStepType;

/**
 * @author Ahmet.Izgi
 */
public class FormFillGenerator extends AbstractFillTestStepGenerator {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    public FormFillGenerator(SnapshotDefinitionService snapshotDefinitionService) {
        super(snapshotDefinitionService);
    }

    @Override
    public String generateLookUp() {
        return TestStepType.FORM_FILL.getBeginRegex() + generate() + TestStepType.FORM_FILL.getEndRegex();
    }

    @Override
    public void degenerateLookUp(String parameter) throws LocalizedException {
        String paramWithNoRegex = parameter.replaceAll(TestStepType.FORM_FILL.getBeginRegex(), "");
        degenerate(paramWithNoRegex);
    }

}
