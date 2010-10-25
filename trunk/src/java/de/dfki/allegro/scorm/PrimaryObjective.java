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



/** The primary objective that reflects the objective of the whole
 *  SCO (the top level objective).
 *
 *  The progress measure should be set to ProgressMeasure.INCOMPLETE
 *  until a completion threshold is defined and the progress measure
 *  is >= the threshold.
 *  
 * @author Timo Scheuer
 *
 */
public class PrimaryObjective extends BasicObjective {

	/** Ctor.
	 * 
	 * @param k  the access key
	 */
	PrimaryObjective(String k) {
		super(k);
	}

	/** The primary objective has no need of an id. Hence this
	 *  method always returns <code>null</code>
	 * 
	 * @return <code>null</code>
	 */
	public String getId() {
		return null;
	}

	/** Get the completion threshold. This value can be compared to
	 *  the progress measure to determine whether the SCO should be
	 *  considered having the status of being complete.
	 *  
	 * @return the completion threshold
	 * @throws ScormDataAccessException an error is reported by a SCORM function
	 * @throws de.dfki.allegro.scorm.ScormEmptyAttributeException
	 *          you tried to access a value before it had been set to a valid value 
	 */
	public float getCompletionThreshold() throws ScormDataAccessException, ScormEmptyAttributeException {
		return ScormAdapter.adapter.getScormFloat("cmi.completion_theshold");
	}

	/** Get the scaled passing score. This value is normalized to the
	 *  interval [-1; 1]. It tells about the minimum scaled score that
	 *  is required to master this SCO.
	 *  
	 * @return the scaled passing score
	 * @throws ScormDataAccessException an error is reported by a SCORM function
	 * @throws de.dfki.allegro.scorm.ScormEmptyAttributeException
	 *          you tried to access a value before it had been set to a valid value 
	 */
	public float getScaledPassingScore() throws ScormDataAccessException, ScormEmptyAttributeException {
		return ScormAdapter.adapter.getScormFloat("cmi.scaled_passing_score");
	}

}
