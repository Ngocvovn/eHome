package com.duyngoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.duyngoc.model.User;
import com.duyngoc.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {
	private static final int MAX = 1000000;

	@Autowired
	private UserRepository userRepo;
	
	@RequestMapping(value="/users", method= RequestMethod.GET)
	public ResponseEntity<?> getAllUsers(){
		try {
			return new ResponseEntity<List<User>>(userRepo.findByRole("ROLE_USER"),HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/employees", method= RequestMethod.GET)
	public ResponseEntity<?> getAllEmployees(){
		try {
			return new ResponseEntity<List<User>>(userRepo.findByRole("ROLE_EMPLOYEE"),HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResponseEntity<?> updateUser(@RequestBody User user) {
		try {
			userRepo.save(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/employees", method = RequestMethod.POST)
	public ResponseEntity<?> addEmployee(@RequestBody User employee) {
		try {
			if (userRepo.findByUsername(employee.getUsername()) != null && employee.getId() == MAX) {
				
				//throw new Exception("Username has existed");
				return new ResponseEntity<Exception>(new Exception("Username has existed"), HttpStatus.NOT_FOUND);
			}

			employee.setRole("ROLE_EMPLOYEE");
			userRepo.save(employee);
			return new ResponseEntity<User>(employee, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/users/{id}", method= RequestMethod.DELETE)
	public ResponseEntity<?> deleteApartment(@PathVariable Long id){
		try {
			userRepo.delete(id);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
