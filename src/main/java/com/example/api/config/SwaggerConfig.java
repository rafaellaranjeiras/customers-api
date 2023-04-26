package com.example.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public OpenAPI config() {
		OpenAPI openApi = new OpenAPI()
				.info(new Info()
						.title("Customers & Cards API")
						.version("1.0")
						.contact(new Contact()
								.name("Rafael Laranjeiras")
								.email("ralaranjeiras@gmail.com")));
						
		return openApi;
	}
}
