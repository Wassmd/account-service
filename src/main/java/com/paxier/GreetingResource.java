package com.paxier;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
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
