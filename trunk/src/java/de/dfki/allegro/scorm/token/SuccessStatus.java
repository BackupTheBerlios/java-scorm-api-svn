/**
 * 
 */
package de.dfki.allegro.scorm.token;

import de.dfki.allegro.scorm.ScormAdapter;

/** Indicates whether a learner has mastered an objective.
 * 
 * @author Timo Scheuer
 *
 */
public enum SuccessStatus {

	PASSED("passed"),
	FAILED("failed"),
	UNKNOWN("unknown");
	
	/** Objective status. */
	private String status;


	/** Get <code>String</code> representation.
	 * 
	 * @return <code>String</code> representation.
	 */
	public String toString() {
		return status;
	}
	
	/** Ctor.
	 * 
	 * @param s  the status
	 */
	private SuccessStatus(String s) {
		status = s;
	}
	
	/** Get the enum corresponding to a given <code>String</code>.
	 * 
	 * @param s  input <code>String</code>
	 * @return the corresponding enum type
	 */
	public static SuccessStatus getEnum(String s) {
		if (PASSED.status.equals(s))
			return PASSED;
		if (FAILED.status.equals(s))
			return FAILED;
		if (UNKNOWN.status.equals(s))
			return UNKNOWN;
		String error = "The value '" + s +
			"' is none of the allowed objective success status values (passed, failed or unknown)!";
		if (ScormAdapter.getStrictStandardHandling())
			throw new RuntimeException(error);
		System.err.println("Warning: " + error +
				" Using default value 'unknown'.");
		return UNKNOWN;
	}
}
