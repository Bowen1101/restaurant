package com.ascending.training.bowen.service;

import com.amazonaws.services.mediaconvert.model.Queue;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageService {

    @Autowired
    Logger logger;
    @Autowired
    AmazonSQS amazonSQS;

    public String createQueue(String queueName){
        String queueUrl = null;

        try{
            queueUrl = getQueueUrl(queueName);
        }
        catch (QueueDoesNotExistException e){
            CreateQueueRequest createQueueRequest = new CreateQueueRequest(queueName);
            queueUrl = amazonSQS.createQueue(createQueueRequest).getQueueUrl();
        }
        return queueUrl;
    }

    public List<String> listQueue(){
        List<String> list = new ArrayList<>();
        try {
            ListQueuesResult listQueuesResult = amazonSQS.listQueues();
            list = listQueuesResult.getQueueUrls();
            return list;
        } catch (Exception e){
            list = null;
        }
        return list;
    }

    public boolean deleteQueue(String queueName){
        boolean isSuccess = false;
        if(amazonSQS.getQueueUrl(queueName)!=null){
            amazonSQS.deleteQueue(queueName);
            isSuccess = true;
        }
        else {
            return isSuccess;
        }
        return isSuccess;
    }

    public String getQueueUrl(String queueName){
        return amazonSQS.getQueueUrl(queueName).getQueueUrl();
    }

    public void sendMessage(String queueName, String msg){
        Map<String, MessageAttributeValue> messageAttributes = new HashMap();
        MessageAttributeValue messageAttributeValue = new MessageAttributeValue();
        messageAttributeValue.withDataType("String").withStringValue("File URL in S3");
        messageAttributes.put("message", messageAttributeValue);
        String queueUrl = getQueueUrl(queueName);
        SendMessageRequest sendMessageRequest = new SendMessageRequest();
        sendMessageRequest.withQueueUrl(queueUrl)
                .withMessageBody(msg)
                .withMessageAttributes(messageAttributes);
        amazonSQS.sendMessage(sendMessageRequest);
    }

    public List<Message> getMessage(String queueName){
        System.out.printf("Receiving messages from %s \n", queueName);
        String queueUrl = getQueueUrl(queueName);
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueUrl);
        List<Message> messages = amazonSQS.receiveMessage(receiveMessageRequest).getMessages();
        for (Message message : messages) {
            System.out.println("Message");
            System.out.println("  MessageId:     " + message.getMessageId());
            System.out.println("  ReceiptHandle: " + message.getReceiptHandle());
            System.out.println("  MD5OfBody:     " + message.getMD5OfBody());
            System.out.println("  Body:          " + message.getBody());
        }
        return messages;
    }

    public boolean deleteMessage(String queueName){
        boolean isSuccess = false;
        System.out.println("Deleting the message.\n");
        String queueUrl = amazonSQS.getQueueUrl(queueName).getQueueUrl();
        List<Message> messages = amazonSQS.receiveMessage(new ReceiveMessageRequest(queueUrl)).getMessages();
        String messageReceiptHandle = messages.get(0).getReceiptHandle();
        DeleteMessageRequest deleteMessageRequest = new DeleteMessageRequest(queueUrl, messageReceiptHandle);
        amazonSQS.deleteMessage(deleteMessageRequest);
        isSuccess = true;
        return isSuccess;
    }

}
