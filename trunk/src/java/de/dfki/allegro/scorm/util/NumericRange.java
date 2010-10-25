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
package de.dfki.allegro.scorm.util;

/** A numeric range is defined by a minimum and a maximum value.
 *  The class has no default constructor to enforce valid
 *  initialization.
 *  
 *  It is allowed to set only the minimum or maximum 
 *  (lower or upper bound). If you do not give any bound
 *  then there are no limits at all.
 * 
 * @author Timo Scheuer
 *
 */
public class NumericRange {

	/** Minimum number. Is also used for synchronization.*/
	private Float minimum;
	/** Maximum number.*/
	private Float maximum;
	

	/** Ctor
	 * 
	 */
	public NumericRange() {
	}

	/** Ctor
	 * 
	 * @param min  minimum value
	 * @param max  maximum value
	 */
	public NumericRange(Float min, Float max) {
		setRange(min, max);
	}

	/** Ctor
	 * 
	 * @param s  encoded mumeric range
	 */
	public NumericRange(String s) {
		String[] a = s.split("\\[:\\]");
		if (!a[0].isEmpty())
			setMinimum(Float.valueOf(a[0]));
		if (!a[1].isEmpty())
			setMaximum(Float.valueOf(a[1]));
	}

	/** Change the numeric range.
	 * 
	 * @param min  minimum value
	 * @param max  maximum value
	 */
	public void setRange(Float min, Float max) {
		synchronized(minimum) {
			minimum = min;
			maximum = max;
		}
	}

	/** Get the minimum value.
	 * 
	 * @return minimum value
	 */
	public Float getMinimum() {
		synchronized(minimum) {
			return minimum;
		}
	}
	
	/** Set the minimum value.
	 * 
	 * @param m  minimum value
	 */
	public void setMinimum(Float m) {
		synchronized(minimum) {
			minimum = m;
		}
	}
	
	/** Get the maximum value.
	 * 
	 * @return maximum value
	 */
	public Float getMaxnimum() {
		synchronized(minimum) {
			return maximum;
		}
	}
	
	/** Set the maximum value.
	 * 
	 * @param m  maximum value
	 */
	public void setMaximum(Float m) {
		synchronized(maximum) {
			maximum = m;
		}
	}
	
	/** Get <code>String</code> representation.
	 * 
	 * @return <code>String</code> representation.
	 */
	public String toString() {
		return minimum.toString() + "[:]" + maximum.toString();
	}
}
