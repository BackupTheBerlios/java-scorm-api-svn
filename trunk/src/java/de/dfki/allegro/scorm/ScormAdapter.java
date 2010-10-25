package de.dfki.allegro.scorm;
/**
 * 
 */



import java.applet.Applet;
import java.text.ParseException;
import java.util.BitSet;
import java.util.Date;

import de.dfki.allegro.scorm.token.LMSCapability;
import de.dfki.allegro.scorm.util.ScormDateFormat;
import de.dfki.allegro.scorm.util.TimeInterval;

import netscape.javascript.JSException;
import netscape.javascript.JSObject;


/** Initiates a connection to a learning management system (LMS) via
 *  JavaScript SCORM API. This class is the base for the communication
 *  with an LMS. 
 *   
 * @author Timo Scheuer
 *
 */
public class ScormAdapter extends SingletonFactory {

	/** Serial version id.*/
	private static final long serialVersionUID = 1L;

	/** SCORM date and time format.*/
	private static final ScormDateFormat TIMESTAMP_FORMAT= new ScormDateFormat();
	
	/** Singleton instance of the SCORM adapter.*/
	static ScormAdapter adapter;
	
	/** The methods this collection supports. Not every LMS supports
	 *  all methods.
	 */
	private static BitSet capabilities;

	/** The Java applet this adapter is attached to.*/
	private transient static Applet applet;

	/** The connection to the JavaScript engine of the surrounding
	 *  web page.
	 */
	transient JSObject scorm;
	
	/** If this flag is <code>true</code> then every standard violation
	 *  throws an exception. Otherwise, if it is plausible, the adapter
	 *  tries a mapping to default values. 
	 */
	private boolean strictStandardHandling = true;
	
	
	/** Get the singleton instance of this class.
	 * 
	 * @param a  the applet this adapter is attached to
	 */
	public static synchronized ScormAdapter getInstance(Applet a) {
		if (!a.equals(applet) || adapter == null) {
			// on multiple calls the applet can change
			applet = a;
			adapter = new ScormAdapter(applet);
		}
		return adapter;
	}
	
	/** Ctor.
	 * 
	 * @param applet  the Java applet that wishes to communicate with
	 *                  the LMS.
	 */
	private ScormAdapter(Applet applet) {
		// get SCORM object
		scorm = JSObject.getWindow(applet);
		scorm = (JSObject)scorm.eval(
		"var nFindAPITries = 0;" +
		"var API = null;" +
		"var maxTries = 500;" +
		"var APIVersion = \"\";" +
		"function ScanForAPI(win) {" +
			"while ((win.API_1484_11 == null) && (win.parent != null)" +
					"&& (win.parent != win)) {" +
				"nFindAPITries++;" +
				"if (nFindAPITries > maxTries) {" +
					"return null;" +
				"}" +
				"win = win.parent;" +
			"}" +
			"return win.API_1484_11;" +
		"}" +
		"function GetAPI(win) {" +
			"if ((win.parent != null) && (win.parent != win)) {" +
				"API = ScanForAPI(win.parent);" +
			"}" +
			"if ((API == null) && (win.opener != null)) {" +
				"API = ScanForAPI(win.opener);" +
			"}" +
			"if (API != null) {" +
				"APIVersion = API.version;" +
			"}" +
			"return API;" +
		"}" +
		"GetAPI(window);"
		);
	}
	
