/*
 * Copyright 2014-2019 Logo Business Solutions
 * (a.k.a. LOGO YAZILIM SAN. VE TIC. A.S)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.lbs.tedam.snapshot.utils;

import com.lbs.tedam.model.DTO.ButtonCtrl;
import com.lbs.tedam.util.*;
import com.lbs.tedam.util.Enums.PathType;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SnapShotUtils {
	private static Logger LOGGER = LoggerFactory.getLogger(SnapShotUtils.class);
	private List<List<Integer>> m_totalMenulist = new ArrayList<List<Integer>>();
	private List<List<Integer>> m_defAndTransMenulist = new ArrayList<List<Integer>>();
	private List<List<Integer>> m_batchMenulist = new ArrayList<List<Integer>>();
	private List<List<Integer>> m_reportMenulist = new ArrayList<List<Integer>>();

	private Map<String, String> m_popUpMenuItems = null;

	public SnapShotUtils() {
		super();
	}

	public List<List<Integer>> getMenuList() {
		return m_totalMenulist;
	}

	public List<List<Integer>> getMenuBrowserList() {
		return m_defAndTransMenulist;
	}

	public List<List<Integer>> getMenuBatchList() {
		return m_batchMenulist;
	}

	public List<List<Integer>> getMenuReportList() {
		return m_reportMenulist;
	}

	public Map<String, String> getPopUpMenuItems() {
		return m_popUpMenuItems;
	}

	public void setPopUpMenuItems(List<Object> popUpMenuItems) {
		if (m_popUpMenuItems == null) {
			m_popUpMenuItems = new HashMap<String, String>();
		} else {
			m_popUpMenuItems.clear();
		}

		for (int i = 0; i < popUpMenuItems.size(); i++) {
			// It is parsed according to Name and tag values.
			String[] nameAndTag = popUpMenuItems.get(i).toString().split(",");
			nameAndTag[0] = nameAndTag[0].substring(1);
			nameAndTag[1] = nameAndTag[1].replace(")", "");
			m_popUpMenuItems.put(nameAndTag[0], nameAndTag[1]);
		}
	}

	public void setPopUpMenuItems(Map<String, String> popUpMenuItems) {
		this.m_popUpMenuItems = popUpMenuItems;
	}

	/**
	 * Used in: SnapshotCollector.bsh, SnapshotCollectorForBatch.bsh, MenuPathCollector.bsh
	 *
	 * @param type
	 * @return
	 * @throws XPathExpressionException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @author Ozgur.Ozbil
	 */
	public List<List<Integer>> createMenuPathList(PathType type) throws XPathExpressionException {
		String configPoolPath = PropUtils.getProperty(Constants.PROPERTY_MODULE_CONFIG_POOL_PATH);
		File file = new File(configPoolPath);
		// All config xml files under given container (c:\Projects\jguar_GIT_Set\jprod\Unity\bin\Config\Modules\)
		List<String> configFileNameList = listFilesForFolder(file);
		// Sorted list of config xml files with respect to product tree in J'guar
		List<String> configFileOrderList = TedamXPathUtils.getOrderedConfigFileNameList();
		XPathExpression expr;
		Document configDoc;
		XPath configXPath = XPathFactory.newInstance().newXPath();
		checkForConfigFileOrderList(configPoolPath, configFileNameList, configFileOrderList, configXPath);
		List<List<Integer>> menuList = getMenuListByType(type);
		return menuList;
	}

	private void checkForConfigFileOrderList(String configPoolPath, List<String> configFileNameList, List<String> configFileOrderList, XPath configXPath)
			throws XPathExpressionException {
		XPathExpression expr;
		Document configDoc;
		for (int j = 0; j < configFileOrderList.size(); j++) {
			if (configFileNameList.contains(configFileOrderList.get(j))) {
				// Gets root module definition element from config xml
				configDoc = TedamDOMUtils.domParserStarter(configPoolPath + "\\" + configFileOrderList.get(j));
				expr = configXPath.compile("//nodes/node");
				NodeList result = (NodeList) expr.evaluate(configDoc, XPathConstants.NODESET);
				checkForNodeListResult(result);
			}
		}
	}

	private void checkForNodeListResult(NodeList result) {
		for (int i = 0; i < result.getLength(); i++) {
			List<Integer> menuList = new ArrayList<Integer>();
			// Bypass improper nodes
			if (TedamDOMUtils.isDummyNode(result.item(i)) || result.item(i).getAttributes() == null
					|| result.item(i).getAttributes().getNamedItem(Constants.CONFIG_NODE_ATTRIBUTES_CODE) == null
					|| !"M".equals(result.item(i).getAttributes().getNamedItem(Constants.CONFIG_NODE_ATTRIBUTES_CODE).getNodeValue())) {
				continue;
			}
			// Loop for all first generation children, for defining pathTypes(Definitions, Transactions ,Operations, Reports...etc)
			checkForDummyNode(result, i, menuList);
		}
	}

	private void checkForDummyNode(NodeList result, int i, List<Integer> menuList) {
		for (int k = 0; k < result.item(i).getChildNodes().getLength(); k++) {
			if (TedamDOMUtils.isDummyNode(result.item(i).getChildNodes().item(k))) {
				continue;
			}
			// Fills member lists according to elements defined types
			createSeperatedMenus(result.item(i), k, menuList);
		}
	}

	private List<List<Integer>> getMenuListByType(PathType type) {
		switch (type) {
		case TOTAL:
			return m_totalMenulist;
		case DEFINITION_AND_TRANSACTION:
			return m_defAndTransMenulist;
		case BATCH:
			return m_batchMenulist;
		case REPORT:
			return m_reportMenulist;
		default:
			return m_defAndTransMenulist;
		}
	}

	/**
	 * Returns list of all documents under given folder.
	 *
	 * @param folder
	 * @return
	 * @author Ozgur.Ozbil
	 */
	private List<String> listFilesForFolder(final File folder) {
		List<String> configNameList = new ArrayList<String>();
		for (File listDoc : folder.listFiles()) {
			if (listDoc.isDirectory()) {
				listFilesForFolder(listDoc);
			} else {
				configNameList.add(listDoc.getName());
			}
		}
		return configNameList;
	}

	/**
	 * According to secondary parent groups under the module (definitions, movements, operations, reports ...), group ConfigPaths and assign them to lists.
	 *
	 * @param rootNode
	 * @param childIndex
	 * @param menuList
	 * @author Ozgur.Ozbil
	 */
	private void createSeperatedMenus(Node rootNode, int childIndex, List<Integer> menuList) {
		menuList.clear();
		switch (rootNode.getChildNodes().item(childIndex).getAttributes().getNamedItem(Constants.CONFIG_NODE_ATTRIBUTES_CODE).getNodeValue()) {
		case "C":
			// Browsers (Definitions/Transactions...etc)
			menuList.add(Integer.parseInt(rootNode.getAttributes().getNamedItem(Constants.CONFIG_NODE_ATTRIBUTES_ID).getNodeValue()));
			menuList.add(Integer.parseInt(rootNode.getChildNodes().item(childIndex).getAttributes().getNamedItem(Constants.CONFIG_NODE_ATTRIBUTES_ID).getNodeValue()));
			createMenu(rootNode.getChildNodes().item(childIndex), m_defAndTransMenulist, m_totalMenulist, menuList);
			break;
		case "O":
			// Operations(Batch)
			menuList.add(Integer.parseInt(rootNode.getAttributes().getNamedItem(Constants.CONFIG_NODE_ATTRIBUTES_ID).getNodeValue()));
			menuList.add(Integer.parseInt(rootNode.getChildNodes().item(childIndex).getAttributes().getNamedItem(Constants.CONFIG_NODE_ATTRIBUTES_ID).getNodeValue()));
			createMenu(rootNode.getChildNodes().item(childIndex), m_batchMenulist, m_totalMenulist, menuList);
			break;
		case "R":
			// Reports
			menuList.add(Integer.parseInt(rootNode.getAttributes().getNamedItem(Constants.CONFIG_NODE_ATTRIBUTES_ID).getNodeValue()));
			menuList.add(Integer.parseInt(rootNode.getChildNodes().item(childIndex).getAttributes().getNamedItem(Constants.CONFIG_NODE_ATTRIBUTES_ID).getNodeValue()));
			createMenu(rootNode.getChildNodes().item(childIndex), m_reportMenulist, m_totalMenulist, menuList);
			break;
		default:
			break;
		}
	}

	/**
	 * Recursive method. He visits the children of the node that came to him. Child nodes recursively if they have children. Otherwise it gets the current and adds it to both the
	 * menuPathlist and the totalMenuPathlist.
	 *
	 * @param node
	 * @param menuPathlist
	 * @param totalMenuPathlist
	 * @param menuPath
	 */
	private void createMenu(Node node, List<List<Integer>> menuPathlist, List<List<Integer>> totalMenuPathlist, List<Integer> menuPath) {
		List<Integer> tempMenuList;
		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			tempMenuList = new ArrayList<Integer>(menuPath);
			// Bypass improper nodes
			if (TedamDOMUtils.isDummyNode(node.getChildNodes().item(i))) {
				continue;
			} else if (node.getChildNodes().item(i).getAttributes() == null
					|| node.getChildNodes().item(i).getAttributes().getNamedItem(Constants.CONFIG_NODE_ATTRIBUTES_ID) == null) {
				continue;
			} else if (node.getChildNodes().item(i).hasChildNodes()) {
				// If there are child nodes under current node, recursively call same method until get info from every single leaf
				tempMenuList.add(Integer.parseInt(node.getChildNodes().item(i).getAttributes().getNamedItem(Constants.CONFIG_NODE_ATTRIBUTES_ID).getNodeValue()));
				createMenu(node.getChildNodes().item(i), menuPathlist, totalMenuPathlist, tempMenuList);
			} else {
				// If there is no child of the node definition in our hand
				tempMenuList.add(Integer.parseInt(node.getChildNodes().item(i).getAttributes().getNamedItem(Constants.CONFIG_NODE_ATTRIBUTES_ID).getNodeValue()));
				menuPathlist.add(tempMenuList);
				totalMenuPathlist.add(tempMenuList);
			}
		}
	}

	/**
	 * Returns the button with the DBNEW attribute on the browser.
	 * <p>
	 * Used in: SnapshotCollector.bsh
	 *
	 * @param element
	 * @return
	 * @throws XPathExpressionException
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	public ButtonCtrl getDBNewButton(Element element) throws XPathExpressionException {
		XPath xpath = XPathFactory.newInstance().newXPath();
		XPathExpression tagExpr = xpath.compile("//Control[@attribute='DBNEW']");

		NodeList btnDbNewList = (NodeList) tagExpr.evaluate(element, XPathConstants.NODESET);
		try {
			for (int i = 0; i < btnDbNewList.getLength(); i++) {
				if (btnDbNewList.item(i).getAttributes().getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_VISIBLE).getNodeValue().equals(Constants.VALUE_TRUE)
						&& btnDbNewList.item(i).getAttributes().getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_ENABLED).getNodeValue().equals(Constants.VALUE_TRUE)) {
					return fillButtonInfo(btnDbNewList.item(i));
				}
			}
		} catch (NullPointerException e) {
			LOGGER.error("" + e);
			return null;
		}
		return null;
	}

	/**
	 * Fills button information into a ButtonCtrl object, and returns that object.
	 *
	 * @param menuButtonNode
	 * @return
	 * @author Ozgur.Ozbil
	 */
	private ButtonCtrl fillButtonInfo(Node menuButtonNode) {
		ButtonCtrl buttonControl = new ButtonCtrl();
		if (menuButtonNode.getAttributes().getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_ITEMLIST) != null) {
			// If button is with type menuButton, element in xml document should have "itemList" attribute.
			buttonControl.setMenuButtonItemTagList(
					TedamStringUtils.collectResourceItemList(menuButtonNode.getAttributes().getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_ITEMLIST).getNodeValue()));
			buttonControl.setMenuButtonItemTextList(
					TedamStringUtils.collectResourceItemNameList(menuButtonNode.getAttributes().getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_ITEMLIST).getNodeValue()));
		}
		buttonControl.setTag(menuButtonNode.getAttributes().getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TAG).getNodeValue());
		buttonControl.setType(menuButtonNode.getAttributes().getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TYPE).getNodeValue());
		return buttonControl;
	}

	/**
	 * Returns the element that has the configIDs in the array given as parameters, cleared from the main menu.
	 *
	 * @param excludedMenuPaths
	 * @return
	 * @author Ozgur.Ozbil
	 */
	public List<List<Integer>> excludeMenuPaths(Integer[] excludedMenuPaths) {
		List<List<Integer>> menuList = new ArrayList<List<Integer>>();

		for (List<Integer> currentMenuPath : m_defAndTransMenulist) {
			boolean excludedCond = false;
			for (Integer currentExcluded : excludedMenuPaths) {
				if (currentMenuPath.contains(currentExcluded)) {
					excludedCond = true;
					break;
				}
			}
			if (!excludedCond) {
				menuList.add(currentMenuPath);
			}
		}

		return menuList;
	}

	/**
	 * Writes the given configOrderParameter value to the corresponding file.
	 *
	 * @param configOrderParameter
	 * @param isNextModule
	 *            If true, the next module value is found and its value is printed if false, an incoming value is written to the file.
	 */
	public void updateConfigOrderParameter(int configOrderParameter, boolean isNextModule) {
		int newConfigOrderParameter = 0;
		if (isNextModule) {
			newConfigOrderParameter = getConfigOrderParameter(configOrderParameter);
		}
		try (PrintStream ps = new PrintStream(PropUtils.getProperty(Constants.PROPERTY_SNAPSHOTCOLLECTOR_ORDERFILE_PATH))) {
			ps.print(newConfigOrderParameter);
		} catch (FileNotFoundException e) {
			LOGGER.error("" + e);
		}
	}

	/**
	 * This procedure calls the given int on static m_defAndTransMenulist static. the next module value is found. If the first value is not its own next value, it returns the
	 * variable.
	 *
	 * @param configOrderParameter
	 * @author Tarik.Mikyas
	 */
	public int getConfigOrderParameter(int configOrderParameter) {

		for (int i = 0; i < m_defAndTransMenulist.size(); i++) {
			if (configOrderParameter == m_defAndTransMenulist.get(i).get(0) && i != m_defAndTransMenulist.size() - 1
					&& m_defAndTransMenulist.get(i + 1).get(0) != configOrderParameter) {

				return m_defAndTransMenulist.get(i + 1).get(0);
			}
		}
		return configOrderParameter;
	}

}
