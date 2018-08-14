package com.learn.kafkalearn.kafka.controller;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :lwy
 * @date 2018/7/18 15:29
 */
@RestController
public class KafkaController {


    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Value(value = "${message.topic.name}")
    private String topicName;

    @GetMapping
    @ResponseBody
    public void sendMessage() {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, "hello");
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.err.println(throwable);
            }

            @Override
            public void onSuccess(SendResult<String, String> stringStringSendResult) {
                RecordMetadata recordMetadata = stringStringSendResult.getRecordMetadata();
                System.err.println("recordMetaData: " + recordMetadata);
            }
        });
    }
}
