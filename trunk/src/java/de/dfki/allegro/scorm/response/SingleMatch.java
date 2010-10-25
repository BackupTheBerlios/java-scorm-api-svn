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
package de.dfki.allegro.scorm.response;

import de.dfki.allegro.scorm.annotation.ScormIdentifier;
import de.dfki.allegro.scorm.annotation.ScormSizeLimit;

/** A single match is a single mapping from one
 * source short identifier to one target
 * short identifier.
 * 
 * SCORM 2004 4th edition limits the length of a
 * short identifier to 250 characters.
 *  
 * @author Timo Scheuer
 *
 */
public class SingleMatch {

	/** Source short identifier.*/
	private String source;
	/** Target short identifier.*/
	private String target;
	
	
	/** Ctor.
	 * 
	 * @param s  source short identifier
	 * @param t  target short identifier
	 */
	public SingleMatch(String s, String t) {
		setSource(s);
		setTarget(t);
	}

	/** Ctor.
	 * 
	 * @param e  encoded <code>String</code> that contains
	 *            both source and target
	 */
	public SingleMatch(String e) {
		String[] a = e.split("\\[.\\]");
		setSource(a[0]);
		setTarget(a[1]);
	}

	/** Get the source short identifier.
	 * 
	 * SCORM 2004 4th edition limits the length of a
	 * short identifier to 250 characters.
	 * 
	 * @return source short identifier
	 */
	public String getSource() {
		return source;
	}

	/** Set the source short identifier.
	 * 
	 * SCORM 2004 4th edition limits the length of a
	 * short identifier to 250 characters.
	 * 
	 * @param s  source short identifier
	 */
	@ScormSizeLimit(250)
	@ScormIdentifier
	public void setSource(String s) {
		source = s;
	}


	/** Get the target short identifier.
	 * 
	 * SCORM 2004 4th edition limits the length of a
	 * short identifier to 250 characters.
	 * 
	 * @return target short identifier
	 */
	public String getTarget() {
		return target;
	}

	/** Set the target short identifier.
	 * 
	 * SCORM 2004 4th edition limits the length of a
	 * short identifier to 250 characters.
	 * 
	 * @param t  target short identifier
	 */
	@ScormSizeLimit(250)
	@ScormIdentifier
	public void setTarget(String t) {
		target = t;
	}

	/** Get a <code>String</code> representation.
	 * 
	 * @return <code>String</code> representation.
	 */
	public String toString() {
		return source + "[.]" + target;
	}
}
