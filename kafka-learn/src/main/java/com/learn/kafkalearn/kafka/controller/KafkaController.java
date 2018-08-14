package com.learn.kafkalearn.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
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
    public void sendMessage(){
        kafkaTemplate.send(topicName,"hello");
    }
}
