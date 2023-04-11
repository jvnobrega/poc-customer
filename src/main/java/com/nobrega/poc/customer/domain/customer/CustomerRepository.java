package com.nobrega.poc.customer.domain.customer;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

  Optional<Customer> findByUuid(String uuid);

  Page<Customer> findAll(Pageable pageable);

}
