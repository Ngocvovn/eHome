package com.duyngoc.controller;

import java.util.TreeSet;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.duyngoc.model.Apartment;
import com.duyngoc.repository.ApartmentRepository;
import com.duyngoc.service.ApartmentService;

@RestController
@RequestMapping("/api/search")
@CrossOrigin
public class SearchController {

	@Autowired
	private ApartmentService service;

	@Autowired
	private ApartmentRepository repo;

	@RequestMapping(value = "/{city}/{street}", method = RequestMethod.GET)
	public ResponseEntity<?> getApartment(@PathVariable String city, @PathVariable String street) {
		try {
			return new ResponseEntity<TreeSet<Apartment>>(service.getDataFromStreet(city, street), HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("errow" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getApartmentQueryParams(
			@RequestParam(name = "city", required = false, defaultValue = " ") String city,
			@RequestParam(name = "streets", required = false, defaultValue = " ") String street,
			@RequestParam(name = "minPrice", required = false, defaultValue = "0") double minPrice,
			@RequestParam(name = "maxPrice", required = false, defaultValue = "10000000") double maxPrice,
			@RequestParam(name = "bedrooms", required = false, defaultValue = "100") int bedrooms) {
		try {
			return new ResponseEntity<List<Apartment>>(service.customSearch(city, street, minPrice, maxPrice, bedrooms),
					HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("errow" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/price", method = RequestMethod.GET)
	public ResponseEntity<?> searchPrice(
			@RequestParam(name = "minprice", required = false, defaultValue = "1000") double minprice,
			@RequestParam(name = "maxprice", required = false, defaultValue = "10000000") double maxprice,
			@RequestParam(name = "city", required = false, defaultValue = "") String city,
			@RequestParam(name = "streets", required = false, defaultValue = "") String street) {
		return new ResponseEntity<List<Apartment>>(repo.searchPrice(minprice, maxprice, city, street), HttpStatus.OK);
	}
}
