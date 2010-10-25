/**
 * 
 */
package de.dfki.allegro.scorm.response;


import de.dfki.allegro.scorm.annotation.ScormSizeLimit;
import de.dfki.allegro.scorm.token.InteractionType;

/** An other response according to the SCORM 2004
 *  specification.
 *  
 *  SCORM 2004 4th edition limits the number of characters
 *  to 4000.
 *  
 * @author Timo Scheuer
 *
 */
public class ResponseOther extends Response<String> {

	/** Ctor.
	 * 
	 */
	public ResponseOther() {
		typeData = "";
	}

	/** Ctor.
	 * 
	 * @param s  encoded <code>String</code>
	 */
	public ResponseOther(String s) {
		typeData = s;
	}

	/** Get the interaction type.
	 *  
	 *  @return interaction type
	 */
	public InteractionType getType() {
		return InteractionType.OTHER;
	}

	/** Set the response value.
	 * 
	 *  SCORM 2004 4th edition limits the number of characters
	 *  to 4000.
	 *  
	 * @param v  response value
	 * @throws de.dfki.allegro.scorm.ScormObjectSizeOutOfLimitException size limit exceeded
	 */
	@ScormSizeLimit(4000)
	public void setValue(String v) {
		typeData = v;
	}

	/** Get <code>String</code> representation.
	 * 
	 * @return <code>String</code> representation.
	 */
	public String toString() {
		return typeData;
	}
}
