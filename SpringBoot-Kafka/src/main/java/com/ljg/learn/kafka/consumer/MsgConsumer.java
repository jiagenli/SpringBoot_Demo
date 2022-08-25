package com.ljg.learn.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@KafkaListener(topics = {"mingyue"}, groupId = "test-consumer-group")
public class MsgConsumer {

//    @KafkaHandler
//    public void receive(String message) {
//        log.info("消费者收到的消息是：" + message);
//    }

    /**
     * 这个方法是测试用ConsumerRecord来接收消息
     * 一开始的时候报了这个异常 No method found for class java.lang.String
     * 原因是因为没有加 (isDefault = true)
     * @param consumerRecord
     */
    @KafkaHandler(isDefault = true)
    public void receiveRecord(ConsumerRecord<String, String> consumerRecord) {
        log.info("测试用consumerRecord得到的消息：" + consumerRecord.key() + consumerRecord.value());
    }
}
