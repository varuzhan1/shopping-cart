package com.shoppingCart.service.s3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@Data
@Service

public class AmazonS3ServiceImpl implements AmazonS3Service {

//    private final String imageBucketName;
//    private final String dataBucketName;
//    private final String webBucketName;

    private final String bucketName = "shopping-cart123456-test";

    AWSCredentials credentials = new BasicAWSCredentials(
            "AKIA2AHFPMZSMLNH3NB4",
            "rnbvLS7mVhDv0+wiWuUGNPNrWk5mlGwNtcDb1iyG"
    );
    AmazonS3 s3client = AmazonS3ClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withRegion(Regions.US_EAST_2)
            .build();

    public void createBucket(String bucketName) {

        if (s3client.doesBucketExist(bucketName)) {
            log.info("Bucket name is not available."
                    + " Try again with a different Bucket name.");
            return;
        }

        s3client.createBucket(bucketName);
    }

    public void removeBucket(String bucketName) {
        try {
            ObjectListing objectListing = s3client.listObjects(bucketName);
            if (!s3client.listObjects(bucketName).getObjectSummaries().isEmpty()) {

                List<S3ObjectSummary> objectSummaries = objectListing.getObjectSummaries();

                for (S3ObjectSummary s3ObjectSummary : objectSummaries) {
                    s3client.deleteObject(bucketName, s3ObjectSummary.getKey());
                }
            }
            s3client.deleteBucket(bucketName);
        } catch (AmazonServiceException e) {
            System.out.println(e.getErrorMessage());
        }
    }


    public List<Bucket> getBucketsList() {
        List<Bucket> buckets = s3client.listBuckets();
        for (Bucket bucket : buckets) {
            System.out.println(bucket.getName());
        }
        return buckets;
    }

    public void uploadTextToS3(String bucketName, String key, File file) {
        s3client.putObject(bucketName, key, file);
    }

    public ObjectListing getAllObjectListing() {
        ObjectListing objectListing = s3client.listObjects(bucketName);
        for (S3ObjectSummary os : objectListing.getObjectSummaries()) {
            log.info(os.getKey());
        }
        return objectListing;

    }

    public void downloadFile(String bucketName, String key, String pathname) throws IOException {
        S3Object s3object = s3client.getObject(bucketName, key);
        S3ObjectInputStream inputStream = s3object.getObjectContent();
        FileUtils.copyInputStreamToFile(inputStream, new File(pathname));
    }

    public void copyFile(String sourceBucketName, String sourceKey, String destinationBucketName, String destinationKey) {
        s3client.copyObject(
                sourceBucketName,
                sourceKey,
                destinationBucketName,
                destinationKey
        );
    }

    public void deleteObject(String bucketName, String key) {
        s3client.deleteObject(bucketName, key);
    }

    public void DeletingMultipleObjects(String bucketName, String[] objKeyArr) {
        DeleteObjectsRequest delObjReq = new DeleteObjectsRequest(bucketName)
                .withKeys(objKeyArr);
        s3client.deleteObjects(delObjReq);
    }


}
