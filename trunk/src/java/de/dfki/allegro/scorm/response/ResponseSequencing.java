/**
 * 
 */
package de.dfki.allegro.scorm.response;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import de.dfki.allegro.scorm.annotation.ScormIdentifier;
import de.dfki.allegro.scorm.annotation.ScormSizeLimit;
import de.dfki.allegro.scorm.token.InteractionType;


/** A sequencing response according to the SCORM 2004
 *  specification.
 *  
 *  SCORM 2004 4th edition limits the number of elements
 *  of the list to 36. Duplicate entries are allowed.
 *  
 * @author Timo Scheuer
 *
 */
public class ResponseSequencing extends Response<List<String>> {

	/** Ctor.
	 * 
	 */
	public ResponseSequencing() {
		typeData = new LinkedList<String>();
	}

	/** Ctor.
	 * 
	 * @param s  encoded <code>String</code>
	 */
	public ResponseSequencing(String s) {
		typeData = Arrays.asList(s.split("\\[,\\]"));
	}

	/** Get the interaction type.
	 *  
	 *  @return interaction type
	 */
	public InteractionType getType() {
		return InteractionType.SEQUENCING;
	}

	/** Set the response value.
	 * 
	 *  SCORM 2004 4th edition limits the number of elements
	 *  of the list to 36.
	 *  
	 * @param v  response value
	 * @throws de.dfki.allegro.scorm.ScormObjectSizeOutOfLimitException size limit exceeded
	 */
	@ScormSizeLimit(36)
	public void setValue(List<String> v) {
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
	@ScormSizeLimit(250)
	@ScormIdentifier
	private static StringBuilder addToStringArrayEncoding(String s, StringBuilder b) {
		if (b.length() > 0)
			b.append("[,]");
		return b.append(s);
	}
}
