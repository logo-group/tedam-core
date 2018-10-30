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

import com.lbs.tedam.data.dao.BaseDAO;
import com.lbs.tedam.data.service.BaseService;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.AbstractBaseEntity;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @param <T>
 * @param <ID>
 * @author Tarik.Mikyas<br>
 */
@Service
public class BaseServiceImpl<T extends AbstractBaseEntity, ID extends Serializable> implements BaseService<T, ID> {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private BaseDAO<T, ID> baseDao;

    public void setBaseDao(BaseDAO<T, ID> baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public T getById(ID id) throws LocalizedException {
        T item = baseDao.getById(id);
        afterGet(Arrays.asList(item));
        return item;
    }

    @Override
    public T save(T entity) throws LocalizedException {
        T saved = baseDao.save(entity);
        return saved;
    }

    @Override
    public List<T> save(List<T> entityList) throws LocalizedException {
        List<T> savedEntityList = baseDao.save(entityList);
        return savedEntityList;
    }

    @Override
    public void delete(T entity) throws LocalizedException {
        beforeDelete(entity);
        baseDao.delete(entity);
    }

    @Override
    public void deleteById(ID id) throws LocalizedException {
        beforeDelete(id);
        baseDao.deleteById(id);
    }

    @Override
    public List<T> getAll() throws LocalizedException {
        List<T> itemList = baseDao.getAll();
        afterGet(itemList);
        return itemList;
    }

    @Override
    public void deleteByLogic(ID id) throws LocalizedException {
        beforeDelete(id);
        baseDao.deleteByLogic(id);
        afterDeleteByLogic(id);
    }

    public void beforeDelete(ID id) throws LocalizedException {
        // before delete operations by id
    }

    public void beforeDelete(T entity) throws LocalizedException {
        // before delete operations by entity
    }

    public void afterGet(List<T> itemList) throws LocalizedException {
        // after get operations by itemList
    }

    public void afterDeleteByLogic(ID id) throws LocalizedException {
        // after delete operations by id
    }

}