	/** Ask the LMS which optional methods are supported and cache
	 *  this information. 
	 * 
	 */
	static void initCapabilities() {
		capabilities = new BitSet(
				LMSCapability.NUMBER_OF_OPTIONAL_CAPABILITIES.ordinal());
		String[] children = adapter.getScormCharValue(
				"cmi.comments_from_learner._children").split(",");
		for (String child : children) {
			if ("comment".equals(child))
				capabilities.set(LMSCapability.COMMENT_FROM_LEARNER_COMMENT.ordinal());
			else if ("location".equals(child))
				capabilities.set(LMSCapability.COMMENT_FROM_LEARNER_LOCATION.ordinal());
			else if ("timestamp".equals(child))
				capabilities.set(LMSCapability.COMMENT_FROM_LEARNER_TIMESTAMP.ordinal());
		}
		children = adapter.getScormCharValue(
				"cmi.comments_from_lms._children").split(",");
		for (String child : children) {
			if ("comment".equals(child))
				capabilities.set(LMSCapability.COMMENT_FROM_LMS_COMMENT.ordinal());
			else if ("location".equals(child))
				capabilities.set(LMSCapability.COMMENT_FROM_LMS_LOCATION.ordinal());
			else if ("timestamp".equals(child))
				capabilities.set(LMSCapability.COMMENT_FROM_LMS_TIMESTAMP.ordinal());
		}
		children = adapter.getScormCharValue(
				"cmi.interactions._children").split(",");
		for (String child : children) {
			if ("id".equals(child))
				capabilities.set(LMSCapability.INTERACTION_ID.ordinal());
			else if ("type".equals(child))
				capabilities.set(LMSCapability.INTERACTION_TYPE.ordinal());
			else if ("objectives".equals(child))
				capabilities.set(LMSCapability.INTERACTION_OBJECTIVES.ordinal());
			else if ("timestamp".equals(child))
				capabilities.set(LMSCapability.INTERACTION_TIMESTAMP.ordinal());
			else if ("correct_responses".equals(child))
				capabilities.set(LMSCapability.INTERACTION_CORRECT_RESPONSES.ordinal());
			else if ("weighting".equals(child))
				capabilities.set(LMSCapability.INTERACTION_WEIGHTING.ordinal());
			else if ("learner_response".equals(child))
				capabilities.set(LMSCapability.INTERACTION_LEARNER_RESPONSE.ordinal());
			else if ("result".equals(child))
				capabilities.set(LMSCapability.INTERACTION_RESULT.ordinal());
			else if ("latency".equals(child))
				capabilities.set(LMSCapability.INTERACTION_LATENCY.ordinal());
			else if ("description".equals(child))
				capabilities.set(LMSCapability.INTERACTION_DESCRIPTION.ordinal());
		}
		children = adapter.getScormCharValue(
				"cmi.learner_preference._children").split(",");
		for (String child : children) {
			if ("audio_level".equals(child))
				capabilities.set(LMSCapability.PREFERENCE_AUDIO_LEVEL.ordinal());
			else if ("language".equals(child))
				capabilities.set(LMSCapability.PREFERENCE_LANGUAGE.ordinal());
			else if ("delivery_speed".equals(child))
				capabilities.set(LMSCapability.PREFERENCE_DELIVERY_SPEED.ordinal());
			else if ("audio_captioning".equals(child))
				capabilities.set(LMSCapability.PREFERENCE_AUDIO_CAPTIONING.ordinal());
		}
		children = adapter.getScormCharValue(
				"cmi.objectives._children").split(",");
		for (String child : children) {
			if ("id".equals(child))
				capabilities.set(LMSCapability.OBJECTIVES_ID.ordinal());
			else if ("score".equals(child))
				capabilities.set(LMSCapability.OBJECTIVES_SCORE.ordinal());
			else if ("success_status".equals(child))
				capabilities.set(LMSCapability.OBJECTIVES_SUCCESS_STATUS.ordinal());
			else if ("completion_status".equals(child))
				capabilities.set(LMSCapability.OBJECTIVES_COMPLETION_STATUS.ordinal());
			else if ("progress_measure".equals(child))
				capabilities.set(LMSCapability.OBJECTIVES_PROGRESS_MEASURE.ordinal());
			else if ("description".equals(child))
				capabilities.set(LMSCapability.OBJECTIVES_DESCRIPTION.ordinal());
		}
		// reference to the first element with index 0 to check availability for the
		// minimum amount of possible entries
		// an LMS may have implemented this (by using the index 0) as a constant lookup
		children = adapter.getScormCharValue(
				"cmi.objectives.0.score._children").split(",");
		for (String child : children) {
			if ("scaled".equals(child))
				capabilities.set(LMSCapability.OBJECTIVES_SCORE_SCALED.ordinal());
			else if	 ("raw".equals(child))
				capabilities.set(LMSCapability.OBJECTIVES_SCORE_RAW.ordinal());
			else if	 ("min".equals(child))
				capabilities.set(LMSCapability.OBJECTIVES_SCORE_MIN.ordinal());
			else if	 ("max".equals(child))
				capabilities.set(LMSCapability.OBJECTIVES_SCORE_MAX.ordinal());
		}
		children = adapter.getScormCharValue(
				"cmi.score._children").split(",");
		for (String child : children) {
			if ("scaled".equals(child))
				capabilities.set(LMSCapability.SCORE_SCALED.ordinal());
			else if	 ("raw".equals(child))
				capabilities.set(LMSCapability.SCORE_RAW.ordinal());
			else if	 ("min".equals(child))
				capabilities.set(LMSCapability.SCORE_MIN.ordinal());
			else if	 ("max".equals(child))
				capabilities.set(LMSCapability.SCORE_MAX.ordinal());
		}
		children = adapter.getScormCharValue(
				"adl.data._children").split(",");
		for (String child : children) {
			if ("id".equals(child))
				capabilities.set(LMSCapability.ADL_DATA_ID.ordinal());
			else if	 ("store".equals(child))
				capabilities.set(LMSCapability.ADL_DATA_STORE.ordinal());
		}
	}

