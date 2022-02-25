package com.nobrega.poc.customer.commons;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.nobrega.poc.customer.commons.ApplicationErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum CommonsErrorCode implements ApplicationErrorCode {

  ARGUMENT_NOT_VALID("001", BAD_REQUEST, "Argument not valid");

  @Getter
  private final String code;

  @Getter
  private final String message;

  @Getter
  private final HttpStatus status;

  CommonsErrorCode(String code, HttpStatus status, String message) {
    this.code = getPrefixCode().concat(code);
    this.status = status;
    this.message = message;
  }

  @Override
  public String getPrefixCode() {
    return "PCOM-";
  }
}
