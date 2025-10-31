package io.github.mangocrisp.exp.wsm.server;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import io.github.taybct.tool.core.exception.def.BaseException;
import io.github.taybct.tool.core.result.ResultCode;
import io.github.taybct.tool.core.websocket.endpoint.AbstractWebSocketServer;
import io.github.taybct.tool.core.websocket.enums.MessageUserType;
import io.github.taybct.tool.core.websocket.support.MessageUser;
import io.github.taybct.tool.core.websocket.support.WSR;
import jakarta.websocket.CloseReason;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.HttpStatus;

import java.io.IOException;
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
@ServerEndpoint("/websocket/{deptId}/{userId}")
public class WebSocketServer extends AbstractWebSocketServer {

    @SneakyThrows
    @Override
    public void onOpen(Long userId, Session session) {
        // TODO，可以利用请求参数传递鉴权参数比如 jti 之类的，如果验证失败，说明 token 已经过期或者被篡改
        //noAuthorizationHandle(userId, session);
        super.onOpen(userId, session);
    }

    /**
     * 未鉴权处理
     *
     * @param userId  用户 id
     * @param session session 会话
     * @throws IOException io 异常
     */
    private void noAuthorizationHandle(Long userId, Session session) throws IOException {
        sendMessage(WSR.fail(ResultCode.TOKEN_INVALID_OR_EXPIRED.getCode(), "用户鉴权失败！"), new MessageUser(MessageUserType.USER, userId, session.getId()));
        session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT, "用户鉴权失败！"));
        // 如果在在线用户列表里面找得到这个 jti 说明是登录成功并且在线的，允许连接，如果不在就报错
        throw new BaseException("用户鉴权失败！").setHttpStatus(HttpStatus.UNAUTHORIZED);
    }

    @Override
    public void onMessage(Session session, Long userId, String message) {
        Map<String, List<String>> requestParameter = getRequestParameterMap(session.getId());
        // 从 session 里面拿请求参数，知道需要发送给的用户，这些用户可能是群发
        List<String> toUserIdList = requestParameter.get("toUserId");
        if (CollectionUtil.isNotEmpty(toUserIdList)) {
            toUserIdList.forEach(toUserId -> sendMessage(WSR.ok(message)
                    .setFromUser(new MessageUser(MessageUserType.USER, userId, session.getId()))
                    .setToUserId(Convert.toLong(toUserId))));
        }
    }

    @Override
    public void onMessage(Session session, Long userId, byte[] binaryMessage) {
        // 文件传输测试
        if (binaryMessage != null) {
            sendMessage(WSR.ok().setBytes(binaryMessage));
        }
    }

}
