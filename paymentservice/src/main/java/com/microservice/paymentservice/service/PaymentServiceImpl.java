package com.microservice.paymentservice.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.paymentservice.dao.PaymentRepository;
import com.microservice.paymentservice.dto.PaymentRequest;
import com.microservice.paymentservice.dto.PaymentResponse;
import com.microservice.paymentservice.entity.Payment;
import com.microservice.paymentservice.handler.PaymentServiceCustomException;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	PaymentRepository paymentRepo;

	@Override
	public String doPayment(PaymentRequest paymentRequest) {
		System.out.println("PaymentService | Do payment method called");
		Payment payment = Payment.builder()
				.orderId(paymentRequest.getOrderId())
				.amount(paymentRequest.getAmount())
				.paymentDate(new Date())
				.paymentStatus("SUCCESS")
				.paymentMode(paymentRequest.getPaymentMode()).build();
		
		payment = paymentRepo.save(payment);
		return payment.getOrderId();
	}

	@Override
	public PaymentResponse getPaymentDetailsByOrderId(String orderId) {

        Payment transactionDetails = paymentRepo.findByOrderId(orderId)
                .orElseThrow(() -> new PaymentServiceCustomException("TransactionDetails with given id not found","TRANSACTION_NOT_FOUND"));

        PaymentResponse paymentResponse  = PaymentResponse.builder()
                .paymentId(transactionDetails.getId())
                .paymentMode(transactionDetails.getPaymentMode())
                .paymentDate(transactionDetails.getPaymentDate())
                .orderId(transactionDetails.getOrderId())
                .status(transactionDetails.getPaymentStatus())
                .amount(transactionDetails.getAmount())
                .build();

        return paymentResponse;
	}
	
}
