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

package com.lbs.tedam.data.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import com.lbs.tedam.data.dao.FormFieldDAO;
import com.lbs.tedam.data.service.FormFieldService;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.FormDefinition;
import com.lbs.tedam.model.FormField;
import com.lbs.tedam.model.Snapshot;
import com.lbs.tedam.snapshot.utils.SnapshotFieldParser;
import com.lbs.tedam.util.Constants;
import com.lbs.tedam.util.TedamDOMUtils;

import javassist.NotFoundException;

@Service
public class FormFieldServiceImpl extends BaseServiceImpl<FormField, Integer> implements FormFieldService {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private FormFieldDAO dao;

	@Autowired
	public void setDao(FormFieldDAO dao) {
		this.dao = dao;
		super.setBaseDao(dao);
	}

	@Override
	public List<FormField> getFormFieldListByVersionAndFormDefId(String version, int formId) throws LocalizedException {
		List<FormField> formFieldList = dao.getFormFieldListByVersionAndFormDefId(version, formId);
		return formFieldList;
	}

	@Override
	public List<FormField> getFieldsOfVersionsAndForms(String version, int formId, boolean status) throws LocalizedException {
		List<FormField> allFieldList = dao.findByFormDefinitionIdAndStatus(formId, status);
		String[][] formFieldList = dao.getFieldsOfVersionsAndForms(version, formId);
		List<FormField> refinedList = createRefinedListForFields(allFieldList, formFieldList);

		return refinedList;
	}

	private List<FormField> createRefinedListForFields(List<FormField> allFieldList, String[][] formFieldList) {
		return createRefinedList(allFieldList, formFieldList);
	}

	@Override
	public List<FormField> getHistoryOfTag(int id) throws LocalizedException {
		FormField formField = dao.getById(id);
		int formDefinitionId = formField.getFormDefinitionId();
		String tag = formField.getTag();
		List<FormField> formFieldList = dao.getHistoryOfTag(formDefinitionId, tag);
		return formFieldList;
	}

	@Override
	public List<FormField> getControlFieldsOfVersionsAndForms(String version, int formId) throws LocalizedException {
		List<FormField> allFieldList = dao.findByFormDefinitionId(formId);
		String[][] formFieldList = dao.getControlFieldsOfVersionsAndForms(version, formId);

		List<FormField> refinedList = createRefinedList(allFieldList, formFieldList);

		return refinedList;
	}

	private List<FormField> createRefinedList(List<FormField> allFieldList, String[][] formFieldList) {
		List<FormField> refinedList = new ArrayList<>();
		for (FormField field : allFieldList) {
			for (int i = 0; i < formFieldList.length; i++) {
				String[] innerArray = formFieldList[i];
				if (field.getTag().equalsIgnoreCase(innerArray[0]) && field.getVersion().equalsIgnoreCase(innerArray[1]))
					refinedList.add(field);
			}
		}
		return refinedList;
	}

	@Override
	public String getCaptionBySnapshotValue(Integer snapshotValueId, String tag) throws LocalizedException {
		String caption = dao.getCaptionBySnapshotValue(snapshotValueId, tag);
		return caption;
	}

	/**
	 * This method handles the submitted SnapShot as a parameter to the FormDefinition, FormField and Snapshot tables in the database.<br>
	 * If the FormDefinition that the parameters are sent to is in the FormDefinition tab before the snapshotCollector works (existenceCond = true): The difference between the
	 * fields in the FormField table and the fields in the snapshot are added. If there is a difference, the xml doc is added to the Snapshot table.<br>
	 * If it is found in the FormDefinition table (existenceCond = false): Form efinition, FormField and Snapshot are added completely.
	 * <p>
	 * <p>
	 * oldname: saveSnapshotFields
	 *
	 * @param xmlDoc
	 * @param version
	 * @param formDefinitionId
	 * @param existenceCond
	 * @throws SQLException
	 * @throws XPathExpressionException
	 * @throws LocalizedException
	 * @throws NotFoundException
	 * @throws TransformerException
	 */
	@Override
	public List<FormField> prepareFormFields(Element xmlDoc, FormDefinition formDefinition, boolean existenceCond, String version)
			throws XPathExpressionException, LocalizedException {
		List<FormField> incomingFieldList = new ArrayList<FormField>();
		XPathExpression tagExpr = XPathFactory.newInstance().newXPath().compile("//Control[not(contains(@type,'154'))]");
		NodeList nodes = (NodeList) tagExpr.evaluate(xmlDoc, XPathConstants.NODESET);
		if (nodes != null && nodes.getLength() > 0) {
			incomingFieldList = createOrUpdateFormDefinition(xmlDoc, formDefinition, existenceCond, version, nodes);
		}
		return incomingFieldList;
	}

