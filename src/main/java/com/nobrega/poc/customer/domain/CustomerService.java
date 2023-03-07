package com.nobrega.poc.customer.domain;

import com.nobrega.poc.customer.application.CustomerRequest;
import com.nobrega.poc.customer.application.CustomerResponse;
import com.nobrega.poc.customer.commons.ResponseList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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

  public ResponseList<CustomerResponse> getAll(PageRequest pageRequest) {
    return customerMapper.convertFromModel(customerRepository.findAll(pageRequest));
  }

  public void update(String uuid, CustomerRequest request) {
    Customer customerUpdated = customerRepository
        .findByUuid(uuid)
        .map(customer -> {
          customer.setDocument(request.getDocument());
          customer.setName(request.getName());
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
