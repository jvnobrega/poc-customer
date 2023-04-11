package com.nobrega.poc.customer.commons.domain.error;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public interface ApplicationErrorCode extends Serializable {

  String getPrefixCode();

  String getCode();

  String getMessage();

  HttpStatus getStatus();
}
