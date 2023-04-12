package com.microservice.productservice.service;

import com.microservice.productservice.dto.ProductRequest;
import com.microservice.productservice.dto.ProductResponse;

public interface ProductService {
	
	long addProduct(ProductRequest productRequest);
	ProductResponse getProductById(long productId);
	void deleteProductById(long productId);
	void reduceQuantity(long productId,long quantity);

}
