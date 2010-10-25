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
package de.dfki.allegro.scorm.response.correct;

import de.dfki.allegro.scorm.response.ResponseFillIn;


/** A correct fill-in response according to the SCORM 2004
 *  specification.
 *  
 *  SCORM 2004 4th edition limits the number of elements
 *  of the ordered list of o<code>LocalizedString</code>s to 10.
 *  Duplicate entries are allowed.
 *  
 * @author Timo Scheuer
 *
 */
public class CorrectResponseFillIn extends ResponseFillIn implements CorrectResponse {

	/** Flag that tells if case matters.*/
	private boolean caseMatters = false;
	/** Flag that tells if the order of the single entries matters.*/
	private boolean orderMatters = true;
	
	
	/** Ctor.
	 * 
	 */
	public CorrectResponseFillIn() {
		super();
	}

	/** Ctor.
	 * 
	 * @param s  encoded <code>String</code>
	 */
	public CorrectResponseFillIn(String s) {
		super(s);
	}

	/** Set a flag that decides whether case will matter.
	 *  If set to <code>true</code> learner responses that
	 *  use a different case are considered being not
	 *  correct. If set to <code>false</code> it does not
	 *  matter which case has been used in a learner
	 *  response. If the case in the learner response
	 *  differs and everything else is correct then the
	 *  response is considered being correct.
	 *   
	 * @param f  case flag
	 */
	public void setCaseFlag(boolean f) {
		caseMatters = f;
	}
	
	/** Set a flag that decides whether order will matter.
	 *  If set to <code>true</code> learner responses are
	 *  only considered being correct if, besides everything
	 *  else, the right order of the responses has been given.
	 *  If set to <code>false</code> it does not
	 *  matter which order has been used in a learner
	 *  response. If the order in the learner response
	 *  is not the same as in the <code>CorrectResponse</code>
	 *  and everything else is correct then the
	 *  response is considered being correct.
	 *   
	 * @param f  case flag
	 */
	public void setOrderFlag(boolean f) {
		orderMatters = f;
	}
	
	/** Check if case matters. Returns <code>true</code> iff
	 *  case matters else <code>false</code>. The default
	 *  value is <code>false</code>.
	 *  
	 * @return <code>true</code> iff case matters
	 */
	public boolean doesCaseMatter() {
		return caseMatters;
	}

	/** Check if order matters. Returns <code>true</code> iff
	 *  order matters else <code>false</code>. The default
	 *  value is <code>true</code>.
	 *  
	 * @return <code>true</code> iff order matters
	 */
	public boolean doesOrderMatter() {
		return orderMatters;
	}
	
	/** Get <code>String</code> representation.
	 * 
	 * @return <code>String</code> representation.
	 */
	public String toString() {
		return String.format("{case_matters=%s}{order_matters=%s}%s",
				Boolean.toString(caseMatters),
				Boolean.toString(orderMatters),
				super.toString());
	}

	/** Parses an encoded <code>String</code> and fills
	 *  this object with the encoded data.
	 * 
	 */
	protected void parse(String s) {
		String r = s;
		int i = r.indexOf('}');
		if (i>0) {
			if (r.startsWith("{case_matters=")) {
				caseMatters = Boolean.getBoolean(r.substring(14, i));
				r = s.substring(i + 1);
				i = r.indexOf('}');
				if (i>0) {
					if (r.startsWith("{order_matters=")) {
						orderMatters = Boolean.getBoolean(r.substring(15, i));
						r = s.substring(i + 1);
					}
				}
			} else if (r.startsWith("{order_matters=")) {
				orderMatters = Boolean.getBoolean(r.substring(15, i));
				r = s.substring(i + 1);
			}
		}
		super.parse(r);
	}
}
