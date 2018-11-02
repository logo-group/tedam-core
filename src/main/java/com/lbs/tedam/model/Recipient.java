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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Where;

@Entity
@Where(clause = "IS_DELETED=0")
@Table(name = "RECIPIENT")
public class Recipient extends AbstractBaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3892628583277525774L;

	@Column(name = "ADDRESS")
	@Size(min = 1, max = 255)
	private String address;

	@ManyToOne
	@JoinColumn(name = "NOTIFICATION_GROUP_ID")
	@Where(clause = "IS_DELETED=0")
	private NotificationGroup notificationGroup;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public NotificationGroup getNotifcationGroup() {
		return notificationGroup;
	}

	public void setNotifcationGroup(NotificationGroup notificationGroup) {
		this.notificationGroup = notificationGroup;
	}

}
