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
package de.dfki.allegro.scorm;


import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import de.dfki.allegro.scorm.annotation.ScormSizeLimit;

/** Set of objective ids.
 * 
 * @author Timo Scheuer
 *
 */
public class ObjectiveIds extends AbstractMap<String, ObjectiveId> {

	/** SCORM access key.*/
	private String key;
	
	/** The internal map data represented as a set.*/
	private Set<Map.Entry<String, ObjectiveId>> objectiveIdSet =
		new AbstractSet<Map.Entry<String, ObjectiveId>>() {
		
		/** Get an <code>Iterator</code> over the elements of
		 *  the set.
		 *  
		 *  @return element iterator
		 */
		public Iterator<Map.Entry<String, ObjectiveId>> iterator() {
			return new Iterator<Map.Entry<String, ObjectiveId>>() {
				/** The actual iterator position.*/
				private int position = 0;
				
				/** Returns <code>true</code> iff there is another
				 *  element.
				 *  
				 *  @return <code>true</code> iff there is a successor
				 */
				public boolean hasNext() {
					return position<size();
				}

				/** Get the next element.
				 * 
				 * @return the next element
				 */
				public Map.Entry<String, ObjectiveId> next() {
					return	new Map.Entry<String, ObjectiveId> () {
						private ObjectiveId oid = new ObjectiveId (key + position++);
						
						/** Get the entry key
						 * 
						 * @return the entry key
						 */
						public String getKey() {
							return oid.getId();
						}

						/** Get the entry value.
						 * 
						 * @return the entry value
						 */
						public ObjectiveId getValue() {
							return oid;
						}

						/** The operation is not supported.
						 * 
						 */
						public ObjectiveId setValue(ObjectiveId value) {
							throw new UnsupportedOperationException(
							"changing objective ids is not supported!");
						}
					};
				}

				/** The remove operation is not supported.
				 * 
				 */
				public void remove() {
					throw new UnsupportedOperationException(
							"Removing Interactions is not supported!");
				}			
			};
		}

		/** Get the number of elements.
		 *  
		 *  @return number of elements of the set 
		 */
		public int size() {
			return Integer.parseInt(ScormAdapter.adapter.getScormCharValue(key + "_count"));
		}
	};
	
	/** Ctor.
	 * 
	 * @param k  SCORM access key
	 */
	ObjectiveIds(String k) {
		key = k + ".";
	}

	/** Get the objective id iterator.
	 * 
	 * @return objective id iterator
	 */
	public Iterator<ObjectiveId> iterator() {
		return new Iterator<ObjectiveId>() {
			/** The actual iterator position.*/
			private int position = 0;
			
			/** Returns <code>true</code> iff there is another
			 *  element.
			 *  
			 *  @return <code>true</code> iff there is a successor
			 */
			public boolean hasNext() {
				return position<size();
			}

			/** Get the next element.
			 * 
			 * @return the next element
			 */
			public ObjectiveId next() {
				return	new ObjectiveId (key + position++);
			}

			/** The remove operation is not supported.
			 * 
			 */
			public void remove() {
				throw new UnsupportedOperationException(
						"Removing Interactions is not supported!");
			}			
		};
	}

	/** Create a new <code>ObjectiveId</code>.
	 * 
	 *  SCORM 2004 4th edition limits the number of elements to 10.
	 *  
	 * @return The new element
	 */
	@ScormSizeLimit(10)
	public synchronized ObjectiveId createObjectiveId(String i) {
		return new ObjectiveId(key + size(), i);
	}

	/** Get a set view of the map.
	 * 
	 * @return set representation of this map
	 */
	public Set<java.util.Map.Entry<String, ObjectiveId>> entrySet() {
		return objectiveIdSet;
	}

}
