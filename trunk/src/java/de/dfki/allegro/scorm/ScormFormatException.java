package de.dfki.allegro.scorm;
/**
 * 
 */


/** The SCORM format used was not correct according to the
 *  specification.
 * 
 * @author Timo Scheuer
 *
 */
public class ScormFormatException extends RuntimeException {

	/** Serial version id. */
	private static final long serialVersionUID = 1L;

	
	/** Ctor.
	 * 
	 * @param message  error message
	 */
	ScormFormatException(String message) {
		super(message);
	}

	/** Ctor.
	 * 
	 * @param message  error message
	 * @param cause  a <code>Throwable</code> encapsulating more
	 *                details of the problem
	 */
	ScormFormatException(String message, Throwable cause) {
		super(message, cause);
	}

}
