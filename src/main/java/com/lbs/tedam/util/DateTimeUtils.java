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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateTimeUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(DateTimeUtils.class);

	private DateTimeUtils() {
		// DateTimeUtils private constructor
	}

	/**
	 * This procedure converts the given String time value to date type according to the format and puts it in "H:m:s:S" format and converts it to string.
	 *
	 * @usedIn : Used in the formfill procedure.
	 * @author Tarik.Mikyas
	 */
	public static String getFormattedDateAsString(String time) {
		SimpleDateFormat sdf = null;
		Date date = null;
		String formattedTime = null;
		try {
			if (time.length() == 5) {
				sdf = new SimpleDateFormat("H:m");
				date = sdf.parse(time);
				formattedTime = sdf.format(date) + ":0:0";
			} else if (time.length() == 8) {
				sdf = new SimpleDateFormat("H:m:s");
				date = sdf.parse(time);
				formattedTime = sdf.format(date) + ":0";
			} else if (time.length() == 11) {
				sdf = new SimpleDateFormat("H:m:s:S");
				date = sdf.parse(time);
				formattedTime = sdf.format(date);
			}

		} catch (ParseException e) {
			LOGGER.error("{0}", e);
		}
		return formattedTime;
	}

	/**
	 * It is used to convert the incoming value in milliseconds to hours, minutes and seconds.
	 *
	 * @param count
	 * @return
	 */
	public static String formatTime(int count) {

		StringBuilder stringBuilder = new StringBuilder();
		int days = count / 86400;
		int hours = (count - days * 86400) / 3600;
		int minutes = (count - (days * 86400) - hours * 3600) / 60;
		int seconds = count - (days * 86400) - (hours * 3600) - (minutes * 60);
		if (days != 0) {
			stringBuilder.append(String.format("%01d", days) + "d ");
		}
		if (hours != 0) {
			stringBuilder.append(String.format("%01d", hours) + "h ");
		}
		if (minutes != 0) {
			stringBuilder.append(String.format("%01d", minutes) + "m ");
		}
		if (seconds != 0) {
			stringBuilder.append(String.format("%01d", seconds) + "s ");
		}
		return stringBuilder.toString();
	}

	public static String getTEDAMdbFormatSystemDateAsString() {
		DateFormat formatter = new SimpleDateFormat(Constants.TEDAM_DB_DATE_FORMAT);
		Date date = new Date();
		return formatter.format(date);
	}

	public static String getTEDAMFormatSystemDate() {
		Date date = new Date();
		DateFormat formatter = new SimpleDateFormat(Constants.TEDAM_DATE_FORMAT);
		return formatter.format(date);
	}

}
