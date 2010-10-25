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


import java.util.*;

import de.dfki.allegro.scorm.annotation.ScormIdentifier;
import de.dfki.allegro.scorm.annotation.ScormRealRange;
import de.dfki.allegro.scorm.annotation.ScormSizeLimit;
import de.dfki.allegro.scorm.util.LocalizedString;


/** The SCORM 2004 specification constraints many containers
 *  (objects and character container) to a certain size
 *  (number of elements). This aspect ensures that these
 *  are limits never exceeded. Otherwise an exception is
 *  thrown.
 * 
 * @author Timo Scheuer
 *
 */
public aspect ScormConstraints {
	
	private pointcut printWelcome() :
		(call (public * initialize*()));
	
	before() :
	printWelcome() {
		System.err.println("*****  welcome  ************************");
	}
	
	
	private pointcut printBye() :
		(call (public * terminate*()));
	
	after() :
	printBye() {
		System.err.println("*****  bye  *****************************");
	}
	
	
	//-----  string based argument size limits  -----------------------------------------
	/** Public setter of with a <code>String</code> argument that are
	 *  marked by a <code>ScormSizeLimit</code>.
	 *
	 * @see de.dfki.allegro.scorm.annotation.ScormSizeLimit
	 */
	pointcut sizeLimitOnStringSetMethod() :
		call(@ScormSizeLimit public void set*(String));
	
	/** Package level id setter methods. They are used to initialize
	 *  newly created objects which happens internally. The ids are
	 *  typically <code>String</code> type arguments that are
	 *  marked by a <code>ScormSizeLimit</code>.
	 *
	 * @see de.dfki.allegro.scorm.annotation.ScormSizeLimit
	 */
	pointcut sizeLimitOnStringSetIdOnceMethod() :
		call(@ScormSizeLimit void setId(String));
	
	/** Private add method that is used by a method encoding a whole
	 *  object for writing it to the LMS. The data comprises not only
	 *  this <code>String</code> but typically a collection of
	 *  <code>String</code>s. This pointcut defines a postponed check
	 *  that avoids adding an additional iteration over the collection.
	 *  The add method is marked by a <code>ScormSizeLimit</code> that
	 *  defines the size limit of the <code>String</code>.
	 *
	 * @see de.dfki.allegro.scorm.annotation.ScormSizeLimit
	 */
	pointcut sizeLimitOnStringAddForEncodingMethod() :
		call(@ScormSizeLimit private * add*(String,..));
	
	/** Check the size of <code>String</code> type arguments with size
	 *  limits.
	 *
	 * @param l  the SCORM size limit 
	 * @param s  the <code>String</code> to set
	 */
	before(ScormSizeLimit l, String s) :
		(sizeLimitOnStringSetMethod()
		|| sizeLimitOnStringSetIdOnceMethod()
		|| sizeLimitOnStringAddForEncodingMethod())
		&& @annotation(l) && args(s,..)
	 {
		System.out.print("___checkArgString: ");
		if (s.length() > l.value()) {
			System.out.println("error");
			throw new ScormObjectSizeOutOfLimitException(
					"@" + thisJoinPoint.getSignature() + 
					" You tried to set '" +
					s + "' which has a length of " + s.length() +
					" characters that is bigger that the maximum allowed length of " +
					l.value() + "!");
		}
		System.out.println("ok");
	}
	

	//-----  localized string based argument size limits  --------------------------------
	/** Public setter of with a <code>LocalizedString</code> argument that are
	 *  marked by a <code>ScormSizeLimit</code>.
	 *
	 * @see de.dfki.allegro.scorm.annotation.ScormSizeLimit
	 */
	pointcut sizeLimitOnLocalizedStringSetMethod() :
		call(@ScormSizeLimit public void set*(LocalizedString));
	
	/** Private add method that is used by a method encoding a whole
	 *  object for writing it to the LMS. The data comprises not only
	 *  this <code>LocalizedString</code> but typically a collection of
	 *  <code>LocalizedString</code>s. This pointcut defines a postponed check
	 *  that avoids adding an additional iteration over the collection.
	 *  The add method is marked by a <code>ScormSizeLimit</code> that
	 *  defines the size limit of the <code>LocalizedString</code>.
	 *
	 * @see de.dfki.allegro.scorm.annotation.ScormSizeLimit
	 */
	pointcut sizeLimitOnLocalizedStringAddForEncodingMethod() :
		call(@ScormSizeLimit private * add*(LocalizedString,..));
	
	/** Check the size of <code>LocalizedString</code> type arguments with size
	 *  limits.
	 *
	 * @param l  the SCORM size limit 
	 * @param s  the <code>LocalizedString</code> to set
	 */
	before(ScormSizeLimit l, LocalizedString s) :
		(sizeLimitOnLocalizedStringSetMethod()
		|| sizeLimitOnLocalizedStringAddForEncodingMethod())
		&& @annotation(l) && args(s,..)
	{
		System.out.print("___checkArgLocalizedString: ");
		if (s.length() > l.value()) {
			System.out.println("error");
			throw new ScormObjectSizeOutOfLimitException(
					"@" + thisJoinPoint.getSignature() + 
					" You tried to set '" +
					s + "' which has a length of " + s.length() +
					" characters that is bigger that the maximum allowed length of " +
					l.value() + "!");
		}
		System.out.println("ok");
	}

	//-----  collection type based argument size limits  -------------------------------
	/** Public setter of with a <code>Collection</code> argument that are
	 *  marked by a <code>ScormSizeLimit</code>.
	 *
	 * @see de.dfki.allegro.scorm.annotation.ScormSizeLimit
	 */
	pointcut sizeLimitOnCollectionSetMethod() :
		call(@ScormSizeLimit * set*(*));

	/** Check the size of <code>Collection</code> type arguments. These
	 *  are used for example by some of the <code>Response</code> classes.
	 *
	 * @param l  the SCORM size limit 
	 * @param c  the <code>Collection</code> or a subclass
	 */
	before(ScormSizeLimit l, Collection<?> c) :
		sizeLimitOnCollectionSetMethod()
		&& !sizeLimitOnStringSetMethod()
		&& !sizeLimitOnLocalizedStringSetMethod()
		&& @annotation(l) && args(c)
	{
		System.out.print("___checkArgSizeCollection: ");
		if (c.size() > l.value()) {
			System.out.println("error");
			throw new ScormObjectSizeOutOfLimitException(
					"@" + thisJoinPoint.getSignature() +
					"You tried to set a collection of " + c.size() +
					" elements but the maximum number allowed is " + l.value() + "!");
		}
		if (l.min()>0 && c.size() <l.min()) {
			System.out.println("error");
			throw new ScormObjectSizeOutOfLimitException(
					"@" + thisJoinPoint.getSignature() +
					"You tried to set a collection of " + c.size() +
					" elements but the minimum number allowed is " + l.min() + "!");
		}
		System.out.println("ok");
	}

	
	//-----  collection type based size limits  -------------------------------
	/** Method of a collection based class that creates a new
	 *  element.
	 *
	 */
	pointcut sizeLimitCreateNewCollectionElement() :
		call(@ScormSizeLimit public synchronized * create*());
	
	/** Check if the size limits of a <code>Collection</code> type
	 *  class whose method to create (add) a new element has been
	 *  called exceeds the limit.
	 *
	 * @param l  the SCORM size limit 
	 */
	before(ScormSizeLimit l) :
		sizeLimitCreateNewCollectionElement()
		&& @annotation(l)
	{
		System.out.print("___checkSizeList: ");
		Collection<?> s = (Collection<?>)thisJoinPoint.getTarget();
		if (s.size() >= l.value()) {
			System.out.println("error");
			throw new ScormObjectSizeOutOfLimitException(
					"@" + thisJoinPoint.getSignature() + 
					"You tried to create a new element but the maximum limit of " +
					l.value() + " elements has already been reached!");
		}
		System.out.println("ok");
	}
	

	//-----  map type based size limits  -------------------------------
	/** Method of a map based class that creates a new
	 *  element.
	 *
	 */
	pointcut sizeLimitCreateNewMapElement() :
		call(@ScormSizeLimit public synchronized * create*(*));
	
	/** Check the size limits of <csetCorrectResponsesode>Map</code> type classes whose method
	 *  to create (add) a new element has been called.
	 *
	 * @param l  the SCORM size limit 
	 * @param m  the <code>String</code> to set
	 */
	before(ScormSizeLimit l) :
		sizeLimitCreateNewMapElement()
		&& @annotation(l)
	{
		System.out.print("___checkSizeMap: ");
		@SuppressWarnings("unchecked")
		Map<String, ?> m = (Map<String,?>)thisJoinPoint.getTarget();
		if (m.size() >= l.value()) {
			System.out.println("error");
			throw new ScormObjectSizeOutOfLimitException(
					"@" + thisJoinPoint.getSignature() + 
					"You tried to create a new element but the maximum limit of " +
					l.value() + " elements has already been reached!");
		}
		System.out.println("ok");
	}
	

	//-----  float type based argument range limits  -------------------------
	/** Check the size of <code>float</code> type arguments with range
	 *  limits.
	 *
	 * @param r  the SCORM range limit 
	 * @param f  the <code>float</code> to set
	 */
	pointcut rangeLimitOnFloatSetMethod() :
		call(@ScormRealRange public void set*(Float));
	
	/** Check the size of <code>float</code> type arguments with range
	 *  limits.
	 *
	 * @param r  the SCORM range limit 
	 * @param f  the <code>float</code> to set
	 */
	before(ScormRealRange r, float f) :
		rangeLimitOnFloatSetMethod()
		&& @annotation(r) && args(f)
	{
		System.out.print("___checkArgRealRange: ");
		if (f < r.minValue()) {
			System.out.println("error");
			throw new RuntimeException(
					"@" + thisJoinPoint.getSignature() + 
					"The value '" + f +
					"' is smaller than the minimum allowed value '" +
					r.minValue() + "'!");
		}
		if (f > r.maxValue()) {
			System.out.println("error");
			throw new RuntimeException("The value '" + f +
					"@" + thisJoinPoint.getSignature() + 
					"' is bigger than the maximum allowed value '" +
					r.maxValue() + "'!");
		}
		System.out.println("ok");
	}
	

	//-----  identifier type based argument -------------------------------
	/** Public setter of with a <code>String</code> argument that are
	 *  marked by <code>ScormIdentifier</code>.
	 *
	 * @see de.dfki.allegro.scorm.annotation.ScormIdentifier
	 */
	pointcut identifierSetMethod() :
		call(@ScormIdentifier public void set*(String));
	
	/** Package level id setter methods. They are used to initialize
	 *  newly created objects which happens internally. The ids are
	 *  typically <code>String</code> type arguments that are
	 *  marked by <code>ScormIdentifier</code>.
	 *
	 * @see de.dfki.allegro.scorm.annotation.ScormIdentifier
	 */
	pointcut identifierSetIdOnceMethod() :
		call(@ScormIdentifier void setId(String));
	
	/** Private add method that is used by a method encoding a whole
	 *  object for writing it to the LMS. The data comprises not only
	 *  this <code>String</code> but typically a collection of
	 *  <code>String</code>s. This pointcut defines a postponed check
	 *  that avoids adding an additional iteration over the collection.
	 *  The add method is marked by <code>ScormIdentifier</code>.
	 *
	 * @see de.dfki.allegro.scorm.annotation.ScormIdentifier
	 */
	pointcut identifierAddForEncodingMethod() :
		call(@ScormIdentifier private * add*(String,..));
	
	/** Check if the <code>String</code> argument is an
	 *  identifier.
	 *
	 * @param s  the <code>String</code> to set
	 */
	before(String s) :
		(identifierSetMethod()
		|| identifierSetIdOnceMethod()
		|| identifierAddForEncodingMethod())
		&& args(s,..)
	{
		System.out.print("___checkArgIdentifier: ");
		if (s.isEmpty()) {
			System.out.println("error");
			throw new RuntimeException(
					"@" + thisJoinPoint.getSignature() + 
					"An empty String is no valid long identifier (URI)!");
		}
		System.out.println("ok");
	}
	

}
