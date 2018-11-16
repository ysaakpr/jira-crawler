package com.interview.atlassian.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class BeanConfigs {
    Logger logger = LoggerFactory.getLogger(BeanConfigs.class);

    @Autowired
    Environment env;

    @Bean
    public AmazonSQS createSQSClient() {
        logger.info("Queue URl is {}",env.getProperty("queue.url"));
        logger.info("Jira Base URL is {}", env.getProperty("jira.base.url"));

        String endpoint = env.getProperty("queue.url");
        String region = "elasticmq";
        String accessKey = "x";
        String secretKey = "x";
        AmazonSQS client = AmazonSQSClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, region))
                .build();
        return  client;
    }
}
