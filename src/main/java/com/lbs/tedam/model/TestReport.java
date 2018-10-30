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

package com.lbs.tedam.model;

import com.lbs.tedam.util.Enums.StatusMessages;

/**
 * @author Tarik.Mikyas
 */
public class TestReport {

    /**
     * Keeps your step id.
     */
    private Integer m_stepId;
    /**
     * It holds the step type. (FormFill)
     */
    private String m_stepName;
    /**
     * The name of the form on which Step is working. (MMXFItemSlipBrowser%0)
     */
    private String m_formName;
    /**
     * Keeps detailed information set after step. (There is a record with the same
     * feature.)
     */
    private String m_message;
    /**
     * Keeps track of success after step work. (FAILED, SUCCEEDED, CAUTION)
     */
    private String m_statusMsg;
    /**
     * Step includes knowledge whether to proceed according to success status.
     */
    private boolean m_status;

    /**
     * @param stepName
     * @param formName
     */
    public TestReport(String stepName, String formName) {
        super();
        this.m_stepName = stepName;
        this.m_formName = formName;
    }

    /**
     * @param stepName
     * @param formName
     * @param isLookup
     */
    public TestReport(String stepName, String formName, boolean isLookup) {
        super();
        if (isLookup) {
            this.m_stepName = "(Lookup)" + stepName;
            this.m_formName = formName;
        } else {
            this.m_stepName = stepName;
            this.m_formName = formName;
        }
    }

    /**
     *
     */
    public TestReport() {
        super();
    }

    /**
     * @return <br>
     * this method getStepName
     * @author Tarik.Mikyas
     */
    public String getStepName() {
        return m_stepName;
    }

    /**
     * @param stepName <br>
     *                 this method setStepName
     * @author Tarik.Mikyas
     */
    public void setStepName(String stepName) {
        this.m_stepName = stepName;
    }

    /**
     * @return <br>
     * this method getFormName
     * @author Tarik.Mikyas
     */
    public String getFormName() {
        return m_formName;
    }

    /**
     * @param formName <br>
     *                 this method setFormName
     * @author Tarik.Mikyas
     */
    public void setFormName(String formName) {
        this.m_formName = formName;
    }

    /**
     * @return <br>
     * this method getMessage
     * @author Tarik.Mikyas
     */
    public String getMessage() {
        return m_message;
    }

    /**
     * @param message <br>
     *                this method setMessage
     * @author Tarik.Mikyas
     */
    public void setMessage(String message) {
        this.m_message = message;
    }

    /**
     * @param message <br>
     *                this method addMessage
     * @author Tarik.Mikyas
     */
    public void addMessage(String message) {
        if (m_message == null) {
            m_message = message;
        } else {
            // "\ 012" refers to the new line for regex excel.
            m_message += "\012" + message;
        }
    }

    /**
     * @return <br>
     * this method getStatusMsg
     * @author Tarik.Mikyas
     */
    public String getStatusMsg() {

        return m_statusMsg;
    }

    /**
     * @param statusMsg <br>
     *                  this method setStatusMsg
     * @author Tarik.Mikyas
     */
    public void setStatusMsg(String statusMsg) {
        if (statusMsg != null) {
            if (statusMsg.equals(StatusMessages.FAILED.getStatus())) {
                this.m_status = false;
                this.m_statusMsg = statusMsg;
            } else if (statusMsg.equals(StatusMessages.SUCCEEDED.getStatus())
                    || statusMsg.equals(StatusMessages.CAUTION.getStatus())) {
                this.m_status = true;
                this.m_statusMsg = statusMsg;
            } else {
                this.m_status = false;
                this.m_statusMsg = "Error";
            }
        }
    }

    /**
     * @return <br>
     * this method getStatus
     * @author Tarik.Mikyas
     */
    public boolean getStatus() {
        return m_status;
    }

    /**
     * @param status <br>
     *               this method setStatus
     * @author Tarik.Mikyas
     */
    public void setStatus(boolean status) {
        this.m_status = status;
    }

    public Integer getTestStepId() {
        return m_stepId;
    }

    public void setTestStepId(Integer m_stepId) {
        this.m_stepId = m_stepId;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TestReport [m_stepId=" + m_stepId + ", m_stepName=" + m_stepName + ", m_formName=" + m_formName
                + ", m_message=" + m_message + ", m_statusMsg=" + m_statusMsg + ", m_status=" + m_status + "]";
    }

}
