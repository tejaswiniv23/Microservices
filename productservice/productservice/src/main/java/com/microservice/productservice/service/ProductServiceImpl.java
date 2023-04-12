package com.microservice.productservice.service;

import static org.springframework.beans.BeanUtils.copyProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.productservice.dto.ProductRequest;
import com.microservice.productservice.dto.ProductResponse;
import com.microservice.productservice.enity.Product;
import com.microservice.productservice.handler.ProductServiceCustomException;
import com.microservice.productservice.repo.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl  implements	 ProductService {

	//@Autowired
	private final ProductRepository productRepo;
	
	@Override
	public long addProduct(ProductRequest productRequest) {
		Product product = Product.builder()
				.productName(productRequest.getProductName())
				.price(productRequest.getPrice())
				.quantity(productRequest.getQuantity()).build();
		productRepo.save(product);
		return product.getProductId();
	}
	
	@Override
	public ProductResponse getProductById(long productId) {
		Product product = productRepo.findById(productId).orElseThrow(() -> new ProductServiceCustomException("ERROR :","No product Found for the Id "+productId));
		ProductResponse productRes = new ProductResponse();
		copyProperties(product , productRes);
		
		return productRes;
	}

	@Override
	public void deleteProductById(long productId) {
		if(!productRepo.existsById(productId))
			throw new ProductServiceCustomException("No product found for the id "+productId,"PRODUCT_NOT_FOUND");
		
		productRepo.deleteById(productId);
		
	}

	@Override
	public void reduceQuantity(long productId, long quantity) {
		Product product = productRepo.findById(productId).orElseThrow(() -> new ProductServiceCustomException("Product with given Id not found",
	                "PRODUCT_NOT_FOUND"));
	
		if(product.getQuantity() < quantity) {
		    throw new ProductServiceCustomException("Product does not have sufficient Quantity","INSUFFICIENT_QUANTITY"
		    );
		}
	
		product.setQuantity(product.getQuantity() - quantity);
		productRepo.save(product);
		
	}
}
