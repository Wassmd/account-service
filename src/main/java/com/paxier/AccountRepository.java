package com.paxier;

import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.jbosslog.JBossLog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

@JBossLog
@ApplicationScoped
public class AccountRepository {
    private static final String SCHEMA_TABLE = " mydatabase.accountentity";
    private static final String FIELDS = "(id, accountnumber, customernumber, customername, balance, accountstatus)";
    private static final String INSERT_SQL = "INSERT INTO " + SCHEMA_TABLE + " " + FIELDS + " VALUES (DEFAULT,?,?,?,?,?)";

    @Inject
    AgroalDataSource postgresDatasource;

    public Optional<Account> getAccountDetails(String accountNumber) {
        log.infov("Fetching the account details for account number {} ", accountNumber);
        String query = "SELECT * FROM accountentity WHERE account_number = ?";

        try(Connection connection = postgresDatasource.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, accountNumber);
            try(ResultSet resultSet = ps.executeQuery()) {
                return Optional.of(new Account(resultSet.getLong("account_number"),
                        resultSet.getLong("customer_number"),
                        resultSet.getString("customer_name"),
                        resultSet.getBigDecimal("balance"),
                        AccountStatus.valueOf(resultSet.getString("account_status")))
                );
            }

        } catch (Exception e) {
            log.error("Error while fetching the account details", e);

        }

        return Optional.empty();
    }

    public Optional<Account> createAccount(AccountEntity accountEntity) {
        log.infov("Creating account the account details for account number {0} ", accountEntity);

        try(Connection connection = postgresDatasource.getConnection();
            PreparedStatement ps = connection.prepareStatement(INSERT_SQL)) {
            ps.setLong(1, accountEntity.getAccountNumber());
            ps.setLong(2, accountEntity.getCustomerNumber());
            ps.setString(3, accountEntity.getCustomerName());
            ps.setBigDecimal(4, accountEntity.getBalance());
            ps.setString(5, accountEntity.getAccountStatus().toString());

            ps.executeUpdate();

        } catch (Exception e) {
            log.error("Error while fetching the account details", e);
        }

        return Optional.empty();
    }
}
