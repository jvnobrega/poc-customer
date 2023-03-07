package com.nobrega.poc.customer.domain;

import com.nobrega.poc.customer.commons.DomainModelUuid;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Table(name = "customer")
public class Customer extends DomainModelUuid {

  @EqualsAndHashCode.Include
  private String name;

  @EqualsAndHashCode.Include
  private String document;

}
