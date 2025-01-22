package com.paxier;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.jbosslog.JBossLog;

import java.util.Optional;

@JBossLog
@ApplicationScoped
public class AccountService {

    @Inject
    AccountRepository accountRepository;

    public Optional<Account> createAccount(AccountEntity accountEntity) {
        return accountRepository.createAccount(accountEntity);
    }

    public Optional<Account> getAccountDetails(String accountNumber) {
        return accountRepository.getAccountDetails(accountNumber);
    }
}
