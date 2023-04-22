package com.example.service;

import java.util.List;

import com.example.common.dto.PaymentCardDto;

public interface PaymentCardService {
	
	List<PaymentCardDto> findByCustomer(Long customerId);

}
