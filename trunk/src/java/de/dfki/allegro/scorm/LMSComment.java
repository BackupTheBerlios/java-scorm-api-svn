package de.dfki.allegro.scorm;
/**
 * 
 */



import java.util.Date;

import de.dfki.allegro.scorm.util.LocalizedString;


/** One LMS comment entry.
 * 
 * @author Timo Scheuer
 *
 */
public class LMSComment extends ScormElement {

	
	/** Ctor.
	 * 
	 * @param k  SCORM data access key
	 */
	LMSComment(String k) {
		super(k);
	}

	
	/** Get the LMS comment.
	 *  SCORM 2004 4th edition limits the length to 4000 characters.
	 * 
	 * @return LMS comment.
	 */
	public LocalizedString getComment() {
		return new LocalizedString(
				ScormAdapter.adapter.getScormCharValue(key + "comment"));
	}

	/** Get the location in the LMS at which the
	 *  comment has been entered.
	 *  SCORM 2004 4th edition limits the length to 250 characters.
	 * 
	 * @return location at which the comment has been entered
	 */
	public String getLocation() {
		return ScormAdapter.adapter.getScormCharValue(key + "location");
	}

	/** Get the time when the comment has been entered.
	 * 
	 * @return time when the comment has been entered.
	 * @throws de.dfki.allegro.scorm.ScormEmptyAttributeException  the timestamp has not
	 *                                                              been initialized yet to
	 *                                                              any value
	 */
	public Date getTimestamp() throws ScormEmptyAttributeException {
		return ScormAdapter.adapter.getScormTimestamp(key + "timestamp");
	}
}
