package com.example.common.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaymentCardDto {	
	private Long id;
	private String cardNumber;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date expirationDate;
	private String holderName;
	private String cvv;
	private String brand;	
}
