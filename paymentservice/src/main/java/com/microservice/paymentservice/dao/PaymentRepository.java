package com.microservice.paymentservice.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.paymentservice.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer>{

	Optional<Payment> findByOrderId(String orderId);

}
