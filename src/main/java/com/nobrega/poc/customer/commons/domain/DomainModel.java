package com.nobrega.poc.customer.commons.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import static java.time.Clock.systemUTC;
import static java.time.LocalDateTime.now;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public class DomainModel implements Serializable {

  @Serial
  private static final long serialVersionUID = 442483281102619496L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDateTime inputDate;

  private LocalDateTime lastUpdatedDate;

  @Version
  private Integer version;

  @PrePersist
  protected void initialize() {
    this.inputDate = now(systemUTC());
    this.lastUpdatedDate = now(systemUTC());
  }

  @PreUpdate
  public void updateLastUpdatedDate() {
    this.lastUpdatedDate = now(systemUTC());
  }

}
