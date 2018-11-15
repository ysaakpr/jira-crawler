package com.interview.atlassian.controller;

import com.interview.atlassian.model.IssueSumResponse;
import com.interview.atlassian.services.JiraService;
import com.interview.atlassian.services.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * IssueController handles the http api related to issue
 */
@RestController
@RequestMapping("/api/issue")
public class IssueController {

    @Autowired JiraService service;
    @Autowired Publisher publisher;

    /**
     * /sum handle  s the story point aggregation for a specific issues query
     * @param searchQuery
     * @param descriptiveName
     * @return IssueSumResponse
     */
    @RequestMapping(value = "/sum",method = {RequestMethod.GET})
    public IssueSumResponse getAssemblyRequestDetails(
            @RequestParam(name = "query",required = true) String searchQuery,
            @RequestParam(name = "name",required = true) String descriptiveName
    ) {
        //Fetch the IssueSumResponse from the jira service
        IssueSumResponse response = service.sumIssuesPoints(searchQuery, descriptiveName);

        //Publish the response to the publisher before sending the response back to the Api
        publisher.publish(response);
        return response;
    }

}
