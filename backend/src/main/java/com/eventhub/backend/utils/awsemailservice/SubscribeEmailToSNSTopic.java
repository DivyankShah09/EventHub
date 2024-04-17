package com.eventhub.backend.utils.awsemailservice;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;
import com.eventhub.backend.utils.AwsDetailsConstants;
import org.springframework.stereotype.Component;

@Component
public class SubscribeEmailToSNSTopic {

    public void subscribeToEmailService(String email) {
        // Create AWS credentials
        BasicSessionCredentials credentials = new BasicSessionCredentials(AwsDetailsConstants.AWS_ACCESS_KEY, AwsDetailsConstants.AWS_SECRET_KEY, AwsDetailsConstants.SESSION_TOKEN);

        // Create an Amazon SNS client
        AmazonSNS snsClient = AmazonSNSClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1) // Set your preferred region
                .build();

        // Subscribe the email address to the SNS topic
        SubscribeRequest subscribeRequest = new SubscribeRequest()
                .withProtocol("email")
                .withEndpoint(email)
                .withTopicArn(AwsDetailsConstants.SNS_TOPIC_ARN);

        SubscribeResult subscribeResult = snsClient.subscribe(subscribeRequest);
        System.out.println("Subscription ARN: " + subscribeResult.getSubscriptionArn());
    }
}

