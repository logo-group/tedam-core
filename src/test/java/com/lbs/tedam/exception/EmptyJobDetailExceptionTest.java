package com.lbs.tedam.exception;

import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.lbs.tedam.exception.localized.EmptyJobDetailException;

public class EmptyJobDetailExceptionTest {

	@Test
	public void testEmptyJobDetailException() {
		EmptyJobDetailException exception = new EmptyJobDetailException();
		assertNull(exception.getMessage());
	}

}
