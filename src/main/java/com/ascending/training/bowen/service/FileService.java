package com.ascending.training.bowen.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

@Service
public class FileService {
    @Autowired private Logger logger;
    @Autowired private AmazonS3 amazonS3;

    public boolean createBucket(String bucketName){
        boolean isSuccess = false;
        logger.info("Creating a bucket " + bucketName);
        if( amazonS3.doesBucketExistV2(bucketName)) {
            return isSuccess;
        }else{
            amazonS3.createBucket(bucketName);
            isSuccess = true;
        }
        return isSuccess;
    }

    public List<Bucket> listBuckets() {
        return amazonS3.listBuckets();
    }

    public boolean deleteBucket(String bucketName){
        boolean isSuccess = false;
        if(amazonS3.doesBucketExistV2(bucketName)){
            amazonS3.deleteBucket(bucketName);
            isSuccess = true;
        }

        logger.info("delete: " +isSuccess);
        return isSuccess;
    }

    public String getFileUrl(String bucketName, String fileName){
        LocalDateTime expiration = LocalDateTime.now().plusDays(1);
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName,fileName);
        generatePresignedUrlRequest.withMethod(HttpMethod.GET);
        generatePresignedUrlRequest.withExpiration(Date.from(expiration.toInstant(ZoneOffset.UTC)));

        return amazonS3.generatePresignedUrl(generatePresignedUrlRequest).toString();
    }

    public String uploadFile(String bucketName, MultipartFile file) throws IOException {
        try {
            if (amazonS3.doesObjectExist(bucketName, file.getOriginalFilename())) {
                logger.info(String.format("The file '%s' exists in the bucket %s", file.getOriginalFilename(), bucketName));
                return null;
            }
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(file.getSize());
            amazonS3.putObject(bucketName, file.getOriginalFilename(), file.getInputStream(), objectMetadata);
            logger.info(String.format("The file name=%s, size=%d was uploaded to bucket %s", file.getOriginalFilename(), file.getSize(), bucketName));
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
        return getFileUrl(bucketName, file.getOriginalFilename());
    }

    public boolean deleteFile(String bucketName, String fileName){
        boolean isSuccess = false;
        try{
            if (!amazonS3.doesObjectExist(bucketName, fileName)) {
                logger.info(String.format("The file name=%s does not exist in the bucket %s", fileName, bucketName));
                return isSuccess;
            }
            amazonS3.deleteObject(bucketName,fileName);
            isSuccess = true;
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return isSuccess;
        }
        return isSuccess;
    }

    public List<S3ObjectSummary> listFiles(String bucketName){
        try {
            if (!amazonS3.doesBucketExistV2(bucketName)) {
                logger.info(String.format("The bucket %s does not exist", bucketName));
                return null;
            }
            ListObjectsV2Request listObjectsV2Request = new ListObjectsV2Request().withBucketName(bucketName);
            ListObjectsV2Result result;
            do {
                result = amazonS3.listObjectsV2(listObjectsV2Request);
                for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {
                    System.out.printf(" - %s (size: %d)\n", objectSummary.getKey(), objectSummary.getSize());
                }
            }
            while (result.isTruncated());
        }catch (AmazonS3Exception e){
            e.getMessage();
        }catch (SdkClientException e){
            e.getMessage();
        }
        return amazonS3.listObjectsV2(new ListObjectsV2Request().withBucketName(bucketName)).getObjectSummaries();
    }

    public boolean saveFile(MultipartFile multipartFile, String filePath){
        boolean isSuccess = false;

        try{
            File directory = new File(filePath);
            if(!directory.exists()) directory.mkdir();
            Path filepath = Paths.get(filePath, multipartFile.getOriginalFilename());
            multipartFile.transferTo(filepath);
            isSuccess = true;
            logger.info(String.format("The file %s is saved in the folder%s", multipartFile.getOriginalFilename(),filePath));
        }
        catch (Exception e){
            logger.error(e.getMessage());
        }
        return isSuccess;
    }
}
