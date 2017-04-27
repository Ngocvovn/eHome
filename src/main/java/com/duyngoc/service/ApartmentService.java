package com.duyngoc.service;

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

	public TreeSet<Apartment> getDataFromStreet(String city, String street) {

		TreeSet<Apartment> apartments = new TreeSet<>();
		if (city.equals("all") && street.equals("all")) {
			apartments.addAll((Collection<? extends Apartment>) aparmentRepository.findAll());

		} else if (street.equals("all")) {
			System.out.println("card");
			apartments= aparmentRepository.findByCity(city);
		} else {
			apartments= findStreet(city, street);
		}
		
		addImagesOfApartment(apartments);
		return apartments;

	}

	public TreeSet<Apartment> findStreet(String city, String street) {
		TreeSet<Apartment> apartments = new TreeSet<>();
		if(!city.equals("all")){
			apartments = aparmentRepository.findByCity(city);
		}
		else{
			apartments.addAll((Collection<? extends Apartment>) aparmentRepository.findAll());
		}

	

		Iterator<Apartment> iterator = apartments.iterator();
		while (iterator.hasNext()) {
			if (!iterator.next().getStreet().contains(street)) {
				iterator.remove();
			}
		}

		return apartments;
	}
	
	public void addImagesOfApartment(Collection<Apartment> apartments){
		Iterator<Apartment> iterator = apartments.iterator();
		
		while(iterator.hasNext()){
			Apartment apartment = iterator.next();
			apartment.setImageUrls(imageRepository.findByAparmentid(apartment.getApartmentId()));
		}
	}
	
	public List<Apartment> getApartmentsByOwner(String owner){
		List<Apartment> apartments = aparmentRepository.findByOwner(owner);
		addImagesOfApartment(apartments);
		return apartments;
	}
	
	public Apartment save(Apartment apartment){
		System.out.println("ngoc");
		return aparmentRepository.save(apartment);
	}

}
