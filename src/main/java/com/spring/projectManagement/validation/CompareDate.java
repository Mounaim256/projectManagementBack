package com.spring.projectManagement.validation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Constraint(validatedBy = CompareDateValidator.class)
public @interface CompareDate {
	String message() default "the EndDate must greater than StartDate";
	Class<?> [] groups() default {} ;
	Class<? extends Payload>[] payload() default {};
}
