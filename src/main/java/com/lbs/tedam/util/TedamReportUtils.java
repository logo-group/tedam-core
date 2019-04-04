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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch;
import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch.Diff;
import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lbs.tedam.model.TestReport;
import com.lbs.tedam.util.Enums.StatusMessages;
import com.lbs.tedam.util.Enums.TedamLogLevel;

public class TedamReportUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(TedamReportUtils.class);
    private final String TIME24HOURS_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";
    private final String DATE_PATTERN = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";
    private TedamLogLevel logLevelInfo = TedamLogLevel.INFO;
    private TedamLogLevel logLevelError = TedamLogLevel.ERROR;
    private Boolean printLog = Boolean.TRUE;
    private MemoryUsageSetting memoryUsageSetting = MemoryUsageSetting.setupTempFileOnly();
    private PDFTextStripper stripper = null;

    public TestReport compareTwoPDFFile(File pdfSourceFile, File pdfTargetFile) {
        String headerCompareTwoPDFFile = "compareTwoPDFFile";
        String pdfTargetFormName = getFormName(pdfTargetFile.getPath());
        List<TestReport> reportList = new ArrayList<>();
        try {
            stripper = new PDFTextStripper();
            PDDocument sourceDocument = PDDocument.load(pdfSourceFile, memoryUsageSetting);
            PDDocument targetDocument = PDDocument.load(pdfTargetFile, memoryUsageSetting);
            if (sourceDocument.getPages().getCount() != targetDocument.getPages().getCount()) {
                TedamLogUtils.log(headerCompareTwoPDFFile,
                        "SourceDocument # of pages : " + sourceDocument.getPages().getCount() + " TargetDocument # of pages : " + targetDocument.getPages().getCount(),
                        logLevelError, printLog);
                TestReport testReport = createTestReport("", pdfTargetFormName,
                        "SourceDocument # of pages : " + sourceDocument.getPages().getCount() + " TargetDocument # of pages : " + targetDocument.getPages().getCount(),
                        StatusMessages.FAILED.getStatus());
                return testReport;
            }

            loopOnSourceDocument(headerCompareTwoPDFFile, pdfTargetFormName, reportList, sourceDocument,
					targetDocument);
            sourceDocument.getDocument().close();
            targetDocument.getDocument().close();
            sourceDocument.close();
            targetDocument.close();
            return buildReport(pdfTargetFormName, reportList);
        } catch (IOException e) {
            LOGGER.error("" + e);
            return buildReport(pdfTargetFormName, Constants.EMPTY_STRING, e.getMessage(), StatusMessages.FAILED.getStatus());
        }
    }

	private void loopOnSourceDocument(String headerCompareTwoPDFFile, String pdfTargetFormName,
			List<TestReport> reportList, PDDocument sourceDocument, PDDocument targetDocument) throws IOException {
		for (int i = 0; i < sourceDocument.getPages().getCount(); i++) {
		    StringBuilder sourceStringBuilderPage = getPDFTextStripperTextWithReplaceDateAndTime(sourceDocument, i + 1);
		    StringBuilder targetStringBuilderPage = getPDFTextStripperTextWithReplaceDateAndTime(targetDocument, i + 1);

		    String[] sourceLines = sourceStringBuilderPage.toString().split("\\n");
		    String[] targetLines = targetStringBuilderPage.toString().split("\\n");

		    loopOnSourceLines(headerCompareTwoPDFFile, pdfTargetFormName, reportList, i, sourceLines, targetLines);
		}
	}

	private TestReport buildReport(String pdfTargetFormName, List<TestReport> reportList) {
		if (!reportList.isEmpty()) {
		    return buildReport(pdfTargetFormName, Constants.EMPTY_STRING, buildMessage(reportList), StatusMessages.FAILED.getStatus());
		} else {
		    return buildReport(pdfTargetFormName, Constants.EMPTY_STRING, Constants.EMPTY_STRING, StatusMessages.SUCCEEDED.getStatus());
		}
	}

	private void loopOnSourceLines(String headerCompareTwoPDFFile, String pdfTargetFormName,
			List<TestReport> reportList, int i, String[] sourceLines, String[] targetLines) {
		if (sourceLines.length != targetLines.length) {
			TestReport testReport = createTestReport(
					"", pdfTargetFormName, "Different line count in page " + (i + 1) + " -> SourceLines # : "
							+ sourceLines.length + " TargetLines # : " + targetLines.length,
					StatusMessages.FAILED.getStatus());
			reportList.add(testReport);
		} else {

			for (int j = 0; j < sourceLines.length; j++) {
				LinkedList<Diff> diffList = new DiffMatchPatch().diffMain(sourceLines[j], targetLines[j]);
				if (diffList.size() > 0) {
					loopOnDiff(headerCompareTwoPDFFile, pdfTargetFormName, reportList, i, j, diffList);

				}
			}
		}
	}

	private void loopOnDiff(String headerCompareTwoPDFFile, String pdfTargetFormName, List<TestReport> reportList,
			int i, int j, LinkedList<Diff> diffList) {
		for (int k = 0; k < diffList.size(); k++) {
		    Diff diff = diffList.get(k);
		    if (!Operation.EQUAL.equals(diff.operation) && !TedamStringUtils.isInteger(diff.text) && !diff.text.trim().isEmpty()) {
		        TedamLogUtils.log(headerCompareTwoPDFFile, diff.operation + " " + diff.text, logLevelInfo, printLog);
		        TestReport testReport = createTestReport("Page: " + (i + 1) + ", Line number: " + j, pdfTargetFormName,
		                String.valueOf(diff.operation).toLowerCase() + ": '" + diff.text + "'", StatusMessages.FAILED.getStatus());
		        reportList.add(testReport);
		    }
		}
	}

    private TestReport buildReport(String formName, String stepName, String message, String statusMessage) {
        TestReport report = new TestReport();
        report.setStepName(stepName);
        report.setFormName(formName);
        report.addMessage(message);
        report.setStatusMsg(statusMessage);
        return report;
    }

    private String buildMessage(List<TestReport> reportList) {
        StringBuilder sb = new StringBuilder();
        for (TestReport testReport : reportList) {
            sb.append(testReport.getStepName());
            sb.append(", ");
            sb.append(testReport.getMessage());
            sb.append(". ");
        }
        return sb.toString();
    }

    private StringBuilder getPDFTextStripperTextWithReplaceDateAndTime(PDDocument document, int page) throws IOException {
        stripper.setStartPage(page);
        stripper.setEndPage(page);
        String text = stripper.getText(document).replaceAll(TIME24HOURS_PATTERN, Constants.EMPTY_STRING).replaceAll(DATE_PATTERN, Constants.EMPTY_STRING);
        StringBuilder stringBuilder = new StringBuilder(text);
        return stringBuilder;
    }

    private TestReport createTestReport(String stepName, String formName, String message, String status) {
        TestReport testReport = new TestReport(stepName, formName);
        testReport.addMessage(message);
        testReport.setStatusMsg(status);
        return testReport;
    }

    private String getFormName(String filePath) {
        return filePath.substring(filePath.lastIndexOf('\\') + 1, filePath.indexOf(Constants.FILE_EXTENSION_PDF));
    }
}
