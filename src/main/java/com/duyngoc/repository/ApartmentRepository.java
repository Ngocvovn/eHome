package com.duyngoc.repository;

import java.util.List;
import java.util.TreeSet;

import javax.naming.directory.SearchResult;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.duyngoc.model.Apartment;

public interface ApartmentRepository extends CrudRepository<Apartment, Long> {
	TreeSet<Apartment> findByCity(String city);

	TreeSet<Apartment> findByStreet(String street);

	List<Apartment> findByOwner(String owner);

	@Query("select apartment from Apartment apartment where apartment.city like %?1% and apartment.street like %?2% AND"
			+ " apartment.price>=?3 and apartment.price<=?4 and apartment.bedrooms=?5 and"
			+ " apartment.lotSizeSqFt>=?6 and apartment.lotSizeSqFt<=?7 and apartment.parkingType like %?8%")
	List<Apartment> searchResultWithBedrooms(String city, String street, double minPrice, double maxPrice, int bedrooms,
			float minArea, float maxArea, String garage);

	@Query("select apartment from Apartment apartment where apartment.city like %?1% and apartment.street like %?2% AND"
			+ " apartment.price>=?3 and apartment.price<=?4 and apartment.bedrooms=?5 and apartment.bathrooms=?6"
			+ " and apartment.lotSizeSqFt>=?7 and apartment.lotSizeSqFt<=?8 and apartment.parkingType like %?9%")
	List<Apartment> searchResult(String city, String street, double minPrice, double maxPrice, int bedrooms,
			float bathrooms, float minArea, float maxArea, String garage);

	@Query("select apartment from Apartment apartment where apartment.city like %?1% and apartment.street like %?2% AND"
			+ " apartment.price>=?3 and apartment.price<=?4 and apartment.bathrooms=?5 and"
			+ " apartment.lotSizeSqFt>=?6 and apartment.lotSizeSqFt<=?7 and apartment.parkingType like %?8%")
	List<Apartment> searchResultWithBathrooms(String city, String street, double minPrice, double maxPrice,
			float bathrooms, float minArea, float maxArea, String garage);

	@Query("select apartment from Apartment apartment where apartment.city like %?1% and apartment.street like %?2% "
			+ "AND apartment.price>=?3 and apartment.price<=?4 and apartment.lotSizeSqFt>=?5"
			+ " and apartment.lotSizeSqFt<=?6 and apartment.parkingType like %?7%")
	List<Apartment> searchResultWithoutRooms(String city, String street, double minPrice, double maxPrice,
			float minArea, float maxArea, String garage);

}
