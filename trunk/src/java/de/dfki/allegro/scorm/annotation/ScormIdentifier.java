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


/** This is a marker for SCORM 2004 identifier.
 * 
 * @author Timo Scheuer
 *
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ScormIdentifier {
	
}
