package com.paxier.githubservice;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/api/github")
public class GitHubResource {
    @Inject
    GitHubServiceClient gitHubServiceClient;

    @GET
    @Path("/users/{username}")
    public GitHubUser getUser(@PathParam("username") String username) {
        return gitHubServiceClient.getUser(username);
    }
}
