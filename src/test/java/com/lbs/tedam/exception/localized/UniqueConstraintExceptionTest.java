package com.lbs.tedam.exception.localized;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class UniqueConstraintExceptionTest {

	@Test
	public void testUniqueConstraintException() {
		UniqueConstraintException exception = new UniqueConstraintException(new Exception("uniqueException"));
		assertNotNull(exception.getMessage());
	}

}
