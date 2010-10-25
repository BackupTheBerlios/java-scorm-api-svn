/**
 * 
 */
package de.dfki.allegro.scorm.util;

import java.io.Serializable;
import java.util.Locale;

/** Representation of a localized SCORM character string.
 *  This string can contain language settings.
 *  
 *  This class is only a container which does not trigger any
 *  SCORM operations.
 * 
 * @author Timo Scheuer
 *
 */
public class LocalizedString implements Serializable {

	/** Serial version id.*/
	private static final long serialVersionUID = 1L;
	
	/** The character string.*/
	protected String string;
	/** Language settings. The default language is 'en'. */
	private Locale lang;

	
	/** Ctor.
	 * 
	 * @param s  a character string
	 */
	public LocalizedString(String s) {
		setString(s);
	}

	/** Ctor.
	 * 
	 * @param s  a character string
	 */
	public LocalizedString(String s, Locale l) {
		this(s);
		lang = l;
	}

	/** Change the character string. The string can also
	 *  contain SCORM specific localization information.
	 *  
	 * @param s  character string
	 */
	public void setString(String s) {
		if (s.startsWith("{lang=")) {
			int end = s.indexOf('}');
			lang = new Locale(s.substring(6, end));
			string = s.substring(end + 1);
		} else {
			string = s;
		}
	}

	/** Change the language.
	 * 
	 * @param l  new language settings or <code>null</code> for
	 *             the default language 'en'.
	 */
	public void setLanguage(Locale l) {
		lang = l;
	}
	
	/** Get the language settings of this string.
	 * 
	 * @return language settings.
	 */
	public Locale getLanguage() {
		if (lang==null)
			return Locale.ENGLISH;
		return lang;
	}
	
	/** Get a <code>String</code> representation of the
	 *  localized character string including the language
	 *  setting according to the SCORM 2004 specification.
	 * 
	 * @return <code>String</code> representation
	 */
	public String toString() {
		return ((!(lang==null || "en".equals(lang))) ?
			"{lang=" + lang + "}" : "") + string;
	}

	/** Get the pure character string without control data
	 *  that some derived types allow (e.g. localization
	 *  settings).
	 *  
	 *  @return the pure character string
	 */
	public String getString() {
		return string;
	}

	/** Get the length of the pure string. The computation excludes the
	 *  the length of characters used for control data like
	 *  localization settings.
	 *  
	 * @return length of the pure string
	 */
	public int length() {
		return string.length();
	}
	
	/** Check if the string is empty. The computation excludes the
	 *  the length of characters used for control data like
	 *  localization settings.
	 *  
	 * @return <code>true</code> if the pure string is empty
	 */
	public boolean isEmpty() {
		return string.isEmpty();
	}
}
