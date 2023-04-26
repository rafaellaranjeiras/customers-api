package com.example.model.converter;

import com.example.common.enums.CustomerStatus;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CustomerStatusConverter implements AttributeConverter<CustomerStatus, String> {
	
	@Override
    public String convertToDatabaseColumn(CustomerStatus status) {
        return status.getCode();
    }

    @Override
    public CustomerStatus convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }
        for (CustomerStatus status : CustomerStatus.values()) {
            if (code.equals(status.getCode())) {
                return status;
            }
        }
        return null;
    }

}
