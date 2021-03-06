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


/** All exceptions thrown by the SCORM package are of this type.
 * 
 * @author Timo Scheuer
 *
 */
abstract public class ScormException extends Exception {

	/** Serial version id. */
	private static final long serialVersionUID = 1L;

	
	/** Ctor.
	 * 
	 * @param message
	 */
	ScormException(String message) {
		super(message);
	}

	/** Ctor.
	 * 
	 * @param cause
	 */
	ScormException(Throwable cause) {
		super(cause);
	}

	/** Ctor.
	 * 
	 * @param message
	 * @param cause
	 */
	ScormException(String message, Throwable cause) {
		super(message, cause);
	}

}
