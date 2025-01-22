package com.paxier;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/accounts")
public class AccountResource {

    Set<Account> accounts = new HashSet<>();
    @Inject
    EntityManager entityManager;

    @Inject
    AccountService accountService;

    @PostConstruct
    public void setup() {
        accounts.add(Account.builder().accountNumber(1111L).customerNumber(1111L).customerName("Mary").balance(new BigDecimal("1000")).build());
        accounts.add(Account.builder().accountNumber(2222L).customerNumber(2222L).customerName("Amna").balance(new BigDecimal("2000")).build());
        accounts.add(Account.builder().accountNumber(3333L).customerNumber(3333L).customerName("Roq").balance(new BigDecimal("3000")).build());
    }

    @GET
    @Produces(APPLICATION_JSON)
    public Set<Account> allAccounts() {
        return accounts;
    }

    @GET
    @Path("/{accountNumber}")
    public Account getAccount(@PathParam("accountNumber") Long accountNumber) {
        return accounts.stream()
                .filter(it -> it.getAccountNumber().equals(accountNumber))
                .findFirst()
//                .orElseThrow(() -> new NotFoundException("Account with id of "+ accountNumber + "doesn't exist"));
        .orElseThrow(() -> new WebApplicationException("Account with id of "+ accountNumber + "doesn't exist", 404));
    }

    @GET
    @Path("/q/{accountNumber}")
    public Account getAccountUsingQuery(@PathParam("accountNumber") String accountNumber) {
        return accountService.getAccountDetails(accountNumber)
                .orElseThrow(() -> new NotFoundException("Account with id of "+ accountNumber + "doesn't exist"));
    }

//    @POST
//    @Consumes(APPLICATION_JSON)
//    @Produces(APPLICATION_JSON)
//    public Response createAccount(Account account) {
//        if (account.getAccountNumber() == null) {
//            throw new WebApplicationException("Account number not specified", 400);
//        }
//        accounts.add(account);
//        return Response.status(201).entity(account).build();
//    }

    @GET
    public List<AccountEntity> allBankAccounts() {
        return entityManager
                .createNamedQuery("AccountEntity.findAll", AccountEntity.class)
                .getResultList();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Transactional
    public Response createAccount(Account account) {
        AccountEntity accountEntity = new AccountEntity(
                -1L,
                account.getAccountNumber(),
                account.getCustomerNumber(),
                account.getCustomerName(),
                account.getBalance(),
                AccountStatus.OPEN);
        try {
            accountService.createAccount(accountEntity);
            return Response.status(201).entity(account).build();
        } catch (Exception e) {
            throw new WebApplicationException("Exception", 500);
        }
    }
}
