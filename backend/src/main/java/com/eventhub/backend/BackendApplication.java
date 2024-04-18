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
        String bucketName = "eventhub-event-image-bucket";
        String cloudFrontDistributionId = "E24ONSGIU8SZ2P";

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
//ASIA6GBMBALOYPTJZBBS /eJnec+gRzqA0jn5Xtt+/arBaliDh5AoexrDPV7u IQoJb3JpZ2luX2VjELr//////////wEaCXVzLXdlc3QtMiJHMEUCIBxC8qXJAVN8izGAytO+0Mu5G6wGokC2uJk56bzEOMAbAiEAzitKzaLMwfBt9jYJ63gLJGuQNLU8OgFopToc6sKgyFMqsgII8///////////ARAAGgw5NzUwNDk5MTcxNDkiDGhiQ5qELe68gR4CqiqGAt+P5H+L8DkLHA7/mjfn4KrOUsfNYqD5Gu1gH/N8p+FXIJQ+H0A4vMCec6rv7DqtfC6+wxv74uZ85I+p9SimYUOSGaYATnax3Drq1Sea8blq+fvBLrBUIB5psFXFSPWBclP0bKb5v0pv45IRUPI0mSXcToPUA7DbVPpj6H8jA6plovC3po6MCZgtGRwFGHBZeSvx9zxWlBORmCv53Xp/7nrqo90FIarSEX+Q975BdSHMqeDFY2Kv3h/SuzNzeNqlrmm2hwPRso1ZE0jqpvgFUVN/It+EziNPbvunwT9j0g+g0N4JH0ezqUOmSM62b2P+3m0R9/bjFUOGF4LDmUD8RSCswY8pis0w/saFsQY6nQG86d4TeHqx3Pejyrqj7LDH0MjG6awxr/Z7ZFHc+kc9bsZXf0ipHmurg5axvsNayYeTdc8/NdhZE6WTb/XZSMrqnuZXSzfmgPwYpneAGF+nEFLGrOlfhe8lilmmLY9A+1OMqBhEwi88kaYA/Z9F+3UF6FVsdP7CdHEBN9NU68Id8R3ptZOvsVZWEk+MbnGRUcUEmjDR4FVIC7OrU/ZN