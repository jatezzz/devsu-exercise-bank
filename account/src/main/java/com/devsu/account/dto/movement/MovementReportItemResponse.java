package com.devsu.account.dto.movement;

import com.devsu.account.model.AccountType;
import com.devsu.account.model.MovementType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MovementReportItemResponse(
        String movementUid,
        LocalDateTime date,
        String accountNumber,
        AccountType accountType,
        MovementType movementType,
        BigDecimal initialBalance,
        BigDecimal currentBalance,
        Boolean status,
        BigDecimal movementAmount

) {
}
