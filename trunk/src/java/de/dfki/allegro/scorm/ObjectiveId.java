package de.dfki.allegro.scorm;
/**
 * 
 */


import de.dfki.allegro.scorm.annotation.ScormIdentifier;
import de.dfki.allegro.scorm.annotation.ScormSizeLimit;

/** Unique objective id.
 * 
 * @author Timo Scheuer
 *
 */
public class ObjectiveId extends ScormElement {

	
	/** Ctor.
	 * 
	 */
	ObjectiveId() {
	}

	/** Ctor.
	 * 
	 * @param k  SCORM access key
	 */
	ObjectiveId(String k) {
		super(k + ".");
	}

	/** Ctor.
	 * 
	 * @param k  SCORM access key
	 * @param i  the id of the new objective id
	 */
	ObjectiveId(String k, String i) {
		this(k);
		setId(i);
	}

	/** Set the id of the interaction. The id represents a URI.
	 *  Empty <code>String</code>s are not allowed. The id is the
	 *  first element to be set if you are creating a new interactions
	 *  entry.
	 *  SCORM 2004 4th edition limits the length to 4000 characters.
	 * 
	 * @param c  comment of the student.
	 */
	@ScormIdentifier
	@ScormSizeLimit(4000)
	void setId(String c) {
		ScormAdapter.adapter.setScormCharValue(
				key + "id", c.toString());
	}

	/** Get the id of the objective. The id represents a URI.
	 *  SCORM 2004 4th edition limits the length to 4000 characters.
	 * 
	 * @return the objective id
	 */
	public String getId() {
		return ScormAdapter.adapter.getScormCharValue(key + "id");
	}

}
