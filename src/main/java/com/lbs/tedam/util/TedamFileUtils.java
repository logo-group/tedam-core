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
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import com.lbs.tedam.bsh.utils.ScriptService;
import com.lbs.tedam.exception.CreateNewFileException;
import com.lbs.tedam.model.TedamFile;
import com.lbs.tedam.model.TestReport;
import com.lbs.tedam.model.DTO.LogoTestResult;
import com.lbs.tedam.util.Enums.StatusMessages;
import com.lbs.tedam.util.Enums.TedamLogLevel;
import com.lbs.tedam.util.EnumsV2.ExecutionStatus;

import jxl.Cell;
import jxl.CellView;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class TedamFileUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(TedamFileUtils.class);
	/**
	 * int STEPNAMECOL
	 */
	private static final int STEPNAMECOL = 1;
	/**
	 * int FORMNAMECOL
	 */
	private static final int FORMNAMECOL = 2;
	/**
	 * int MESSAGECOL
	 */
	private static final int MESSAGECOL = 3;
	/**
	 * int STATUSCOL
	 */
	private static final int STATUSCOL = 4;
	/**
	 * int TITLEROW
	 */
	private static final int TITLEROW = 0;
	/**
	 * int STEPIDCOL
	 */
	private static final int STEPIDCOL = 0;

	private static final int CELL_CHARACTER_LIMIT = 32767;

	private static final String FILE_NOT_FOUND = " File could not create!";

	private TedamFileUtils() {
		// TedamFileUtils private constructor
	}

	public static List<TedamFile> getFiles(String filepath) {
		File filePathDirectory = new File(filepath);
		List<TedamFile> fileNameList = new ArrayList<>();

		if (filePathDirectory.exists()) {
			for (File fileEntry : filePathDirectory.listFiles()) {
				fileNameList.add(new TedamFile(fileEntry.getName()));
			}
		}
		return fileNameList;
	}

	/**
	 * It arranges and fills and creates an excel file based on the incoming list
	 * and filePath.
	 *
	 * @param reportList
	 * @param resultFilePath
	 * @author Tarik.Mikyas
	 */
	public static boolean printReport(List<TestReport> reportList, String resultFilePath) {

		File file = new File(resultFilePath);
		WorkbookSettings wbSettings = new WorkbookSettings();
		int fontSize = 12;
		// create create a bold font with underlines
		WritableCellFormat timesBoldUnderline = new WritableCellFormat(
				new WritableFont(WritableFont.TIMES, fontSize, WritableFont.BOLD, false));
		// Lets automatically wrap the cells
		WritableWorkbook workbook = null;
		try {
			timesBoldUnderline.setWrap(true);

			WritableCellFormat wcf = new WritableCellFormat();
			wcf.setBorder(Border.ALL, BorderLineStyle.THICK);
			CellView cvLong = new CellView();
			cvLong.setFormat(timesBoldUnderline);
			int cellWidthLong = 10000;
			cvLong.setSize(cellWidthLong);

			CellView cvShort = new CellView();
			cvShort.setFormat(timesBoldUnderline);
			int cellWidthShort = 7000;
			cvShort.setSize(cellWidthShort);
			workbook = Workbook.createWorkbook(file, wbSettings);
			workbook.createSheet("Report", 0);
			WritableSheet excelSheet = workbook.getSheet(0);
			initiateReport(excelSheet, cvLong, cvShort, wcf);
			fillReport(excelSheet, reportList);
			workbook.write();
			TedamLogUtils.log("TedamFileUtils.printReport", "The printReport procedure has been called.",
					TedamLogLevel.INFO, true);
			return true;
		} catch (WriteException e) {
			LOGGER.error("" + e);
			return false;
		} catch (IOException e) {
			LOGGER.error("" + e);
			return false;
		} finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (WriteException e) {
					LOGGER.error("" + e);
				} catch (IOException e) {
					LOGGER.error("" + e);
				}
			}
		}
	}

	/**
	 * This method initiateReport
	 *
	 * @param sheet
	 * @param cvLong
	 * @param cvShort
	 * @param wcf
	 * @throws WriteException
	 * @author Tarik.Mikyas
	 */
	private static void initiateReport(WritableSheet sheet, CellView cvLong, CellView cvShort, WritableCellFormat wcf) {
		// The columns are defined.
		Label labStepId = new Label(STEPIDCOL, TITLEROW, "Step Id", wcf);
		Label labStepName = new Label(STEPNAMECOL, TITLEROW, "Step Name", wcf);
		Label labFormName = new Label(FORMNAMECOL, TITLEROW, "Form Name", wcf);
		Label labMessage = new Label(MESSAGECOL, TITLEROW, "Message", wcf);
		Label labStatus = new Label(STATUSCOL, TITLEROW, "Status", wcf);

		// Columns are added to the cast.
		try {
			sheet.addCell(labStepId);
			sheet.setColumnView(STEPIDCOL + 1, cvShort);
			sheet.addCell(labStepName);
			sheet.setColumnView(STEPNAMECOL + 1, cvShort);
			sheet.addCell(labFormName);
			sheet.setColumnView(FORMNAMECOL + 1, cvLong);
			sheet.addCell(labMessage);
			sheet.setColumnView(MESSAGECOL + 1, cvLong);
			sheet.addCell(labStatus);
			sheet.setColumnView(STATUSCOL + 1, cvShort);
		} catch (RowsExceededException e) {
			LOGGER.error("" + e);
		} catch (WriteException e) {
			LOGGER.error("" + e);
		}
	}

	/**
	 * This method fillReport
	 *
	 * @param sheet
	 * @param reportList
	 * @throws WriteException
	 * @author Tarik.Mikyas
	 */
	private static void fillReport(WritableSheet sheet, List<TestReport> reportList) {
		// A report line is created for each element
		// in the reportList.
		WritableCellFormat wcf = new WritableCellFormat();
		try {
			wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
			for (int i = 0; i < reportList.size(); i++) {
				// Step id
				TestReport report = reportList.get(i);
				Label labStepId = new Label(STEPIDCOL, 1 + i, String.valueOf(report.getTestStepId()), wcf);
				sheet.addCell(labStepId);
				// Step type
				Label labStepName = new Label(STEPNAMECOL, 1 + i, report.getStepName(), wcf);
				sheet.addCell(labStepName);
				// Form name
				Label labFormName = new Label(FORMNAMECOL, 1 + i, report.getFormName(), wcf);
				sheet.addCell(labFormName);
				// Step process messages
				String reportMessage = report.getMessage();
				if (reportMessage != null && reportMessage.length() > CELL_CHARACTER_LIMIT) {
					reportMessage = reportMessage.substring(0, CELL_CHARACTER_LIMIT);
				}
				Label labMessage = new Label(MESSAGECOL, 1 + i, reportMessage, wcf);
				sheet.addCell(labMessage);
				// State of step(FAILED, CAUTION, SUCCEEDDED)
				Label labStatus = new Label(STATUSCOL, 1 + i, report.getStatusMsg(), wcf);
				sheet.addCell(labStatus);
			}
		} catch (WriteException e) {
			LOGGER.error("" + e);
		}
	}

	public static boolean copySourceToDestinationWithFormat(String sourceFilePathStr, String destinationFilePathStr,
			String format, boolean isMoved) {
		String methodName = "TedamFileUtils.copySourceToDestinationWithFormat";
		Path sourceFilePath = Paths.get(sourceFilePathStr + format);
		Path destinationFilePath = Paths.get(destinationFilePathStr + format);
		try {
			if (!Files.exists(sourceFilePath)) {
				throw new CreateNewFileException(sourceFilePath.toString());
			}
			createNewFilePath(destinationFilePath.toFile().getParent());
			if (isMoved) {
				Files.move(sourceFilePath, destinationFilePath, StandardCopyOption.ATOMIC_MOVE);
			} else {
				Files.copy(sourceFilePath, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
			}
			if (!Files.exists(destinationFilePath)) {
				throw new CreateNewFileException(destinationFilePath.toString());
			}
			TedamLogUtils.log(
					methodName, sourceFilePath.toUri() + " File successfully "
							+ destinationFilePath.toFile().getParent() + " copied to path. ",
					TedamLogLevel.INFO, java.lang.Boolean.TRUE);
			return true;
		} catch (CreateNewFileException e) {
			TedamLogUtils.log(methodName, "File does not exist filePath : " + e.getMessage(), TedamLogLevel.ERROR,
					java.lang.Boolean.TRUE);
			LOGGER.error("" + e);
			return false;
		} catch (IOException e) {
			TedamLogUtils.log(methodName, sourceFilePath.toUri() + " File " + destinationFilePath.toFile().getParent()
					+ " Unable to copy to path.", TedamLogLevel.ERROR, java.lang.Boolean.TRUE);
			LOGGER.error("" + e);
			return false;
		}

	}

	/**
	 * This method is used to copy the newly generated report and filter files to
	 * the Z machine. <br>
	 * These new report files are located under the moduler_scripts file where
	 * base.bsh is located.
	 *
	 * @param testCaseID
	 * @param reportFilePath
	 * @param reportFileName
	 * @param format         <br>
	 * @author Tarik.Mikyas
	 */
	public static void overwriteReportParameterFile(String sourceFileDirectory, String destinationDirectory) {
		ScriptService ss = new ScriptService();
		// Before this method can be used, the machine where the archive file is located
		// must be authorized to the C:\\TedamFace folder on the machine 172.16.2.190.
		// In addition, the corresponding folder map must be introduced as Z network
		// location by network drive.
		// Unless these priorities are provided, this method will not work due to
		// authorization failure.
		String methodName = "TedamFileUtils.overwriteReportParameterFile";
		ss.log(methodName, "The file to be copied is being prepared... sourceFileDirectory :" + sourceFileDirectory,
				TedamLogLevel.INFO, java.lang.Boolean.TRUE);

		// path of the file to read
		Path sourceFilePath = Paths.get(sourceFileDirectory);
		// the path to the destination file
		Path destinationFilePath = Paths.get(destinationDirectory);
		ss.log(methodName, "sourceFile :" + sourceFileDirectory, TedamLogLevel.INFO, java.lang.Boolean.TRUE);
		ss.log("TedamReportUtils.overwriteReportParameterFile", "filterFile :" + destinationDirectory,
				TedamLogLevel.INFO, java.lang.Boolean.TRUE);
		try {
			byte[] source = Files.readAllBytes(sourceFilePath);
			Files.write(destinationFilePath, source, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
			ss.log(methodName, "The files have been successfully copied. ", TedamLogLevel.INFO, java.lang.Boolean.TRUE);
		} catch (IOException e) {
			LOGGER.error("" + e);
		}
	}

	/**
	 * @return <br>
	 *         this method isFileExist
	 * @author Tarik.Mikyas
	 */
	public static boolean isFileExist(String filePath) {
		boolean isControlFileExist;
		File file = new File(filePath);
		if (file.exists()) {
			isControlFileExist = true;
			LOGGER.info(filePath + " The file was found.");
		} else {
			isControlFileExist = false;
			LOGGER.warn(filePath + " The file was not found.");
		}
		return isControlFileExist;
	}

	/**
	 * Created Tedam Excel report file is read.
	 *
	 * @param filePath
	 * @return
	 */
	public static List<LogoTestResult> readFromExcelFileTEDAM(String filePath) {
		List<LogoTestResult> resultSet = new ArrayList<>();
		File fileExcel = new File(filePath);
		Workbook workBook = null;
		try {
			workBook = Workbook.getWorkbook(fileExcel);
		} catch (BiffException e) {
			LOGGER.error("" + e);
		} catch (IOException e) {
			LOGGER.error("" + e);
		}
		if (workBook != null) {

			for (int k = 0; k < workBook.getNumberOfSheets(); k++) {
				Sheet excelSheet = workBook.getSheet(k);
				Cell statusCell = excelSheet.findCell("Status");
				int j = statusCell.getColumn();
				checkForStatusMessage(resultSet, excelSheet, j);
			}
		}
		return resultSet;
	}

	private static void checkForStatusMessage(List<LogoTestResult> resultSet, Sheet excelSheet, int j) {
		for (int i = 1; i < excelSheet.getRows(); i++) {
			Cell cell = excelSheet.getCell(j, i);
			if (!TedamStringUtils.isInteger(excelSheet.getCell(0, i).getContents())) {
				continue;
			}
			if (StatusMessages.FAILED.getStatus().equalsIgnoreCase(cell.getContents())) {
				resultSet.add(createTestResult(excelSheet, i, ExecutionStatus.FAILED));
				break;
			} else if (StatusMessages.CAUTION.getStatus().equalsIgnoreCase(cell.getContents())) {
				resultSet.add(createTestResult(excelSheet, i, ExecutionStatus.CAUTION));
			} else if (StatusMessages.SUCCEEDED.getStatus().equalsIgnoreCase(cell.getContents())) {
				resultSet.add(createTestResult(excelSheet, i, ExecutionStatus.SUCCEEDED));
			}
		}
	}

	private static LogoTestResult createTestResult(Sheet excelSheet, int i, ExecutionStatus executionStatus) {
		LogoTestResult testResult = new LogoTestResult();
		testResult.setResult(executionStatus);
		testResult.setId(Integer.valueOf(excelSheet.getCell(0, i).getContents()));
		if (!ExecutionStatus.SUCCEEDED.equals(executionStatus)) {
			testResult.setName(excelSheet.getCell(1, i).getContents());
			testResult.setDescription(excelSheet.getCell(3, i).getContents());
		} else {
			testResult.setName("");
			testResult.setDescription("");
		}
		return testResult;
	}

	/**
	 * this method deleteFile <br>
	 *
	 * @param filePath <br>
	 * @author Canberk.Erkmen
	 */
	public static void deleteFile(String filePath) {
		try {
			File file = new File(filePath);

			if (file.delete()) {
				LOGGER.info(file.getName() + " The file has been deleted.");
			} else if (!file.exists()) {
				LOGGER.info(file.getName() + " The file could not be deleted because it does not exist.");
			} else {
				LOGGER.warn("Error while deleting file.");
			}

		} catch (Exception e) {
			LOGGER.error("" + e);
		}
	}

	/**
	 * Returns an Element object, depending on the given file path.
	 *
	 * @param filePath
	 * @return <br>
	 *         this method getElementFromXmlFile
	 * @author Tarik.Mikyas
	 */
	public static Element getElementFromXmlFile(String filePath) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(filePath));
			return document.getDocumentElement();
		} catch (Exception e) {
			LOGGER.error("" + e);
			return null;
		}
	}

	/**
	 * Returns an Element object, depending on the given file path.
	 *
	 * @param filePath
	 * @return <br>
	 *         this method getElementFromXmlFile
	 * @author Tarik.Mikyas
	 */
	public static Document getDocumentFromXmlString(String xml) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			return builder.parse(new InputSource(new StringReader(xml)));
		} catch (Exception e) {
			LOGGER.error("" + e);
			return null;
		}
	}

	/**
	 * this method createFile <br>
	 *
	 * @param fileAbsolutePath
	 * @param fileContent
	 * @throws IOException <br>
	 * @author Canberk.Erkmen
	 */
	public static void createFile(String fileAbsolutePath, String fileContent) throws IOException {
		Path filePath = Paths.get(fileAbsolutePath);
		Files.write(filePath, fileContent.getBytes(), StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING,
				StandardOpenOption.CREATE);

	}

	/**
	 * this method createNewFile The procedure that should be called when a file is
	 * created on the system. <br>
	 *
	 * @param filePath <br>
	 * @throws CreateNewFileException
	 * @author Tarik.Mikyas
	 */
	public static boolean createNewFile(String filePath) throws CreateNewFileException {
		try {
			File file = new File(filePath);
			createNewFilePath(file.getParent());
			if (file.exists()) {
				return true;
			} else if (file.createNewFile()) {
				return true;
			} else {
				LOGGER.error(filePath + FILE_NOT_FOUND);
				return false;
			}
		} catch (IOException e) {
			LOGGER.error(filePath + "-error occurred while creating file..." + e);
			throw new CreateNewFileException(filePath + "-error occurred while creating file..." + e);
		}
	}

	/**
	 * this method createNewFilePath creates the given path hierarchy if the given
	 * path does not exist. <br>
	 * true if such a file path already exists or if the given file path is created.
	 * <br>
	 * If this file path can not be created, an exception is thrown.
	 *
	 * @param filePath
	 * @return <br>
	 * @author Tarik.Mikyas
	 */
	public static void createNewFilePath(String filePath) throws CreateNewFileException {
		File file = new File(filePath);
		try {
			if (file.exists()) {
				return;
			} else if (file.mkdirs()) {
				return;
			} else {
				LOGGER.error(filePath + FILE_NOT_FOUND);
				throw new IOException(" File could not create! ");
			}
		} catch (IOException e) {
			LOGGER.error(filePath + FILE_NOT_FOUND + e);
			throw new CreateNewFileException(filePath + FILE_NOT_FOUND);
		}
	}

	/**
	 * this method writeToTextDocument Creates the given file if no path exists and
	 * writes the given contents in UTF8 format.<br>
	 * Returns false if it can not be written or created. <br>
	 *
	 * @param filePath
	 * @param content
	 * @return <br>
	 * @throws CreateNewFileException
	 * @throws IOException
	 * @author Tarik.Mikyas
	 */
	public static void writeToTextDocumentUTF8(String filePath, String content)
			throws CreateNewFileException, IOException {

		List<String> lines = new ArrayList<>();
		try (Scanner scanner = new Scanner(content)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				lines.add(line);
			}
			// if the file does not exist is created.
			TedamFileUtils.createNewFile(filePath);
			Path file = Paths.get(filePath);
			// SnapShot is a new way to break down the document.
			Files.write(file, lines, StandardCharsets.UTF_8);
		} catch (IOException e) {
			LOGGER.error(filePath + "Could not write to file. IOException : " + e);
			throw e;
		} catch (CreateNewFileException e) {
			LOGGER.error(filePath + "Failed to create file. CreateNewFileException : " + e);
			throw e;
		}
	}

	public static String getFileContent(String filePath) {
		String fileContent = Constants.EMPTY_STRING;
		try {
			fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
			LOGGER.info("getFileContent succeded");
		} catch (IOException e) {
			LOGGER.error("" + e);
		}
		return fileContent;
	}

	public static void copyFile(String sourceFilePath, String targetFilePath) {
		try {
			File file = new File(sourceFilePath);
			File newFile = new File(targetFilePath);
			if (file.exists() && !newFile.exists()) {
				LOGGER.info(newFile.getPath() + " the file will be created.");
				Files.copy(Paths.get(file.getPath()), Paths.get(newFile.getPath()), StandardCopyOption.COPY_ATTRIBUTES);
			}
		} catch (Exception e) {
			LOGGER.error("" + e);
		}
	}

	public static void moveFile(String sourceFilePath, String targetFilePath) {
		try {
			File sourceFile = new File(sourceFilePath);
			if (sourceFile.exists()) {
				LOGGER.info("sourceFilePath : " + sourceFilePath + " targetFilePath : " + targetFilePath);
				Files.move(Paths.get(sourceFile.getPath()), Paths.get(targetFilePath), StandardCopyOption.ATOMIC_MOVE);
			}
		} catch (Exception e) {
			LOGGER.error("" + e);
		}
	}

}
