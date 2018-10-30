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

import com.lbs.tedam.exception.CreateNewFileException;
import com.lbs.tedam.model.DTO.LogoTestResult;
import com.lbs.tedam.model.TedamFile;
import com.lbs.tedam.model.TestReport;
import com.lbs.tedam.test.BaseServiceTest;
import com.lbs.tedam.util.Enums.StatusMessages;
import org.junit.Test;
import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Tarik.Mikyas<br>
 */
public class TedamFileUtilsTest extends BaseServiceTest implements HasLogger {

    @Test
    public void testCreateAndDeleteNewFile() throws CreateNewFileException {
        assertTrue(TedamFileUtils.createNewFile(getTempdir() + "tedam" + Constants.FILE_SEPARATOR + "ykg.txt"));
        TedamFileUtils.deleteFile(getTempdir() + "tedam" + Constants.FILE_SEPARATOR + "ykg.txt");
        TedamFileUtils.deleteFile(getTempdir() + "tedam" + Constants.FILE_SEPARATOR + "ykg.txt");
    }

    @Test
    public void testWriteToTextDocumentUTF8() throws CreateNewFileException, IOException {
        TedamFileUtils.writeToTextDocumentUTF8(getTempdir() + "tedam" + Constants.FILE_SEPARATOR + "tedamtest.txt", "TedamTest");
    }

    @Test
    public void testPrintReport() {
        List<TestReport> reportList = new ArrayList<>();
        TestReport bshTestReport = new TestReport();
        bshTestReport.addMessage("Mesaj 1");
        bshTestReport.setStepName("Step 1");
        bshTestReport.setTestStepId(1);
        bshTestReport.setFormName("Form 1");
        bshTestReport.setStatusMsg(StatusMessages.SUCCEEDED.getStatus());
        reportList.add(bshTestReport);
        TedamFileUtils.printReport(reportList, getTempdir() + "tedam" + Constants.FILE_SEPARATOR + "tedam.xls");

    }

    @Test
    public void testCreateNewFilePath() throws CreateNewFileException {
        TedamFileUtils.createNewFilePath(getTempdir() + "tedam" + Constants.FILE_SEPARATOR + "tedam.txt");
        TedamFileUtils.deleteFile(getTempdir() + "tedam" + Constants.FILE_SEPARATOR + "tedam.txt");
    }

    @Test
    public void testIsFileExist() {
        assertTrue(TedamFileUtils.isFileExist(getFilePath("/Base.bsh").getPath()));
    }

    @Test
    public void testCopyFileException() {
        TedamFileUtils.copyFile(getFilePath("/Base.bsh").getPath(), "/BaseCopy.bsh");
    }

    @Test
    public void testCopyFileException2() {
        TedamFileUtils.copyFile("asd.bsh", "/BaseCopy.bsh");
    }

    @Test
    public void testCopyFileException3() {
        TedamFileUtils.copyFile(getFilePath("/Base.bsh").getPath(), getTempdir() + "BaseCopy.bsh");
    }

    @Test
    public void testCopyFile() {
        TedamFileUtils.deleteFile(getTempdir() + "BaseCopy.bsh");
        TedamFileUtils.copyFile(getFilePath("/Base.bsh").getPath(), getTempdir() + "BaseCopy.bsh");
    }

    @Test
    public void testMoveFile() {
        TedamFileUtils.moveFile(getFilePath("/Base.bsh").getPath(), getFilePath("/Base.bsh").getPath());
    }

    @Test
    public void testMoveFileNotExist() {
        TedamFileUtils.moveFile("asd.bsh", getFilePath("/Base.bsh").getPath());
    }

    @Test
    public void testMoveFileException() {
        TedamFileUtils.moveFile(getFilePath("/Base.bsh").getPath(), "");
    }

