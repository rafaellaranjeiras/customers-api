package com.example.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.common.converter.PaymentCardConverter;
import com.example.common.dto.PaymentCardDto;
import com.example.model.PaymentCard;
import com.example.model.repository.PaymentCardRepository;
import com.example.service.CustomerService;
import com.example.service.PaymentCardService;

@Service
public class PaymentCardServiceImpl implements PaymentCardService {
	
	@Autowired
	private PaymentCardRepository paymentCardRepository;
	
	@Autowired
	private PaymentCardConverter cardConverter;
	
	@Autowired
	private CustomerService customerService;

	@Override
	public List<PaymentCardDto> findByCustomer(Long customerId) {
		List<PaymentCard> entities = paymentCardRepository.findByCustomerId(customerId);
		return cardConverter.toDto(entities);
	}

	@Override
	public PaymentCardDto createCard(Long customerId, PaymentCardDto dto) {
		PaymentCard entity = cardConverter.toEntity(dto);
		entity.setCustomer(customerService.getEntityById(customerId));
		paymentCardRepository.save(entity);
		return cardConverter.toDto(entity);
	}

	@Override
	public PaymentCardDto getCard(Long customerId, Long cardId) {
		customerService.getEntityById(customerId);
		PaymentCard entity = paymentCardRepository.findById(cardId).orElseThrow();
		if(!entity.getCustomer().getId().equals(customerId))
			throw new NoSuchElementException();
		return cardConverter.toDto(entity);
	}

}
