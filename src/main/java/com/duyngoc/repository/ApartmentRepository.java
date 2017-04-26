package com.duyngoc.repository;

import java.util.List;
import java.util.TreeSet;

import org.springframework.data.repository.CrudRepository;

import com.duyngoc.model.Apartment;

public interface ApartmentRepository extends CrudRepository<Apartment, Long> {
	TreeSet<Apartment> findByCity(String city);
	TreeSet<Apartment> findByStreet(String street);
	List<Apartment> findByOwner(String owner);

}
