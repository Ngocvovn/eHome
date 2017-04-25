package com.duyngoc.controller;

import java.util.ArrayList;
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

import com.duyngoc.model.Apartment;
import com.duyngoc.model.ImageUrl;
import com.duyngoc.model.Student;
import com.duyngoc.repository.ApartmentRepository;
import com.duyngoc.repository.ImageUrlRepostiory;
@RestController
@RequestMapping("api/apartment")
@CrossOrigin
public class ApartmentController {
	
	@Autowired
	private ApartmentRepository repo;
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> addAparrtment(@RequestBody Apartment apartment) {
		try{
			repo.save(apartment);
			return new ResponseEntity<Apartment>(apartment, HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<String>("can not save please check again"+ e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		
	}


}
