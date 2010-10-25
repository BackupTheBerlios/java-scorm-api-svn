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
package de.dfki.allegro.scorm;


import de.dfki.allegro.scorm.annotation.ScormRealRange;
import de.dfki.allegro.scorm.token.LMSCapability;

/**
 * @author Timo Scheuer
 *
 */
public class Score extends ScormElement {

	
	/** Ctor.
	 * 
	 * @param k
	 */
	Score(String k) {
		super(k + ".");
	}

	/** Set the scaled score that reflects the performance of the
	 *  learner. The value has been normalized to fit into the
	 *  interval [-1;1].
	 * 
	 * @param v  value to set
	 */
	@ScormRealRange(minValue=-1F,maxValue=1F)
	public void setScaledScore(Float v) {
		ScormAdapter.adapter.setScormCharValue(
				key + "scaled", v.toString());
	}

	/** Get the scaled score that reflects the performance of the
	 *  learner. The value has been normalized to fit into the
	 *  interval [-1;1]. 
	 * 
	 * @return scaled score
	 * @throws de.dfki.allegro.scorm.ScormEmptyAttributeException
	 *          the raw score has not bean set before this access 
	 */
	public float getScaledScore() throws ScormEmptyAttributeException {
		return ScormAdapter.adapter.getScormFloat(key + "scaled");
	}

	/** Set the raw score that reflects the performance of the
	 *  learner. The value has to fit into the
	 *  interval [minimum score;maximum score].
	 * 
	 * @param v  value to set
	 */
	public void setRawScore(Float v) {
		ScormAdapter.adapter.setScormCharValue(
				key + "raw", v.toString());
	}

	/** Get the raw score that reflects the performance of the
	 *  learner. The value has to fit into the
	 *  interval [minimum score; maximum score]. 
	 * 
	 * @return raw score
	 * @throws de.dfki.allegro.scorm.ScormEmptyAttributeException
	 *          the raw score has not bean set before this access 
	 */
	public float getRawScore() throws ScormEmptyAttributeException {
		return ScormAdapter.adapter.getScormFloat(key + "raw");
	}

	/** Set the minimum limit for the raw score.
	 * 
	 * @param v  value to set
	 */
	public void setMinimumRawScoreLimit(Float v) {
		ScormAdapter.adapter.setScormCharValue(
				key + "min", v.toString());
	}

	/** Get the minimum limit of the raw score. 
	 * 
	 * @return minimum raw score limit
	 * @throws de.dfki.allegro.scorm.ScormEmptyAttributeException
	 *          the raw score has not bean set before this access 
	 */
	public float getMinimumRawScoreLimit() throws ScormEmptyAttributeException {
		return ScormAdapter.adapter.getScormFloat(key + "min");
	}

	/** Set the maximum limit for the raw score.
	 * 
	 * @param v  value to set
	 */
	public void setMaximumRawScoreLimit(Float v) {
		ScormAdapter.adapter.setScormCharValue(
				key + "max", v.toString());
	}

	/** Get the maximum limit of the raw score. 
	 * 
	 * @return maximum raw score limit
	 * @throws de.dfki.allegro.scorm.ScormEmptyAttributeException
	 *          the raw score has not bean set before this access 
	 */
	public float getMaximumRawScoreLimit() throws ScormEmptyAttributeException {
		return ScormAdapter.adapter.getScormFloat(key + "max");
	}

	/** Check if the LMS supports a scaled score.
	 * 
	 * @return <code>true</code> iff the LMS supports a scaled score 
	 */
	public boolean supportsScaledScore() {
		return ScormAdapter.supportsCapability(
				key.length() > 10 ?
						LMSCapability.OBJECTIVES_SCORE_SCALED :
						LMSCapability.SCORE_SCALED);
	}

	/** Check if the LMS supports a raw score.
	 * 
	 * @return <code>true</code> iff the LMS supports a raw score
	 */
	public boolean supportsRawScore() {
		return ScormAdapter.supportsCapability(
				key.length() > 10 ?
						LMSCapability.OBJECTIVES_SCORE_RAW :
						LMSCapability.SCORE_RAW);
	}

	/** Check if the LMS supports a minimum score.
	 * 
	 * @return <code>true</code> iff the LMS supports
	 *          a minimum score
	 */
	public boolean supportsMinimumScore() {
		return ScormAdapter.supportsCapability(
				key.length() > 10 ?
						LMSCapability.OBJECTIVES_SCORE_MIN :
						LMSCapability.SCORE_MIN);
	}

	/** Check if the LMS supports a maximum score.
	 * 
	 * @return <code>true</code> iff the LMS supports a
	 *          maximum score
	 */
	public boolean supportsMaximumScore() {
		return ScormAdapter.supportsCapability(
				key.length() > 10 ?
						LMSCapability.OBJECTIVES_SCORE_MAX :
						LMSCapability.SCORE_MAX);
	}
	
}
