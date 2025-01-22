package com.paxier;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.paxier.AccountStatus.OPEN;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "AccountEntity.findAll",
        query = "SELECT a FROM AccountEntity a  ORDER BY a.accountNumber")
@NamedQuery(name = "AccountEntity.findByAccountNumber",
        query = "SELECT a FROM AccountEntity a  WHERE   a.accountNumber = :accountNumber ORDER BY a.accountNumber")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long accountNumber;
    private Long customerNumber;
    private String customerName;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus = OPEN;
}
