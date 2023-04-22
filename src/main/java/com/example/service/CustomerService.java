package com.example.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.common.dto.CustomerDto;
import com.github.fge.jsonpatch.JsonPatch;

public interface CustomerService {
	
	Page<CustomerDto> listCustomers(Pageable pageable);
	CustomerDto getCustomer(Long id);
	CustomerDto createCustomer(CustomerDto dto);	
	CustomerDto updateCustomer(Long id, CustomerDto dto);	
	CustomerDto patchCustomer(Long id, JsonPatch patch);
	void deleteCustomer(Long id);

}
