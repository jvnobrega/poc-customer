package com.nobrega.poc.customer.commons;

import java.io.Serializable;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseList<T extends Serializable> {

  private static final Integer FIRST_PAGE = 0;

  private Integer number;

  private Integer size;

  private Integer numberOfElements;

  private Long totalElements;

  private List<T> content;

}
