package com.nobrega.poc.customer.domain.customer;

import com.nobrega.poc.customer.application.request.CustomerRequest;
import com.nobrega.poc.customer.application.response.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerMapper {

    public Customer convertFromRequest(CustomerRequest customerRequest) {
        return Customer.builder()
                .name(customerRequest.name())
                .document(customerRequest.document())
                .build();
    }

    public CustomerResponse convertFromModel(Customer customer) {
        return CustomerResponse.builder()
                .uuid(customer.getUuid())
                .name(customer.getName())
                .document(customer.getDocument())
                .build();
    }
}
