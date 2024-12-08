package com.paxier;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class AccountResourceTest {

    @Test
    void testRetrieveAll() {
        Response result = given()
                .when().get("/accounts")
                .then().statusCode(200)
                .body(
                        containsString("Amna")
                )
                .extract().response();

        List<Account> account = result.jsonPath().getList("$");
        assertFalse(account.isEmpty());
        assertEquals(3, account.size());
    }

    @Test
    void testGetAccount() {
        Account account = given()
                .when().get("/accounts/{accpuntNumber}", 1111L)
                .then().statusCode(200)
                .extract()
                .as(Account.class);

        assertEquals(1111, account.getAccountNumber());
        assertEquals("Mary", account.getCustomerName());
        assertEquals(new BigDecimal("1000"), account.getBalance());
    }

    @Test
    void testCreateAccount() {
        Account newAccount = Account.builder()
                .customerNumber(4444L)
                .accountNumber(4444L)
                .customerName("Wasim")
                .balance(new BigDecimal("110"))
                .build();
        Account accountCreated = given().contentType(ContentType.JSON)
                .body(newAccount)
                .when().post("/accounts")
                .then().statusCode(201)
                .extract()
                .as(Account.class);

        assertEquals(accountCreated, newAccount);

        Response result = given()
                .when().get("/accounts")
                .then().statusCode(200)
                .body(
                        containsString("Wasim")
                )
                .extract().response();

        List<Account> account = result.jsonPath().getList("$");
        assertFalse(account.isEmpty());
        assertEquals(4, account.size());
    }
}
