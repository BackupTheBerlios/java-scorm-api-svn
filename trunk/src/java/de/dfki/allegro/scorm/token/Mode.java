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

/** The mode indicates the SCO's behavior after launch.
 * 
 * @author Timo Scheuer
 *
 */
public enum Mode {
	BROWSE("browse"),
	NORMAL("normal"),
	REVIEW("review");
	
	/** State of the actual token. */
	private String mode;


	/** Get <code>String</code> representation.
	 * 
	 * @return <code>String</code> representation.
	 */
	public String toString() {
		return mode;
	}
	
	/** Ctor.
	 * 
	 * @param m  the mode
	 */
	private Mode(String m) {
		mode = m;
	}
	
	/** Get the enum corresponding to a given <code>String</code>.
	 * 
	 * @param s  input <code>String</code>
	 * @return the corresponding enum type
	 */
	public static Mode getEnum(String s) {
		if (NORMAL.mode.equals(s))
			return NORMAL;
		if (BROWSE.mode.equals(s))
			return BROWSE;
		if (REVIEW.mode.equals(s))
			return REVIEW;
		String error = "The value '" + s +
			"' is none of the allowed mode values ('browse', 'normal', 'review')!";
		throw new RuntimeException(error);
	}

}
