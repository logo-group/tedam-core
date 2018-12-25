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

package com.lbs.tedam.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lbs.tedam.model.Job;
import com.lbs.tedam.model.JobDetail;
import com.lbs.tedam.model.Recipient;
import com.lbs.tedam.model.TestCase;
import com.lbs.tedam.model.TestCaseTestRun;
import com.lbs.tedam.util.EnumsV2.ExecutionStatus;

public class TedamMailSender {

	private static final Logger LOGGER = LoggerFactory.getLogger(TedamMailSender.class);
	private static final String BR = "<br/>";

	private static TedamMailSender sender;

	private Properties props;

	private TedamMailSender() {
		try {
			props = new Properties();
			String propFileName = "mail.properties";
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			if (inputStream != null) {
				props.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMail(Job job) {
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(props.getProperty("username"), props.getProperty("password"));
			}
		});
		try {
			List<String> mailList = new ArrayList<>();
			List<Recipient> recipientList = job.getNotificationGroup().getRecipientList();
			recipientList.forEach(recipient -> mailList.add(recipient.getAddress()));
			Address[] recipientAddress = new Address[mailList.size()];
			int counter = 0;
			for (String recipient : mailList) {
				recipientAddress[counter] = new InternetAddress(recipient.trim());
				counter++;
			}
			String messageBody = "<strong>Job Name: </strong>" + job.getName() + BR + BR;
			String jobDetailBody = "";
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(props.getProperty("username")));
			message.setSubject("TEDAM Execution Report");
			for (JobDetail jobDetail : job.getJobDetails()) {
				jobDetailBody += getJobDetails(jobDetail);
			}
			if (jobDetailBody.length() > 0) {
				message.setContent(messageBody + jobDetailBody, "text/html; charset=utf-8");
				message.setRecipients(Message.RecipientType.TO, recipientAddress);
				Transport.send(message);
				LOGGER.info("Email sent successfully!");
			}
		} catch (MessagingException e) {
			LOGGER.info("Something went wrong while email sending!" + e);
			throw new RuntimeException(e);
		}

	}

	private String getJobDetails(JobDetail jobDetail) {
		String testCaseStatus = getTestCaseStatus(jobDetail.getTestSet().getTestCases());
		String testSetInfo = "";
		if (testCaseStatus.length() > 0) {
			testSetInfo = "<strong>Test Set Name: </strong>" + jobDetail.getTestSetName() + BR;
			testSetInfo += testCaseStatus + BR;
		}
		return testSetInfo;
	}

	private String getTestCaseStatus(List<TestCase> testCaseList) {
		String testCaseStatus = "";
		for (TestCase testCase : testCaseList) {
			String testCaseName = testCase.getName();
			List<TestCaseTestRun> testCaseTestRunList = testCase.getTestCaseTestRunList();
			TestCaseTestRun testCaseTestRun = testCaseTestRunList.get(testCaseTestRunList.size() - 1);
			if (testCaseTestRun.getExecutionStatus().equals(ExecutionStatus.BLOCKED)
					|| testCaseTestRun.getExecutionStatus().equals(ExecutionStatus.CAUTION)
					|| testCaseTestRun.getExecutionStatus().equals(ExecutionStatus.FAILED)) {
				testCaseStatus = "<strong>Test Cases: </strong><br/>";
				testCaseStatus += "&emsp;<strong>" + testCaseName + ": </strong>"
						+ testCaseTestRun.getExecutionStatus().getValue() + " at " + testCaseTestRun.getEndDate() + BR;
			}
		}
		return testCaseStatus;
	}

	public static TedamMailSender getTedamMailSender() {
		if (sender == null) {
			sender = new TedamMailSender();
		}
		return sender;
	}

}
