/**
 * 
 */
package de.dfki.allegro.scorm.response.correct;

import de.dfki.allegro.scorm.response.AbstractPerformanceStep;
import de.dfki.allegro.scorm.util.NumericRange;

/** A performance step comprises two parts: a step name
 *  and a step answer. Both are optional and can be
 *  empty. A step answer can be a character string or
 *  a real number range (but not both at the same time).
 * 
 * SCORM 2004 4th edition limits the length of a
 * step name and the length of a step answer (if it is
 * a character string) to 250 characters.
 *  
 * @author Timo Scheuer
 *
 */
public class PerformanceStepRange extends AbstractPerformanceStep<NumericRange> {

	
	/** Ctor.
	 * 
	 * @param e  encoded <code>String</code> that contains
	 *            both step name and answer (numerical range or
	 *            character string)
	 */
	public PerformanceStepRange(String e) {
		super(e);
	}

	/** Ctor.
	 * 
	 * @param n  step name
	 * @param a  character string answer
	 */
	public PerformanceStepRange(String n, String a) {
		super(n,a);
	}

	/** Ctor.
	 * 
	 * @param n  step name
	 * @param min  numerical answer minimum
	 * @param max  numerical answer maximum
	 */
	public PerformanceStepRange(String n, NumericRange r) {
		super(n, r);
	}

	/** Parse an encoded numerical and initialize this object
	 *  with the values from the <code>String</code>.
	 *  
	 * @param s  encoded <code>String</code>
	 */
	protected void parseNumerical(String s) {
		stepAnswerNumeric = new NumericRange(s);
	}
}
