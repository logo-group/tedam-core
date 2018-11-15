package com.lbs.tedam.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lbs.tedam.data.config.DataConfig;
import com.lbs.tedam.data.service.TestDataConfig;
import com.lbs.tedam.test.BaseServiceTest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestDataConfig.class, DataConfig.class })
public class RecipientTest extends BaseServiceTest {

	@Test
	public void testSetters() {
		Recipient recipient = new Recipient();
		recipient.setCreatedUser("user");
		recipient.setAddress("address");
		recipient.setNotifcationGroup(null);
	}

	@Test
	public void testGetters() {
		Recipient recipient = new Recipient();
		recipient.getAddress();
		recipient.getNotifcationGroup();
	}
}
