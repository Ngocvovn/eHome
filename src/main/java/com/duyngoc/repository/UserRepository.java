package com.duyngoc.repository;

import org.springframework.data.repository.CrudRepository;

import com.duyngoc.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String username);

}
