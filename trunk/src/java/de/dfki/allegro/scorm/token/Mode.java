/**
 * 
 */
package de.dfki.allegro.scorm.token;

/** The mode indicates the SCO's behavior after launch.
 * 
 * @author Timo Scheuer
 *
 */
public enum Mode {
	BROWSE("browse"),
	NORMAL("normal"),
	REVIEW("review");
	
	/** State of the actual token. */
	private String mode;


	/** Get <code>String</code> representation.
	 * 
	 * @return <code>String</code> representation.
	 */
	public String toString() {
		return mode;
	}
	
	/** Ctor.
	 * 
	 * @param m  the mode
	 */
	private Mode(String m) {
		mode = m;
	}
	
	/** Get the enum corresponding to a given <code>String</code>.
	 * 
	 * @param s  input <code>String</code>
	 * @return the corresponding enum type
	 */
	public static Mode getEnum(String s) {
		if (NORMAL.mode.equals(s))
			return NORMAL;
		if (BROWSE.mode.equals(s))
			return BROWSE;
		if (REVIEW.mode.equals(s))
			return REVIEW;
		String error = "The value '" + s +
			"' is none of the allowed mode values ('browse', 'normal', 'review')!";
		throw new RuntimeException(error);
	}

}
