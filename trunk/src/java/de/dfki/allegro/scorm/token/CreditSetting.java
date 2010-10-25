/**
 * 
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
