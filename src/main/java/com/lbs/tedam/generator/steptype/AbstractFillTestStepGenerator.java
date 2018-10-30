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

import com.lbs.tedam.data.service.SnapshotDefinitionService;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.SnapshotDefinition;

/**
 * @author Ahmet.Izgi
 */
public abstract class AbstractFillTestStepGenerator extends Generator {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private SnapshotDefinition snapshotDefinition;

    private SnapshotDefinitionService snapshotDefinitionService;

    public AbstractFillTestStepGenerator(SnapshotDefinitionService snapshotDefinitionService) {
        this.snapshotDefinitionService = snapshotDefinitionService;
    }

    @Override
    public String generate() {
        return String.valueOf(snapshotDefinition.getId());
    }

    @Override
    public void degenerate(String parameter) throws NumberFormatException, LocalizedException {
        this.snapshotDefinition = snapshotDefinitionService.getById(Integer.valueOf(parameter));
    }

    public SnapshotDefinition getSnapshotDefinition() {
        return snapshotDefinition;
    }

    public void setSnapshotDefinition(SnapshotDefinition snapshotDefinition) {
        this.snapshotDefinition = snapshotDefinition;
    }

    @Override
    public boolean validate() {
        if (snapshotDefinition == null || snapshotDefinition.getSnapshotValues().isEmpty()) {
            return false;
        }
        return true;
    }

}
