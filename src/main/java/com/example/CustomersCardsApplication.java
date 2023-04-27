package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CustomersCardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomersCardsApplication.class, args);
	}

}
