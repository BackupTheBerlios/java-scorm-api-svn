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



import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import de.dfki.allegro.scorm.annotation.ScormIdentifier;
import de.dfki.allegro.scorm.annotation.ScormSizeLimit;
import de.dfki.allegro.scorm.response.*;
import de.dfki.allegro.scorm.response.correct.*;
import de.dfki.allegro.scorm.token.InteractionType;
import de.dfki.allegro.scorm.util.LocalizedString;
import de.dfki.allegro.scorm.util.TimeInterval;

/** Interaction can be used for two primary means: A journaling scheme
 *  which requires the SCO to record interaction data every time the
 *  learner is engaged with the interaction. A status scheme that
 *  requires the SCO to record interaction data and keep this data
 *  up to date based on the learner's experience wit the SCO.
 *  
 *  Changes to this object are immediately written to the LMS.
 *  
 * @author Timo Scheuer
 *
 */
public class Interaction extends ScormElement {

	/** The interaction type.*/
	private InteractionType type;
	
	
	/** Ctor.
	 * 
	 * @param t  the interaction type
	 * @param k  SCORM data access key
	 */
	Interaction(InteractionType t, String k) {
		super(k + ".");
		type = t;
	}
	
	/** Ctor.
	 * 
	 * @param t  the interaction type
	 * @param k  SCORM data access key
	 * @param i  the id of the interaction
	 */
	Interaction(InteractionType t, String k, String i) {
		this(t, k);
		setId(i);
	}
	
	/** Get the id of the interaction. The id represents a URI.
	 *  SCORM 2004 4th edition limits the length to 4000 characters.
	 * 
	 * @return comment of the student.
	 */
	public String getId() {
		return ScormAdapter.adapter.getScormCharValue(key + "id");
	}

	/** Set the id of the interaction. The id represents a URI.
	 *  Empty <code>String</code>s are not allowed. The id is the
	 *  first element to be set if you are creating a new interactions
	 *  entry.
	 *  SCORM 2004 4th edition limits the length to 4000 characters.
	 * 
	 * @param i  the id
	 */
	@ScormIdentifier
	@ScormSizeLimit(4000)
	void setId(String i) {
		ScormAdapter.adapter.setScormCharValue(key + "id", i.toString());
	}

	/** Get the interaction type.
	 *  
	 *  @return interaction type
	 */
	public InteractionType getType() {
		return type;
	}

	/** Get the set of objective ids that correspond to this interaction.
	 *  The <code>ObjectiveId</code>s may or may not be related to the
	 *  <code>Objectives</code> of the <code>Attempt</code>
	 *  
	 *  @return objectives
	 *  @see de.dfki.allegro.scorm.Attempt#getObjectives()
	 */
	public ObjectiveIds getObjectiveIds() {
		return new ObjectiveIds(key + "objectives");
	}
	
	/** Get the interaction timestamp.
	 *  
	 *  @return interaction timestamp
	 *  @throws de.dfki.allegro.scorm.ScormEmptyAttributeException thrown if the
	 *      the attribute has not been set yet
	 */
	public Date getTimestamp() throws ScormEmptyAttributeException {
		return ScormAdapter.adapter.getScormTimestamp(key + "timestamp");
	}

	/** Set the interaction timestamp.
	 * 
	 * @param t  interaction timestamp
	 */
	public void setTimestamp(Date d) {
		ScormAdapter.adapter.setScormTimestamp(key + "timestamp", d);
	}

	/** Get the interaction weighting. The weight of an interaction
	 *  may be used by the SCO to compute the value for a score.
	 *  Interaction weight s are typically used to explain the
	 *  effect of an interaction on the value of a score.
	 *  
	 *  @return interaction weighting
	 *  @throws de.dfki.allegro.scorm.ScormEmptyAttributeException thrown if the
	 *      the attribute has not been set yet
	 */
	public float getWeighting() throws ScormEmptyAttributeException {
		return ScormAdapter.adapter.getScormFloat(key + "weighting");
	}

	/** Set the interaction weighting. The weight of an interaction
	 *  may be used by the SCO to compute the value for a score.
	 *  Interaction weight s are typically used to explain the
	 *  effect of an interaction on the value of a score.
	 * 
	 * @param w  interaction weighting
	 */
	public void setWeighting(Float w) {
		ScormAdapter.adapter.setScormCharValue(
				key + "weighting", w.toString());
	}

	/** Get the description
	 *  SCORM 2004 4th edition limits the length to 250 characters.
	 * 
	 * @return the description
	 */
	public LocalizedString getDescription() {
		return new LocalizedString(
				ScormAdapter.adapter.getScormCharValue(
						key + "description"));
	}

	/** Set the description
	 *  SCORM 2004 4th edition limits the length to 250 characters.
	 * 
	 * @param d  the description
	 */
	@ScormSizeLimit(250)
	public void setDescription(LocalizedString d) {
		ScormAdapter.adapter.setScormCharValue(
				key + "description", d.toString());
	}

