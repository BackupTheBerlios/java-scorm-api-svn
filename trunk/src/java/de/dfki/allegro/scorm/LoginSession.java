package de.dfki.allegro.scorm;
/**
 * 
 */


import java.io.Serializable;



/** The login session comprises everything from login to logout.
 *  All learner attempts occur during the login session. Hence
 *  an attempt ends when a login session ends. Communication sessions
 *  happen during attempts.
 *  
 * 
 * @author Timo Scheuer
 *
 */
public class LoginSession implements Serializable {

	
	/** Serial version id.*/
	private static final long serialVersionUID = 1L;

	/** Ctor.
	 * 
	 */
	LoginSession() {
	}
	
	/** Initialize communication session.
	 * 
	 * @throws ScormDataAccessException  establishing the connection failed
	 */
	public void initializeCommunicationSession() throws ScormDataAccessException {
		try {
			ScormAdapter.adapter.callBooleanScormFunction("Initialize(\"\");");
		} catch (ScormDataAccessException e) {
			if (e.getErrorCode() == 103) {
				String error = "Initialize(\"\") already called!";
				if (ScormAdapter.getStrictStandardHandling()) {
					throw e;
				} else {
					// some LMS, for example Ilias, automatically call Initialize("")
					System.out.println("Warning: " + error);
				}
			} else
				throw e;
		}
		ScormAdapter.initCapabilities();
	}

	/** Terminate communication session. This method call causes also
	 *  the persistence of any data (see <code>commit()</code>). After
	 *  termination the session cannot be reopened.
	 * 
	 * @throws ScormDataAccessException  terminating the connection failed
	 * @see de.dfki.allegro.scorm.ScormAdapter#commit
	 */
	public void terminateCommunicationSession() throws ScormDataAccessException {
		ScormAdapter.adapter.callBooleanScormFunction("Terminate(\"\");");
	}

	/** Persist any data that changed since the last call of this
	 *  method or the last call of <code>initialize</code>.
	 * 
	 * @throws ScormDataAccessException  persisting the actual data failed
	 * @see de.dfki.allegro.scorm.ScormAdapter#initialize()
	 */
	public void commitCommunicationSession() throws ScormDataAccessException {
		ScormAdapter.adapter.callBooleanScormFunction("Commit(\"\");");
	}
	
	
	/** Get the data model version of the LMS.
	 * 
	 * @return data model version of the LMS
	 */
	public String getVersion() {
		return ScormAdapter.adapter.getScormCharValue("cmi._version");
	}

	/** Get access to the learner data.
	 *  
	 * @return the LMS learner data
	 */
	public Learner getLearner() {
		return ScormAdapter.getLearnerInstance();
	}
	
}
