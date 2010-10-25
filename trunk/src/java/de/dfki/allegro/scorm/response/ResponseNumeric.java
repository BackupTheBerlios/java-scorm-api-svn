/**
 * 
 */
package de.dfki.allegro.scorm.response;


import de.dfki.allegro.scorm.token.InteractionType;

/** A numeric response according to the SCORM 2004
 *  specification.
 *  
 * @author Timo Scheuer
 *
 */
public class ResponseNumeric extends Response<Float> {

	/** Ctor.
	 * 
	 */
	public ResponseNumeric() {
		typeData = Float.NaN;
	}

	/** Ctor.
	 * 
	 * @param s  encoded <code>String</code>
	 */
	public ResponseNumeric(String s) {
		try {
			typeData = Float.valueOf(s);
		} catch (NumberFormatException e) {
			typeData = Float.NaN;
		}
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
	public void setValue(Float v) {
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
