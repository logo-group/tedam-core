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

package com.lbs.tedam.report;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lbs.tedam.exception.CreateNewFileException;
import com.lbs.tedam.model.JobReport;
import com.lbs.tedam.model.JobReportResult;
import com.lbs.tedam.model.DTO.JobXMLFailureReportDTO;
import com.lbs.tedam.model.DTO.JobXMLTestCaseReportDTO;
import com.lbs.tedam.model.DTO.JobXMLTestSuiteReportDTO;
import com.lbs.tedam.model.DTO.JobXMLTestSuitesReportDTO;
import com.lbs.tedam.util.Constants;
import com.lbs.tedam.util.EnumsV2.ExecutionStatus;
import com.lbs.tedam.util.TedamFileUtils;

public class JobXMLReportService extends JobReportServiceImpl {
	private static final Logger LOGGER = LoggerFactory.getLogger(JobXMLReportService.class);

	@Override
	public void createJobReportFile(int jobId, String jobReportFilePath) {
		Map<JobReport, JobReportResult> reportMap = buildReportMap(jobId);
		JobXMLTestSuitesReportDTO jobXMLTestSuitesReportModel = buildJobXMLTestSuitesReportModel(jobId, reportMap);
		createXMLReportFile(jobXMLTestSuitesReportModel, jobReportFilePath, jobId);
	}

	private Map<JobReport, JobReportResult> buildReportMap(int jobId) {
		Map<JobReport, JobReportResult> jobReportMap = TedamReportFactory.getJobReportMap();
		Map<JobReport, JobReportResult> reportMap = new HashMap<>();
		for (Iterator<JobReport> iterator = jobReportMap.keySet().iterator(); iterator.hasNext();) {
			JobReport jobReport = iterator.next();
			if (jobReport.getJobId() == jobId) {
				reportMap.put(jobReport, jobReportMap.get(jobReport));
			}
		}
		return reportMap;
	}

	private JobXMLTestSuitesReportDTO buildJobXMLTestSuitesReportModel(int jobId,
			Map<JobReport, JobReportResult> reportMap) {
		JobXMLTestSuitesReportDTO jobXMLTestSuitesReportModel = new JobXMLTestSuitesReportDTO();
		jobXMLTestSuitesReportModel.setJobId(jobId);
		jobXMLTestSuitesReportModel.setJobXMLTestSuiteReportModelList(buildJobXMLTestSuiteReportModel(reportMap));
		return jobXMLTestSuitesReportModel;
	}

	private List<JobXMLTestSuiteReportDTO> buildJobXMLTestSuiteReportModel(Map<JobReport, JobReportResult> reportMap) {
		List<JobXMLTestSuiteReportDTO> jobXMLTestSuiteReportModelList = new ArrayList<>();

		for (Iterator<JobReport> iterator = reportMap.keySet().iterator(); iterator.hasNext();) {
			JobReport jobReport = iterator.next();
			JobXMLTestSuiteReportDTO jobXMLTestSuiteReportModel = getJobXMLTestSuiteReportModel(
					jobReport.getTestSetId(), jobXMLTestSuiteReportModelList);
			if (jobXMLTestSuiteReportModel != null) {
				jobXMLTestSuiteReportModel.getJobXMLTestCaseReportModelList()
						.add(buildJobXMLTestCaseReportModel(jobReport, reportMap.get(jobReport)));
			} else {
				jobXMLTestSuiteReportModel = new JobXMLTestSuiteReportDTO();
				jobXMLTestSuiteReportModel.setTestSetId(jobReport.getTestSetId());
				jobXMLTestSuiteReportModel.setJobXMLTestCaseReportModelList(
						buildJobXMLTestCaseReportModelList(jobReport, reportMap.get(jobReport)));
			}
			jobXMLTestSuiteReportModelList.add(jobXMLTestSuiteReportModel);
		}
		return jobXMLTestSuiteReportModelList;
	}

	private JobXMLTestSuiteReportDTO getJobXMLTestSuiteReportModel(int testSetId,
			List<JobXMLTestSuiteReportDTO> jobXMLTestSuiteReportModelList) {
		for (JobXMLTestSuiteReportDTO jobXMLTestSuiteReportModel : jobXMLTestSuiteReportModelList) {
			if (jobXMLTestSuiteReportModel.getTestSetId() == testSetId) {
				return jobXMLTestSuiteReportModel;
			}
		}
		return null;
	}

