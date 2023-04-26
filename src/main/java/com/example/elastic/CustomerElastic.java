package com.example.elastic;

import java.util.List;

import com.example.common.dto.CustomerElasticDto;

public interface CustomerElastic {
	
	List<CustomerElasticDto> searchCustomerByName(String term, int page, int size);
	void addCustomer(CustomerElasticDto dto);
	void removeCustomer(Long customerId);

}
