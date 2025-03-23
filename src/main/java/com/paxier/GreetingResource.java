package com.paxier;

import io.quarkus.security.Authenticated;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/hello")
@Authenticated
public class GreetingResource {

    @GET
    @Produces("application/vnd.account-service.v1+json")
    public String hello() {
        return "Hello from Quarkus REST V1";
    }

    @GET
    @Produces("application/vnd.account-service.v2+json")
    public String hello1() {
        return "Hello from Quarkus REST V2";
    }
}
