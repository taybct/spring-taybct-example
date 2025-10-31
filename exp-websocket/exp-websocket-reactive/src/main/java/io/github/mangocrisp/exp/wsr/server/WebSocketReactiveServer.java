package io.github.mangocrisp.exp.wsr.server;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import io.github.taybct.tool.core.annotation.ServerReactiveEndpoint;
import io.github.taybct.tool.core.websocket.endpoint.AbstractWebSocketReactiveServer;
import io.github.taybct.tool.core.websocket.enums.MessageUserType;
import io.github.taybct.tool.core.websocket.support.MessageUser;
import io.github.taybct.tool.core.websocket.support.WSR;
import io.github.taybct.tool.core.websocket.support.WebsocketReactiveSession;
import org.springframework.boot.autoconfigure.AutoConfiguration;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 * websocket 服务
 * </pre>
 *
 * @author XiJieYin
 * @since 2025/6/13 14:46
 */
@AutoConfiguration
@ServerReactiveEndpoint("/websocket/{deptId}/{userId}")
public class WebSocketReactiveServer extends AbstractWebSocketReactiveServer {

    @Override
    public void onMessage(WebsocketReactiveSession session, Long userId, String message) {
        Map<String, List<String>> requestParameter = getRequestParameterMap(session.session().getId());
        // 从 session 里面拿请求参数，知道需要发送给的用户，这些用户可能是群发
        List<String> toUserIdList = requestParameter.get("toUserId");
        if (CollectionUtil.isNotEmpty(toUserIdList)) {
            toUserIdList.forEach(toUserId -> sendMessage(WSR.ok(message)
                    .setFromUser(new MessageUser(MessageUserType.USER, userId, session.session().getId()))
                    .setToUserId(Convert.toLong(toUserId))));
        }
    }

    @Override
    public void onMessage(WebsocketReactiveSession session, Long userId, byte[] binaryMessage) {
        // 文件传输测试
        if (binaryMessage != null){
            sendMessage(WSR.ok().setBytes(binaryMessage));
        }
    }
}
