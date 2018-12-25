package com.lbs.tedam.generator.steptype;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DoubleClickGeneratorTest {

	@Test
	public void testValidateFalse() {
		DoubleClickGenerator generator = new DoubleClickGenerator();
		assertFalse(generator.validate());
	}

	@Test
	public void testValidateTrue() {
		DoubleClickGenerator generator = new DoubleClickGenerator();
		generator.setItemTag("item");
		assertTrue(generator.validate());
	}

	@Test
	public void testGenerate() {
		DoubleClickGenerator generator = new DoubleClickGenerator();
		generator.setItemTag("item value");
		generator.generate();
	}

	@Test
	public void testDegenerate() {
		DoubleClickGenerator generator = new DoubleClickGenerator();
		generator.setItemTag("item value");
		generator.degenerate(generator.getItemTag());
	}
}

