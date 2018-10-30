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

package com.lbs.tedam.data;

public class DbConnectionInfo {

    private String driverClassName = "";

    private String url = "";

    private String userName = "";

    private String pass = "";

    private String ddlMode = "validate";

    private String showSql = "false";

    private String dialect = "";

    private String dataSource = "";

    public DbConnectionInfo(String driverClassName, String url, String userName, String pass, String dialect, String dataSource) {
        this.driverClassName = driverClassName;
        this.url = url;
        this.userName = userName;
        this.pass = pass;
        this.dialect = dialect;
        this.dataSource = dataSource;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public String getUserName() {
        return userName;
    }

    public String getPass() {
        return pass;
    }

    public String getDdlMode() {
        return ddlMode;
    }

    public void setDdlMode(String ddlMode) {
        this.ddlMode = ddlMode;
    }

    public String getShowSql() {
        return showSql;
    }

    public void setShowSql(String showSql) {
        this.showSql = showSql;
    }

    public String getDialect() {
        return dialect;
    }

    public String getDataSource() {
        return dataSource;
    }

}
