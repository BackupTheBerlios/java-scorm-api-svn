/**
 * 
 */
package de.dfki.allegro.scorm.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/** Annotation that provides additional range information about SCORM
 *  Real number ranges.
 *  
 * @author Timo Scheuer
 *
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ScormRealRange {

	/** The minimum value of a SCORM real.*/
	float minValue() default Float.MIN_VALUE;
	/** The maximum value of a SCORM real.*/
	float maxValue() default Float.MAX_VALUE;

}
