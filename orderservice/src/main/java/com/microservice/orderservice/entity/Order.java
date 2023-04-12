package com.microservice.orderservice.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ORDER_DETAILS")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "ORDER_ID" ,unique = true)
	private String orderId;
	
	@Column(name = "PRODUCT_ID")
	private long productId;
	
	@Column(name = "QUANTITY")
	private long quantity;
	
	@Column(name = "ORDER_STATUS")
	private String orderStatus;
	
	@Column(name = "ORDER_DATE")
	private Date orderDate;
	
	@Column(name = "AMOUNT")
	private long amount;
	
}
