package com.ascending.training.service;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.util.IOUtils;
import com.ascending.training.bowen.init.AppInitializer;
import com.ascending.training.bowen.service.FileService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInitializer.class)
public class FileServiceMockAWSTest {
    @Mock(answer = Answers.RETURNS_DEEP_STUBS) private AmazonS3 amazonS3;
    @Autowired @Spy private Logger logger;
    @InjectMocks private FileService fileService;


    private String bucketName = "training_ascending_com_bowen_shen_test";
    private String fileName = "test.txt";
    private URL fakeFileUrl;
    private MultipartFile multipartFile;
    private String path;

    @Before
    public void setup() throws MalformedURLException, FileNotFoundException, IOException {
        logger.info(">>>>>>>>>>>>>>>>>> Start Testing ... <<<<<<<<<<<<<<<<<<<");

        //Mocks are initialized before each test method
        MockitoAnnotations.initMocks(this);

        fakeFileUrl = new URL("http://www.fakeQueueUrl.com/abc/123/fake");
        File file = new File("/Users/bowenshen/Downloads/test.txt");
        FileInputStream input = new FileInputStream(file);
        multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(input));
        path = System.getProperty("User.dir") + File.separator + "temp";

        //Stubbing for the method doesObjectExist and generatePresignedUrl
        when(amazonS3.doesObjectExist(anyString(), anyString())).thenReturn(false);
        when(amazonS3.generatePresignedUrl(any())).thenReturn(fakeFileUrl);
    }

    @After
    public void cleanUp(){
        logger.info(">>>>>>>>>>>>>>>>>> End Testing <<<<<<<<<<<<<<<<<<<<<");
    }

    @Test
    public void getFileUrlTest(){
        String fileUrl = fileService.getFileUrl(bucketName,fileName);
        Assert.assertEquals(fileUrl, fakeFileUrl.toString());
        verify(amazonS3,times(1)).generatePresignedUrl(any());
    }

    @Test
    public void uploadFileTest() throws IOException {
        fileService.uploadFile(bucketName,multipartFile);

    }




}
