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
@RequestMapping("/api")

public class ApartmentController {
	
	@Autowired
	private ApartmentService service;
	

	
	@RequestMapping(value = "/apartment/{owner}", method = RequestMethod.POST)
	public ResponseEntity<?> addAparrtment(@RequestBody Apartment apartment,@PathVariable String owner) {
		try{
			
			apartment.setOwner(owner);
			service.save(apartment);
			return new ResponseEntity<Apartment>(apartment, HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<String>("can not save please check again"+ e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		
	}
	@RequestMapping(value = "/apartment/{owner}", method = RequestMethod.GET)
	public ResponseEntity<?> getApartmentofOwner(@PathVariable String owner){
		try {
			return new ResponseEntity<List<Apartment>>(service.getApartmentsByOwner(owner),HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/apartment/{id}", method= RequestMethod.DELETE)
	public ResponseEntity<?> deleteApartment(@PathVariable Long id){
		try {
			service.delete(id);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
