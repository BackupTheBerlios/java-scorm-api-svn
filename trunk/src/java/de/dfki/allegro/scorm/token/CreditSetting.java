/** Copyright 2010 Timo Scheuer
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.dfki.allegro.scorm.token;

/** The "credit"-setting.
 * 
 * @author Timo Scheuer
 *
 */
public enum CreditSetting {
	
	CREDIT("credit"),
	NO_CREDIT("no_credit");

	/** Status of the actual token. */
	private String setting;
	
	
	/** Ctor.
	 * 
	 */
	private CreditSetting(String s) {
		setting = s;
	}
	
	/** Get <code>String</code> representation.
	 * 
	 * @return <code>String</code> representation.
	 */
	public String toString() {
		return setting;
	}
	
	/** Get the enum corresponding to a given <code>String</code>.
	 * 
	 * @param s  input <code>String</code>
	 * @return the corresponding enum type
	 */
	public static CreditSetting getEnum(String s) {
		if (CREDIT.setting.equals(s))
			return CREDIT;
		if (NO_CREDIT.setting.equals(s))
			return NO_CREDIT;
		// if the LMS does not support setting "credit" or "no_credit" then
		// it should return the default value: "credit"
		throw new RuntimeException("The value '" + s +
				"' is no valid credit setting!");
	}
}
