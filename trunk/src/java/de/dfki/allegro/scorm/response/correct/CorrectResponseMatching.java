/**
 * 
 */
package de.dfki.allegro.scorm.response.correct;

import de.dfki.allegro.scorm.response.ResponseMatching;


/** A correct matching response according to the SCORM 2004
 *  specification.
 *  
 *  SCORM 2004 4th edition limits the number of elements
 *  of the unordered Collection of o<code>SingleMatch</code>es to 36.
 *  Duplicate entries are allowed.
 *  
 * @author Timo Scheuer
 *
 */
public class CorrectResponseMatching extends ResponseMatching implements
		CorrectResponse {

	/** Ctor.
	 * 
	 */
	public CorrectResponseMatching() {
		super();
	}

	/** Ctor.
	 * 
	 * @param s  encoded <code>String</code>
	 */
	public CorrectResponseMatching(String s) {
		super(s);
	}

}
