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
package de.dfki.allegro.scorm.response;

import de.dfki.allegro.scorm.token.InteractionType;

/** A true-false response according to the SCORM 2004
 *  specification.
 * 
 * @author Timo Scheuer
 *
 */
public class ResponseTrueFalse extends Response<Boolean> {

	/** Ctor.
	 * 
	 */
	public ResponseTrueFalse() {
		typeData = Boolean.TRUE;
	}

	/** Ctor.
	 * 
	 * @param s  encoded <code>String</code>
	 */
	public ResponseTrueFalse(String s) {
		typeData = "true".equals(s);
	}

	/** Get the interaction type.
	 *  
	 *  @return interaction type
	 */
	public InteractionType getType() {
		return InteractionType.TRUE_FALSE;
	}

	/** Set the response value.
	 * 
	 * @param v  response value
	 */
	public void setValue(Boolean v) {
		typeData = v;
	}

	/** Get <code>String</code> representation.
	 * 
	 * @return <code>String</code> representation.
	 */
	public String toString() {
		return typeData ? "true" : "false";
	}
}
