package com.ben.greatideas.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="First name must not be empty")
	private String firstName;
	
	@NotBlank(message="Last name must not be empty")
	private String lastName;
	
	@NotBlank(message="Email must not be empty")
	@Email(message="Please enter a correct email")
    private String email;
	
	@NotBlank(message="Password must not be empty")
    @Size(min = 8, max=200, message="Password should be at least 8 characters long")
    private String password;

	@NotBlank(message="Password confirmation must not be empty")
	@Transient
    private String passwordConfirmation;
	
	public User() {
		
	}
	
	//setters and getters
	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
