package com.shoppingCart.service.sqs;

import java.io.File;

public interface AmazonSQSService {

    void createBucket(String bucketName);

    void uploadTextToS3(String bucketName, String key, File file);

}
