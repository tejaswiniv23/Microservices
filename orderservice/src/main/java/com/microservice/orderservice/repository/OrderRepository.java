package com.microservice.orderservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.orderservice.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer>{

	Optional<Order> findByOrderId(String orderId);

}
