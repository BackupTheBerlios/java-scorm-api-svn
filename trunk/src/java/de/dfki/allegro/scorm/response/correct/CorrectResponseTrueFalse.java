/**
 * 
 */
package de.dfki.allegro.scorm.response.correct;

import de.dfki.allegro.scorm.response.ResponseTrueFalse;

/** A correct true-false response according to the SCORM 2004
 *  specification.
 * 
 * @author Timo Scheuer
 *
 */
public class CorrectResponseTrueFalse extends ResponseTrueFalse implements CorrectResponse {

	/** Ctor.
	 * 
	 */
	public CorrectResponseTrueFalse() {
		super();
	}

	/** Ctor.
	 * 
	 * @param s  encoded <code>String</code>
	 */
	public CorrectResponseTrueFalse(String s) {
		super(s);
	}

}
