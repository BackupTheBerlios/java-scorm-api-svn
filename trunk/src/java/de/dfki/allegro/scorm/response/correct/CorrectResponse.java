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
