package com.eventhub.backend.utils.awsemailservice;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.eventhub.backend.utils.AwsDetailsConstants;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SendEmailToSubscriber {

    public void sendEmailToSubsciber(String subject, String message, String email) {
        BasicSessionCredentials credentials = new BasicSessionCredentials(AwsDetailsConstants.AWS_ACCESS_KEY, AwsDetailsConstants.AWS_SECRET_KEY, AwsDetailsConstants.SESSION_TOKEN);
        AmazonSNS snsClient = AmazonSNSClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1) // Set your preferred region
                .build();

        Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();
        messageAttributes.put("email", new MessageAttributeValue().withDataType("String").withStringValue(email));

        // Publish the message directly to the specific email address
        PublishRequest publishRequest = new PublishRequest()
                .withMessage(message)
                .withSubject(subject)
                .withTargetArn(AwsDetailsConstants.SNS_TOPIC_ARN) // Replace with your topic ARN
                .withMessageAttributes(messageAttributes);

        PublishResult publishResult = snsClient.publish(publishRequest);
        System.out.println("Message sent. MessageId: " + publishResult.getMessageId());
    }
}
