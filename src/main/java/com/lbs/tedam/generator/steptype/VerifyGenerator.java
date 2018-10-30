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

import com.lbs.tedam.data.service.SnapshotDefinitionService;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.util.Enums.Regex;
import com.lbs.tedam.util.EnumsV2.TestStepType;
import org.springframework.util.StringUtils;

/**
 * @author Ahmet.Izgi
 */
public class VerifyGenerator extends AbstractFillTestStepGenerator {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private String ignoreRowIndex = "0";

    public VerifyGenerator(SnapshotDefinitionService snapshotDefinitionService) {
        super(snapshotDefinitionService);
    }

    @Override
    public String generateLookUp() {
        return TestStepType.VERIFY.getBeginRegex() + generate() + TestStepType.VERIFY.getEndRegex();
    }

    @Override
    public void degenerateLookUp(String parameter) throws LocalizedException {
        String paramWithNoRegex = parameter.replaceAll(TestStepType.VERIFY.getBeginRegex(), "");
        degenerate(paramWithNoRegex);
    }

    @Override
    public String generate() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.valueOf(getSnapshotDefinition().getId()));
        builder.append(Regex.PARAMETER_SPLITTER.getRegex());
        builder.append(getIgnoreRowIndex());
        return builder.toString();
    }

    @Override
    public void degenerate(String parameter) throws LocalizedException {
        if (StringUtils.isEmpty(parameter)) {
            return;
        }
        String[] split = parameter.split(Regex.PARAMETER_SPLITTER.getRegex());
        if (split.length == 2) {
            setIgnoreRowIndex(split[1]);
        }
        super.degenerate(split[0]);
    }

    /**
     * @return the ignoreRowIndex
     */
    public String getIgnoreRowIndex() {
        return ignoreRowIndex;
    }

    /**
     * @param ignoreRowIndex the ignoreRowIndex to set
     */
    public void setIgnoreRowIndex(String ignoreRowIndex) {
        this.ignoreRowIndex = ignoreRowIndex;
    }

}
