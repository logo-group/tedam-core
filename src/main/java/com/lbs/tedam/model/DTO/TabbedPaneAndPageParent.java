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

/**
 * It is used when it is desired to keep hierarchical tab data.
 *
 * @author Tarik.Mikyas
 * @since 20 June 2016 11:27:43
 */
public class TabbedPaneAndPageParent {
    // Index value of tab
    /**
     * String m_value
     */
    private String m_value;
    // Tag of tabbedPane
    /**
     * int m_tag
     */
    private int m_tag;

    /**
     * @return <br>
     * this method getValue
     * @author Tarik.Mikyas
     */
    public String getValue() {
        return m_value;
    }

    /**
     * @param value <br>
     *              this method setValue
     * @author Tarik.Mikyas
     */
    public void setValue(String value) {
        this.m_value = value;
    }

    /**
     * @return <br>
     * this method getTag
     * @author Tarik.Mikyas
     */
    public int getTag() {
        return m_tag;
    }

    /**
     * @param tag <br>
     *            this method setTag
     * @author Tarik.Mikyas
     */
    public void setTag(int tag) {
        this.m_tag = tag;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "value: " + m_value + ", tag: " + m_tag;
    }

}