	/** Set the latency that describes the time elapsed between
	 *  the time the interaction was made available to the student
	 *  and the time of the first response.
	 *  
	 * @param t  the latency
	 */
	public void setLatency(TimeInterval t) {
		ScormAdapter.adapter.setScormCharValue(key + "latency", t.toString());
	}

	/** Get the latency that describes the time elapsed between
	 *  the time the interaction was made available to the student
	 *  and the time of the first response.
	 *  
	 * @return  the latency
	 * @throws de.dfki.allegro.scorm.ScormFormatException the format returned
	 *                                                     by the LMS is not correct
	 *                                                     according to the SCORM
	 *                                                     2004 specification
	 * @throws ScormDataAccessException an error is reported by a SCORM function
	 * @throws de.dfki.allegro.scorm.ScormEmptyAttributeException
	 *          you tried to access a value before it had been set to a valid value 
	 */
	public TimeInterval getLatency() throws ScormFormatException, ScormDataAccessException, ScormEmptyAttributeException {
		return ScormAdapter.adapter.getScormTimeInterval(key + "latency");
	}

	/** Get the learner response. To determine the exact type
	 *  of the response the method <code>getType()</code> can
	 *  be used.
	 * 
	 * @return learner response
	 * @see de.dfki.allegro.scorm.response.Response#getType()
	 */
	public Response<?> getLearnerResponse() {
		String r = ScormAdapter.adapter.getScormCharValue(key + "learner_response");
		switch (type) {
		case TRUE_FALSE:
			return new ResponseTrueFalse(r);
		case CHOICE:
			return	new ResponseChoice(r);
		case FILL_IN:
			return	new ResponseFillIn(r);
		case LONG_FILL_IN:
			return	new ResponseLongFillIn(r);
		case LIKERT:
			return	new ResponseLikert(r);
		case MATCHING:
			return	new ResponseMatching(r);
		case PERFORMANCE:
			return	new ResponsePerformance(r);
		case SEQUENCING:
			return	new ResponseSequencing(r);
		case NUMERIC:
			return	new ResponseNumeric(r);
		default:
			return	new ResponseOther(r);
		}		
	}

	/** Set the learner response.
	 * 
	 * @param r  learner response
	 */
	public void setLearnerResponse(Response<?> r) {
		ScormAdapter.adapter.setScormCharValue(key + "learner_response", r.toString());
	}

	/** Get the all correct responses. To determine the exact type
	 *  of the response the method <code>getType()</code> can
	 *  be used.
	 * 
	 * @return list of all correct responses
	 * @see de.dfki.allegro.scorm.response.Response#getType()
	 */
	public List<CorrectResponse> getCorrectResponses() {
		int size = Integer.valueOf(ScormAdapter.adapter.getScormCharValue(
				key + "correct_responses._count"));
		List<CorrectResponse> l = new ArrayList<CorrectResponse>(size);
		String k = key + "correct_responses.";
		for (int i=0; i<size; i++) {
			String r = ScormAdapter.adapter.getScormCharValue(k + i + ".pattern");
			switch (type) {
			case TRUE_FALSE:
				l.add(new CorrectResponseTrueFalse(r));
			case CHOICE:
				l.add(new CorrectResponseChoice(r));
			case FILL_IN:
				l.add(new CorrectResponseFillIn(r));
			case LONG_FILL_IN:
				l.add(new CorrectResponseLongFillIn(r));
			case LIKERT:
				l.add(new CorrectResponseLikert(r));
			case MATCHING:
				l.add(new CorrectResponseMatching(r));
			case PERFORMANCE:
				l.add(new CorrectResponsePerformance(r));
			case SEQUENCING:
				l.add(new CorrectResponseSequencing(r));
			case NUMERIC:
				l.add(new CorrectResponseNumeric(r));
			default:
				l.add(new CorrectResponseOther(r));
			}		
		}
		return l;
	}
	
	/** Set the correct responses of this interaction.
	 * 
	 * The maximum allowed size of the number of correct
	 * response alternatives differs from type to type
	 * (in a range from 1 to 10). For more details have a
	 * look at the SCORM 2004 specification.
	 * 
	 * @param c  collection of all correct responses of this interaction
	 */
	public void setCorrectResponses(Collection<? extends CorrectResponse> c) {
		switch (type) {
		case TRUE_FALSE:
			setCorrectResponsesTrueFalse(c);
		case CHOICE:
			setCorrectResponsesChoice(c);
		case FILL_IN:
			setCorrectResponsesFillIn(c);
		case LONG_FILL_IN:
			setCorrectResponsesLongFillIn(c);
		case LIKERT:
			setCorrectResponsesLikert(c);
		case MATCHING:
			setCorrectResponsesMatching(c);
		case PERFORMANCE:
			setCorrectResponsesPerformance(c);
		case SEQUENCING:
			setCorrectResponsesSequencing(c);
		case NUMERIC:
			setCorrectResponsesNumeric(c);
		default:
			setCorrectResponsesOther(c);
		}		
	}

	/** Set the correct responses of this interaction.
	 *  This method can be used if there will be exactly
	 *  one correct response.
	 *  
	 *  If more elements have been written before then
	 *  the first element will be overwritten.
	 * 
	 * @param c  collection of all correct responses of this interaction
	 */
	public void setCorrectResponse(CorrectResponse c) {
		ScormAdapter.adapter.setScormCharValue(
				key + "correct_responses.0.pattern", c.toString());
	}

