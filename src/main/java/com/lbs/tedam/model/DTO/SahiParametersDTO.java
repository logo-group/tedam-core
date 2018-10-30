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

import com.lbs.tedam.util.Constants;
import com.lbs.tedam.util.Enums.StepTypeSahi;

import java.util.concurrent.atomic.AtomicInteger;

public class SahiParametersDTO {

    private static final AtomicInteger count = new AtomicInteger(0);

    private int order;

    private StepTypeSahi stepTypeSahi;

    private String stepTypeParameter;

    private String formName = Constants.EMPTY_STRING;

    private int testStepId = 0;

    public SahiParametersDTO() {
        order = count.incrementAndGet();
    }

    /**
     * this method getOrder <br>
     *
     * @return <br>
     * @author Canberk.Erkmen
     */
    public int getOrder() {
        return order;
    }

    /**
     * this method setOrder <br>
     *
     * @param order <br>
     * @author Canberk.Erkmen
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * this method getStepTypeSahi <br>
     *
     * @return <br>
     * @author Canberk.Erkmen
     */
    public StepTypeSahi getStepTypeSahi() {
        return stepTypeSahi;
    }

    /**
     * this method setStepTypeSahi <br>
     *
     * @param stepTypeSahi <br>
     * @author Canberk.Erkmen
     */
    public void setStepTypeSahi(StepTypeSahi stepTypeSahi) {
        this.stepTypeSahi = stepTypeSahi;
    }

    /**
     * this method getStepTypeParameter <br>
     *
     * @return <br>
     * @author Canberk.Erkmen
     */
    public String getStepTypeParameter() {
        return stepTypeParameter;
    }

    /**
     * this method setStepTypeParameter <br>
     *
     * @param stepTypeParameter <br>
     * @author Canberk.Erkmen
     */
    public void setStepTypeParameter(String stepTypeParameter) {
        this.stepTypeParameter = stepTypeParameter;
    }

    /**
     * this method getFormName <br>
     *
     * @return <br>
     * @author Canberk.Erkmen
     */
    public String getFormName() {
        return formName;
    }

    /**
     * this method setFormName <br>
     *
     * @param formName <br>
     * @author Canberk.Erkmen
     */
    public void setFormName(String formName) {
        this.formName = formName;
    }

    /**
     * this method getTestStepId <br>
     *
     * @return <br>
     * @author Canberk.Erkmen
     */
    public int getTestStepId() {
        return testStepId;
    }

    /**
     * this method setTestStepId <br>
     *
     * @param testStepId <br>
     * @author Canberk.Erkmen
     */
    public void setTestStepId(int testStepId) {
        this.testStepId = testStepId;
    }
}
