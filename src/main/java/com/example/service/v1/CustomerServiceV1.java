package com.example.service.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.example.common.converter.CustomerConverter;
import com.example.common.dto.CustomerDto;
import com.example.common.dto.CustomerElasticDto;
import com.example.common.enums.CustomerStatus;
import com.example.elastic.CustomerElastic;
import com.example.model.Customer;
import com.example.model.repository.CustomerRepository;
import com.example.service.CustomerService;
import com.example.util.StringUtil;
import com.example.util.ValidationUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceV1 implements CustomerService {
	
	@Autowired
    private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerConverter customerConverter;
	
	@Autowired
	private CustomerElastic customerElastic;

	@Override
	public Page<CustomerDto> listCustomers(Pageable pageable) {
		Page<Customer> entities = customerRepository.findAll(pageable);
		List<CustomerDto> dtos = customerConverter.toDTO(entities.getContent());
		return new PageImpl<>(dtos, pageable, entities.getTotalElements());
	}
	
	@Override
	public CustomerDto getCustomer(Long id) {
		Customer entity = getEntityById(id);
		return customerConverter.toDTO(entity);
	}
	
	@Transactional
	@Override
	public CustomerDto createCustomer(CustomerDto dto) {
		if(customerRepository.findByDocumentNumber(dto.getDocumentNumber()).isPresent())
			throw new IllegalStateException("Document Number already registered.");
		documentNumberValidation(dto.getDocumentNumber());
		if(dto.getStatus() == null)
			dto.setStatus(CustomerStatus.ACTIVE.toString());
		Customer entity = customerRepository.save(customerConverter.toEntity(dto));
		return customerConverter.toDTO(entity);		
	}

	@Override
	public void deleteCustomer(Long id) {
		customerRepository.deleteById(id);		
	}
	
	@Transactional
	@Override
	public CustomerDto updateCustomer(Long id, CustomerDto dto) {
		Customer entity = getEntityById(id);	
		if(dto.getId() != null && !dto.getId().equals(entity.getId()))
			throw new IllegalArgumentException("Invalid id.");
		if(!dto.getDocumentNumber().equals(entity.getDocumentNumber())
				&& customerRepository.findByDocumentNumber(dto.getDocumentNumber()).isPresent())
			throw new IllegalStateException("Document Number already registered.");
		documentNumberValidation(dto.getDocumentNumber());
		
		Customer newEntity = customerConverter.toEntity(dto);
		newEntity.setId(id);
		newEntity.setPaymentCards(entity.getPaymentCards());
		customerRepository.save(newEntity);
		
		return customerConverter.toDTO(newEntity);		
	}
	
	@Override
	public CustomerDto patchCustomer(Long id, JsonPatch patch) {
		CustomerDto dto = customerConverter.toDTO(getEntityById(id));
		CustomerDto patched = applyPatch(patch, dto);
		return updateCustomer(id, patched);
	}
	
	@Override
	public Customer getEntityById(Long id) {
		return customerRepository.findById(id).orElseThrow();
	}
	
	@Override
	public Page<CustomerElasticDto> searchCustomers(String term, Pageable pageable) {
		List<CustomerElasticDto> content =
				customerElastic.searchCustomerByName(term, pageable.getPageNumber(), pageable.getPageSize());
		Page<CustomerElasticDto> page = new PageImpl<>(content, pageable, -1);
		return page;
	}
	
	private CustomerDto applyPatch(JsonPatch patch, CustomerDto dto) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode patched = patch.apply(objectMapper.convertValue(dto, JsonNode.class));
			return objectMapper.treeToValue(patched, CustomerDto.class);
		} catch (JsonPatchException e) {
			throw new IllegalArgumentException(e.getMessage());
		} catch (JsonProcessingException  e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	private void documentNumberValidation(String documentNumber) {
		Assert.isTrue(StringUtil.isAlphanumeric(documentNumber), "Invalid document number.");
		Assert.isTrue(ValidationUtil.isCPFValid(documentNumber), "Invalid document number.");
	}
}
