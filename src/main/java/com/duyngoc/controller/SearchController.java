package com.duyngoc.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.TreeSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.duyngoc.model.Apartment;
import com.duyngoc.repository.ApartmentRepository;
import com.duyngoc.repository.ImageUrlRepostiory;
import com.duyngoc.repository.UserRepository;
import com.duyngoc.service.ApartmentService;

@RestController
@RequestMapping("/api/public/search")
@CrossOrigin
public class SearchController {

	@Autowired
	private ApartmentService service;

	@Autowired
	private ImageUrlRepostiory repo;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getApartmentQueryParams(
			@RequestParam(name = "city", required = false, defaultValue = " ") String city,
			@RequestParam(name = "streets", required = false, defaultValue = " ") String street,
			@RequestParam(name = "minPrice", required = false, defaultValue = "0") double minPrice,
			@RequestParam(name = "maxPrice", required = false, defaultValue = "10000000") double maxPrice,
			@RequestParam(name = "bedrooms", required = false, defaultValue = "100") int bedrooms,
			@RequestParam(name = "bathrooms", required = false, defaultValue = "100") float bathrooms,
			@RequestParam(name = "minArea", required = false, defaultValue = "0") float minArea,
			@RequestParam(name = "maxArea", required = false, defaultValue = "1000000") float maxArea,
			@RequestParam(name = "garage", required = false, defaultValue = "yes") String garage) {
		try {

			return new ResponseEntity<Set<Apartment>>(service.customSearch(city, street, minPrice, maxPrice, bedrooms,
					bathrooms, minArea, maxArea, garage), HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
