package com.example.service.v1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.example.common.converter.PaymentCardConverter;
import com.example.common.dto.PaymentCardDto;
import com.example.model.PaymentCard;
import com.example.model.Customer;
import com.example.model.repository.PaymentCardRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PaymentCardServiceV1Test {

    @InjectMocks
    private PaymentCardServiceV1 paymentCardService;

    @Mock
    private PaymentCardRepository paymentCardRepository;

    @Mock
    private PaymentCardConverter paymentCardConverter;

    @Mock
    private CustomerServiceV1 customerService;

    private PaymentCardDto paymentCardDto;
    private PaymentCard paymentCard;
    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer();
        customer.setId(1L);

        paymentCard = new PaymentCard();
        paymentCard.setId(2L);
        paymentCard.setCustomer(customer);

        paymentCardDto = new PaymentCardDto();
        paymentCardDto.setId(2L);

        when(paymentCardConverter.toEntity(paymentCardDto)).thenReturn(paymentCard);
        when(paymentCardConverter.toDto(paymentCard)).thenReturn(paymentCardDto);
    }

    @Test
    public void testFindByCustomer() {
        when(paymentCardRepository.findByCustomerId(1L)).thenReturn(Arrays.asList(paymentCard));
        when(paymentCardConverter.toDto(Arrays.asList(paymentCard))).thenReturn(Arrays.asList(paymentCardDto));

        List<PaymentCardDto> result = paymentCardService.findByCustomer(1L);

        assertEquals(1, result.size());
        assertEquals(paymentCardDto, result.get(0));
    }

    @Test
    public void testCreateCard() {
        when(customerService.getEntityById(1L)).thenReturn(customer);
        when(paymentCardRepository.save(paymentCard)).thenReturn(paymentCard);

        PaymentCardDto result = paymentCardService.createCard(1L, paymentCardDto);

        assertEquals(paymentCardDto, result);
    }

    @Test
    public void testGetCard() {
        when(customerService.getEntityById(1L)).thenReturn(customer);
        when(paymentCardRepository.findById(2L)).thenReturn(Optional.of(paymentCard));

        PaymentCardDto result = paymentCardService.getCard(1L, 2L);

        assertEquals(paymentCardDto, result);
    }

    @Test
    public void testGetCardInvalidCustomer() {
        when(customerService.getEntityById(2L)).thenReturn(new Customer());
        when(paymentCardRepository.findById(2L)).thenReturn(Optional.of(paymentCard));

        assertThrows(NoSuchElementException.class, () -> paymentCardService.getCard(2L, 2L));
    }

    @Test
    public void testDeleteCard() {
        paymentCardService.deleteCard(1L, 2L);
        verify(paymentCardRepository).deleteByIdAndCustomerId(2L, 1L);
    }

}