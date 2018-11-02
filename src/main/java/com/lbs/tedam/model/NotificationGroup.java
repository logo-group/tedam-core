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

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Where;

import com.lbs.tedam.util.EnumsV2.NotificationType;

@Entity
@Where(clause = "IS_DELETED=0")
@Table(name = "NOTIFICATION_GROUP")
public class NotificationGroup extends AbstractBaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3138490182147183777L;

	@Column(name = "NAME")
	@Size(min = 1, max = 255)
	private String groupName;

	@Column(name = "TYPE")
	private NotificationType type;

	@ManyToOne
	@JoinColumn(name = "PROJECT_ID")
	@Where(clause = "IS_DELETED=0")
	private Project project;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "NOTIFICATION_GROUP_ID")
	@Where(clause = "IS_DELETED=0")
	private List<Recipient> recipientList = new ArrayList<>();

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public NotificationType getType() {
		return type;
	}

	public void setType(NotificationType type) {
		this.type = type;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Recipient> getRecipientList() {
		return recipientList;
	}

	public void setRecipientList(List<Recipient> recipientList) {
		this.recipientList = recipientList;
	}

	@Override
	public String toString() {
		return groupName;
	}

}
