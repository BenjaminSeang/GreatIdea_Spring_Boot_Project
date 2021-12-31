package com.ben.greatideas.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ben.greatideas.models.User;
import com.ben.greatideas.repositories.UserRepository;

@Component
public class UserValidator implements Validator{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}
	
	@Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        
        if (!user.getPasswordConfirmation().equals(user.getPassword())) {
            errors.rejectValue("password", "Match","Passwords don't not match");
        }  
        
        if(this.userRepository.findByEmail(user.getEmail()) != null) {
        	errors.rejectValue("email", "Unique","Email address already existed");
        }
        
    }

}