	/** Set the numeric correct responses of this interaction.
	 * 
	 *  SCORM 2004 4th edition limits the length to 1 element.
	 * 
	 * @param c  collection of all correct responses of this interaction
	 */
	@ScormSizeLimit(value=1,min=1)
	private void setCorrectResponsesNumeric(Collection<? extends CorrectResponse> c) {
		setCorrectResponse(c.iterator().next());
	}

	/** Set the true-false correct responses of this interaction.
	 * 
	 *  SCORM 2004 4th edition limits the length to 1 element.
	 * 
	 * @param c  collection of all correct responses of this interaction
	 */
	@ScormSizeLimit(value=10,min=1)
	private void setCorrectResponsesChoice(Collection<? extends CorrectResponse> c) {
		int i=0;
		String k = key + "correct_responses.";
		for (CorrectResponse cr : c)
			ScormAdapter.adapter.setScormCharValue(k + i++ + ".pattern", cr.toString());
	}

	/** Set the true-false correct responses of this interaction.
	 * 
	 *  SCORM 2004 4th edition limits the length to 1 element.
	 * 
	 * @param c  collection of all correct responses of this interaction
	 */
	@ScormSizeLimit(value=5,min=1)
	private void setCorrectResponsesFillIn(Collection<? extends CorrectResponse> c) {
		int i=0;
		String k = key + "correct_responses.";
		for (CorrectResponse cr : c)
			ScormAdapter.adapter.setScormCharValue(k + i++ + ".pattern", cr.toString());
	}

	/** Set the true-false correct responses of this interaction.
	 * 
	 *  SCORM 2004 4th edition limits the length to 1 element.
	 * 
	 * @param c  collection of all correct responses of this interaction
	 */
	@ScormSizeLimit(value=5,min=1)
	private void setCorrectResponsesLongFillIn(Collection<? extends CorrectResponse> c) {
		int i=0;
		String k = key + "correct_responses.";
		for (CorrectResponse cr : c)
			ScormAdapter.adapter.setScormCharValue(k + i++ + ".pattern", cr.toString());
	}

	/** Set the true-false correct responses of this interaction.
	 * 
	 *  SCORM 2004 4th edition limits the length to 1 element.
	 * 
	 * @param c  collection of all correct responses of this interaction
	 */
	@ScormSizeLimit(value=1,min=1)
	private void setCorrectResponsesLikert(Collection<? extends CorrectResponse> c) {
		setCorrectResponse(c.iterator().next());
	}

	/** Set the true-false correct responses of this interaction.
	 * 
	 *  SCORM 2004 4th edition limits the length to 1 element.
	 * 
	 * @param c  collection of all correct responses of this interaction
	 */
	@ScormSizeLimit(value=5,min=1)
	private void setCorrectResponsesMatching(Collection<? extends CorrectResponse> c) {
		int i=0;
		String k = key + "correct_responses.";
		for (CorrectResponse cr : c)
			ScormAdapter.adapter.setScormCharValue(k + i++ + ".pattern", cr.toString());
	}

	/** Set the true-false correct responses of this interaction.
	 * 
	 *  SCORM 2004 4th edition limits the length to 1 element.
	 * 
	 * @param c  collection of all correct responses of this interaction
	 */
	@ScormSizeLimit(value=5,min=1)
	private void setCorrectResponsesSequencing(Collection<? extends CorrectResponse> c) {
		int i=0;
		String k = key + "correct_responses.";
		for (CorrectResponse cr : c)
			ScormAdapter.adapter.setScormCharValue(k + i++ + ".pattern", cr.toString());
	}

	/** Set the true-false correct responses of this interaction.
	 * 
	 *  SCORM 2004 4th edition limits the length to 1 element.
	 * 
	 * @param c  collection of all correct responses of this interaction
	 */
	@ScormSizeLimit(value=1, min=1)
	private void setCorrectResponsesTrueFalse(Collection<? extends CorrectResponse> c) {
		setCorrectResponse(c.iterator().next());
	}

	/** Set the true-false correct responses of this interaction.
	 * 
	 *  SCORM 2004 4th edition limits the length to 1 element.
	 * 
	 * @param c  collection of all correct responses of this interaction
	 */
	@ScormSizeLimit(value=1,min=1)
	private void setCorrectResponsesOther(Collection<? extends CorrectResponse> c) {
		setCorrectResponse(c.iterator().next());
	}

	/** Set the true-false correct responses of this interaction.
	 * 
	 *  SCORM 2004 4th edition limits the length to 1 element.
	 * 
	 * @param c  collection of all correct responses of this interaction
	 */
	@ScormSizeLimit(value=5,min=1)
	private void setCorrectResponsesPerformance(Collection<? extends CorrectResponse> c) {
		int i=0;
		String k = key + "correct_responses.";
		for (CorrectResponse cr : c)
			ScormAdapter.adapter.setScormCharValue(k + i++ + ".pattern", cr.toString());
	}

}
