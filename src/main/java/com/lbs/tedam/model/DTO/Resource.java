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

import com.lbs.tedam.util.Constants;

/**
 * It is used to hold values when the String inside the value of the attribute in the snapshot that has the Itemlist attribute is parse. (MenuButton,
 * CheckBoxGroup...etc) (itemList="All of|Those~1|Non~2")
 *
 * @author Tarik.Mikyas
 * @since 20 June 2016 11:26:07
 */
public class Resource {
    // Lambda pre-keeps the value of the specified text.
    /**
     * String m_description
     */
    private String m_description;
    // It holds the specified tag value after the lambda.
    /**
     * int m_tag
     */
    private int m_tag;

    /**
     *
     */
    public Resource() {
        m_description = null;
        m_tag = Constants.VALUE_NULL_INTEGER;
    }

    /**
     * @param description
     * @param tag
     */
    public Resource(String description, int tag) {
        super();
        this.m_description = description;
        this.m_tag = tag;
    }

    /**
     * @return <br>
     * this method getDescription
     * @author Tarik.Mikyas
     */
    public String getDescription() {
        return m_description;
    }

    /**
     * @param description <br>
     *                    this method setDescription
     * @author Tarik.Mikyas
     */
    public void setDescription(String description) {
        this.m_description = description;
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
        return "description: " + m_description + ", tag: " + m_tag;
    }

}
