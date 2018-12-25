package com.lbs.tedam.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.lbs.tedam.snapshot.utils.SnapShotUtils;

public class SnapShotUtilsTest {

	private SnapShotUtils snapShot;

	@Before
	public void init() {
		snapShot = new SnapShotUtils();
	}

	@Test
	public void getMenuListTest() {
		assertNotNull(snapShot.getMenuList());
	}

	@Test
	public void getMenuBrowserListTest() {
		assertNotNull(snapShot.getMenuBrowserList());
	}

	@Test
	public void getMenuReportListTest() {
		assertNotNull(snapShot.getMenuReportList());
	}

	@Test
	public void getPopUpMenuItemsTest() {
		assertNull(snapShot.getPopUpMenuItems());
	}

	@Test
	public void getMenuBatchListTest() {
		assertNotNull(snapShot.getMenuBatchList());
	}

	@Test
	public void setPopUpMenuItemsTest() {
		List<Object> popUpMenuItems = new ArrayList<>();
		String popUp1 = "PopUp,1";
		String popUp2 = "PopUp,2";
		String popUp3 = "PopUp,3";
		popUpMenuItems.add(popUp1);
		popUpMenuItems.add(popUp2);
		popUpMenuItems.add(popUp3);
		snapShot.setPopUpMenuItems(popUpMenuItems);
	}

	@Test
	public void setPopUpMenuItemsNullTest() {
		List<Object> popUpMenuItems = new ArrayList<>();
		String popUp1 = "PopUp,1";
		String popUp2 = "PopUp,2";
		String popUp3 = "PopUp,3";
		popUpMenuItems.add(popUp1);
		popUpMenuItems.add(popUp2);
		popUpMenuItems.add(popUp3);
		Map<String, String> popUpMenu = null;
		snapShot.setPopUpMenuItems(popUpMenu);
	}

}
