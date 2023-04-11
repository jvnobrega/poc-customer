package com.nobrega.poc.customer.domain.customer;

import com.nobrega.poc.customer.commons.domain.DomainModelUuid;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