	/** Calls a given SCORM function that has a boolean return value.
	 *  This method also evaluates the error code and throws an exception
	 *  if an error has occured.
	 *  
	 * @param c  JavaScript function call (as <code>String</code>)
	 *            including parameters
	 * @throws ScormDataAccessException an error is reported by a SCORM function
	 */
	void callBooleanScormFunction(String c) throws ScormDataAccessException{
		try {
			System.out.println("__calling: " + c);
			String r = (String)scorm.eval(c);
			System.out.println("__result: " + r + "\n");
			if (!Boolean.parseBoolean(r)) {
				System.err.println("Function call '" + c + "' failed!");
				throw new ScormDataAccessException();
			}
		} catch (JSException e) {
			System.err.println("Function call '" + c +
					"' failed, JSException was thrown:\n" + e.getMessage());
			e.printStackTrace();
			throw new ScormDataAccessException(scorm, e);
		}
	}
	
	/** Read a character-based value from the LMS.
	 *  This method also evaluates the error code and throws an exception
	 *  if an error has occured.
	 *  
	 * @param key  data access key
	 * @throws ScormDataAccessException an error is reported by a SCORM function
	 */
	String getScormCharValue(String key) throws ScormDataAccessException {
		String call = "GetValue(\"" + key + "\");"; 
		System.out.println("__calling: " + call);
		try {
			String s = (String)scorm.eval(call);
			System.out.println("__result: " + s + "\n");
			return s;
		} catch (JSException e) {
			System.err.println("Function call '" + call +
					"' failed, JSException was thrown:\n" + e.getMessage());
			throw new ScormDataAccessException(scorm, e);
		}
	}
	
	/** Get a Java <code>float</code> representation of the internal
	 *  real value.
	 *  
	 *  @param key  data access key
	 *  @param d  the default value which is returned if the value
	 *             you tried to retrieve is not available. This behavior
	 *             is only available if you do not use strict error
	 *             handling.
	 *             
	 * @return Java <code>float</code> value
	 * @throws ScormDataAccessException an error is reported by a SCORM function
	 */
	float getScormFloat(String key, float d) throws ScormDataAccessException {
		String val = getScormCharValue(key);
		if (val.isEmpty()) {
			if (getStrictStandardHandling())
				throw new RuntimeException("You tried to retrieve (" +
						key + ") which has not been set before!");
			return d;
		}
		try {
			return Float.valueOf(val);
		} catch (NumberFormatException e) {
			String error = "The value '" + val + "' is not a real number!";
			throw new RuntimeException(error, e);
		}
	}

	/** Get a Java <code>float</code> representation of the internal
	 *  real value.
	 *  
	 *  @param key  data access key
	 *             
	 * @return Java <code>float</code> value
	 * @throws ScormDataAccessException an error is reported by a SCORM function
	 * @throws de.dfki.allegro.scorm.ScormEmptyAttributeException
	 *          you tried to access a value before it had been set to a valid value 
	 */
	float getScormFloat(String key) throws ScormDataAccessException, ScormEmptyAttributeException {
		String val = getScormCharValue(key);
		if (val.isEmpty()) {
			throw new ScormEmptyAttributeException("You tried to retrieve (" +
					key + ") which has not been set before!");
		}
		try {
			return Float.valueOf(val);
		} catch (NumberFormatException e) {
			String error = "The value '" + val + "' is not a real number!";
			throw new RuntimeException(error, e);
		}
	}

