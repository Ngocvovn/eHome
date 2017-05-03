package com.duyngoc.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import com.duyngoc.model.Apartment;
import com.duyngoc.model.User;
import com.duyngoc.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	@Autowired
	private UserRepository userRepository;

	private JacksonTester<Apartment> json;

	@Before
	public void setup() {
		ObjectMapper objectMapper = new ObjectMapper();
		JacksonTester.initFields(this, objectMapper);
	}

	@Before
	public void createUser() {
		User user = new User();
		user.setId((long) 1000);
		user.setUsername("a");
		user.setRole("ROLE_USER");
		User employee = new User();
		employee.setId((long) 1000);
		employee.setUsername("b");
		employee.setRole("ROLE_EMPLOYEE");
		userRepository.save(user);
		userRepository.save(employee);

	}

	@Test
	public void createNewUser() {
		User user = new User();
		user.setId((long) 100000);
		user.setUsername("ngoc");
		user = userRepository.save(user);
		assertNotNull(user);
		assertTrue(user.getUsername().equals("ngoc"));
	}

	@Test
	public void updateUser() {
		List<User> users = (List<User>) userRepository.findAll();
		for (User u : users) {
			System.out.println(u.getUsername() + u.getId());
		}
		User user = userRepository.findByUsername("a");
		user.setName("c");
		User update = userRepository.save(user);
		assertTrue(user.getId() == update.getId());
		assertTrue(update.getName().equals("c"));
	}

}
