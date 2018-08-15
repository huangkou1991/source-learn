package com.learn.kafkalearn;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.config.ContainerProperties;
import org.springframework.kafka.requestreply.ReplyingKafkaOperations;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.kafka.support.SendResult;
import org.springframework.retry.annotation.Backoff;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class KafkaLearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaLearnApplication.class, args);
    }


    @KafkaListener(topics = "${message.topic.name}", groupId = "foo", containerFactory = "kafkaListenerContainerFactory")
    public void listenGroupFoo(String message) {
        System.out.println("Received Messasge in group 'foo': " + message);
    }

    //The following Spring Boot application is an example of how to use the feature:
    /*@Bean
    public ApplicationRunner runner(ReplyingKafkaTemplate<String, String, String> template) {

        return args -> {
            ProducerRecord<String, String> record = new ProducerRecord<>("kRequests", "foo");
            record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, "kReplies".getBytes()));
            RequestReplyFuture<String, String, String> replyFuture = template.sendAndReceive(record);
            SendResult<String, String> sendResult = replyFuture.getSendFuture().get();
            System.out.println("Sent ok: " + sendResult.getRecordMetadata());
            ConsumerRecord<String, String> consumerRecord = replyFuture.get();
            System.out.println("Return value: " + consumerRecord.value());
        };
    }*/

    @Bean
    public ReplyingKafkaTemplate<String, String, String> kafkaTemplate(ProducerFactory<String, String> pf,
                                                                       KafkaMessageListenerContainer<String, String> kf) {
        return new ReplyingKafkaTemplate<>(pf, kf);
    }

    @Bean
    public KafkaMessageListenerContainer<String, String> replayContainer(ConsumerFactory<String, String> cf) {
        ContainerProperties containerProperties = new ContainerProperties("kReplies");
        return new KafkaMessageListenerContainer<>(cf, containerProperties);
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "localhost:9092");
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                "foo1");
        props.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public NewTopic kRequests() {
        return new NewTopic("kRequests", 10, (short) 2);
    }

    @Bean
    public NewTopic kReplies() {
        return new NewTopic("kReplies", 10, (short) 2);
    }


}
