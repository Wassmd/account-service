package com.paxier.githubservice;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class GitHubServiceClient {

    @Inject
    @RestClient
    GitHubService gitHubService;

    public GitHubUser getUser(String username) {
        return gitHubService.getUser(username);
    }
}
