package com.example.security;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.security.dto.IdentityLoginRequestDto;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@FeignClient(value = "identityToolkit", url = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword")
public interface IdentityGoogleFeign {
	
	@GetMapping
	Map<String, Object> signInWithPassword(@RequestBody IdentityLoginRequestDto body, @RequestParam String key);

}
