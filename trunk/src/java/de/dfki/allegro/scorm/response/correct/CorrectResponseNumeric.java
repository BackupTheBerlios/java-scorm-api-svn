/**
 * 
 */
package de.dfki.allegro.scorm.response.correct;

import de.dfki.allegro.scorm.response.Response;
import de.dfki.allegro.scorm.token.InteractionType;
import de.dfki.allegro.scorm.util.NumericRange;

/** A correct numeric response range according to the SCORM 2004
 *  specification.
 *  
 * @author Timo Scheuer
 *
 */
public class CorrectResponseNumeric extends Response<NumericRange> implements CorrectResponse {

	
	/** Ctor.
	 * 
	 * @param min  minimum value
	 * @param max  maximum value
	 */
	public CorrectResponseNumeric(Float min, Float max) {
		typeData = new NumericRange(min, max);
	}

	/** Ctor.
	 * 
	 * @param s  encoded <code>String</code>
	 */
	public CorrectResponseNumeric(String s) {
		typeData = new NumericRange(s);
	}

	/** Get the interaction type.
	 *  
	 *  @return interaction type
	 */
	public InteractionType getType() {
		return InteractionType.NUMERIC;
	}

	/** Set the response value.
	 * 
	 * @param v  response value
	 * @throws de.dfki.allegro.scorm.ScormObjectSizeOutOfLimitException size limit exceeded
	 */
	public void setValue(NumericRange v) {
		typeData = v;
	}

	/** Get <code>String</code> representation.
	 * 
	 * @return <code>String</code> representation.
	 */
	public String toString() {
		return typeData.toString();
	}

}
