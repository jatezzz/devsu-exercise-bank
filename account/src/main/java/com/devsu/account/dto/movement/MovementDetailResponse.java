package com.devsu.account.dto.movement;

import com.devsu.account.dto.account.AccountDetailResponse;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record MovementDetailResponse(
        Long id,
        AccountDetailResponse account,
        LocalDateTime date,
        BigDecimal amount,
        BigDecimal initialBalance,
        BigDecimal currentBalance,
        String type,
        Boolean status
) {
}
