/**
 * 
 */
package de.dfki.allegro.scorm.response;

import de.dfki.allegro.scorm.token.InteractionType;

/** A true-false response according to the SCORM 2004
 *  specification.
 * 
 * @author Timo Scheuer
 *
 */
public class ResponseTrueFalse extends Response<Boolean> {

	/** Ctor.
	 * 
	 */
	public ResponseTrueFalse() {
		typeData = Boolean.TRUE;
	}

	/** Ctor.
	 * 
	 * @param s  encoded <code>String</code>
	 */
	public ResponseTrueFalse(String s) {
		typeData = "true".equals(s);
	}

	/** Get the interaction type.
	 *  
	 *  @return interaction type
	 */
	public InteractionType getType() {
		return InteractionType.TRUE_FALSE;
	}

	/** Set the response value.
	 * 
	 * @param v  response value
	 */
	public void setValue(Boolean v) {
		typeData = v;
	}

	/** Get <code>String</code> representation.
	 * 
	 * @return <code>String</code> representation.
	 */
	public String toString() {
		return typeData ? "true" : "false";
	}
}
