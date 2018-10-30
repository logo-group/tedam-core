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

package com.lbs.tedam.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * All configurations must be regitered in this context class.
 */
public class ConfigTestContext {

    /**
     * Single instance of context.
     */
    private static ApplicationContext context;

    /**
     * Gets a bean registered in configurations.
     *
     * @param clazz Type to get.
     * @return Registered bean of class given by parameter.
     */
    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

    /**
     * Registers configuration class. configurationClasses by parameter will be
     * registered.
     *
     * @param configurationClasses Configuration annotated classes.
     */
    public static void registerConfig(Class<?>... configurationClasses) {
        if (context == null) {
            context = new AnnotationConfigApplicationContext(configurationClasses);
        }
    }

}
