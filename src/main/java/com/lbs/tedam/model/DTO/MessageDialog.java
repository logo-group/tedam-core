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

package com.lbs.tedam.model.DTO;

import com.lbs.tedam.util.Enums.Regex;

/**
 * @author Tarik.Mikyas
 * @since 20 June 2016 11:21:44
 */
public class MessageDialog {
    // The message displays information on pressing the OK or CANCEL key on the dialog.
    /**
     * boolean m_selection
     */
    private boolean m_selection;
    // Message dialog keeps text information if message is checked.
    /**
     * String m_message
     */
    private String m_message;

    /**
     *
     */
    public MessageDialog() {
        super();
    }

    /**
     * @param message
     * @param selection
     */
    public MessageDialog(String message, boolean selection) {
        super();
        this.m_selection = selection;
        this.m_message = message.replace(Regex.SPACE.getRegex(), " ");
    }

    /**
     * @return <br>
     * this method getSelection
     * @author Tarik.Mikyas
     */
    public boolean getSelection() {
        return m_selection;
    }

    /**
     * @param button <br>
     *               this method setSelection
     * @author Tarik.Mikyas
     */
    public void setSelection(boolean button) {
        this.m_selection = button;
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

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MessageDialog [m_selection=" + m_selection + ", m_message=" + m_message + "]";
    }

}
