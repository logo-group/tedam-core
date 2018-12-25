package com.lbs.tedam.exception;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.lbs.tedam.exception.localized.EmptyTestStepException;

public class EmptyTestStepExceptionTest {

	@Test
	public void testEmptyTestStepExceptionTest() {
		EmptyTestStepException exception = new EmptyTestStepException(1);
		assertNotNull(exception.getLocalizedMessage());
	}
}
