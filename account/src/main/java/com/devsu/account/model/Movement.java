package com.devsu.account.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Movement extends AbstractEntity {

    @ManyToOne
    private Account account;
    private LocalDateTime date;
    private BigDecimal amount;
    private BigDecimal initialBalance;
    private BigDecimal currentBalance;
    private MovementType type;
    private Boolean status;
}
