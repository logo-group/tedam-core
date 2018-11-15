package com.lbs.tedam.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lbs.tedam.data.config.DataConfig;
import com.lbs.tedam.data.service.TestDataConfig;
import com.lbs.tedam.test.BaseServiceTest;
import com.lbs.tedam.util.EnumsV2.NotificationType;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestDataConfig.class, DataConfig.class })
public class NotificationGroupTest extends BaseServiceTest {

	@Test
	public void testSetters() {
		NotificationGroup notificationGroup = new NotificationGroup();
		notificationGroup.setGroupName("Grup1");
		notificationGroup.setType(NotificationType.MAIL);
		notificationGroup.setProject(null);
		List<Recipient> recipientList = new ArrayList<>();
		Recipient recipient = new Recipient();
		recipientList.add(recipient);
		notificationGroup.setRecipientList(recipientList);
	}

	@Test
	public void testGetters() {
		NotificationGroup notificationGroup = new NotificationGroup();
		notificationGroup.getRecipientList();
		notificationGroup.getGroupName();
		notificationGroup.getProject();
		notificationGroup.getType();
	}

}
