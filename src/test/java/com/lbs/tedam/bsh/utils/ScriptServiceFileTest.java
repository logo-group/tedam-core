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

package com.lbs.tedam.bsh.utils;

import com.lbs.tedam.model.TestReport;
import com.lbs.tedam.test.BaseServiceTest;
import com.lbs.tedam.util.Constants;
import com.lbs.tedam.util.Enums.StatusMessages;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tarik.Mikyas<br>
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScriptServiceFileTest extends BaseServiceTest {

    private final ScriptService scriptService = new ScriptService(true);

    /**
     * @author Tarik.Mikyas <br>
     */
    @Test
    public void test01PrintTestReport() {
        List<TestReport> reportList = new ArrayList<>();
        TestReport bshTestReport;
        bshTestReport = new TestReport();
        bshTestReport.addMessage("Mesaj 1");
        bshTestReport.setStepName("Step 1");
        bshTestReport.setTestStepId(1);
        bshTestReport.setFormName("Form 1");
        bshTestReport.setStatusMsg(StatusMessages.SUCCEEDED.getStatus());
        reportList.add(bshTestReport);
        bshTestReport = new TestReport();
        bshTestReport.addMessage("Mesaj 2");
        bshTestReport.setStepName("Step 2");
        bshTestReport.setTestStepId(2);
        bshTestReport.setFormName("Form 2");
        bshTestReport.setStatusMsg(StatusMessages.FAILED.getStatus());
        reportList.add(bshTestReport);
        bshTestReport = new TestReport();
        bshTestReport.addMessage("Mesaj 3");
        bshTestReport.setStepName("Step 3");
        bshTestReport.setTestStepId(3);
        bshTestReport.setFormName("Form 3");
        bshTestReport.setStatusMsg(StatusMessages.CAUTION.getStatus());
        reportList.add(bshTestReport);
        scriptService.printTestReport(reportList, "Tedam_Test_Excel_Report.xls", getTempdir());
    }

    /**
     * @author Tarik.Mikyas <br>
     * this method testPrepareReportFile
     */
    @Test
    public void test02PrepareReportFile() {
        String sourceFilePath = getFilePath("/SatisFaturalari").getPath();
        scriptService.copySourceToDestinationWithFormat(sourceFilePath, getTempdir() + "SatisFaturalari", Constants.FILE_EXTENSION_PDF, false);
    }

}
