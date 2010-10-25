/** Copyright 2010 Timo Scheuer
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.dfki.allegro.scorm;


/** A learner attempt. During the lifetime of an attempt
 *  a student can sequentially start several learner
 *  sessions. A session has to be ended by the
 *  suspend() method if the student wishes to continue
 *  later resuming the state from the last learner session.
 * 
 * @author Timo Scheuer
 *
 */
public class Attempt implements ScormCommunicationElement {

	
	/** Ctor.
	 * 
	 */
	Attempt() {
	}

	
	/** Get the learner preferences of the actual attempt.
	 *  
	 * @return the learner preferences
	 * @see de.dfki.allegro.scorm.AttemptLearnerPreferences
	 */
	public AttemptLearnerPreferences getAttemptLearnerPreferences() {
		return ScormAdapter.getAttemptLearnerPreferencesInstance();
	}

	/** Get the objectives which are available during the actual
	 *  attempt. They can change from <code>CommunicationSession</code>
	 *  to <code>CommunicationSession</code>. This can happen
	 *  when updates of the sequencing (specified in the SCORM
	 *  package) are propagated to the objectives.
	 *  
	 *  The tracked data stored in the objectives is used for
	 *  sequencing.
	 *  
	 * @return the objectives
	 * @see de.dfki.allegro.scorm.Objectives
	 */
	public Objectives getObjectives() {
		return ScormAdapter.getObjectivesInstance();
	}

	/** Get the time object which encapsulates all time related
	 *  methods.
	 * 
	 * @return time
	 */
	public Time getTime() {
		return ScormAdapter.getTimeInstance();
	}

	/** Get the state object which encapsulates the state related
	 *  methods.
	 * 
	 * @return state
	 */
	public State getState() {
		return ScormAdapter.getStateInstance();
	}

	/** Suspend the actual learner attempt (which ends the learner
	 *  session). The attempt embracing the learner session is still
	 *  active. This method should be used if the student wishes to
	 *  continue the attempt later. The entry status is set
	 *  to "resume".
	 * 
	 *  This method sets the status of the attempt and have to
	 *  explicitly call the method <code>terminateCommunicationSession()</code>
	 *  of the actual <code>LoginSession</code> for cleanup before
	 *  leaving.
	 * @see de.dfki.allegro.scorm.LoginSession#terminateCommunicationSession()
	 */
	public void suspend() {
		ScormAdapter.adapter.setScormCharValue("cmi.exit", "suspend");
	}

	/** Logout is marked deprecated in the actual SCORM version.
	 *  Use the exit() method instead.
	 * 
	 *  This method sets the status of the attempt and have to
	 *  explicitly call the method <code>terminateCommunicationSession()</code>
	 *  of the actual <code>LoginSession</code> for cleanup before
	 *  leaving.
	 * @see de.dfki.allegro.scorm.LoginSession#terminateCommunicationSession()
	 * @deprecated
	 */
	public void logout() {
		ScormAdapter.adapter.setScormCharValue("cmi.exit", "logout");
	}

	/** Exit normally. This ends the learner session and
	 *  the learner attempt. The learner has met the requirements
	 *  defined by the SCO.
	 * 
	 *  This method sets the status of the attempt and have to
	 *  explicitly call the method <code>terminateCommunicationSession()</code>
	 *  of the actual <code>LoginSession</code> for cleanup before
	 *  leaving.
	 * @see de.dfki.allegro.scorm.LoginSession#terminateCommunicationSession()
	 */
	public void exit() {
		ScormAdapter.adapter.setScormCharValue("cmi.exit", "normal");
	}
	
	/** Aborts the actual learner attempt and the actual learner
	 *  session too. This ends the actual attempt and the actual session.
	 *  The exit state is undetermined.  
	 * 
	 *  This method sets the status of the attempt and have to
	 *  explicitly call the method <code>terminateCommunicationSession()</code>
	 *  of the actual <code>LoginSession</code> for cleanup before
	 *  leaving.
	 * @see de.dfki.allegro.scorm.LoginSession#terminateCommunicationSession()
	 */
	public void abort() {
		ScormAdapter.adapter.setScormCharValue("cmi.exit", "");
	}

}
