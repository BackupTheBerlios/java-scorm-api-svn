package de.dfki.allegro.scorm;
/**
 * 
 */


/** This exception is thrown if a certain attribute is accessed
 *  for reading but the attribute has not yet been initialized
 *  to any value and SCORM 2004 4th edition does not define a
 *  default value that can be mapped to Java without any problems.
 * 
 * @author Timo Scheuer
 *
 */
public class ScormEmptyAttributeException extends ScormException {

	/** Serial version id. */
	private static final long serialVersionUID = 1L;

	
	/** Ctor.
	 * 
	 * @param k  the key of the attribute
	 */
	public ScormEmptyAttributeException(String k) {
		super("The attribute '" + k + "' has not been initialized to any value");
	}
}