    @Test
    public void test01GetDocumentFromXmlString() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Form enabled=\"true\" height=\"110\" lastUpdate=\"2017-01-27 16:22:16\" locationX=\"0\" locationY=\"0\" name=\"UNXFChangeWorkDate.jfm\" tag=\"1000003\" title=\"Çalışma Tarihleri\" version=\"v2.48.6.0\" visible=\"true\" width=\"425\"> "
                + "		<Control enabled=\"true\" hasLookup=\"false\" height=\"59\" locationX=\"5\" locationY=\"5\" mandatory=\"false\" tag=\"101\" text=\"\" title=\"Unknown\" type=\"10\" value=\"\" visible=\"true\" width=\"415\"> "
                + " 		<Control enabled=\"true\" hasLookup=\"false\" height=\"22\" locationX=\"5\" locationY=\"5\" mandatory=\"false\" tag=\"1000001\" text=\"\" title=\"İnsan Kaynakları\" type=\"101\" value=\"\" visible=\"true\" width=\"87\"/> "
                + " 		<Control enabled=\"true\" hasLookup=\"false\" height=\"22\" locationX=\"92\" locationY=\"5\" mandatory=\"false\" tag=\"111\" text=\"\" title=\"Unknown\" type=\"116\" value=\"01/07/2016\" visible=\"true\" width=\"104\"/> "
                + " 		<Control enabled=\"true\" hasLookup=\"false\" height=\"22\" locationX=\"201\" locationY=\"5\" mandatory=\"false\" tag=\"112\" text=\"\" title=\"Günün Tarihi Kullanılsın\" type=\"107\" value=\"false\" visible=\"true\" width=\"135\"/> "
                + " 		<Control enabled=\"true\" hasLookup=\"false\" height=\"22\" locationX=\"5\" locationY=\"32\" mandatory=\"false\" tag=\"1000002\" text=\"\" title=\"Ticari Sistem\" type=\"101\" value=\"\" visible=\"true\" width=\"67\"/> "
                + " 		<Control enabled=\"true\" hasLookup=\"false\" height=\"22\" locationX=\"92\" locationY=\"32\" mandatory=\"false\" tag=\"121\" text=\"\" title=\"Unknown\" type=\"116\" value=\"27/01/2017\" visible=\"true\" width=\"104\"/> "
                + " 		<Control enabled=\"true\" hasLookup=\"false\" height=\"22\" locationX=\"201\" locationY=\"32\" mandatory=\"false\" tag=\"122\" text=\"\" title=\"Günün Tarihi Kullanılsın\" type=\"107\" value=\"false\" visible=\"true\" width=\"135\"/> "
                + " 	</Control> "
                + " 	<Control enabled=\"true\" hasLookup=\"false\" height=\"26\" locationX=\"276\" locationY=\"79\" mandatory=\"false\" tag=\"3000000\" text=\"\" title=\"Unknown\" type=\"10\" value=\"\" visible=\"true\" width=\"144\"> "
                + " 		<Control attribute=\"SAVECLOSE\" enabled=\"true\" hasLookup=\"false\" height=\"21\" locationX=\"0\" locationY=\"0\" mandatory=\"false\" tag=\"500\" text=\"\" title=\"Kaydet\" type=\"109\" value=\"\" visible=\"true\" width=\"67\"/> "
                + " 		<Control attribute=\"CLOSE\" enabled=\"true\" hasLookup=\"false\" height=\"21\" locationX=\"72\" locationY=\"0\" mandatory=\"false\" tag=\"600\" text=\"\" title=\"Vazgeç\" type=\"109\" value=\"\" visible=\"true\" width=\"67\"/> "
                + " 	</Control> " + " 	<Messages/> " + " </Form> ";
        assertNotNull(TedamFileUtils.getDocumentFromXmlString(xml));
    }

    @Test
    public void test01GetDocumentFromXmlStringException() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Form enabled=\"true\" height=\"110\" lastUpdate=\"2017-01-27 16:22:16\" locationX=\"0\" locationY=\"0\" name=\"UNXFChangeWorkDate.jfm\" tag=\"1000003\" title=\"Çalışma Tarihleri\" version=\"v2.48.6.0\" visible=\"true\" width=\"425\"> "
                + "		<Control enabled=\"true\" hasLookup=\"false\" height=\"59\" locationX=\"5\" locationY=\"5\" mandatory=\"false\" tag=\"101\" text=\"\" title=\"Unknown\" type=\"10\" value=\"\" visible=\"true\" width=\"415\"> "
                + " 		<Control enabled=\"true\" hasLookup=\"false\" height=\"22\" locationX=\"5\" locationY=\"5\" mandatory=\"false\" tag=\"1000001\" text=\"\" title=\"İnsan Kaynakları\" type=\"101\" value=\"\" visible=\"true\" width=\"87\"/> "
                + " 	</Control> "
                + " 	<Control enabled=\"true\" hasLookup=\"false\" height=\"26\" locationX=\"276\" locationY=\"79\" mandatory=\"false\" tag=\"3000000\" text=\"\" title=\"Unknown\" type=\"10\" value=\"\" visible=\"true\" width=\"144\"> "
                + " 		<Control attribute=\"SAVECLOSE\" enabled=\"true\" hasLookup=\"false\" height=\"21\" locationX=\"0\" locationY=\"0\" mandatory=\"false\" tag=\"500\" text=\"\" title=\"Kaydet\" type=\"109\" value=\"\" visible=\"true\" width=\"67\"/> "
                + " 	</Control> ";
        assertNull(TedamFileUtils.getDocumentFromXmlString(xml));
    }

    @Test
    public void test02readFromExcelFileTEDAMSucceeded() {
        List<LogoTestResult> testResultList = TedamFileUtils.readFromExcelFileTEDAM(getFilePath("/SampleTestTEDAMSucceeded.xls").getPath());
        getLogger().info("testResultList size : " + testResultList.size());
        assertNotNull(testResultList);
    }

    @Test
    public void test03readFromExcelFileTEDAMFailed() {
        List<LogoTestResult> testResultList = TedamFileUtils.readFromExcelFileTEDAM(getFilePath("/SampleTestTEDAMFailed.xls").getPath());
        assertNotNull(testResultList);
    }

    @Test
    public void test04readFromExcelFileTEDAMCaution() {
        List<LogoTestResult> testResultList = TedamFileUtils.readFromExcelFileTEDAM(getFilePath("/SampleTestTEDAMCaution.xls").getPath());
        assertNotNull(testResultList);
    }

    @Test
    public void test05readFromExcelFileTEDAMNotStarted() {
        List<LogoTestResult> testResultList = TedamFileUtils.readFromExcelFileTEDAM(new File("").getPath());
        assertNotNull(testResultList);
    }

    @Test
    public void test08getFileContent() {
        String text = TedamFileUtils.getFileContent(getFilePath("/Butceler.xml").getPath());
        assertNotNull(text);
    }

    @Test
    public void test08getFileContentException() {
        TedamFileUtils.getFileContent("asd.xml");
    }

    @Test
    public void testGetFiles() {
        List<TedamFile> files = TedamFileUtils.getFiles("src/test/resources");
        assertNotEquals(files.size(), 0);
    }

    @Test
    public void testGetFilesNull() {
        List<TedamFile> files = TedamFileUtils.getFiles("asd.xml");
        assertEquals(files.size(), 0);
    }

    @Test
    public void testGetElementFromXmlFile() {
        Element element = TedamFileUtils.getElementFromXmlFile(getFilePathFromSourceName("/GetVersionTest.xml"));
        assertNotNull(element);
    }

    @Test()
    public void testGetElementFromXmlFileException() {
        Element element = TedamFileUtils.getElementFromXmlFile("");
        assertNull(element);
    }

    @Test
    public void testCreateFile() throws IOException {
        TedamFileUtils.createFile(getTempdir() + "ykg.txt", "deneme");
    }

}
