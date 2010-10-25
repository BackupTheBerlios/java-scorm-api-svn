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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/** 
 * 
 * @author Timo Scheuer
 *
 */
class TokenSelection<E> extends ScormElement {

	/** The token which is actually used.*/
	private E token;
	/** The method of the token type which is used for
	 *  parsing the token.
	 */
	private Method getEnumMethod;
	
	
	/** Ctor. No token has been selected yet.
	 * 
	 */
	TokenSelection() {
	}

	/** Ctor.
	 * 
	 * @param k  the SCORM access key
	 */
	public TokenSelection(Class<E> c, String k) {
		super(k);
		try {
			getEnumMethod = c.getMethod("getEnum", String.class);
			// the exceptions below should never been thrown
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	/** Get a selection token.
	 *  
	 *  @throws java.lang.IllegalStateException  the actual object has been
	 *                                            newly created and a setter
	 *                                            method has to be called first
	 *                                            to make the data for this
	 *                                            getter method available
	 */
	@SuppressWarnings("unchecked")
	public E getToken() throws IllegalStateException {
		if (key == null)
			throw new IllegalStateException("You tried to read from a new ScormTokenSelection " +
					"which has not been initialized yet by a setter method!");
		String t = ScormAdapter.adapter.getScormCharValue(key);
		try {
			return (E)getEnumMethod.invoke(null, t);
			// the exceptions below should never been thrown
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			e.getCause().printStackTrace();
		}
		throw new Error("Error in getToken@" + getClass().getName());
	}

	/** Replace the actual token selected by a new one.
	 * 
	 * @param t  replacement token. This is
	 *  an optional method that is not supported by all
	 *  implementing classes.
	 * @throws java.lang.UnsupportedOperationException  this method is not
	 *                                                   supported by the
	 *                                                   implementing class
	 * @see de.dfki.allegro.scorm.ScormAdapter#commit()
	 * @see de.dfki.allegro.scorm.ScormAdapter#terminate()
	 */
	public void setToken(E t) throws UnsupportedOperationException {
		token = t;
		save();
	}

	/** Compute the value which is to be written to the LMS.
	 * 
	 * @return write value
	 */
	protected String computeValue() {
		return token.toString();
	}

	/** Save the string to the LMS.
	 *  Remark: Writing is postponed until the next call of
	 *  <code>ScormAdapter.commit()</code> or
	 *  <code>ScormAdapter.terminate()</code>.
	 * 
	 * @see de.dfki.allegro.scorm.ScormAdapter#commit()
	 * @see de.dfki.allegro.scorm.ScormAdapter#terminate()
	 * @throws ScormDataAccessException an error is reported by a SCORM function
	 */
	protected void save() throws ScormDataAccessException {
		String value = computeValue();
		ScormAdapter.adapter.setScormCharValue(key, value);
	}
}
