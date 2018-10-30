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

package com.lbs.tedam.model;

import com.lbs.tedam.data.config.DataConfig;
import com.lbs.tedam.data.service.TestDataConfig;
import com.lbs.tedam.test.BaseServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestDataConfig.class, DataConfig.class})
public class SnapshotValueTest extends BaseServiceTest {

    @Test
    public void testSetters() {
        SnapshotValue snapshotValue = new SnapshotValue();
        snapshotValue.setCaption("caption");
        snapshotValue.setContinueOnError(false);
        snapshotValue.setDialogParameter("dialogParameter");
        snapshotValue.setLookUp(false);
        snapshotValue.setLookUpParameter("lookUpParameter");
        snapshotValue.setOrder(2);
        snapshotValue.setParentTag("parentTag");
        snapshotValue.setRowIndex(3);
        snapshotValue.setSnapshotDefinitionId(Integer.valueOf(1));
        snapshotValue.setStatus(false);
        snapshotValue.setTag("tag");
        snapshotValue.setTempControl(true);
        snapshotValue.setType("type");
        snapshotValue.setValue("value");
        snapshotValue.setVersion("version");
        snapshotValue.cloneSnapshotValue();
    }

    @Test
    public void testGetters() {
        SnapshotValue snapshotValue = new SnapshotValue();
        snapshotValue.getCaption();
        snapshotValue.getContinueOnError();
        snapshotValue.getDialogParameter();
        snapshotValue.isLookUp();
        snapshotValue.getLookUpParameter();
        snapshotValue.getOrder();
        snapshotValue.getParentTag();
        snapshotValue.getRowIndex();
        snapshotValue.getSnapshotDefinitionId();
        snapshotValue.getStatus();
        snapshotValue.getTag();
        snapshotValue.isTempControl();
        snapshotValue.getType();
        snapshotValue.getValue();
        snapshotValue.getVersion();
    }

}
