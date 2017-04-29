package com.duyngoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.duyngoc.model.Apartment;
import com.duyngoc.service.ApartmentService;
@RestController
@RequestMapping("api/apartment")
@CrossOrigin
public class ApartmentController {
	
	@Autowired
	private ApartmentService service;
	

	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> addAparrtment(@RequestBody Apartment apartment) {
		try{
			
			System.out.println(apartment.getStreet()+"  "+apartment.getId());
			service.save(apartment);
			return new ResponseEntity<Apartment>(apartment, HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<String>("can not save please check again"+ e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		
	}
	@RequestMapping(value = "/{owner}", method = RequestMethod.GET)
	public ResponseEntity<?> getApartmentofOwner(@PathVariable String owner){
		try {
			return new ResponseEntity<List<Apartment>>(service.getApartmentsByOwner(owner),HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
