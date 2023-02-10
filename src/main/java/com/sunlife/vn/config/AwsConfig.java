package com.sunlife.vn.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
public class AwsConfig {
    @Value("${application.aws.region:ap-southeast-1}")
    private String regionName;

    /**
     * Bean Config for S3
     * @return S3Client
     */
    @Bean
    @ConditionalOnProperty(
            value = "application.aws.s3.enabled",
            havingValue = "true"
    )
    public S3Client s3Client() {
       //ProfileCredentialsProvider profileCredentialsProvider = ProfileCredentialsProvider.create();
        return S3Client
                .builder()
                .region(Region.of(regionName))
                .build();
    }

    /**
     * Bean Config for SQS
     * @return SqsClient
     */
    @Bean
    @ConditionalOnProperty(
            value = "application.aws.sqs.enabled",
            havingValue = "true"
    )
    public SqsClient sqsClient() {
       //ProfileCredentialsProvider profileCredentialsProvider = ProfileCredentialsProvider.create();
        return SqsClient
                .builder()
                .region(Region.of(regionName))
                .build();
    }

    /**
     * Bean Config for DynamoDB
     * @return
     */
    @Bean
    @ConditionalOnProperty(
            value = "application.aws.dynamodb.enabled",
            havingValue = "true"
    )
    public DynamoDbClient dynamoDbClient() {
        //ProfileCredentialsProvider profileCredentialsProvider = ProfileCredentialsProvider.create();
        return DynamoDbClient
                .builder()
                .region(Region.of(regionName))
                .build();
    }

    @Bean
    @ConditionalOnProperty(
            value = "application.aws.dynamodb.enabled",
            havingValue = "true"
    )
    public DynamoDbEnhancedClient dynamoDbEnhancedClient() {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient())
                .build();
    }

}
