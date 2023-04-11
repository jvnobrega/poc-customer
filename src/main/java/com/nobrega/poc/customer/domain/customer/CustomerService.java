package com.nobrega.poc.customer.domain.customer;

import com.nobrega.poc.customer.application.request.CustomerRequest;
import com.nobrega.poc.customer.application.response.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    public CustomerResponse getCustomerByUuid(String uuid) {
        return customerRepository
                .findByUuid(uuid)
                .map(customerMapper::convertFromModel)
                .orElseThrow(CustomerNotFoundException::new);
    }

    public Page<CustomerResponse> getAll(Pageable pageable) {
        return customerRepository.findAll(pageable)
                .map(customerMapper::convertFromModel);
    }

    public void update(String uuid, CustomerRequest request) {
        Customer customerUpdated = customerRepository
                .findByUuid(uuid)
                .map(customer -> {
                    customer.setDocument(request.document());
                    customer.setName(request.name());
                    return customer;
                }).orElseThrow(CustomerNotFoundException::new);
        customerRepository.save(customerUpdated);
    }

    public void delete(String uuid) {
        final var customer = customerRepository
                .findByUuid(uuid)
                .orElseThrow(CustomerNotFoundException::new);
        customerRepository.delete(customer);
    }
}
