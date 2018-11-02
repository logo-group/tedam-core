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

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Where;

import com.lbs.tedam.util.DateTimeUtils;
import com.lbs.tedam.util.EnumsV2.JobStatus;
import com.lbs.tedam.util.EnumsV2.JobType;

/**
 * @author Tarik.Mikyas<br>
 */
@Entity
@Where(clause = "IS_DELETED=0")
@Table(name = "JOB")
public class Job extends AbstractBaseEntity {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 300276841746208067L;

    /**
     * String name
     */
    @Column(name = "NAME", unique = true)
    @Size(min = 1, max = 255)
    private String name;

    @Column(name = "STATUS")
    private JobStatus status = JobStatus.NOT_STARTED;

    @Column(name = "TYPE")
    private JobType type = JobType.NORMAL;

    @Column(name = "LAST_EXECUTED_START_DATE")
    private LocalDateTime lastExecutedStartDate;

    @Column(name = "LAST_EXECUTED_END_DATE")
    private LocalDateTime lastExecutedEndDate;

    @Transient
    private String executionDuration;

    @Column(name = "IS_ACTIVE")
    private boolean active = false;

    /**
     * String project
     */
    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    @Where(clause = "IS_DELETED=0")
    private Project project;

    /**
     * IS_CI
     */
    @Column(name = "IS_CI")
    private boolean ci = false;

    /**
     * Environment jobEnvironment
     */
    @ManyToOne
    @JoinColumn(name = "ENVIRONMENT_ID")
    @Where(clause = "IS_DELETED=0")
    private Environment jobEnvironment;

	@ManyToOne
	@JoinColumn(name = "NOTIFICATION_GROUP_ID")
	@Where(clause = "IS_DELETED=0")
	private NotificationGroup notificationGroup;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "JOB_ID")
    @Where(clause = "IS_DELETED=0")
    @OrderBy("position ASC")
    private List<JobDetail> jobDetails = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Client> clients = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "LAST_EXECUTING_USER_ID")
    @Where(clause = "IS_DELETED=0")
    private TedamUser lastExecutingUser;

    public Job() {
        // An empty constructor is needed for all beans
    }

    public Job(String name, Project project) {
        super();
        this.name = name;
        this.project = project;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the status
     */
    public JobStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(JobStatus status) {
        this.status = status;
    }

    /**
     * @return the ci
     */
    public boolean isCi() {
        return ci;
    }

    /**
     * @param ci the ci to set
     */
    public void setCi(boolean ci) {
        this.ci = ci;
    }

    /**
     * @return the lastExecutedStartDate
     */
    public LocalDateTime getLastExecutedStartDate() {
        return lastExecutedStartDate;
    }

    /**
     * @param lastExecutedStartDate the lastExecutedStartDate to set
     */
    public void setLastExecutedStartDate(LocalDateTime lastExecutedStartDate) {
        this.lastExecutedStartDate = lastExecutedStartDate;
    }

    /**
     * @return the lastExecutedEndDate
     */
    public LocalDateTime getLastExecutedEndDate() {
        return lastExecutedEndDate;
    }

    /**
     * @param lastExecutedEndDate the lastExecutedEndDate to set
     */
    public void setLastExecutedEndDate(LocalDateTime lastExecutedEndDate) {
        this.lastExecutedEndDate = lastExecutedEndDate;
    }

    /**
     * @return the executionDuration
     */
    public String getExecutionDuration() {
        if (getLastExecutedStartDate() != null && getLastExecutedEndDate() != null && getLastExecutedEndDate().isAfter(getLastExecutedStartDate())) {
            Long until = getLastExecutedStartDate().until(getLastExecutedEndDate(), ChronoUnit.SECONDS);
            return DateTimeUtils.formatTime(until.intValue());
        }
        return "";
    }

    /**
     * @return the project
     */
    public Project getProject() {
        return project;
    }

    /**
     * @param project the project to set
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * @return the jobEnvironment
     */
    public Environment getJobEnvironment() {
        return jobEnvironment;
    }

    /**
     * @param jobEnvironment the jobEnvironment to set
     */
    public void setJobEnvironment(Environment jobEnvironment) {
        this.jobEnvironment = jobEnvironment;
    }

	public NotificationGroup getNotificationGroup() {
		return notificationGroup;
	}

	public void setNotificationGroup(NotificationGroup notificationGroup) {
		this.notificationGroup = notificationGroup;
	}

	/**
	 * @return the jobDetails
	 */
    public List<JobDetail> getJobDetails() {
        return jobDetails;
    }

    /**
     * @param jobDetails the jobDetails to set
     */
    public void setJobDetails(List<JobDetail> jobDetails) {
        this.jobDetails = jobDetails;
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

    /**
     * @return the isActive
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param isActive the isActive to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    public JobType getType() {
        return type;
    }

    public void setType(JobType type) {
        this.type = type;
    }

    public TedamUser getLastExecutingUser() {
        return lastExecutingUser;
    }

    public void setLastExecutingUser(TedamUser lastExecutingUser) {
        this.lastExecutingUser = lastExecutingUser;
    }

    public String getLastExecutingUserName() {
        return getLastExecutingUser() != null ? getLastExecutingUser().getUserName() : "";
    }

}
