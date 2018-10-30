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

import com.lbs.tedam.util.EnumsV2.TedamSocketMessageType;

public class TedamSocketMessage {

    private String detail;
    private TedamSocketMessageType tedamSocketMessageType;

    public TedamSocketMessage() {
        // An empty constructor is needed for all beans
    }

    public TedamSocketMessage(String detail, TedamSocketMessageType tedamSocketMessageType) {
        super();
        this.detail = detail;
        this.tedamSocketMessageType = tedamSocketMessageType;
    }

    /**
     * @return the detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * @param detail the detail to set
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * @return the tedamSocketMessageType
     */
    public TedamSocketMessageType getTedamSocketMessageType() {
        return tedamSocketMessageType;
    }

    /**
     * @param tedamSocketMessageType the tedamSocketMessageType to set
     */
    public void setTedamSocketMessageType(TedamSocketMessageType tedamSocketMessageType) {
        this.tedamSocketMessageType = tedamSocketMessageType;
    }
}
