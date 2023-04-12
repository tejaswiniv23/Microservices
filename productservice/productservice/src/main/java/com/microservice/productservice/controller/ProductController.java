package com.microservice.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.productservice.dto.ProductRequest;
import com.microservice.productservice.dto.ProductResponse;
import com.microservice.productservice.service.ProductService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/product")
@Log4j2
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@PostMapping
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest) {

        log.info("ProductController | addProduct is called");
        log.info("ProductController | addProduct | productRequest : " + productRequest.toString());

        long productId = productService.addProduct(productRequest);
        return new ResponseEntity<>(productId, HttpStatus.CREATED);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") long productId) {

        log.info("ProductController | getProductById is called");
        log.info("ProductController | getProductById | productId : " + productId);

        ProductResponse productResponse = productService.getProductById(productId);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }
	
	@GetMapping("/{message}")
    public ResponseEntity<ProductResponse> getProductError(@PathVariable("message") String message) {

        log.info("ProductController | getProductError is called");
        log.info("ProductController | getProductError | productId : " + message);

        return new Exception(message);
    }
	
	@DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable("id") long productId) {
        productService.deleteProductById(productId);
    }
	
	@PutMapping("/reduceQuantity/{id}/{quantity}")
	public ResponseEntity<Void> reduceQuantity(@PathVariable("id") long productId, @PathVariable long quantity) {

        log.info("ProductController | reduceQuantity is called");
        log.info("ProductController | reduceQuantity | productId : " + productId + "quantity :"+quantity);
        
        productService.reduceQuantity(productId,quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    

}
