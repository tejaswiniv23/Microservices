package com.microservice.orderservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderServiceException extends RuntimeException{

	private String errMsg;
	private String errCode;
	
}
