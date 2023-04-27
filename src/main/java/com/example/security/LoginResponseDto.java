package com.example.security;

import lombok.Data;

@Data
public class LoginResponseDto {
	
	private String token;
	private Integer expiresInSeconds;

}
