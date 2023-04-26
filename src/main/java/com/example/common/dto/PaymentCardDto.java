package com.example.common.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "Payment Card data")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentCardDto {	
	private Long id;
	private String cardNumber;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate expirationDate;
	private String holderName;
	private String cvv;
	private String brand;	
}
