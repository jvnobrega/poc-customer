package com.nobrega.poc.customer.commons.infra;

import com.nobrega.poc.customer.commons.domain.error.ApplicationErrorCode;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
public class ServiceException extends RuntimeException implements Serializable {
  @Serial
  private static final long serialVersionUID = 2405172041950251807L;

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
