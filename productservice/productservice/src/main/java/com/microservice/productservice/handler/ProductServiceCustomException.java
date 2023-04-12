package com.microservice.productservice.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductServiceCustomException extends RuntimeException{
	
	private String errMsg;
	private String errCode;
}
