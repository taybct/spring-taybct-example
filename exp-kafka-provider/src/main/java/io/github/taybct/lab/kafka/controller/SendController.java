package io.github.taybct.lab.kafka.controller;

import io.github.taybct.lab.kafka.domain.MessagePayload;
import io.github.taybct.lab.kafka.service.SendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * <pre>
 * 发送消息
 * </pre>
 *
 * @author XiJieYin
 * @since 2025/5/15 16:38
 */
@RestController
@RequestMapping("/send")
@RequiredArgsConstructor
public class SendController {

    final SendService sendService;

    @RequestMapping("/message")
    public String sendMessage(@RequestParam String key, @RequestParam String message) {
        sendService.send("my-topic", key, message);
        return "Message sent to Kafka topic with key: " + key;
    }

    @RequestMapping("/payload")
    public String sendPayload(@RequestParam String key, @RequestParam String message) {
        sendService.send("my-topic2", MessagePayload.builder()
                .key(key)
                .content(message)
                .timestamp(LocalDateTime.now())
                .build());
        return "Message sent to Kafka topic with key: " + key;
    }
}
