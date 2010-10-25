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


/** This exception is thrown if a certain attribute is accessed
 *  for reading but the attribute has not yet been initialized
 *  to any value and SCORM 2004 4th edition does not define a
 *  default value that can be mapped to Java without any problems.
 * 
 * @author Timo Scheuer
 *
 */
public class ScormEmptyAttributeException extends ScormException {

	/** Serial version id. */
	private static final long serialVersionUID = 1L;

	
	/** Ctor.
	 * 
	 * @param k  the key of the attribute
	 */
	public ScormEmptyAttributeException(String k) {
		super("The attribute '" + k + "' has not been initialized to any value");
	}
}
