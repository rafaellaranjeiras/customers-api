package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.dto.PaymentCardDto;
import com.example.service.PaymentCardService;
import com.example.util.RestResponse;

@RestController
@RequestMapping("customers/{customerId}/cards")
public class CustomerPaymentCardController extends AbstractController {
	
	@Autowired
	PaymentCardService paymentCardService;
	
	@GetMapping
	public RestResponse<List<PaymentCardDto>> findByCustomer(@PathVariable Long customerId) {
		List<PaymentCardDto> data = paymentCardService.findByCustomer(customerId);
		return getRestResponse(data);		
	}
	
	@GetMapping("{cardId}")
	public RestResponse<PaymentCardDto> getCard(@PathVariable Long customerId,
													  @PathVariable Long cardId) {
		PaymentCardDto data = paymentCardService.getCard(customerId, cardId);
		return getRestResponse(data);		
	}
	
	@PostMapping
	public RestResponse<PaymentCardDto> createCard(@PathVariable Long customerId,
												   @RequestBody PaymentCardDto dto) {
		PaymentCardDto data = paymentCardService.createCard(customerId, dto);
		return getRestResponse(data);		
	}

}
