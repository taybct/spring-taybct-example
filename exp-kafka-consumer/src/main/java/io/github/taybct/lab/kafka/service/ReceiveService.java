package io.github.taybct.lab.kafka.service;

import io.github.taybct.lab.kafka.domain.MessagePayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * 发送服务
 * </pre>
 *
 * @author XiJieYin
 * @since 2025/5/15 16:22
 */
@AutoConfiguration
@Service
@Slf4j
@RequiredArgsConstructor
public class ReceiveService {

    @KafkaListener(topics = "my-topic", groupId = "my-group")
    public void listen(ConsumerRecord<String, String> record, Acknowledgment ack) {
        String key = record.key();           // 获取消息的 Key
        String value = record.value();       // 获取消息的 Value
        String topic = record.topic();       // 获取消息的 Topic
        int partition = record.partition(); // 获取消息的 Partition
        long offset = record.offset();      // 获取消息的 Offset
        long timestamp = record.timestamp(); // 获取消息的时间戳

        // 处理消息
        System.out.println("Received message: ");
        System.out.println("Key: " + key);
        System.out.println("Value: " + value);
        System.out.println("Topic: " + topic);
        System.out.println("Partition: " + partition);
        System.out.println("Offset: " + offset);
        System.out.println("Timestamp: " + timestamp);

        // 手动提交偏移量
        ack.acknowledge();
    }

    @KafkaListener(topics = "my-topic2", groupId = "my-group")
    public void listen2(ConsumerRecord<String, MessagePayload> record, Acknowledgment ack) {
        String key = record.key();           // 获取消息的 Key
        MessagePayload value = record.value();       // 获取消息的 Value
        String topic = record.topic();       // 获取消息的 Topic
        int partition = record.partition(); // 获取消息的 Partition
        long offset = record.offset();      // 获取消息的 Offset
        long timestamp = record.timestamp(); // 获取消息的时间戳

        // 处理消息
        System.out.println("Received message: ");
        System.out.println("Key: " + key);
        System.out.println("Value: " + value);
        System.out.println("Topic: " + topic);
        System.out.println("Partition: " + partition);
        System.out.println("Offset: " + offset);
        System.out.println("Timestamp: " + timestamp);

        // 手动提交偏移量
        ack.acknowledge();
    }

}
