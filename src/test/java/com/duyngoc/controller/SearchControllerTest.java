package com.duyngoc.controller;

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
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


	private ApartmentService service;
	
	@Autowired
	private ApartmentRepository repo;

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Before
	public void generateData() throws IOException, ClassNotFoundException {
		

		FileInputStream fileIn = new FileInputStream("D:/snake/apartments.txt");
		ObjectInputStream in = new ObjectInputStream(fileIn);
		List<Apartment> list = (ArrayList<Apartment>) in.readObject();
		in.close();
		fileIn.close();
		System.out.println("size" + list.size());
		repo.save(list);
	

	}

	@Test
	public void getAll() {
		assertTrue(true);
	}
}
