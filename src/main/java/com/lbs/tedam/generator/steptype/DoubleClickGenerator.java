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

import com.lbs.tedam.util.Enums.Regex;
import org.springframework.util.StringUtils;

public class DoubleClickGenerator extends Generator {

    private static final long serialVersionUID = 1L;
    private String itemTag = "";

    @Override
    public boolean validate() {
        if (itemTag == null || "".equals(itemTag.trim())) {
            return false;
        }
        return true;
    }

    @Override
    public String generate() {
        String parameter = "";
        if (!StringUtils.isEmpty(itemTag))
            parameter = itemTag;
        return parameter.replace(" ", Regex.SPACE.getRegex());
    }

    @Override
    public void degenerate(String parameter) {
        if (parameter != null) {
            parameter = parameter.replace(Regex.SPACE.getRegex(), " ");
        }
        if (StringUtils.isEmpty(parameter)) {
            return;
        }
        itemTag = parameter;
    }

    public String getItemTag() {
        return itemTag;
    }

    public void setItemTag(String itemTag) {
        this.itemTag = itemTag;
    }

}
