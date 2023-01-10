package com.shoppingCart.service;

import com.shoppingCart.service.s3.AmazonS3ServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;

@ExtendWith(MockitoExtension.class)
public class AmazonS3ServiceTest {

    private AmazonS3ServiceImpl amazonS3Service;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        amazonS3Service = new AmazonS3ServiceImpl();

    }

    @Test
    public void uploadTextToS3Test() {

        String bucketName = "shopping-cart-sqs-test";
        String kay = "Document/sqs/testFile";

        amazonS3Service.createBucket(bucketName);

        amazonS3Service.uploadTextToS3(bucketName,kay,new File("src/main/resources/files/hello.txt"));

    }


}
