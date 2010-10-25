package de.dfki.allegro.scorm;
/**
 * 
 */



import de.dfki.allegro.scorm.token.TimeLimitAction;
import de.dfki.allegro.scorm.util.TimeInterval;

/** This class bundles all methods that cope with time
 *  in any way.
 * 
 * @author Timo Scheuer
 *
 */
public class Time {

	
	/** Ctor.
	 * 
	 */
	Time() {
	}

	/** Get the predefined action that should be executed by the
	 *  SCO when the time limit is exceeded. The action is defined
	 *  in the SCORM package.
	 *  
	 * @return the action to execute when time limit is exceeded
	 * @see de.dfki.allegro.scorm.Attempt#getMaxTimeAllowed()
	 */
	public TimeLimitAction getTimeLimitAction() {
		return TimeLimitAction.getEnum(
				ScormAdapter.adapter.getScormCharValue("cmi.time_limit_action"));
	}

	/** Get the account of accumulated time the learner is allowed
	 *  to use the SCO in a single learner attempt.
	 *  
	 * @return maximum time allowed per learner attempt or
	 *          <code>null</code> if no maximum time limit has
	 *          been specified in the SCORM package
	 * @see de.dfki.allegro.scorm.util.TimeInterval
	 * @throws ScormDataAccessException an error is reported by a SCORM function
	 * @throws de.dfki.allegro.scorm.ScormEmptyAttributeException
	 *          you tried to access a value before it had been set to a valid value 
	 */
	public TimeInterval getMaxTimeAllowed() throws ScormDataAccessException, ScormEmptyAttributeException {
		return ScormAdapter.adapter.getScormTimeInterval(
					"cmi.max_time_allowed");
	}
	
	/** The actual learner attempt (and the actual learner session) will
	 *  be ended because of a time-out.
	 * 
	 *  This method sets the status of the attempt and have to
	 *  explicitly call the method <code>terminateCommunicationSession()</code>
	 *  of the actual <code>LoginSession</code> for cleanup before
	 *  leaving.
	 * @see de.dfki.allegro.scorm.LoginSession#terminateCommunicationSession()
	 */
	public void triggerTimeout() {
		ScormAdapter.adapter.setScormCharValue("cmi.exit", "time-out");
	}
	
	/** Set the actual time the learner has used. If this
	 *  method is called multiple times then the LMS will
	 *  use the value of the last call.
	 *  
	 * @param t  the session time used
	 */
	public void setSessionTime(TimeInterval t) {
		ScormAdapter.adapter.setScormCharValue("cmi.session_time", t.toString());
	}

	/** Get the total time used. This is the sum of all of the learner's
	 *  session times accumulated in the current learner attempt.
	 *  
	 * @return the total time
	 * @throws de.dfki.allegro.scorm.ScormFormatException the format returned
	 *                                                     by the LMS is not correct
	 *                                                     according to the SCORM
	 *                                                     2004 specification
	 * @throws ScormDataAccessException an error is reported by a SCORM function
	 * @throws de.dfki.allegro.scorm.ScormEmptyAttributeException
	 *          you tried to access a value before it had been set to a valid value 
	 */
	public TimeInterval getTotalTime() throws ScormFormatException, ScormDataAccessException, ScormEmptyAttributeException {
		return ScormAdapter.adapter.getScormTimeInterval(
			"cmi.total_time");
	}
}
