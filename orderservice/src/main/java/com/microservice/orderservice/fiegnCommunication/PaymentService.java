package com.microservice.orderservice.fiegnCommunication;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservice.paymentservice.dto.PaymentRequest;
import com.microservice.paymentservice.dto.PaymentResponse;

@FeignClient(value = "PAYMENT-SERVICE",path="/payment")
public interface PaymentService {

	@PostMapping(value = "/doPayment")
	public String doPayment(@RequestBody PaymentRequest paymentRequest);
	
	@GetMapping(value = "/{orderId}")
	public PaymentResponse getPaymentDetailsByOrderId(@PathVariable String orderId);
}
