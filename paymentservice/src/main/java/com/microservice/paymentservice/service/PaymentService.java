package com.microservice.paymentservice.service;

import com.microservice.paymentservice.dto.PaymentRequest;
import com.microservice.paymentservice.dto.PaymentResponse;

public interface PaymentService {
	
	String doPayment(PaymentRequest paymentRequest);
	PaymentResponse getPaymentDetailsByOrderId(String orderId);

}
