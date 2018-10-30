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

import org.junit.Test;

import static org.junit.Assert.*;

public class TimeRecorderTest {

    private static final int LOOP = 1000000;

    @Test
    public void testNoRecordByTestStepTimeRecorder() {
        TimeRecorder<TestStepTimeRecord> recorder = new TestStepTimeRecorder(false);
        recorder.record(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < LOOP; i++) {
                }
            }
        });
        assertEquals(recorder.getRecordList().size(), 0);
    }

    @Test
    public void testRecordByTestStepTimeRecorder() {
        TimeRecorder<TestStepTimeRecord> recorder = new TestStepTimeRecorder(true);
        recorder.record(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < LOOP; i++) {
                    System.out.println(i);
                }
            }
        });
        assertEquals(recorder.getRecordList().size(), 1);
        TimeRecord record = recorder.getRecordList().get(0);
        assertNotNull(record.getStartDate());
        assertNotEquals(record.getDuration(), 0);
    }

    @Test
    public void testRecordWithLoopByTestStepTimeRecorder() {
        TimeRecorder<TestStepTimeRecord> recorder = new TestStepTimeRecorder();
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < LOOP; i++) {
                }
            }
        };
        recorder.record(runnable);
        recorder.record(runnable);
        assertEquals(recorder.getRecordList().size(), 2);
    }

    /**
     * @author Seyma.Sahin
     */
    @Test
    public void testSetters() {
        TimeRecorder<TestStepTimeRecord> recorder = new TestStepTimeRecorder();
        recorder.setRecording(true);
    }

}
