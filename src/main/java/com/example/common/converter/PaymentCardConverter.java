package com.example.common.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.example.common.dto.PaymentCardDto;
import com.example.model.PaymentCard;

public class PaymentCardConverter {
	
	public static PaymentCardDto toDto(PaymentCard entity) {
		return PaymentCardDto.builder()
				.cardNumber(entity.getCardNumber())
				.brand(entity.getBrand().getName())
				.cvv(entity.getCvv())
				.expirationDate(entity.getExpirationDate())
				.holderName(entity.getHolderName())
				.id(entity.getId())
				.build();				
	}
	
	public static List<PaymentCardDto> toDto(List<PaymentCard> entities) {
        return entities.stream()
                .map(entity -> toDto(entity))
                .collect(Collectors.toList());
    }

}
