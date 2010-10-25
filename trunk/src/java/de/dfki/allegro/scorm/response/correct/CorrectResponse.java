/**
 * 
 */
package de.dfki.allegro.scorm.response.correct;

import de.dfki.allegro.scorm.token.InteractionType;

/** Interface that is implemented by all correct responses.
 *  Some correct responses a similar to learner responses
 *  but not all correct responses.
 * 
 * @author Timo Scheuer
 *
 */
public interface CorrectResponse {

	/** Get the interaction type.
	 *  
	 *  @return interaction type
	 */
	public InteractionType getType();
	
}
