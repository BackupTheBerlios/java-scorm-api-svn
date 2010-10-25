package de.dfki.allegro.scorm;
/**
 * 
 */



/** You tried to add to more elements to a SCORM collection type
 *  than the SCORM specification requires as a limit for LMS. The
 *  collection type can be a <code>ScormString</code> (a string of
 *  characters) too.
 * 
 * @author Timo Scheuer
 *
 */
public class ScormObjectSizeOutOfLimitException extends RuntimeException {

	/** Serial version id. */
	private static final long serialVersionUID = 1L;

	
	/** Ctor.
	 *
	 * @param msg  error message
	 */
	public ScormObjectSizeOutOfLimitException(String msg) {
		super(msg);
	}

}
