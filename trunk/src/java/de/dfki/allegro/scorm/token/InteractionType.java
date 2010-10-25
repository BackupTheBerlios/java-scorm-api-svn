/**
 * 
 */
package de.dfki.allegro.scorm.token;

import java.util.HashMap;
import java.util.Map;

import de.dfki.allegro.scorm.ScormAdapter;

/** All types of interaction which are defined by the
 *  SCORM.
 * 
 * @author Timo Scheuer
 *
 */
public enum InteractionType {

	TRUE_FALSE("true-false"),
	CHOICE("choice"),
	FILL_IN("fill-in"),
	LONG_FILL_IN("long_fill_in"),
	LIKERT("likert"),
	MATCHING("matching"),
	PERFORMANCE("performance"),
	SEQUENCING("sequencing"),
	NUMERIC("numeric"),
	OTHER("other");
	
	/** Mapping between the values and the enums.*/
	private static Map<String, InteractionType> map;
	/** The interaction type.*/
	private String type;
	
	
	static {
		InteractionType[] a = InteractionType.values();
		map = new HashMap<String, InteractionType>(a.length);
		for (InteractionType s : a)
			map.put(s.toString(), s);
	}


	/** Ctor.
	 * 
	 * @param t  the interaction type
	 */
	private InteractionType(String t) {
		type = t;
	}
	
	/** Get <code>String</code> representation.
	 * 
	 * @return <code>String</code> representation.
	 */
	public String toString() {
		return type;
	}
	
	
	/** Get the enum corresponding to a given <code>String</code>.
	 * 
	 * @param s  input <code>String</code>
	 * @return the corresponding enum type
	 */
	public static InteractionType getEnum(String s) {
		InteractionType e = map.get(s);
		if (e == null) {
			String error = "The value '" + s + "' is no valid interaction type! ";
			if (ScormAdapter.getStrictStandardHandling()) {
				throw new RuntimeException(error);
			} else {
				System.err.println("Warning: " + error +
						" Value has been set to 'other'.");
				e = OTHER;
			}
		}
		return e;
	}

}
