package com.duyngoc.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.duyngoc.model.Apartment;
import com.duyngoc.repository.ApartmentRepository;
import com.duyngoc.service.ApartmentService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SearchControllerTest {
	private static final long serialVersionUID = 1L;
	@LocalServerPort
	int port;

	@Autowired
	private Environment env;

	private ApartmentService service;

	@Autowired
	private ApartmentRepository repo;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Before
	public void generateData() throws IOException, ClassNotFoundException {

		String location = env.getProperty("blackJack.apartments.path");

		FileInputStream fileIn = new FileInputStream(location);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		List<Apartment> list = (ArrayList<Apartment>) in.readObject();
		in.close();
		fileIn.close();
		System.out.println("size" + list.size());
		repo.save(list);

	}

	@Test
	public void repoCreated() {
		assertNotNull(repo);
	}

	@Test
	public void DatabasePopulated() {
		assertNotNull(repo.findOne((long) 1));
	}

	@Test
	public void callSuccessfully() {
		ResponseEntity<List> entity = this.testRestTemplate
				.getForEntity("http://localhost:" + port + "/api/public/search?city=seattle", List.class);

		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}
	
	@Test
	public void searchByDefaultSuccessfully(){
		ResponseEntity<List> entity = this.testRestTemplate
				.getForEntity("http://localhost:" + port + "/api/public/search?", List.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertTrue(entity.getBody().size()>0);
		
	}
	@Test
	public void searchByBedrooms(){
		ResponseEntity<List> entity = this.testRestTemplate
				.getForEntity("http://localhost:" + port + "/api/public/search?bedrooms=4", List.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		boolean check = true;
		for(int i =0;i<entity.getBody().size();i++){
			Map a= (Map) entity.getBody().get(i);
			if((int)a.get("bedrooms")!=4){
				check=false;
			}
		}
		assertTrue(check);
	}
	
	@Test
	public void searchByBathrooms(){
		ResponseEntity<List> entity = this.testRestTemplate
				.getForEntity("http://localhost:" + port + "/api/public/search?bathrooms=2", List.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		boolean check = true;
		for(int i =0;i<entity.getBody().size();i++){
			Map a= (Map) entity.getBody().get(i);
			if((double)a.get("bathrooms")!=2){
				check=false;
			}
		}
		assertTrue(check);
	}
	
	@Test
	public void searchByminPrice(){
		ResponseEntity<List> entity = this.testRestTemplate
				.getForEntity("http://localhost:" + port + "/api/public/search?minPrice=5000", List.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		boolean check = true;
		for(int i =0;i<entity.getBody().size();i++){
			Map a= (Map) entity.getBody().get(i);
			if((double)a.get("price")<5000){
				check=false;
			}
		}
		assertTrue(check);
	}
	
	@Test
	public void searchBymaxPrice(){
		ResponseEntity<List> entity = this.testRestTemplate
				.getForEntity("http://localhost:" + port + "/api/public/search?maxPrice=8000", List.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		boolean check = true;
		for(int i =0;i<entity.getBody().size();i++){
			Map a= (Map) entity.getBody().get(i);
			if((double)a.get("price")>8000){
				check=false;
			}
		}
		assertTrue(check);
	}
	
	@Test
	public void searchByCity(){
		ResponseEntity<List> entity = this.testRestTemplate
				.getForEntity("http://localhost:" + port + "/api/public/search?city=Seattle", List.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		boolean check = true;
		for(int i =0;i<entity.getBody().size();i++){
			Map a= (Map) entity.getBody().get(i);
			if(!a.get("city").toString().contains("Seattle")){
				check=false;
			}
		}
		assertTrue(check);
	}
	
	@Test
	public void searchByFullAddress(){
		ResponseEntity<List> entity = this.testRestTemplate
				.getForEntity("http://localhost:" + port + "/api/public/search?street=1525 TayLor Ave", List.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		boolean check = true;
		for(int i =0;i<entity.getBody().size();i++){
			Map a= (Map) entity.getBody().get(i);
			if(!a.get("city").toString().contains("1525 TayLor Ave")){
				check=false;
			}
		}
		
		assertTrue(entity.getBody().size()>0);
	}
	
	@Test
	public void searchByStreet(){
		ResponseEntity<List> entity = this.testRestTemplate
				.getForEntity("http://localhost:" + port + "/api/public/search?street=Ave", List.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		boolean check = true;
		for(int i =0;i<entity.getBody().size();i++){
			Map a= (Map) entity.getBody().get(i);
			if(!a.get("city").toString().contains("Ave")){
				check=false;
			}
		}
		
		assertTrue(entity.getBody().size()>0);
	}


	
	
}
