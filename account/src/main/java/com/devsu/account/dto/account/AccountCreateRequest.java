package com.devsu.account.dto.account;

import com.devsu.account.model.AccountType;

import java.math.BigDecimal;

public record AccountCreateRequest(
        String clientId,
        String number,
        BigDecimal balance,
        Boolean status,
        AccountType type
) {
}
