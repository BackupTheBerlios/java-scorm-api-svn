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


import de.dfki.allegro.scorm.annotation.ScormSizeLimit;
import de.dfki.allegro.scorm.util.LocalizedString;

/** The objective is available during the actual
 *  attempt. They can change from <code>CommunicationSession</code>
 *  to <code>CommunicationSession</code>. This can happen
 *  when updates of the sequencing (specified in the SCORM
 *  package) are propagated to the objective.
 *  
 *  The tracked data stored in the objective is used for
 *  sequencing.
 *  
 *  Changes to this object are immediately written to the LMS.
 *  
 * @author Timo Scheuer
 *
 */
public class Objective extends BasicObjective {

	
	/** Ctor used for reading.
	 * 
	 * @param k  access key
	 */
	Objective(String k) {
		super(k);
	}

	/** Ctor used for creating a new objective. The objective
	 *  id is the first attribute that has to be written.
	 * 
	 * @param k  access key
	 * @param i  the objective id
	 */
	Objective(String k, String i) {
		super(k, i);
	}


	/** Get the description
	 *  SCORM 2004 4th edition limits the length to 250 characters.
	 * 
	 * @return the description
	 */
	public LocalizedString getDescription() {
		return new LocalizedString(
				ScormAdapter.adapter.getScormCharValue(
						key + "description"));
	}

	/** Set the description
	 *  SCORM 2004 4th edition limits the length to 250 characters.
	 * 
	 * @param d  the description
	 */
	@ScormSizeLimit(250)
	public void setDescription(LocalizedString d) {
		ScormAdapter.adapter.setScormCharValue(
				key + "description", d.toString());
	}

}
