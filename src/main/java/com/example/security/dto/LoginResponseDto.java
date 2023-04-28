package com.example.security.dto;

import lombok.Data;

@Data
public class LoginResponseDto {
	
	private String token;
	private Integer expiresInSeconds;

}
