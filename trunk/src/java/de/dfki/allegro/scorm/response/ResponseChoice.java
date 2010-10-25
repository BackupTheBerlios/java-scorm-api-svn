/**
 * 
 */
package de.dfki.allegro.scorm.response;



import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import de.dfki.allegro.scorm.ScormObjectSizeOutOfLimitException;
import de.dfki.allegro.scorm.annotation.ScormIdentifier;
import de.dfki.allegro.scorm.annotation.ScormSizeLimit;
import de.dfki.allegro.scorm.token.InteractionType;

/** A (multiple) choice response according to the SCORM 2004
 *  specification.
 *  
 *  SCORM 2004 4th edition limits the number of elements
 *  of the set of o<code>ShortIdentifiers</code> to 36.
 *  
 * @author Timo Scheuer
 *
 */
public class ResponseChoice extends Response<Set<String>> {

	
	/** Ctor.
	 * 
	 */
	public ResponseChoice() {
		typeData = new HashSet<String>();
	}

	/** Ctor.
	 * 
	 * @param s  encoded <code>String</code>
	 */
	public ResponseChoice(String s) {
		String[] array = s.split("\\[,\\]");
		typeData = new HashSet<String>(Arrays.asList(array));
	}

	/** Get the interaction type.
	 *  
	 *  @return interaction type
	 */
	public InteractionType getType() {
		return InteractionType.CHOICE;
	}

	/** Set the response value.
	 * 
	 *  SCORM 2004 4th edition limits the number of short identifiers
	 *  of the set to 36 and the size of each short identifier to
	 *  at most 250 characters.
	 *  
	 * @param v  response value
	 * @throws de.dfki.allegro.scorm.ScormObjectSizeOutOfLimitException size limit exceeded
	 */
	@ScormSizeLimit(36)
	public void setValue(Set<String> v) throws ScormObjectSizeOutOfLimitException {
		typeData = v;
	}

	/** Get <code>String</code> representation.
	 * 
	 * @return <code>String</code> representation.
	 */
	public String toString() {
		StringBuilder b = new StringBuilder();
		for (String i : typeData)
			addToStringArrayEncoding(i, b);
		return b.toString();
	}

	/** Add a character-based element to a <code>StringBuilder</code>
	 *  that is used for encoding a whole collection of this
	 *  character-based elements. Element separators are added
	 *  automatically.
	 *  
	 * @param b  the complete encoded collection (may be empty at start) 
	 * @param s  the <code>String</code> to add.
	 * @return the new <code>StringBuilder</code> with the given
	 *          <code>String</code> added.
	 */
	@ScormIdentifier
	@ScormSizeLimit(250)
	private static StringBuilder addToStringArrayEncoding(String s, StringBuilder b) {
		if (b.length() > 0)
			b.append("[,]");
		return b.append(s);
	}
	
}
