package com.paxier;

import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Path("/accounts")
public class AccountResource {

    Set<Account> accounts = new HashSet<>();

    @PostConstruct
    public void setup() {
        accounts.add(Account.builder().accountNumber(1111L).customerNumber(1111L).customerName("Mary").balance(new BigDecimal("1000")).build());
        accounts.add(Account.builder().accountNumber(2222L).customerNumber(2222L).customerName("Amna").balance(new BigDecimal("2000")).build());
        accounts.add(Account.builder().accountNumber(3333L).customerNumber(3333L).customerName("Roq").balance(new BigDecimal("3000")).build());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Account> allAccounts() {
        return accounts;
    }
}
