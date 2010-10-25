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
import de.dfki.allegro.scorm.token.LMSCapability;

/** The objectives are available during the actual
 *  attempt. They can change from <code>CommunicationSession</code>
 *  to <code>CommunicationSession</code>. This can happen
 *  when updates of the sequencing (specified in the SCORM
 *  package) are propagated to the objectives.
 *  
 *  The tracked data stored in the objectives is used for
 *  sequencing.
 *  
 *  Data of an existing <code>Objective</code> objects can only
 *  be changed by retrieving the <code>Objective</code> and after
 *  that modifying this object. New <code>Objective</code>s
 *  are created by using the method <code>createObjective()</code>.
 *  
 *  SCORM 2004 4th edition limits the numbers of entries to 100.
 *  
 * @author Timo Scheuer
 *
 */
public class Objectives extends AbstractMap<String, Objective> {

	
	/** The internal map data represented as a set.*/
	private Set<Map.Entry<String, Objective>> objectiveSet =
		new AbstractSet<Map.Entry<String, Objective>>() {
		
		/** Get an <code>Iterator</code> over the elements of
		 *  the set.
		 *  
		 *  @return element iterator
		 */
		public Iterator<Map.Entry<String, Objective>> iterator() {
			return new Iterator<Map.Entry<String, Objective>>() {
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
				public Map.Entry<String, Objective> next() {
					return new Map.Entry<String, Objective>() {
						private Objective objective =
							new Objective("cmi.objectives." + position++);

						/** Get the entry key
						 * 
						 * @return the entry key
						 */
						public String getKey() {
							return objective.getId();
						}

						/** Get the entry value.
						 * 
						 * @return the entry value
						 */
						public Objective getValue() {
							return objective;
						}

						/** This method is not supported.
						 * 
						 */
						public Objective setValue(Objective value) {
							throw new UnsupportedOperationException(
									"Objectives do not allow to use the Map.Entry for replacing the value!");
						}
					};
				}

				/** The remove operation is not supported.
				 * 
				 */
				public void remove() {
					throw new UnsupportedOperationException(
							"Removing objectives is not supported!");
				}
			};
		}

		/** Get the number of elements.
		 *  
		 *  @return number of elements of the set 
		 */
		public int size() {
			return Integer.parseInt(
					ScormAdapter.adapter.getScormCharValue(
							"cmi.objectives._count"));
		}
	};
	
	
	/** Ctor.
	 * 
	 */
	Objectives() {
	}
	
	/** Get a set view of the map.
	 * 
	 * @return set representation of this map
	 */
	public Set<Map.Entry<String, Objective>> entrySet() {
		return objectiveSet;
	}

	/** Create a new <code>Objective</code> with a given key
	 *  (id). It is automatically added to the map of all
	 *  objectives.
	 *  
	 *  SCORM 2004 4th edition limits the length to 100 elements.
	 *  
	 * @param k  the key (objective id)
	 * @return the new objective
	 * @see de.dfki.allegro.scorm.BasicObjective
	 */
	@ScormSizeLimit(100)
	public synchronized Objective createObjective(String k) {
		return new Objective("cmi.objectives." + objectiveSet.size(), k);
	}

	/** Check if the LMS supports objective ids.
	 * 
	 * @return <code>true</code> iff the LMS supports ids
	 */
	public static boolean supportsId() {
		return ScormAdapter.supportsCapability(
				LMSCapability.OBJECTIVES_ID);
	}
	
	/** Check if the LMS supports scores.
	 * 
	 * @return <code>true</code> iff the LMS supports scores
	 */
	public static boolean supportsScore() {
		return ScormAdapter.supportsCapability(
				LMSCapability.OBJECTIVES_SCORE);
	}
	
	/** Check if the LMS supports success status information.
	 * 
	 * @return <code>true</code> iff the LMS supports  success
	 *          status information
	 */
	public static boolean supportsSuccessStatus() {
		return ScormAdapter.supportsCapability(
				LMSCapability.OBJECTIVES_SUCCESS_STATUS);
	}
	
	/** Check if the LMS supports completion status information.
	 * 
	 * @return <code>true</code> iff the LMS supports completion
	 *          status information
	 */
	public static boolean supportsCompletionStatus() {
		return ScormAdapter.supportsCapability(
				LMSCapability.OBJECTIVES_COMPLETION_STATUS);
	}
	
	/** Check if the LMS supports progress measure information.
	 * 
	 * @return <code>true</code> iff the LMS supports progress
	 *          measure information
	 */
	public static boolean supportsProgressMeasure() {
		return ScormAdapter.supportsCapability(
				LMSCapability.OBJECTIVES_PROGRESS_MEASURE);
	}
	
	/** Check if the LMS supports descriptions.
	 * 
	 * @return <code>true</code> iff the LMS supports descriptions
	 */
	public static boolean supportsDescription() {
		return ScormAdapter.supportsCapability(
				LMSCapability.OBJECTIVES_DESCRIPTION);
	}
	
	/** Get the primary objective that reflects the whole SCO.
	 * 
	 * @return the primary objective
	 */
	public PrimaryObjective getPrimaryObjective() {
		return new PrimaryObjective("cmi");
	}

}
