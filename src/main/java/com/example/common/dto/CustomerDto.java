package com.example.common.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "Customer Data")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDto {
	private Long id;
	private String fullName;
	@JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthDate; 
	private String documentNumber;
    private String status;
}
