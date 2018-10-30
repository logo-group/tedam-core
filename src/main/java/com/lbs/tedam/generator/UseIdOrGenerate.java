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

package com.lbs.tedam.generator;

import com.lbs.tedam.model.AbstractBaseEntity;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IncrementGenerator;

import java.io.Serializable;

public class UseIdOrGenerate extends IncrementGenerator {

    @Override
    public synchronized Serializable generate(SessionImplementor session, Object object) throws HibernateException {

        if (object == null)
            throw new HibernateException(new NullPointerException());

        if (((AbstractBaseEntity) object).getId() == 0) {
            Serializable id = super.generate(session, object);
            return id;
        } else {
            return ((AbstractBaseEntity) object).getId();
        }
    }
}
