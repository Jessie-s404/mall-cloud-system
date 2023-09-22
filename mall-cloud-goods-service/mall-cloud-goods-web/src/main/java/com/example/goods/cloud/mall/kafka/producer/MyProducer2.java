package com.example.goods.cloud.mall.kafka.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: MyProducer2
 * Description:
 *
 * @Author: shenjiaqi
 * 编辑于：2023/9/22 9:30   @Version 1.0        描述
 */
public class MyProducer2 {
    public static void main(String[] args) {
        Map<String, Object> configs = new HashMap<>();
        configs.put("bootstrap.servers", "node1:9092");
        configs.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        configs.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<Integer, String> producer = new KafkaProducer<>(configs);

        ProducerRecord<Integer, String> record = new ProducerRecord<>(
                "topic_1",
                0,
                0,
                "lagou message 2"
        );

        //NOTE 使用回调异步等待消息的确认
        producer.send(record, (metadata, e) -> {
            if (e == null) {
                System.out.println(
                        "主题：" + metadata.topic() + "\n"
                        + "分区：" + metadata.partition() + "\n"
                        + "偏移量：" + metadata.offset() + "\n"
                        + "序列化的key字节：" + metadata.serializedKeySize() + "\n"
                        + "序列化的value字节：" + metadata.serializedValueSize() + "\n"
                        + "时间戳" + metadata.timestamp()
                );
            } else {
                System.out.println("有异常：" + e.getMessage());
            }
        });
        // 关闭连接
        producer.close();
    }
}
