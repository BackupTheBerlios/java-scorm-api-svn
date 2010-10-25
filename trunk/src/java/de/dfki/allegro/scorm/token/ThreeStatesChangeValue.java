/**
 * 
 */
package de.dfki.allegro.scorm.token;

import de.dfki.allegro.scorm.ScormAdapter;


/** Encapsulates a change value that can have the
 *  three states <code>off</code>, <code>no_change</code>
 *  and <code>on</code>.
 * 
 * @author Timo Scheuer
 *
 */
public enum ThreeStatesChangeValue {

	OFF("-1"),
	NO_CHANGE("0"),
	ON("1");
	
	/** State of the actual token. */
	private String state;


	/** Get <code>String</code> representation.
	 * 
	 * @return <code>String</code> representation.
	 */
	public String toString() {
		return state;
	}
	
	/** Ctor.
	 * 
	 * @param s  the state
	 */
	private ThreeStatesChangeValue(String s) {
		state = s;
	}
	
	/** Get the enum corresponding to a given <code>String</code>.
	 * 
	 * @param s  input <code>String</code>
	 * @return the corresponding enum type
	 */
	public static ThreeStatesChangeValue getEnum(String s) {
		if (ON.state.equals(s))
			return ON;
		if (OFF.state.equals(s))
			return OFF;
		if (NO_CHANGE.state.equals(s))
			return NO_CHANGE;
		String error = "The value '" + s +
			"' is none of the allowed state values (-1, 0 or 1)!";
		if (ScormAdapter.getStrictStandardHandling())
			throw new RuntimeException(error);
		System.err.println("Warning: " + error +
				" Using default value 0 (no_change).");
		return NO_CHANGE;
	}

}
