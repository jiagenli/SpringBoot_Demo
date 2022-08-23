package com.ljg.learn.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@KafkaListener(topics = {"mingyue"}, groupId = "test-consumer-group")
public class MsgConsumer {

    @KafkaHandler
    public void receive(String message) {

    }
}
