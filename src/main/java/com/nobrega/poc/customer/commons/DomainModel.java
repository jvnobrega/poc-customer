package com.nobrega.poc.customer.commons;

import static java.time.Clock.systemUTC;
import static java.time.LocalDateTime.now;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
public class DomainModel implements Serializable {

  private static final long serialVersionUID = 442483281102619496L;

  @Id
  @GeneratedValue
  private Long id;

  private LocalDateTime inputDate;

  private LocalDateTime lastUpdatedDate;

  @Version
  private Integer version;

  @PrePersist
  public void initialize() {
    this.inputDate = now(systemUTC());
    this.lastUpdatedDate = now(systemUTC());
  }

  @PreUpdate
  public void updateLastUpdatedDate() {
    this.lastUpdatedDate = now(systemUTC());
  }

}
