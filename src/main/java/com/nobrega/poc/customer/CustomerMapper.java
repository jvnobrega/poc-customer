package com.nobrega.poc.customer;

import com.nobrega.poc.customer.commons.ResponseList;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerMapper {

  private final ModelMapper modelMapper;

  public Customer convertFromRequest(CustomerRequest customerRequest) {
    return modelMapper.map(customerRequest, Customer.class);
  }

  public CustomerResponse convertFromModel(Customer customer) {
    return modelMapper.map(customer, CustomerResponse.class);
  }

  public ResponseList<CustomerResponse> convertFromModel(Page<Customer> customers) {
    return ResponseList.<CustomerResponse>builder()
        .content(customers.stream()
            .map(this::convertFromModel)
            .collect(Collectors.toList()))
        .numberOfElements(customers.getNumberOfElements())
        .number(customers.getNumber())
        .size(customers.getSize())
        .totalElements(customers.getTotalElements())
        .build();
  }
}
