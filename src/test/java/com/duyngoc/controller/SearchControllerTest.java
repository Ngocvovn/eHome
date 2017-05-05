package com.duyngoc.controller;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
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
import org.springframework.test.context.junit4.SpringRunner;

import com.duyngoc.model.Apartment;
import com.duyngoc.model.ImageUrl;
import com.duyngoc.repository.ApartmentRepository;
import com.duyngoc.service.ApartmentService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class SearchControllerTest {
	@LocalServerPort
	int port;
	
	@Autowired
	private ApartmentService service;
	
	@Autowired
	private  ApartmentRepository repo;
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	private JacksonTester<Apartment> json;
	
	@Before
	public void generateData(){
		try {
			FileInputStream fileIn = new FileInputStream("D:/snake/apartments.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			List<Apartment> list = (List) in.readObject();
			System.out.println("size"+list.size());
			repo.save(list);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void getAll(){
		System.out.println(service.getApartmentsByOwner("ehome").size());
	}
}
