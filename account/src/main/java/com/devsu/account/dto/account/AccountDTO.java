package com.devsu.account.dto.account;

import com.devsu.account.model.AccountType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccountDTO {
    private Long id;
    private ClientDto client;
    private String number;
    private BigDecimal balance;
    private Boolean status;
    private AccountType type;
}
