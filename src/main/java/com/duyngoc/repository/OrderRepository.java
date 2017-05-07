package com.duyngoc.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.duyngoc.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{
	
	List<Order> findByUserId(Long userId);
}
