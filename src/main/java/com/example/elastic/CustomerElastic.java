package com.example.elastic;

import java.util.List;

import com.example.common.dto.CustomerElasticDto;

public interface CustomerElastic {
	
	List<CustomerElasticDto> searchCustomerByName(String name);
	void addCustomer(CustomerElasticDto dto);

}
