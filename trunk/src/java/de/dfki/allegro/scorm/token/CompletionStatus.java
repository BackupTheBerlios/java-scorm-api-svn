/**
 * 
 */
package de.dfki.allegro.scorm.token;

import de.dfki.allegro.scorm.ScormAdapter;


/** The completion status of a SCO.
 * 
 * @author Timo Scheuer
 *
 */
public enum CompletionStatus {
	
	COMPLETED("completed"),
	INCOMPLETE("incomplete"),
	NOT_ATTEMPTED("not attempted"),
	UNKNOWN("unknown");
	

	/** Status of the actual token. */
	private String status;
	
	
	/** Ctor.
	 * 
	 * @param s  
	 */
	private CompletionStatus(String s) {
		status = s;
	}
	
	/** Get <code>String</code> representation.
	 * 
	 * @return <code>String</code> representation.
	 */
	public String toString() {
		return status;
	}
	
	/** Get the enum corresponding to a given <code>String</code>.
	 * 
	 * @param s  input <code>String</code>
	 * @return the corresponding enum type
	 */
	public static CompletionStatus getEnum(String s) {
		if (COMPLETED.status.equals(s))
			return COMPLETED;
		if (INCOMPLETE.status.equals(s))
			return INCOMPLETE;
		if (NOT_ATTEMPTED.status.equals(s))
			return NOT_ATTEMPTED;
		String error = "The value '" + s +
			"' is none of the allowed completion status values (completed, incomplete, not attempted or unknown)!";
		if (ScormAdapter.getStrictStandardHandling())
			throw new RuntimeException(error);
		System.err.println("Warning: " + error +
				" Using default value 'unknown'.");
		return UNKNOWN;
	}
}
