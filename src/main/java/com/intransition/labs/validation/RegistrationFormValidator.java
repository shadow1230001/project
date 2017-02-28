package com.intransition.labs.validation;

import org.apache.commons.validator.routines.EmailValidator;
import com.intransition.labs.domain.user.User;
import com.intransition.labs.form.SignupForm;
import com.intransition.labs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RegistrationFormValidator implements Validator {
	
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SignupForm form = (SignupForm) o;

        //Email
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "page.registration.signupform.validation.empty.email");
        if( form.getEmail().length() > 64 ) {
            errors.rejectValue("email", "page.registration.signupform.validation.size.email");
        }
        if( !EmailValidator.getInstance().isValid( form.getEmail() ) ){
        	errors.rejectValue("email", "page.registration.signupform.validation.notvalid.email" );
        }
		if ( userService.findByEmail( form.getEmail() ) != null ) {
            errors.rejectValue("email", "page.registration.signupform.validation.duplicate.email");
        }
		
		//First name
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "page.registration.signupform.validation.empty.firstname");
        if (form.getFirstName().length() < 2 || form.getFirstName().length() > 50 ) {
            errors.rejectValue("firstName", "page.registration.signupform.validation.size.firstname");
        }

        //lastname
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "page.registration.signupform.validation.empty.lastname");
        if (form.getFirstName().length() < 2 || form.getFirstName().length() > 50 ) {
            errors.rejectValue("lastName", "page.registration.signupform.validation.size.lastname");
        }
        
        //nickname
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nickname", "page.registration.signupform.validation.empty.nickname");
        if ( userService.findByNickname( form.getNickname() ) != null ) {
            errors.rejectValue("nickname", "page.registration.signupform.validation.duplicate.nickname");
        }
        if (form.getNickname().length() < 6 || form.getNickname().length() > 50 ) {
            errors.rejectValue("nickname", "page.registration.signupform.validation.size.nickname");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "page.registration.signupform.validation.empty.password");
        if ( form.getPassword().length() < 6 ) {
            errors.rejectValue("password", "page.registration.signupform.validation.size.password");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "page.registration.signupform.validation.empty.passwordconfirm");
        if ( form.getPassword().length() < 6 ) {
            errors.rejectValue("passwordConfirm", "page.registration.signupform.validation.size.passwordconfirm");
        }
        
        if ( !form.getPassword().equals( form.getPasswordConfirm() ) ) {
            errors.rejectValue("passwordConfirm", "page.registration.signupform.validation.nodiff.passwordconfirm");
        }
       
    }
    
        
}
