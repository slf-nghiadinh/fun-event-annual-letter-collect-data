package com.sunlife.vn.services.engine;

import com.sunlife.vn.actionframework.model.input.SqsInput;
import com.sunlife.vn.actionframework.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageBatchRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageBatchRequestEntry;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class AmazonSqsEngine {

    @Autowired(required = false)
    private SqsClient sqsClient;

    public void sendMessage(SqsInput input) {
        String queueUrl = input.getQueueUrl();
        List<SqsInput.MessageData> messageDataList = input.getMessageData();

        if (CommonUtils.isNullOrEmpty(queueUrl)) {
            throw new RuntimeException("Queue Url is null/empty");
        }

        if (CommonUtils.isNullOrEmpty(messageDataList)) {
            throw new RuntimeException("Message data is null/empty");
        }

        SqsInput.MessageData messageData = messageDataList.get(0);
        String messageBody = messageData.getMessageBody();
        Integer delaySeconds = messageData.getDelaySecond();
        if (CommonUtils.isNullOrEmpty(messageBody)) {
            throw new RuntimeException("Message body is null/empty");
        }

        if (!queueUrl.endsWith(".fifo")) {
            sqsClient.sendMessage(SendMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .messageBody(messageBody)
                    .delaySeconds(delaySeconds)
                    .build());
        } else {
            String groupId = messageData.getGroupId();
            String deduplicationId = messageData.getDeduplicationId();

            if (CommonUtils.isNullOrEmpty(groupId) || CommonUtils.isNullOrEmpty(deduplicationId))
                throw new RuntimeException("SQS fifo, groupId or deduplicationId is null/empty");
            SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .messageBody(messageBody)
                    .messageDeduplicationId(deduplicationId) // deduplication Id
                    .messageGroupId(groupId) // message group Id
                    .build();
            sqsClient.sendMessage(sendMessageRequest);
        }
    }

    public void sendMessageBatch(SqsInput input) {
        String queueUrl = input.getQueueUrl();
        List<SqsInput.MessageData> batchInputs = input.getMessageData();

        if (CommonUtils.isNullOrEmpty(queueUrl)) {
            throw new RuntimeException("Queue Url is null/empty");
        }

        if (CommonUtils.isNullOrEmpty(batchInputs)) {
            throw new RuntimeException("Batch data is null/empty");
        }

        List<SendMessageBatchRequestEntry> sendMessageBatchRequestEntries =
                batchInputs.stream().map(req -> {
                    String id = req.getId();
                    String messageBody = req.getMessageBody();
                    Integer delaySeconds = req.getDelaySecond();

                    if (CommonUtils.isNullOrEmpty(id)) {
                        return SendMessageBatchRequestEntry.builder()
                                .messageBody(messageBody)
                                .delaySeconds(delaySeconds)
                                .build();
                    } else {
                        return SendMessageBatchRequestEntry.builder()
                                .id(id)
                                .messageBody(messageBody)
                                .delaySeconds(delaySeconds)
                                .build();
                    }
                }).collect(Collectors.toList());

        SendMessageBatchRequest sendMessageBatchRequest = SendMessageBatchRequest.builder()
                .queueUrl(queueUrl)
                .entries(sendMessageBatchRequestEntries)
                .build();

        sqsClient.sendMessageBatch(sendMessageBatchRequest);
    }
}
