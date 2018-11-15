package com.interview.atlassian.services;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.atlassian.exceptions.MessagePublishFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SQSPublisherService implements  Publisher{

    @Autowired private Environment env;
    @Autowired private ObjectMapper mapper;
    @Autowired private AmazonSQS queue;

    @Override
    public void publish(Object message) {
        try {
            String sMessage = mapper.writeValueAsString(message);
            queue.sendMessage(env.getProperty("queue.url"), sMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new MessagePublishFailedException(e);
        }
    }
}
