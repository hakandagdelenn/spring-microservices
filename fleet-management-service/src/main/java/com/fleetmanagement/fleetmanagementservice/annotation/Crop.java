package com.fleetmanagement.fleetmanagementservice.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Hakan Dağdelen
 */
@Retention(RUNTIME)
@Target({FIELD})
public @interface Crop {

  int value() default 255;
}
