package com.example.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.dto.PaymentCardDto;
import com.example.service.PaymentCardService;
import com.example.util.RestResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Customer's Payment Cards")
@RestController
@RequestMapping("{version}/customers/{customerId}/cards")
public class CustomerPaymentCardController extends AbstractController {
	
	@Operation(summary = "List customer cards")
	@GetMapping
	public RestResponse<List<PaymentCardDto>> findByCustomer(@PathVariable String version,
															 @PathVariable Long customerId) {
		PaymentCardService service = getService(PaymentCardService.class, version);
		List<PaymentCardDto> data = service.findByCustomer(customerId);
		return getRestResponse(data);		
	}
	
	@Operation(summary = "Get a card by customer id and card id")
	@GetMapping("{cardId}")
	public RestResponse<PaymentCardDto> getCard(@PathVariable String version,
												@PathVariable Long customerId,
												@PathVariable Long cardId) {
		PaymentCardService service = getService(PaymentCardService.class, version);
		PaymentCardDto data = service.getCard(customerId, cardId);
		return getRestResponse(data);		
	}
	
	@Operation(summary = "Create a customer payment card")
	@PostMapping
	public RestResponse<PaymentCardDto> createCard(@PathVariable String version,
												   @PathVariable Long customerId,
												   @RequestBody PaymentCardDto dto) {
		PaymentCardService service = getService(PaymentCardService.class, version);
		PaymentCardDto data = service.createCard(customerId, dto);
		return getRestResponse(data);		
	}
	
	@Operation(summary = "Delete a customer payment card")
	@DeleteMapping("{cardId}")
	public RestResponse<Void> deleteCard(@PathVariable String version,
										 @PathVariable Long customerId,
									     @PathVariable Long cardId) {
		PaymentCardService service = getService(PaymentCardService.class, version);
		service.deleteCard(customerId, cardId);
		return getRestResponse();		
	}

}
