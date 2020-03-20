package com.ssk.haoke.im.websocket;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Component
/**
 * 消息处理的拦截器
 */
public class MessageHandshakeInterceptor implements HandshakeInterceptor {

    /**
     * @param request
     * @param response
     * @param webSocketHandler
     * @param map
     * @return false： true：放行
     * @throws Exception
     *在MessageHandler之前执行
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response,
                                   WebSocketHandler webSocketHandler,
                                   Map<String, Object> map) throws Exception {

        String path = request.getURI().getPath();
        String[] ss = StringUtils.split(path,"/");
        //todo
        for (String s : ss) {
            System.out.println(s);
        }
        if (ss.length != 2) {
            return false;
        }
        if (!StringUtils.isNumeric(ss[1])) {
            return false;
        }
        map.put("uid", Long.valueOf(ss[1]));
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request,
                               ServerHttpResponse response,
                               WebSocketHandler webSocketHandler, @Nullable Exception e) {

    }
}