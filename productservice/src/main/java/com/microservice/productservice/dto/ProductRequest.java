package com.microservice.productservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequest {
	
	private String productName;
	private long price;
	private long quantity;

}
