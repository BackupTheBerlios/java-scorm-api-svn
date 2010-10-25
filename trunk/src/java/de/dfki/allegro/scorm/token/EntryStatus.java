/**
 * 
 */
package de.dfki.allegro.scorm.token;

import java.util.HashMap;
import java.util.Map;

/** The entry status tells you if the learner has accessed
 *  the SCO before.
 * 
 * @author Timo Scheuer
 *
 */
public enum EntryStatus {

	// There is a contradiction in the SCORM definition:
	// At one point the name is "ab_initio" and at another
	// point it is called "ab-initio" 
	/** Initial state which means that a clean data model is
	 *  accessed.
	 */
	INITIAL("ab_initio"),
	/** The SCO has already been accessed.*/
	RESUME("resume"),
	/** A non-determined state.*/
	UNKNOWN("");
	
	/** Mapping between the values and the enums.*/
	private static Map<String, EntryStatus> map;
	/** Status of the actual token. */
	private String status;


	static {
		EntryStatus[] a = EntryStatus.values();
		map = new HashMap<String, EntryStatus>(a.length + 1);
		for (EntryStatus s : a)
			map.put(s.toString(), s);
		map.put("ab-initio", INITIAL);
	}

	/** Get <code>String</code> representation.
	 * 
	 * @return <code>String</code> representation.
	 */
	public String toString() {
		return status.length()==0 ? "_nil_" : status;
	}
	
	/** Ctor.
	 * 
	 * @param s  the status
	 */
	private EntryStatus(String s) {
		status = s;
	}
	
	/** Get the enum corresponding to a given <code>String</code>.
	 * 
	 * @param s  input <code>String</code>
	 * @return the corresponding enum type
	 */
	public static EntryStatus getEnum(String s) {
		EntryStatus e = map.get(s);
		if (e == null)
			throw new RuntimeException("The value '" + s +
					"' is no valid entry status!");
		return e;
	}
}
