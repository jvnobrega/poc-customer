package com.nobrega.poc.customer;

import com.nobrega.poc.customer.commons.DomainModelUuid;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
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
@Table(name = "customer")
public class Customer extends DomainModelUuid {

  private String name;

  private String document;

}
