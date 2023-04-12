package com.microservice.orderservice.fiegnCommunication;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice.productservice.dto.ProductResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@FeignClient(value = "PRODUCT-SERVICE",path="/product")
public interface ProductService {
		
	@GetMapping("/{id}")
	@CircuitBreaker(name = "productServiceCircuitBreaker",fallbackMethod = "productService_FallBack")
	public ProductResponse getProductById(@PathVariable("id") long productId);
	

	default ProductResponse productService_FallBack(long productId) {
		System.out.println("fallback method called");
		return new ProductResponse();
		
	}	
}