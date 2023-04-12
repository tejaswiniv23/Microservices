package com.microservice.paymentservice.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentServiceCustomException extends RuntimeException{
	
	private String errMsg;
	private String errCode;
	

}
