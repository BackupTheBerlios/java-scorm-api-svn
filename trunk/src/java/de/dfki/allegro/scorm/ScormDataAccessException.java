package de.dfki.allegro.scorm;
/**
 * 
 */


import netscape.javascript.JSException;
import netscape.javascript.JSObject;

/** A problem occured during the communication with the LMS.
 *  The error message is mainly based on the SCORM method
 *  <code>GetLastError()</code>. More details can be retrieved
 *  by calling <code>getDetailMessage()</code> which is
 *  based on the SCORM method <code>GetDiagnostic()</code>.
 * 
 * @author Timo Scheuer
 *
 */
public class ScormDataAccessException extends RuntimeException {

	/** Serial version id. */
	private static final long serialVersionUID = 1L;
	
	
	/** JavaScript connection to the LMS. */
	private JSObject scorm;
	/** Error code. */
	private int err;

	
	/** Ctor.
	 * 
	 */
	ScormDataAccessException() {
		this(ScormAdapter.adapter.scorm, null);
	}

	/** Ctor.
	 * 
	 * @param jso  JavaScript connection to the LMS
	 */
	ScormDataAccessException(JSObject jso, JSException e) {
		super(e);
		scorm = jso;
		try {
			String errString = (String)jso.eval("GetLastError(\"\");");
			try {
				err = Integer.parseInt(errString);
			} catch (NumberFormatException ex) {
				System.err.println("Warning: Error number format error:\n" +
						ex.getLocalizedMessage());
			}
			System.err.println("ErrorCode: " + err);
		} catch (JSException eOuter) {
			System.err.println("Unable to read error code of last error.\n" +
					eOuter.getLocalizedMessage());
		}
	}

	/** Get the SCORM error message. Actually the message
	 *  is localized according to the student's settings
	 *  in the LMS.
	 *  
	 *   @return SCORM error message
	 */
	public String getLocalizedMessage() {
		return getMessage();
	}
	
	/** Get the SCORM error message. Actually the message
	 *  is localized according to the student's settings
	 *  in the LMS.
	 *  
	 *   @return SCORM error message
	 */
	public String getMessage() {
		return (String)scorm.eval("GetErrorString(\"\");");
	}
	
	/** Get more details about the problem (if available).
	 * 
	 * @return more detailed description or <code>null</code>
	 *           if no more details are available.
	 */
	public String getDetailMessage() {
		return (String)scorm.eval("GetDiagnostic(" + err + ");");
	}
	
	/** Get the numeric representation of the error.
	 * 
	 * @return numeric error representation
	 */
	public int getErrorCode() {
		return err;
	}
}
