package com.lbs.tedam.exception;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.lbs.tedam.exception.localized.EmptyTestCaseException;

public class EmptyTestCaseExceptionTest {

	@Test
	public void testEmptyTestCaseExceptionTest() {
		EmptyTestCaseException exception = new EmptyTestCaseException(1);
		assertNotNull(exception.getLocalizedMessage());
	}
}
