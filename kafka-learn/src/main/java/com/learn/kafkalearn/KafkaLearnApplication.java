package com.learn.kafkalearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class KafkaLearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaLearnApplication.class, args);
    }


    @KafkaListener(topics = "${message.topic.name}", groupId = "foo", containerFactory = "kafkaListenerContainerFactory")
    public void listenGroupFoo(String message) {
        System.out.println("Received Messasge in group 'foo': " + message);
    }

}
