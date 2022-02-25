package com.nobrega.poc.customer;

import com.nobrega.poc.customer.commons.ResponseList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerRepository customerRepository;

  private final CustomerMapper customerMapper;

  private final KafkaTemplate<String, String> kafkaTemplate;

  public CustomerResponse save(CustomerRequest request) {
    final var customer = customerMapper.convertFromRequest(request);
    customerRepository.save(customer);
    kafkaTemplate.send("create-customer", customer.toString());
    return  customerMapper.convertFromModel(customer);
  }

  public CustomerResponse getCustomeByUuid(String uuid) {
    return customerRepository
        .findByUuid(uuid)
        .map(customerMapper::convertFromModel)
        .orElseThrow(() -> new CustomerNotFoundException());
  }

  public ResponseList<CustomerResponse> getAll(PageRequest pageRequest) {
    return customerMapper.convertFromModel(customerRepository.findAll(pageRequest));
  }

  public void update(String uuid, CustomerRequest request) {
    customerRepository
        .findByUuid(uuid)
        .map(customer -> {
          customer.setDocument(request.getDocument());
          customer.setName(request.getName());
          return customer;
        })
        .map(customerRepository::save)
        .orElseThrow(() -> new CustomerNotFoundException());
  }

  public void delete(String uuid) {
    final var customer = customerRepository
        .findByUuid(uuid)
        .orElseThrow(() -> new CustomerNotFoundException());
    customerRepository.delete(customer);
  }
}
