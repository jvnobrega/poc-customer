package com.nobrega.poc.customer.domain;

import com.nobrega.poc.customer.application.CustomerResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    private CustomerService service;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerMapper customerMapper;

    @Test
    void testGetCustomerByUuid() {
        String uuid = UUID.randomUUID().toString();
        Customer customer = Customer.builder()
                .name("name")
                .document("document")
                .build();
        CustomerResponse customerResponse = CustomerResponse.builder()
                .uuid(uuid)
                .name("name")
                .document("document")
                .build();

        when(customerRepository.findByUuid(uuid)).thenReturn(Optional.of(customer));
        when(customerMapper.convertFromModel(customer)).thenReturn(customerResponse);

        CustomerResponse actual = service.getCustomerByUuid(uuid);

        verify(customerRepository).findByUuid(uuid);
        verify(customerMapper).convertFromModel(customer);
        assertEquals(uuid, actual.getUuid());
        assertEquals("name", actual.getName());
        assertEquals("document", actual.getDocument());

    }
}