	/** Get a Java <code>Date</code> representation of the internal
	 *  date/time.
	 *  
	 *  @param key  data access key
	 *             
	 * @return Java <code>Date</code> value
	 * @throws ScormDataAccessException an error is reported by a SCORM function
	 * @throws de.dfki.allegro.scorm.ScormEmptyAttributeException
	 *          you tried to access a value before it had been set to a valid value 
	 */
	Date getScormTimestamp(String key) throws ScormDataAccessException, ScormEmptyAttributeException {
		String val = getScormCharValue(key);
		if (val.isEmpty()) {
			throw new ScormEmptyAttributeException("You tried to retrieve (" +
					key + ") which has not been set before!");
		}
		try {
			return TIMESTAMP_FORMAT.parse(val);
		} catch (ParseException e) {
			String error = "The value '" + val + "' is no SCORM timestamp!";
			throw new RuntimeException(error, e);
		}
	}

	/** Get a <code>TimeInterval</code> representation of the internal
	 *  time interval.
	 *  
	 *  @param key  data access key
	 *             
	 * @return Java <code>Date</code> value
	 * @throws ScormDataAccessException an error is reported by a SCORM function
	 * @throws de.dfki.allegro.scorm.ScormEmptyAttributeException
	 *          you tried to access a value before it had been set to a valid value 
	 */
	TimeInterval getScormTimeInterval(String key) throws ScormDataAccessException, ScormEmptyAttributeException {
		try {
			String val = getScormCharValue(key);
			if (val.isEmpty())
				throw new ScormEmptyAttributeException("You tried to retrieve (" +
						key + ") which has not been set before!");
			return new TimeInterval(val);
		} catch (ParseException e) {
			throw new ScormFormatException(e.getMessage(), e);
		}
	}

	/** Write a character-based value to the LMS.
	 *  This method also evaluates the error code and throws an exception
	 *  if an error has occured.
	 *  
	 * @param key  data access key
	 * @param value  to write to the LMS
	 * @throws ScormException an error is reported by a SCORM function
	 */
	void setScormCharValue(String key, String value) throws ScormDataAccessException{
		String call = "SetValue(\"" + key + "\",\"" + value + "\");"; 
		System.out.println("__calling: " + call);
		try {
			String r = (String)scorm.eval(call);
			System.out.println("__result: " + r + "\n");
			if (!Boolean.parseBoolean(r)) {
				System.err.println("Function call '" + call + "' failed!");
				throw new ScormDataAccessException();
			}
		} catch (JSException e) {
			System.err.println("Function call '" + call +
					"' failed, JSException was thrown:\n" + e.getMessage());
			throw new ScormDataAccessException(scorm, e);
		}
	}
	
	/** Write a <code>Date</code> value to the LMS.
	 *  This method also evaluates the error code and throws an exception
	 *  if an error has occured.
	 *  
	 * @param key  data access key
	 * @param value  to write to the LMS
	 * @throws ScormException an error is reported by a SCORM function
	 */
	void setScormTimestamp(String key, Date value) throws ScormDataAccessException{
		setScormCharValue(key, TIMESTAMP_FORMAT.format(value));
	}
	
	/** Get the login session
	 * 
	 * @return login session
	 */
	public LoginSession getLoginSession() {
		return getLoginSessionInstance();
	}
	
	/** Check if a given capability is supported by the LMS.
	 * 
	 * @return <code>true</code> iff the given capability
	 *          is supported else <code>false</code>.
	 */
	public static boolean supportsCapability(LMSCapability c) {
		return capabilities.get(c.ordinal());
	}
	
	/** Activate or deactivate strict standard handling.
	 *  If this flag is <code>true</code> then every standard violation
	 *  throws an exception. Otherwise, if it is plausible, the adapter
	 *  tries a mapping to default values. The default handling is
	 *  strict.
	 *  
	 *  @param f  <code>true</code> for strict standard handling 
	 */
	public static void setStrictStandardHandling(boolean f) {
		adapter.strictStandardHandling = f;
	}
	
	/** Get the setting of the setting of the standard handling method.
	 *  If this flag is <code>true</code> then every standard violation
	 *  throws an exception. Otherwise, if it is plausible, the adapter
	 *  tries a mapping to default values which is less strict. The
	 *  default handling is strict.
	 */
	public static boolean getStrictStandardHandling() {
		return adapter.strictStandardHandling;
	}
}
