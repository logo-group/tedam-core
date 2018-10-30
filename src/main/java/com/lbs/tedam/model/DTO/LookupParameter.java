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

import com.lbs.tedam.util.Enums.ScriptParameters;

import java.util.HashMap;
import java.util.Map;

/**
 * Used for a single lookup definition.
 *
 * @author Tarik.Mikyas
 * @since 20 June 2016 11:21:43
 */
public class LookupParameter {
    // It keeps track of what type of lookup it is. i.e.: GridSearch
    /**
     * String m_lookupMethod
     */
    private String m_lookupMethod;
    // It holds the lookup parameters. i.e.: (gridTag, 400)
    /**
     * Map<ScriptParameters,String> m_paramMap
     */
    private Map<ScriptParameters, String> m_paramMap = new HashMap<ScriptParameters, String>();

    /**
     * @param lookupMethod
     */
    public LookupParameter(String lookupMethod) {
        this.m_lookupMethod = lookupMethod;
    }

    /**
     * @return <br>
     * this method getLookupMethod
     * @author Tarik.Mikyas
     */
    public String getLookupMethod() {
        return m_lookupMethod;
    }

    /**
     * @param lookupMethod <br>
     *                     this method setLookUpMethod
     * @author Tarik.Mikyas
     */
    public void setLookUpMethod(String lookupMethod) {
        this.m_lookupMethod = lookupMethod;
    }

    /**
     * @return <br>
     * this method getParameters
     * @author Tarik.Mikyas
     */
    public Map<ScriptParameters, String> getParameters() {
        return this.m_paramMap;
    }

    /**
     * @param scriptParam
     * @param value       <br>
     *                    this method addParameters
     * @author Tarik.Mikyas
     */
    public void addParameters(ScriptParameters scriptParam, String value) {
        m_paramMap.put(scriptParam, value);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "LookUp type: " + m_lookupMethod + ", parameters: " + m_paramMap;
    }

}
