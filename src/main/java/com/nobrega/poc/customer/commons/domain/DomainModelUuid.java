package com.nobrega.poc.customer.commons.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public class DomainModelUuid extends DomainModel {

    @Column(name = "uuid")
    private String uuid;

    @Override
    protected void initialize() {
        this.uuid = UUID.randomUUID().toString();
        super.initialize();
    }
}
