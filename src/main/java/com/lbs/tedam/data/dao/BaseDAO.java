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
import com.lbs.tedam.model.AbstractBaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * This class is an intermediate transition class. This should be the class that will be connected when switching to any DI framework.(Spring, CDI etc.)
 *
 * @author Tarik.Mikyas
 */
public interface BaseDAO<T extends AbstractBaseEntity, ID extends Serializable> extends Serializable {

    public T getById(ID id) throws LocalizedException;

    public T save(T entity) throws LocalizedException;

    public List<T> save(List<T> entityList) throws LocalizedException;

    public void delete(T entity) throws LocalizedException;

    public void deleteById(ID id) throws LocalizedException;

    public List<T> getAll() throws LocalizedException;

    public void deleteByLogic(ID id) throws LocalizedException;

}
