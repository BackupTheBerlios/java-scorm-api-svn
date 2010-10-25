/**
 * 
 */
package de.dfki.allegro.scorm.response.correct;

import de.dfki.allegro.scorm.response.ResponseLikert;

/** A correct likert response according to the SCORM 2004
 *  specification.
 *  
 *  SCORM 2004 4th edition limits the number of length
 *  of the response to 250 characters.
 *  
 * @author Timo Scheuer
 *
 */
public class CorrectResponseLikert extends ResponseLikert implements
		CorrectResponse {

	/** Ctor.
	 * 
	 */
	public CorrectResponseLikert() {
		super();
	}

	/** Ctor.
	 * 
	 * @param s  encoded <code>String</code>
	 */
	public CorrectResponseLikert(String s) {
		super(s);
	}

}
