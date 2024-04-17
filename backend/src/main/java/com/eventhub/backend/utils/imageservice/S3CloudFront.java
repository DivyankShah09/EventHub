package com.eventhub.backend.utils.imageservice;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cloudfront.AmazonCloudFront;
import com.amazonaws.services.cloudfront.AmazonCloudFrontClientBuilder;
import com.amazonaws.services.cloudfront.model.GetDistributionRequest;
import com.amazonaws.services.cloudfront.model.GetDistributionResult;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.eventhub.backend.utils.AwsDetailsConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Component
public class S3CloudFront {
    public String uploadImageGetUrl(String eventName, MultipartFile multipartFile) {
//        String bucketName = "eventhub-event-images";
//        String cloudFrontDistributionId = "E3O32Q0P596X8A";

        BasicSessionCredentials awsCredentials = new BasicSessionCredentials(AwsDetailsConstants.AWS_ACCESS_KEY, AwsDetailsConstants.AWS_SECRET_KEY, AwsDetailsConstants.SESSION_TOKEN);

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.US_EAST_1)
                .build();

        AmazonCloudFront cloudFrontClient = AmazonCloudFrontClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.US_EAST_1)
                .build();


        eventName = eventName.replace(" ", "_");
        String fileName = multipartFile.getOriginalFilename();

        try {
            InputStream inputStream = multipartFile.getInputStream();

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(multipartFile.getSize());
            PutObjectRequest request = new PutObjectRequest(AwsDetailsConstants.BUCKET_NAME, eventName + "/" + fileName, inputStream, metadata);
            PutObjectResult putObjectResult = s3Client.putObject(request);

            // Get CloudFront distribution domain
            GetDistributionRequest distributionRequest = new GetDistributionRequest().withId(AwsDetailsConstants.CLOUD_FRONT_DISTRIBUTION_ID);
            GetDistributionResult distributionResult = cloudFrontClient.getDistribution(distributionRequest);
            String distributionDomain = distributionResult.getDistribution().getDomainName();

            String url = "https://" + distributionDomain + "/" + eventName + "/" + fileName;
            System.out.println(url);
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
