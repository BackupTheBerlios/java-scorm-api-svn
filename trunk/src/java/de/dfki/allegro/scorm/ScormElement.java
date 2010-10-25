package de.dfki.allegro.scorm;
/**
 * 
 */


/** Base class of SCORM elements that are referenced by key.
 * 
 * @author Timo Scheuer
 *
 */
abstract class ScormElement {

	/** Access key of the character data. */
	protected String key;

	
	/** Ctor.
	 * 
	 */
	ScormElement() {
	}

	/** Ctor.
	 * 
	 * @param k  SCORM data access key
	 */
	ScormElement(String k) {
		key = k;
	}

	/** Get a <code>String</code> representation.
	 *  
	 *  @return Java <code>String</code> representation
	 */
	public String toString() {
		return "ScormObject(" + key + ")";
	}
	
}
