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

package com.lbs.tedam.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.lbs.tedam.model.SnapshotValue;
import com.lbs.tedam.model.DTO.ButtonCtrl;
import com.lbs.tedam.model.DTO.DoubleClick;
import com.lbs.tedam.model.DTO.FormName;
import com.lbs.tedam.model.DTO.GridCell;
import com.lbs.tedam.model.DTO.GridRow;
import com.lbs.tedam.model.DTO.TabbedPaneAndPageParent;
import com.lbs.tedam.util.Enums.SnapshotControls;
import com.lbs.tedam.util.Enums.TedamLogLevel;

public class TedamXPathUtils extends TedamBaseXPathUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(TedamXPathUtils.class);
	private static final String METHOD_NAME = "TedamXPathUtils.getReportNameFromFile";
	//////////////////////////////////////////////
	//////////////////////////////////////////////
	////////////////////////////////////////////// START from DifferencesSnapshot
	//////////////////////////////////////////////
	//////////////////////////////////////////////
	private static final List<String> unpermittedComponentList = new ArrayList<>(Arrays.asList(
			Constants.COMBO_CONTROLTYPE_LABEL, Constants.COMBO_CONTROLTYPE_IMAGE, Constants.COMBO_CONTROLTYPE_BUTTON,
			Constants.COMBO_CONTROLTYPE_IMAGEBUTTON, Constants.COMBO_CONTROLTYPE_MENUBUTTON));

	/**
	 * It is used to take columns and rows in the given node and to pour it into a
	 * grid as in the case. With this method, List <GridRow> is created.
	 *
	 * @param node
	 * @return
	 * @author Ahmet.Izgi
	 */
	public static List<GridRow> getGridMatrix(Node node) {
		List<GridRow> lstGridRows = new ArrayList<GridRow>();
		NodeList rows;
		NodeList columns;

		if (TedamDOMUtils.isDummyNode(node.getChildNodes().item(0))) {
			rows = node.getChildNodes().item(3).getChildNodes();
			columns = node.getChildNodes().item(1).getChildNodes();
		} else {
			rows = node.getChildNodes().item(1).getChildNodes();
			columns = node.getChildNodes().item(0).getChildNodes();
		}

		int rowIndex = 0;
		for (int i = 0; i < rows.getLength(); i++) {
			GridRow gridRow = new GridRow();
			if (TedamDOMUtils.isDummyNode(rows.item(i))) {
				continue;
			}
			for (int j = 0; j < columns.getLength(); j++) {
				if (TedamDOMUtils.isDummyNode(columns.item(j))) {
					continue;
				}
				if (columns.item(j).getAttributes().getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_VISIBLE)
						.getNodeValue().equals(Constants.VALUE_FALSE)) {
					continue;
				}
				NamedNodeMap colAttr = columns.item(j).getAttributes();
				NamedNodeMap rowAttr = rows.item(i).getAttributes();

				String tag = colAttr.getNamedItem("tag").getNodeValue();
				String caption = colAttr.getNamedItem("caption").getNodeValue();
				String value = rowAttr.getNamedItem("col" + tag).getNodeValue();
				gridRow.add(new GridCell(tag, caption, value, rowIndex));
			}
			rowIndex++;
			lstGridRows.add(gridRow);
		}

		return lstGridRows;
	}

	/**
	 * Searches for the given gridTag value as //DataGrid[tag] in the file named
	 * fileAbsolutePath.
	 *
	 * @param gridTag
	 * @param fileAbsolutePath
	 * @return The node found is descended. Returns null if not found.
	 * @author Ahmet.Izgi
	 */
	public static Node getGridNode(String gridTag, String fileAbsolutePath) {
		return getNodeFromDocument(TedamDOMUtils.domParserStarter(fileAbsolutePath),
				Constants.XPATH_DATAGRID_TAG + gridTag + Constants.XPATH_END_OF_COMPILE);
	}

	/**
	 * Goes to the file in the given path and returns the elements of the popup to
	 * List<String>.
	 *
	 * @param fileAbsolutePath
	 * @return
	 * @author Ahmet.Izgi
	 */
	public static List<String> getPopupItemList(String fileAbsolutePath) {

		List<String> popupItems = new ArrayList<String>();
		try {
			XPath xpath = XPathFactory.newInstance().newXPath();
			XPathExpression tagExpr = xpath.compile(Constants.XPATH_CONTROL_ATTRIBUTE
					+ Constants.PROPERTY_PROPERTY_ATTRIBUTE_FORMPOPUP + Constants.XPATH_END_OF_COMPILE);
			NodeList a = (NodeList) tagExpr.evaluate(TedamDOMUtils.domParserStarter(fileAbsolutePath),
					XPathConstants.NODESET);
			String[] itemList = a.item(0).getAttributes().getNamedItem(Constants.XPATH_ITEM_LIST_ITEM).getNodeValue()
					.split("\\|");
			for (int i = 0; i < itemList.length; i++) {
				String[] temp = itemList[i].split("~");
				if ("-".equals(temp[0]) || temp[0].isEmpty()) {
					continue;
				}
				popupItems.add(temp[0]);
			}
			return popupItems;
		} catch (XPathExpressionException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return popupItems;
	}

	public static List<String> getGridTags(String fileAbsolutePath) {

		List<String> tags = new ArrayList<String>();
		Document doc = TedamDOMUtils.domParserStarter(fileAbsolutePath);
		NodeList nodes = getNodeListFromDocument(doc, Constants.XPATH_DATAGRID);
		for (int i = 0; i < nodes.getLength(); i++) {
			if (nodes.item(i).getAttributes() != null && nodes.item(i).getAttributes().getNamedItem("tag") != null) {
				tags.add(nodes.item(i).getAttributes().getNamedItem("tag").getNodeValue());
			}
		}
		return tags;
	}

	/**
	 * @param document
	 * @param parentID
	 * @return <br>
	 *         this method getPropertValueFromDocument
	 * @author Tarik.Mikyas
	 */
	public static String getPropertValueFromDocument(Document document, String parentID) {
		Node node = getNodeFromDocument(document,
				"//DataGrid[not(ancestor::Control[@visible='false' and @type!='3'])][@visible='true' and @tag='"
						+ parentID + "']");
		return node != null ? node.getAttributes().getNamedItem(Constants.SNAPSHOT_GRID_SPLITTER_INDEX).getNodeValue()
				: null;
	}

	/**
	 * Returns the tag value of the control element whose attribute is popup in the
	 * snapshot given as parameter.
	 *
	 * @param formName
	 * @return
	 * @throws XPathExpressionException
	 */
	public static int getPopUpMenuTag(Element snapshot) {

		Node node = getNodeFromSnapshot(snapshot, Constants.XPATH_CONTROL_ATTRIBUTE
				+ Constants.PROPERTY_PROPERTY_ATTRIBUTE_FORMPOPUP + Constants.XPATH_END_OF_COMPILE);
		if (node != null) {
			return Integer.parseInt(
					node.getAttributes().getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TAG).getNodeValue());
		} else {
			return Constants.VALUE_NULL_INTEGER;
		}
	}

	/**
	 * Returns the tag value of the control element whose attribute is CLOSE in the
	 * snapshot given as parameter. will return '0' in case of error.
	 *
	 * @param xmlDoc
	 * @return
	 * @author Tarik.Mikyas
	 */
	public static int getCloseButton(Element snapshot) {

		NodeList nodeList = getNodeListFromSnapshot(snapshot,
				Constants.XPATH_CONTROL_ATTRIBUTE + Constants.CTRL_ATTRIBUTE_CLOSE + Constants.XPATH_END_OF_COMPILE);
		if (nodeList == null) {
			return 0;
		}

		for (int i = 0; i < nodeList.getLength(); i++) {
			NamedNodeMap namedNodeMap = nodeList.item(i).getAttributes();
			if (namedNodeMap == null || TedamDOMUtils.isDummyNode(nodeList.item(i))) {
				continue;
			} else if (namedNodeMap.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_VISIBLE).getNodeValue()
					.equals(Constants.VALUE_TRUE)
					&& namedNodeMap.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_ENABLED).getNodeValue()
							.equals(Constants.VALUE_TRUE)) {
				return Integer.parseInt(nodeList.item(i).getAttributes()
						.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TAG).getNodeValue());
			}
		}
		return 0;
	}

	/**
	 * Returns the value of the node whose attribute is name in the snapshot given
	 * as a parameter.
	 *
	 * @param snapshot
	 * @return
	 * @author Tarik.Mikyas
	 */
	public static String getFormNameFromSnapshot(Element snapshot) {
		Node node = getNodeFromSnapshot(snapshot, Constants.XPATH_CONTROL);
		String version = node.getParentNode().getAttributes().getNamedItem("name").getNodeValue();
		if (version != null) {
			return version.replace(".jfm", "");
		}
		return null;
	}

	/**
	 * In the given snapshot, the control with fieldTagId tag value is found. All
	 * the tabs / tabbedPane binaries contained in the found control are
	 * hierarchically listed up to root. If a field that is to be edited while
	 * formFill is done in this way is in a different tab, then tabbing can be
	 * performed.
	 *
	 * @param element
	 * @param fieldTagId
	 * @return All parent nodes of type TabbedPaneAndPageParent are returned as
	 *         control.
	 * @throws XPathExpressionException
	 * @usedIn: FormFill.bsh
	 */
	public static List<TabbedPaneAndPageParent> getControlTabProperties(Element snapshot, int fieldTagId) {
		List<TabbedPaneAndPageParent> tabbedPaneAndPageParentList = new ArrayList<>();
		// The snapshot that is given as a parameter has a control element with the
		// value of the field tag given as a parameter.
		NodeList nodeList = getNodeListFromSnapshot(snapshot,
				Constants.XPATH_CONTROL_TAG + fieldTagId + Constants.XPATH_END_OF_COMPILE);
		if (nodeList != null && nodeList.getLength() > 0) {
			return getTabbedPaneAndPageParentList(nodeList.item(0));
		} else {
			return tabbedPaneAndPageParentList;
		}
	}

	/**
	 * The given snapshot contains the DataGrid with the dataGridTag tag value. All
	 * of the tab / tabbedPane binaries in the found DataGrid are hierarchically
	 * listed up to root.If a field that is to be edited while formFill is done in
	 * this way is in a different tab, then tabbing can be performed.
	 * <p>
	 * Used for: FormFill.bsh, PopUp.bsh
	 *
	 * @param element
	 * @param dataGridTag
	 * @return
	 * @throws XPathExpressionException
	 */
	public static List<TabbedPaneAndPageParent> getGridTabParents(Element snapshot, Integer dataGridTag) {
		List<TabbedPaneAndPageParent> tabbedPaneAndPageParentList = new ArrayList<>();
		// The parameter SnapShot contains a DataGrid element with the dataGrid tag
		// value given as a parameter.
		NodeList nodeList = getNodeListFromSnapshot(snapshot,
				Constants.XPATH_DATAGRID_TAG + dataGridTag + Constants.XPATH_END_OF_COMPILE);
		if (nodeList != null && nodeList.getLength() > 0) {
			return getTabbedPaneAndPageParentList(nodeList.item(0));
		} else {
			return tabbedPaneAndPageParentList;
		}
	}

	/**
	 * The given snapshot contains the filterGrid with the filterGridTag tag value.
	 * All tab / tabbedPane binaries in the filterGrid found are hierarchically
	 * listed up to root. If a field that is to be edited while formFill is done in
	 * this way is in a different tab, then tabbing can be performed.
	 * <p>
	 * Used for: FilterFill.bsh
	 *
	 * @param element
	 * @param filterGridTag
	 * @return
	 * @throws XPathExpressionException
	 */
	public static List<TabbedPaneAndPageParent> getFilterTabParents(Element snapshot, int filterGridTag) {
		// The snapshot that is given as a parameter contains the FilterGrid element
		// with the value of the filterGrid tag given as a parameter.
		List<TabbedPaneAndPageParent> tabbedPaneAndPageParentList = new ArrayList<>();
		Node node = getNodeFromSnapshot(snapshot,
				Constants.XPATH_FILTERGRID_TAG + filterGridTag + Constants.XPATH_END_OF_COMPILE);
		if (node != null) {
			return getTabbedPaneAndPageParentList(node);
		} else {
			return tabbedPaneAndPageParentList;
		}
	}

	/**
	 * @param nodeList
	 * @return <br>
	 *         it aggregates the information of tabs hierarchically closest to the
	 *         root depending on the given node and returns a list of type List
	 *         <TabbedPaneAndPageParent>.
	 * @author Tarik.Mikyas
	 */
	private static List<TabbedPaneAndPageParent> getTabbedPaneAndPageParentList(Node node) {
		List<TabbedPaneAndPageParent> tabbedPaneAndPageParentList = new ArrayList<>();
		// The table that is found collects information of the tabs in the hierarchy up
		// to the nearest coke.
		while (node.getParentNode() != null && node.getParentNode().getAttributes() != null && node.getParentNode()
				.getAttributes().getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TYPE) != null) {
			TabbedPaneAndPageParent tpapp = new TabbedPaneAndPageParent();
			if (SnapshotControls.TAB.getType()
					.equals(node.getParentNode().getAttributes()
							.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TYPE).getNodeValue())
					&& node.getParentNode().getParentNode() != null
					&& SnapshotControls.TABBED_PANE.getType().equals(node.getParentNode().getParentNode()
							.getAttributes().getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TYPE).getNodeValue())) {
				// The parent is a list with tabbedPane (type = 2), the tab itself (type = 3) to
				// be able to open the parent values one by one.
				tpapp.setTag(Integer.parseInt(node.getParentNode().getParentNode().getAttributes()
						.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TAG).getNodeValue()));
				tpapp.setValue(node.getParentNode().getAttributes()
						.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_VALUE).getNodeValue());
				tabbedPaneAndPageParentList.add(tpapp);
			}
			node = node.getParentNode();
		}
		return tabbedPaneAndPageParentList;
	}

	/**
	 * The snapshot parameter returns the tag value of the first DataGrid element
	 * found in the XML document.
	 *
	 * @param snapShot
	 * @return
	 * @throws XPathExpressionException
	 * @author Ozgur.Ozbil
	 */
	public static int getFirstOccuredGridTag(Element snapshot) {
		// The database definitions contained in the given form snapshot are collected.
		NodeList nodeList = getNodeListFromSnapshot(snapshot, Constants.XPATH_DATAGRID);
		if (nodeList != null && nodeList.getLength() > 0 && nodeList.item(0).getAttributes() != null
				&& nodeList.item(0).getAttributes().getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TAG) != null) {
			return Integer.parseInt(nodeList.item(0).getAttributes()
					.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TAG).getNodeValue());
		} else {
			// In cases where the appropriate DataGrid definition can not be found, a value
			// of -9999 is returned.
			return Constants.VALUE_NULL_INTEGER;
		}
	}

	/**
	 * It examines the message definitions in the given snapshot. Messages with type
	 * value of the given messageType value are returned. If the MessageType value
	 * is -9999, it returns all results.
	 *
	 * @param snapShot
	 * @param messageType
	 * @return
	 * @throws XPathExpressionException
	 * @author Ozgur.Ozbil
	 */
	public static List<String> getMessages(Element snapshot, int messageType) {
		List<String> messageList = new ArrayList<>();
		NodeList nodeList;
		String compileParameter;
		if (messageType != Constants.VALUE_NULL_INTEGER) {
			// If the message type value is given
			compileParameter = Constants.XPATH_MESSAGE_TYPE + messageType + Constants.XPATH_END_OF_COMPILE;
		} else {
			// To return a message type independent result
			compileParameter = Constants.XPATH_MESSAGE;
		}
		nodeList = getNodeListFromSnapshot(snapshot, compileParameter);
		if (nodeList != null) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				messageList.add(nodeList.item(i).getAttributes()
						.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_MESSAGE).getNodeValue().trim());
			}
		}
		return messageList;
	}

	/**
	 * This method checks the enabled value of the control element with the given
	 * tag. <br>
	 * Enabled if true; false if it is not. If the snapshot or tag is incorrect,
	 * nullPointerException is thrown.<br>
	 * Used in: FormFill.bsh
	 *
	 * @param snapShot
	 * @param tag
	 * @throws XPathExpressionException
	 */
	public static boolean isControlEnabled(Element snapshot, int tag) {
		Node node = getNodeFromSnapshot(snapshot, Constants.XPATH_ALL_TAG + tag + Constants.XPATH_END_OF_COMPILE);
		if (node != null && node.getAttributes().getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_ENABLED)
				.getNodeValue().trim().equals(Constants.VALUE_TRUE)) {
			return true;
		} else if (node == null) {
			throw new NullPointerException(
					"in snapshot" + tag + " field was not found. Snapshot is bad or tag removed.");
		}
		return false;
	}

	/**
	 * Returns true if there is a control tagged element that holds the given
	 * buttonTag as a "tag" attribute value, with "visible" and "enabled" equals
	 * true.
	 *
	 * @param snapshot
	 * @param buttonTag
	 * @return
	 * @throws XPathExpressionException
	 * @author Tarik.Mikyas
	 */
	public static boolean buttonExists(Element snapshot, int buttonTag) {
		Node button = getNodeFromSnapshot(snapshot,
				Constants.XPATH_CONTROL_TAG + buttonTag + Constants.XPATH_END_OF_COMPILE);
		if (button != null
				&& button.getAttributes().getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_VISIBLE).getNodeValue()
						.equals(Constants.VALUE_TRUE)
				&& button.getAttributes().getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_ENABLED).getNodeValue()
						.equals(Constants.VALUE_TRUE)) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the rowIndex of Splitter of the grid with given gridTag in given
	 * xmlDoc Used in: FormFill.bsh In this procedure, the
	 * Constants.VALUE_NULL_INTEGER value is returned if an error is encountered. It
	 * should be checked where he was called gore.
	 *
	 * @param snapshot
	 * @param gridTag
	 * @return
	 * @throws XPathExpressionException
	 */
	public static int getCurrentGridSplitter(Element snapshot, String gridTag) {
		NodeList nodelist = getNodeListFromSnapshot(snapshot,
				Constants.XPATH_DATAGRID_TAG + gridTag + Constants.XPATH_END_OF_COMPILE);
		if (nodelist != null && nodelist.getLength() > 0) {
			return Integer.valueOf(nodelist.item(0).getAttributes().getNamedItem(Constants.SNAPSHOT_GRID_SPLITTER_INDEX)
					.getNodeValue());
		} else {
			return Constants.VALUE_NULL_INTEGER;
		}
	}

	/**
	 * This method returns the tag of the button whose attribute is equal to the 2nd
	 * parameter in the parameterized snapshot. used by SnapShotUtils In this
	 * procedure, the Constants.VALUE_NULL_INTEGER value is returned if an error is
	 * encountered. According to him, control must be done where he is called.
	 *
	 * @param snapshot
	 * @param buttonAttribute
	 * @return
	 * @throws XPathExpressionException
	 * @throws TransformerException
	 */
	public static int getButtonTag(Element snapshot, String buttonAttribute) {
		Node buttonTag = getNodeFromSnapshot(snapshot,
				Constants.XPATH_CONTROL_ATTRIBUTE + buttonAttribute + Constants.XPATH_END_OF_COMPILE);
		if (buttonTag != null) {
			return Integer.parseInt(
					buttonTag.getAttributes().getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TAG).getNodeValue());
		} else {
			return Constants.VALUE_NULL_INTEGER;
		}
	}

	/**
	 * @param snapshot
	 * @return <br>
	 *         Snapshot version is used to capture knowledge.
	 * @author Tarik.Mikyas
	 */
	public static String getVersionFromSnapshot(Element snapshot) {
		Node node = getNodeFromSnapshot(snapshot, Constants.XPATH_CONTROL);
		// Snapshot When reaching the first element of xml form, when we have problems
		// from time to time, we reach the first control element and reach its
		// parentina.
		if (node != null) {
			// the version attribute is returned by refining the first character "v".
			String version = node.getParentNode().getAttributes().getNamedItem(Constants.SNAPSHOT_FORM_VERSION)
					.getNodeValue();
			if (version.substring(0, 1).equalsIgnoreCase(Constants.XPATH_IGNORED_VERSION_CHAR)) {
				return version.substring(1, version.length());
			} else {
				return version;
			}
		}
		return null;
	}

	/**
	 * The dataGrid and control fields in the snapshot given as parameters are added
	 * to the map as <tag, value>.
	 *
	 * @param xmlDoc
	 * @return
	 * @throws XPathExpressionException
	 * @author Ozgur.Ozbil
	 */
	public static Map<String, String> getSnapshotFieldMap(Element snapshot) {
		Map<String, String> verifyFieldMap = new HashMap<>();
		NodeList visibleNodeList = getNodeListFromSnapshot(snapshot,
				Constants.XPATH_CONTROL_VISIBLE + Constants.VALUE_TRUE + Constants.XPATH_END_OF_COMPILE);
		for (int i = 0; (visibleNodeList != null && i < visibleNodeList.getLength()); i++) {
			if (TedamDOMUtils.isDummyNode(visibleNodeList.item(i))) {
				continue;
			}
			if (visibleNodeList.item(i).getChildNodes().getLength() >= 1) {
				continue;
			}

			verifyFieldMap = getMapByVisibleNodeList(visibleNodeList.item(i).getAttributes(), verifyFieldMap);
		}

		// For values entered under DataGrid.
		NodeList rowsNodelist = getNodeListFromSnapshot(snapshot, Constants.XPATH_ROWS);
		// It returns for ROWS of each grid in SNAPSHOT.
		for (int i = 0; (rowsNodelist != null && i < rowsNodelist.getLength()); i++) {
			if (TedamDOMUtils.isDummyNode(rowsNodelist.item(i))) {
				continue;
			}
			verifyFieldMap = getMapByRowsNodeList(rowsNodelist.item(i), verifyFieldMap);
		}

		return verifyFieldMap;
	}

	/**
	 * @param namedNodeMap
	 * @return <br>
	 *         this method get Map By VisibleNodeList
	 * @author Tarik.Mikyas
	 */
	private static Map<String, String> getMapByVisibleNodeList(NamedNodeMap namedNodeMap,
			Map<String, String> verifyFieldMap) {
		if (namedNodeMap != null && namedNodeMap.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TAG) != null) {
			if (namedNodeMap.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TYPE).getNodeValue()
					.equals(Constants.COMBO_CONTROLTYPE_CHECKBOXGROUP)
					&& namedNodeMap.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_SELECTEDLIST) != null) {
				// The control element type in our hand is checkBoxGroup (151)
				Node selectedList = namedNodeMap.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_SELECTEDLIST);
				// The values of the selectList attribute are parse and only the tag values
				// shown after the lambda character are separated by the character
				// "/" and taken as value. (selectedList = "commercial merchandise ~ 1 |
				// non-merchandise merchandise ~ 2 | normal merchandise so ~ 3" value: 1/2/3)
				String[] selectedItems = selectedList.getNodeValue().split("\\|");
				StringBuilder parameter = new StringBuilder("");
				for (int j = 0; j < selectedItems.length; j++) {
					parameter.append("/").append(selectedItems[j].substring(selectedItems[j].indexOf('~') + 1));
				}
				verifyFieldMap.put(namedNodeMap.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TAG).getNodeValue(),
						parameter.substring(1));
			} else if (namedNodeMap.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_VALUETAG) != null) {
				// We take value_tag instead of value.
				verifyFieldMap.put(namedNodeMap.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TAG).getNodeValue(),
						namedNodeMap.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_VALUETAG).getNodeValue()
								.trim());
			} else if (namedNodeMap.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_VALUE) != null) {
				// Standard available control definitions
				verifyFieldMap.put(namedNodeMap.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TAG).getNodeValue(),
						namedNodeMap.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_VALUE).getNodeValue().trim());
			}
		}
		return verifyFieldMap;
	}

	/**
	 * @param node
	 * @return <br>
	 *         this method get MapBy RowsNodeList
	 * @author Tarik.Mikyas
	 */
	private static Map<String, String> getMapByRowsNodeList(Node node, Map<String, String> verifyFieldMap) {
		// It returns for ROWs in ROWS.
		NodeList childNodes = node.getChildNodes();
		int rowIndex = 0;
		for (int i = 0; i < childNodes.getLength(); i++) {
			if (TedamDOMUtils.isDummyNode(childNodes.item(i))) {
				continue;
			}
			// It returns for col values in ROW.
			NamedNodeMap namedNodeMap = childNodes.item(i).getAttributes();

			for (int j = 0; (namedNodeMap != null && j < namedNodeMap.getLength()); j++) {
				if (TedamDOMUtils.isDummyNode(namedNodeMap.item(j))) {
					continue;
				}
				if (!(namedNodeMap.item(j).getNodeName().equals(Constants.SNAPSHOT_GRID_ROW_STATE))
						&& !(namedNodeMap.item(j).getNodeName().contains("_tag"))) {
					// key: colTag + "," + rowIndex The standard cell value is taken.
					verifyFieldMap.put(namedNodeMap.item(j).getNodeName().substring(3) + "," + rowIndex,
							namedNodeMap.item(j).getNodeValue().trim());
				}
			}
			// A lower row is checked.
			rowIndex++;
		}

		return verifyFieldMap;
	}

	/**
	 * @param filePath
	 * @return <br>
	 *         The XML expression finds reportFilePath to determine where to put the
	 *         report.
	 * @author Tarik.Mikyas
	 */
	public static String findExcelFilePath(String filePath) {
		// The commandline process returns null because no excelpath occurred.
		if (filePath.isEmpty()) {
			return "";
		}
		String reportFilePath = null;
		Document document = getDocumentFromFilePath(filePath);
		if (document != null) {
			NodeList nodesReportFile = getNodeListFromDocument(document, "/TestPlayer/ReportFile");
			for (int i = 0; i < nodesReportFile.getLength(); i++) {
				Node node1 = nodesReportFile.item(i);
				reportFilePath = node1.getAttributes().getNamedItem("Directory").getTextContent()
						+ Constants.FILE_SEPARATOR + node1.getAttributes().getNamedItem("Name").getTextContent() + ".";
			}
			NodeList nodesTestPackageFile = getNodeListFromDocument(document,
					"/TestPlayer/TestPackageList/TestPackage");
			for (int i = 0; i < nodesTestPackageFile.getLength(); i++) {
				Node node1 = nodesTestPackageFile.item(i);
				reportFilePath = reportFilePath + node1.getAttributes().getNamedItem("Name").getTextContent()
						+ Constants.FILE_EXTENSION_XLS;
			}
		}
		return reportFilePath;
	}

	/**
	 * In the ModuleSchema.xml document found under
	 * c:\Projects\jguar_GIT_Set\jprod\UnityServer\Config\Modules\ path, there is a
	 * sequence of config xml on the J'guar product tree. This method parses
	 * ModuleSchema.xml and returns the name of the config documents of the modules
	 * in the tree, respectively.
	 *
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws XPathExpressionException
	 */
	public static List<String> getOrderedConfigFileNameList() {
		List<String> configOrderList = new ArrayList<>();
		Document document = getDocumentFromFilePath(PropUtils.getProperty(Constants.PROPERTY_MODULE_CONFIG_ORDER_PATH));
		NodeList nodeList = getNodeListFromDocument(document, "//module-definitions");
		for (int i = 0; i < nodeList.item(0).getChildNodes().getLength(); i++) {
			if (TedamDOMUtils.isDummyNode(nodeList.item(0).getChildNodes().item(i))) {
				continue;
			}
			configOrderList.add(nodeList.item(0).getChildNodes().item(i).getAttributes()
					.getNamedItem(Constants.CONFIG_NODE_ATTRIBUTES_NAME).getNodeValue());
		}
		return configOrderList;
	}

	/**
	 * All the buttons in the snapshot given as parameter; type, tag, xuiDoc,
	 * attribute and menubutton return itemList values.
	 *
	 * @param snapshot
	 * @return
	 * @throws XPathExpressionException
	 * @author Ozgur.Ozbil
	 */
	public static List<ButtonCtrl> getButtonList(Element snapshot) {
		List<ButtonCtrl> returnButtonList = new ArrayList<>();
		// Button, menuButton and imageButton control values are retrieved.
		NodeList nodeList = getNodeListFromSnapshot(snapshot,
				"//Control[@type='109' or @type='111' or @type='112' or contains(@type, 'click')]"); // TODO:canberk
																										// bunları proje
		// bazında
		// we must make it dynamic.
		for (int i = 0; i < nodeList.getLength(); i++) {
			if (TedamDOMUtils.isDummyNode(nodeList.item(i))) {
				continue;
			}
			if (nodeList.item(i).getAttributes().getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_ENABLED)
					.getNodeValue().equals(Constants.VALUE_TRUE)
					&& nodeList.item(i).getAttributes().getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_VISIBLE)
							.getNodeValue().equals(Constants.VALUE_TRUE)) {
				// enabled and visible attributes must be true.
				ButtonCtrl buttonProp = new ButtonCtrl();
				buttonProp.setType(nodeList.item(i).getAttributes()
						.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TYPE).getNodeValue());
				if (nodeList.item(i).getAttributes().getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TYPE)
						.getNodeValue().equals(Constants.COMBO_CONTROLTYPE_MENUBUTTON)) {
					// If menuButton is in the itemlist attribute, we also skip the info.
					String itemList = nodeList.item(i).getAttributes()
							.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_ITEMLIST).getNodeValue();
					buttonProp.setMenuButtonItemTagList(TedamStringUtils.collectResourceItemList(itemList));
					buttonProp.setMenuButtonItemTextList(TedamStringUtils.collectResourceItemNameList(itemList));
				}
				if (nodeList.item(i).getAttributes()
						.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_ATTRIBUTE) != null) {
					// The button's attribute value is set.
					buttonProp.setAttribute(nodeList.item(i).getAttributes()
							.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_ATTRIBUTE).getNodeValue());
				}
				buttonProp.setTag(nodeList.item(i).getAttributes()
						.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TAG).getNodeValue());
				buttonProp.setXuiDoc(nodeList.item(i).getAttributes()
						.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TITLE).getNodeValue());
				returnButtonList.add(buttonProp);
			}
		}
		return returnButtonList;
	}

	public static List<DoubleClick> getDoubleClickList(Element snapshot) {
		List<DoubleClick> doubleClikList = new ArrayList<>();
		NodeList nodeList = getNodeListFromSnapshot(snapshot,
				"//Control[contains(@type, 'write') or contains(@type, 'read') or contains(@type, 'inputtextbox')]");
		for (int i = 0; i < nodeList.getLength(); i++) {
			if (TedamDOMUtils.isDummyNode(nodeList.item(i))) {
				continue;
			}
			if (nodeList.item(i).getAttributes().getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_ENABLED)
					.getNodeValue().equals(Constants.VALUE_TRUE)
					&& nodeList.item(i).getAttributes().getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_VISIBLE)
							.getNodeValue().equals(Constants.VALUE_TRUE)) {
				DoubleClick doubleClick = new DoubleClick();
				doubleClick.setTag(nodeList.item(i).getAttributes()
						.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TAG).getNodeValue());
				doubleClick.setType(nodeList.item(i).getAttributes()
						.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TYPE).getNodeValue());
				doubleClick.setTitle(nodeList.item(i).getAttributes()
						.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TITLE).getNodeValue());
				doubleClikList.add(doubleClick);
			}
		}
		return doubleClikList;
	}

	/**
	 * This method returns given tag's xml trace with title attributes until the
	 * root
	 *
	 * @param snapshot
	 * @param tag
	 * @return
	 * @author Ozgur.Ozbil
	 */
	public static String pathTooltip(Element snapshot, String tag) {
		String pathString = "";
		Node node = getNodeFromSnapshot(snapshot, Constants.XPATH_ALL_TAG + tag + Constants.XPATH_END_OF_COMPILE);
		// Loop continues until it reaches to the root element
		while (node != null && !node.getParentNode().getNodeName().equals(Constants.SNAPSHOT_FORM_FORM)) {
			if (node.getParentNode().getAttributes() == null
					|| node.getParentNode().getAttributes().getNamedItem(Constants.VALUE_TITLE) == null) {
				node = node.getParentNode();
				continue;
			} else if (node.getParentNode().getNodeName().equals(Constants.SNAPSHOT_FORM_CONTROL)
					&& !node.getParentNode().getAttributes().getNamedItem(Constants.VALUE_TITLE).getNodeValue()
							.equals(Constants.VALUE_UNKNOWN)
					&& !node.getParentNode().getAttributes().getNamedItem(Constants.VALUE_TITLE).getNodeValue()
							.equals(Constants.VALUE_TABBEDPANE)) {
				// As you go from top to bottom, the title values for the Control elements are
				// listed in order unless
				// they are "unknown".
				pathString = node.getParentNode().getAttributes().getNamedItem(Constants.VALUE_TITLE).getNodeValue()
						+ "\\" + pathString;
			}
			node = node.getParentNode();
		}
		return pathString;
	}

	/**
	 * Returns the formName and mode information from the given snapshot.
	 *
	 * @param snapshot
	 * @return
	 * @throws XPathExpressionException
	 * @author Ozgur.Ozbil
	 */
	public static FormName getFormNameAndMode(Element snapshot) {
		Node node = getNodeFromSnapshot(snapshot, Constants.XPATH_CONTROL);
		Node rootNode = node.getParentNode();
		String formName = rootNode.getAttributes().getNamedItem(Constants.SNAPSHOT_FORM_NAME).getNodeValue()
				.replaceAll(Constants.FILE_EXTENSION_JFM, "");
		Map<String, String> decomposedFormName = TedamStringUtils.decomposeFormName(formName);

		return new FormName(decomposedFormName.get(Constants.VALUE_NAME), decomposedFormName.get(Constants.VALUE_MODE));

	}

	/**
	 * Control definitions that conform to the standards in the given nodes
	 * parameter are converted to SnapshotValue objects and returned in a list.
	 *
	 * @param nodes
	 * @return
	 * @author Ozgur.Ozbil
	 */
	public static List<SnapshotValue> getControlValuesFromFile(NodeList nodes) {

		List<SnapshotValue> tempSSValues = new ArrayList<SnapshotValue>();

		for (int i = 0; i < nodes.getLength(); i++) {
			String tempType = nodes.item(i).getAttributes().getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TYPE)
					.getNodeValue();
			if (unpermittedComponentList.contains(tempType) || nodes.item(i).hasChildNodes()) {
				continue;
			}

			NamedNodeMap namedNodeMap = nodes.item(i).getAttributes();

			boolean tmpHasLookUp = namedNodeMap.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_HAS_LOOKUP)
					.getNodeValue().equalsIgnoreCase(Constants.VALUE_TRUE) ? true : false;
			String value;
			// For elements with type 106, value_tag attribute is used for gathering value
			if (namedNodeMap.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TYPE).getNodeValue()
					.equals(Constants.COMBO_CONTROLTYPE_COMBOBOX)
					&& namedNodeMap.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_VALUETAG) != null) {
				value = namedNodeMap.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_VALUETAG).getNodeValue();
			} else if (SnapshotControls.CHECKBOXGROUP.getType()
					.equals(namedNodeMap.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TYPE).getNodeValue())) {
				// For elements with type 151 (SnapshotControls), selectedlist attribute is
				// parsed and value is constitutd with putting "/" between
				// resulting values
				if (namedNodeMap.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_SELECTEDLIST) != null) {
					if (namedNodeMap.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_SELECTEDLIST).getNodeValue()
							.isEmpty()) {
						continue;
					}
					List<Integer> itemList = TedamStringUtils.collectResourceItemList(namedNodeMap
							.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_SELECTEDLIST).getNodeValue());
					value = "";
					value = itemListLoop(value, itemList);
					value = value.substring(1);
				} else {
					value = "";
				}
			} else {
				// Otherwise just the value attribute is used
				value = namedNodeMap.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_VALUE).getNodeValue();
			}

			SnapshotValue snapshotValue = new SnapshotValue(
					namedNodeMap.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TITLE).getNodeValue(),
					namedNodeMap.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TAG).getNodeValue(), value,
					"TEMP_VERSION", true, -1,
					namedNodeMap.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TYPE).getNodeValue());
			snapshotValue.setParentTag(Constants.VALUE_NULL);
			snapshotValue.setLookUp(tmpHasLookUp);
			snapshotValue.setOrder(Constants.VALUE_NULL_INTEGER * -1);
			snapshotValue.setDialogParameter(Constants.VALUE_NULL);
			snapshotValue.setContinueOnError(true);
			snapshotValue.setSnapshotDefinitionId(null);
			tempSSValues.add(snapshotValue);
		}
		return tempSSValues;
	}

	private static String itemListLoop(String value, List<Integer> itemList) {
		for (int j = 0; j < itemList.size(); j++) {
			value += "/" + itemList.get(j);
		}
		return value;
	}

	/**
	 * The information obtained from the definitions of Row and Column (DataGrid
	 * children) that conforms to the standards in the given nodes parameter is
	 * converted into SnapshotValue objects and returned in a list. The value
	 * property of the SnapshotValue object is the information from the Row element;
	 * ParentTag property with information from the DataGrid element; all remaining
	 * information is filled with information from the Column element.
	 *
	 * @param nodes
	 * @return
	 * @author Ozgur.Ozbil
	 */
	public static List<SnapshotValue> getGridContentsFromFile(NodeList nodes) {

		ArrayList<SnapshotValue> returnValueList = new ArrayList<SnapshotValue>();
		// Returns for dataGrid elements.
		for (int t = 0; t < nodes.getLength(); t++) {
			ArrayList<SnapshotValue> incomingValueList = new ArrayList<SnapshotValue>();
			// GridTag is fetched.
			String parentTag = nodes.item(t).getAttributes().getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TAG)
					.getNodeValue();

			// Row and Column information is retrieved.
			checkForDummyNode(nodes, t, incomingValueList, parentTag);
			returnValueList.addAll(incomingValueList);
		}
		return returnValueList;
	}

	private static void checkForDummyNode(NodeList nodes, int t, ArrayList<SnapshotValue> incomingValueList,
			String parentTag) {
		NodeList rows;
		NodeList columns;
		if (TedamDOMUtils.isDummyNode(nodes.item(t).getChildNodes().item(0))) {
			rows = nodes.item(t).getChildNodes().item(3).getChildNodes();
			columns = nodes.item(t).getChildNodes().item(1).getChildNodes();
		} else {
			rows = nodes.item(t).getChildNodes().item(1).getChildNodes();
			columns = nodes.item(t).getChildNodes().item(0).getChildNodes();
		}
		rowsLoop(incomingValueList, parentTag, rows, columns);
	}

	private static void rowsLoop(ArrayList<SnapshotValue> incomingValueList, String parentTag, NodeList rows,
			NodeList columns) {
		// Keeps rowIndex value.
		int counter = 0;
		// Returns for the Row elements.
		for (int i = 0; i < rows.getLength(); i++) {
			if (TedamDOMUtils.isDummyNode(rows.item(i))) {
				continue;
			}
			Node columnDefinition = null;
			// It returns for the attribute of the row element in our hand.
			columnDefinition = checkForAttributes(incomingValueList, parentTag, rows, columns, counter, i,
					columnDefinition);
			counter++;
		}
	}

	private static Node checkForAttributes(ArrayList<SnapshotValue> incomingValueList, String parentTag, NodeList rows,
			NodeList columns, int counter, int i, Node columnDefinition) {
		for (int j = 0; j < rows.item(i).getAttributes().getLength() - 1; j++) {
			if (rows.item(i).getAttributes().item(j).getNodeValue().isEmpty()) {
				continue;
			}
			// If row attribute has "_tag" text within, its value must replace for the cells
			// value attribute defined without the "_tag" text
			if (rows.item(i).getAttributes().item(j).getNodeName()
					.contains(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TAG)) {
				continue;
			}
			// The column tag is drawn in the name of the attribute that you have on the row
			// definition.
			columnDefinition = getColumnByTag(columns, rows.item(i).getAttributes().item(j).getNodeName().substring(3));
			// Column Node identification with a colon tag is taken.

			checkForColumnDefinition(incomingValueList, parentTag, rows, counter, i, columnDefinition, j);

		}
		return columnDefinition;
	}

	private static void checkForColumnDefinition(ArrayList<SnapshotValue> incomingValueList, String parentTag,
			NodeList rows, int counter, int i, Node columnDefinition, int j) {
		if (columnDefinition != null) {
			SnapshotValue tempSSValue = fillAndGetFormFieldsWithAttributes(columnDefinition.getAttributes());
			tempSSValue.setParentTag(parentTag);
			tempSSValue.setRowIndex(counter);
			tempSSValue.setValue(rows.item(i).getAttributes().item(j).getNodeValue());
			tempSSValue.setCaption(columnDefinition.getAttributes()
					.getNamedItem(Constants.SNAPSHOT_GRID_COLUMN_ATTRIBUTES_CAPTION).getNodeValue());
			incomingValueList.add(tempSSValue);
		}
	}

	/**
	 * @param nodes
	 * @param tag
	 * @return
	 * @author Tarik.Mikyas
	 */
	public static Node getColumnByTag(NodeList nodes, String tag) {
		for (int i = 0; i < nodes.getLength(); i++) {
			if (TedamDOMUtils.isDummyNode(nodes.item(i))) {
				continue;
			}
			if (nodes.item(i).getAttributes().getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TAG).getNodeValue()
					.equals(tag)) {
				return nodes.item(i);
			}
		}
		return null;
	}

	/**
	 * @param tempAttributes
	 * @return
	 * @author Tarik.Mikyas
	 */
	public static SnapshotValue fillAndGetFormFieldsWithAttributes(NamedNodeMap tempAttributes) {

		SnapshotValue tempSSValue = new SnapshotValue();

		tempSSValue.setTag(tempAttributes.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TAG).getNodeValue());
		tempSSValue.setType(tempAttributes.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TYPE).getNodeValue());
		tempSSValue.setStatus(true);
		tempSSValue.setLookUp(tempAttributes.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_HAS_LOOKUP)
				.getNodeValue().equalsIgnoreCase(Constants.VALUE_TRUE) ? true : false);

		return tempSSValue;
	}

	/**
	 * Filter definitions that conform to the standards in the given nodes parameter
	 * are converted to SnapshotValue objects and returned in a list.
	 *
	 * @param nodes
	 * @return
	 * @author Ozgur.Ozbil
	 */
	public static List<SnapshotValue> getFilterContentsFromFile(NodeList nodes) {
		NodeList rows;
		List<SnapshotValue> snapshotValues = new ArrayList<SnapshotValue>();

		for (int t = 0; t < nodes.getLength(); t++) {

			rows = checkForFilterContentsFromFile(nodes, t);
			String parentTag = nodes.item(t).getAttributes().getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TAG)
					.getNodeValue();

			for (int i = 0; i < rows.getLength(); i++) {
				if (TedamDOMUtils.isDummyNode(rows.item(i))) {
					continue;
				}
				// The SnapshotValue information will be populated using the Filter node's
				// attribute values.
				SnapshotValue snapshotValue = new SnapshotValue();
				snapshotValue.setParentTag(parentTag);
				snapshotValue.setTag(rows.item(i).getAttributes().getNamedItem(Constants.SNAPSHOT_FILTER_ATTRIBUTES_ID)
						.getNodeValue());
				snapshotValue.setType(rows.item(i).getAttributes()
						.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TYPE).getNodeValue());
				snapshotValue.setVersion("TEMP_VERSION");
				snapshotValue.setLookUp(Boolean.getBoolean(rows.item(i).getAttributes()
						.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_HAS_LOOKUP).getNodeValue()));
				snapshotValue.setRowIndex(-3);
				snapshotValue.setStatus(true);

				// The value of the SnapshotValue object will be formatted according to the type
				// of Filter node you have.
				String value = "";
				switch (rows.item(i).getAttributes().getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TYPE)
						.getNodeValue()) {
				case "STRINGRANGE":
				case "TIMERANGE":
				case "DATERANGE":
				case "NUMERICRANGE":
					value = checkForRange(rows, i, value);
					// If there is any excluded value, it must be added at the end of the value with
					// regex "<$!>"
					value = checkForRangeAttributes(rows, i, value);
					break;
				case "SELECTION":
					value = checkForSelection(rows, i, value);
					break;
				case "GROUP":
					// If it is a group type, the valueList in the Filter element is parse and the
					// value
					// is generated with the tag values. Value form will be 1/2/5/76.
					value = checkForGroup(rows, i);
					break;
				case "STRING":
				case "TIME":
				case "DATE":
				case "NUMERIC":
					value = checkForNonRange(rows, i);
					break;
				default:
					break;

				}
				if (value.isEmpty()) {
					continue;
				}
				snapshotValue.setCaption(rows.item(i).getAttributes()
						.getNamedItem(Constants.SNAPSHOT_FILTER_ATTRIBUTES_DESCRIPTION).getNodeValue());
				snapshotValue.setValue(value);
				snapshotValues.add(snapshotValue);
			}
		}
		return snapshotValues;
	}

	private static String checkForNonRange(NodeList rows, int i) {
		String value;
		value = Constants.VALUE_REGEX_GROUPPED
				+ rows.item(i).getAttributes().getNamedItem("value").getNodeValue();
		if (rows.item(i).getAttributes()
				.getNamedItem(Constants.SNAPSHOT_FILTER_ATTRIBUTES_EXCLUDEDVALUE) != null
				&& !rows.item(i).getAttributes()
						.getNamedItem(Constants.SNAPSHOT_FILTER_ATTRIBUTES_EXCLUDEDVALUE).getNodeValue()
						.trim().isEmpty()
				&& (!value.isEmpty())) {
			// If there is a value in the ExcludedValue field, it is appended to the value.
			value += "<$!>" + rows.item(i).getAttributes()
					.getNamedItem(Constants.SNAPSHOT_FILTER_ATTRIBUTES_EXCLUDEDVALUE).getNodeValue();
		}
		return value;
	}

	private static String checkForGroup(NodeList rows, int i) {
		String value;
		List<Integer> valueList = TedamStringUtils.collectResourceItemList(
				rows.item(i).getAttributes().getNamedItem("valueList").getNodeValue());
		value = "";
		if (valueList != null && valueList.isEmpty()) {
			value = "";
		}
		StringBuilder sb = new StringBuilder(value);
		if (valueList != null) {
			for (int j = 0; j < valueList.size(); j++) {
				if (j == 0) {
					sb.append(Integer.toString(valueList.get(j)));
				} else {
					sb.append("/" + Integer.toString(valueList.get(j)));
				}
			}
		}
		value = sb.toString();
		return value;
	}

	private static String checkForSelection(NodeList rows, int i, String value) {
		try {
			value = Integer
					.toString(TedamStringUtils
							.collectResourceItemList(
									rows.item(i).getAttributes().getNamedItem("valueList").getNodeValue())
							.get(0));
		} catch (IndexOutOfBoundsException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return value;
	}

	private static String checkForRangeAttributes(NodeList rows, int i, String value) {
		if ((rows.item(i).getAttributes()
				.getNamedItem(Constants.SNAPSHOT_FILTER_ATTRIBUTES_EXCLUDEDVALUE) != null)
				&& (rows.item(i).getAttributes()
						.getNamedItem(Constants.SNAPSHOT_FILTER_ATTRIBUTES_EXCLUDEDVALUE)
						.getNodeValue() != null)
				&& (!rows.item(i).getAttributes()
						.getNamedItem(Constants.SNAPSHOT_FILTER_ATTRIBUTES_EXCLUDEDVALUE).getNodeValue()
						.trim().isEmpty())) {
			value += "<$!>" + rows.item(i).getAttributes()
					.getNamedItem(Constants.SNAPSHOT_FILTER_ATTRIBUTES_EXCLUDEDVALUE).getNodeValue();
		}
		return value;
	}

	private static String checkForRange(NodeList rows, int i, String value) {
		if (rows.item(i).getAttributes()
				.getNamedItem(Constants.SNAPSHOT_FILTER_ATTRIBUTES_GROUPVALUE) == null) {
			if (rows.item(i).getAttributes()
					.getNamedItem(Constants.SNAPSHOT_FILTER_ATTRIBUTES_HIGHVALUE) == null
					|| rows.item(i).getAttributes()
							.getNamedItem(Constants.SNAPSHOT_FILTER_ATTRIBUTES_LOWVALUE) == null) {
				value = "";
			} else {
				if (!rows.item(i).getAttributes()
						.getNamedItem(Constants.SNAPSHOT_FILTER_ATTRIBUTES_LOWVALUE).getNodeValue().trim()
						.isEmpty()) {
					value += "<$gt>" + rows.item(i).getAttributes()
							.getNamedItem(Constants.SNAPSHOT_FILTER_ATTRIBUTES_LOWVALUE).getNodeValue();
				}
				if (!rows.item(i).getAttributes()
						.getNamedItem(Constants.SNAPSHOT_FILTER_ATTRIBUTES_HIGHVALUE).getNodeValue().trim()
						.isEmpty()) {
					value += "<$lt>" + rows.item(i).getAttributes()
							.getNamedItem(Constants.SNAPSHOT_FILTER_ATTRIBUTES_HIGHVALUE).getNodeValue();
				}
			}
		} else {
			// If filter is grouped, there would not be any high or low value. Value must be
			// like <$gr>
			if (!rows.item(i).getAttributes().getNamedItem(Constants.SNAPSHOT_FILTER_ATTRIBUTES_GROUPVALUE)
					.getNodeValue().trim().isEmpty()) {
				value += "<$gr>" + rows.item(i).getAttributes()
						.getNamedItem(Constants.SNAPSHOT_FILTER_ATTRIBUTES_GROUPVALUE).getNodeValue();
			}
		}
		return value;
	}

	private static NodeList checkForFilterContentsFromFile(NodeList nodes, int t) {
		NodeList rows;
		if (TedamDOMUtils.isDummyNode(nodes.item(t).getChildNodes().item(0))) {
			rows = nodes.item(t).getChildNodes().item(1).getChildNodes();
		} else {
			rows = nodes.item(t).getChildNodes().item(0).getChildNodes();
		}
		return rows;
	}

	/**
	 * @param reportFileName
	 * @return <br>
	 *         This method extracts the report name from the file with the .igv
	 *         extension and returns it as the result.
	 * @author Tarik.Mikyas
	 */
	public static String getReportNameFromFile(String reportFilePath) {
		String nodeValue;
		String directory = reportFilePath + Constants.FILE_EXTENSION_IGV;
		TedamLogUtils.log(METHOD_NAME, "getReportNameFromFile(" + reportFilePath + ")", TedamLogLevel.INFO,
				Boolean.TRUE);
		Document document = TedamDOMUtils.domParserStarter(directory);
		TedamLogUtils.log(METHOD_NAME, "directory : " + directory, TedamLogLevel.INFO, Boolean.TRUE);
		Node node = getNodeFromDocument(document, Constants.XPATH_REPORT);
		if (node == null) {
			LOGGER.error(
					"File name could not be obtained from file with .igv extension. getNodeFromDocument () procedure returned null!");
			TedamLogUtils.log(METHOD_NAME,
					".igv uzantili dosyadan Dosya ismi elde edilemedi. getNodeFromDocument() yordami null dondurdu!!!",
					TedamLogLevel.INFO, Boolean.TRUE);
			return null;
		}
		nodeValue = node.getAttributes().getNamedItem("name").getNodeValue();
		TedamLogUtils.log(METHOD_NAME, "nodeValue : " + nodeValue, TedamLogLevel.INFO, Boolean.TRUE);
		return nodeValue;
	}
}
