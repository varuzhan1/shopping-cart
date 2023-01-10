package com.shoppingCart.service.sqs;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;

@Slf4j
@Data
@Service
public class AmazonSQSServiceImpl implements AmazonSQSService{


    AWSCredentials credentials = new BasicAWSCredentials(
            "AKIA2AHFPMZSMLNH3NB4",
            "rnbvLS7mVhDv0+wiWuUGNPNrWk5mlGwNtcDb1iyG"
    );
    AmazonS3 s3client = AmazonS3ClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
//            .withRegion(Regions.US_EAST_2)
            .withRegion(Regions.US_EAST_1)
            .build();

    @Override
    public void createBucket(String bucketName) {

        if (s3client.doesBucketExist(bucketName)) {
            log.info("Bucket name is not available."
                    + " Try again with a different Bucket name.");
            return;
        }

        s3client.createBucket(bucketName);
    }

    @Override
    public void uploadTextToS3(String bucketName, String key, File file) {
        s3client.putObject(bucketName, key, file);
    }
}
