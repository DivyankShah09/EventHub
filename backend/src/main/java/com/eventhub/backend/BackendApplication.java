package com.eventhub.backend;

import com.eventhub.backend.utils.AwsDetailsConstants;
import com.eventhub.backend.utils.awssecretmanager.BackendCredentials;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        try {
            String awsAccessKey = args[0];
            String awsSecretKey = args[1];
            String sessionToken = args[2];

            getSecretValues(awsAccessKey, awsSecretKey, sessionToken);

            SpringApplication.run(BackendApplication.class, args);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void getSecretValues(String awsAccessKey, String awsSecretKey, String sessionToken) {
        System.out.println("getting secrets...");
//        BackendCredentials backendCredentials = new BackendCredentials();
//        JSONObject jsonObject = new JSONObject(backendCredentials.getSecret(awsAccessKey, awsSecretKey, sessionToken, "eventhub/secretmanager"));

        // getting secrets
//        String databaseUrl = jsonObject.get("databaseUrl").toString();
//        String databaseUsername = jsonObject.get("databaseUsername").toString();
//        String databasePassword = jsonObject.get("databasePassword").toString();
//        String jwtSecretKey = jsonObject.get("jwtSecretKey").toString();
//        String snsTopicArn = jsonObject.get("snsTopicArn").toString();
//        String bucketName = jsonObject.get("bucketName").toString();
//        String cloudFrontDistributionId = jsonObject.get("cloudFrontDistributionId").toString();
        String databaseUrl = "*********";
        String databaseUsername = "*********";
        String databasePassword = "*********";
        String jwtSecretKey = "*********";
        String snsTopicArn = "";
        String bucketName = "*********";
        String cloudFrontDistributionId = "*********";

        AwsDetailsConstants.AWS_ACCESS_KEY = awsAccessKey;
        AwsDetailsConstants.AWS_SECRET_KEY = awsSecretKey;
        AwsDetailsConstants.SESSION_TOKEN = sessionToken;
        AwsDetailsConstants.JWT_SECRET_KEY = jwtSecretKey;
        AwsDetailsConstants.SNS_TOPIC_ARN = snsTopicArn;
        AwsDetailsConstants.BUCKET_NAME = bucketName;
        AwsDetailsConstants.CLOUD_FRONT_DISTRIBUTION_ID = cloudFrontDistributionId;

        //setting secrets to application.properties
//        System.setProperty("spring.datasource.url", "jdbc:mysql://cloud-database.cbusgeiy8bid.us-east-1.rds.amazonaws.com:3306/eventhub?allowPublicKeyRetrieval=true&useSSL=false");
//        System.setProperty("spring.datasource.url", "jdbc:mysql://localhost:3306/event_hub?allowPublicKeyRetrieval=true&useSSL=false");
        System.setProperty("spring.datasource.url", databaseUrl);
        System.out.println("DB changed: " + databaseUrl);
        System.setProperty("spring.datasource.username", databaseUsername);
        System.setProperty("spring.datasource.password", databasePassword);
    }
}
