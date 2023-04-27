package com.example.security;

import lombok.Data;

@Data
public class IdentityLoginRequestDto {
	
	private String email;
	private String password;
	private Boolean returnSecureToken;

}
