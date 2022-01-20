package com.jeffreyghj.springusers.validation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

//@Documented
@Retention(RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = MatchingPasswordValidator.class)
public @interface ValidPassword {

		String message() default "Passwords must match";
		
		String password();
		
		String confirmPass();
		
		Class<?>[] groups() default {};
		
		Class<? extends Payload>[] payload() default {};
}
