package com.paxier;

import lombok.*;

import java.math.BigDecimal;

import static com.paxier.AccountStatus.OPEN;
import static com.paxier.AccountStatus.OVERDRAWN;

@Data
@Builder
public class Account {
    private final Long accountNumber;
    private final Long customerNumber;
    private String customerName;
    private BigDecimal balance;
    @Builder.Default
    AccountStatus accountStatus = OPEN;

    public void markOverdrawn() {
        accountStatus = OVERDRAWN;
    }

    public void removeOverdrawnStatus() {
        accountStatus = OPEN;
    }

    public void close() {
        accountStatus = OVERDRAWN;
        balance = BigDecimal.valueOf(0);
    }

    public void withdrawFunds(BigDecimal amount) {
        balance = balance.subtract(amount);
    }

    public void addFunds(BigDecimal amount) {
        balance = balance.add(amount);
    }
}

