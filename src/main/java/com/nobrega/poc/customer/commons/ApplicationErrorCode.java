package com.nobrega.poc.customer.commons;

import org.springframework.http.HttpStatus;

public interface ApplicationErrorCode {

  String getPrefixCode();

  String getCode();

  String getMessage();

  HttpStatus getStatus();
}
