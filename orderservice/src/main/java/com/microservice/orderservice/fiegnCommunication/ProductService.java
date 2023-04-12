package com.microservice.orderservice.fiegnCommunication;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice.productservice.dto.ProductResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@FeignClient(value = "PRODUCT-SERVICE",path="/product")
public interface ProductService {
		
	@CircuitBreaker(name = "productServiceBreaker", fallbackMethod = "productServiceFallBack")
	@GetMapping("/{id}")
	public ProductResponse getProductById(long productId);
	
	default ProductResponse productServiceFallBack(long productId, Throwable e) {
		System.out.println("Running FallBAck");
		return new ProductResponse(1,"HI",1,1);
	}
		
}