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

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tarik.Mikyas<br>
 */
@Entity
@Where(clause = "IS_DELETED=0")
@Table(name = "SINGLE_COMMAND", uniqueConstraints = {@UniqueConstraint(columnNames = {"NAME", "PROJECT_ID"})})
public class SingleCommand extends DefinedCommand {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 8661312830056616561L;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Where(clause = "IS_DELETED=0")
    private List<Client> clients = new ArrayList<>();

    public SingleCommand() {
    }

    public SingleCommand(String name, String windowsValue, String unixValue, Project project,
                         String firstExpectedResult, String lastExpectedResult) {
        super(name, windowsValue, unixValue, project, firstExpectedResult, lastExpectedResult);
    }

    /**
     * @return the clients
     */
    public List<Client> getClients() {
        return clients;
    }

    /**
     * @param clients the clients to set
     */
    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

}
