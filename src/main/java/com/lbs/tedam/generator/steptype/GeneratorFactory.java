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

package com.lbs.tedam.generator.steptype;

import com.lbs.tedam.data.service.MenuPathService;
import com.lbs.tedam.data.service.SnapshotDefinitionService;
import com.lbs.tedam.util.EnumsV2.TestStepType;
import org.springframework.beans.factory.BeanFactory;

/**
 * @author Ahmet.Izgi
 */
public class GeneratorFactory {

    private GeneratorFactory() {

    }

    public static Generator getGenerator(TestStepType testStepType, BeanFactory beanFactory) {
        switch (testStepType) {
            case FORM_OPEN:
                return new FormOpenGenerator(beanFactory.getBean(MenuPathService.class));
            case FORM_OPEN_SHORTCUT:
                return new FormOpenShortcutGenerator();
            case BUTTON_CLICK:
                return new ButtonClickGenerator();
            case FORM_FILL:
                return new FormFillGenerator(beanFactory.getBean(SnapshotDefinitionService.class));
            case FILTER_FILL:
                return new FilterFillGenerator(beanFactory.getBean(SnapshotDefinitionService.class));
            case VERIFY:
                return new VerifyGenerator(beanFactory.getBean(SnapshotDefinitionService.class));
            case MESSAGE_VERIFY:
                return new MessageVerifyGenerator();
            case ROW_COUNT_VERIFY:
                return new RowCountVerifyGenerator();
            case GRID_SEARCH:
                return new GridSearchGenerator();
            case GRID_CELL_SELECT:
                return new GridCellSelectGenerator();
            case GRID_ROW_SELECT:
                return new GridRowSelectGenerator();
            case GRID_DOUBLE_CLICK:
                return new GridDoubleClickGenerator();
            case GRID_DELETE:
                return new GridDeleteGenerator();
            case POPUP:
                return new PopupGenerator();
            case REPORT:
                return new ReportGenerator();
            case DOUBLE_CLICK:
                return new DoubleClickGenerator();
            case WAIT:
                return new WaitGenerator();
            default:
                break;
        }
        return null;
    }

    public static boolean validate(Generator generator) {
        return generator.validate();
    }

}
