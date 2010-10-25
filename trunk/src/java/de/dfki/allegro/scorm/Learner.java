package de.dfki.allegro.scorm;
/**
 * 
 */


import java.io.Serializable;

import de.dfki.allegro.scorm.util.LocalizedString;

/** Predefined data which is set via LMS or via SCORM package.
 *  This data cannot be changed by the SCO and is read-only.
 * 
 * @author Timo Scheuer
 *
 */
public class Learner implements Serializable {

	
	/** Serial version id.*/
	private static final long serialVersionUID = 1L;

	/** Ctor.
	 * 
	 */
	Learner() {
	}

	/** Get the learner LMS identification. The learner is the person
	 *  on behalf of whom the SCO was launched.
	 *  
	 *  SCORM 2004 4th edition limits the length to 4000 characters.
	 * 
	 * @return learner id.
	 */
	public String getLearnerId() {
		return ScormAdapter.adapter.getScormCharValue("cmi.learner_id");
	}

	/** Get the name of the learner. The name is set via the LMS.
	 * 
	 *  SCORM 2004 4th edition limits the length to 250 characters.
	 * 
	 * @return learner name.
	 * @see de.dfki.allegro.scorm.util.LocalizedString
	 */
	public LocalizedString getLearnerName() {
		return new LocalizedString(
				ScormAdapter.adapter.getScormCharValue(
						"cmi.learner_name"));
	}

	/** Get a collection of all LMS comments which include not
	 *  only the comment but also the location (where in the content)
	 *  and the time (when the comment was given).
	 *  
	 * @return collection of LMS comments
	 * @see de.dfki.allegro.scorm.LMSComments
	 */
	public LMSComments getLMSComments() {
		return ScormAdapter.getLMSCommentsInstance();
	}

	/** Get a collection of all LMS comments which include not
	 *  only the comment but also the location (where in the content)
	 *  and the time (when the comment was given).
	 *  
	 * @return collection of learner comments
	 */
	public LearnerComments getLearnerComments() {
		return ScormAdapter.getLearnerCommentsInstance();
	}

	/** Get the object that manages the actual attempt.
	 *  
	 * @return the actual attempt
	 */
	public Attempt getActualAttempt() {
		return ScormAdapter.getAttemptInstance();
	}
	
	/** Get a collection of all learner interactions.
	 *  
	 * @return collection of learner interactions
	 */
	public Interactions getInteractions() {
		return ScormAdapter.getInteractionsInstance();
	}

}