	private JobXMLTestCaseReportDTO buildJobXMLTestCaseReportModel(JobReport jobReport,
			JobReportResult jobReportResult) {
		JobXMLTestCaseReportDTO jobXMLTestCaseReportModel = new JobXMLTestCaseReportDTO();
		jobXMLTestCaseReportModel.setTestCaseId(jobReport.getTestCaseId());
		jobXMLTestCaseReportModel.setTestCaseName(jobReport.getTestCaseName());
		if (!jobReportResult.getExecutionStatus().equals(ExecutionStatus.SUCCEEDED)) {
			JobXMLFailureReportDTO jobXMLFailureReportModel = new JobXMLFailureReportDTO();
			jobXMLFailureReportModel.setDescription(jobReportResult.getDescription());
			jobXMLFailureReportModel.setType(jobReportResult.getExecutionStatus().getValue().toLowerCase());
			jobXMLTestCaseReportModel.setJobXMLFailureReportModel(jobXMLFailureReportModel);
		}
		return jobXMLTestCaseReportModel;
	}

	private List<JobXMLTestCaseReportDTO> buildJobXMLTestCaseReportModelList(JobReport jobReport,
			JobReportResult jobReportResult) {
		List<JobXMLTestCaseReportDTO> jobXMLTestCaseReportModelList = new ArrayList<>();
		jobXMLTestCaseReportModelList.add(buildJobXMLTestCaseReportModel(jobReport, jobReportResult));
		return jobXMLTestCaseReportModelList;
	}

	private void createXMLReportFile(JobXMLTestSuitesReportDTO jobXMLTestSuitesReportModel, String jobReportFilePath,
			int jobId) {
		try {

			TedamFileUtils.createNewFilePath(jobReportFilePath);

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			Document doc = createTestSuitesNode(jobXMLTestSuitesReportModel, docBuilder);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			transformerFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(jobReportFilePath + "/Job_" + jobId + "_Test_Result.xml"));
			transformer.transform(source, result);

		} catch (ParserConfigurationException pce) {
			LOGGER.error("" + pce);
		} catch (TransformerException tfe) {
			LOGGER.error("" + tfe);
		} catch (CreateNewFileException e) {
			LOGGER.error("" + e);
		}
	}

	private Document createTestSuitesNode(JobXMLTestSuitesReportDTO jobXMLTestSuitesReportModel,
			DocumentBuilder docBuilder) {
		// root elements
		Document doc = docBuilder.newDocument();
		Element testSuites = doc.createElement(Constants.ELEMENT_TESTSUITES);
		testSuites.setAttribute(Constants.ATTR_ID, String.valueOf(jobXMLTestSuitesReportModel.getJobId()));
		doc.appendChild(testSuites);

		createTestSuiteNode(jobXMLTestSuitesReportModel, doc, testSuites);
		return doc;
	}

	private void createTestSuiteNode(JobXMLTestSuitesReportDTO jobXMLTestSuitesReportModel, Document doc,
			Element testSuites) {
		for (JobXMLTestSuiteReportDTO jobXMLTestSuiteReportModel : jobXMLTestSuitesReportModel
				.getJobXMLTestSuiteReportModelList()) {
			Element testSuite = doc.createElement(Constants.ELEMENT_TESTSUITE);
			testSuites.appendChild(testSuite);
			testSuite.setAttribute(Constants.ATTR_ID, String.valueOf(jobXMLTestSuiteReportModel.getTestSetId()));
			createTestCaseNode(doc, jobXMLTestSuiteReportModel, testSuite);
		}
	}

	private void createTestCaseNode(Document doc, JobXMLTestSuiteReportDTO jobXMLTestSuiteReportModel,
			Element testSuite) {
		for (JobXMLTestCaseReportDTO jobXMLTestCaseReportModel : jobXMLTestSuiteReportModel
				.getJobXMLTestCaseReportModelList()) {
			Element testCase = doc.createElement(Constants.ELEMENT_TESTCASE);
			testCase.setAttribute(Constants.ATTR_ID, String.valueOf(jobXMLTestCaseReportModel.getTestCaseId()));
			testCase.setAttribute(Constants.ATTR_NAME, jobXMLTestCaseReportModel.getTestCaseName());
			testCase.setAttribute(Constants.ATTR_CLASSNAME, Constants.ATTR_CLASSNAME_VALUE);
			createFailureNode(doc, jobXMLTestCaseReportModel, testCase);
			testSuite.appendChild(testCase);
		}
	}

	private void createFailureNode(Document doc, JobXMLTestCaseReportDTO jobXMLTestCaseReportModel, Element testCase) {
		if (jobXMLTestCaseReportModel.getJobXMLFailureReportModel() != null) {
			Element failure = doc.createElement(Constants.ELEMENT_FAILURE);
			testCase.setAttribute(Constants.ATTR_TYPE,
					jobXMLTestCaseReportModel.getJobXMLFailureReportModel().getType());
			failure.appendChild(
					doc.createTextNode(jobXMLTestCaseReportModel.getJobXMLFailureReportModel().getDescription()));
			testCase.appendChild(failure);
		}
	}

}
