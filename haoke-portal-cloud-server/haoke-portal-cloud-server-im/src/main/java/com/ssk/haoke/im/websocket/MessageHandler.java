package com.ssk.haoke.im.websocket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.result.UpdateResult;
import com.ssk.haoke.im.dao.MessageDAO;
import com.ssk.haoke.im.pojo.Message;
import com.ssk.haoke.im.pojo.UserData;
import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.bson.types.ObjectId;
import org.hibernate.validator.internal.engine.groups.ValidationOrderGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
//@RocketMQMessageListener(
//        topic = "haoke-im-send-message-topic",
//        consumerGroup = "haoke-im-consumer",
//        messageModel = MessageModel.BROADCASTING,
//        selectorExpression = "SEND_MSG"
//)
public class MessageHandler extends TextWebSocketHandler
//        implements RocketMQListener<String>
{
    @Autowired
    private MessageDAO messageDAO;
//    @Autowired
//    private RocketMQTemplate rocketMQTemplate;

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Map<Long,WebSocketSession> SESSIONS = new HashMap<>();
    /**
     * 连接成功后处理
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws
            Exception {
        Long uid = (Long)session.getAttributes().get("uid");
        // 将当前用户的session放置到map中，后面会使用相应的session通信
        SESSIONS.put(uid, session);
    }

    public void handleTextMessage(WebSocketSession session, TextMessage textMessage)
            throws Exception {
        //连接的用户id
        Long uid = (Long) session.getAttributes().get("uid");
        //获取消息有效负载（内容：发送消息框的内容）
        JsonNode jsonNode = MAPPER.readTree(textMessage.getPayload());
        Long toId = jsonNode.get("toId").asLong();
        String msg = jsonNode.get("msg").asText();
        //封装Message对象，UserData是模拟数据（所有聊天室成员）
        Message message = Message.builder().from(UserData.USER_MAP.get(uid))
                .to(UserData.USER_MAP.get(toId))
                .msg(msg)
                .build();
        // 将消息保存到MongoDB
        message = this.messageDAO.saveMessage(message);

        String msgStr = MAPPER.writeValueAsString(message);
        System.out.println("msgStr"+msgStr);
        // 判断to用户是否在线
        WebSocketSession toSession = SESSIONS.get(toId);
        if(toSession != null && toSession.isOpen()){
            //TODO 具体格式需要和前端对接
            toSession.sendMessage(new TextMessage(msgStr));
            // 更新消息状态为已读
            this.messageDAO.updateMessageState(message.getId(), 2);
        }
//        else {
//            //用户不在线，或者不在当前的jvm中，发送消息到RocketMQ
//            org.springframework.messaging.Message mqMessage = MessageBuilder.withPayload(msgStr).build();
//            //topic：tags 设置主题和标签
//            this.rocketMQTemplate.send("haoke-im-send-message-topic:SEND_MSG",mqMessage);
//        }
    }
    //有消息时执行该方法
//    @Override
//    public void onMessage(String msg) {
//        System.out.println("接收到的消息->"+msg);
//        try {
//            JsonNode jsonNode = MAPPER.readTree(msg);
//            long toId = jsonNode.get("to").get("id").longValue();
//
//            WebSocketSession toSession = SESSIONS.get(toId);
//            if (toSession != null && toSession.isOpen()) {
//                toSession.sendMessage(new TextMessage(msg));
//                /**
//                 * 更新消息状态为已读
//                 * 当Message的ObjectId属性不加@JsonSerialize(using = ToStringSerializer.class)注解时
//                 * ObjectId属性是："id":{"timestamp":1565865013,"machineIdentifier":14785117,"
//                 * processIdentifier":11080,"counter":2634761,"time":1565865013000,
//                 * "date":1565865013000,"timeSecond":1565865013}
//                 *
//                 * 这里会抛异常，new ObjectId()需要一个十六进制的id值做为参数
//                 */
//                UpdateResult id = this.messageDAO.updateMessageState(new ObjectId(jsonNode.get("id").asText()), 2);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}