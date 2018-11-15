package com.interview.atlassian.model.jira;

/**
 * Basic Issue format from Jira
 */
public class Issue {
    private String issueKey;
    private IssueFields fields;

    public Issue() {
    }

    public String getIssueKey() {
        return issueKey;
    }

    public void setIssueKey(String issueKey) {
        this.issueKey = issueKey;
    }

    public IssueFields getFields() {
        return fields;
    }

    public void setFields(IssueFields fields) {
        this.fields = fields;
    }
}
