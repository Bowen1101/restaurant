package com.ascending.training.service;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.ascending.training.bowen.init.AppInitializer;
import com.ascending.training.bowen.service.FileService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Table;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInitializer.class)
public class FileServiceTest {
    @Autowired
    private FileService fileService;
    @Autowired
    private Logger logger;
    @Autowired
    AmazonS3 amazonS3;

    private String bucketName = "training-bucket.com.ascending.bowen.shen.test";
    private String fileName = "test.txt";
    private String filePath = "/Users/bowenshen/Downloads/test.txt";
    private String downloadPath = "/Users/bowenshen/Downloads/temp";
    private MultipartFile multipartFile;

    @Before
    public void init() throws IOException {
        fileService.createBucket(bucketName);
        File file = new File(filePath);
        FileInputStream input = new FileInputStream(file);
        multipartFile = new MockMultipartFile(fileName, file.getName(),"text/plain", input );
    }

    @After
    public void cleanUp(){

        if(amazonS3.doesObjectExist(bucketName,fileName)){
            fileService.deleteFile(bucketName,fileName);
        }

        if(amazonS3.doesBucketExistV2(bucketName)) {

            fileService.deleteBucket(bucketName);
        }

        fileService = null;
    }

    @Test
    public void createBucketTest(){
        boolean result = fileService.createBucket(bucketName);
        Assert.assertFalse(result);
    }

    @Test
    public void listBucketTest(){
        List<Bucket> result = fileService.listBuckets();
        Assert.assertEquals(result.size(),2);
    }

    @Test
    public void deleteBucketTest(){
        boolean result = fileService.deleteBucket(bucketName);
        Assert.assertTrue(result);
    }

    @Test
    public void uploadFileTest() throws IOException {
        String result = fileService.uploadFile(bucketName, multipartFile);
        Assert.assertNotNull(result);
    }

    @Test
    public void saveFileTest(){
        boolean result = fileService.saveFile(multipartFile,downloadPath);
        Assert.assertTrue(result);
    }

    @Test
    public void listFilesTest(){
        List<S3ObjectSummary> result = fileService.listFiles(bucketName);
        Assert.assertEquals(result.size(), 0);
    }

    @Test
    public void deleteFileTest(){
        fileService.deleteFile(bucketName,fileName);
        List<S3ObjectSummary> result = fileService.listFiles(bucketName);
        Assert.assertEquals(result.size(),0);
    }
}
