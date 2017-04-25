package com.duyngoc.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.duyngoc.model.Apartment;

public interface ApartmentRepository extends CrudRepository<Apartment, Long> {
	List<Apartment> findByCity(String city);
	Apartment findByStreet(String street);

}
