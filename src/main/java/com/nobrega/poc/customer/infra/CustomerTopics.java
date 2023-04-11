package com.nobrega.poc.customer.infra;

import lombok.Getter;

@Getter
public enum CustomerTopics {
    CREATE_CUSTOMER("create-customer");

    private final String name;

    CustomerTopics(String name) {
        this.name = name;
    }
}
