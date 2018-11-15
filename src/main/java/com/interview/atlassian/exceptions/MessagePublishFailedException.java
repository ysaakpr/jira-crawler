package com.interview.atlassian.exceptions;

public class MessagePublishFailedException extends RuntimeException {
    public MessagePublishFailedException() {
    }

    public MessagePublishFailedException(String message) {
        super(message);
    }

    public MessagePublishFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessagePublishFailedException(Throwable cause) {
        super(cause);
    }

    public MessagePublishFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
