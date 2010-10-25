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

import de.dfki.allegro.scorm.annotation.ScormSizeLimit;
import de.dfki.allegro.scorm.token.CreditSetting;
import de.dfki.allegro.scorm.token.EntryStatus;
import de.dfki.allegro.scorm.token.Mode;

/** The <code>State</code> bundles all state related methods
 *  that do not fit into any other more specialized classes.
 *  Some of the methods reflect fixed settings defined in the
 *  SCORM package. Another set of methods deals with attempt
 *  related session data which is can be used to save a state
 *  during an attempt but most importantly to resume the state
 *  of a previously suspended session. The "shared data" is
 *  used for sharing data between multiple SCOs.
 * 
 * @author Timo Scheuer
 *
 */
public class State {

	/** Ctor.
	 * 
	 */
	State() {
	}

	//-----  fixed data from the SCORM package  ------------------
	/** Get the launch data to initialize the SCO associated with
	 *  a learning activity. This data has to be provided by the
	 *  content designer and is included in the SCORM package. 
	 *  
	 *  SCORM 2004 4th edition limits the length to 4000 characters.
	 *  
	 *  @return launch data
	 */
	public String getScoLaunchData() {
		return ScormAdapter.adapter.getScormCharValue("cmi.launch_data");
	}
	
	/** Get the mode which indicates the SCO behavior after launch.
	 *  The mode is set in the SCORM package.
	 *  
	 *  @return mode
	 *  @see de.dfki.allegro.scorm.token.Mode
	 */
	public Mode getScoMode() {
		return Mode.getEnum(
				ScormAdapter.adapter.getScormCharValue("cmi.mode"));
	}

	/** Get the credit setting that indicates whether the student
	 *  will be credited for performance in the SCO. The credit
	 *  setting has to be defined in the SCORM package.
	 *  
	 *  @return completion status
	 *  @see de.dfki.allegro.scorm.token.CreditSetting
	 */
	public CreditSetting getScoCreditSetting() {
		return CreditSetting.getEnum(
				ScormAdapter.adapter.getScormCharValue("cmi.credit"));
	}

	//-----  attempt related data  ----------------------------------
	/** Get the entry status of the actual attempt. The entry status
	 *  indicates whether the student has suspended the SCO.
	 *  
	 *  @return entry status
	 *  @see de.dfki.allegro.scorm.token.EntryStatus
	 */
	public EntryStatus getAttemptEntryStatus() {
		return EntryStatus.getEnum(
				ScormAdapter.adapter.getScormCharValue("cmi.entry"));
	}

	/** Get the location of the SCO. This data can be used to
	 *  indicate a "bookmark" or "checkpoint" within the SCO.
	 *  It may be used as a starting point after resuming a
	 *  suspended learner attempt.
	 *  
	 *  SCORM 2004 4th edition limits the length to 1000 characters.
	 * 
	 * @return location at which the comment has been entered
	 */
	public String getAttemptLocation() {
		return ScormAdapter.adapter.getScormCharValue("cmi.location");
	}

	/** Set the location. This data can be used to
	 *  indicate a "bookmark" or "checkpoint" within the SCO.
	 *  It may be used as a starting point after resuming a
	 *  suspended learner attempt.
	 *  
	 *  SCORM 2004 4th edition limits the length to 1000 characters.
	 * 
	 * @param l  location at which the comment has been entered
	 */
	@ScormSizeLimit(1000)
	public void setAttemptLocation(String l) {
		ScormAdapter.adapter.setScormCharValue("cmi.location", l.toString());
	}

	/** Get the suspend data to resume the learner attempt from the
	 *  last learner session (in the last session the attempt has been
	 *  suspended).
	 *  
	 *  The data can also be stored to use later in the current learner
	 *  session. The suspend data filed should only be used if the
	 *  data does not fit into the location field.
	 *  
	 *  SCORM 2004 4th edition limits the length to 64000 characters.
	 *
	 * @return the suspend data
	 * @see de.dfki.allegro.scorm.Attempt#getLocation()
	 */
	public String getAttemptSuspendData() {
		return ScormAdapter.adapter.getScormCharValue("cmi.suspend_data");
	}

	/** Set the suspend data to resume the learner attempt from the
	 *  last learner session (in the last session the attempt has been
	 *  suspended).
	 *  
	 *  The data can also be stored to use later in the current learner
	 *  session. The suspend data filed should only be used if the
	 *  data does not fit into the location field.
	 *  
	 *  The suspend data may be discarded if the SCO calls the
	 *  <code>terminateCommunicationSession()</code> of a
	 *  <code>LoginSession</code> without having called the method
	 *  <code>suspend()</code> of the actual attempt.
	 *  
	 *  SCORM 2004 4th edition limits the length to 64000 characters.
	 *
	 * @param d the suspend data
	 * @see de.dfki.allegro.scorm.Attempt#setLocation(String)
	 * @see de.dfki.allegro.scorm.LoginSession#terminateCommunicationSession()
	 */
	@ScormSizeLimit(64000)
	public void setAttemptSuspendData(String d) {
		ScormAdapter.adapter.setScormCharValue("cmi.suspend_data", d.toString());
	}

	//-----  global data shared with other SCOs  --------------------------
	/** Get the shared data store which allows sharing data between multiple
	 *  SCOs. The scope can be configured in the SCORM package to survive
	 *  attempts or to be reset after each new attempt.
	 * 
	 * @return shared data store
	 */
	public SharedDataStore getSharedDataStore() {
		return ScormAdapter.getSharedDataStoreInstance();
	}
}
