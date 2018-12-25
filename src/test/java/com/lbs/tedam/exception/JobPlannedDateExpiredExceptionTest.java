package com.lbs.tedam.exception;

import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.lbs.tedam.exception.localized.JobPlannedDateExpiredException;

public class JobPlannedDateExpiredExceptionTest {

	@Test
	public void testJobPlannedDateExpiredException() {
		JobPlannedDateExpiredException exception = new JobPlannedDateExpiredException();
		assertNull(exception.getMessage());
	}

}
