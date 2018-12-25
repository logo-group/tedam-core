package com.lbs.tedam.notification;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.lbs.tedam.util.EnumsV2.NotificationType;

public class NotifierFactoryTest {

	@Test
	public void testGetNotifier() {
		NotificationType type = NotificationType.MAIL;
		assertNotNull(NotifierFactory.getNotifier(type));
	}

}
