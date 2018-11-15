package com.interview.atlassian.services;

import com.interview.atlassian.exceptions.MessagePublishFailedException;

public interface Publisher {
    void publish(Object message);
}
