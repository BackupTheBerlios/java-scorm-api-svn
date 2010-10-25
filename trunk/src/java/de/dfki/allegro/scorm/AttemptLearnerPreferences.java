package de.dfki.allegro.scorm;
/**
 * 
 */


import java.util.Locale;

import de.dfki.allegro.scorm.annotation.ScormRealRange;
import de.dfki.allegro.scorm.token.LMSCapability;
import de.dfki.allegro.scorm.token.ThreeStatesChangeValue;

/** The learner preferences. Changing the preferences has
 *  only an effect during the actual attempt. After this
 *  attempt the values are reset to the default values.
 * 
 * @author Timo Scheuer
 *
 */
public class AttemptLearnerPreferences implements ScormCommunicationElement {

	/** Change to the audio captioning.*/
	private TokenSelection<ThreeStatesChangeValue> audioCaptioningChange;
	

	/** Ctor.
	 * 
	 */
	AttemptLearnerPreferences() {
		audioCaptioningChange = new TokenSelection<ThreeStatesChangeValue>(
				ThreeStatesChangeValue.class,
				"cmi.learner_preference.audio_captioning");
	}
	
	/** Get the audio change level. This setting is relative to
	 *  the LMS audio level. A value of 1 means no change
	 *  which is also the default value. The value 0 means effectively
	 *  turning audio off.
	 * 
	 * @return the audio change level
	 */
	public float getAudioChangeLevel() {
		return ScormAdapter.adapter.getScormFloat(
				"cmi.learner_preference.audio_level", 1F);
	}
	
	/** Alter the audio change level.  This setting is relative to
	 *  the LMS audio level. A value of 1 means no change
	 *  which is also the default value. The value 0 means effectively
	 *  turning audio off. An value of 2 specifies an amplification
	 *  of 10 decibels and a value of 0.5 an attenuation of 10
	 *  decibels.
	 *  
	 * @param f  the new audio change level
	 */
	@ScormRealRange(minValue=0F)
	public void setAudioChangeLevel(Float f) {
		ScormAdapter.adapter.setScormCharValue(
				"cmi.learner_preference.audio_level", f.toString());
	}
	
	/** Get the actual language settings. The default language
	 *  is <code>en</code>.
	 * @return
	 */
	public Locale getLanguage() {
		String l = ScormAdapter.adapter.getScormCharValue(
				"cmi.learner_preference.language");
		if (l.isEmpty())
			return Locale.ENGLISH;
		return new Locale(l);
	}
	
	/** Change the language settings.
	 * 
	 * @param l  the new language or <code>null</code> to
	 *            set the default language <code>en</code>
	 */
	public void setLanguage(Locale l) {
		String val = l==null ? "" : l.toString();
		ScormAdapter.adapter.setScormCharValue(
				"cmi.learner_preference.language", val);
	}
	
	/** Get the delivery speed change. This setting is relative to
	 *  the LMS delivery speed setting. A value of 1 means no change
	 *  which is also the default value. A value of 2 doubles the
	 *  speed and 0.5 is one half of the reference speed.
	 * 
	 * @return the delivery speed change
	 */
	public float getDeliverySpeedChange() {
		return ScormAdapter.adapter.getScormFloat(
				"cmi.learner_preference.delivery_speed", 1F);
	}
	
	/** Alter the delivery speed change. This setting is relative to
	 *  the LMS delivery speed setting. A value of 1 means no change
	 *  which is also the default value. A value of 2 doubles the
	 *  speed and 0.5 is one half of the reference speed. The minimum
	 *  value is 0.
	 *  
	 * @param f  the new delivery speed change
	 */
	@ScormRealRange(minValue=0F)
	public void setDeliverySpeedChange(Float f) {
		ScormAdapter.adapter.setScormCharValue(
				"cmi.learner_preference.delivery_speed", f.toString());
	}
	
	/** Get the audio captioning change setting. This value is relative to
	 *  the LMS setting.
	 * 
	 * @return the audio captioning change setting
	 */
	public ThreeStatesChangeValue getAudioCaptioningChange() {
		return audioCaptioningChange.getToken();
	}
	
	/** Alter the audio captioning change setting. This setting is relative to
	 *  the LMS setting.
	 *  
	 * @param t  the new audio captioning change setting
	 */
	public void setAudioCaptioningChange(ThreeStatesChangeValue t) {
		audioCaptioningChange.setToken(t);
	}
	

	/** Check if the LMS supports an audio level setting.
	 * 
	 * @return <code>true</code> iff the LMS supports an audio
	 *          level setting.
	 */
	public static boolean supportsAudioLevel() {
		return ScormAdapter.supportsCapability(
				LMSCapability.PREFERENCE_AUDIO_LEVEL);
	}
	
	/** Check if the LMS supports a language setting.
	 * 
	 * @return <code>true</code> iff the LMS supports a language setting.
	 */
	public static boolean supportsLanguage() {
		return ScormAdapter.supportsCapability(
				LMSCapability.PREFERENCE_LANGUAGE);
	}
	
	/** Check if the LMS supports a delivery speed setting.
	 * 
	 * @return <code>true</code> iff the LMS supports a delivery speed
	 *          setting.
	 */
	public static boolean supportsDeliverySpeed() {
		return ScormAdapter.supportsCapability(
				LMSCapability.PREFERENCE_DELIVERY_SPEED);
	}
	
	/** Check if the LMS supports an audio captioning setting.
	 * 
	 * @return <code>true</code> iff the LMS supports an audio
	 *          captioning setting.
	 */
	public static boolean supportsAudioCaptioning() {
		return ScormAdapter.supportsCapability(
				LMSCapability.PREFERENCE_AUDIO_CAPTIONING);
	}
	
}
