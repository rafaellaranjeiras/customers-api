package com.example.security;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.controller.AbstractController;
import com.example.util.RestResponse;

@RestController
@RequestMapping("/login")
public class AuthController extends AbstractController {
	
	@Autowired
	LoginService loginService;
	
	@GetMapping
    public RestResponse<LoginResponseDto> login(@RequestHeader String authorization) throws AuthenticationException {		
		return getRestResponse(loginService.login(authorization));
	}

}
