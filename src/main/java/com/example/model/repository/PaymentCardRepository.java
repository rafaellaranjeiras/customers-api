package com.example.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.PaymentCard;

public interface PaymentCardRepository extends JpaRepository<PaymentCard, Long> {
	
	List<PaymentCard> findByCustomerId(Long customerId);

}
