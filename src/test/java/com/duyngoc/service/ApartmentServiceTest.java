package com.duyngoc.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
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
	public void saveSuccessfully() throws Exception{
		Apartment tes = new Apartment();
		tes.setId((long) 20);
		tes.setStreet("lintu");
		System.out.println(tes.getStreet()+tes);
		Apartment apartment= apartmentService.save(tes);
		assertEquals("lintu", apartment.getStreet());
		assertNull(apartment.getImageUrls());
	}
	
	
}
