package com.interview.atlassian.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class BeanConfigs {

    @Autowired
    Environment env;

    @Bean
    public AmazonSQS createSQSClient() {
        System.out.println(env.getProperty("queue.url"));
        System.out.println(env.getProperty("jira.base.url"));

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
