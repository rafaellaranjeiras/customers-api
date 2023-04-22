package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.dto.CustomerDto;
import com.example.service.CustomerService;
import com.example.util.RestResponse;
import com.github.fge.jsonpatch.JsonPatch;

@RestController
@RequestMapping("customers")
public class CustomerController extends AbstractController {
	
	@Autowired
	private CustomerService customerService;	
	
	@GetMapping
	public RestResponse<List<CustomerDto>> listCustomers(@RequestHeader(defaultValue = "0") int __page,
														 @RequestHeader(defaultValue = "30") int __size) {
		PageRequest pageRequest = PageRequest.of(__page, __size);
		Page<CustomerDto> data = customerService.listCustomers(pageRequest);
		return getRestResponse(data);				
	}
	
	@GetMapping("{id}")
	public RestResponse<CustomerDto> getCustomer(@PathVariable Long id) {		
		CustomerDto data = customerService.getCustomer(id);
		return getRestResponse(data);				
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestResponse<CustomerDto> createCustomer(@RequestBody CustomerDto dto) {
		CustomerDto data = customerService.createCustomer(dto);
		return getRestResponse(data);		
	}
	
	@PutMapping("{id}")
	public RestResponse<CustomerDto> updateCustomer(@PathVariable Long id,
													@RequestBody CustomerDto dto) {
		CustomerDto data = customerService.updateCustomer(id, dto);
		return getRestResponse(data);		
	}
	
	@PatchMapping(path = "{id}", consumes = "application/json-patch+json")
	public RestResponse<CustomerDto> patchCustomer(@PathVariable Long id,
												   @RequestBody JsonPatch patch) {
		CustomerDto data = customerService.patchCustomer(id, patch);
		return getRestResponse(data);		
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public RestResponse<Void> deleteCustomer(@PathVariable Long id) {		
		customerService.deleteCustomer(id);
		return getRestResponse();				
	}	

}
