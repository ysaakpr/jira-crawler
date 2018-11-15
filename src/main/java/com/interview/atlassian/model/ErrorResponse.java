package com.interview.atlassian.model;

/**
 * Generic error message for the api
 */
public class ErrorResponse {
    String message;
    Integer errorCode;

    public ErrorResponse(String message, Integer errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}
