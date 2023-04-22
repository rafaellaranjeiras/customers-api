package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.common.converter.PaymentCardConverter;
import com.example.common.dto.PaymentCardDto;
import com.example.model.PaymentCard;
import com.example.model.repository.PaymentCardRepository;
import com.example.service.PaymentCardService;

@Service
public class PaymentCardServiceImpl implements PaymentCardService {
	
	@Autowired
	private PaymentCardRepository paymentCardRepository;

	@Override
	public List<PaymentCardDto> findByCustomer(Long customerId) {
		List<PaymentCard> entities = paymentCardRepository.findByCustomerId(customerId);
		return PaymentCardConverter.toDto(entities);
	}

}
