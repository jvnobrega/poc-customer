package com.nobrega.poc.customer.commons;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationErrorResponse {

  private String timestamp;

  private String error;

  private int status;

  private String message;

  private String path;

  @JsonInclude(NON_NULL)
  private List<String> details;

  @JsonIgnore
  private ApplicationErrorCode applicationErrorCode;

}
