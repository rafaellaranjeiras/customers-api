package com.example.common.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CustomerDto {
	private Long id;
	private String fullName;
	@JsonFormat(pattern="yyyy-MM-dd")
    private Date birthDate; 
	private String documentNumber;
    private String status;
}
