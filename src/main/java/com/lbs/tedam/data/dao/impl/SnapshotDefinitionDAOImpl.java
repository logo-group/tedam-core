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

import com.lbs.tedam.data.dao.SnapshotDefinitionDAO;
import com.lbs.tedam.data.repository.SnapshotDefinitionRepository;
import com.lbs.tedam.exception.localized.GeneralLocalizedException;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.SnapshotDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SnapshotDefinitionDAOImpl extends BaseDAOImpl<SnapshotDefinition, Integer> implements SnapshotDefinitionDAO {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private transient SnapshotDefinitionRepository repository;

    @Autowired
    public void setRepository(SnapshotDefinitionRepository repository) {
        this.repository = repository;
        super.setRepository(repository);
    }

    @Override
    public List<SnapshotDefinition> findByUserIdAndDescriptionLikeAndLookupNullOrIdLike(String description, String userId) throws LocalizedException {
        try {
            List<SnapshotDefinition> returnList = repository.findByUserIdAndDescriptionLikeAndLookupNullOrIdLike(userId, description);
            return returnList;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public List<SnapshotDefinition> findByUserIdAndDescriptionLikeAndLookupNotNullOrIdLike(String description, String userId, String lookUpType) throws LocalizedException {
        try {
            List<SnapshotDefinition> returnList = repository.findByUserIdAndDescriptionLikeAndLookupNotNullOrIdLike(userId, description, lookUpType.length(), lookUpType);
            return returnList;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public List<SnapshotDefinition> findByUserIdAndDescriptionEqualAndLookupNullOrIdEquals(String description, String userId) throws LocalizedException {
        try {
            List<SnapshotDefinition> returnList = repository.findByUserIdAndDescriptionEqualAndLookupNullOrIdEquals(userId, description);
            return returnList;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }

    @Override
    public List<SnapshotDefinition> findByUserIdAndDescriptionEqualAndLookupNotNullOrIdEquals(String description, String userId, String lookUpType) throws LocalizedException {
        try {
            List<SnapshotDefinition> returnList = repository.findByUserIdAndDescriptionEqualAndLookupNotNullOrIdEquals(userId, description, lookUpType.length(), lookUpType);
            return returnList;
        } catch (Exception e) {
            throw new GeneralLocalizedException(e);
        }
    }
}
