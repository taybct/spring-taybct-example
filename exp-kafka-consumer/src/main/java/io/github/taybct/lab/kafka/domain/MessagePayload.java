package io.github.taybct.lab.kafka.domain;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <pre>
 * 消息体
 * </pre>
 *
 * @author XiJieYin
 * @since 2025/5/15 16:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MessagePayload implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 键
     */
    private String key;
    /**
     * 内容
     */
    private String content;
    /**
     * 时间戳
     */
    private LocalDateTime timestamp;

}
