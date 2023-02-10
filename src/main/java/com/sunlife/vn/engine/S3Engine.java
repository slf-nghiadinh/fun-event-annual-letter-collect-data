package com.sunlife.vn.engine;

import com.sunlife.vn.actionframework.engine.AmazonS3Engine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import com.amazonaws.services.s3.model.AmazonS3Exception;

@Component
public class S3Engine extends AmazonS3Engine {

    @Autowired(required = false)
    private S3Client s3Client;

    public void createFloderStream(String bucket, String key) throws NoSuchKeyException {
        PutObjectRequest putObjectRequest;
        try {
            this.getObjectAsStream(bucket, key);
        } catch (AmazonS3Exception e) {
            System.err.println(e.getErrorMessage());
        } catch (NoSuchKeyException ex) {
            putObjectRequest = PutObjectRequest.builder().bucket(bucket).key(key).build();
            this.s3Client.putObject(putObjectRequest, RequestBody.empty());
        }
    }
    public String putObjectByteArray(String bucket, String key, byte[] dataAsByte) {
        PutObjectRequest putObjectRequest = (PutObjectRequest)PutObjectRequest.builder().bucket(bucket).key(key).build();
        RequestBody requestBody = RequestBody.fromBytes(dataAsByte);
        PutObjectResponse putObjectResponse = s3Client.putObject(putObjectRequest, requestBody);
        return putObjectResponse.eTag();
    }
}
