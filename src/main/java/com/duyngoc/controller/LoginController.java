package com.duyngoc.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.duyngoc.model.User;
import com.duyngoc.repository.UserRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class LoginController {
	
	@Autowired
	private UserRepository userRepository;
	

	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<?> login(Principal principal){
		try {
			System.out.println("login "+ principal.getName());
			return new ResponseEntity<User>(userRepository.findByUsername(principal.getName()), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	public ResponseEntity<?> getCurrentUser(){
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			User user = userRepository.findByUsername(auth.getName());
			return new ResponseEntity<User>(user, HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

}
