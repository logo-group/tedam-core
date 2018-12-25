package com.lbs.tedam.exception;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.lbs.tedam.exception.localized.JobDetailTestSetNotSelectedException;

public class JobDetailTestSetNotSelectedExceptionTest {

	@Test
	public void testJobDetailTestSetNotSelectedExceptionTest() {
		JobDetailTestSetNotSelectedException exception = new JobDetailTestSetNotSelectedException(1);
		assertNotNull(exception.getLocalizedMessage());
	}
}
