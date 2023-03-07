package com.nobrega.poc.customer.domain;

import com.nobrega.poc.customer.application.CustomerRequest;
import com.nobrega.poc.customer.application.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerCreatorService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public CustomerResponse save(CustomerRequest request) {
        final var customer = customerMapper.convertFromRequest(request);
        customerRepository.save(customer);
        kafkaTemplate.send("create-customer", customer.toString());
        return  customerMapper.convertFromModel(customer);
    }
}
