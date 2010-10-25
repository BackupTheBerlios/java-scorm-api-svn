package de.dfki.allegro.scorm;
/**
 * 
 */


import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import de.dfki.allegro.scorm.annotation.ScormSizeLimit;
import de.dfki.allegro.scorm.token.LMSCapability;

/** Shared data store which allows sharing data between multiple
 *  SCOs. The scope can be configured in the SCORM package to survive
 *  attempts or to be reset after each new attempt.
 *  
 *  All map entries and their ids have to be defined in the SCORM
 *  package. You are only allowed to change the storage element value
 *  and are neither allowed to change the id nor to add new entries.
 *  
 *  SCORM 2004 4th edition limits the length of a storage element to
 *  64000 characters.
 *  
 * @author Timo Scheuer
 *
 */
public class SharedDataStore extends AbstractMap<String, String> {

	/** Ctor.
	 * 
	 */
	SharedDataStore() {
	}

	/** The internal map data represented as a set.*/
	private Set<Map.Entry<String, String>> dataStoreSet = new AbstractSet<Map.Entry<String, String>>() {
		/** Get an <code>Iterator</code> over the elements of
		 *  the set.
		 *  
		 *  @return element iterator
		 */
		public Iterator<java.util.Map.Entry<String, String>> iterator() {
			return new Iterator<Map.Entry<String, String>>() {
				/** The actual iterator position.*/
				private int position;
				
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
				public java.util.Map.Entry<String, String> next() {
					return new Map.Entry<String, String>() {
						/** Position of the actual entry.*/
						int entryPosition = position++;

						/** Get the entry key
						 * 
						 *  SCORM 2004 4th edition limits the length of a storage element key to
						 *  4000 characters.
						 *  
						 * @return the entry key
						 */
						public String getKey() {
							return ScormAdapter.adapter.getScormCharValue(
									"adl.data." + entryPosition + ".id");
						}

						/** Get the entry value.
						 * 
						 *  SCORM 2004 4th edition limits the length of a storage element to
						 *  64000 characters.
						 *  
						 * @return the entry value
						 */
						public String getValue() {
							return ScormAdapter.adapter.getScormCharValue(
									"adl.data." + entryPosition + ".store");
						}

						/** Replace the actual value.
						 * 
						 *  SCORM 2004 4th edition limits the length of a storage element to
						 *  64000 characters.
						 *  
						 * @param value  new value to write to the data store
						 */
						@ScormSizeLimit(64000)
						public String setValue(String value) {
							String old = getValue();
							ScormAdapter.adapter.setScormCharValue(
									"adl.data." + entryPosition + ".store", value);
							return old;
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

		/** Get the number of elements. New elements
		 *  will only be added if they are saved.
		 *  
		 *  @return number of elements of the set 
		 */
		public int size() {
			try {
				return Integer.parseInt(
						ScormAdapter.adapter.getScormCharValue(
								"adl.data._count"));
			} catch (NumberFormatException e) {
				if (ScormAdapter.getStrictStandardHandling())
					throw e;
				// return the default value 0
				return 0;
			}
		}
	};

	/** Get a set view of the map.
	 * 
	 * @return set representation of this map
	 */
	public Set<java.util.Map.Entry<String, String>> entrySet() {
		return dataStoreSet;
	}

	/** Check if the LMS supports objective ids.
	 * 
	 * @return <code>true</code> iff the LMS supports ids
	 */
	public static boolean supportsId() {
		return ScormAdapter.supportsCapability(
				LMSCapability.ADL_DATA_ID);
	}
	
	/** Check if the LMS supports the data store.
	 * 
	 * @return <code>true</code> iff the LMS supports the data store
	 */
	public static boolean supportsStore() {
		return ScormAdapter.supportsCapability(
				LMSCapability.ADL_DATA_STORE);
	}
	
}
