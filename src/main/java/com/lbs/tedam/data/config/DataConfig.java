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

package com.lbs.tedam.data.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.lbs.tedam.data.DbConnectionInfo;

/**
 * This class is configuration class for data management. Database connection, data source, entity and adapter beans are in this class. Looks up package
 * com.lbs.tedam.data.repository for repository operations and package com.lbs.tedam.data.dao for DAO implementations.
 */
@Configuration
@EnableJpaRepositories(basePackages = {"com.lbs.tedam.data.repository"})
@ComponentScan(basePackages = {"com.lbs.tedam.data.dao"})
public class DataConfig {

    /**
     * Creates a data source based on connection info given by parameter.
     *
     * @param connectionInfo Database connection info to create data source instance.
     * @return New data source instance.
     */
    @Bean
    public DataSource dataSource(DbConnectionInfo connectionInfo) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(connectionInfo.getDriverClassName());
        dataSource.setUrl(connectionInfo.getUrl());
        dataSource.setUsername(connectionInfo.getUserName());
        dataSource.setPassword(connectionInfo.getPass());
        return dataSource;
    }

    /**
     * Creates entity manager factory based on data source and adapter.
     *
     * @param dataSource           Data source instance to connect database.
     * @param adapter              JPA adapter.
     * @param additionalProperties Additional properties.
     * @return New LocalContainerEntityManagerFactoryBean instance.
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter adapter, Properties additionalProperties) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("com.lbs.tedam.model", "com.lbs.tedam.util");
        emf.setJpaVendorAdapter(adapter);
        emf.setJpaProperties(additionalProperties);
        return emf;
    }

    /**
     * Creates additional properties.
     *
     * @return Singleton Properties instance.
     */
    @Bean
    @ConfigurationProperties
    public Properties additionalProperties(DbConnectionInfo connectionInfo) {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", connectionInfo.getDialect());
        properties.setProperty("hibernate.current_session_context_class", "thread");
        properties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
        properties.setProperty("hibernate.connection.provider_class", "org.hibernate.hikaricp.internal.HikariCPConnectionProvider");
        properties.setProperty("hibernate.hikari.dataSourceClassName", connectionInfo.getDataSource());
        properties.setProperty("hibernate.hikari.dataSource.url", connectionInfo.getUrl());
        properties.setProperty("hibernate.hikari.dataSource.user", connectionInfo.getUserName());
        properties.setProperty("hibernate.hikari.dataSource.password", connectionInfo.getPass());
		properties.setProperty("hibernate.hikari.maximumPoolSize", "1");
        properties.setProperty("hibernate.hikari.connectionTimeout", "30000");
        properties.setProperty("hibernate.hikari.maxLifetime", "600000");
        properties.setProperty("hibernate.hbm2ddl.auto", connectionInfo.getDdlMode());
        properties.setProperty("hibernate.show_sql", connectionInfo.getShowSql());
        return properties;
    }

    /**
     * Creates a JPA vendor adapter. Default is HibernateJpaVendorAdapter.
     *
     * @return New JpaVendorAdapter instance.
     */
    @Bean
    public JpaVendorAdapter adapter() {
        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        return adapter;
    }

    /**
     * Creates a JpaTransactionManager instance based on given parameters.
     *
     * @param dataSource           Data source instance to connect database.
     * @param entityManagerFactory LocalContainerEntityManagerFactoryBean instance for entity operations.
     * @return New JpaTransactionManager instance.
     */
    @Bean
    public JpaTransactionManager transactionManager(DataSource dataSource, LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        JpaTransactionManager jtm = new JpaTransactionManager();
        jtm.setEntityManagerFactory(entityManagerFactory.getObject());
        return jtm;
    }

}
