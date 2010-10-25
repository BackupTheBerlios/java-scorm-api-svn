/**
 * 
 */
package de.dfki.allegro.scorm.response;


import java.util.ArrayList;
import java.util.List;

import de.dfki.allegro.scorm.annotation.ScormSizeLimit;
import de.dfki.allegro.scorm.token.InteractionType;
import de.dfki.allegro.scorm.util.LocalizedString;

/** A fill-in response according to the SCORM 2004
 *  specification.
 *  
 *  SCORM 2004 4th edition limits the number of elements
 *  of the ordered list of o<code>LocalizedString</code>s to 10.
 *  Duplicate entries are allowed.
 *  
 * @author Timo Scheuer
 *
 */
public class ResponseFillIn extends Response<List<LocalizedString>> {

	/** Ctor.
	 * 
	 */
	public ResponseFillIn() {
		typeData = new ArrayList<LocalizedString>();
	}

	/** Ctor.
	 * 
	 * @param s  encoded <code>String</code>
	 */
	public ResponseFillIn(String s) {
		parse(s);
	}

	/** Get the interaction type.
	 *  
	 *  @return interaction type
	 */
	public InteractionType getType() {
		return InteractionType.FILL_IN;
	}

	/** Set the response value.
	 * 
	 *  SCORM 2004 4th edition limits the number of elements
	 *  of the list to 10 and the size of each <code>LocalizedString</code> to
	 *  at most 250 characters.
	 *  
	 * @param v  response value
	 * @throws de.dfki.allegro.scorm.ScormObjectSizeOutOfLimitException size limit exceeded
	 */
	@ScormSizeLimit(value=10,min=1)
	public void setValue(List<LocalizedString> v) {
		typeData = v;
	}

	/** Get <code>String</code> representation.
	 * 
	 * @return <code>String</code> representation.
	 */
	public String toString() {
		StringBuilder b = new StringBuilder();
		for (LocalizedString i : typeData)
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
	private static StringBuilder addToStringArrayEncoding(LocalizedString s, StringBuilder b) {
		if (b.length() > 0)
			b.append("[,]");
		return b.append(s);
	}

	/** Parses an encoded <code>String</code> and fills
	 *  this object with the encoded data.
	 * 
	 */
	protected void parse(String s) {
		String[] a = s.split("\\[,\\]");
		typeData = new ArrayList<LocalizedString>(a.length);
		for (String as : a)
			typeData.add(new LocalizedString(as));
	}
}
