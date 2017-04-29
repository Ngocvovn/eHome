package com.duyngoc.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.duyngoc.model.Apartment;
import com.duyngoc.service.ApartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)

public class ApartmentControllerTest {
	
	@LocalServerPort
	int port;
	
	@Autowired
	ApartmentService service;
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	private JacksonTester<Apartment> json;
	
	@Before
	public void setup(){
		ObjectMapper objectMapper= new ObjectMapper();
		JacksonTester.initFields(this, objectMapper);
	}
	

	
	@Before
	public void createData() throws Exception{
		Apartment apartment = new Apartment();
		apartment.setId((long) 1);
		apartment.setCity("Helsinki");
		apartment.setOwner("ehome");
		service.save(apartment);
	
	}
	
	public Apartment createApartmentForTest(Long id, String city,String owner){
		Apartment apartment = new Apartment();
		apartment.setId(id);
		apartment.setCity(city);
		apartment.setOwner(owner);
		return apartment;
	}
	
	@Test
	public void serializeJson() throws IOException{
		Apartment apartment = createApartmentForTest((long)10, "Helsinki", "ehome");
		apartment.setOwner("ehome");
		System.out.println(json.write(apartment));
		assertThat(json.write(apartment)).extractingJsonPathArrayValue("ehome", "@.owner");
	}

	
	
	@Test
	public void shouldReturn200() throws Exception{
	
		ResponseEntity<List> entity = this.testRestTemplate.getForEntity("http://localhost:"+port+"/api/apartment/ehome", List.class);
		
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		
	}
	
	@Test
	public void shouldReturnJson() throws Exception{
		ResponseEntity<List> entity = this.testRestTemplate.getForEntity("http://localhost:"+port+"/api/apartment/ehome", List.class);
		
		assertTrue(entity.getHeaders().getContentType().toString().contains("application/json"));
	}
	@Test
	public void returnListCorrectly() throws Exception{
		ResponseEntity<List> entity = this.testRestTemplate.getForEntity("http://localhost:"+port+"/api/apartment/ehome", List.class);
		assertTrue(entity.getBody().size()==1);
		assertTrue(entity.getBody().get(0).toString().contains("owner=ehome"));		
		
	}
	
	@Test
	public void createNewSuccefully(){
		Apartment apartment =createApartmentForTest((long)2, "Helsinki", "ehome2");
		ResponseEntity<Apartment> entity = testRestTemplate.postForEntity("http://localhost:"+port+"/api/apartment/", apartment, Apartment.class);
		assertNotNull(entity.getBody());
		assertTrue(entity.getBody().getId()==2&&entity.getBody().getOwner().equals("ehome2"));	
		assertEquals(entity.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void updateSuccessfully() throws Exception{
		Apartment apartment = createApartmentForTest((long)1, "Espoo", "ehome");
		ResponseEntity<Apartment> entity = testRestTemplate.postForEntity("http://localhost:"+port+"/api/apartment/", apartment, Apartment.class);
		assertNotNull(entity.getBody());
		assertTrue(entity.getBody().getId()==1&&entity.getBody().getCity().equals("Espoo"));
	}
	
	
	
	
}
