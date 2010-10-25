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
package de.dfki.allegro.scorm.response.correct;

import de.dfki.allegro.scorm.response.ResponseOther;

/** An other response according to the SCORM 2004
 *  specification.
 *  
 *  SCORM 2004 4th edition limits the number of characters
 *  to 4000.
 *  
 * @author Timo Scheuer
 *
 */
public class CorrectResponseOther extends ResponseOther implements CorrectResponse {

	/** Ctor.
	 * 
	 */
	public CorrectResponseOther() {
		super();
	}

	/** Ctor.
	 * 
	 * @param s  encoded <code>String</code>
	 */
	public CorrectResponseOther(String s) {
		super(s);
	}

}
