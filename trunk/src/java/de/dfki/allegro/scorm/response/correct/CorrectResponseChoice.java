/**
 * 
 */
package de.dfki.allegro.scorm.response.correct;


import de.dfki.allegro.scorm.response.ResponseChoice;

/** A correct (multiple) choice response according to the SCORM 2004
 *  specification.
 *  
 *  SCORM 2004 4th edition limits the number of elements
 *  of the set of o<code>ShortIdentifiers</code> to 36.
 *  
 * @author Timo Scheuer
 *
 */
public class CorrectResponseChoice extends ResponseChoice implements CorrectResponse {

	/** Ctor.
	 * 
	 */
	public CorrectResponseChoice() {
		super();
	}

	/** Ctor.
	 * 
	 * @param s  encoded <code>String</code>
	 */
	public CorrectResponseChoice(String s) {
		super(s);
	}

}
