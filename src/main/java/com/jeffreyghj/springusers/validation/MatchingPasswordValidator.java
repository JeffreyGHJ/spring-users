package com.jeffreyghj.springusers.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

public class MatchingPasswordValidator implements ConstraintValidator<ValidPassword, Object> {
	
	private String password;
	private String confirmPass;
	private String message;
	
	@Override
	public void initialize(ValidPassword constraintAnnotation) {
		this.password = constraintAnnotation.password();
		this.confirmPass = constraintAnnotation.confirmPass();
		this.message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(password);
		Object fieldMatchValue = new BeanWrapperImpl(value).getPropertyValue(confirmPass);
		boolean isValid = false;
		        
        if (fieldValue != null) {
            isValid = fieldValue.equals(fieldMatchValue);
        }
        
        if (!isValid) {
        	context.disableDefaultConstraintViolation();
        	context.buildConstraintViolationWithTemplate( message )
        		   .addPropertyNode( "confirmPassword" ).addConstraintViolation();
        }
        
        return isValid;
    }

}
