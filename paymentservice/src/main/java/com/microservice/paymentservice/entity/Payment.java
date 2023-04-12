package com.microservice.paymentservice.entity;

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
@Entity
@Builder
@Table(name = "PAYMENT_TBL")
public class Payment {
	
	   @Id
	   @GeneratedValue(strategy = GenerationType.AUTO)
       private Integer id;
	   
	   @Column(name ="ORDER_ID")
       private String orderId;
	   
	   @Column(name = "AMOUNT")
       private long amount;
	   
	   @Column(name = "PAYMENT_DATE")
       private Date paymentDate;
	   
	   @Column(name = "PAYMENT_MODE")
       private String paymentMode;
	   
	   @Column(name = "PAYMENT_STATUS")
       private String paymentStatus;
       
}
