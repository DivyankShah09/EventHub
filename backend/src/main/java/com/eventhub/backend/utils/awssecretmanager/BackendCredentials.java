package com.eventhub.backend.utils.awssecretmanager;

// Use this code snippet in your app.
// If you need more information about configurations or implementing the sample
// code, visit the AWS docs:
// https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/home.html

// Make sure to import the following packages in your code

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.*;
import org.springframework.stereotype.Component;

@Component
public class BackendCredentials {
    public String getSecret(String awsAccessKey, String awsSecretKey, String sessionToken, String secretName) {

        BasicSessionCredentials credentials = new BasicSessionCredentials(awsAccessKey, awsSecretKey, sessionToken);
        AWSSecretsManager client = AWSSecretsManagerClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1)
                .build();
        GetSecretValueRequest request = new GetSecretValueRequest().withSecretId(secretName);

        try {
            GetSecretValueResult result = client.getSecretValue(request);
            return result.getSecretString();
        } catch (ResourceNotFoundException e) {
            System.out.println("The requested secret " + secretName + " was not found");
        } catch (InvalidRequestException e) {
            System.out.println("The request was invalid due to: " + e.getMessage());
        } catch (InvalidParameterException e) {
            System.out.println("The request had invalid params: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }
}



