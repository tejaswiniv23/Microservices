package com.microservice.orderservice.service;

import com.microservice.orderservice.dto.OrdePlacedResponse;
import com.microservice.orderservice.dto.OrderRequest;
import com.microservice.orderservice.dto.OrderResponse;

public interface OrderService {
	
	OrdePlacedResponse placeOrder(OrderRequest orderRequest);
    OrderResponse getOrderDetails(String orderId);

}
