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


/** Optional capabilities which the LMS may support.
 * 
 * @author Timo Scheuer
 *
 */
public enum LMSCapability {

	COMMENT_FROM_LEARNER_COMMENT,
	COMMENT_FROM_LEARNER_LOCATION,
	COMMENT_FROM_LEARNER_TIMESTAMP,
	COMMENT_FROM_LMS_COMMENT,
	COMMENT_FROM_LMS_LOCATION,
	COMMENT_FROM_LMS_TIMESTAMP,
	INTERACTION_ID,
	INTERACTION_TYPE,
	INTERACTION_OBJECTIVES,
	INTERACTION_TIMESTAMP,
	INTERACTION_CORRECT_RESPONSES,
	INTERACTION_WEIGHTING,
	INTERACTION_LEARNER_RESPONSE,
	INTERACTION_RESULT,
	INTERACTION_LATENCY,
	INTERACTION_DESCRIPTION,
	PREFERENCE_AUDIO_LEVEL,
	PREFERENCE_LANGUAGE,
	PREFERENCE_DELIVERY_SPEED,
	PREFERENCE_AUDIO_CAPTIONING,
	OBJECTIVES_ID,
	OBJECTIVES_SCORE,
	OBJECTIVES_SUCCESS_STATUS,
	OBJECTIVES_COMPLETION_STATUS,
	OBJECTIVES_PROGRESS_MEASURE,
	OBJECTIVES_DESCRIPTION,
	OBJECTIVES_SCORE_SCALED,
	OBJECTIVES_SCORE_RAW,
	OBJECTIVES_SCORE_MIN,
	OBJECTIVES_SCORE_MAX,
	SCORE_SCALED,
	SCORE_RAW,
	SCORE_MIN,
	SCORE_MAX,
	ADL_DATA_ID,
	ADL_DATA_STORE,
	NUMBER_OF_OPTIONAL_CAPABILITIES; // this has to be the last one!
	
}
