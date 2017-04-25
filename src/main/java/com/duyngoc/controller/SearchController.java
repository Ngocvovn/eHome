package com.duyngoc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.duyngoc.model.Apartment;
import com.duyngoc.repository.ApartmentRepository;
import com.duyngoc.repository.ImageUrlRepostiory;

@RestController
@RequestMapping("api/search")
public class SearchController {
	@Autowired
	private ApartmentRepository repo;
	
	@RequestMapping(value = "/{city}", method = RequestMethod.GET)
	public ResponseEntity<?> listApartmentsByCity(@PathVariable String city) {
		try {
			List<Apartment> apartments = repo.findByCity(city);
			return new ResponseEntity<List<Apartment>>(apartments,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("errow"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@RequestMapping(value = "/{city}/{street}", method = RequestMethod.GET)
	public ResponseEntity<?> getApartment(@PathVariable String city, @PathVariable String street){
		try {
			List<Apartment> result = new ArrayList<>();
			List<Apartment> apartments = repo.findByCity(city);
			for(Apartment apartment:apartments){
				if(apartment.getStreet().contains(street)){
					result.add(apartment);				}
			}
			return new ResponseEntity<List<Apartment>>(result,HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("errow"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
