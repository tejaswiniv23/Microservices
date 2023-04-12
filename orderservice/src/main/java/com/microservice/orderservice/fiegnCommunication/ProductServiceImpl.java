package com.microservice.orderservice.fiegnCommunication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.productservice.dto.ProductResponse;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service	
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductService productService;
	
	@Autowired RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getProductById_FallBack")
	public ProductResponse getProductById(long productId) {
		return productService.getProductById(productId);
		
		/*
		 * ProductResponse proRes =
		 * restTemplate.getForObject("http://localhost:8081/product/{productId}",
		 * ProductResponse.class, productId); return proRes;
		 */
		 
	}
	
	public ProductResponse getProductById_FallBack(long productId) {
		return new ProductResponse();
	}

}
