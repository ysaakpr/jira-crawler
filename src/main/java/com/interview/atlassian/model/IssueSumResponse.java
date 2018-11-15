package com.interview.atlassian.model;

/**
 * IssueSumResponse represents the sum of total story points  against a query
 */
public class IssueSumResponse {
    private String name;
    private long totalPoints;

    public IssueSumResponse() {
    }

    public IssueSumResponse(String name, long totalPoints) {
        this.name = name;
        this.totalPoints = totalPoints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(long totalPoints) {
        this.totalPoints = totalPoints;
    }
}
