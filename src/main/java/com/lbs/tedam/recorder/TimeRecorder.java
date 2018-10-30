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

package com.lbs.tedam.recorder;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * This class is to record time slice on any operation.
 */
public abstract class TimeRecorder<T extends TimeRecord> {

    /**
     * Recording flag.
     */
    private boolean recording = true;

    /**
     * Time record list.
     */
    private List<T> recordList = new LinkedList<>();

    public TimeRecorder() {
    }

    public TimeRecorder(boolean recording) {
        this.recording = recording;
    }

    /**
     * @return the recording
     */
    public boolean isRecording() {
        return recording;
    }

    /**
     * @param recording the recording to set
     */
    public void setRecording(boolean recording) {
        this.recording = recording;
    }

    /**
     * @return the recordList
     */
    public List<T> getRecordList() {
        return recordList;
    }

    /**
     * Runs Runnable instance and creates a TimeRecord instance adds it into record
     * list.
     *
     * @param runable
     */
    public void record(Runnable runable) {
        Date startDate = new Date();
        runable.run();
        Date endDate = new Date();
        if (isRecording()) {
            getRecordList().add(createTimeRecord(startDate, endDate));
        }
    }

    /**
     * Creates a new TimeRecord due to start date and end date.
     *
     * @param startDate Start date of TimeRecord.
     * @param endDate   To find duration.
     * @return New TimeRecord instance.
     */
    public abstract T createTimeRecord(Date startDate, Date endDate);

}
