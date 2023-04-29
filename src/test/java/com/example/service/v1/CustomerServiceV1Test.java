package com.example.service.v1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.common.converter.CustomerConverter;
import com.example.common.dto.CustomerDto;
import com.example.common.dto.CustomerElasticDto;
import com.example.elastic.CustomerElastic;
import com.example.model.Customer;
import com.example.model.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CustomerServiceV1Test {

    @InjectMocks
    private CustomerServiceV1 customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerConverter customerConverter;

    @Mock
    private CustomerElastic customerElastic;

    private CustomerDto customerDto;
    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer();
        customer.setId(1L);
        customer.setDocumentNumber("24895061027");

        customerDto = new CustomerDto();
        customerDto.setId(1L);
        customerDto.setDocumentNumber("24895061027");

        when(customerConverter.toDTO(customer)).thenReturn(customerDto);
        when(customerConverter.toEntity(customerDto)).thenReturn(customer);
    }

    @Test
    public void testListCustomers() {
        Pageable pageable = PageRequest.of(0, 5);
        Page<Customer> entityPage = new PageImpl<>(Arrays.asList(customer), pageable, 1);
        when(customerRepository.findAll(pageable)).thenReturn(entityPage);
        when(customerConverter.toDTO(Arrays.asList(customer))).thenReturn(Arrays.asList(customerDto));

        Page<CustomerDto> result = customerService.listCustomers(pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals(customerDto, result.getContent().get(0));
    }

    @Test
    public void testGetCustomer() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        CustomerDto result = customerService.getCustomer(1L);

        assertEquals(customerDto, result);
    }
    
    @Test
    public void testCreateCustomer() {
        when(customerRepository.findByDocumentNumber(customerDto.getDocumentNumber())).thenReturn(Optional.empty());
        when(customerRepository.save(customer)).thenReturn(customer);

        CustomerDto result = customerService.createCustomer(customerDto);

        assertEquals(customerDto, result);
    }

    @Test
    public void testCreateCustomerDuplicateDocumentNumber() {
        when(customerRepository.findByDocumentNumber(customerDto.getDocumentNumber())).thenReturn(Optional.of(customer));

        assertThrows(IllegalStateException.class, () -> customerService.createCustomer(customerDto));
    }

    @Test
    public void testDeleteCustomer() {
        customerService.deleteCustomer(1L);

        verify(customerRepository).deleteById(1L);
    }

    @Test
    public void testUpdateCustomer() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerRepository.save(customer)).thenReturn(customer);

        CustomerDto result = customerService.updateCustomer(1L, customerDto);

        assertEquals(customerDto, result);
    }

    @Test
    public void testGetEntityById() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Customer result = customerService.getEntityById(1L);

        assertEquals(customer, result);
    }

    @Test
    public void testSearchCustomers() {
        Pageable pageable = PageRequest.of(0, 5);
        List<CustomerElasticDto> content = Arrays.asList(new CustomerElasticDto());
        when(customerElastic.searchCustomerByName("test", pageable.getPageNumber(), pageable.getPageSize())).thenReturn(content);

        Page<CustomerElasticDto> result = customerService.searchCustomers("test", pageable);

        assertEquals(1, result.getContent().size());
        assertEquals(content.get(0), result.getContent().get(0));
    }

}
