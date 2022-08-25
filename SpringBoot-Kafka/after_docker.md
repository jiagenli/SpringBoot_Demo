## 在这里记录一下通过docker-compose创建 Containers 之后的命令
[参考资料](https://blog.csdn.net/csp732171109/article/details/124489764)

### 启动docker
```shell
docker-compose up -d
```

### 测试
#### 通过容器名称进入到kafka容器中
```shell
docker exec -it kafka /bin/bash
```
-it的作用是可以进入伪终端

### 创建topic
```shell
kafka-topics.sh --create --topic mingyue \
--zookeeper zookeeper:2181 --replication-factor 1 \
--partitions 1
```

### 查看刚刚创建的 topic
```shell
kafka-topics.sh --zookeeper zookeeper:2181 --describe --topic mingyue
```

### 打开生产者发送消息
```shell
kafka-console-producer.sh --topic=mingyue --broker-list kafka:9092
```

### 打开消费者查看消息
```shell
kafka-console-consumer.sh --bootstrap-server kafka:9092 --from-beginning --topic mingyue
```

### 查看offset，offset存在broker中
```shell
kafka-consumer-groups.sh --bootstrap-server kafka:9092 --describe --group test-consumer-group
```
