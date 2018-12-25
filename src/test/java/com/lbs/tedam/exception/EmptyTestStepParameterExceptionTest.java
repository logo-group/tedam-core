package com.lbs.tedam.exception;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.lbs.tedam.exception.localized.EmptyTestStepParameterException;

public class EmptyTestStepParameterExceptionTest {

	@Test
	public void testEmptyTestStepParameterExceptionTest() {
		EmptyTestStepParameterException exception = new EmptyTestStepParameterException(1);
		assertNotNull(exception.getLocalizedMessage());
	}
}
