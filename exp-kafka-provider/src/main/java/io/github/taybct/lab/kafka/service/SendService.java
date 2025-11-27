package io.github.taybct.lab.kafka.service;

import io.github.taybct.lab.kafka.domain.MessagePayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.kafka.core.KafkaTemplate;
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
public class SendService {

    final KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * 发送消息
     *
     * @param topic   主题
     * @param key     key
     * @param message 消息
     */
    public void send(String topic, String key, String message) {
        kafkaTemplate.send(topic, key, message).handle((result, ex) -> {
            if (ex == null) {
                log.info("发送成功：{}", result);
            } else {
                log.error("发送失败：{}", ex.getMessage());
            }
            return result;
        });
    }

    /**
     * 发送消息
     *
     * @param topic   主题
     * @param payload 消息
     */
    public void send(String topic, MessagePayload payload) {
        kafkaTemplate.send(topic, payload.getKey(), payload).handle((result, ex) -> {
            if (ex == null) {
                log.info("发送成功：{}", result);
            } else {
                log.error("发送失败：{}", ex.getMessage());
            }
            return result;
        });
    }

}
