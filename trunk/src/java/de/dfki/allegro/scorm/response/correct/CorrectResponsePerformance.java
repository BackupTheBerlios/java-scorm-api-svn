/**
 * 
 */
package de.dfki.allegro.scorm.response.correct;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import de.dfki.allegro.scorm.annotation.ScormSizeLimit;
import de.dfki.allegro.scorm.response.Response;
import de.dfki.allegro.scorm.token.InteractionType;


/** A performance response according to the SCORM 2004
 *  specification.
 *  
 *  SCORM 2004 4th edition limits the number of elements
 *  of the list of o<code>PerformanceStep</code>s to 250.
 *  Duplicate entries are allowed.
 *  
 * @author Timo Scheuer
 *
 */
public class CorrectResponsePerformance extends Response<List<PerformanceStepRange>> implements CorrectResponse {

	/** Flag that tells if the order of the single entries matters.*/
	private boolean orderMatters = true;

	
	/** Ctor.
	 * 
	 */
	public CorrectResponsePerformance() {
		typeData = new LinkedList<PerformanceStepRange>();
	}

	/** Ctor.
	 * 
	 * @param s  encoded <code>String</code>
	 */
	public CorrectResponsePerformance(String s) {
		String[] a = s.split("\\[,\\]");
		typeData = new ArrayList<PerformanceStepRange>(a.length);
		for (String sa : a)
			typeData.add(new PerformanceStepRange(sa));
	}

	/** Get the interaction type.
	 *  
	 *  @return interaction type
	 */
	public InteractionType getType() {
		return InteractionType.PERFORMANCE;
	}

	/** Set the response value.
	 * 
	 *  SCORM 2004 4th edition limits the number of elements
	 *  of the list to 125.
	 *  
	 * @param v  response value
	 * @throws de.dfki.allegro.scorm.ScormObjectSizeOutOfLimitException size limit exceeded
	 */
	@ScormSizeLimit(value=125,min=1)
	public void setValue(List<PerformanceStepRange> v) {
		typeData = v;
	}

	/** Get <code>String</code> representation.
	 * 
	 * @return <code>String</code> representation.
	 */
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("{order_matters=").append(orderMatters).append("}");
		boolean first = true;
		for (PerformanceStepRange i : typeData) {
			if (first)
				first = false;
			else
				b.append("[,]");
			b.append(i);
		}
		return b.toString();
	}
	
	/** Set a flag that decides whether order will matter.
	 *  If set to <code>true</code> learner responses are
	 *  only considered being correct if, besides everything
	 *  else, the right order of the responses has been given.
	 *  If set to <code>false</code> it does not
	 *  matter which order has been used in a learner
	 *  response. If the order in the learner response
	 *  is not the same as in the <code>CorrectResponse</code>
	 *  and everything else is correct then the
	 *  response is considered being correct.
	 *   
	 * @param f  case flag
	 */
	public void setOrderFlag(boolean f) {
		orderMatters = f;
	}
	
	/** Check if order matters. Returns <code>true</code> iff
	 *  order matters else <code>false</code>. The default
	 *  value is <code>true</code>.
	 *  
	 * @return <code>true</code> iff order matters
	 */
	public boolean doesOrderMatter() {
		return orderMatters;
	}

}
