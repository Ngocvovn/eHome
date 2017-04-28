package com.duyngoc.repository;

import java.util.List;
import java.util.TreeSet;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.duyngoc.model.Apartment;

public interface ApartmentRepository extends CrudRepository<Apartment, Long> {
	TreeSet<Apartment> findByCity(String city);
	TreeSet<Apartment> findByStreet(String street);
	List<Apartment> findByOwner(String owner);
	@Query("select apartment from Apartment apartment where apartment.city like %?1% and apartment.street like %?2% AND apartment.price>=?3 and apartment.price<=?4 and apartment.bedrooms=?5")
	List<Apartment> searchResultWithBedrooms(String city, String street,double minPrice, double maxPrice,int bedrooms);
	@Query("select apartment from Apartment apartment where apartment.city like %?1% and apartment.street like %?2% AND apartment.price>=?3 and apartment.price<=?4")
	List<Apartment> searchResultWithoutBedrooms(String city, String street,double minPrice, double maxPrice);
	@Query("select apartment from Apartment apartment where apartment.city=?3 and apartment.price>?1 and apartment.price<?2")
	List<Apartment> searchPrice(double minprice,double maxprice,String city,String street);
}
