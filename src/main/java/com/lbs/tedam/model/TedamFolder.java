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

import com.lbs.tedam.util.EnumsV2.TedamFolderType;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Where(clause = "IS_DELETED=0")
@Table(name = "TEDAM_FOLDER", uniqueConstraints = {@UniqueConstraint(columnNames = {"NAME", "PROJECT_ID", "FOLDER_TYPE"})})
public class TedamFolder extends AbstractBaseEntity {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1279539030870882339L;

    @Column(name = "NAME")
    @Size(min = 1, max = 255)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROJECT_ID")
    @Where(clause = "IS_DELETED=0")
    private Project project;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_FOLDER_ID")
    @Where(clause = "IS_DELETED=0")
    private TedamFolder parentFolder;

    @Column(name = "FOLDER_TYPE")
    private TedamFolderType folderType;

    /**
     * List<Job> jobList
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "TESTSET_FOLDER_ID")
    @Where(clause = "FOLDER_TYPE=0 AND IS_DELETED=0")
    private List<TestSet> testSets = new ArrayList<>();

    /**
     * List<Job> jobList
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "TESTCASE_FOLDER_ID")
    @Where(clause = "FOLDER_TYPE=1 AND IS_DELETED=0")
    private List<TestCase> testCases = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_FOLDER_ID")
    @Where(clause = "IS_DELETED=0")
    private List<TedamFolder> childFolders = new ArrayList<>();

    public TedamFolder() {
    }

    public TedamFolder(String name, TedamFolder parentFolder, Project project, TedamFolderType folderType) {
        super();
        this.name = name;
        this.parentFolder = parentFolder;
        this.project = project;
        this.folderType = folderType;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the parentFolder
     */
    public TedamFolder getParentFolder() {
        return parentFolder;
    }

    /**
     * @param parentFolder the parentFolder to set
     */
    public void setParentFolder(TedamFolder parentFolder) {
        this.parentFolder = parentFolder;
    }

    /**
     * @return the childFolders
     */
    public List<TedamFolder> getChildFolders() {
        return childFolders;
    }

    /**
     * @param childFolders the childFolders to set
     */
    public void setChildFolders(List<TedamFolder> childFolders) {
        this.childFolders = childFolders;
    }

    /**
     * @return the testSets
     */
    public List<TestSet> getTestSets() {
        return testSets;
    }

    /**
     * @param testSets the testSets to set
     */
    public void setTestSets(List<TestSet> testSets) {
        this.testSets = testSets;
    }

    /**
     * @return the testCases
     */
    public List<TestCase> getTestCases() {
        return testCases;
    }

    /**
     * @param testCases the testCases to set
     */
    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
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

    /**
     * @return the folderType
     */
    public TedamFolderType getFolderType() {
        return folderType;
    }

    /**
     * @param folderType the folderType to set
     */
    public void setFolderType(TedamFolderType folderType) {
        this.folderType = folderType;
    }

    @Override
    public String toString() {
        return name;
    }

    public String findParentFolder(TedamFolder tedamFolder) {
        String folderPath = "";
        if (tedamFolder.getParentFolder() != null) {
            folderPath += findParentFolder(tedamFolder.getParentFolder());
            folderPath += tedamFolder.getName() + "/";
        } else {
            folderPath += tedamFolder.getName() + "/";
        }
        return folderPath;
    }

}
