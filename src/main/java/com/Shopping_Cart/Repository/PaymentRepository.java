package com.Shopping_Cart.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Shopping_Cart.Models.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

	public Payment findPaymentByOrderId(Integer orderId);
}
