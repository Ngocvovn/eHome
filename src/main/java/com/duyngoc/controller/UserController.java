package com.duyngoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.duyngoc.model.User;
import com.duyngoc.repository.UserRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {
	private static final int MAX = 1000000;

	@Autowired
	private UserRepository userRepo;

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<?> addUser(@RequestBody User user) {
		try {
			userRepo.save(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/employee", method = RequestMethod.POST)
	public ResponseEntity<?> addEmployee(@RequestBody User employee) {
		try {
			if (userRepo.findByUsername(employee.getUsername()) != null && employee.getId() == MAX) {
				return new ResponseEntity<String>("Username has existed", HttpStatus.NOT_ACCEPTABLE);
			}

			employee.setRole("ROLE_EMPLOYEE");
			userRepo.save(employee);
			return new ResponseEntity<User>(employee, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
