package com.devsu.account.dto.movement;

import com.devsu.account.model.MovementType;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record MovementItemResponse(
        Long id,
        String accountNumber,
        BigDecimal amount,
        BigDecimal initialBalance,
        BigDecimal currentBalance,
        MovementType type,
        Boolean status
) {
}
