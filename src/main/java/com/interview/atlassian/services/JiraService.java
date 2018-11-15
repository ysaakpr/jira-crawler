package com.interview.atlassian.services;

import com.interview.atlassian.exceptions.MessagePublishFailedException;
import com.interview.atlassian.model.IssueSumResponse;
import com.interview.atlassian.model.jira.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;

/**
 * JiraService abstracts the apis for Jira, and the aggregative functions over it;
 */
@Service
public class JiraService {

    RestTemplate restTemplate = new RestTemplate();

    public static final String JIRA_ISSUE_URL = "/rest/api/2/search?q=";

    @Autowired Environment env;

    /**
     * Creates the summary report
     * @param searchQuery
     * @param name
     * @return
     */
    public IssueSumResponse sumIssuesPoints(String searchQuery, String name) {
        IssueSumResponse response = new IssueSumResponse(name,0);
        Issue[] issues = fetchIssues(searchQuery);
        for(Issue issue : issues) {
            response.setTotalPoints(response.getTotalPoints() + issue.getFields().getStoryPoints());
        }
        return response;
    }

    /**
     * Fetches the issues from Jira with the query
     * @param searchQuery
     * @return
     */
    public Issue[] fetchIssues(String searchQuery) {
        try {
            ResponseEntity<Issue[]> responseEntity = restTemplate.getForEntity(env.getProperty("jira.base.url") + JIRA_ISSUE_URL + searchQuery, Issue[].class);
            Object[] objects = responseEntity.getBody();
            HttpStatus statusCode = responseEntity.getStatusCode();
            if (statusCode.is2xxSuccessful()) {
                return responseEntity.getBody();
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        throw new RuntimeException("Unable to fetch details from jira api");
    }
}
