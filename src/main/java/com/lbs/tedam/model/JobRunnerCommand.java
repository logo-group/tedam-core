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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Base class for job runner commands.
 */
public class JobRunnerCommand implements Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    private Integer testSetId;

    private Client client;

    private List<JobRunnerDetailCommand> jobRunnerDetailCommandList = new ArrayList<>();

    /**
     * @return the testSetId
     */
    public Integer getTestSetId() {
        return testSetId;
    }

    /**
     * @param testSetId the testSetId to set
     */
    public void setTestSetId(Integer testSetId) {
        this.testSetId = testSetId;
    }

    /**
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * @return the jobRunnerDetailCommandList
     */
    public List<JobRunnerDetailCommand> getJobRunnerDetailCommandList() {
        return jobRunnerDetailCommandList;
    }

    /**
     * @param jobRunnerDetailCommandList the jobRunnerDetailCommandList to set
     */
    public void setJobRunnerDetailCommandList(List<JobRunnerDetailCommand> jobRunnerDetailCommandList) {
        this.jobRunnerDetailCommandList = jobRunnerDetailCommandList;
    }

}
