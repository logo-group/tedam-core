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

package com.lbs.tedam.data.service;

import com.lbs.tedam.data.config.DataConfig;
import com.lbs.tedam.data.service.impl.MenuPathServiceImpl;
import com.lbs.tedam.data.service.impl.ProjectServiceImpl;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.MenuPath;
import com.lbs.tedam.model.Project;
import com.lbs.tedam.snapshot.utils.SnapShotUtils;
import com.lbs.tedam.test.BaseServiceTest;
import com.lbs.tedam.util.Enums.PathType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.xpath.XPathExpressionException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MenuPathServiceImpl.class, ProjectServiceImpl.class, TestDataConfig.class, DataConfig.class})
public class MenuPathServiceTest extends BaseServiceTest {

    @Autowired
    private MenuPathService menuPathService;

    @Autowired
    private ProjectService projectService;

    /**
     * this method test02Save <br>
     *
     * @throws LocalizedException
     * @author Tarik.Mikyas <br>
     */
    @Test
    public void test02Save() throws LocalizedException {

        Set<Integer> menuTagSet = new HashSet<>();
        SnapShotUtils snapshotUtils = new SnapShotUtils();
        List<List<Integer>> menuList = new ArrayList<>();
        try {
            menuList = snapshotUtils.createMenuPathList(PathType.TOTAL);
            for (List<Integer> list : menuList) {
                for (Integer integer : list) {
                    menuTagSet.add(integer);
                }
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        saveAllMenu(menuTagSet);
        saveAllMenuWithParent(menuList);
    }

    private void saveAllMenuWithParent(List<List<Integer>> menuList) throws LocalizedException {
        List<MenuPath> menuPathList = menuPathService.getAll();
        for (MenuPath menuPath : menuPathList) {
            Integer parentId = findParentId(menuPath.getMenuTag(), menuList);
            MenuPath parentMenuPath = findParent(menuPathList, parentId);
            menuPath.setParentMenuPath(parentMenuPath);
        }
//		 menuPathService.save(menuPathList);
    }

    private Integer findParentId(Integer menuTag, List<List<Integer>> menuList) {
        for (List<Integer> list : menuList) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(0).equals(menuTag)) {
                    return 0;
                }
                // If it is not the first element, the previous element is its parent.
                else if (menuTag.equals(list.get(i))) {
                    return list.get(i - 1);
                }
            }
        }
        return 0;
    }

    private MenuPath findParent(List<MenuPath> menuPathList, Integer parentId) {
        MenuPath parentMenu = findParentMenuPath(menuPathList, parentId);
        return parentMenu;
    }

    private void saveAllMenu(Set<Integer> menuTagSet) throws LocalizedException {
        List<Integer> menuTagList = menuTagSet.stream().sorted().collect(Collectors.toList());
        Project project = projectService.getAll().get(0);
        List<MenuPath> menuPathList = new ArrayList<>();
        for (Integer menuTag : menuTagList) {
            MenuPath menuPath = new MenuPath();
            menuPath.setMenuTag(menuTag);
            menuPath.setProject(project);
            menuPath.setCreatedUser("canberk.erkmen");
            menuPath.setDateCreated(LocalDateTime.now());
            menuPathList.add(menuPath);
        }
//		 menuPathService.save(menuPathList);
    }

    private MenuPath findParentMenuPath(List<MenuPath> menuPathList, Integer parentId) {
        for (MenuPath menuPath : menuPathList) {
            if (menuPath.getMenuTag().equals(parentId)) {
                return menuPath;
            }
        }
        return null;
    }

    @Test
    public void testGetMenuPathListByProject() throws LocalizedException {
        Project project = projectService.getAll().get(0);
        List<MenuPath> menuPathList = menuPathService.getMenuPathListByProject(project);
        Assert.assertNotEquals(menuPathList.size(), 0);
    }

    @Test
    public void testGetMenuPathByMenuTag() throws LocalizedException {
        MenuPath menuPath = menuPathService.getMenuPathByMenuTag(10100);
        Assert.assertNotNull(menuPath);
    }
}
