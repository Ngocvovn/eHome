package com.duyngoc.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.duyngoc.model.Order;
import com.duyngoc.repository.OrderRepository;

@RestController
@RequestMapping("/api")
public class OrderController {

	@Autowired
	private OrderRepository repository;
	@RequestMapping(value="/orders", method= RequestMethod.GET)
	public ResponseEntity<?> getOrders(){
		try {
			return new ResponseEntity<List<Order>>((List<Order>)repository.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@RequestMapping(value="/orders/{userId}", method= RequestMethod.GET)
	public ResponseEntity<?> getOrderByOwner(@PathVariable Long userId){
		try {
			return new ResponseEntity<List<Order>>(repository.findByUserId(userId),HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(value="/orders}", method= RequestMethod.POST)
	public ResponseEntity<?> updateOrDelete(@RequestBody Order order){
		try {
			return new ResponseEntity<Order>(repository.save(order),HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/orders/{id}", method= RequestMethod.DELETE)
	public ResponseEntity<?> deleteApartment(@PathVariable Long id){
		try {
			repository.delete(id);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
