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

import com.lbs.tedam.data.dao.SnapshotValueDAO;
import com.lbs.tedam.data.repository.SnapshotValueRepository;
import com.lbs.tedam.exception.localized.GeneralLocalizedException;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.SnapshotValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class SnapshotValueDAOImpl extends BaseDAOImpl<SnapshotValue, Integer> implements SnapshotValueDAO {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private transient SnapshotValueRepository repository;

    @Autowired
    public void setRepository(SnapshotValueRepository repository) {
        this.repository = repository;
        super.setRepository(repository);
    }

    @Override
    public List<SnapshotValue> findBySnapshotDefinitionId(int snapshotDefinitionId) throws LocalizedException {
        try {
            List<SnapshotValue> snapshotValueList = repository.findBySnapshotDefinitionId(snapshotDefinitionId);
            return snapshotValueList;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public List<SnapshotValue> findBySnapshotDefinitionIdAndRowIndexNot(int snapshotDefinitionId, Integer excluded) throws LocalizedException {
        try {
            List<SnapshotValue> snapshotValueList = repository.findBySnapshotDefinitionIdAndRowIndexNot(snapshotDefinitionId, excluded);
            return snapshotValueList;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public List<SnapshotValue> getSnapshotValueListByVersion(String version) throws LocalizedException {
        try {
            List<SnapshotValue> resultList = repository.findByVersionLike("%" + version + "%");
            return resultList;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public SnapshotValue getLatestVersionOfSnapshotValue(String tag, int snapshotDefinitionId) throws LocalizedException {
        try {
            Object[][] records = repository.findLatestVersionOfSnapshotValueByTag(tag);
            List<SnapshotValue> resultList = repository.findByRowIndexAndSnapshotDefinitionId(-1, snapshotDefinitionId);
            List<SnapshotValue> refinedList = new ArrayList<>();

            refineList(records, resultList, refinedList);

            SnapshotValue snapshotValue = null;
            if (refinedList.size() > 0)
                snapshotValue = refinedList.get(0);

            return snapshotValue;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    private void refineList(Object[][] records, List<SnapshotValue> resultList, List<SnapshotValue> refinedList) {
        Iterator<SnapshotValue> iterator = resultList.iterator();
        while (iterator.hasNext()) {
            SnapshotValue sv = iterator.next();
            for (int i = 0; i < records.length; i++) {
                Object[] row = records[i];
                if (sv.getSnapshotDefinitionId() == (Integer) row[0] && sv.getTag().equals(row[1]) && sv.getVersion().equals(row[2])) {
                    refinedList.add(sv);
                    break;
                }
            }
        }
    }

    @Override
    public List<SnapshotValue> getSnapshotValuesVersioned(String version, int snapshotDefinitionId, Boolean scope) throws LocalizedException {
        try {
            Object[][] records = repository.findSnapshotValuesVersioned(version, snapshotDefinitionId);
            List<SnapshotValue> resultList = null;
            List<SnapshotValue> refinedList = new ArrayList<>();

            if (Boolean.TRUE.equals(scope)) {
                resultList = repository.findByStatusAndSnapshotDefinitionIdAndRowIndex(Boolean.TRUE, snapshotDefinitionId, -3);
            } else if (Boolean.FALSE.equals(scope)) {
                resultList = repository.findByStatusAndSnapshotDefinitionIdAndRowIndexNot(Boolean.TRUE, snapshotDefinitionId, -3);
            } else {
                resultList = repository.findByStatusAndSnapshotDefinitionId(Boolean.TRUE, snapshotDefinitionId);
            }

            refineVersionedList(records, resultList, refinedList);

            return refinedList;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    private void refineVersionedList(Object[][] records, List<SnapshotValue> resultList, List<SnapshotValue> refinedList) {
        Iterator<SnapshotValue> iterator = resultList.iterator();
        while (iterator.hasNext()) {
            SnapshotValue sv = iterator.next();
            for (int i = 0; i < records.length; i++) {
                Object[] row = records[i];
                if (sv.getTag().equals(row[0]) && sv.getVersion().equals(row[1])) {
                    refinedList.add(sv);
                    break;
                }
            }
        }
    }

}
