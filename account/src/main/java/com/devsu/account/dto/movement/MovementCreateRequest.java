package com.devsu.account.dto.movement;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record MovementCreateRequest(
        @JsonProperty("numeroCuenta")
        String accountNumber,
        @JsonProperty("valor")
        BigDecimal amount,
        @JsonProperty("tipo")
        String type,
        @JsonProperty(value = "estado", defaultValue = "true")
        Boolean status
) {
}
