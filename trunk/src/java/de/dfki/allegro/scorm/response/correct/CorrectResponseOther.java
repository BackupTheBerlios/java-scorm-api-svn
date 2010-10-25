/**
 * 
 */
package de.dfki.allegro.scorm.response.correct;

import de.dfki.allegro.scorm.response.ResponseOther;

/** An other response according to the SCORM 2004
 *  specification.
 *  
 *  SCORM 2004 4th edition limits the number of characters
 *  to 4000.
 *  
 * @author Timo Scheuer
 *
 */
public class CorrectResponseOther extends ResponseOther implements CorrectResponse {

	/** Ctor.
	 * 
	 */
	public CorrectResponseOther() {
		super();
	}

	/** Ctor.
	 * 
	 * @param s  encoded <code>String</code>
	 */
	public CorrectResponseOther(String s) {
		super(s);
	}

}
