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

package com.lbs.tedam.exception;

import com.lbs.tedam.test.BaseServiceTest;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ExceptionTest extends BaseServiceTest {

    @Test
    public void testAuthenticateException() {
        AuthenticateException exception = new AuthenticateException();
        exception = new AuthenticateException("AuthenticateException", new Throwable());
        exception = new AuthenticateException(new Throwable());
        exception = new AuthenticateException("AuthenticateException");
        assertNotNull(exception.getMessage());
    }

    @Test
    public void testTestCaseNotSelectedException() {
        TestCaseNotSelectedException exception = new TestCaseNotSelectedException();
        exception = new TestCaseNotSelectedException("TestCaseNotSelectedException");
        assertNotNull(exception.getMessage());
    }

    @Test
    public void testTedamDatabaseException() {
        TedamDatabaseException exception = new TedamDatabaseException();
        exception = new TedamDatabaseException("TedamDatabaseException", new Throwable());
        exception = new TedamDatabaseException(new Throwable());
        exception = new TedamDatabaseException("TedamDatabaseException");
        assertNotNull(exception.getMessage());
    }

    @Test
    public void testTedamConstraintViolationException() {
        TedamConstraintViolationException exception = new TedamConstraintViolationException();
        exception = new TedamConstraintViolationException("TedamConstraintViolationException", new Throwable());
        exception = new TedamConstraintViolationException(new Throwable());
        exception = new TedamConstraintViolationException("TedamConstraintViolationException");
        assertNotNull(exception.getMessage());
    }

    @Test
    public void testJobRunnerEngineException() {
        JobRunnerEngineException exception = new JobRunnerEngineException();
        exception = new JobRunnerEngineException("JobRunnerEngineException");
        assertNotNull(exception.getMessage());
    }

    @Test
    public void testJobCommandBuildException() {
        JobCommandBuildException exception = new JobCommandBuildException();
        exception = new JobCommandBuildException("JobCommandBuildException");
        assertNotNull(exception.getMessage());
    }

    @Test
    public void testInvalidScriptArgumentException() {
        InvalidScriptArgumentException exception = new InvalidScriptArgumentException();
        exception = new InvalidScriptArgumentException("InvalidScriptArgumentException");
        assertNotNull(exception.getMessage());
    }

    @Test
    public void testDocumentBuildException() {
        DocumentBuildException exception = new DocumentBuildException();
        exception = new DocumentBuildException("DocumentBuildException");
        assertNotNull(exception.getMessage());
    }

    @Test
    public void testDifferencesSnapshotException() {
        DifferencesSnapshotException exception = new DifferencesSnapshotException();
        exception = new DifferencesSnapshotException("DifferencesSnapshotException");
        assertNotNull(exception.getMessage());
    }

    @Test
    public void testDeleteWithPropertyException() {
        DeleteWithPropertyException exception = new DeleteWithPropertyException();
        exception = new DeleteWithPropertyException("DeleteWithPropertyException");
        assertNotNull(exception.getMessage());
    }

    @Test
    public void testDataProviderException() {
        DataProviderException exception = new DataProviderException();
        exception = new DataProviderException("DataProviderException");
        assertNotNull(exception.getMessage());
    }

    @Test
    public void testCreateNewFileException() {
        CreateNewFileException exception = new CreateNewFileException();
        exception = new CreateNewFileException("CreateNewFileException");
        assertNotNull(exception.getMessage());
    }

    @Test
    public void testVersionParameterValueException() {
        VersionParameterValueException exception = new VersionParameterValueException();
        VersionParameterValueException exception1 = new VersionParameterValueException("message");
    }

}
