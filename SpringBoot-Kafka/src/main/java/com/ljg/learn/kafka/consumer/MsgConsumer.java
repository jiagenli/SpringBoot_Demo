package com.ljg.learn.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@KafkaListener(topics = {"mingyue"}, groupId = "test-consumer-group")
public class MsgConsumer {

    /**
     * 这个方法是测试用ConsumerRecord来接收消息
     * 一开始的时候报了这个异常 No method found for class java.lang.String
     * 原因是因为没有加 (isDefault = true)
     * @param consumerRecord
     */
    @KafkaHandler(isDefault = false)
    public void receiveRecord(ConsumerRecord<String, String> consumerRecord) {
        log.info("测试用consumerRecord得到的消息：" + consumerRecord.key() + consumerRecord.value());
    }

    /**
     * 测试手动提交
     * @param ack
     * 报错：java.lang.ClassCastException: java.lang.String cannot be cast to org.apache.kafka.clients.consumer.ConsumerRecord
     * 修改了batchFactory bean的配置
     */
    @KafkaListener(topics = {"test"}, containerFactory = "batchFactory")
    public void receiveRecordManualOffset(List<ConsumerRecord<String, String>> recordList, Acknowledgment ack) {
        for (ConsumerRecord<String, String> record : recordList) {
            log.info("测试用consumerRecord得到的消息：" + record.toString());
        }
        ack.acknowledge();
    }

//    @KafkaHandler(isDefault = false)
//    public void receiveRecord2(Object message) {
//        log.info("消费者收到的消息是：" + message);
//    }
}
