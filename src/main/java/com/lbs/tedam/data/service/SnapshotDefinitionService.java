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
import com.lbs.tedam.model.SnapshotDefinition;
import com.lbs.tedam.model.TestCase;

/**
 * @author Tarik.Mikyas
 * @entity A service that contains the logic written for SnapshotDefinition-centric operations.
 */
public interface SnapshotDefinitionService extends BaseService<SnapshotDefinition, Integer> {

    /**
     * The same blueprint has been implemented in order to apply the snapshot upgrade operations, which are property links, to the rollback mechanism.
     *
     * @param snapshotDefinition
     * @param propertyList
     * @return
     * @throws LocalizedException
     * @author Tarik.Mikyas
     */
    public int save(SnapshotDefinition snapshotDefinition, String filename) throws LocalizedException;

    /**
     * this method createLookupParameterContent <br>
     *
     * @param snapshotDefinition
     * @param newTestCase        <br>
     * @throws LocalizedException
     * @author Canberk.Erkmen
     */
    public void createLookupParameterContent(SnapshotDefinition snapshotDefinition, TestCase newTestCase) throws LocalizedException;

}
