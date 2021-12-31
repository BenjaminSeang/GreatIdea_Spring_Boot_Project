package com.ben.greatideas.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ben.greatideas.models.User;
import com.ben.greatideas.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	//hash password and register user
	public User registerUser(User user) {
		String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashedPassword);
		return userRepository.save(user);
	}
	
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public User findById(Long id) {
		return userRepository.findById(id).orElse(null);
	}
	
	//authenticate user's login information
	public boolean authenticateUser(String email, String password) {
		//1.retrieve the user by email 
		User user = userRepository.findByEmail(email);
		
		//2.no user with this email found, cannot authenticate
		if(user == null) {
			return false;
		}else {
			//3.user with this email found, verify its password
			if(BCrypt.checkpw(password, user.getPassword())) {
				return true;
			}else {
				return false;
			}
		}
	}
}
