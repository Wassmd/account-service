package com.paxier.githubservice;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@RegisterRestClient(configKey = "github-base-url")
public interface GitHubService {
    @GET
    @Path("/users/{username}")
    @Produces(APPLICATION_JSON)
    GitHubUser getUser(@PathParam("username") String username);
}
