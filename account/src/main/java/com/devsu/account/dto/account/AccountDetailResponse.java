package com.devsu.account.dto.account;

import com.devsu.account.model.AccountType;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AccountDetailResponse(
        Long id,
        Long clientId,
        String number,
        BigDecimal balance,
        Boolean status,
        AccountType type
) {
}
