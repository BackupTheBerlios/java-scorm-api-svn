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


import java.util.AbstractList;

import de.dfki.allegro.scorm.token.LMSCapability;

/** Collection of all LMS comments which contain comments and
 *  annotations intended to be seen by all learners. This is
 *  a mechanism for adding information of interest to all learners
 *  in a particular community, instructor notes, etc.
 *  
 *  SCORM 2004 4th edition limits the numbers of entries to 100.
 *  The elements are read-only. The LMS is responsible for
 *  offering a means for managing the entries.
 * 
 * @author Timo Scheuer
 *
 */
public class LMSComments extends AbstractList<LMSComment> {

	
	/** Ctor.
	 * 
	 */
	LMSComments() {
	}
	
	/** Get an element of the collection at index <code>i</code> 
	 * 
	 * @param i  index of the element to retrieve
	 * @return the element at position <code>i</code>
	 * @exception ScormDataAccessException access to the collection element at index
	 *              <code>i</code> failed
	 */
	public LMSComment get(int i) {
		try {
			return new LMSComment("cmi.comments_from_lms." + i);
		} catch (ScormDataAccessException e) {
			if (e.getErrorCode() == 301)
				throw new IndexOutOfBoundsException("The collection holds " +
						size() + " elements. The index " + i +
						" is out if bounds!");
			throw e;
		}
	}

	/** Get the number of elements.
	 *  
	 *  @return number of elements of the set 
	 */
	public int size() {
		return Integer.parseInt(
				ScormAdapter.adapter.getScormCharValue(
						"cmi.comments_from_lms._count"));
	}

	/** Check if the LMS supports comments.
	 * 
	 * @return <code>true</code> iff the LMS supports comments.
	 */
	public static boolean supportsComment() {
		return ScormAdapter.supportsCapability(
				LMSCapability.COMMENT_FROM_LMS_COMMENT);
	}
	
	/** Check if the LMS supports locations.
	 * 
	 * @return <code>true</code> iff the LMS supports locations.
	 */
	public static boolean supportsLocation() {
		return ScormAdapter.supportsCapability(
				LMSCapability.COMMENT_FROM_LMS_LOCATION);
	}
	
	/** Check if the LMS supports timestamps.
	 * 
	 * @return <code>true</code> iff the LMS supports timestamps.
	 */
	public static boolean supportsTimestamp() {
		return ScormAdapter.supportsCapability(
				LMSCapability.COMMENT_FROM_LMS_TIMESTAMP);
	}

}
