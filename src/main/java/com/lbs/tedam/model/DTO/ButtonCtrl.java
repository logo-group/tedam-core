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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tarik.Mikyas
 * @since 20 June 2016 11:21:04
 */
public class ButtonCtrl {

    /**
     * Holds the type information of the Button.
     */
    private String m_type;
    /**
     * The button holds the label information.
     */
    private String m_xuiDoc;
    /**
     * Holds the unique id (tag) information of the Button.
     */
    private String m_tag;
    /**
     * Holds the attribute of the Button (DBNEW, DBCLOSE ... etc).
     */
    private String m_attribute;
    /**
     * If the Button is a menuButton, it holds the tag values of the list of items that can be selected.
     */
    private List<Integer> m_menuButtonItemTags = new ArrayList<Integer>();
    /**
     * Holds the text values of the list of items that can be selected if Button is a menuButton.
     */
    private List<String> m_menuButtonItemTexts = new ArrayList<String>();

    /**
     *
     */
    public ButtonCtrl() {
        super();
    }

    /**
     * @param type
     * @param xuiDoc
     * @param tag
     */
    public ButtonCtrl(String type, String xuiDoc, String tag) {
        super();
        this.m_type = type;
        this.m_xuiDoc = xuiDoc;
        this.m_tag = tag;
    }

    /**
     * @return <br>
     * this method getType
     * @author Tarik.Mikyas
     */
    public String getType() {
        return m_type;
    }

    /**
     * @param type <br>
     *             this method setType
     * @author Tarik.Mikyas
     */
    public void setType(String type) {
        this.m_type = type;
    }

    /**
     * @return <br>
     * this method getXuiDoc
     * @author Tarik.Mikyas
     */
    public String getXuiDoc() {
        return m_xuiDoc;
    }

    /**
     * @param xuiDoc <br>
     *               this method setXuiDoc
     * @author Tarik.Mikyas
     */
    public void setXuiDoc(String xuiDoc) {
        this.m_xuiDoc = xuiDoc;
    }

    /**
     * @return <br>
     * this method getTag
     * @author Tarik.Mikyas
     */
    public String getTag() {
        return m_tag;
    }

    /**
     * @param tag <br>
     *            this method setTag
     * @author Tarik.Mikyas
     */
    public void setTag(String tag) {
        this.m_tag = tag;
    }

    /**
     * @return <br>
     * this method getAttribute
     * @author Tarik.Mikyas
     */
    public String getAttribute() {
        return m_attribute;
    }

    /**
     * @param attribute <br>
     *                  this method setAttribute
     * @author Tarik.Mikyas
     */
    public void setAttribute(String attribute) {
        this.m_attribute = attribute;
    }

    /**
     * @return <br>
     * this method getMenuButtonItemTagList
     * @author Tarik.Mikyas
     */
    public List<Integer> getMenuButtonItemTagList() {
        return m_menuButtonItemTags;
    }

    /**
     * @param mbItemNo <br>
     *                 this method setMenuButtonItemTagList
     * @author Tarik.Mikyas
     */
    public void setMenuButtonItemTagList(List<Integer> mbItemNo) {
        this.m_menuButtonItemTags = mbItemNo;
        clearButtonLists(m_menuButtonItemTexts, mbItemNo);
    }

    /**
     * @return <br>
     * this method getMenuButtonItemTextList
     * @author Tarik.Mikyas
     */
    public List<String> getMenuButtonItemTextList() {
        return m_menuButtonItemTexts;
    }

    /**
     * @param mbItemName <br>
     *                   this method setMenuButtonItemTextList
     * @author Tarik.Mikyas
     */
    public void setMenuButtonItemTextList(List<String> mbItemName) {
        this.m_menuButtonItemTexts = mbItemName;
        clearButtonLists(mbItemName, m_menuButtonItemTags);
    }

    /**
     * The resource records with empty "-" text values in the given lists are removed from the lists.
     *
     * @param nameList
     * @param itemList
     */
    public void clearButtonLists(List<String> nameList, List<Integer> itemList) {
        if (nameList.size() > 0 && itemList.size() > 0) {
            int i = 0;
            while (i < nameList.size()) {
                if ("-".equalsIgnoreCase(nameList.get(i)) || nameList.get(i).isEmpty()) {
                    m_menuButtonItemTexts.remove(i);
                    m_menuButtonItemTags.remove(i);
                    i--;
                }
                i++;
            }
        }
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "tag: " + m_tag + ", type: " + m_type + ", caption: " + m_xuiDoc + ", attribute: " + m_attribute + ", MenuButton item Tags: " + m_menuButtonItemTags
                + ", MenuButton item Texts: " + m_menuButtonItemTexts;
    }

}
