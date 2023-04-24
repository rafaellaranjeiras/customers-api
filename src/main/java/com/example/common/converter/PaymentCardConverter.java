package com.example.common.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.common.dto.PaymentCardDto;
import com.example.model.PaymentCard;
import com.example.model.repository.CardBrandRepository;

@Component
public class PaymentCardConverter {
	
	@Autowired
	CardBrandRepository brandRepository;
	
	public PaymentCardDto toDto(PaymentCard entity) {
		return PaymentCardDto.builder()
				.cardNumber(entity.getCardNumber())
				.brand(entity.getBrand().getName())
				.cvv(entity.getCvv())
				.expirationDate(entity.getExpirationDate())
				.holderName(entity.getHolderName())
				.id(entity.getId())
				.build();				
	}
	
	public List<PaymentCardDto> toDto(List<PaymentCard> entities) {
        return entities.stream()
                .map(entity -> toDto(entity))
                .collect(Collectors.toList());
    }
	
	public PaymentCard toEntity(PaymentCardDto dto) {
		return PaymentCard.builder()
				.cardNumber(dto.getCardNumber())
				.brand(brandRepository.findByName(dto.getBrand()))
				.cvv(dto.getCvv())
				.expirationDate(dto.getExpirationDate())
				.holderName(dto.getHolderName())
				.id(dto.getId())
				.build();
	}

}
