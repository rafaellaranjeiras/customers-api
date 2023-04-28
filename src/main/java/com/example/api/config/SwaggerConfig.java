package com.example.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(
		  info =@Info(
		    title = "Customers & Cards API",
		    version = "1.0",
		    contact = @Contact(
		      name = "Rafael Laranjeiras", email = "ralaranjeiras@gmail.com")))
@io.swagger.v3.oas.annotations.security.SecurityScheme(
		  name = "Basic Authentication",
		  type = SecuritySchemeType.HTTP,
		  bearerFormat = "BASE64",
		  scheme = "basic"
		)
public class SwaggerConfig {
	
	
	@Bean
	public OpenAPI customizeOpenAPI() {
		final String securitySchemeName = "Bearer token";
		return new OpenAPI()
				.addSecurityItem(new SecurityRequirement()
						.addList(securitySchemeName))
				.components(new Components()
						.addSecuritySchemes(securitySchemeName, new SecurityScheme()
								.name(securitySchemeName)
								.type(SecurityScheme.Type.HTTP)
								.scheme("bearer")
								.bearerFormat("JWT")));
	}

}