	private List<FormField> createOrUpdateFormDefinition(Element xmlDoc, FormDefinition formDefinition, boolean existenceCond, String version, NodeList nodes)
			throws LocalizedException {
		List<FormField> incomingFieldList = new ArrayList<FormField>();
		if (!existenceCond) {
			// If the FormDefinition record is newly added to the database (if it is a new screen)
			addFieldsToDefinition(nodes, formDefinition, Constants.VERSION);
		} else {
			// If the previous record is being updated.
			incomingFieldList.addAll(getIncomingControlFieldList(formDefinition, nodes));

			List<FormField> formFields = getControlFieldsOfVersionsAndForms(version, formDefinition.getId());
			List<FormField> result = SnapshotFieldParser.checkFields(formFields, incomingFieldList, version);
			if (!result.isEmpty()) {
				formDefinition.addAllFormFields(result);
				// If there is a record to be updated.

				// If an update is made, the current snapshot must be added to the snapshot set linked to the formDefinition in the list.
				// To do this, the existing snapshot contains the latest version of the snapshot; Cloned; version and xml information will
				// be updated and added to the list to be added.
				// TODO: mikyas Here we can find formDefination.getSnapshotList and snapshot list of formdef object.
				List<Snapshot> snapshotList = formDefinition.getSnapshots();
				String maxVersion = "";
				Snapshot targetSnapshot = null;
				for (Snapshot snapshot : snapshotList) {
					if (snapshot.getVersion().compareTo(maxVersion) > 0) {
						targetSnapshot = snapshot.cloneSnapshot();
					}
				}
				if (targetSnapshot != null) {
					targetSnapshot.setXml(TedamDOMUtils.elementToString(xmlDoc));
					targetSnapshot.setVersion(version);
					formDefinition.getSnapshots().add(targetSnapshot);
					snapshotList.clear();
					snapshotList.add(targetSnapshot);
				}
			}
		}
		return incomingFieldList;
	}

	/**
	 * Converts the values of the Control values in the given NodeList to the FormField objects and inserts them into the formFields list of the given formDefinition.
	 * <p>
	 * oldname: saveControlValuesToDB
	 *
	 * @param version
	 * @param nodes
	 * @param formID
	 * @throws SQLException
	 */
	private void addFieldsToDefinition(NodeList nodes, FormDefinition formDefinition, String version) {
		for (int i = 0; i < nodes.getLength(); i++) {
			String tempType = nodes.item(i).getAttributes().getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TYPE).getNodeValue();
			// ms_UnpermittedComponentList array ignore type control in array.
			if (Constants.getUnpermittedcomponentlist().contains(tempType)) {
				continue;
			}

			// Do not care if it is not Leaf
			if (nodes.item(i).hasChildNodes()) {
				continue;
			}

			if (nodes.item(i).getAttributes() == null || nodes.item(i).getAttributes().getLength() == 0) {
				continue;
			}
			FormField formField = new FormField();
			// The formField object is populated with the node attributes in our hand.
			formField.setTag(nodes.item(i).getAttributes().getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TAG).getNodeValue());
			formField.setType(nodes.item(i).getAttributes().getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TYPE).getNodeValue());
			formField.setCaption(nodes.item(i).getAttributes().getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TITLE).getNodeValue());
			formField.setVersion(version);
			formField.setStatus(true);
			formField.setMandatory(
					nodes.item(i).getAttributes().getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_MANDATORY).getNodeValue().equalsIgnoreCase(Constants.VALUE_TRUE) ? true
							: false);

			formField.setLookUp(
					nodes.item(i).getAttributes().getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_HAS_LOOKUP).getNodeValue().equalsIgnoreCase(Constants.VALUE_TRUE) ? true
							: false);

			if (nodes.item(i).getAttributes().getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_ATTRIBUTE) != null) {
				formField.setAttribute(nodes.item(i).getAttributes().getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_ATTRIBUTE).getNodeValue());
			}

			formDefinition.addFormField(formField);
		}
	}

	/**
	 * Prepares the control elements in the given nodeList in the FormField object format, and returns them.
	 *
	 * @param formDefinition
	 * @param nodes
	 * @param version
	 * @param operationType
	 * @return
	 */
	private List<FormField> getIncomingControlFieldList(FormDefinition formDefinition, NodeList nodes) {
		List<FormField> incomingFieldList = new ArrayList<FormField>();
		for (int i = 0; i < nodes.getLength(); i++) {
			String tempType = nodes.item(i).getAttributes().getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TYPE).getNodeValue();
			// Fields written in the ms_UnpermittedComponentList array should be ignored.
			// Do not care if it is not Leaf.
			if (Constants.getUnpermittedcomponentlist().contains(tempType) || nodes.item(i).hasChildNodes()) {
				continue;
			}
			incomingFieldList.add(fillAndGetFormFieldsWithAttributes(formDefinition, nodes.item(i).getAttributes()));
		}
		return incomingFieldList;
	}

	private FormField fillAndGetFormFieldsWithAttributes(FormDefinition formDefinition, NamedNodeMap tempAttributes) {
		// It only receives control tagged elements from within the snapshot.
		FormField formField = new FormField();
		formField.setType(tempAttributes.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TYPE).getNodeValue());
		formField.setVersion(null);
		formField.setStatus(true);
		formField.setCaption(tempAttributes.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TITLE).getNodeValue());
		formField.setParentTag(Constants.VALUE_NULL);
		formField.setTag(tempAttributes.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_TAG).getNodeValue());
		formField.setMandatory(tempAttributes.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_MANDATORY).getNodeValue().equalsIgnoreCase(Constants.VALUE_TRUE) ? true : false);
		formField.setLookUp(tempAttributes.getNamedItem(Constants.SNAPSHOT_CONTROL_ATTRIBUTES_HAS_LOOKUP).getNodeValue().equalsIgnoreCase(Constants.VALUE_TRUE) ? true : false);

		formDefinition.addFormField(formField);

		return formField;
	}

}
