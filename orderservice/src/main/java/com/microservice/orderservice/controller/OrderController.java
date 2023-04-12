package com.microservice.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.orderservice.dto.OrderRequest;
import com.microservice.orderservice.dto.OrderResponse;
import com.microservice.orderservice.service.OrderService;
import com.microservice.productservice.dto.ProductResponse;
import com.microservice.orderservice.fiegnCommunication.ProductService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/order")
@Log4j2
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	
	 @PostMapping("/placeOrder")
	    public ResponseEntity<Object> placeOrder(@RequestBody OrderRequest orderRequest) {

	        log.info("OrderController | placeOrder is called |  orderRequest :"+ orderRequest.toString());

	        return new ResponseEntity<>(orderService.placeOrder(orderRequest), HttpStatus.OK);
	    }
	
	 @GetMapping("/{orderId}")
	 public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable("orderId") String orderId){
		 
		 log.info("OrderController | getOrderDetails is called |  orderId :"+ orderId);

	        return new ResponseEntity<>(orderService.getOrderDetails(orderId), HttpStatus.OK);
		 
	 }

}
