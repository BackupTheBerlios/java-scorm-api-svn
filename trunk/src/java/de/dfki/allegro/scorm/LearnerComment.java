/** Copyright 2010 Timo Scheuer
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.dfki.allegro.scorm;


import java.util.Date;

import de.dfki.allegro.scorm.annotation.ScormSizeLimit;
import de.dfki.allegro.scorm.util.LocalizedString;



/** Student comment which includes not
 *  only the comment but also the location (where in the content)
 *  and the time (when the comment was given).
 *  
 *  The value of this data model is intended to provide feedback about
 *  the SCO or the learning experience with the SCO from a specific
 *  student.
 * 
 * @author Timo Scheuer
 *
 */
public class LearnerComment extends ScormElement {
	
	
	/** Ctor.
	 * 
	 * @param k  SCORM data access key
	 * @param lc  the parent collection
	 */
	LearnerComment(String k) {
		super(k + ".");
	}

	/** Ctor.
	 * 
	 * @param k  SCORM data access key
	 * @param i  index of the new element to create
	 */
	LearnerComment(String k, int i) {
		this(k + i);
		// create a new empty comment to ensure that the given
		// index is valid after this constructor call
		ScormAdapter.adapter.setScormCharValue(key + "comment", "");
	}

	
	/** Get the student's comment.
	 *  SCORM 2004 4th edition limits the length to 4000 characters.
	 * 
	 * @return comment of the student.
	 */
	public LocalizedString getComment() {
		return new LocalizedString(
				ScormAdapter.adapter.getScormCharValue(
						key + "comment"));
	}

	/** Set the student's comment.
	 *  SCORM 2004 4th edition limits the length to 4000 characters.
	 * 
	 * @param c  comment of the student.
	 */
	@ScormSizeLimit(4000)
	public void setComment(LocalizedString c) {
		ScormAdapter.adapter.setScormCharValue(
				key + "comment", c.toString());
	}

	
	/** Get the location in the LMS at which the student
	 *  entered the comment.
	 *  SCORM 2004 4th edition limits the length to 250 characters.
	 * 
	 * @return location at which the comment has been entered
	 */
	public String getLocation() {
		return ScormAdapter.adapter.getScormCharValue(key + "location");
	}

	/** Set the location in the LMS at which the student
	 *  entered the comment.
	 *  SCORM 2004 4th edition limits the length to 250 characters.
	 * 
	 * @param l  location at which the comment has been entered
	 */
	@ScormSizeLimit(250)
	public void setLocation(String l) {
		ScormAdapter.adapter.setScormCharValue(key + "location", l.toString());
	}

	
	/** Get the time when the student entered the comment.
	 * 
	 * @return time when the student entered the comment
	 * @throws ScormEmptyAttributeException the timestamp value has
	 *                                       not yet been set
	 */
	public Date getTimestamp() throws ScormEmptyAttributeException {
		return ScormAdapter.adapter.getScormTimestamp(key + "timestamp");
	}

	/** Replace the <code>Date</code> by a new one.  This is
	 *  an optional method that is not supported by all
	 *  implementing classes.
	 * 
	 * @param d  replacement <code>Date</code>
	 * @throws ScormException  setting the new data and setting it to
	 *                           the LMS failed.
	 *                           Remark: Writing is postponed until the next call of
	 *                           <code>ScormAdapter.commit()</code> or
	 *                           <code>ScormAdapter.terminate()</code>.
	 * @see de.dfki.allegro.scorm.ScormAdapter#commit()
	 * @see de.dfki.allegro.scorm.ScormAdapter#terminate()
	 */
	public void setTimestamp(Date d) throws UnsupportedOperationException {
		ScormAdapter.adapter.setScormTimestamp(key + "timestamp", d);
	}

	/** Replace the <code>Date</code> by the actual date and time.  This is
	 *  an optional method that is not supported by all
	 *  implementing classes.
	 * 
	 * @throws ScormException  setting the new data and setting it to
	 *                           the LMS failed.
	 *                           Remark: Writing is postponed until the next call of
	 *                           <code>ScormAdapter.commit()</code> or
	 *                           <code>ScormAdapter.terminate()</code>.
	 * @see de.dfki.allegro.scorm.ScormAdapter#commit()
	 * @see de.dfki.allegro.scorm.ScormAdapter#terminate()
	 */
	public void setActualTimestamp() throws UnsupportedOperationException {
		setTimestamp(new Date());
	}

}
