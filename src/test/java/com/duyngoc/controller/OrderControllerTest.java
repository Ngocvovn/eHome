package com.duyngoc.controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.endsWith;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

import com.duyngoc.model.Order;
import com.duyngoc.repository.OrderRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class OrderControllerTest {

	@LocalServerPort
	int port;

	@Autowired
	private OrderRepository repository;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Before
	public void populateData() {
		Order order = new Order();
		order.setAdditionalInfomation("ngoc");
		order.setId((long) 10000);
		order.setDate(new Date());
		order.setUserId((long)1000);
		repository.save(order);

	}

	@Test
	public void isNotEmpty() {
		assertTrue(repository.count() > 0);
	}

	@Test
	public void addOrderSuccessfully() {
		Order order = new Order();
		order.setAdditionalInfomation("bao");
		order.setId((long) 10000);
		order.setDate(new Date());
		ResponseEntity<Order> entity = this.testRestTemplate
				.postForEntity("http://localhost:" + port + "/api/private/orders", order, Order.class);
		assertTrue(repository.count() == 2);
		assertTrue(entity.getBody().getAdditionalInfomation().equals("bao"));
	}

	@Test
	public void updateOrderSuccessfully() {
		Order order = repository.findOne((long)1);
		order.setAdditionalInfomation("gfarming");
		ResponseEntity<Order> entity = this.testRestTemplate
				.postForEntity("http://localhost:" + port + "/api/private/orders", order, Order.class);
		assertTrue(entity.getBody().getAdditionalInfomation().equals("gfarming"));
		assertTrue(repository.findOne((long)1).getAdditionalInfomation().equals("gfarming"));
	}
	@Test
	public void getOrdersByOwnerSuccessfully(){
		ResponseEntity<List> entity = this.testRestTemplate
				.getForEntity("http://localhost:" + port + "/api/private/orders/1000", List.class);
		assertTrue(entity.getBody().size()>0);
		boolean check=true;
		for(int i =0;i<entity.getBody().size();i++){
			Map a= (Map) entity.getBody().get(i);
			if((int)a.get("userId")!=1000){
				check=false;
			}
		}
		assertTrue(check);
	}
	

}
