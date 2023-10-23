package com.devsu.account.dto.movement;

import com.devsu.account.model.MovementType;

import java.math.BigDecimal;

public record MovementUpdateRequest(
        BigDecimal amount,
        BigDecimal initialBalance,
        BigDecimal currentBalance,
        MovementType type,
        Boolean status
) {
}
