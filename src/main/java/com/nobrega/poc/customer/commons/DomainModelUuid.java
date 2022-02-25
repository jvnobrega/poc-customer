package com.nobrega.poc.customer.commons;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public class DomainModelUuid extends DomainModel {

  @Column(name = "uuid")
  private String uuid;

  @PrePersist
  public void initialize() {
    this.uuid = UUID.randomUUID().toString();
    super.initialize();
  }
}
