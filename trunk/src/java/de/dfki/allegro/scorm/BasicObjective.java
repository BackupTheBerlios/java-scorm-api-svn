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
package de.dfki.allegro.scorm;


import de.dfki.allegro.scorm.annotation.ScormRealRange;
import de.dfki.allegro.scorm.token.CompletionStatus;
import de.dfki.allegro.scorm.token.SuccessStatus;


/** An objective encapsulates tracking data which is used by
 *  the LMS for sequencing.
 *  
 * @author Timo Scheuer
 *
 */
public abstract class BasicObjective extends ObjectiveId {
	
	/** The score.*/
	private Score score;
	/** The success status.*/
	private TokenSelection<SuccessStatus> successStatus;
	/** The completion status.*/
	private TokenSelection<CompletionStatus> completionStatus;

	
	/** Ctor used for reading.
	 * 
	 * @param k  access key
	 */
	BasicObjective(String k) {
		super(k);
		score = new Score(key + "score");
		successStatus = new TokenSelection<SuccessStatus>(
				SuccessStatus.class, key + "success_status");
		completionStatus = new TokenSelection<CompletionStatus>(
				CompletionStatus.class, key + "completion_status");
	}

	/** Ctor used for creating a new objective. The objective
	 *  id is the first attribute that has to be written.
	 * 
	 * @param k  access key
	 * @param i  the objective id
	 */
	BasicObjective(String k, String i) {
		this(k);
		setId(i);
	}

	/** Get the tracked score of the objective.
	 * 
	 * @return the score of this objective
	 */
	public Score getScore() {
		return score;
	}

	/** Set the success status of the objective.
	 * 
	 * @param s  the success status
	 */
	public void setSuccessStatus(SuccessStatus s) {
		successStatus.setToken(s);
	}

	/** Get the success status of the objective.
	 * 
	 * @return the success status of the objective.
	 */
	public SuccessStatus getSuccessStatus() {
		return successStatus.getToken();
	}

	/** Set the completion status of the objective.
	 * 
	 * @param s  the completion status
	 */
	public void setCompletionStatus(CompletionStatus s) {
		completionStatus.setToken(s);
	}

	/** Get the completion status of the objective.
	 * 
	 * @return the completion status of the objective.
	 */
	public CompletionStatus getCompletionStatus() {
		return completionStatus.getToken();
	}

	/** Set the progress measure. The value has to be in
	 *  the interval [0; 1].
	 * 
	 * @param p  progress measure
	 */
	@ScormRealRange(minValue=0F,maxValue=1F)
	public void setProgressMeasure(Float p) {
		ScormAdapter.adapter.setScormCharValue(
				key + "progress_measure", p.toString());
	}

	/** Get the progress measure. The value is defined
	 * for the interval [0; 1].
	 * 
	 * @return the progress measure
	 * @throws ScormEmptyAttributeException thrown if the value is being
	 *                                       accessed before it has been set
	 */
	public float getProgressMeasure() throws ScormEmptyAttributeException {
		return ScormAdapter.adapter.getScormFloat(key + "progress_measure");
	}

}
