/**
 * 
 */
package de.dfki.allegro.scorm.util;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** This class represents a time interval. Every value of the
 *  time interval, e.g. the minutes, is positive.
 * 
 *  Comparison of two time intervals is not defined in the
 *  SCORM specification. If want to compare two intervals
 *  you need to know the number of days a month should have.
 *  Selecting different start dates would yield different
 *  results of the comparison.
 * 
 * @author Timo Scheuer
 *
 */
public final class TimeInterval {

	/** Years. */
	private int year;
	/** Months. */
	private int month;
	/** Days.*/
	private int day;
	/** Hours. */
	private int hour;
	/** Minutes.*/
	private int minute;
	/** Seconds inluding fraction.*/
	private float second;
	
	
	/** Ctor of a time interval of the length 0.
	 * 
	 */
	public TimeInterval() {
	}

	/** Ctor.
	 * 
	 * @param s  time interval represented as <code>String</code>
	 * @throws ParseException  The format is not correct according to the
	 *                          SCORM specification
	 */
	public TimeInterval(String s) throws ParseException {
		setString(s);
	}

	/** Get the years of the time interval.
	 * 
	 * @return years of the time interval.
	 */
	public int getYear() {
		return year;
	}

	/** Set the years of the time interval.
	 * 
	 * @param val  years of the time interval.
	 */
	public void setYear(int val) {
		if (val<0)
			throw new IllegalArgumentException("The value '" + val +
					"' is negative. The time interval allow only positive values!");
		year = val;
	}

	/** Get the months of the time interval.
	 * 
	 * @return months of the time interval
	 */
	public int getMonth() {
		return month;
	}

	/** Set the months of the time interval.
	 * 
	 * @param val  months of the time interval
	 */
	public void setMonth(int val) {
		if (val<0)
			throw new IllegalArgumentException("The value '" + val +
					"' is negative. The time interval allow only positive values!");
		month = val;
	}

	/** Get the days of the time interval.
	 * 
	 * @return days of the time interval.
	 */
	public int getDay() {
		return day;
	}

	/** Set the days of the time interval.
	 * 
	 * @param val  days of the time interval
	 */
	public void setDay(int val) {
		if (val<0)
			throw new IllegalArgumentException("The value '" + val +
					"' is negative. The time interval allow only positive values!");
		day = val;
	}

	/** Get the hours of the time interval.
	 * 
	 * @return hours of the time interval
	 */
	public int getHour() {
		return hour;
	}

	/** Set the hours of the time interval.
	 * 
	 * @param val  hours of the time interval.
	 */
	public void setHour(int val) {
		if (val<0)
			throw new IllegalArgumentException("The value '" + val +
					"' is negative. The time interval allow only positive values!");
		hour = val;
	}

	/** Get the minutes of the time interval
	 * 
	 * @return minutes of the time interval
	 */
	public int getMinute() {
		return minute;
	}

	/** Set the minutes of the time interval.
	 * 
	 * @param val  minutes of the time interval
	 */
	public void setMinute(int val) {
		if (val<0)
			throw new IllegalArgumentException("The value '" + val +
					"' is negative. The time interval allow only positive values!");
		minute = val;
	}

	/** Get the seconds of the time interval including the
	 *  fractions of a second.
	 * 
	 * @return seconds of the time interval
	 */
	public float getSecond() {
		return second;
	}

	/** Set the seconds of the time interval.
	 * 
	 * @param val  seconds
	 */
	public void setSecond(float val) {
		if (val<0F)
			throw new IllegalArgumentException("The value '" + val +
					"' is negative. The time interval allow only positive values!");
		second = val;
	}

	/** Set all values of the time interval by parsing a
	 *  given string.
	 *  
	 * @param s  time interval <code>String</code>
	 * @throws ParseException  The format is not correct according to the
	 *                          SCORM specification
	 */
	public void setString(String s) throws ParseException {
		if (s.length() < 3)
			throw new IllegalArgumentException("The value '" + s +
				"' is is no valid time interval!");

		final Pattern p = Pattern.compile("P(\\d+Y)?(\\d+M)?(\\d+D)?(T(\\d+H)?(\\d+M)?(\\d+(?:\\.\\d{1,2})S)?)?");
		Matcher m = p.matcher(s);
		if (!m.matches())
			throw new ParseException("The timeinterval '" + s +
					"' does not match the format of the SCORM 2004 specification!", 0);
		int max = m.groupCount();
		for (int i=1; i<=max; i++) {
			String g = m.group(i);
			if (g == null)
				continue;
			if (g.charAt(0)!='T') {
				int val = Integer.parseInt(g.substring(0,g.length()-1));
				switch (g.charAt(g.length()-1)) {
				case 'Y':
					year = val;
					continue;
				case 'M':
					month = val;
					continue;
				case 'D':
					day = val;
					continue;
				}
			} else {
				for (++i; i<=max; i++) {
					g = m.group(i);
					if (g == null)
						continue;
					String val = g.substring(0, g.length() - 1);
					switch (g.charAt(g.length()-1)) {
					case 'H':
						hour = Integer.parseInt(val);
						continue;
					case 'M':
						minute = Integer.parseInt(val);
						continue;
					case 'S':
						second = Float.parseFloat(val);
						continue;
					}
				}
			}
		}
	}
	
	/** Get a <code>String</code> representation.
	 * 
	 * @return <code>String</code> representation
	 */
	public String toString() {
		StringBuilder b = new StringBuilder("P");
		if (year != 0)
			b.append(year).append('Y');
		if (month != 0)
			b.append(month).append('M');
		if (day != 0)
			b.append(day).append('D');
		StringBuilder b2 = new StringBuilder("T");
		if (hour != 0)
			b2.append(hour).append('H');
		if (minute != 0)
			b2.append(minute).append('M');
		if (second!=0F) {
			int sec = Float.valueOf(second).intValue();
			b2.append(sec);
			int frac = Float.valueOf((second - sec + 0.005F) * 100F).intValue();
			if (frac!=0)
				b2.append('.').append(frac);
			b2.append('S');
		}
		if (b2.length()>1)
			b.append(b2);
		// if everything is 0 then a single value has to be given
		if (b.length() == 1)
			b.append("0Y");
		return b.toString();
	}
}
