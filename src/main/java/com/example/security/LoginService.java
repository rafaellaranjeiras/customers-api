package com.example.security;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	
	@Value("${google.firebase.api-key}")
	private String firebaseApiKey;
	
	@Autowired
	IdentityGoogleFeign identityGoogleFeign;
	
	protected LoginResponseDto login(String authorization) throws AuthenticationException {
		String base64Credentials = authorization.substring("Basic".length()).trim();
		String credentials = new String(Base64.getDecoder().decode(base64Credentials),StandardCharsets.UTF_8);
	    String email = credentials.split(":")[0];
	    String password = credentials.split(":")[1];
        return login(email, password);
	}
	
	private LoginResponseDto login(String email, String password) throws AuthenticationException {
		try {
			IdentityLoginRequestDto body = new IdentityLoginRequestDto();
			body.setEmail(email);
			body.setPassword(password);
			body.setReturnSecureToken(true);
			Map<String, Object> response = identityGoogleFeign.signInWithPassword(body, firebaseApiKey);
			
			LoginResponseDto dto = new LoginResponseDto();
			dto.setToken(response.get("idToken").toString());
			dto.setExpiresInSeconds(Integer.valueOf(response.get("expiresIn").toString()));
			
			return dto;
		
		}catch(Exception e) {
			throw new AuthenticationException();
		}
				
	}

}
