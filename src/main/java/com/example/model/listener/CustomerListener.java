package com.example.model.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.example.common.converter.CustomerConverter;
import com.example.elastic.CustomerElastic;
import com.example.model.Customer;

import jakarta.persistence.PostPersist;

@Component
public class CustomerListener {
	
	@Autowired
	private CustomerElastic customerElastic;
	
	@Autowired
	private CustomerConverter customerConverter;
	
	@Async
	@PostPersist
	public void prePersist(Customer entity) {
		customerElastic.addCustomer(
				customerConverter.toElasticDto(entity));
	}

}
