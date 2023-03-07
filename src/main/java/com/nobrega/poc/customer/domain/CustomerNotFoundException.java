package com.nobrega.poc.customer.domain;

import static com.nobrega.poc.customer.domain.CustomerErrorCode.CUSTOMER_NOT_FOUND;

import com.nobrega.poc.customer.commons.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Customer Not Found")
public class CustomerNotFoundException extends ServiceException {

  public CustomerNotFoundException() {
    super(CUSTOMER_NOT_FOUND);
  }
}
