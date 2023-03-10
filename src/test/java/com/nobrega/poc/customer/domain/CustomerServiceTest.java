package com.nobrega.poc.customer.domain;

import com.nobrega.poc.customer.application.CustomerRequest;
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

    private static final String NAME = "name";
    private static final String NEW_NAME = "newName";
    private static final String DOCUMENT = "document";
    private static final String CUSTOMER_UUID = "customerUuid";

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
                .name(NAME)
                .document(DOCUMENT)
                .build();
        CustomerResponse customerResponse = CustomerResponse.builder()
                .uuid(uuid)
                .name(NAME)
                .document(DOCUMENT)
                .build();

        when(customerRepository.findByUuid(uuid)).thenReturn(Optional.of(customer));
        when(customerMapper.convertFromModel(customer)).thenReturn(customerResponse);

        CustomerResponse actual = service.getCustomerByUuid(uuid);

        verify(customerRepository).findByUuid(uuid);
        verify(customerMapper).convertFromModel(customer);
        assertEquals(uuid, actual.getUuid());
        assertEquals(NAME, actual.getName());
        assertEquals(DOCUMENT, actual.getDocument());

    }

    @Test
    void testUpdateName_should_update_name_to_newName() {
        Customer customerWithNewName = Customer
                .builder()
                .uuid(CUSTOMER_UUID)
                .name(NEW_NAME)
                .document(DOCUMENT)
                .build();

        when(customerRepository.findByUuid(CUSTOMER_UUID))
                .thenReturn(Optional.of(Customer.builder()
                        .uuid(CUSTOMER_UUID)
                        .name(NAME)
                        .document(DOCUMENT)
                        .build()));

        when(customerRepository.save(customerWithNewName))
                .thenReturn(customerWithNewName);

        service.update(CUSTOMER_UUID, CustomerRequest.builder()
                .name(NEW_NAME)
                .document(DOCUMENT)
                .build());

        verify(customerRepository)
                .findByUuid(CUSTOMER_UUID);
        verify(customerRepository)
                .save(customerWithNewName);
    }

}
