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

package com.lbs.tedam.generator.steptype;

import org.junit.Test;

import com.lbs.tedam.exception.localized.LocalizedException;

public class ReportGeneratorTest {

    /**
     * @author Seyma.Sahin
     */
    @Test
    public void testSetters() {
        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.setReportName("reportName");
        reportGenerator.setContinueOnError("continueOnError");
        reportGenerator.setWriteFilters("writeFilters");
        reportGenerator.setReportWaitSleepMillis(null);
        reportGenerator.degenerate("parameter");
        reportGenerator.generate();
        reportGenerator.generateLookUp();
    }

    @Test
    public void testGetters() {
        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.getReportName();
        reportGenerator.getContinueOnError();
        reportGenerator.getWriteFilters();
        reportGenerator.getReportWaitSleepMillis();
    }

	@Test(expected = UnsupportedOperationException.class)
	public void testDegenerateLookUp() throws LocalizedException {
        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.degenerateLookUp("parameter");
    }
}
