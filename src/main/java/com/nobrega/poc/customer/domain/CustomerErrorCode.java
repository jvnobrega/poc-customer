package com.nobrega.poc.customer.domain;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.nobrega.poc.customer.commons.ApplicationErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum CustomerErrorCode implements ApplicationErrorCode {

  CUSTOMER_NOT_FOUND("001", NOT_FOUND, "Customer not Found");

  @Getter
  private final String code;

  @Getter
  private final String message;

  @Getter
  private final HttpStatus status;

  CustomerErrorCode(String code, HttpStatus status, String message) {
    this.code = getPrefixCode().concat(code);
    this.status = status;
    this.message = message;
  }

  @Override
  public String getPrefixCode() {
    return "PCUS-";
  }
}
