/**
 * 
 */
package de.dfki.allegro.scorm.util;


import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/** <code>String</code>/SCORM date and time converter.
 * 
 * @author Timo Scheuer
 *
 */
public class ScormDateFormat extends DateFormat {

	/** Serial version id. */
	private static final long serialVersionUID = 1L;

	
	/** Ctor.
	 * 
	 */
	public ScormDateFormat() {
	}

	/** Formats a Date into a date/time string.
	 * 
	 * @param date  a Date to be formatted into a date/time string.
	 * @param toAppendTo  the string buffer for the returning date/time string.
	 * @param fieldPosition  keeps track of the position of the field within
	 *                        the returned string. On input: an alignment field,
	 *                        if desired. On output: the offsets of the alignment
	 *                        field. For example, given a time text "1996.07.10 AD
	 *                        at 15:08:56 PDT", if the given fieldPosition is
	 *                        DateFormat.YEAR_FIELD, the begin index and end index
	 *                        of fieldPosition will be set to 0 and 4, respectively.
	 *                        Notice that if the same time field appears more than
	 *                        once in a pattern, the fieldPosition will be set for
	 *                        the first occurrence of that time field. For instance,
	 *                        formatting a Date to the time string "1 PM PDT
	 *                        (Pacific Daylight Time)" using the pattern
	 *                        "h a z (zzzz)" and the alignment field
	 *                        DateFormat.TIMEZONE_FIELD, the begin index and end
	 *                        index of fieldPosition will be set to 5 and 8,
	 *                        respectively, for the first occurrence of the
	 *                        timezone pattern character 'z'.
	 * @return the string buffer passed in as toAppendTo, with formatted
	 *          text appended.
	 */
	public StringBuffer format(Date date, StringBuffer toAppendTo,
			FieldPosition fieldPosition) {
		toAppendTo.append(String.format("%1$tY-%1$tm-%1$tdT%1$tH:%1$tM:%1$tS.%1$tL%1$tz", date));
		// only two digits are allow for millisecond
		toAppendTo.deleteCharAt(22);
		// timezone uses a separator between hours and minutes
		toAppendTo.insert(25, ':');
		return toAppendTo;
	}

	/** Parse a date/time string according to the given parse
	 *  position. For example, a time text "07/10/96 4:5 PM, PDT"
	 *  will be parsed into a Date that is equivalent to Date(837039928046).
	 *  
	 *  By default, parsing is lenient: If the input is not in the
	 *  form used by this object's format method but can still be
	 *  parsed as a date, then the parse succeeds. Clients may
	 *  insist on strict adherence to the format by calling
	 *  setLenient(false).
	 *  
	 *  @param source  The date/time string to be parsed
	 *  @param pos  On input, the position at which to start
	 *               parsing; on output, the position at which
	 *               parsing terminated, or the start position
	 *               if the parse failed.
	 * @return A Date, or null if the input could not be parsed
	 */
	public Date parse(String source, ParsePosition pos) {
		int start = pos.getIndex();
		int end;
		GregorianCalendar c = new GregorianCalendar();
		int msw = 1;
		// accepts also some invalid formats with missing characters
		switch(source.length()) {
		case 28:
			end = 28;
			msw = 2;
		case 27:
			end = 27;
			int ms = Integer.parseInt(source.substring(start + 24 + msw, 26 + msw)) * 60000 +
				Integer.parseInt(source.substring(start + 21 + msw, 23 + msw)) * 3600000;
			c.set(Calendar.ZONE_OFFSET, source.charAt(start + 22) == '-' ? -ms : ms);
		case 22:
			end = 22;
			// UTC (z) has an offset of 0 ==> nothing to do
		case 21:
			end = 21;
			msw = 2;
			c.set(Calendar.MILLISECOND, Integer.parseInt(source.substring(start + 20, 20 + msw)));
		case 20:
		case 19:
			end = 19;
			c.set(Calendar.SECOND, Integer.parseInt(source.substring(start + 17, 19)));
		case 18:
		case 17:
		case 16:
			end = 16;
			c.set(Calendar.MINUTE, Integer.parseInt(source.substring(start + 14, 16)));
		case 15:
		case 14:
		case 13:
			end = 13;
			c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(source.substring(start + 11, 13)));
		case 12:
		case 11:
		case 10:
			end = 10;
			c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(source.substring(start + 8, 10)));
		case 9:
		case 8:
		case 7:
			end = 7;
			c.set(Calendar.MONTH, Integer.parseInt(source.substring(start + 5, 7)) - 1);
		case 6:
		case 5:
		default:
			end = 5;
			c.set(Calendar.YEAR, Integer.parseInt(source.substring(start + 0, 4)));
		}
		pos.setIndex(end);
		return c.getTime();
	}

}
