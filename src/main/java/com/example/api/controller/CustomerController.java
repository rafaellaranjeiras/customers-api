package com.example.api.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.dto.CustomerDto;
import com.example.common.dto.CustomerElasticDto;
import com.example.service.CustomerService;
import com.example.util.RestResponse;
import com.github.fge.jsonpatch.JsonPatch;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Customer")
@RestController
@RequestMapping("{version}/customers")
public class CustomerController extends AbstractController {
	
	@Operation(summary = "List all customers")
	@GetMapping
	public RestResponse<List<CustomerDto>> listCustomers(@PathVariable String version,
														 @RequestHeader(defaultValue = "0") int __page,
														 @RequestHeader(defaultValue = "30") int __size) {
		CustomerService service = getService(CustomerService.class, version);
		PageRequest pageRequest = PageRequest.of(__page, __size);
		Page<CustomerDto> data = service.listCustomers(pageRequest);
		return getRestResponse(data);				
	}
	
	@Operation(summary = "Get customer by id")
	@GetMapping("{id}")
	public RestResponse<CustomerDto> getCustomer(@PathVariable String version,
												 @PathVariable Long id) {	
		CustomerService service = getService(CustomerService.class, version);
		CustomerDto data = service.getCustomer(id);
		return getRestResponse(data);				
	}
	
	@Operation(summary = "Search customers by name")
	@GetMapping("/search")
	public RestResponse<List<CustomerElasticDto>> searchCustomers(@PathVariable String version,
																  @RequestParam String q,
																  @RequestHeader(defaultValue = "0") int __page,
																  @RequestHeader(defaultValue = "30") int __size) {		
		CustomerService service = getService(CustomerService.class, version);
		PageRequest pageRequest = PageRequest.of(__page, __size);
		Page<CustomerElasticDto> data = service.searchCustomers(q, pageRequest);
		return getRestResponse(data);				
	}
	
	@Operation(summary = "Create a customer")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestResponse<CustomerDto> createCustomer(@PathVariable String version,
													@RequestBody CustomerDto dto) {
		CustomerService service = getService(CustomerService.class, version);
		CustomerDto data = service.createCustomer(dto);
		return getRestResponse(data);		
	}
	
	@Operation(summary = "Update a customer")
	@PutMapping("{id}")
	public RestResponse<CustomerDto> updateCustomer(@PathVariable String version,
													@PathVariable Long id,
													@RequestBody CustomerDto dto) {
		CustomerService service = getService(CustomerService.class, version);
		CustomerDto data = service.updateCustomer(id, dto);
		return getRestResponse(data);		
	}
	
	@Operation(summary = "Update a customer", description = "Use [JsonPatch](https://jsonpatch.com/) notation.")
	@PatchMapping(path = "{id}", consumes = "application/json-patch+json")
	public RestResponse<CustomerDto> patchCustomer(@PathVariable String version,
												   @PathVariable Long id,
												   @RequestBody JsonPatch patch) {
		CustomerService service = getService(CustomerService.class, version);
		CustomerDto data = service.patchCustomer(id, patch);
		return getRestResponse(data);		
	}
	
	@Operation(summary = "Delete a customer")
	@DeleteMapping("{id}")
	public RestResponse<Void> deleteCustomer(@PathVariable String version,
											 @PathVariable Long id) {		
		CustomerService service = getService(CustomerService.class, version);
		service.deleteCustomer(id);
		return getRestResponse();				
	}

}
