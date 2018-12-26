package com.lbs.tedam.model.DTO;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DoubleClickDTOTest {

	@Test
	public void testSetters() {
		DoubleClick doubleClick = new DoubleClick();
		doubleClick.setTitle("Double Click");
		doubleClick.setTag("DC");
		doubleClick.setType("Click");
	}

	@Test
	public void testGetters() {
		DoubleClick doubleClick = new DoubleClick();
		doubleClick.setTitle("Double Get");
		doubleClick.setTag("DC Get");
		doubleClick.setType("Click Get");
		String tag = doubleClick.getTag();
		String title = doubleClick.getTitle();
		String type = doubleClick.getType();
		assertEquals("DC Get", tag);
	}
}
