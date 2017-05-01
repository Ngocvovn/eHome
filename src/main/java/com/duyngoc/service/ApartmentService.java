package com.duyngoc.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duyngoc.model.Apartment;
import com.duyngoc.repository.ApartmentRepository;
import com.duyngoc.repository.ImageUrlRepostiory;

@Service
public class ApartmentService {

	@Autowired
	private ApartmentRepository aparmentRepository;

	@Autowired
	private ImageUrlRepostiory imageRepository;




	public void addImagesOfApartment(Collection<Apartment> apartments) {
		Iterator<Apartment> iterator = apartments.iterator();

		while (iterator.hasNext()) {
			Apartment apartment = iterator.next();
			apartment.setImageUrls(imageRepository.findByAparmentid(apartment.getApartmentId()));
		}
	}

	public List<Apartment> getApartmentsByOwner(String owner) {
		List<Apartment> apartments = aparmentRepository.findByOwner(owner);
		addImagesOfApartment(apartments);
		return apartments;
	}

	public Apartment save(Apartment apartment) {

		return aparmentRepository.save(apartment);
	}

	public List<Apartment> customSearch(String city, String street, double minPrice, double maxPrice, int bedrooms,
			float bathrooms, float minArea, float maxArea, String garage) {
		List<Apartment> apartments = new ArrayList<>();
		if (bedrooms >= 100 && bathrooms >= 100) {
			apartments = aparmentRepository.searchResultWithoutRooms(city, street, minPrice, maxPrice, minArea, maxArea,
					garage);
		} else if (bedrooms >= 100) {
			apartments = aparmentRepository.searchResultWithBathrooms(city, street, minPrice, maxPrice, bathrooms,
					minArea, maxArea, garage);
		} else if (bathrooms >= 100) {
			System.out.println("here");
			apartments = aparmentRepository.searchResultWithBedrooms(city, street, minPrice, maxPrice, bedrooms,
					minArea, maxArea, garage);
		} else {
			apartments = aparmentRepository.searchResult(city, street, minPrice, maxPrice, bedrooms, bathrooms, minArea,
					maxArea, garage);
		}
		addImagesOfApartment(apartments);
		return apartments;
	}

}
