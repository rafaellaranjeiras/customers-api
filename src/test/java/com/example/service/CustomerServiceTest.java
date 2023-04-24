package com.example.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.common.dto.CustomerDto;
import com.example.common.enums.CustomerStatus;
import com.example.model.Customer;
import com.example.model.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {
	
	@Autowired
	private CustomerService customerService;
	
	@MockBean
	private CustomerRepository customerRepository;

	@Test
	public void listCustomers() {
		Mockito.when(customerRepository.findAll(Mockito.any(Pageable.class)))
			.thenReturn(buildCustomersEntities());
		
		Pageable pageable = PageRequest.of(0, 30);
		Page<CustomerDto> customersPage = customerService.listCustomers(pageable);	
		
		assertThat(customersPage.getContent().size() == 30);
		assertNotNull(customersPage.getContent().get(0));
		assertNotNull(customersPage.getContent().get(29));
		assertNotNull(customersPage.getContent().get(0).getId());
		assertNotNull(customersPage.getContent().get(0).getBirthDate());
		assertNotNull(customersPage.getContent().get(0).getFullName());
		assertNotNull(customersPage.getContent().get(0).getDocumentNumber());
		assertThat(customersPage.getContent().get(0).getStatus().equals(CustomerStatus.ACTIVE.toString()));		
	}
	
	private Page<Customer> buildCustomersEntities() {
		List<Customer> list = new ArrayList<>();
		for(int i=1; i<=30; i++) {
			Customer entity = new Customer();
			entity.setId(Long.valueOf(i));
			entity.setBirthDate(new Date());
			entity.setFullName("full name");
			entity.setDocumentNumber("12345678901");
			entity.setStatus(CustomerStatus.ACTIVE);
			list.add(entity);
		}
		return new PageImpl<>(list);
	}

}
