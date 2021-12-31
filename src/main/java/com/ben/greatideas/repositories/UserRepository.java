package com.ben.greatideas.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ben.greatideas.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	    User findByEmail(String email);
}
