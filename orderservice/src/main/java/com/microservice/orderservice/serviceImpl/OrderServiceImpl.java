package com.microservice.orderservice.serviceImpl;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.orderservice.dto.OrdePlacedResponse;
import com.microservice.orderservice.dto.OrderEnum;
import com.microservice.orderservice.dto.OrderRequest;
import com.microservice.orderservice.dto.OrderResponse;
import com.microservice.orderservice.entity.Order;
import com.microservice.orderservice.exception.OrderServiceException;
import com.microservice.orderservice.fiegnCommunication.PaymentService;
import com.microservice.orderservice.fiegnCommunication.ProductService;
import com.microservice.orderservice.repository.OrderRepository;
import com.microservice.orderservice.service.OrderService;
import com.microservice.paymentservice.dto.PaymentRequest;
import com.microservice.paymentservice.dto.PaymentResponse;
import com.microservice.productservice.dto.ProductResponse;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private PaymentService paymentFeignClient;
	
	@Autowired
	private ProductService productfeignClient;

	@Autowired
	OrderRepository orderRepo;

	@Autowired
	RestTemplate restTemplate;

	@Value("${PlaceOrderURL}")
	private String placeOrderUrL;

	@Override
	public OrdePlacedResponse placeOrder(OrderRequest orderRequest) {

		String orderId = null ,orderStatus = null , paymentStatus = null;
		orderId = getOrderId();

		Order order = Order.builder()
				.amount(orderRequest.getTotalAmount())
				.orderStatus(OrderEnum.ORDER_CREATED.toString()).orderId(orderId)
				.productId(orderRequest.getProductId())
				.orderDate(new Date())
				.quantity(orderRequest.getQuantity())
				.build();

		PaymentRequest paymentRequest = PaymentRequest.builder()
				.orderId(order.getOrderId())
				.paymentMode(orderRequest.getPaymentMode())
				.amount(orderRequest.getTotalAmount()).build();

		try {
			//String placeOrder = placeOrder(paymentRequest);//using restTemplate
			String placeOrder = paymentFeignClient.doPayment(paymentRequest);
			orderStatus = OrderEnum.ORDER_PALCED.toString();
			paymentStatus = OrderEnum.PAYMENT_DONE.toString();

		} catch (Exception e) {
			log.info("Order Id :", orderId);
			log.error("Exception occured while calling doPayment service : ", e, e, orderRequest, orderId, order,
					paymentRequest, orderStatus, e);
			orderStatus = OrderEnum.FAILED.toString();
			paymentStatus = OrderEnum.PAYMENT_FAILED.toString();
		}

		order.setOrderStatus(orderStatus);
		orderRepo.save(order);
		
		OrdePlacedResponse  ordeResponse = OrdePlacedResponse.builder()
				.orderId(order.getOrderId())
				.orderStatus(orderStatus)
				.paymentStatus(paymentStatus).build();
		
		return ordeResponse;
	}

	@Override
	public OrderResponse getOrderDetails(String orderId) {
		Order order = orderRepo.findByOrderId(orderId)
				.orElseThrow(() -> new OrderServiceException("ERR:","Order Details not found for orderId :"+ orderId));
		
		PaymentResponse paymentResponse  = null;
		
		try {
			
			 paymentResponse = paymentFeignClient.getPaymentDetailsByOrderId(orderId);
		} catch (Exception e) {
			log.error("Exception occured  while calling getPaymentDetailsByOrderId :"+e);
		}
		
		ProductResponse productResponse = null;
		try {
			
			productResponse = productfeignClient.getProductById(order.getProductId());
			log.info("Product Response :"+productResponse.toString());
			
		}catch (Exception e) {
			log.info("Product Id :",order.getProductId());
			log.error("Exception occured  while calling getProductByid :"+e);
		}
		
		OrderResponse.ProductDetails productDeatils = OrderResponse.ProductDetails.builder()
				.productId(order.getProductId())
				.productName(productResponse.getProductName())
				.price(productResponse.getPrice())
				.build();
		
		OrderResponse.PaymentDetails paymentDetails = OrderResponse.PaymentDetails.builder()
				.paymentDate(paymentResponse.getPaymentDate())
				.paymentMode(paymentResponse.getPaymentMode())
				.paymentStatus(paymentResponse.getStatus()).build();
		
		OrderResponse orderResponse = OrderResponse.builder()
				.orderId(orderId)
				.amount(order.getAmount())
				.orderDate(order.getOrderDate())
				.orderStatus(order.getOrderStatus())
				.productDetails(productDeatils)
				.paymentDetails(paymentDetails).build();

		return orderResponse;
	}
	
	private String placeOrder(PaymentRequest paymentRequest) {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<PaymentRequest> entity = new HttpEntity<PaymentRequest>(paymentRequest, headers);

		String orderPID = restTemplate.exchange(placeOrderUrL, HttpMethod.POST, entity, String.class).getBody();
		return orderPID;
	}
	
	private String getOrderId()
	{
		Random rnd = new Random();
		return  "TEST" + rnd.nextInt(999999) + "";
	}


}
