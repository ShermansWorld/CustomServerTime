package me.ShermansWorld.CustomServerTime.date;

import me.ShermansWorld.CustomServerTime.config.Config;

public class ServerDate {

	/**
	 * Data container for storing dates. Composed of a day, month and year. Dates
	 * are stored in date.yml.
	 */

	private int day;
	private ServerMonth month;
	private int year;

	public ServerDate() {
	}

	public ServerDate(int day, ServerMonth month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public String generateDate() {

		String dateFormat = Config.dateFormat;

		// Fill in placeholders
		if (dateFormat.contains("@DAY_SUFFIX")) {
			dateFormat = dateFormat.replaceAll("@DAY_SUFFIX", getDaySuffix());
		}
		if (dateFormat.contains("@DAY")) {
			dateFormat = dateFormat.replaceAll("@DAY", String.valueOf(day));
		}
		if (dateFormat.contains("@MONTH_NAME")) {
			dateFormat = dateFormat.replaceAll("@MONTH_NAME", month.getName());
		}
		if (dateFormat.contains("@MONTH_LENGTH")) {
			dateFormat = dateFormat.replaceAll("@MONTH_LENGTH", String.valueOf(month.getLength()));
		}
		if (dateFormat.contains("@MONTH")) {
			dateFormat = dateFormat.replaceAll("@MONTH", String.valueOf(month.getID()));
		}
		if (dateFormat.contains("@YEAR")) {
			dateFormat = dateFormat.replaceAll("@YEAR", String.valueOf(year));
		}

		return dateFormat;
	}

	// Private methods
	private String getDaySuffix() {
		String dayString = String.valueOf(day);
		switch (dayString.charAt(dayString.length() - 1)) {
		case '1':
			if (dayString.length() >= 2) {
				if (dayString.charAt(dayString.length() - 2) == '1') {
					return "th";
				}
			}
			return "st";
		case '2':
			if (dayString.length() >= 2) {
				if (dayString.charAt(dayString.length() - 2) == '1') {
					return "th";
				}
			}
			return "nd";
		case '3':
			if (dayString.length() >= 2) {
				if (dayString.charAt(dayString.length() - 2) == '1') {
					return "th";
				}
			}
			return "rd";
		default:
			return "th";
		}
	}

	// Getters and setters

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public ServerMonth getMonth() {
		return month;
	}

	public void setMonth(ServerMonth month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
