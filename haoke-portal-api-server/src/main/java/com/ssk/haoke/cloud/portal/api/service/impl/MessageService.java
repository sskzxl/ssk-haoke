package com.ssk.haoke.cloud.portal.api.service.impl;

import com.ssk.haoke.cloud.portal.api.dao.impl.MessageDAOImpl;
import com.ssk.haoke.cloud.portal.api.pojo.Message;
import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("messageService")
public class MessageService {
    @Autowired
    private MessageDAOImpl messageDAO;

    public RestResponse<List<Message>> queryMessageList(Long fromId, Long toId, Integer page,
                                                        Integer rows){
        List<Message> list = this.messageDAO.findListByFromAndTo(fromId, toId, page, rows);

        for (Message message : list) {
            if(message.getStatus() == 1){
                //修改消息狀態為已讀
                this.messageDAO.updateMessageState(message.getId(),2);
            }
        }

        return new RestResponse<>(list);

    }
    public Message findNearestMessage(Long fromId, Long toId){
        return this.messageDAO.findNearestMessage(fromId,toId);
    }
}
