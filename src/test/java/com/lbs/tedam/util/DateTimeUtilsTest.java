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

package com.lbs.tedam.util;

import com.lbs.tedam.test.BaseServiceTest;
import org.junit.Test;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class DateTimeUtilsTest extends BaseServiceTest {

    @Test
    public void testFormatTime() {
        assertNotNull(DateTimeUtils.formatTime(396560));
    }

    @Test
    public void testFormatTimeZeroDay() {
        assertNotNull(DateTimeUtils.formatTime(86399));
    }

    @Test
    public void testFormatTimeZeroHour() {
        assertNotNull(DateTimeUtils.formatTime(3599));
    }

    @Test
    public void testFormatTimeZeroMinutes() {
        assertNotNull(DateTimeUtils.formatTime(59));
    }

    @Test
    public void testFormatTimeZeroSeconds() {
        assertNotNull(DateTimeUtils.formatTime(0));
    }

    @Test
    public void testGetFormattedDateAsString() {
        assertNotNull(DateTimeUtils.getFormattedDateAsString("12:34"));
    }

    @Test
    public void testGetFormattedDateAsString1() {
        assertNotNull(DateTimeUtils.getFormattedDateAsString("12:34:56"));
    }

    @Test
    public void testGetFormattedDateAsString2() {
        assertNotNull(DateTimeUtils.getFormattedDateAsString("12:34:56:78"));
    }

    @Test
    public void testGetFormattedDateAsStringNull() {
        assertNull(DateTimeUtils.getFormattedDateAsString("12:34:56:781"));
    }

    @Test
    public void testGetFormattedDateAsStringException() {
        assertNull(DateTimeUtils.getFormattedDateAsString("12:34:56:ab"));
    }

    @Test
    public void testGetTEDAMdbFormatSystemDateAsString() {
        assertNotNull(DateTimeUtils.getTEDAMdbFormatSystemDateAsString());
    }

    @Test
    public void testGetTEDAMFormatSystemDate() {
        assertNotNull(DateTimeUtils.getTEDAMFormatSystemDate());
    }

    @Test
    public void testLocalDateAttributeConverter() {
        LocalDateAttributeConverter converter = new LocalDateAttributeConverter();
        assertNotNull(converter.convertToDatabaseColumn(LocalDate.now()));
        assertNull(converter.convertToDatabaseColumn(null));
        assertNotNull(converter.convertToEntityAttribute(new Date(0L)));
        assertNull(converter.convertToEntityAttribute(null));
    }

    @Test
    public void testLocalDateTimeAttributeConverter() {
        LocalDateTimeAttributeConverter converter = new LocalDateTimeAttributeConverter();
        assertNotNull(converter.convertToDatabaseColumn(LocalDateTime.now()));
        assertNull(converter.convertToDatabaseColumn(null));
        assertNotNull(converter.convertToEntityAttribute(Timestamp.valueOf(LocalDateTime.now())));
        assertNull(converter.convertToEntityAttribute(null));
    }

}
