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
package de.dfki.allegro.scorm.token;

import de.dfki.allegro.scorm.ScormAdapter;

/** Defines to tokens that indicate what a SCO should do when
 *  maximum time allowed is exceeded.
 * 
 * @author Timo Scheuer
 * @see de.dfki.allegro.scorm.Attempt#getMaxTimeAllowed()
 */
public enum TimeLimitAction {

	EXIT_MESSAGE("exit_message"),
	CONTINUE_MESSAGE("continue_message"),
	EXIT_NO_MESSAGE("exit_no_message"),
	CONTINUE_NO_MESSAGE("continue_no_message");
	
	/** The setting. */
	private String setting;


	/** Get <code>String</code> representation.
	 * 
	 * @return <code>String</code> representation.
	 */
	public String toString() {
		return setting;
	}
	
	/** Ctor.
	 * 
	 * @param s  the setting
	 */
	private TimeLimitAction(String s) {
		setting = s;
	}
	
	/** Get the enum corresponding to a given <code>String</code>.
	 * 
	 * @param s  input <code>String</code>
	 * @return the corresponding enum type
	 */
	public static TimeLimitAction getEnum(String s) {
		if (EXIT_MESSAGE.setting.equals(s))
			return EXIT_MESSAGE;
		if (CONTINUE_MESSAGE.setting.equals(s))
			return CONTINUE_MESSAGE;
		if (EXIT_NO_MESSAGE.setting.equals(s))
			return EXIT_NO_MESSAGE;
		if (CONTINUE_NO_MESSAGE.setting.equals(s))
			return CONTINUE_NO_MESSAGE;
		String error = "The value '" + s +
			"' is none of the allowed time limit action values " +
			"(exit_message, continue_message, exit_no_message or continue_no_message)!";
		if (ScormAdapter.getStrictStandardHandling())
			throw new RuntimeException(error);
		System.err.println("Warning: " + error +
				" Using default value (continue_no_message).");
		return CONTINUE_NO_MESSAGE;
	}

}
