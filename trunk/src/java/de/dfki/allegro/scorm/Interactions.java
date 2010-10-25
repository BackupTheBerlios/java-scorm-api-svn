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
import de.dfki.allegro.scorm.token.InteractionType;
import de.dfki.allegro.scorm.token.LMSCapability;

/** Interaction can be used for two primary means: A journaling scheme
 *  which requires the SCO to record interaction data every time the
 *  learner is engaged with the interaction. A status scheme that
 *  requires the SCO to record interaction data and keep this data
 *  up to date based on the learner's experience wit the SCO.
 *  
 *  SCORM 2004 4th edition limits the numbers of entries to 250.
 *  
 *  Data of an existing <code>Interaction</code> objects can only
 *  be changed by retrieving the <code>Interaction</code> and after
 *  that modifying this object. New <code>Interaction</code>s
 *  are created by using the method <code>createInteraction()</code>.
 *  
 * @author Timo Scheuer
 *
 */
public class Interactions extends AbstractMap<String, Interaction> {

	/** Base class of all interaction map entries. This class provides
	 *  some common methods.
	 *  
	 * @author Timo Scheuer
	 *
	 * @param <T>  interaction type
	 */
	private static class InteractionMapEntry
		implements Map.Entry<String, Interaction> {
		
		/** Ctor.
		 * 
		 * @param i  the interaction
		 */
		protected Interaction interaction;
		InteractionMapEntry(Interaction i) {
			interaction = i;
		}
		/** Get the entry key
		 * 
		 * @return the entry key
		 */
		public String getKey() {
			return interaction.getId();
		}

		/** Get the value.
		 * 
		 * @return the value.
		 */
		public Interaction getValue() {
			return interaction;
		}

		/** This method is not supported.
		 * 
		 */
		public Interaction setValue(Interaction value) {
			throw new UnsupportedOperationException(
					"Interactions do not allow to use the Map.Entry for replacing the value!");
		}
	}
	
	
	/** The internal map data represented as a set.*/
	private Set<Map.Entry<String, Interaction>> interactionSet =
		new AbstractSet<Map.Entry<String, Interaction>>() {
		
		/** Get an <code>Iterator</code> over the elements of
		 *  the set.
		 *  
		 *  @return element iterator
		 */
		public Iterator<Map.Entry<String, Interaction>> iterator() {
			return new Iterator<Map.Entry<String, Interaction>>() {
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
				public InteractionMapEntry next() {
					String k = "cmi.interactions." + position++;
					InteractionType type = new TokenSelection<InteractionType>(
							InteractionType.class, k + ".type").getToken();
					return	new InteractionMapEntry	(new Interaction(type, k));
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
			return Integer.parseInt(
					ScormAdapter.adapter.getScormCharValue(
							"cmi.interactions._count"));
		}
	};

	
	/** Ctor.
	 * 
	 */
	Interactions() {
	}

	/** Create a new <code>Interaction</code> with a given key
	 *  (id). It is automatically added to the map of all
	 *  interaction.
	 *  
	 *  SCORM 2004 4th edition limits the length to 250 elements.
	 *  
	 * @param k  the key (interaction id)
	 * @param t  interaction type
	 * @return the new interaction
	 */
	@ScormSizeLimit(250)
	public synchronized Interaction createInteraction(String k, InteractionType t) {
		String sk = "cmi.interactions." + interactionSet.size();
		return new Interaction(t, sk, k);
	}

	/** Get a set view of the map.
	 * 
	 * @return set representation of this map
	 */
	public Set<java.util.Map.Entry<String, Interaction>> entrySet() {
		return interactionSet;
	}
	
	/** Check if the LMS supports interaction ids.
	 * 
	 * @return <code>true</code> iff the LMS supports interaction ids.
	 */
	public static boolean supportsId() {
		return ScormAdapter.supportsCapability(
				LMSCapability.INTERACTION_ID);
	}
	
	/** Check if the LMS supports interaction types.
	 * 
	 * @return <code>true</code> iff the LMS supports interaction types.
	 */
	public static boolean supportsType() {
		return ScormAdapter.supportsCapability(
				LMSCapability.INTERACTION_TYPE);
	}
	
	/** Check if the LMS supports interaction objectives.
	 * 
	 * @return <code>true</code> iff the LMS supports interaction objectives.
	 */
	public static boolean supportsObjectives() {
		return ScormAdapter.supportsCapability(
				LMSCapability.INTERACTION_OBJECTIVES);
	}
	
	/** Check if the LMS supports interaction timestamps.
	 * 
	 * @return <code>true</code> iff the LMS supports interaction timestamps.
	 */
	public static boolean supportsTimestamp() {
		return ScormAdapter.supportsCapability(
				LMSCapability.INTERACTION_TIMESTAMP);
	}
	
	/** Check if the LMS supports interaction correct responses.
	 * 
	 * @return <code>true</code> iff the LMS supports interaction
	 *           correct responses.
	 */
	public static boolean supportsCorrectRepsonses() {
		return ScormAdapter.supportsCapability(
				LMSCapability.INTERACTION_CORRECT_RESPONSES);
	}
	
	/** Check if the LMS supports interaction weighting.
	 * 
	 * @return <code>true</code> iff the LMS supports interaction
	 *          weighting.
	 */
	public static boolean supportsWeighting() {
		return ScormAdapter.supportsCapability(
				LMSCapability.INTERACTION_WEIGHTING);
	}
	
	/** Check if the LMS supports interaction learner response.
	 * 
	 * @return <code>true</code> iff the LMS supports interaction
	 *          learner response.
	 */
	public static boolean supportsLearnerResponse() {
		return ScormAdapter.supportsCapability(
				LMSCapability.INTERACTION_LEARNER_RESPONSE);
	}
	
	/** Check if the LMS supports interaction results.
	 * 
	 * @return <code>true</code> iff the LMS supports interaction results.
	 */
	public static boolean supportsResult() {
		return ScormAdapter.supportsCapability(
				LMSCapability.INTERACTION_RESULT);
	}
	
	/** Check if the LMS supports interaction latency.
	 * 
	 * @return <code>true</code> iff the LMS supports interaction latency.
	 */
	public static boolean supportsLatency() {
		return ScormAdapter.supportsCapability(
				LMSCapability.INTERACTION_LATENCY);
	}
	
	/** Check if the LMS supports interaction descriptions.
	 * 
	 * @return <code>true</code> iff the LMS supports interaction
	 *           descriptions.
	 */
	public static boolean supportsDescriptions() {
		return ScormAdapter.supportsCapability(
				LMSCapability.INTERACTION_DESCRIPTION);
	}

}
