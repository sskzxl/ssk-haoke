package com.ssk.haoke.cloud.server.im.api.impl;

import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import com.ssk.haoke.cloud.server.im.api.IMessageApi;
import com.ssk.haoke.cloud.server.im.pojo.Message;
import com.ssk.haoke.cloud.server.im.service.ImUserService;
import com.ssk.haoke.cloud.server.im.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class MessageApiImpl implements IMessageApi {
    @Autowired(required = false)
    private MessageService messageService;
    @Autowired(required = false)
    private ImUserService imUserService;
    @Override
    public RestResponse<List<Message>> queryMessageList(Long fromId, Long toId, Integer page, Integer rows) {
        return new RestResponse<>(messageService.queryMessageList(fromId, toId, page, rows));
    }

    @Override
    public RestResponse<List<Map<String, Object>>> queryUserList(Long fromId) {
        return new RestResponse<>(imUserService.queryUserList(fromId));
    }
}
