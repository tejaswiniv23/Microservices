package com.microservice.orderservice.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
	
	private String orderId;
	private Date orderDate;
	private String orderStatus;
	private long amount;
	private ProductDetails productDetails;
	private PaymentDetails paymentDetails;
	
	@Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductDetails {

        private long productId;
        private String productName;
        private long quantity;
        private long price;
    }
	
	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class PaymentDetails{
		
		private long paymentId;
        private String paymentMode;
        private String paymentStatus;
        private Date paymentDate;
		
		
	}

	

}
