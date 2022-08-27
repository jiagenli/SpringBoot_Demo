package com.ljg.learn.kafka.controller;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    /**
     * 测试批量提交和手动offset
     * @param message
     */
    @RequestMapping("/send3")
    public void send3(String message) {
        for (int i = 0; i < 10; i++) {
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>("test", message + String.valueOf(i));
            kafkaTemplate.send(producerRecord);
        }
    }

    /**
     * 测试Kafka事务 注解式
     * @param num
     */
    @RequestMapping("/trans")
    @Transactional(rollbackFor = RuntimeException.class)
    public void send3(Integer num) {
        kafkaTemplate.send("test-transaction", "这是事务消息1 + " + num);
        if (num == 0) {
            throw new RuntimeException();
        }
        kafkaTemplate.send("test-transaction", "这是事务消息2 + " + num);
    }

    /**
     * 测试Kafka事务 声明式
     * @param num
     */
    @RequestMapping("/trans2")
    public void send4(Integer num) {
        kafkaTemplate.executeInTransaction(new KafkaOperations.OperationsCallback() {
            @Override
            public Object doInOperations(KafkaOperations operations) {
                operations.send("test-transaction", "这是事务消息1 + " + num);
                if (num == 0) {
                    throw new RuntimeException();
                }
                operations.send("test-transaction", "这是事务消息2 + " + num);
                return true;
            }
        });
    }
}
