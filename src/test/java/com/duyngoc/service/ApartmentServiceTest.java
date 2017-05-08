package com.duyngoc.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.duyngoc.model.Apartment;
import com.duyngoc.repository.ApartmentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApartmentServiceTest {
	
	@Autowired
	private ApartmentService apartmentService;
	
	@Autowired
	private ApartmentRepository apartmentRepository;

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
		TreeSet<Apartment> apartments = (TreeSet<Apartment>) apartmentService.customSearch("Helsinki", "Lintu", 1000, 4000, 4,100,0,100000,"garage");
		assertNotNull(apartments);
		assertEquals(1, apartments.size());
		assertEquals("Helsinki", apartments.first().getCity());
	}
	
	public void testCustomSearchDifferentBedrooms(){
		Apartment tes = new Apartment();
		tes.setCity("Helsinki");
		tes.setStreet("Lintu");
		tes.setPrice(3000);
		tes.setBedrooms(3);
		tes.setParkingType("garage");
		apartmentService.save(tes);
		System.out.println(tes);
		Set<Apartment> apartments = apartmentService.customSearch("Helsinki", "Lintu", 1000, 4000, 4,100,0,100000,"garage");
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
		tes.setParkingType("garage");
		apartmentService.save(tes);
		Set<Apartment> apartments = apartmentService.customSearch("Helsinki", "Lintu",0, 4000, 4,100,0,100000,"Yes");
		assertNotNull(apartments);
		assertEquals(1, apartments.size());
	}
	
	@Test
	public void reformatParams(){
		String city ="SeaTtle";
		assertEquals("seattle", apartmentService.reformatParam(city));
	}
	@Test
	public void reformatParams2(){
		String city ="seattle";
		assertEquals("seattle", apartmentService.reformatParam(city));
	}
	
	@Test
	public void formatGarage(){
		String garage = "Yes";
		assertEquals("garage", apartmentService.formatGarage(garage));
	}
	
	@Test
	public void formatGarage2(){
		String garage = "No";
		assertEquals("no", apartmentService.formatGarage(garage));
	}
	
	@Test
	public void formatGarage3(){
		String garage = "yes";
		assertEquals("no", apartmentService.formatGarage(garage));
	}
	
	
}
