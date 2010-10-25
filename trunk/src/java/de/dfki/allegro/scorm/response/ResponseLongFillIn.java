/**
 * 
 */
package de.dfki.allegro.scorm.response;


import de.dfki.allegro.scorm.annotation.ScormSizeLimit;
import de.dfki.allegro.scorm.token.InteractionType;
import de.dfki.allegro.scorm.util.LocalizedString;

/** A long fill-in response according to the SCORM 2004
 *  specification.
 *  
 *  SCORM 2004 4th edition limits the number of length
 *  of the response to 4000 characters.
 *  
 * @author Timo Scheuer
 *
 */
public class ResponseLongFillIn extends Response<LocalizedString> {

	/** Ctor.
	 * 
	 */
	public ResponseLongFillIn() {
		typeData = new LocalizedString("");
	}

	/** Ctor.
	 * 
	 * @param s  encoded <code>String</code>
	 */
	public ResponseLongFillIn(String s) {
		parse(s);
	}

	/** Get the interaction type.
	 *  
	 *  @return interaction type
	 */
	public InteractionType getType() {
		return InteractionType.LONG_FILL_IN;
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
	public void setValue(LocalizedString v) {
		typeData = v;
	}

	/** Get <code>String</code> representation.
	 * 
	 * @return <code>String</code> representation.
	 */
	public String toString() {
		return typeData.toString();
	}

	/** Parses an encoded <code>String</code> and fills
	 *  this object with the encoded data.
	 * 
	 */
	protected void parse(String s) {
		typeData = new LocalizedString(s);
	}
}
