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

package com.lbs.tedam.model.DTO;

import com.lbs.tedam.util.EnumsV2.ClientStatus;
import com.lbs.tedam.util.EnumsV2.CommandStatus;

public class ClientDTO {

    private String clientName;

    private ClientStatus clientStatus;

    private int testSetId;

    private CommandStatus testSetStatus;

    private int jobId;

    public ClientDTO(String clientName, ClientStatus clientStatus) {
        super();
        this.clientName = clientName;
        this.clientStatus = clientStatus;
    }

    /**
     * @return the clientName
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * @param clientName the clientName to set
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * @return the clientStatus
     */
    public ClientStatus getClientStatus() {
        return clientStatus;
    }

    /**
     * @param clientStatus the clientStatus to set
     */
    public void setClientStatus(ClientStatus clientStatus) {
        this.clientStatus = clientStatus;
    }

    /**
     * @return the testSetId
     */
    public int getTestSetId() {
        return testSetId;
    }

    /**
     * @param testSetId the testSetId to set
     */
    public void setTestSetId(int testSetId) {
        this.testSetId = testSetId;
    }

    /**
     * @return the testSetStatus
     */
    public CommandStatus getTestSetStatus() {
        return testSetStatus;
    }

    /**
     * @param testSetStatus the testSetStatus to set
     */
    public void setTestSetStatus(CommandStatus testSetStatus) {
        this.testSetStatus = testSetStatus;
    }

    /**
     * @return the jobId
     */
    public int getJobId() {
        return jobId;
    }

    /**
     * @param jobId the jobId to set
     */
    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

}
