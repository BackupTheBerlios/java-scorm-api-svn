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


import de.dfki.allegro.scorm.response.ResponseChoice;

/** A correct (multiple) choice response according to the SCORM 2004
 *  specification.
 *  
 *  SCORM 2004 4th edition limits the number of elements
 *  of the set of o<code>ShortIdentifiers</code> to 36.
 *  
 * @author Timo Scheuer
 *
 */
public class CorrectResponseChoice extends ResponseChoice implements CorrectResponse {

	/** Ctor.
	 * 
	 */
	public CorrectResponseChoice() {
		super();
	}

	/** Ctor.
	 * 
	 * @param s  encoded <code>String</code>
	 */
	public CorrectResponseChoice(String s) {
		super(s);
	}

}
