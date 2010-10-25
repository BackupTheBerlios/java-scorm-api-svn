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


import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import de.dfki.allegro.scorm.annotation.ScormSizeLimit;
import de.dfki.allegro.scorm.token.InteractionType;

/** A matching response according to the SCORM 2004
 *  specification.
 *  
 *  SCORM 2004 4th edition limits the number of elements
 *  of the unordered Collection of o<code>SingleMatch</code>es to 36.
 *  Duplicate entries are allowed.
 *  
 * @author Timo Scheuer
 *
 */
public class ResponseMatching extends Response<Collection<SingleMatch>> {

	/** Ctor.
	 * 
	 */
	public ResponseMatching() {
		typeData = new LinkedList<SingleMatch>();
	}

	/** Ctor.
	 * 
	 * @param s  encoded <code>String</code>
	 */
	public ResponseMatching(String s) {
		String[] a = s.split("\\[,\\]");
		typeData = new ArrayList<SingleMatch>(a.length);
		for (String sa : a)
			typeData.add(new SingleMatch(sa));
	}

	/** Get the interaction type.
	 *  
	 *  @return interaction type
	 */
	public InteractionType getType() {
		return InteractionType.MATCHING;
	}

	/** Set the response value.
	 * 
	 *  SCORM 2004 4th edition limits the number of elements
	 *  of the list to 36.
	 *  
	 * @param v  response value
	 * @throws de.dfki.allegro.scorm.ScormObjectSizeOutOfLimitException size limit exceeded
	 */
	@ScormSizeLimit(36)
	public void setValue(Collection<SingleMatch> v) {
		typeData = v;
	}

	/** Get <code>String</code> representation.
	 * 
	 * @return <code>String</code> representation.
	 */
	public String toString() {
		StringBuilder b = new StringBuilder();
		boolean first = true;
		for (SingleMatch i : typeData) {
			if (first)
				first = false;
			else
				b.append("[,]");
			b.append(i);
		}
		return b.toString();
	}

}
