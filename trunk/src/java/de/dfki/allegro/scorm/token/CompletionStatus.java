/** Copyright 2010 Timo Scheuer
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
