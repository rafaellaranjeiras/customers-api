package com.example.api.config;

import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.parameters.Parameter;

@Component
public class OpenApiOperationCustomizer implements OperationCustomizer {

	@Override
	public Operation customize(Operation operation, HandlerMethod handlerMethod) {
		for(Parameter parameter: operation.getParameters()) {
			if (parameter.getName().equals("version")) {
	           parameter.setExample("v1");
	        }
		}
        return operation;
	}

}
