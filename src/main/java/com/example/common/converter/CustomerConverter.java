package com.example.common.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.example.common.dto.CustomerDto;
import com.example.common.enums.CustomerStatus;
import com.example.model.Customer;

public class CustomerConverter {
	
	public static CustomerDto toDTO(Customer entity) {
        return CustomerDto.builder()
                .id(entity.getId())
                .birthDate(entity.getBirthDate())
                .fullName(entity.getFullName())
                .status(entity.getStatus().toString())
                .documentNumber(entity.getDocumentNumber())
                .build();
    }

    public static List<CustomerDto> toDTO(List<Customer> entities) {
        return entities.stream()
                .map(CustomerConverter::toDTO)
                .collect(Collectors.toList());
    }

    public static Customer toEntity(CustomerDto dto) {
        return Customer.builder()
                .id(dto.getId())
                .birthDate(dto.getBirthDate())
                .fullName(dto.getFullName())
                .status(CustomerStatus.valueOf(dto.getStatus()))
                .documentNumber(dto.getDocumentNumber())
                .build();
    }
    public static List<Customer> toEntity(List<CustomerDto> dtos) {
        return dtos.stream()
                .map(CustomerConverter::toEntity)
                .collect(Collectors.toList());
    }

}
