package com.example.common.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.common.dto.CustomerDto;
import com.example.common.dto.CustomerElasticDto;
import com.example.common.enums.CustomerStatus;
import com.example.model.Customer;

@Component
public class CustomerConverter {
	
	public CustomerDto toDTO(Customer entity) {
        return CustomerDto.builder()
                .id(entity.getId())
                .birthDate(entity.getBirthDate())
                .fullName(entity.getFullName())
                .status(entity.getStatus().toString())
                .documentNumber(entity.getDocumentNumber())
                .build();
    }

    public List<CustomerDto> toDTO(List<Customer> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Customer toEntity(CustomerDto dto) {
        return Customer.builder()
                .id(dto.getId())
                .birthDate(dto.getBirthDate())
                .fullName(dto.getFullName())
                .status(CustomerStatus.valueOf(dto.getStatus()))
                .documentNumber(dto.getDocumentNumber())
                .build();
    }
    
    public List<Customer> toEntity(List<CustomerDto> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
    
    public CustomerElasticDto toElasticDto(Customer entity) {
    	CustomerElasticDto dto = new CustomerElasticDto();
    	dto.setFullname(entity.getFullName());
    	dto.setId(entity.getId());
    	return dto;
    }

}
