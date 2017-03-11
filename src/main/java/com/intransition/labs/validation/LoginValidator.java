package com.intransition.labs.validation;

import com.intransition.labs.dto.LoginForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class LoginValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        return LoginForm.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
    /*	LoginForm loginForm = (LoginForm) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "username.empty", "Username must not be empty.");
		String name = loginForm.getName();
		
		if ( ( name.length() ) > 16 ) {
			errors.rejectValue("username", "username.tooLong", "Username must not more than 16 characters.");
		}
				
		if( !EmailValidator.getInstance().isValid( loginForm.getEmail() ) ){
			errors.rejectValue("email", "email.notValid", "Email address is not valid.");
		}
	*/
    }

}
