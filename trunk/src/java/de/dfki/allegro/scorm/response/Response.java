/**
 * 
 */
package de.dfki.allegro.scorm.response;

import de.dfki.allegro.scorm.token.InteractionType;

/** Base class of all response types
 * 
 * @author Timo Scheuer
 *
 */
abstract public class Response<E>  {
	
	/** The data of the response type.*/
	protected E typeData;
	

	/** Get the value of the response.
	 * 
	 * @return response value
	 */
	public E getValue() {
		return typeData;
	}
	
	/** Set the response value.
	 * 
	 * @param v  response value
	 */
	abstract public void setValue(E v);
	
	/** Get the interaction type.
	 *  
	 *  @return interaction type
	 */
	abstract public InteractionType getType();
	
	/** Get <code>String</code> representation.
	 * 
	 * @return <code>String</code> representation.
	 */
	abstract public String toString();
}
