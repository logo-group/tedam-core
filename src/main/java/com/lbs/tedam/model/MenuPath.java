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

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tarik.Mikyas<br>
 */
@Entity
@Where(clause = "IS_DELETED=0")
@Table(name = "MENU_PATH")
public class MenuPath extends AbstractBaseEntity {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = -77739509519579092L;

    /**
     * String caption
     */
    @Column(name = "MENU_TAG")
    private Integer menuTag;

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    @Where(clause = "IS_DELETED=0")
    private Project project;

    @OneToOne
    @JoinColumn(name = "PARENT_ID")
    @Where(clause = "IS_DELETED=0")
    private MenuPath parentMenuPath;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    @Where(clause = "IS_DELETED=0")
    private List<MenuPath> childMenuPaths = new ArrayList<>();

    @Transient
    private String caption;

    public MenuPath() {

    }

    /**
     * @param id
     * @param caption
     * @param parentId
     */
    public MenuPath(Integer menuTag, String caption, MenuPath parentmenMenuPath, Project project) {
        super();
        this.caption = caption;
        this.menuTag = menuTag;
        this.parentMenuPath = parentmenMenuPath;
        this.project = project;
    }

    /**
     * @return the menuTag
     */
    public Integer getMenuTag() {
        return menuTag;
    }

    /**
     * @param menuTag the menuTag to set
     */
    public void setMenuTag(Integer menuTag) {
        this.menuTag = menuTag;
    }

    /**
     * @return <br>
     * this method getCaption
     * @author Tarik.Mikyas
     */
    public String getCaption() {
        return caption;
    }

    /**
     * @param caption <br>
     *                this method setCaption
     * @author Tarik.Mikyas
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     * @return the parentMenuPath
     */
    public MenuPath getParentMenuPath() {
        return parentMenuPath;
    }

    /**
     * @param parentMenuPath the parentMenuPath to set
     */
    public void setParentMenuPath(MenuPath parentMenuPath) {
        this.parentMenuPath = parentMenuPath;
    }

    /**
     * @return the childMenuPaths
     */
    public List<MenuPath> getChildMenuPaths() {
        return childMenuPaths;
    }

    /**
     * @param childMenuPaths the childMenuPaths to set
     */
    public void setChildMenuPaths(List<MenuPath> childMenuPaths) {
        this.childMenuPaths = childMenuPaths;
    }

    /**
     * @return the project
     */
    public Project getProject() {
        return project;
    }

    /**
     * @param project the project to set
     */
    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return caption;
    }

}
