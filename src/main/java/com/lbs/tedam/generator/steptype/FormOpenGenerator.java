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

/**
 *
 */
package com.lbs.tedam.generator.steptype;

import com.lbs.tedam.data.service.MenuPathService;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.MenuPath;
import com.lbs.tedam.util.Constants;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ahmet.Izgi
 */
public class FormOpenGenerator extends Generator {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private List<MenuPath> menuPathList = new ArrayList<>();
    private MenuPathService menuPathService;

    public FormOpenGenerator(MenuPathService menuPathService) {
        this.menuPathService = menuPathService;
    }

    @Override
    public boolean validate() {
        if (menuPathList.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public String generate() {
        String parameter = "[";
        for (MenuPath menuPath : menuPathList) {
            parameter += menuPath.getMenuTag();
            parameter += Constants.TEXT_COMMA;
        }
        parameter = parameter.substring(0, parameter.lastIndexOf(Constants.TEXT_COMMA)) + "]";
        return parameter;
    }

    @Override
    public void degenerate(String parameter) throws LocalizedException {
        if (StringUtils.isEmpty(parameter)) {
            return;
        }
        parameter = parameter.replace("[", "").replace("]", "");
        String[] splittedParameter = parameter.split(Constants.TEXT_COMMA);
        for (String param : splittedParameter) {
            MenuPath menuPath = menuPathService.getMenuPathByMenuTag(Integer.valueOf(param));
            menuPathList.add(menuPath);
        }
    }

    /**
     * @return the menuPathList
     */
    public List<MenuPath> getMenuPathList() {
        return menuPathList;
    }

    /**
     * @param menuPathList the menuPathList to set
     */
    public void addMenuPathList(MenuPath menuPath) {
        menuPathList.add(0, menuPath);
    }

    /**
     * reset the menuPathList
     */
    public void resetMenuPathList() {
        menuPathList = new ArrayList<>();
    }
}
