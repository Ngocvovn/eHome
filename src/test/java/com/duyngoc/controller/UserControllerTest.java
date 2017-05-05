package com.duyngoc.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.duyngoc.model.Apartment;
import com.duyngoc.model.User;
import com.duyngoc.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

	@LocalServerPort
	int port;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TestRestTemplate testRestTemplate;

	private JacksonTester<Apartment> json;

	@Before
	public void setup() {
		ObjectMapper objectMapper = new ObjectMapper();
		JacksonTester.initFields(this, objectMapper);
	}


	@Before
	public void createUser() {
		User user = new User();
		user.setId((long) 100);
		user.setUsername("vo");
		user.setRole("ROLE_USER");
		User employee = new User();
		employee.setId((long) 100);
		employee.setUsername("jim");
		employee.setRole("ROLE_EMPLOYEE");
		User employee2 = new User();
		employee2.setId((long) 100);
		employee2.setUsername("anna");
		employee2.setRole("ROLE_EMPLOYEE");
		userRepository.save(user);
		userRepository.save(employee);
		userRepository.save(employee2);

	}

	@Test
	public void updateSuccessfully() {
		User user = userRepository.findByUsername("vo");
		assertNotNull(user);
		assertTrue(user.getUsername().equals("vo"));
		user.setUsername("vu");
		userRepository.save(user);
		List<User> users = (List<User>) userRepository.findAll();
		assertNotNull(users);
		assertTrue(users.size() == 3);
		assertTrue(users.get(0).getUsername().equals("vu"));

	}

	@Test
	public void createNewUser() {
		User user = new User();
		user.setId((long) 1000000);
		user.setUsername("ngoc");
		user = userRepository.save(user);
		assertNotNull(user);
		assertTrue(user.getUsername().equals("ngoc"));
	}

	@Test
	public void updateUserSuccessfully() {
		User user = userRepository.findByUsername("vo");
		System.out.println(user.getUsername() + user.getId());
		user.setUsername("jack");
		ResponseEntity<User> entity = this.testRestTemplate.postForEntity("http://localhost:" + port + "/api/users",
				user, User.class);
		User update = entity.getBody();
		assertTrue(user.getId() == update.getId());
		assertTrue(update.getUsername().equals("jack"));
	}

	@Test(expected = Exception.class)
	public void createEmployeeWithExistedUsername() {
		User user = new User();
		user.setId((long) 1000000);
		user.setUsername("jim");
		ResponseEntity<User> entity = this.testRestTemplate.postForEntity("http://localhost:" + port + "/api/employees",
				user, User.class);
	}

	@Test
	public void createEmployeeSuccessfully() {
		User user = new User();
		user.setId((long) 1000000);
		user.setUsername("jacklondon");
		ResponseEntity<User> entity = this.testRestTemplate.postForEntity("http://localhost:" + port + "/api/employees",
				user, User.class);
		
		user = entity.getBody();
		assertNotNull(user);
		assertTrue(user.getUsername().equals("jacklondon"));
	}
	
	
	
	


}