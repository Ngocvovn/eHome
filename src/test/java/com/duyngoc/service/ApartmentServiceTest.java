package com.duyngoc.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.duyngoc.model.Apartment;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApartmentServiceTest {
	
	@Autowired
	private ApartmentService apartmentService;
	

	@Test
	public void saveSuccessfully(){
		Apartment tes = new Apartment();
		Apartment apartment= apartmentService.save(tes);
		assertNotNull(apartment);
	}
	
	@Test
	public void setAndSaveSuccessfully(){
		Apartment tes = new Apartment();
		tes.setId((long) 20);
		tes.setStreet("lintu");
		Apartment apartment= apartmentService.save(tes);
		assertEquals("lintu", apartment.getStreet());
		assertNull(apartment.getImageUrls());
	}
	
	@Test
	public void getApartmentsByOwnerSuccessfully(){
		Apartment tes = new Apartment();
		tes.setId((long) 20);
		tes.setStreet("lintu");
		tes.setOwner("ngoc");
		apartmentService.save(tes);
		List<Apartment> apartments= apartmentService.getApartmentsByOwner("ngoc");
		assertNotNull(apartments);
		assertEquals(1, apartments.size());
		assertEquals("ngoc", apartments.get(0).getOwner());
		
		
	}
	
	public void testCustomSearchFullParameter(){
		Apartment tes = new Apartment();
		tes.setCity("Helsinki");
		tes.setStreet("Lintu");
		tes.setPrice(3000);
		tes.setBedrooms(4);
		apartmentService.save(tes);
		List<Apartment> apartments = apartmentService.customSearch("Helsinki", "Lintu", 1000, 4000, 4,100,0,100000,"garage");
		assertNotNull(apartments);
		assertEquals(1, apartments.size());
		assertEquals("Helsinki", apartments.get(0).getCity());
	}
	
	public void testCustomSearchDifferentBedrooms(){
		Apartment tes = new Apartment();
		tes.setCity("Helsinki");
		tes.setStreet("Lintu");
		tes.setPrice(3000);
		tes.setBedrooms(4);
		apartmentService.save(tes);
		List<Apartment> apartments = apartmentService.customSearch("Helsinki", "Lintu", 1000, 4000, 4,100,0,100000,"garage");
		assertNotNull(apartments);
		assertEquals(0, apartments.size());
	}
	@Test
	public void testCustomSearchWithOutBedroom(){
		Apartment tes = new Apartment();
		tes.setCity("Helsinki");
		tes.setStreet("Lintu");
		tes.setPrice(3000);
		tes.setBedrooms(4);
		apartmentService.save(tes);
		List<Apartment> apartments = apartmentService.customSearch("Helsinki", "Lintu", 1000, 4000, 4,100,0,100000,"garage");
		assertNotNull(apartments);
		assertEquals(1, apartments.size());
	}
	
}
