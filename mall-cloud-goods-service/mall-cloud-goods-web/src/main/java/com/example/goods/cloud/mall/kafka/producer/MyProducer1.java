package com.example.goods.cloud.mall.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * ClassName: MyProducer1
 * Description:
 *
 * @Author: shenjiaqi
 * 编辑于：2023/9/22 9:14   @Version 1.0        描述
 */
public class MyProducer1 {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        Map<String, Object> configs = new HashMap<>();
        //note 设置连接kafka的初始连接用到的服务器地址
        //     如果是集群，则可以通过此初始连接发现集群中的其他broker
        configs.put("bootstrap.servers", "node1:9092");
        //设置key的序列化器
        configs.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        //设置value的序列化器
        configs.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        configs.put("acks", "1");

        KafkaProducer<Integer, String> producer = new KafkaProducer<Integer, String>(configs);

        //NOTE ProducerRecord用于封装Producer的消息
        ProducerRecord<Integer, String> record = new ProducerRecord<>(
                "topic_1",  // 主题名称
                0,  // 分区编号
                0,  // 数字作为key
                "message 0" // 字符串作为value
        );

        // 发送消息，同步等待消息的确认
        producer.send(record).get(3_000, TimeUnit.MILLISECONDS);

        // 关闭生产者
        producer.close();
    }
}
