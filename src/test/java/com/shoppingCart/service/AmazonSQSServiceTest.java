package com.shoppingCart.service;

import com.shoppingCart.service.s3.AmazonS3ServiceImpl;
import com.shoppingCart.service.sqs.AmazonSQSService;
import com.shoppingCart.service.sqs.AmazonSQSServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class AmazonSQSServiceTest {


   private final  String bucketName = "shopping-cart-sqs-test";
   private final String kay = "Document/sqs/testFile";

    private AmazonSQSService amazonS3Service;



    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        amazonS3Service = new AmazonSQSServiceImpl();
    }

    @Test
    public void createBucketForSqs(){
        amazonS3Service.createBucket(bucketName);
    }

    @Test
    public void uploadTextToS3Test() {
        amazonS3Service.uploadTextToS3(bucketName,kay.concat(String.valueOf(UUID.randomUUID())),new File("src/main/resources/files/hello.txt"));
    }

}
