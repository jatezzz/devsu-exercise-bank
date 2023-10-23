package com.devsu.account.dto.account;

import java.math.BigDecimal;

public record AccountUpdateRequest(
        BigDecimal balance,
        Boolean status
) {
}
