package com.lbs.tedam.generator.steptype;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.lbs.tedam.exception.localized.LocalizedException;

public class WaitGeneratorTest {

	@Test
	public void validateTest() {
		WaitGenerator generator = new WaitGenerator();
		assertFalse(generator.validate());
	}

	@Test
	public void validateTrueTest() {
		WaitGenerator generator = new WaitGenerator();
		generator.setWaitSleepMillis(new Long(10));
		assertTrue(generator.validate());
	}

	@Test
	public void generateTest() {
		WaitGenerator generator = new WaitGenerator();
		assertNotNull(generator.generate());
	}

	@Test
	public void generateNotNullMilisTest() {
		WaitGenerator generator = new WaitGenerator();
		generator.setWaitSleepMillis(new Long(10));
		assertNotNull(generator.generate());
	}

	@Test
	public void getContinueOnErrorTest() {
		WaitGenerator generator = new WaitGenerator();
		assertEquals("0", generator.getContinueOnError());
	}

	@Test
	public void getWriteFiltersTest() {
		WaitGenerator generator = new WaitGenerator();
		assertEquals("1", generator.getWriteFilters());
	}

	@Test
	public void setWriteFiltersTest() {
		WaitGenerator generator = new WaitGenerator();
		generator.setWriteFilters("0");
		assertEquals("0", generator.getWriteFilters());
	}

	@Test
	public void degenerateTest() throws LocalizedException {
		WaitGenerator generator = new WaitGenerator();
		generator.degenerate("");
	}

	@Test
	public void degenerateParameterTest() throws LocalizedException {
		WaitGenerator generator = new WaitGenerator();
		generator.degenerate("12651");
	}

	@Test
	public void getWaitSleepMillisTest() {
		WaitGenerator generator = new WaitGenerator();
		generator.setWaitSleepMillis(new Long(10));
		assertNotNull(generator.getWaitSleepMillis());
	}

	@Test
	public void setContinueOnErrorTest() {
		WaitGenerator generator = new WaitGenerator();
		generator.setContinueOnError("1");
		assertNotNull(generator.getContinueOnError());
	}
}
