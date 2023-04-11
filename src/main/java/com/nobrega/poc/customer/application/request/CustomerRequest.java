package com.nobrega.poc.customer.application.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CustomerRequest(
        @NotBlank
        String name,
        @NotBlank
        String document
) {
}
