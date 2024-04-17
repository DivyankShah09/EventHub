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
        String databaseUrl = "jdbc:mysql://localhost:3306/event_hub?allowPublicKeyRetrieval=true&useSSL=false";
        String databaseUsername = "divyank";
        String databasePassword = "div123!!!";
        String jwtSecretKey = "xBrexmbxkkI3L0Dd8wY9EHjpYq6nqqYVOpUr1vtbVFHD9avKZtyfujRCndbcASasbJUZeceG62nacNtDrm3Q2oTDtnIjAcMXVZ";
        String snsTopicArn = "";
        String bucketName = "eventhub-image-bucket";
        String cloudFrontDistributionId = "EPX7E1ZTNS8UZ";

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


//args
//ASIA6GBMBALO565UWHXP kMx7oRMlDXdoc/QmpzFNzsQ100xbuNA0Df9wtQoI IQoJb3JpZ2luX2VjEKT//////////wEaCXVzLXdlc3QtMiJHMEUCIEUu6+180F8NC86K3tqyHgZk7bs4W+itoA4Y+b0s5i73AiEAhaIkWBZpARmsRnouk6nYZryL5GHZLMhlsVhIrUD+CWIqsgII3f//////////ARAAGgw5NzUwNDk5MTcxNDkiDKrr7uw3VROYTH7BfSqGAn25mRelbEAPNMAdqBLRDnn4b7gEGDDsX+1L1wJaOHR3WOy26lUAtD8OVO0nfvlv5NR3q8iKq+6DCb8t3e5WCn0auV5+Ynwx/6ogcxRejmww2YGQyv7fCjlxeK+Q7FJfbC4ZgBnqgYRca8ykKewtagVKQRA0vGXmJMX3zYNVE8LPnA+HzW9USd3ZXZSA+LnKeO8nSSQoNDaJ6C54OjDWEh5t5PwGkYhqu7Azojptmp8Diy5aK76UKT8GIsehOxMLQM/MQGgMnP0yJNkWVRMTVqf8FiU5XM1K7gziUiRVWWACjsmDB8xn8d0wE6ePWPBAFH+SVUdf2m7/c/NjaX+NUSwRYCWvgE0w8tmAsQY6nQEOFP7ST+gb6wY1CtVqrY1NG/X15UJgZ4Az9aAQIrB5Zn9rfGJMCZjmx4/I+fA0/yZK0ffgoNoKyw4GTZkCmxqtx8oD627w5Z7ytWCsBEL6UfFL+QXTuG43kYk7y2vjVmhLrPQ2EMtCFh/cWpNRGhRcnngCGWVxuTWgbgR6woqk+9Ik3CL5pzlyi7wFLEP4xuzLRCLR5kX+9z3nHe/v