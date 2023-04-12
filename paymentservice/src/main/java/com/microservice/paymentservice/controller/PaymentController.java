package com.microservice.paymentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.paymentservice.dto.PaymentRequest;
import com.microservice.paymentservice.dto.PaymentResponse;
import com.microservice.paymentservice.service.PaymentService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;
	
	@PostMapping("/doPayment")
	public ResponseEntity<String> doPayment(@RequestBody PaymentRequest paymentRequest) {
		log.info("PaymentController | doPayment is called");
        return new ResponseEntity<>( paymentService.doPayment(paymentRequest),HttpStatus.OK);
    }
	
	@GetMapping("/{orderId}")
    public ResponseEntity<PaymentResponse> getPaymentDetailsByOrderId(@PathVariable String orderId) {

        log.info("PaymentController | doPayment is called"+ orderId);

        return new ResponseEntity<>( paymentService.getPaymentDetailsByOrderId(orderId), HttpStatus.OK);
    }

}
