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

import de.dfki.allegro.scorm.annotation.ScormSizeLimit;

/** Base class of performance steps with a single numerical
 *  value and with range numerical values.
 * 
 * @author Timo Scheuer
 *
 */
public abstract class AbstractPerformanceStep<N> {

	/** Step name (also used as synchronization object).*/
	protected String stepName;
	/** Step answer (string; numerical is set to NaN).*/
	protected String stepAnswerString;
	/** Set numerical answer (range).*/
	protected N stepAnswerNumeric;

	
	/** Ctor.
	 * 
	 */
	public AbstractPerformanceStep() {
		this("", "");
	}

	/** Ctor.
	 * 
	 * @param e  encoded <code>String</code> that contains
	 *            both step name and answer (numerical or
	 *            character string)
	 */
	public AbstractPerformanceStep(String e) {
		String[] a = e.split("\\[.\\]");
		setStepName(a[0]);
		try {
			parseNumerical(a[1]);
		} catch (Exception pe) {
			setStepAnswerString(a[1]);
		}
	}

	/** Ctor.
	 * 
	 * @param n  step name
	 * @param a  character string answer
	 */
	public AbstractPerformanceStep(String n, String a) {
		setStepName(n);
		setStepAnswerString(a);
	}

	/** Ctor.
	 * 
	 * @param n  step name
	 * @param a  character string answer
	 */
	public AbstractPerformanceStep(String n, N v) {
		setStepName(n);
		setStepAnswerNumerical(v);
	}

	/** Get the performance step name. If not set then the
	 *  empty <code>String</code> is returned.
	 *  
	 * @return step name
	 */
	public String getStepName() {
		return stepName;
	}

	/** Set the step name.
	 * 
	 * SCORM 2004 4th edition limits the length of the
	 * character string to 250 characters.
	 * 
	 * @param n  step name  
	 */
	@ScormSizeLimit(250)
	public void setStepName(String n) {
		stepName = n;
	}


	/** Get the performance step name. If not set then the
	 *  empty <code>String</code> is returned.
	 *  
	 * @return step answer
	 */
	public String getStepAnswerString() {
		synchronized (stepName) {
			if (!isAnswerNumerical())
				throw new RuntimeException("You cannot get the answer string " +
						"of the performance step because it is numerical!");
			return stepAnswerString;
		}
	}

	/** Set the character string answer. This changes the type
	 *  to non-numerical.
	 * 
	 * SCORM 2004 4th edition limits the length of the
	 * character string to 250 characters.
	 * 
	 * @param a  character string answer  
	 */
	@ScormSizeLimit(250)
	public void setStepAnswerString(String a) {
		synchronized (stepName) {
			stepAnswerString = a;
			switchToStringData();
		}
	}

	/** Set the numerical answer or the numerical answer range
	 * (depends on the type parameter).
	 * 
	 * @param n  the numerical answer or the numerical answer range
	 *            (depends on the type parameter).
	 */
	public void setStepAnswerNumerical(N n) {
		synchronized (stepName) {
			stepAnswerNumeric = n;
			switchToNumericalData();
		}
	}

	/** Get the performance step numerical value
	 *  or the numerical range (depends on the type parameter).
	 *  
	 * @return step numerical value or numerical range
	 */
	public N getStepAnswerNumerical() {
		synchronized (stepName) {
			if (!isAnswerNumerical())
				throw new RuntimeException("You cannot get the numerical answer " +
						"of the performance step because it is not numerical!");
			return stepAnswerNumeric;
		}
	}


	/** Switch the type of the data to numerical.
	 * 
	 */
	protected void switchToNumericalData() {
		stepAnswerString = "";
	}

	/** Returns <code>true</code> iff the is given as numerical.
	 * 
	 * @return <code>true</code> iff the is given as numerical.
	 */
	public boolean isAnswerNumerical() {
		synchronized (stepName) {
			return stepAnswerNumeric == null;
		}
	}

	/** Switch the type of the data to character-based data.
	 * 
	 */
	protected void switchToStringData() {
		stepAnswerNumeric = null;
	}

	/** Get a <code>String</code> representation.
	 * 
	 * @return <code>String</code> representation.
	 */
	public String toString() {
		return stepName + "[.]" +
			(isAnswerNumerical() ? stepAnswerNumeric.toString() : stepAnswerString);
	}

	/** Parse an encoded numerical and initialize this object
	 *  with the values from the <code>String</code>.
	 *  
	 * @param s  encoded <code>String</code>
	 */
	abstract protected void parseNumerical(String s);
}
