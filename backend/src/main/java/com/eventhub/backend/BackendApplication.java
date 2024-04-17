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

            System.setProperty("aws.access.key", awsAccessKey);
            System.setProperty("aws.secret.key", awsSecretKey);
            System.setProperty("aws.session.token", sessionToken);

            getSecretValues(awsAccessKey, awsSecretKey, sessionToken);

            SpringApplication.run(BackendApplication.class, args);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void getSecretValues(String awsAccessKey, String awsSecretKey, String sessionToken) {
        System.out.println("getting secrets...");
        BackendCredentials backendCredentials = new BackendCredentials();
        JSONObject jsonObject = new JSONObject(backendCredentials.getSecret(awsAccessKey, awsSecretKey, sessionToken, "eventhub/secretmanager"));

        // getting secrets
        String databaseUrl = jsonObject.get("databaseUrl").toString();
        String databaseUsername = jsonObject.get("databaseUsername").toString();
        String databasePassword = jsonObject.get("databasePassword").toString();
        String jwtSecretKey = jsonObject.get("jwtSecretKey").toString();
        String snsTopicArn = jsonObject.get("snsTopicArn").toString();
        String bucketName = jsonObject.get("bucketName").toString();
        String cloudFrontDistributionId = jsonObject.get("cloudFrontDistributionId").toString();

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
//ASIA6GBMBALOWCT26N5K UU24j3rNsdu9A3htGRuWVODbspmlCvKAqFB2boAm IQoJb3JpZ2luX2VjEMf//////////wEaCXVzLXdlc3QtMiJHMEUCIC4sSyPCGdfyJaWatUEL4sKWT8B9DUzfMPY0vVSP9XDbAiEAq3xnHUVqDAltxDeYxNcKx06jdk+aajZm24HltU0FzVYqsgII8P//////////ARAAGgw5NzUwNDk5MTcxNDkiDLgco+41kRYrwPVbeCqGAjBs5xGf1Rqz5Nhh3u2R5zwR8/tVIoYX4F1QAs2f3N8czQ+9JHMZXcAAwXyqipvX+6WkYlIvn7l57EnC1b8kT4frTra5WKygDlpJw3lOPm2J5Gu5/Uj3VGMmkZFx97RUD5CQzoHSIPNQqxNCnoWIwVZNSMbZIDowgy7wNSvqu+4GKZ/1/mH3zlvq4yS37ez4CxMgSF5l4BDPmzpiWW/v2e72gdnxn7YxiF+Zus5asHCtiNt/8G6ZOj9zhPaIIY1wvXvBYPxkixueZsLQissfyyG6c7bUFSvu/PJquq6OS+PpsMIPSu1AqEOjokJUvebwuoPoZ8vUSsTPqsOocIQNKJYSo/1KZdownIXQsAY6nQFGZBfWgterudIt2XQ++yIpyS5CNmVtvnCKI5BZ9uqvshlMr0XLMovAgR3cR2OylEr3zZFbvY/SOhpXGjhcu5Hev6f15DOlvrL5iSBbzCvy4FSVZFZZAWU4Z8bJX7GigkAIl7HB6WCx9SFeGOwmO2P9rEEtQ5qPlxUm5AylsGl+q7pFwNL+7oBXJ4875cW+sBg7hV+KELlEedV9239D