package com.paxier.githubservice;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubUser {
    private String login;
    private Long id;
    private String url;

}
