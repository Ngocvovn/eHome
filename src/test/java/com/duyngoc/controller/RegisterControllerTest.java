package com.duyngoc.controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.endsWith;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.duyngoc.model.User;
import com.duyngoc.repository.UserRepository;
import com.duyngoc.service.ApartmentService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class RegisterControllerTest {
	@LocalServerPort
	int port;
	
	@Autowired
	private UserRepository repository;
	
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Before
	public void generateData(){
		User user = new User();
		user.setUsername("abc");
		user.setId((long)1000);
		user.setRole("ROLE_USER");
		repository.save(user);
	}
	
	@Test
	public void isNotEmpty(){
		assertTrue(repository.count()>0);
	}
	
	@Test
	public void registerUnSuccessfully(){
		User user = new User();
		user.setUsername("abc");
		user.setId((long)1000000 );
		
		ResponseEntity<Exception> entity = this.testRestTemplate.postForEntity("http://localhost:"+port+"/api/public/register",user, Exception.class);
		assertTrue(repository.count()==1);
	}
	
	@Test
	public void registerSuccessfully(){
		User user = new User();
		user.setUsername("abcd");
		user.setId((long)1000000 );
		user.setRole("ROLE_USER");
		ResponseEntity<User> entity = this.testRestTemplate.postForEntity("http://localhost:"+port+"/api/public/register",user, User.class);
		assertTrue(repository.findByUsername("abcd")!=null);
	}
}
