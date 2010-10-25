package de.dfki.allegro.scorm;
/**
 * 
 */


/** All exceptions thrown by the SCORM package are of this type.
 * 
 * @author Timo Scheuer
 *
 */
abstract public class ScormException extends Exception {

	/** Serial version id. */
	private static final long serialVersionUID = 1L;

	
	/** Ctor.
	 * 
	 * @param message
	 */
	ScormException(String message) {
		super(message);
	}

	/** Ctor.
	 * 
	 * @param cause
	 */
	ScormException(Throwable cause) {
		super(cause);
	}

	/** Ctor.
	 * 
	 * @param message
	 * @param cause
	 */
	ScormException(String message, Throwable cause) {
		super(message, cause);
	}

}
