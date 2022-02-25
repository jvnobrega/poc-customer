package com.nobrega.poc.customer.commons;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {

  private final ApplicationErrorCode applicationErrorCode;

  public ServiceException(ApplicationErrorCode applicationErrorCode) {
    super(applicationErrorCode.getMessage());
    this.applicationErrorCode = applicationErrorCode;
  }

  public ServiceException(ApplicationErrorCode applicationErrorCode, String message) {
    super(message);
    this.applicationErrorCode = applicationErrorCode;
  }

}
