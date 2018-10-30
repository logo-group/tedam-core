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

import com.lbs.tedam.util.EnumsV2.CommandStatus;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tarik.Mikyas<br>
 */
@Entity
@Where(clause = "IS_DELETED=0")
@Table(name = "JOB_DETAIL")
public class JobDetail extends AbstractBaseEntity {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 6891240727075019647L;

    @Column(name = "JOB_ID")
    private Integer jobId;

    @OneToOne
    @JoinColumn(name = "CLIENT_ID")
    @Where(clause = "IS_DELETED=0")
    private Client client;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "TEST_SET_ID", insertable = false, updatable = false)
    @Where(clause = "IS_DELETED=0")
    private TestSet testSet;

    @Column(name = "TEST_SET_ID")
    private Integer testSetId;

    @Column(name = "STATUS")
    private CommandStatus status = CommandStatus.NOT_STARTED;

    @Column(name = "POSITION")
    private Integer position;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "JOB_DETAIL_ID")
    @Where(clause = "IS_DELETED=0")
    private List<JobCommand> jobCommands = new ArrayList<>();

    public JobDetail() {
        // An empty constructor is needed for all beans
    }

    public JobDetail(Client client, TestSet testSet) {
        super();
        this.client = client;
        this.testSet = testSet;
        this.testSetId = testSet.getId();
    }

    /**
     * @return the jobId
     */
    public Integer getJobId() {
        return jobId;
    }

    /**
     * @param jobId the jobId to set
     */
    public void setJobId(Integer jobId) {
        this.jobId = jobId;
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
     * @return the testSet
     */
    public TestSet getTestSet() {
        return testSet;
    }

    /**
     * @param testSet the testSet to set
     */
    public void setTestSet(TestSet testSet) {
        this.testSet = testSet;
    }

    /**
     * @return the status
     */
    public CommandStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(CommandStatus status) {
        this.status = status;
    }

    /**
     * @return the jobCommands
     */
    public List<JobCommand> getJobCommands() {
        return jobCommands;
    }

    /**
     * @param jobCommands the jobCommands to set
     */
    public void setJobCommands(List<JobCommand> jobCommands) {
        this.jobCommands = jobCommands;
    }

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
     * @return the position
     */
    public Integer getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getTestSetName() {
        if (this.testSet != null) {
            return testSet.getName();
        }
        return "";
    }

}
