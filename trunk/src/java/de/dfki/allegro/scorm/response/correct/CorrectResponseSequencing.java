/**
 * 
 */
package de.dfki.allegro.scorm.response.correct;

import de.dfki.allegro.scorm.response.ResponseSequencing;


/** A correct sequencing response according to the SCORM 2004
 *  specification.
 *  
 *  SCORM 2004 4th edition limits the number of elements
 *  of the list to 36. Duplicate entries are allowed.
 *  
 * @author Timo Scheuer
 *
 */
public class CorrectResponseSequencing extends ResponseSequencing implements
		CorrectResponse {

	/** Ctor.
	 * 
	 */
	public CorrectResponseSequencing() {
		super();
	}

	/** Ctor.
	 * 
	 * @param s  encoded <code>String</code>
	 */
	public CorrectResponseSequencing(String s) {
		super(s);
	}

}
