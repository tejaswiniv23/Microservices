package com.microservice.paymentservice.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {
	
	private long paymentId;
    private String status;
    private String paymentMode;
    private long amount;
    private Date paymentDate;
    private String orderId;

}
