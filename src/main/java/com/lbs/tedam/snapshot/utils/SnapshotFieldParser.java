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

package com.lbs.tedam.snapshot.utils;

import com.lbs.tedam.model.FormField;

import java.util.ArrayList;
import java.util.List;

public class SnapshotFieldParser {
    private SnapshotFieldParser() {
    }

    public static List<FormField> checkFields(List<FormField> currentDBList, List<FormField> incomingFieldList,
                                              String version) {
        List<FormField> formFieldList = new ArrayList<FormField>();
        for (int i = 0; i < currentDBList.size(); i++) {
            if (!containsFields(currentDBList.get(i), incomingFieldList)) {
                // The record in the database does not exist in the newcomers.
                // status will be rolled out and will be posted to the fieldsForDB list.
                FormField formField = currentDBList.get(i);
                // if the status value of the last version record of the relevant field is NONE, no addition to the table is performed.
                if (formField.getStatus() == true) {
                    formField.setVersion(version);
                    formField.setStatus(false);
                    formField.setId(0);
                    formFieldList.add(formField);
                }
            }
        }
        for (int i = 0; i < incomingFieldList.size(); i++) {
            if (!containsFields(incomingFieldList.get(i), currentDBList)) {
                // newcomers do not have the record databased,
                // we go directly in.
                FormField formField = incomingFieldList.get(i);
                formField.setId(0);
                formFieldList.add(formField);
            }
        }
        for (int k = 0; k < currentDBList.size(); k++) {
            for (int i = 0; i < incomingFieldList.size(); i++) {
                // Do any of the fields in the incomingFieldList have
                //anything in our database?
                FormField formField = incomingFieldList.get(i);
                if (incomingFieldList.get(i).getTag().trim().equals(currentDBList.get(k).getTag().trim())) {
                    // Are there any mandatory changes from those?
                    if (incomingFieldList.get(i).getMandatory() == true && currentDBList.get(k).getMandatory() == false) {
                        // The area that was formerly mandatory NO is now YES.
                        // new registration mandatory YES done and version updated
                        // version updated
                        formField = incomingFieldList.get(i);
                        formField.setMandatory(true);
                        formField.setVersion(version);
                        formField.setId(0);
                        formFieldList.add(formField);
                    } else if (incomingFieldList.get(i).getMandatory() == false && currentDBList.get(k).getMandatory() == true) {
                        // In the past the mandatory YES field has now become NO.
                        // new registration to be made mandatory NO and
                        // updated version
                        formField = incomingFieldList.get(i);
                        formField.setMandatory(false);
                        formField.setVersion(version);
                        formField.setId(0);
                        formFieldList.add(formField);
                    }
                    // If there is a field with the same tag but different type in Db and newcomers, we update it. At the same time we look at
                    // the status of DB.
                    if (!incomingFieldList.get(i).getType().equals(currentDBList.get(k).getType()) && currentDBList.get(k).getStatus()) {
                        boolean alreadyAddedIntoFormFieldList = false;
                        for (int j = 0; j < formFieldList.size(); j++) {
                            if (formFieldList.get(j).getTag().equals(formField.getTag())) {
                                alreadyAddedIntoFormFieldList = true;
                                break;
                            }
                        }
                        if (!alreadyAddedIntoFormFieldList) {
                            formField = incomingFieldList.get(i);
                            formField.setVersion(version);
                            formField.setId(0);
                            formFieldList.add(formField);
                        }
                    }
                }
            }
        }
        return formFieldList;
    }

    /**
     * Looks for an element in the given list that has the tag value of the given formField.
     *
     * @param formField
     * @param list
     * @return
     * @author Ozgur.Ozbil
     */
    private static boolean containsFields(FormField formField, List<FormField> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getTag().trim().equals(formField.getTag().trim())) {
                return true;
            }
        }
        return false;
    }


}
