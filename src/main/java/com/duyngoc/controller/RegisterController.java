package com.duyngoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.duyngoc.model.User;
import com.duyngoc.repository.UserRepository;

@RestController
@RequestMapping("api/public/register")
public class RegisterController {
	
	@Autowired
	private UserRepository userRepository;
	
	private static final int MAX=1000000;
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addUser(@RequestBody User user) {
		try {
			System.out.println(user.getName());
			if (userRepository.findByUsername(user.getUsername()) != null && user.getId() == MAX) {
				return new ResponseEntity<Exception>(new IllegalArgumentException(), HttpStatus.NOT_ACCEPTABLE);
			}
			user.setRole("ROLE_USER");
			userRepository.save(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
