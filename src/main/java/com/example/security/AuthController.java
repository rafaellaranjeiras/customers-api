package com.example.security;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.controller.AbstractController;
import com.example.security.dto.LoginResponseDto;
import com.example.util.RestResponse;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag (name = "1. Login / get token")
@RestController
@RequestMapping("/login")
public class AuthController extends AbstractController {
	
	@Autowired
	LoginService loginService;
	
	@SecurityRequirement(name = "Basic Authentication")
	@GetMapping
    public RestResponse<LoginResponseDto> login(@Parameter(hidden = true) @RequestHeader(name = "Authorization") String authorization) throws AuthenticationException {		
		return getRestResponse(loginService.login(authorization));
	}

}
