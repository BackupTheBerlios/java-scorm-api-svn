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

/** Annotation that provides additional information about SCORM
 *  size limits of character strings and collections.
 * 
 * @author Timo Scheuer
 *
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ScormSizeLimit {

	/** The maximum size limit of a character strings and collections.*/
	int value();
	
	/** The minimum size limit of a character strings and collections.*/
	int min() default 0;
	
}
