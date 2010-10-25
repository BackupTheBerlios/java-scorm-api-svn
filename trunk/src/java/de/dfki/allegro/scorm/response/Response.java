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
