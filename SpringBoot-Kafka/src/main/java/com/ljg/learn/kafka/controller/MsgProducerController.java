package com.ljg.learn.kafka.controller;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MsgProducerController {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping("/send")
    public void send(String message) {
        kafkaTemplate.send("mingyue", message);
    }

    /**
     * 其实这个方法是来测试直接发ProducerRecord会怎么样
     * 点进send方法一看，其实不管是String还是其他对象，send方法内部都是发送的ProducerRecord
     * @param message
     */
    @RequestMapping("/send2")
    public void send2(String message) {
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>("mingyue", message);
        kafkaTemplate.send(producerRecord);
    }
}
