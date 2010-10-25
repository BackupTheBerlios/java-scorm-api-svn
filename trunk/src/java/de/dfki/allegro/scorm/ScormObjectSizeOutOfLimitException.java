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


/** You tried to add to more elements to a SCORM collection type
 *  than the SCORM specification requires as a limit for LMS. The
 *  collection type can be a <code>ScormString</code> (a string of
 *  characters) too.
 * 
 * @author Timo Scheuer
 *
 */
public class ScormObjectSizeOutOfLimitException extends RuntimeException {

	/** Serial version id. */
	private static final long serialVersionUID = 1L;

	
	/** Ctor.
	 *
	 * @param msg  error message
	 */
	public ScormObjectSizeOutOfLimitException(String msg) {
		super(msg);
	}

}
