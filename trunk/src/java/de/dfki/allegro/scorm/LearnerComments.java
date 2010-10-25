package de.dfki.allegro.scorm;
/**
 * 
 */


import java.util.AbstractList;

import de.dfki.allegro.scorm.annotation.ScormSizeLimit;
import de.dfki.allegro.scorm.token.LMSCapability;

/** List of all learner comments which include not
 *  only the comment but also the location (where in the content)
 *  and the time (when the comment was given).
 *  
 *  The value of this data model is intended to provide feedback about
 *  the SCO or the learning experience with the SCO from a specific
 *  learner.
 *  
 *  SCORM 2004 4th edition limits the numbers of entries to 250.
 *  
 * @author Timo Scheuer
 *
 */
public class LearnerComments extends AbstractList<LearnerComment> {

	
	/** Ctor.
	 * 
	 */
	LearnerComments() {
	}

	/** Get an element of the collection at index <code>i</code> 
	 * 
	 * @param i  index of the element to retrieve
	 * @return the element at position <code>i</code>
	 * @exception ScormDataAccessException access to the collection element at index
	 *              <code>i</code> failed
	 */
	public LearnerComment get(int i) {
		try {
			return new LearnerComment("cmi.comments_from_learner." + i);
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
						"cmi.comments_from_learner._count"));
	}

	/** Create a new empty <code>LearnerComment</code>.
	 * 
	 *  SCORM 2004 4th edition limits the length to 250 elements.
	 *  
	 * @return The new element which is empty yet.
	 */
	@ScormSizeLimit(250)
	public synchronized LearnerComment createLearnerComment() {
		LearnerComment lc = new LearnerComment("cmi.comments_from_learner.", size());
		return lc;
	}


	/** Check if the LMS supports comments.
	 * 
	 * @return <code>true</code> iff the LMS supports comments.
	 */
	public static boolean supportsComment() {
		return ScormAdapter.supportsCapability(
				LMSCapability.COMMENT_FROM_LEARNER_COMMENT);
	}
	
	/** Check if the LMS supports locations.
	 * 
	 * @return <code>true</code> iff the LMS supports locations.
	 */
	public static boolean supportsLocation() {
		return ScormAdapter.supportsCapability(
				LMSCapability.COMMENT_FROM_LEARNER_LOCATION);
	}
	
	/** Check if the LMS supports timestamps.
	 * 
	 * @return <code>true</code> iff the LMS supports timestamps.
	 */
	public static boolean supportsTimestamp() {
		return ScormAdapter.supportsCapability(
				LMSCapability.COMMENT_FROM_LEARNER_TIMESTAMP);
	}

}
