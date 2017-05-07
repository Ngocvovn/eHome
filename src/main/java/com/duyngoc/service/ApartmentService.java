package com.duyngoc.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duyngoc.model.Apartment;
import com.duyngoc.model.ImageUrl;
import com.duyngoc.repository.ApartmentRepository;
import com.duyngoc.repository.ImageUrlRepostiory;
import com.duyngoc.repository.UserRepository;

@Service
public class ApartmentService {

	@Autowired
	private ApartmentRepository aparmentRepository;

	@Autowired
	private ImageUrlRepostiory imageRepository;

	public List<ImageUrl> getAllApartments() {
		return (List<ImageUrl>) imageRepository.findAll();
	}

	public void addImagesOfApartment(Collection<Apartment> apartments) {
		Iterator<Apartment> iterator = apartments.iterator();

		while (iterator.hasNext()) {
			Apartment apartment = iterator.next();
			apartment.setImageUrls(imageRepository.findByApartmentId(apartment.getApartmentId()));
		}
	}

	public List<Apartment> getApartmentsByOwner(String owner) {
		List<Apartment> apartments = aparmentRepository.findByOwner(owner);
		addImagesOfApartment(apartments);
		return apartments;
	}

	public Apartment save(Apartment apartment) {
		imageRepository.save(apartment.getImageUrls());
		apartment.setImageUrls(null);
		return aparmentRepository.save(apartment);
	}
	

	public List<Apartment> customSearch(String city, String street, double minPrice, double maxPrice, int bedrooms,
			float bathrooms, float minArea, float maxArea, String garage) {
		city = reformatParam(city);
		street= reformatParam(street);
		garage= formatGarage(garage);
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
	
	public String reformatParam(String query){
		query = query.toLowerCase();
		return query;
	}
	
	public String formatGarage(String garage){
		if(garage.equals("Yes")){
			return "garage";
		}
		return "no";
	}
	public void delete(Long id){
		imageRepository.delete(aparmentRepository.findOne(id).getImageUrls());
		aparmentRepository.delete(id);
	}
	

}
