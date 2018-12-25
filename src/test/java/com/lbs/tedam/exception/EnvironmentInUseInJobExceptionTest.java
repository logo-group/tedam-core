package com.lbs.tedam.exception;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.lbs.tedam.exception.localized.EnvironmentInUseInJobException;

public class EnvironmentInUseInJobExceptionTest {

	@Test
	public void testEnvironmentInUseInJobExceptionTest() {
		List<Integer> jobIdList = new ArrayList<>();
		jobIdList.add(1);
		jobIdList.add(2);
		EnvironmentInUseInJobException exception = new EnvironmentInUseInJobException(jobIdList);
		assertNotNull(exception.getLocalizedMessage());
	}

}
