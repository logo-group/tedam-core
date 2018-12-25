package com.lbs.tedam.exception.localized;

import static org.junit.Assert.assertNull;

import org.junit.Test;

public class TestSetDeleteExceptionTest {

	@Test
	public void testTestSetDeleteException() {
		TestSetDeleteException exception = new TestSetDeleteException("TestSetDeleteException");
		assertNull(exception.getMessage());
	}
}
