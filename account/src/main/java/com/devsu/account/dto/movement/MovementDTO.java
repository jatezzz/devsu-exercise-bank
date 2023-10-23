package com.devsu.account.dto.movement;

import com.devsu.account.dto.account.AccountDTO;
import com.devsu.account.model.MovementType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class MovementDTO {
    private Long id;
    private AccountDTO account;
    private LocalDateTime date;
    private BigDecimal amount;
    private BigDecimal initialBalance;
    private BigDecimal currentBalance;
    private MovementType type;
    private Boolean status;
}
