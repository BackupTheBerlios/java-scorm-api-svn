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


import de.dfki.allegro.scorm.annotation.ScormIdentifier;
import de.dfki.allegro.scorm.annotation.ScormSizeLimit;

/** Unique objective id.
 * 
 * @author Timo Scheuer
 *
 */
public class ObjectiveId extends ScormElement {

	
	/** Ctor.
	 * 
	 */
	ObjectiveId() {
	}

	/** Ctor.
	 * 
	 * @param k  SCORM access key
	 */
	ObjectiveId(String k) {
		super(k + ".");
	}

	/** Ctor.
	 * 
	 * @param k  SCORM access key
	 * @param i  the id of the new objective id
	 */
	ObjectiveId(String k, String i) {
		this(k);
		setId(i);
	}

	/** Set the id of the interaction. The id represents a URI.
	 *  Empty <code>String</code>s are not allowed. The id is the
	 *  first element to be set if you are creating a new interactions
	 *  entry.
	 *  SCORM 2004 4th edition limits the length to 4000 characters.
	 * 
	 * @param c  comment of the student.
	 */
	@ScormIdentifier
	@ScormSizeLimit(4000)
	void setId(String c) {
		ScormAdapter.adapter.setScormCharValue(
				key + "id", c.toString());
	}

	/** Get the id of the objective. The id represents a URI.
	 *  SCORM 2004 4th edition limits the length to 4000 characters.
	 * 
	 * @return the objective id
	 */
	public String getId() {
		return ScormAdapter.adapter.getScormCharValue(key + "id");
	}

}
